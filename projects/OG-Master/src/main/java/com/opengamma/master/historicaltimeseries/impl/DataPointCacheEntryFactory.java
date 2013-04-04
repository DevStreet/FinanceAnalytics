/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.historicaltimeseries.impl;

import java.util.concurrent.ConcurrentSkipListSet;

import org.threeten.bp.LocalDate;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.UniqueId;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesGetFilter;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeries;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.tuple.ObjectsPair;

import net.sf.ehcache.constructs.blocking.CacheEntryFactory;

public class DataPointCacheEntryFactory implements CacheEntryFactory {

  /** The underlying master. */
  private final HistoricalTimeSeriesMaster _underlying;

  public DataPointCacheEntryFactory(HistoricalTimeSeriesMaster underlying) {
    ArgumentChecker.notNull(underlying, "underlying");
    _underlying = underlying;
  }

  @Override
  public Object createEntry(Object key) throws Exception {

    // UniqueId request for a missing index node
    if (UniqueId.class.isAssignableFrom(key.getClass())) {
      // Return new, empty index node
      return new ConcurrentSkipListSet<LocalDate>();

    // UniqueId/start date request for a missing data-point range
    } else if (ObjectsPair.class.isAssignableFrom(key.getClass())
           && (UniqueId.class.isAssignableFrom(((ObjectsPair) key).getFirst().getClass()))
           && (LocalDate.class.isAssignableFrom(((ObjectsPair) key).getSecond().getClass()))) {
      // Return data-points from underlying (internal queries only)
      HistoricalTimeSeriesGetFilter historicalTimeSeriesGetFilter = HistoricalTimeSeriesGetFilter.ofRange(
          (LocalDate) ((ObjectsPair) key).getSecond(),
          ((LocalDate) ((ObjectsPair) key).getSecond()).plusDays(500) //TODO
      );
      ManageableHistoricalTimeSeries manageableHistoricalTimeSeries =
          _underlying.getTimeSeries((UniqueId) ((ObjectsPair) key).getFirst(), historicalTimeSeriesGetFilter);
      return manageableHistoricalTimeSeries.getTimeSeries();

    } else {
      throw new OpenGammaRuntimeException("Unsupported key type in SelfPopulatingCache");
    }
  }
}
