/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.historicaltimeseriesdatapoint;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.UniqueId;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesGetFilter;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchRequest;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchResult;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeries;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeriesInfo;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.timeseries.localdate.LocalDateDoubleTimeSeries;
import com.opengamma.util.tuple.ObjectsPair;

import java.util.Iterator;

public class HistoricalTimeSeriesDataPointMasterReader implements Iterable<LocalDateDoubleTimeSeries> {

  ManageableHistoricalTimeSeries _manageableHistoricalTimeSeries;

  public HistoricalTimeSeriesDataPointMasterReader(HistoricalTimeSeriesMaster historicalTimeSeriesMaster,
                                                   UniqueId uniqueId,
                                                   HistoricalTimeSeriesGetFilter historicalTimeSeriesGetFilter) {
    ArgumentChecker.notNull(historicalTimeSeriesMaster, "historicalTimeSeriesMaster");
    ArgumentChecker.notNull(uniqueId, "uniqueId");
    ArgumentChecker.notNull(historicalTimeSeriesGetFilter, "historicalTimeSeriesGetFilter");

    if (historicalTimeSeriesGetFilter == null) {
      historicalTimeSeriesGetFilter = HistoricalTimeSeriesGetFilter.ofAll();
    }
    _manageableHistoricalTimeSeries = historicalTimeSeriesMaster.getTimeSeries(uniqueId, historicalTimeSeriesGetFilter);
  }

  @Override
  public Iterator<LocalDateDoubleTimeSeries> iterator() {

    return new Iterator<LocalDateDoubleTimeSeries>() {

      LocalDateDoubleTimeSeries entireSeries = _manageableHistoricalTimeSeries.getTimeSeries();

      @Override
      public boolean hasNext() {
        return false;
      }

      @Override
      public LocalDateDoubleTimeSeries next() {
        return null;
      }

      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove operation is not supported");
      }
    };
  }
}
