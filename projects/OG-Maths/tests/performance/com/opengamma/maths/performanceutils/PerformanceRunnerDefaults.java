/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.performanceutils;

/**
 * Holder for performance runner common blocks 
 */
public class PerformanceRunnerDefaults {
  private CacheThrasher _thrasher = new CacheThrasher();
  private int _warmupRuns = 5000;
  private int _timedRuns = 5000;

  /**
   * Gets the warmup_runs.
   * @return the warmup_runs
   */
  public int getWarmupRuns() {
    return _warmupRuns;
  }

  /**
   * Gets the timed_runs.
   * @return the timed_runs
   */
  public int getTimedRuns() {
    return _timedRuns;
  }

  /**
   * Gets the thrasher.
   * @return the thrasher
   */
  public CacheThrasher getCacheThrasher() {
    return _thrasher;
  }

}
