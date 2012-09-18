/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.historicaltimeseries;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.integration.copiernew.historicaltimeseriesdatapoint.HistoricalTimeSeriesDataPointMasterReader;
import com.opengamma.master.config.ConfigDocument;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesGetFilter;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchRequest;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchResult;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeriesInfo;

import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.paging.PagingRequest;
import com.opengamma.util.timeseries.localdate.LocalDateDoubleTimeSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HistoricalTimeSeriesMasterReader implements Iterable<HistoricalTimeSeriesEntry> {


  private static final Logger s_logger = LoggerFactory.getLogger(HistoricalTimeSeriesMasterReader.class);

  private HistoricalTimeSeriesInfoSearchRequest _historicalTimeSeriesInfoSearchRequestTemplate;
  private HistoricalTimeSeriesMaster _historicalTimeSeriesMaster;
  private HistoricalTimeSeriesGetFilter _historicalTimeSeriesGetFilter;
  private int _datapointBufferSize;

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
        HistoricalTimeSeriesDataPointMasterReader.DEFAULT_BUFFER_SIZE, PagingRequest.DEFAULT_PAGING_SIZE);
  }

  public HistoricalTimeSeriesMasterReader(HistoricalTimeSeriesMaster historicalTimeSeriesMaster,
                                          HistoricalTimeSeriesInfoSearchRequest historicalTimeSeriesInfoSearchRequest,
                                          HistoricalTimeSeriesGetFilter historicalTimeSeriesGetFilter,
                                          int dataPointBufferSize, int bufferSize) {
    ArgumentChecker.notNull(historicalTimeSeriesMaster, "historicalTimeSeriesMaster");
    ArgumentChecker.notNegativeOrZero(dataPointBufferSize, "dataPointBufferSize");
    ArgumentChecker.notNegativeOrZero(bufferSize, "bufferSize");

    if (historicalTimeSeriesInfoSearchRequest == null) {
      historicalTimeSeriesInfoSearchRequest = new HistoricalTimeSeriesInfoSearchRequest();
      historicalTimeSeriesInfoSearchRequest.setPagingRequest(PagingRequest.ofIndex(0, bufferSize));
    }
    if (historicalTimeSeriesGetFilter == null) {
      historicalTimeSeriesGetFilter = HistoricalTimeSeriesGetFilter.ofAll();
    }
    _historicalTimeSeriesInfoSearchRequestTemplate = historicalTimeSeriesInfoSearchRequest;
    _historicalTimeSeriesGetFilter = historicalTimeSeriesGetFilter;
    _historicalTimeSeriesMaster = historicalTimeSeriesMaster;
    _datapointBufferSize = dataPointBufferSize;
  }

  @Override
  public Iterator<HistoricalTimeSeriesEntry> iterator() {
    return new Iterator<HistoricalTimeSeriesEntry>() {
  
      private int _nextPageIndex;
      private HistoricalTimeSeriesInfoSearchResult _historicalTimeSeriesInfoSearchResult;
      private Iterator<ManageableHistoricalTimeSeriesInfo> _iterator;
  
      {
        _nextPageIndex = 0;
        turnPage();
      }
  
      @Override
      public boolean hasNext() {
        if (!_iterator.hasNext()) {
          return (_historicalTimeSeriesInfoSearchResult != null) && (!_historicalTimeSeriesInfoSearchResult.getPaging().isLastPage());
        } else {
          return true;
        }
      }
  
      @Override
      public HistoricalTimeSeriesEntry next() {
        while (true) {
          try {
            ManageableHistoricalTimeSeriesInfo info = _iterator.next();
            Iterable<LocalDateDoubleTimeSeries> dataPointMasterReader =
                new HistoricalTimeSeriesDataPointMasterReader(
                    _historicalTimeSeriesMaster, info.getUniqueId(), _historicalTimeSeriesGetFilter, _datapointBufferSize
                );
            return new HistoricalTimeSeriesEntry(info, dataPointMasterReader);
          } catch (NoSuchElementException e) {
            if ((_historicalTimeSeriesInfoSearchResult != null) && (!_historicalTimeSeriesInfoSearchResult.getPaging().isLastPage())) {
              turnPage();
            } else {
              throw new NoSuchElementException();
            }
          }
        }
      }

      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove is not supported on this iterator");
      }
  
      private void turnPage() {
        HistoricalTimeSeriesInfoSearchRequest historicalTimeSeriesInfoSearchRequest = _historicalTimeSeriesInfoSearchRequestTemplate;
        historicalTimeSeriesInfoSearchRequest.setPagingRequest(
            PagingRequest.ofIndex(_nextPageIndex,
                _historicalTimeSeriesInfoSearchRequestTemplate.getPagingRequest().getPagingSize()));
        _nextPageIndex += _historicalTimeSeriesInfoSearchRequestTemplate.getPagingRequest().getPagingSize();
        try {
          _historicalTimeSeriesInfoSearchResult = _historicalTimeSeriesMaster.search(historicalTimeSeriesInfoSearchRequest);
          _iterator = _historicalTimeSeriesInfoSearchResult.getInfoList().iterator();
        } catch (Throwable t) {
          _iterator = new ArrayList<ManageableHistoricalTimeSeriesInfo>().iterator();
          s_logger.error("Error performing historical time series master search: " + t.getMessage());
        }
      }
    };
  }
  
}
