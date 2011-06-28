/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.view.calc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;
import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.engine.marketdata.MarketDataListener;
import com.opengamma.engine.marketdata.MarketDataProvider;
import com.opengamma.engine.marketdata.MarketDataSnapshot;
import com.opengamma.engine.marketdata.availability.MarketDataAvailabilityProvider;
import com.opengamma.engine.marketdata.spec.MarketDataSpecification;
import com.opengamma.engine.value.ValueRequirement;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.engine.view.ViewProcessContext;
import com.opengamma.engine.view.ViewProcessImpl;
import com.opengamma.engine.view.compilation.CompiledViewDefinitionWithGraphsImpl;
import com.opengamma.engine.view.compilation.ViewCompilationServices;
import com.opengamma.engine.view.compilation.ViewDefinitionCompiler;
import com.opengamma.engine.view.execution.ViewCycleExecutionOptions;
import com.opengamma.engine.view.execution.ViewExecutionFlags;
import com.opengamma.engine.view.execution.ViewExecutionOptions;
import com.opengamma.id.UniqueIdentifier;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.TerminatableJob;
import com.opengamma.util.monitor.OperationTimer;

/**
 * The job which schedules and executes computation cycles for a view process.
 */
public class ViewComputationJob extends TerminatableJob implements MarketDataListener {
  private static final Logger s_logger = LoggerFactory.getLogger(ViewComputationJob.class);
  
  private static final long MARKET_DATA_TIMEOUT_MILLIS = 10000;
  private static final long NANOS_PER_MILLISECOND = 1000000;

  private final ViewProcessImpl _viewProcess;
  private final ViewExecutionOptions _executionOptions;
  private final ViewProcessContext _processContext;
  private final EngineResourceManagerInternal<SingleComputationCycle> _cycleManager;
  private final boolean _executeCycles;
  
  private double _numExecutions;
  private EngineResourceReference<SingleComputationCycle> _previousCycleReference;
  
  // Nanoseconds - all 0 initially so that a full computation will run 
  private long _eligibleForDeltaComputationFromNanos;
  private long _deltaComputationRequiredByNanos; 
  private long _eligibleForFullComputationFromNanos;
  private long _fullComputationRequiredByNanos;
  private int _deltasSinceLastFull;
  
  private CompiledViewDefinitionWithGraphsImpl _latestCompiledViewDefinition;
  private final Set<ValueRequirement> _marketDataSubscriptions = new HashSet<ValueRequirement>();
  private final Set<ValueRequirement> _pendingSubscriptions = Collections.newSetFromMap(new ConcurrentHashMap<ValueRequirement, Boolean>());
  private CountDownLatch _pendingSubscriptionLatch;
  
  private volatile boolean _wakeOnMarketDataChanged;
  private volatile boolean _marketDataChanged;
  
  private enum ViewCycleType { FULL, DELTA, NONE }
  
  /**
   * Nanoseconds
   */
  private double _totalTimeNanos;

  private MarketDataProvider _marketDataProvider;
  
  public ViewComputationJob(ViewProcessImpl viewProcess, ViewExecutionOptions executionOptions,
      ViewProcessContext processContext, EngineResourceManagerInternal<SingleComputationCycle> cycleManager) {
    ArgumentChecker.notNull(viewProcess, "viewProcess");
    ArgumentChecker.notNull(executionOptions, "executionOptions");
    ArgumentChecker.notNull(processContext, "processContext");
    ArgumentChecker.notNull(cycleManager, "cycleManager");
    _viewProcess = viewProcess;
    _executionOptions = executionOptions;
    _processContext = processContext;
    _cycleManager = cycleManager;

    _executeCycles = !getExecutionOptions().getFlags().contains(ViewExecutionFlags.COMPILE_ONLY);
  }

  //-------------------------------------------------------------------------
  private ViewProcessImpl getViewProcess() {
    return _viewProcess;
  }
  
  private ViewExecutionOptions getExecutionOptions() {
    return _executionOptions;
  }
  
  private ViewProcessContext getProcessContext() {
    return _processContext;
  }
  
  private EngineResourceManagerInternal<SingleComputationCycle> getCycleManager() {
    return _cycleManager;
  }
  
  private void updateComputationTimes(long currentNanos, boolean deltaOnly) {
    // If time-based triggers disabled then always eligible for a cycle (if data changes), but never required
    boolean timeTriggersEnabled = getExecutionOptions().getFlags().contains(ViewExecutionFlags.TRIGGER_CYCLE_ON_TIME_ELAPSED);
    
    _eligibleForDeltaComputationFromNanos = timeTriggersEnabled ? getUpdatedTime(currentNanos, getViewProcess().getDefinition().getMinDeltaCalculationPeriod(), 0) : 0;
    _deltaComputationRequiredByNanos = timeTriggersEnabled ? getUpdatedTime(currentNanos, getViewProcess().getDefinition().getMaxDeltaCalculationPeriod(), Long.MAX_VALUE) : Long.MAX_VALUE;
    
    if (!deltaOnly) {
      _eligibleForFullComputationFromNanos = timeTriggersEnabled ? getUpdatedTime(currentNanos, getViewProcess().getDefinition().getMinFullCalculationPeriod(), 0) : 0;
      _fullComputationRequiredByNanos = timeTriggersEnabled ? getUpdatedTime(currentNanos, getViewProcess().getDefinition().getMaxFullCalculationPeriod(), Long.MAX_VALUE) : Long.MAX_VALUE;
    }
  }
  
  private long getUpdatedTime(long currentNanos, Long computationPeriod, long nullEquivalent) {
    if (computationPeriod == null) {
      return nullEquivalent;
    }

    return currentNanos + NANOS_PER_MILLISECOND * computationPeriod;
  }
  
  /**
   * Determines whether to run, and runs if required, a single computation cycle using the following rules:
   * 
   * <ul>
   *   <li>A computation cycle can only be triggered if the relevant minimum computation period has passed since the
   *   start of the previous cycle.
   *   <li>A computation cycle will be forced if the relevant maximum computation period has passed since the start of
   *   the previous cycle.
   *   <li>A full computation is preferred over a delta computation if both are possible.
   *   <li>Performing a full computation also updates the times to the next delta computation; i.e. a full computation
   *   is considered to be as good as a delta.
   * </ul>
   */
  @Override
  protected void runOneCycle() {
    // Exception handling is important here to ensure that computation jobs do not just die quietly while consumers are
    // potentially blocked, waiting for results.
    
    ViewCycleType cycleType = waitForNextCycle();
    if (cycleType == ViewCycleType.NONE) {
      // Will get back to runOneCycle() straight away unless the job has been terminated
      return;
    }
    
    ViewCycleExecutionOptions executionOptions = null;
    try {
      if (!getExecutionOptions().getExecutionSequence().isEmpty()) {
        executionOptions = getExecutionOptions().getExecutionSequence().getNext(getExecutionOptions().getDefaultExecutionOptions());
        s_logger.debug("Next cycle execution options: {}", executionOptions);
      }
      if (executionOptions == null) {
        s_logger.info("No more view cycle execution options");
        processCompleted();
        return;
      }
    } catch (Exception e) {
      s_logger.error("Error obtaining next view cycle execution options from sequence for view process " + getViewProcess(), e);
      return;
    }
    
    if (executionOptions.getMarketDataSpecification() == null) {
      s_logger.error("No market data specification for cycle");
      cycleExecutionFailed(executionOptions, new OpenGammaRuntimeException("No market data specification for cycle"));
      return;
    }
    
    if (getMarketDataProvider() == null || !getMarketDataProvider().isCompatible(executionOptions.getMarketDataSpecification())) {
      // A different market data provider is required. We support this because we can, but changing provider is not the
      // most efficient operation.
      if (getMarketDataProvider() != null) {
        s_logger.info("Replacing market data provider between cycles");
      }
      replaceMarketDataProvider(executionOptions.getMarketDataSpecification());
    }
    
    // Obtain the snapshot in case it is needed, but don't explicitly initialise it until the data is required
    MarketDataSnapshot marketDataSnapshot = getMarketDataProvider().snapshot(executionOptions.getMarketDataSpecification());
    
    Instant compilationValuationTime;
    if (executionOptions.getValuationTime() != null) {
      compilationValuationTime = executionOptions.getValuationTime();
    } else {
      // Neither the cycle-specific options nor the defaults have overridden the valuation time so use the time
      // associated with the market data snapshot. To avoid initialising the snapshot perhaps before the required
      // inputs are known or even subscribed to, only ask for an indication at the moment.
      compilationValuationTime = marketDataSnapshot.getSnapshotTimeIndication();
    }
    
    CompiledViewDefinitionWithGraphsImpl compiledViewDefinition = getCompiledViewDefinition(compilationValuationTime);
    
    if (getExecutionOptions().getFlags().contains(ViewExecutionFlags.AWAIT_MARKET_DATA)) {
      marketDataSnapshot.init(compiledViewDefinition.getMarketDataRequirements().keySet(), MARKET_DATA_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
    } else {
      marketDataSnapshot.init();
    }
    
    if (executionOptions.getValuationTime() == null) {
      executionOptions.setValuationTime(marketDataSnapshot.getSnapshotTime());
    }
    
    EngineResourceReference<SingleComputationCycle> cycleReference;
    try {
      cycleReference = createCycle(executionOptions, compiledViewDefinition);
    } catch (Exception e) {
      s_logger.error("Error creating next view cycle for view process " + getViewProcess(), e);
      return;
    }
    
    if (_executeCycles) {
      try {
        executeViewCycle(cycleType, cycleReference, marketDataSnapshot);
      } catch (InterruptedException e) {
        // Execution interrupted - don't propagate as failure
        s_logger.info("View cycle execution interrupted for view process {}", getViewProcess());
        cycleReference.release();
        return;
      } catch (Exception e) {
        // Execution failed
        s_logger.error("View cycle execution failed for view process " + getViewProcess(), e);
        cycleReference.release();
        cycleExecutionFailed(executionOptions, e);
        return;
      }
    }
    
    // Don't push the results through if we've been terminated, since another computation job could be running already
    // and the fact that we've been terminated means the view is no longer interested in the result. Just die quietly.
    if (isTerminated()) {
      cycleReference.release();
      return;
    }
    
    if (_executeCycles) {
      cycleCompleted(cycleReference.get());
    }
    
    if (getExecutionOptions().getExecutionSequence().isEmpty()) {
      processCompleted();
    }
    
    if (_executeCycles) {
      if (_previousCycleReference != null) {
        _previousCycleReference.release();
      }
      _previousCycleReference = cycleReference;
    }
  }

  private void cycleCompleted(ViewCycle cycle) {
    try {
      getViewProcess().cycleCompleted(cycle);
    } catch (Exception e) {
      s_logger.error("Error notifying view process " + getViewProcess() + " of view cycle completion", e);
    }
  }

  private void cycleExecutionFailed(ViewCycleExecutionOptions executionOptions, Exception exception) {
    try {
      getViewProcess().cycleExecutionFailed(executionOptions, exception);
    } catch (Exception vpe) {
      s_logger.error("Error notifying the view process " + getViewProcess() + " of the cycle execution error", vpe);
    }
  }
  
  private synchronized ViewCycleType waitForNextCycle() {
    long currentTime = System.nanoTime();
    
    boolean doFullRecalc = false;
    boolean doDeltaRecalc = false;
    
    if (requireFullCycleNext(currentTime)) {
      s_logger.debug("Forcing a full computation");
      doFullRecalc = true;
    } else if (requireDeltaCycleNext(currentTime)) {
      s_logger.debug("Forcing a delta computation");
      doDeltaRecalc = true;
    }
    
    if (_marketDataChanged) {
      s_logger.debug("Market data has changed");
      if (currentTime >= _eligibleForFullComputationFromNanos) {
        // Do (or upgrade to) a full computation because we're eligible for one
        s_logger.debug("Performing a full computation for the market data change");
        doFullRecalc = true;
        _marketDataChanged = false;
      } else if (currentTime >= _eligibleForDeltaComputationFromNanos) {
        // Do a delta computation
        s_logger.debug("Performing a delta computation for the market data change");
        doDeltaRecalc = true;
        _marketDataChanged = false;
      }
    }
    
    if (doFullRecalc || doDeltaRecalc) {
      // Set the times for the next computation cycle. These might have passed by the time this cycle completes, in
      // which case another cycle will run straight away.
      updateComputationTimes(currentTime, !doFullRecalc);
      
      return doFullRecalc ? ViewCycleType.FULL : ViewCycleType.DELTA;
    }
    
    // Going to sleep
    long minWakeUpTime = Math.min(_eligibleForDeltaComputationFromNanos, _eligibleForFullComputationFromNanos);
    long wakeUpTime;
    if (_marketDataChanged) {
      s_logger.debug("Sleeping until eligible to perform the next computation cycle");
      
      // Market data has arrived but we decided not to perform a computation cycle; this must be because we're not
      // eligible for one right now. We'll do one as soon as we are.
      wakeUpTime = minWakeUpTime;
      
      // No amount of market data can make us eligible for a computation cycle any sooner.
      _wakeOnMarketDataChanged = false;
    } else {
      s_logger.debug("Sleeping until forced to perform the next computation cycle");
      
      // Only *plan* to wake up when we really have to
      wakeUpTime = Math.min(_deltaComputationRequiredByNanos, _fullComputationRequiredByNanos);
      
      // If we're not scheduled to wake up until after we're eligible for a computation cycle, then allow us to be
      // woken sooner by market data changing. The benefit of this is when min=max, meaning market data will never wake
      // us up.
      _wakeOnMarketDataChanged = wakeUpTime > minWakeUpTime;
    }
    
    long sleepTime = wakeUpTime - currentTime;
    sleepTime = Math.max(0, sleepTime);
    sleepTime /= NANOS_PER_MILLISECOND;
    sleepTime += 1; // round up a bit to make sure it'll be enough
    s_logger.debug("Waiting for {} ms", sleepTime);
    try {
      // This could wait until end of time if both full and delta maximum recalc periods are null.
      // In this case, only marketDataChanged() will wake it up
      wait(sleepTime);
    } catch (InterruptedException e) {
      // We support interruption as a signal that we have been terminated. If we're interrupted without having been
      // terminated, we'll just return to this method and go back to sleep.
      Thread.interrupted();
      s_logger.info("Interrupted while delaying. Continuing operation.");
    }

    return ViewCycleType.NONE;
  }
  
  private void executeViewCycle(ViewCycleType cycleType, EngineResourceReference<SingleComputationCycle> cycleReference, MarketDataSnapshot marketDataSnapshot) throws Exception {
    SingleComputationCycle deltaCycle;
    if (cycleType == ViewCycleType.FULL) {
      s_logger.info("Performing full computation");
      _deltasSinceLastFull = 0;
      deltaCycle = null;
    } else {
      s_logger.info("Performing delta computation");
      _deltasSinceLastFull++;
      deltaCycle = _previousCycleReference.get();
    }
    
    try {
      cycleReference.get().execute(deltaCycle, marketDataSnapshot);
    } catch (InterruptedException e) {
      Thread.interrupted();
      // In reality this means that the job has been terminated, and it will end as soon as we return from this method.
      // In case the thread has been interrupted without terminating the job, we tidy everything up as if the
      // interrupted cycle never happened so that deltas will be calculated from the previous cycle.
      s_logger.info("Interrupted while executing a computation cycle. No results will be output from this cycle.");
      throw e;
    } catch (Exception e) {
      s_logger.error("Error while executing view cycle", e);
      throw e;
    }
    
    long duration = cycleReference.get().getDurationNanos();
    _totalTimeNanos += duration;
    _numExecutions += 1.0;
    s_logger.info("Last latency was {} ms, Average latency is {} ms", duration / NANOS_PER_MILLISECOND, (_totalTimeNanos / _numExecutions) / NANOS_PER_MILLISECOND);
  }
    
  @Override
  protected void postRunCycle() {
    if (_previousCycleReference != null) {
      _previousCycleReference.release();
    }
    removeMarketDataProvider();
    invalidateCachedCompiledViewDefinition();
  }
  
  private void processCompleted() {
    s_logger.info("Computation job completed for view process {}", getViewProcess());
    try {
      getViewProcess().processCompleted();
    } catch (Exception e) {
      s_logger.error("Error notifying view process " + getViewProcess() + " of computation job completion", e);
    }
    terminate();
  }
  
  public synchronized void marketDataChanged() {
    // REVIEW jonathan 2010-10-04 -- this synchronisation is necessary, but it feels very heavyweight for
    // high-frequency market data. See how it goes, but we could take into account the recalc periods and apply a
    // heuristic (e.g. only wake up due to market data if max - min < e, for some e) which tries to see whether it's
    // worth doing all this.
    
    s_logger.debug("Market Data changed");
    _marketDataChanged = true;
    if (!_wakeOnMarketDataChanged) {
      return;
    }
    notifyAll();
  }
  
  //-------------------------------------------------------------------------
  private EngineResourceReference<SingleComputationCycle> createCycle(ViewCycleExecutionOptions executionOptions, CompiledViewDefinitionWithGraphsImpl compiledViewDefinition) {
    // View definition was compiled based on compilation options, which might have only included an indicative
    // valuation time. A further check ensures that the compiled view definition is still valid.
    if (!compiledViewDefinition.isValidFor(executionOptions.getValuationTime())) {
      throw new OpenGammaRuntimeException("Compiled view definition " + compiledViewDefinition + " not valid for execution options " + executionOptions);
    }
    UniqueIdentifier cycleId = getViewProcess().generateCycleId();
    SingleComputationCycle cycle = new SingleComputationCycle(cycleId, getViewProcess().getUniqueId(), getProcessContext(), compiledViewDefinition, executionOptions);
    return getCycleManager().manage(cycle);
  }
  
  private CompiledViewDefinitionWithGraphsImpl getCompiledViewDefinition(Instant valuationTime) {
    long functionInitId = getProcessContext().getFunctionCompilationService().getFunctionCompilationContext().getFunctionInitId();
    CompiledViewDefinitionWithGraphsImpl compiledViewDefinition = getCachedCompiledViewDefinition();
    if (compiledViewDefinition != null && compiledViewDefinition.isValidFor(valuationTime) && functionInitId == compiledViewDefinition.getFunctionInitId()) {
      // Existing cached model is valid (an optimisation for the common case of similar, increasing valuation times)
      return compiledViewDefinition;
    }
    
    try {
      MarketDataAvailabilityProvider availabilityProvider = getMarketDataProvider().getAvailabilityProvider();
      ViewCompilationServices compilationServices = getProcessContext().asCompilationServices(availabilityProvider);
      compiledViewDefinition = ViewDefinitionCompiler.compile(getViewProcess().getDefinition(), compilationServices, valuationTime);
    } catch (Exception e) {
      getViewProcess().viewDefinitionCompilationFailed(valuationTime, e);
      throw new OpenGammaRuntimeException("Error compiling view definition", e);
    }
    setLatestCompiledViewDefinition(compiledViewDefinition);
    
    // Notify the view that a (re)compilation has taken place before going on to do any time-consuming work.
    // This might contain enough for clients to e.g. render an empty grid in which results will later appear. 
    getViewProcess().viewDefinitionCompiled(compiledViewDefinition, getMarketDataProvider().getPermissionProvider());
    
    // Update the market data subscriptions to whatever is now required, ensuring the computation cycle can find the
    // required input data when it is executed.
    setMarketDataSubscriptions(compiledViewDefinition.getMarketDataRequirements().keySet());
    return compiledViewDefinition;
  }
  
  /**
   * Gets the cached compiled view definition which may be re-used in subsequent computation cycles.
   * <p>
   * External visibility for tests.
   * 
   * @return the cached compiled view definition, or {@code null} if nothing is currently cached
   */
  public CompiledViewDefinitionWithGraphsImpl getCachedCompiledViewDefinition() {
    return _latestCompiledViewDefinition;
  }
  
  private void invalidateCachedCompiledViewDefinition() {
    _latestCompiledViewDefinition = null;
  }
  
  /**
   * Replaces the cached compiled view definition.
   * <p>
   * External visibility for tests.
   * 
   * @param latestCompiledViewDefinition  the compiled view definition, may be {@code null}
   */
  public void setLatestCompiledViewDefinition(CompiledViewDefinitionWithGraphsImpl latestCompiledViewDefinition) {
    _latestCompiledViewDefinition = latestCompiledViewDefinition;
  }
  
  private boolean requireFullCycleNext(long currentTime) {
    if (currentTime >= _fullComputationRequiredByNanos) {
      return true;
    }
    if (getExecutionOptions().getMaxSuccessiveDeltaCycles() == null) {
      return false;
    }
    return getExecutionOptions().getMaxSuccessiveDeltaCycles() <= _deltasSinceLastFull;
  }
  
  private boolean requireDeltaCycleNext(long currentTime) {
    if (getExecutionOptions().getFlags().contains(ViewExecutionFlags.RUN_AS_FAST_AS_POSSIBLE)) {
      // Run as fast as possible on delta cycles, with full cycles as required by the view definition and execution options
      return true;
    }
    return currentTime >= _deltaComputationRequiredByNanos;
  }

  //-------------------------------------------------------------------------
  private void replaceMarketDataProvider(MarketDataSpecification marketDataSpec) {
    removeMarketDataProvider();
    // A different market data provider may change the availability of market data, altering the dependency graph
    invalidateCachedCompiledViewDefinition();
    setMarketDataProvider(marketDataSpec);
  }
  
  private void removeMarketDataProvider() {
    if (_marketDataProvider == null) {
      return;
    }
    removeMarketDataSubscriptions();
    _marketDataProvider.removeListener(this);
    _marketDataProvider = null;
  }
  
  private MarketDataProvider getMarketDataProvider() {
    return _marketDataProvider;
  }
  
  private void setMarketDataProvider(MarketDataSpecification marketDataSpec) {
    _marketDataProvider = getProcessContext().getMarketDataProviderResolver().resolve(marketDataSpec);
    _marketDataProvider.addListener(this);
  }
  
  private void setMarketDataSubscriptions(final Set<ValueRequirement> requiredSubscriptions) {
    final Set<ValueRequirement> currentSubscriptions = _marketDataSubscriptions;
    final Set<ValueRequirement> unusedMarketData = Sets.difference(currentSubscriptions, requiredSubscriptions);
    if (!unusedMarketData.isEmpty()) {
      s_logger.debug("{} unused market data subscriptions: {}", unusedMarketData.size(), unusedMarketData);
      removeMarketDataSubscriptions(unusedMarketData);
    }
    final Set<ValueRequirement> newMarketData = Sets.difference(requiredSubscriptions, currentSubscriptions);
    if (!newMarketData.isEmpty()) {
      s_logger.debug("{} new market data requirements: {}", newMarketData.size(), newMarketData);
      addMarketDataSubscriptions(newMarketData);
    }
  }

  //-------------------------------------------------------------------------
  private void addMarketDataSubscriptions(final Set<ValueRequirement> requiredSubscriptions) {
    final OperationTimer timer = new OperationTimer(s_logger, "Adding {} market data subscriptions", requiredSubscriptions.size());
    _pendingSubscriptions.addAll(requiredSubscriptions);
    _pendingSubscriptionLatch = new CountDownLatch(requiredSubscriptions.size());
    getMarketDataProvider().subscribe(getViewProcess().getDefinition().getMarketDataUser(), requiredSubscriptions);
    _marketDataSubscriptions.addAll(requiredSubscriptions);
    try {
      if (!_pendingSubscriptionLatch.await(MARKET_DATA_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
        long remainingCount = _pendingSubscriptionLatch.getCount();
        s_logger.warn("Timed out after {} ms waiting for market data subscriptions to be made. The market data " +
            "snapshot used in the computation cycle could be incomplete. Still waiting for {} out of {} market data " +
            "subscriptions",
          new Object[] {MARKET_DATA_TIMEOUT_MILLIS, remainingCount, _marketDataSubscriptions.size()});
      }
    } catch (InterruptedException ex) {
      s_logger.info("Interrupted while waiting for subscription results.");
    } finally {
      _pendingSubscriptions.clear();
      _pendingSubscriptionLatch = null;
    }
    timer.finished();
  }
  
  private void removePendingSubscription(ValueRequirement requirement) {
    CountDownLatch pendingSubscriptionLatch = _pendingSubscriptionLatch;
    if (_pendingSubscriptions.remove(requirement) && pendingSubscriptionLatch != null) {
      pendingSubscriptionLatch.countDown();
    }
  }
  
  private void removeMarketDataSubscriptions() {
    removeMarketDataSubscriptions(_marketDataSubscriptions);
  }

  private void removeMarketDataSubscriptions(final Set<ValueRequirement> unusedSubscriptions) {
    final OperationTimer timer = new OperationTimer(s_logger, "Removing {} market data subscriptions", unusedSubscriptions.size());
    // [ENG-251] TODO getLiveDataSnapshotProvider().removeSubscription(getDefinition().getLiveDataUser(), requiredLiveData);
    _marketDataSubscriptions.removeAll(unusedSubscriptions);
    timer.finished();
  }
  
  //-------------------------------------------------------------------------
  @Override
  public void subscriptionSucceeded(ValueRequirement requirement) {
    // REVIEW jonathan 2011-01-07
    // Can't tell in general whether this subscription message was relating to a subscription that we made or one that
    // a concurrent user of the MarketDataProvider made.
    s_logger.debug("Subscription succeeded: {}", requirement);
    removePendingSubscription(requirement);
  }

  @Override
  public void subscriptionFailed(ValueRequirement requirement, String msg) {
    s_logger.warn("Market data subscription to {} failed. This market data may be missing from computation cycles.", requirement);
    removePendingSubscription(requirement);
  }

  @Override
  public void subscriptionStopped(ValueRequirement requirement) {   
  }

  @Override
  public void valueChanged(ValueRequirement value) {
    if (!getExecutionOptions().getFlags().contains(ViewExecutionFlags.TRIGGER_CYCLE_ON_MARKET_DATA_CHANGED)) {
      return;
    }
    
    CompiledViewDefinitionWithGraphsImpl compiledView = getCachedCompiledViewDefinition();
    if (compiledView == null) {
      return;
    }
    Map<ValueRequirement, ValueSpecification> marketDataRequirements = compiledView.getMarketDataRequirements();
    if (marketDataRequirements.containsKey(value)) {
      marketDataChanged();
    }
  }

}
