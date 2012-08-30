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

  HistoricalTimeSeriesMaster _historicalTimeSeriesMaster;
  HistoricalTimeSeriesGetFilter _historicalTimeSeriesGetFilter;
  int _bufferSize;
  UniqueId _uniqueId;

  public HistoricalTimeSeriesDataPointMasterReader(HistoricalTimeSeriesMaster historicalTimeSeriesMaster,
                                                   UniqueId uniqueId,
                                                   HistoricalTimeSeriesGetFilter historicalTimeSeriesGetFilter,
                                                   int bufferSize) {

    ArgumentChecker.notNull(historicalTimeSeriesMaster, "historicalTimeSeriesMaster");
    ArgumentChecker.notNull(uniqueId, "uniqueId");
    ArgumentChecker.notNull(historicalTimeSeriesGetFilter, "historicalTimeSeriesGetFilter");
    ArgumentChecker.notNegativeOrZero(bufferSize, "bufferSize");

    _historicalTimeSeriesMaster = historicalTimeSeriesMaster;
    _uniqueId = uniqueId;
    _historicalTimeSeriesGetFilter = historicalTimeSeriesGetFilter;

    _bufferSize = bufferSize;
  }

  @Override
  public Iterator<LocalDateDoubleTimeSeries> iterator() {

    return new Iterator<LocalDateDoubleTimeSeries>() {

      HistoricalTimeSeriesGetFilter _localFilter = HistoricalTimeSeriesGetFilter.ofRange(
          _historicalTimeSeriesGetFilter.getEarliestDate(),
          _historicalTimeSeriesGetFilter.getLatestDate(),
          _bufferSize
      );
      LocalDateDoubleTimeSeries _buffer = _historicalTimeSeriesMaster.getTimeSeries(_uniqueId, _localFilter).getTimeSeries();

      @Override
      public boolean hasNext() {
        return (_buffer != null && !_buffer.isEmpty());
      }

      @Override
      public LocalDateDoubleTimeSeries next() {
        LocalDateDoubleTimeSeries result = _buffer;
        _localFilter = HistoricalTimeSeriesGetFilter.ofRange(
            _historicalTimeSeriesGetFilter.getEarliestDate(),
            _localFilter.getEarliestDate().minusDays(1)
        );
        _buffer = _historicalTimeSeriesMaster.getTimeSeries(_uniqueId, _localFilter).getTimeSeries();
        return result;
      }

      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove operation is not supported");
      }
    };
  }
}
