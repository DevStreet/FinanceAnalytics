/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.historicaltimeseries;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copiernew.historicaltimeseriesdatapoint.HistoricalTimeSeriesDataPointMasterReader;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesGetFilter;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchRequest;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchResult;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeries;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeriesInfo;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.timeseries.localdate.LocalDateDoubleTimeSeries;
import com.opengamma.util.tuple.ObjectsPair;

import java.util.Iterator;

public class HistoricalTimeSeriesMasterReader
    implements Iterable<ObjectsPair<ManageableHistoricalTimeSeriesInfo, Iterable<LocalDateDoubleTimeSeries>>> {

  private HistoricalTimeSeriesMaster _historicalTimeSeriesMaster;
  private HistoricalTimeSeriesInfoSearchResult _historicalTimeSeriesInfoSearchResult;
  private HistoricalTimeSeriesGetFilter _historicalTimeSeriesGetFilter;
  private int _bufferSize;

  public HistoricalTimeSeriesMasterReader(HistoricalTimeSeriesMaster historicalTimeSeriesMaster) {
    this(historicalTimeSeriesMaster, null);
  }

  public HistoricalTimeSeriesMasterReader(HistoricalTimeSeriesMaster historicalTimeSeriesMaster,
                                          HistoricalTimeSeriesInfoSearchRequest historicalTimeSeriesInfoSearchRequest) {
    this(historicalTimeSeriesMaster, historicalTimeSeriesInfoSearchRequest,
        null);
  }

  public HistoricalTimeSeriesMasterReader(HistoricalTimeSeriesMaster historicalTimeSeriesMaster,
                                          HistoricalTimeSeriesInfoSearchRequest historicalTimeSeriesInfoSearchRequest,
                                          HistoricalTimeSeriesGetFilter historicalTimeSeriesGetFilter) {
    this(historicalTimeSeriesMaster, historicalTimeSeriesInfoSearchRequest, historicalTimeSeriesGetFilter,
        HistoricalTimeSeriesDataPointMasterReader.DEFAULT_BUFFER_SIZE);
  }

  public HistoricalTimeSeriesMasterReader(HistoricalTimeSeriesMaster historicalTimeSeriesMaster,
                                          HistoricalTimeSeriesInfoSearchRequest historicalTimeSeriesInfoSearchRequest,
                                          HistoricalTimeSeriesGetFilter historicalTimeSeriesGetFilter,
                                          int bufferSize) {
    ArgumentChecker.notNull(historicalTimeSeriesMaster, "historicalTimeSeriesMaster");
    ArgumentChecker.notNegativeOrZero(bufferSize, "bufferSize");

    if (historicalTimeSeriesInfoSearchRequest == null) {
      historicalTimeSeriesInfoSearchRequest = new HistoricalTimeSeriesInfoSearchRequest();
    }
    if (historicalTimeSeriesGetFilter == null) {
      historicalTimeSeriesGetFilter = HistoricalTimeSeriesGetFilter.ofAll();
    }
    _historicalTimeSeriesInfoSearchResult = historicalTimeSeriesMaster.search(historicalTimeSeriesInfoSearchRequest);
    _historicalTimeSeriesGetFilter = historicalTimeSeriesGetFilter;
    _historicalTimeSeriesMaster = historicalTimeSeriesMaster;
    _bufferSize = bufferSize;
  }

  @Override
  public Iterator<ObjectsPair<ManageableHistoricalTimeSeriesInfo, Iterable<LocalDateDoubleTimeSeries>>> iterator() {

    return new Iterator<ObjectsPair<ManageableHistoricalTimeSeriesInfo, Iterable<LocalDateDoubleTimeSeries>>>() {
      Iterator<ManageableHistoricalTimeSeriesInfo> _iterator =
          _historicalTimeSeriesInfoSearchResult.getInfoList().iterator();

      @Override
      public boolean hasNext() {
        return _iterator.hasNext();
      }

      @Override
      public ObjectsPair<ManageableHistoricalTimeSeriesInfo, Iterable<LocalDateDoubleTimeSeries>> next() {
        ManageableHistoricalTimeSeriesInfo info = _iterator.next();
        Iterable<LocalDateDoubleTimeSeries> dataPointMasterReader =
            new HistoricalTimeSeriesDataPointMasterReader(
                _historicalTimeSeriesMaster, info.getUniqueId(), _historicalTimeSeriesGetFilter, _bufferSize
            );
        return new ObjectsPair<ManageableHistoricalTimeSeriesInfo, Iterable<LocalDateDoubleTimeSeries>>(
            info, dataPointMasterReader);
      }

      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove operation is not supported");
      }
    };
  }
}
