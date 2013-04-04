/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.historicaltimeseries.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentSkipListSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;

import com.opengamma.core.change.BasicChangeManager;
import com.opengamma.core.change.ChangeEvent;
import com.opengamma.core.change.ChangeListener;
import com.opengamma.core.change.ChangeManager;
import com.opengamma.id.ObjectId;
import com.opengamma.id.ObjectIdentifiable;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.master.cache.AbstractEHCachingMaster;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesGetFilter;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeries;
import com.opengamma.timeseries.localdate.ArrayLocalDateDoubleTimeSeries;
import com.opengamma.timeseries.localdate.LocalDateDoubleTimeSeries;
import com.opengamma.util.tuple.ObjectsPair;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.constructs.blocking.SelfPopulatingCache;

/*
 * TS KEY consists of:
 *   ObjectId (of dp)
 *   VersionAsOf (Instant)  OR    UniqueId (of dp)
 *   CorrectedTo (Instant)
 * TS VALUE consists of:
 *   list of:
 *     earliestDate (LocalDate)
 *     latestDate (LocalDate)
 *
 * TS RANGE KEY consists of:
 *   ObjectId (of dp)
 *   VersionAsOf (Instant)
 *   CorrectedTo (Instant)
 *   earliestDate (LocalDate)
 *   latestDate (LocalDate)
 * TS RANGE VALUE consists of:
 *   time series data point list

 *
 * NO maxPoints (revise earliestDate and latestDate instead)
 *
 * ObjectsPair<UniqueId, HistoricalTimeSeriesGetFilter>
 */

public class EHCachingDataPointCache {

  /** Logger. */
  private static final Logger s_logger = LoggerFactory.getLogger(AbstractEHCachingMaster.class);
  /** Cache name. */
  private static final String CACHE_NAME_SUFFIX = "HistoricalTimeSeriesDataPointCache";
  /** The underlying master. */
  private final HistoricalTimeSeriesMaster _underlying;
  /** The cache manager. */
  private final CacheManager _cacheManager;
  /** Listens for changes in the underlying security source. */
  private final ChangeListener _changeListener;
  /** The local change manager. */
  private final ChangeManager _changeManager;
  /** The document cache indexed by UniqueId. */
  private final Ehcache _dataPointCache;

  public EHCachingDataPointCache(final String name,
                                 final HistoricalTimeSeriesMaster underlying,
                                 final CacheManager cacheManager) {
    _underlying = underlying;
    _cacheManager = cacheManager;

    if (cacheManager.getCache(name + CACHE_NAME_SUFFIX) == null) {
      // If cache config not found, set up from scratch
      s_logger.warn("Could not load cache configuration for " + name + CACHE_NAME_SUFFIX
                  + ", building configuration on the fly instead");
      getCacheManager().addCache(new Cache(tweakCacheConfiguration(new CacheConfiguration(name + CACHE_NAME_SUFFIX,
                                                                                          10000))));
    } else {
      // If cache config found, tweak loaded config
      tweakCacheConfiguration(cacheManager.getCache(name + CACHE_NAME_SUFFIX).getCacheConfiguration());
    }

    // Create the self-populating cache decorator
    _dataPointCache = new SelfPopulatingCache(_cacheManager.getCache(name + CACHE_NAME_SUFFIX),
                                                  new DataPointCacheEntryFactory(_underlying));
    getCacheManager().replaceCacheWithDecoratedCache(_cacheManager.getCache(name + CACHE_NAME_SUFFIX),
                                                     getDataPointCache());

    // Listen to change events from underlying, clean this cache accordingly and relay events to our change listeners
    _changeManager = new BasicChangeManager();
    _changeListener = new ChangeListener() {
      @Override
      public void entityChanged(ChangeEvent event) {
        final ObjectId oid = event.getObjectId();
        final Instant versionFrom = event.getVersionFrom();
        final Instant versionTo = event.getVersionTo();
        cleanCaches(oid, versionFrom, versionTo);
        _changeManager.entityChanged(event.getType(), event.getObjectId(),
            event.getVersionFrom(), event.getVersionTo(), event.getVersionInstant());
      }
    };
    underlying.changeManager().addChangeListener(_changeListener);
  }

  private CacheConfiguration tweakCacheConfiguration(CacheConfiguration cacheConfiguration) {
    // Set searchable index
    //Searchable uidToDocumentCacheSearchable = new Searchable();
    //uidToDocumentCacheSearchable.addSearchAttribute(new SearchAttribute().name("ObjectId")
    //    .expression("value.getObjectId().toString()"));
    //uidToDocumentCacheSearchable.addSearchAttribute(new SearchAttribute().name("VersionFromInstant")
    //    .className("com.opengamma.master.cache.InstantExtractor"));
    //uidToDocumentCacheSearchable.addSearchAttribute(new SearchAttribute().name("VersionToInstant")
    //                                                    .className("com.opengamma.master.cache.InstantExtractor"));
    //uidToDocumentCacheSearchable.addSearchAttribute(new SearchAttribute().name("CorrectionFromInstant")
    //                                                    .className("com.opengamma.master.cache.InstantExtractor"));
    //uidToDocumentCacheSearchable.addSearchAttribute(new SearchAttribute().name("CorrectionToInstant")
    //                                                    .className("com.opengamma.master.cache.InstantExtractor"));
    //cacheConfiguration.addSearchable(uidToDocumentCacheSearchable);

    // Make copies of cached objects
    //CopyStrategyConfiguration copyStrategyConfiguration = new CopyStrategyConfiguration();
    //copyStrategyConfiguration.setClass("com.opengamma.master.cache.JodaBeanCopyStrategy");
    //cacheConfiguration.addCopyStrategy(copyStrategyConfiguration);
    cacheConfiguration.setCopyOnRead(true);
    cacheConfiguration.setCopyOnWrite(true);

    return cacheConfiguration;
  }


  public ManageableHistoricalTimeSeries getTimeSeries(UniqueId uniqueId) {
    HistoricalTimeSeriesGetFilter filter = HistoricalTimeSeriesGetFilter.ofAll();
    return getTimeSeries(uniqueId, filter);
  }

  public ManageableHistoricalTimeSeries getTimeSeries(UniqueId uniqueId,
                                                      HistoricalTimeSeriesGetFilter filter) {
    final VersionCorrection vc;
    if (uniqueId.isVersioned()) {
      vc = extractTimeSeriesInstants(uniqueId);
    } else {
      vc = VersionCorrection.LATEST;
    }
    return getTimeSeries(uniqueId.getObjectId(), vc, filter);
  }

  public ManageableHistoricalTimeSeries getTimeSeries(ObjectIdentifiable objectId, VersionCorrection versionCorrection) {
    HistoricalTimeSeriesGetFilter filter = HistoricalTimeSeriesGetFilter.ofAll();
    return getTimeSeries(objectId, versionCorrection, filter);
  }

  public ManageableHistoricalTimeSeries getTimeSeries(ObjectIdentifiable objectId, VersionCorrection versionCorrection,
                                                      HistoricalTimeSeriesGetFilter filter) {
    // Build ts unique id
    UniqueId uniqueId = createTimeSeriesUniqueId(objectId.getObjectId(),
                                                 versionCorrection);

    // Get the cached index entry, keyed by ts unique id
    Element indexElement = getDataPointCache().get(uniqueId);

    // Keep track of missing date ranges, for insertion in index element
    List<LocalDate> missingDateRanges = new ArrayList<>();

    // Get the index sub-range we're interested in
    NavigableSet<LocalDate> indexDates;
    if (indexElement != null) { // is this required in self-populating cache?
      indexDates = ((NavigableSet<LocalDate>) indexElement.getObjectValue())
          .subSet(fixDate(filter.getEarliestDate(), null, LocalDate.MIN), true,
                  fixDate(filter.getLatestDate(), null, LocalDate.MAX), true);
     } else {
      // Create an empty index if not yet cached (unreachable?)
      indexDates = new ConcurrentSkipListSet<>();
    }

    // TODO deal with maxpoints by fetching chunks in the appropriate direction and stopping when maxpoints have been fetched

    // TODO Get all the ranges, filling in any gaps from underlying and coalescing the cached range if necessary
    // (up to an upper range size limit)
    LocalDateDoubleTimeSeries timeSeries = new ArrayLocalDateDoubleTimeSeries();

    // Fill out all data-points if none are cached
    if (indexDates.isEmpty()) {
      LocalDateDoubleTimeSeries missing = cacheMissingRange(uniqueId, filter.getEarliestDate(), filter.getLatestDate());
      timeSeries = (LocalDateDoubleTimeSeries) timeSeries.unionMaximum(missing);
      missingDateRanges.add(fixDate(filter.getEarliestDate(), null, LocalDate.MIN));

    // At least some data-points are cached, fill in the missing ranges
    } else {
      // Keep track of where we're at, starting from the earliest date in the requested range
      LocalDate nextDate = fixDate(filter.getEarliestDate(), null, LocalDate.MIN);

      // Fill out first stretch of data-points if not already cached
      if (indexDates.first().isAfter(nextDate)) {
        timeSeries.add(cacheMissingRange(uniqueId, filter.getEarliestDate(), indexDates.first()));
        nextDate = indexDates.first();
      }

      for (LocalDate indexDate : indexDates) {

        // Get missing range, if any, from underlying
        if (nextDate.isBefore(indexDate)) {

          // Get the missing data-points from underlying and cache them
          timeSeries.add(cacheMissingRange(uniqueId, fixDate(nextDate, LocalDate.MIN, null), indexDate));

          // Add start index of missing data-points to the index
          missingDateRanges.add(nextDate);
        }

        // Use the next available cached range
        Element element = getDataPointCache().get(new ObjectsPair<>(uniqueId, indexDate));

        if (element != null && element.getObjectValue() != null) {
          HistoricalTimeSeriesGetFilter historicalTimeSeriesGetFilter =
              ((ObjectsPair<HistoricalTimeSeriesGetFilter, LocalDateDoubleTimeSeries>) element.getObjectValue()).getFirst();
          LocalDateDoubleTimeSeries localDateDoubleTimeSeries =
              ((ObjectsPair<HistoricalTimeSeriesGetFilter, LocalDateDoubleTimeSeries>) element.getObjectValue()).getSecond();

          // Add (append) cached data points to result
          timeSeries = (LocalDateDoubleTimeSeries) timeSeries.unionMaximum(localDateDoubleTimeSeries);

          // Update next pointer to continue from next day after end of current range
          nextDate = fixDate(historicalTimeSeriesGetFilter.getLatestDate(), null, LocalDate.MAX);
          if (nextDate.isBefore(LocalDate.MAX)) {
            nextDate = nextDate.plusDays(1);
          }
        }
      }

    }

    // Update the cached range list only if new ranges were cached
    if (!missingDateRanges.isEmpty()) {
      indexDates.addAll(missingDateRanges);
      getDataPointCache().put(new Element(uniqueId, indexDates));
    }

    // Build the result
    ManageableHistoricalTimeSeries manageableHistoricalTimeSeries = new ManageableHistoricalTimeSeries();
    manageableHistoricalTimeSeries.setUniqueId(uniqueId);
    manageableHistoricalTimeSeries.setVersionInstant(versionCorrection.getVersionAsOf());
    manageableHistoricalTimeSeries.setCorrectionInstant(versionCorrection.getCorrectedTo());
    manageableHistoricalTimeSeries.setTimeSeries(timeSeries.subSeries(fixDate(filter.getEarliestDate(), null, LocalDate.MIN),
                                                                      fixDate(filter.getLatestDate(), null, LocalDate.MAX)));
    return manageableHistoricalTimeSeries;
  }

  //--------------------------------------------------------------------------------------------------------------------

  public UniqueId updateTimeSeriesDataPoints(ObjectIdentifiable objectId, LocalDateDoubleTimeSeries series) {
    return getUnderlying().updateTimeSeriesDataPoints(objectId, series);  // TODO
  }

  public UniqueId correctTimeSeriesDataPoints(ObjectIdentifiable objectId, LocalDateDoubleTimeSeries series) {
    return getUnderlying().correctTimeSeriesDataPoints(objectId, series);  // TODO
  }

  public UniqueId removeTimeSeriesDataPoints(ObjectIdentifiable objectId,
                                             LocalDate fromDateInclusive,
                                             LocalDate toDateInclusive) {
    return getUnderlying().removeTimeSeriesDataPoints(objectId, fromDateInclusive, toDateInclusive);  // TODO
  }

  //--------------------------------------------------------------------------------------------------------------------

  private LocalDate fixDate(LocalDate localDate, LocalDate toReplace, LocalDate replaceWith) {
    return localDate != toReplace ? localDate : replaceWith;
  }

  private LocalDateDoubleTimeSeries cacheMissingRange(UniqueId uniqueId, LocalDate fromDate, LocalDate toDate) {
     HistoricalTimeSeriesGetFilter historicalTimeSeriesGetFilter =
         HistoricalTimeSeriesGetFilter.ofRange(fixDate(fromDate, LocalDate.MIN, null), fixDate(toDate, LocalDate.MAX, null));
     ManageableHistoricalTimeSeries manageableHistoricalTimeSeries =
         getUnderlying().getTimeSeries(uniqueId, historicalTimeSeriesGetFilter);

     // Cache the missing range (TODO in chunks)
     getDataPointCache().put(new Element(
         new ObjectsPair<>(uniqueId, fixDate(fromDate, null, LocalDate.MIN)),
         new ObjectsPair<>(historicalTimeSeriesGetFilter, manageableHistoricalTimeSeries.getTimeSeries())
     ));

     return manageableHistoricalTimeSeries.getTimeSeries();
   }

  /**
   * Extracts the instants from the unique identifier.
   *
   * @param uniqueId  the unique identifier, not null
   * @return the instants, version, correction, not null
   */
  public static VersionCorrection extractTimeSeriesInstants(UniqueId uniqueId) {
    try {
      int pos = uniqueId.getVersion().indexOf('P');
      String verStr = uniqueId.getVersion().substring(0, pos);
      String corrStr = uniqueId.getVersion().substring(pos);
      Instant ver = Instant.parse(verStr);
      Instant corr = ver.plus(Duration.parse(corrStr));
      return VersionCorrection.of(ver, corr);
    } catch (RuntimeException ex) {
      throw new IllegalArgumentException("UniqueId is not from this master (invalid version): " + uniqueId, ex);
    }
  }

  protected static UniqueId createTimeSeriesUniqueId(ObjectId objectId, VersionCorrection versionCorrection) {
    if (versionCorrection.getVersionAsOf() == null && versionCorrection.getCorrectedTo() == null) {
      // Latest version/correction
      return objectId.atLatestVersion();
    } else {
      // Use a specific version/correction
      Duration dur = Duration.between(versionCorrection.withLatestFixed(Instant.now()).getVersionAsOf(),
                                      versionCorrection.withLatestFixed(Instant.now()).getCorrectedTo());
      String verStr = versionCorrection.withLatestFixed(Instant.now()).getVersionAsOf().toString() + dur.toString();
      return UniqueId.of(objectId, verStr);
    }
  }

  private void cleanCaches(ObjectId objectId, Instant fromVersion, Instant toVersion) {

    //Results results = getDataPointCache().createQuery().includeKeys()
    //    .includeAttribute(getDataPointCache().getSearchAttribute("ObjectId"))
    //    .includeAttribute(getDataPointCache().getSearchAttribute("VersionFromInstant"))
    //    .includeAttribute(getDataPointCache().getSearchAttribute("VersionToInstant"))
    //    .addCriteria(getDataPointCache().getSearchAttribute("ObjectId")
    //                     .eq(objectId.toString()))
    //    .addCriteria(getDataPointCache().getSearchAttribute("VersionFromInstant")
    //                     .le((fromVersion != null ? fromVersion : InstantExtractor.MIN_INSTANT).toString()))
    //    .addCriteria(getDataPointCache().getSearchAttribute("VersionToInstant")
    //                     .ge((toVersion != null ? toVersion : InstantExtractor.MAX_INSTANT).toString()))
    //    .execute();
    //
    //for (Result result : results.all()) {
    //  getDataPointCache().remove(result.getKey());
    //}
  }

  public HistoricalTimeSeriesMaster getUnderlying() {
    return _underlying;
  }

  /**
   * Gets the cache manager.
   *
   * @return the cache manager, not null
   */
  protected CacheManager getCacheManager() {
    return _cacheManager;
  }

  public Ehcache getDataPointCache() {
    return _dataPointCache;
  }

}
