/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.historicaltimeseries.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.threeten.bp.Duration;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;

import com.opengamma.core.change.ChangeManager;
import com.opengamma.core.change.DummyChangeManager;
import com.opengamma.id.ObjectId;
import com.opengamma.id.ObjectIdentifiable;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesGetFilter;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoDocument;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoHistoryRequest;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoHistoryResult;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoMetaDataRequest;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoMetaDataResult;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchRequest;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchResult;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeries;
import com.opengamma.timeseries.localdate.ArrayLocalDateDoubleTimeSeries;
import com.opengamma.timeseries.localdate.LocalDateDoubleTimeSeries;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.tuple.ObjectsPair;

public class DataPointTestMaster implements HistoricalTimeSeriesMaster {

  /**
   * The prefix used for data point unique identifiers.
   */
  protected static final String DATA_POINT_PREFIX = "DP";
  protected static final String UNIQUE_ID_SCHEME = "Hts";

  private Map<ObjectsPair<ObjectId, VersionCorrection>, LocalDateDoubleTimeSeries> _underlying;

  public DataPointTestMaster() {
    _underlying = new HashMap<>();
    LocalDate[] localDates = new LocalDate[400];
    double[] doubles = new double[400];
    for (Integer i = 0; i < 400; i++) {
      localDates[i] = LocalDate.of(2012 + (i / 27 / 12), ((i / 27) % 12) + 1, (i % 27) + 1);
      doubles[i] =  i.doubleValue();
    }
    LocalDateDoubleTimeSeries hts1 = new ArrayLocalDateDoubleTimeSeries(localDates, doubles);

    _underlying.put(ObjectsPair.of(ObjectId.of("Hts", "1"),
                                   VersionCorrection.LATEST), hts1);
    LocalDateDoubleTimeSeries hts2 = new ArrayLocalDateDoubleTimeSeries();
    _underlying.put(ObjectsPair.of(ObjectId.of("Hts", "2"),
                                   VersionCorrection.LATEST), hts2);
  }

  @Override
  public ManageableHistoricalTimeSeries getTimeSeries(UniqueId uniqueId) {
    ArgumentChecker.notNull(uniqueId, "uniqueId");
    checkScheme(uniqueId);

    final VersionCorrection vc;
    if (uniqueId.isVersioned() && uniqueId.getValue().startsWith(DATA_POINT_PREFIX)) {
      vc = extractTimeSeriesInstants(uniqueId);
    } else {
      vc = VersionCorrection.LATEST;
    }
    return getTimeSeries(uniqueId.getObjectId(), vc);
  }

  @Override
  public ManageableHistoricalTimeSeries getTimeSeries(UniqueId uniqueId, HistoricalTimeSeriesGetFilter filter) {
    ArgumentChecker.notNull(uniqueId, "uniqueId");
    checkScheme(uniqueId);

    final VersionCorrection vc;
    if (uniqueId.isVersioned() && uniqueId.getValue().startsWith(DATA_POINT_PREFIX)) {
      vc = extractTimeSeriesInstants(uniqueId);
    } else {
      vc = VersionCorrection.LATEST;
    }
    return getTimeSeries(uniqueId.getObjectId(), vc, filter);
  }

  @Override
  public ManageableHistoricalTimeSeries getTimeSeries(ObjectIdentifiable objectId,
                                                      VersionCorrection versionCorrection) {
    HistoricalTimeSeriesGetFilter filter = HistoricalTimeSeriesGetFilter.ofRange(null, null);
    return getTimeSeries(objectId, versionCorrection, filter);
  }

  @Override
  public ManageableHistoricalTimeSeries getTimeSeries(ObjectIdentifiable objectId,
                                                      VersionCorrection versionCorrection,
                                                      HistoricalTimeSeriesGetFilter filter) {

    ManageableHistoricalTimeSeries manageableHistoricalTimeSeries = new ManageableHistoricalTimeSeries();
    manageableHistoricalTimeSeries.setUniqueId(EHCachingDataPointCache.createTimeSeriesUniqueId(objectId.getObjectId(), versionCorrection));
    manageableHistoricalTimeSeries.setVersionInstant(versionCorrection.getVersionAsOf());
    manageableHistoricalTimeSeries.setCorrectionInstant(versionCorrection.getCorrectedTo());
    LocalDateDoubleTimeSeries dataPoints = _underlying.get(ObjectsPair.of(objectId.getObjectId(), versionCorrection));
    manageableHistoricalTimeSeries.setTimeSeries(dataPoints.subSeries(filter.getEarliestDate(), filter.getLatestDate()));
    return manageableHistoricalTimeSeries;
  }

  @Override
  public UniqueId updateTimeSeriesDataPoints(ObjectIdentifiable objectId, LocalDateDoubleTimeSeries series) {
    return null;  // TODO
  }

  @Override
  public UniqueId correctTimeSeriesDataPoints(ObjectIdentifiable objectId, LocalDateDoubleTimeSeries series) {
    return null;  // TODO
  }

  @Override
  public UniqueId removeTimeSeriesDataPoints(ObjectIdentifiable objectId,
                                             LocalDate fromDateInclusive,
                                             LocalDate toDateInclusive) {
    return null;  // TODO
  }

  @Override
  public ChangeManager changeManager() {
    return DummyChangeManager.INSTANCE;
  }

  //--------------------------------------------------------------------------------------------------------------------

  /**
   * Extracts the object row id from the object identifier.
   *
   * @param objectId  the object identifier, not null
   * @return the date, null if no point date
   */
  protected static long extractOid(ObjectIdentifiable objectId) {
    String value = objectId.getObjectId().getValue();
    if (value.startsWith(DATA_POINT_PREFIX)) {
      value = value.substring(DATA_POINT_PREFIX.length());
    }
    try {
      return Long.parseLong(value);
    } catch (RuntimeException ex) {
      throw new IllegalArgumentException("UniqueId is not from this master (non-numeric object id): " + objectId, ex);
    }
  }

  /**
   * Extracts the instants from the unique identifier.
   *
   * @param uniqueId  the unique identifier, not null
   * @return the instants, version, correction, not null
   */
  protected static VersionCorrection extractTimeSeriesInstants(UniqueId uniqueId) {
    try {
      int pos = uniqueId.getVersion().indexOf('P');
      String verStr = uniqueId.getVersion().substring(0, pos);
      String corrStr = uniqueId.getVersion().substring(pos);
      Instant ver = Instant.parse(verStr);
      Instant corr = ver.plus(Duration.parse(corrStr));
      return VersionCorrection.of(ver, corr);
    } catch (RuntimeException ex) {
      throw new IllegalArgumentException("UniqueId is not from this master (invalid version): " + uniqueId, ex);
    }
  }

  /**
   * Checks the unique identifier scheme is valid.
   *
   * @param objectId  the object identifier, not null
   */
  protected static void checkScheme(final ObjectIdentifiable objectId) {
    if (UNIQUE_ID_SCHEME.equals(objectId.getObjectId().getScheme()) == false) {
      throw new IllegalArgumentException("UniqueId is not from this master" + objectId);
    }
  }

  /**
   * Creates a unique identifier.
   *
   * @param oid  the object identifier
   * @param verInstant  the version instant, not null
   * @param corrInstant  the correction instant, not null
   * @return the unique identifier
   */
  protected UniqueId createTimeSeriesUniqueId(long oid, Instant verInstant, Instant corrInstant) {
    String oidStr = DATA_POINT_PREFIX + oid;
    Duration dur = Duration.between(verInstant, corrInstant);
    String verStr = verInstant.toString() + dur.toString();
    return UniqueId.of(UNIQUE_ID_SCHEME, oidStr, verStr);
  }

  //--------------------------------------------------------------------------------------------------------------------

  @Override
  public HistoricalTimeSeriesInfoMetaDataResult metaData(HistoricalTimeSeriesInfoMetaDataRequest request) {
    throw new UnsupportedOperationException();
  }

  @Override
  public HistoricalTimeSeriesInfoSearchResult search(HistoricalTimeSeriesInfoSearchRequest request) {
    throw new UnsupportedOperationException();
  }

  @Override
  public HistoricalTimeSeriesInfoHistoryResult history(HistoricalTimeSeriesInfoHistoryRequest request) {
    throw new UnsupportedOperationException();
  }

  @Override
  public HistoricalTimeSeriesInfoDocument get(UniqueId uniqueId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public HistoricalTimeSeriesInfoDocument get(ObjectIdentifiable objectId, VersionCorrection versionCorrection) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Map<UniqueId, HistoricalTimeSeriesInfoDocument> get(Collection<UniqueId> uniqueIds) {
    throw new UnsupportedOperationException();
  }

  @Override
  public HistoricalTimeSeriesInfoDocument add(HistoricalTimeSeriesInfoDocument document) {
    throw new UnsupportedOperationException();
  }

  @Override
  public HistoricalTimeSeriesInfoDocument update(HistoricalTimeSeriesInfoDocument document) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void remove(ObjectIdentifiable oid) {
    throw new UnsupportedOperationException();
  }

  @Override
  public HistoricalTimeSeriesInfoDocument correct(HistoricalTimeSeriesInfoDocument document) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<UniqueId> replaceVersion(UniqueId uniqueId, List<HistoricalTimeSeriesInfoDocument> replacementDocuments) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<UniqueId> replaceAllVersions(ObjectIdentifiable objectId,
                                           List<HistoricalTimeSeriesInfoDocument> replacementDocuments) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<UniqueId> replaceVersions(ObjectIdentifiable objectId,
                                        List<HistoricalTimeSeriesInfoDocument> replacementDocuments) {
    throw new UnsupportedOperationException();
  }

  @Override
  public UniqueId replaceVersion(HistoricalTimeSeriesInfoDocument replacementDocument) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void removeVersion(UniqueId uniqueId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public UniqueId addVersion(ObjectIdentifiable objectId, HistoricalTimeSeriesInfoDocument documentToAdd) {
    throw new UnsupportedOperationException();
  }

}
