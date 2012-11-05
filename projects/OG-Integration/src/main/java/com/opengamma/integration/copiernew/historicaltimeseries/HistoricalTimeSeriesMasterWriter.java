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

public class HistoricalTimeSeriesMasterWriter implements Writeable<HistoricalTimeSeriesEntry> {

  private static final String TEMPLATE_NAME = "<name>";

  HistoricalTimeSeriesMaster _historicalTimeSeriesMaster;
  private BeanCompare _beanCompare;
  private String _nameTemplate;

  public HistoricalTimeSeriesMasterWriter(HistoricalTimeSeriesMaster historicalTimeSeriesMaster) {
    this(historicalTimeSeriesMaster, null);
  }

  public HistoricalTimeSeriesMasterWriter(HistoricalTimeSeriesMaster historicalTimeSeriesMaster, String nameTemplate) {
    ArgumentChecker.notNull(historicalTimeSeriesMaster, "historicalTimeSeriesMaster");
    _historicalTimeSeriesMaster = historicalTimeSeriesMaster;
    _beanCompare = new BeanCompare();
    _nameTemplate = nameTemplate;
    if (_nameTemplate != null && !_nameTemplate.contains(TEMPLATE_NAME)) {
      _nameTemplate += TEMPLATE_NAME;
    }
  }

  @Override
  public void addOrUpdate(HistoricalTimeSeriesEntry entry) {
    ArgumentChecker.notNull(entry, "historicalTimeSeriesInfo, historicalTimeSeries");

    // Write info
    ManageableHistoricalTimeSeriesInfo info = entry.getInfo();

    // Clear unique id
    info.setUniqueId(null);
    info.setTimeSeriesObjectId(null);

    // Rename time-series as per supplied template
    if (_nameTemplate != null) {
      info.setName(_nameTemplate.replace(TEMPLATE_NAME, info.getName()));
    }

    HistoricalTimeSeriesInfoSearchRequest searchReq = new HistoricalTimeSeriesInfoSearchRequest();
    searchReq.setName(info.getName());
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
    Iterable<LocalDateDoubleTimeSeries> timeSeries = entry.getSeries();
    if (timeSeries != null) {
      HistoricalTimeSeriesDataPointMasterWriter dataPointMasterWriter =
          new HistoricalTimeSeriesDataPointMasterWriter(_historicalTimeSeriesMaster, info.getUniqueId());
      dataPointMasterWriter.addOrUpdate(timeSeries);
    }
  }

  @Override
  public void addOrUpdate(Iterable<HistoricalTimeSeriesEntry> data) {
    for (HistoricalTimeSeriesEntry datum : data) {
      addOrUpdate(datum);
    }
  }

  @Override
  public void flush() throws IOException {
    // No action
  }
}
