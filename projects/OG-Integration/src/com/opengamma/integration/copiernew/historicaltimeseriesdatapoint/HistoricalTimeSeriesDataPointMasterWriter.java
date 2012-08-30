package com.opengamma.integration.copiernew.historicaltimeseriesdatapoint;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.ExternalIdSearch;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoDocument;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchRequest;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchResult;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeries;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeriesInfo;
import com.opengamma.master.historicaltimeseries.impl.HistoricalTimeSeriesMasterUtils;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.beancompare.BeanCompare;
import com.opengamma.util.beancompare.BeanDifference;
import com.opengamma.util.timeseries.localdate.LocalDateDoubleTimeSeries;
import com.opengamma.util.tuple.ObjectsPair;

import javax.time.calendar.ZonedDateTime;
import java.io.IOException;
import java.util.List;

public class HistoricalTimeSeriesDataPointMasterWriter implements Writeable<LocalDateDoubleTimeSeries> {

  HistoricalTimeSeriesMaster _historicalTimeSeriesMaster;
  ManageableHistoricalTimeSeriesInfo _historicalTimeSeriesInfo;
  HistoricalTimeSeriesMasterUtils _historicalTimeSeriesMasterUtils;


  public HistoricalTimeSeriesDataPointMasterWriter(HistoricalTimeSeriesMaster historicalTimeSeriesMaster,
                                                   ManageableHistoricalTimeSeriesInfo historicalTimeSeriesInfo) {
    ArgumentChecker.notNull(historicalTimeSeriesMaster, "historicalTimeSeriesMaster");
    ArgumentChecker.notNull(historicalTimeSeriesInfo, "historicalTimeSeriesInfo");
    _historicalTimeSeriesMaster = historicalTimeSeriesMaster;
    _historicalTimeSeriesInfo = historicalTimeSeriesInfo;
    _historicalTimeSeriesMasterUtils = new HistoricalTimeSeriesMasterUtils(_historicalTimeSeriesMaster);
  }

  @Override
  public void addOrUpdate(LocalDateDoubleTimeSeries timeSeries) {
    ArgumentChecker.notNull(timeSeries, "timeSeries");

    if (timeSeries != null) {
      UniqueId updatedUniqueId =
          _historicalTimeSeriesMasterUtils.writeTimeSeries(_historicalTimeSeriesInfo.getUniqueId(), timeSeries);
    }
  }

  @Override
  public void addOrUpdate(Iterable<LocalDateDoubleTimeSeries> data) {
    for (LocalDateDoubleTimeSeries datum : data) {
      addOrUpdate(datum);
    }
  }

  @Override
  public void flush() throws IOException {
    // No action
  }
}
