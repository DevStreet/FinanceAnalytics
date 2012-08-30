package com.opengamma.integration.copiernew.historicaltimeseries;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.ExternalIdSearch;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.integration.copiernew.Writeable;
import com.opengamma.integration.copiernew.historicaltimeseriesdatapoint.HistoricalTimeSeriesDataPointMasterWriter;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoDocument;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchRequest;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchResult;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeries;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeriesInfo;

import com.opengamma.master.historicaltimeseries.impl.HistoricalTimeSeriesMasterUtils;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.beancompare.BeanCompare;
import com.opengamma.util.beancompare.BeanDifference;
import com.opengamma.util.timeseries.localdate.LocalDateDoubleTimeSeries;
import com.opengamma.util.tuple.ObjectsPair;

import javax.time.calendar.LocalDate;
import javax.time.calendar.ZonedDateTime;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class HistoricalTimeSeriesMasterWriter
    implements Writeable<ObjectsPair<ManageableHistoricalTimeSeriesInfo, Iterable<LocalDateDoubleTimeSeries>>> {

  HistoricalTimeSeriesMaster _historicalTimeSeriesMaster;
  private BeanCompare _beanCompare;

  public HistoricalTimeSeriesMasterWriter(HistoricalTimeSeriesMaster historicalTimeSeriesMaster) {
    ArgumentChecker.notNull(historicalTimeSeriesMaster, "historicalTimeSeriesMaster");
    _historicalTimeSeriesMaster = historicalTimeSeriesMaster;
    _beanCompare = new BeanCompare();
  }

  @Override
  public void addOrUpdate(ObjectsPair<ManageableHistoricalTimeSeriesInfo, Iterable<LocalDateDoubleTimeSeries>> pair) {
    ArgumentChecker.notNull(pair, "historicalTimeSeriesInfo, historicalTimeSeries");

    // Write info
    ManageableHistoricalTimeSeriesInfo info = pair.getFirst();

    // Clear unique id
    info.setUniqueId(null);

    HistoricalTimeSeriesInfoSearchRequest searchReq = new HistoricalTimeSeriesInfoSearchRequest();
    ExternalIdSearch idSearch = new ExternalIdSearch(info.getExternalIdBundle().toBundle());  // match any one of the IDs
    searchReq.setVersionCorrection(VersionCorrection.ofVersionAsOf(ZonedDateTime.now())); // valid now
    searchReq.setExternalIdSearch(idSearch);
    HistoricalTimeSeriesInfoSearchResult searchResult = _historicalTimeSeriesMaster.search(searchReq);
    ManageableHistoricalTimeSeriesInfo resultInfo = searchResult.getFirstInfo();
    if (resultInfo != null) {
      List<BeanDifference<?>> differences;
      try {
        differences = _beanCompare.compare(resultInfo, info);
      } catch (Exception e) {
        throw new OpenGammaRuntimeException("Error comparing historicalTimeSeriesInfos with ID bundle " +
            info.getExternalIdBundle(), e);
      }
      if (differences.size() == 1 && differences.get(0).getProperty().propertyType() == UniqueId.class) {
        // It's already there, don't update or add it
      } else {
        // Update existing time series
        HistoricalTimeSeriesInfoDocument updatedDoc = new HistoricalTimeSeriesInfoDocument(info);
        updatedDoc.setUniqueId(resultInfo.getUniqueId());
        updatedDoc = _historicalTimeSeriesMaster.update(updatedDoc);
      }
    } else {
      // Not found, so add a new time series
      HistoricalTimeSeriesInfoDocument addedDoc =
          _historicalTimeSeriesMaster.add(new HistoricalTimeSeriesInfoDocument(info));
    }

    // Write data points
    Iterable<LocalDateDoubleTimeSeries> timeSeries = pair.getSecond();
    if (timeSeries != null) {
      HistoricalTimeSeriesDataPointMasterWriter dataPointMasterWriter =
          new HistoricalTimeSeriesDataPointMasterWriter(_historicalTimeSeriesMaster, info);
      dataPointMasterWriter.addOrUpdate(timeSeries);
    }
  }

  @Override
  public void addOrUpdate(Iterable<ObjectsPair<ManageableHistoricalTimeSeriesInfo, Iterable<LocalDateDoubleTimeSeries>>> data) {
    for (ObjectsPair<ManageableHistoricalTimeSeriesInfo, Iterable<LocalDateDoubleTimeSeries>> datum : data) {
      addOrUpdate(datum);
    }
  }

  @Override
  public void flush() throws IOException {
    // No action
  }
}
