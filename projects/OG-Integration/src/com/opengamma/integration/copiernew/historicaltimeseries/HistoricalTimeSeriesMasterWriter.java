package com.opengamma.integration.copiernew.historicaltimeseries;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.id.ExternalIdSearch;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.integration.copiernew.Writeable;
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

import javax.time.calendar.ZonedDateTime;
import java.io.IOException;
import java.util.List;

public class HistoricalTimeSeriesMasterWriter
    implements Writeable<ObjectsPair<ManageableHistoricalTimeSeriesInfo, ManageableHistoricalTimeSeries>> {

  HistoricalTimeSeriesMaster _historicalTimeSeriesMaster;
  HistoricalTimeSeriesMasterUtils _historicalTimeSeriesMasterUtils;

  private BeanCompare _beanCompare;

  public HistoricalTimeSeriesMasterWriter(HistoricalTimeSeriesMaster historicalTimeSeriesMaster) {
    ArgumentChecker.notNull(historicalTimeSeriesMaster, "historicalTimeSeriesMaster");
    _historicalTimeSeriesMaster = historicalTimeSeriesMaster;
    _historicalTimeSeriesMasterUtils = new HistoricalTimeSeriesMasterUtils(_historicalTimeSeriesMaster);
    _beanCompare = new BeanCompare();
  }

  @Override
  public ObjectsPair<ManageableHistoricalTimeSeriesInfo, ManageableHistoricalTimeSeries> addOrUpdate(ObjectsPair<ManageableHistoricalTimeSeriesInfo, ManageableHistoricalTimeSeries> pair) {
    ArgumentChecker.notNull(pair, "historicalTimeSeriesInfo, historicalTimeSeries");

    // Write info
    ManageableHistoricalTimeSeriesInfo info = pair.getFirst();
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
        resultInfo = updatedDoc.getInfo();
      }
    } else {
      // Not found, so add a new time series
      HistoricalTimeSeriesInfoDocument addedDoc =
          _historicalTimeSeriesMaster.add(new HistoricalTimeSeriesInfoDocument(info));
      resultInfo = addedDoc.getInfo();
    }

    // Write data points
    ManageableHistoricalTimeSeries timeSeries = pair.getSecond();
    ManageableHistoricalTimeSeries resultTimeSeries = null;
    if (timeSeries != null) {
      UniqueId updatedUniqueId = addOrUpdate(resultInfo.getUniqueId(),
          timeSeries.getTimeSeries());
      resultTimeSeries = _historicalTimeSeriesMaster.getTimeSeries(updatedUniqueId);
      resultInfo = _historicalTimeSeriesMaster.get(updatedUniqueId).getInfo();
    }

    // Return up-to-date info/data points
    return new ObjectsPair<ManageableHistoricalTimeSeriesInfo, ManageableHistoricalTimeSeries>(
        resultInfo, resultTimeSeries);
  }

  private UniqueId addOrUpdate(UniqueId id, LocalDateDoubleTimeSeries ts) {
    return _historicalTimeSeriesMasterUtils.writeTimeSeries(id, ts);
  }

  @Override
  public void flush() throws IOException {
    // No action
  }
}
