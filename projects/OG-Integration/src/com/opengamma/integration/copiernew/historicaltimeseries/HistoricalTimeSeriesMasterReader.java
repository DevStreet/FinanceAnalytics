/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.historicaltimeseries;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesGetFilter;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchRequest;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchResult;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeries;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeriesInfo;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.tuple.ObjectsPair;

import java.util.Iterator;

public class HistoricalTimeSeriesMasterReader
    implements Iterable<ObjectsPair<ManageableHistoricalTimeSeriesInfo, ManageableHistoricalTimeSeries>> {

  private HistoricalTimeSeriesMaster _historicalTimeSeriesMaster;
  private HistoricalTimeSeriesInfoSearchResult _historicalTimeSeriesInfoSearchResult;
  private HistoricalTimeSeriesGetFilter _historicalTimeSeriesGetFilter;

  public HistoricalTimeSeriesMasterReader(HistoricalTimeSeriesMaster historicalTimeSeriesMaster) {
    this(historicalTimeSeriesMaster, null, null);
  }

  public HistoricalTimeSeriesMasterReader(HistoricalTimeSeriesMaster historicalTimeSeriesMaster,
                                          HistoricalTimeSeriesInfoSearchRequest historicalTimeSeriesInfoSearchRequest,
                                          HistoricalTimeSeriesGetFilter historicalTimeSeriesGetFilter) {
    ArgumentChecker.notNull(historicalTimeSeriesMaster, "historicalTimeSeriesMaster");
    if (historicalTimeSeriesInfoSearchRequest == null) {
      historicalTimeSeriesInfoSearchRequest = new HistoricalTimeSeriesInfoSearchRequest();
    }
    if (historicalTimeSeriesGetFilter == null) {
      historicalTimeSeriesGetFilter = HistoricalTimeSeriesGetFilter.ofAll();
    }
    _historicalTimeSeriesInfoSearchResult = historicalTimeSeriesMaster.search(historicalTimeSeriesInfoSearchRequest);
    _historicalTimeSeriesGetFilter = historicalTimeSeriesGetFilter;
    _historicalTimeSeriesMaster = historicalTimeSeriesMaster;
  }

  @Override
  public Iterator<ObjectsPair<ManageableHistoricalTimeSeriesInfo, ManageableHistoricalTimeSeries>> iterator() {

    return new Iterator<ObjectsPair<ManageableHistoricalTimeSeriesInfo, ManageableHistoricalTimeSeries>>() {
      Iterator<ManageableHistoricalTimeSeriesInfo> _iterator =
          _historicalTimeSeriesInfoSearchResult.getInfoList().iterator();

      @Override
      public boolean hasNext() {
        return _iterator.hasNext();
      }

      @Override
      public ObjectsPair<ManageableHistoricalTimeSeriesInfo, ManageableHistoricalTimeSeries> next() {
        ManageableHistoricalTimeSeriesInfo info = _iterator.next();
        ManageableHistoricalTimeSeries timeSeries =
            _historicalTimeSeriesMaster.getTimeSeries(info.getUniqueId(), _historicalTimeSeriesGetFilter);
        return new ObjectsPair<ManageableHistoricalTimeSeriesInfo, ManageableHistoricalTimeSeries>(info, timeSeries);
      }

      @Override
      public void remove() {
        throw new OpenGammaRuntimeException("Remove operation is not supported");
      }
    };
  }
}
