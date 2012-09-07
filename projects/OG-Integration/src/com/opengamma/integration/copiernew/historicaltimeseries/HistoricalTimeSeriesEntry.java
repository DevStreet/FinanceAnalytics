/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.integration.copiernew.historicaltimeseries;

import com.opengamma.id.UniqueId;
import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeriesInfo;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.timeseries.localdate.LocalDateDoubleTimeSeries;

public class HistoricalTimeSeriesEntry implements UniqueIdentifiable {

  private ManageableHistoricalTimeSeriesInfo _info;
  private  Iterable<LocalDateDoubleTimeSeries> _series;

  public HistoricalTimeSeriesEntry(ManageableHistoricalTimeSeriesInfo info, Iterable<LocalDateDoubleTimeSeries> series) {
    ArgumentChecker.notNull(info, "info");
    ArgumentChecker.notNull(series, "series");

    _info = info;
    _series = series;
  }

  public ManageableHistoricalTimeSeriesInfo getInfo() {
    return _info;
  }

  public Iterable<LocalDateDoubleTimeSeries> getSeries() {
    return _series;
  }

  @Override
  public UniqueId getUniqueId() {
    return _info.getUniqueId();
  }
}
