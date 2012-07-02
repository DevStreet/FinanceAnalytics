/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import javax.time.Instant;
import javax.time.TimeSource;
import javax.time.calendar.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.opengamma.DataNotFoundException;
import com.opengamma.elsql.ElSqlBundle;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundleWithDates;
import com.opengamma.id.ExternalIdWithDates;
import com.opengamma.id.UniqueId;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoDocument;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchRequest;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeriesInfo;
import com.opengamma.masterdb.historicaltimeseries.DbHistoricalTimeSeriesMaster;
import com.opengamma.util.paging.PagingRequest;
import com.opengamma.util.test.DbTest;

/**
 * Base tests for DbSecurityMasterWorker via DbSecurityMaster.
 */
public class DbHTSMasterBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbHTSMasterBulkTest.class);

  protected DbHistoricalTimeSeriesMaster _master;

  ElSqlBundle bundle = ElSqlBundle.of(getDbConnector().getDialect().getElSqlConfig(), DbHistoricalTimeSeriesMaster.class);

  @Factory(dataProvider = "databases", dataProviderClass = DbTest.class)
  public DbHTSMasterBulkTest(String databaseType, String databaseVersion) {
    super(databaseType, databaseVersion);
    s_logger.info("running testcases for {}", databaseType);
  }


  @BeforeMethod
  public void setUp() throws Exception {
    super.setUp();
    ConfigurableApplicationContext context = DbMasterTestUtils.getContext(getDatabaseType());
    _master = (DbHistoricalTimeSeriesMaster) context.getBean(getDatabaseType() + "DbHistoricalTimeSeriesMaster");
    Instant now = Instant.now();
    _master.setTimeSource(TimeSource.fixed(now));
  }

  @Override
  protected AbstractDbMaster getMaster() {
    return _master;
  }


  @Test(groups = {"perftest"})
  public void testOperations() {
    testOperations(100, 100, 1);
  }

  @AfterMethod
  public void tearDown() throws Exception {
    _master = null;
    super.tearDown();
  }

  @Operation(batchSize = 1)
  public void search() {
    HistoricalTimeSeriesInfoSearchRequest request = new HistoricalTimeSeriesInfoSearchRequest();
    request.setPagingRequest(PagingRequest.FIRST_PAGE);
    _master.search(request);
    request.setName("dummy");
    _master.search(request);
  }

  @Operation(batchSize = 100)
  public HistoricalTimeSeriesInfoDocument add() {
    ManageableHistoricalTimeSeriesInfo info = new ManageableHistoricalTimeSeriesInfo();
    info.setName("Added");
    info.setDataField("DF");
    info.setDataSource("DS");
    info.setDataProvider("DP");
    info.setObservationTime("OT");
    ExternalIdWithDates id = ExternalIdWithDates.of(ExternalId.of("A", "B" + randomString(100)), LocalDate.of(2011, 6, 30), null);
    ExternalIdBundleWithDates bundle = ExternalIdBundleWithDates.of(id);
    info.setExternalIdBundle(bundle);
    HistoricalTimeSeriesInfoDocument doc = new HistoricalTimeSeriesInfoDocument(info);
    return _master.add(doc);
  }

  private UniqueId lastInsertedDocumentUid;

  @Operation(batchSize = 100)
  public void get() {
    _master.get(lastInsertedDocumentUid);
  }

  @Operation(batchSize = 100)
  public void remove() {
    try {
      _master.remove(randomUniqueId());
    } catch (DataNotFoundException e) {
      // this is expected for random uid most of the time
    }
  }

  @Operation(batchSize = 100)
  public void correct() {
    ManageableHistoricalTimeSeriesInfo info = new ManageableHistoricalTimeSeriesInfo();
    info.setUniqueId(lastInsertedDocumentUid);
    info.setName("Corrected");
    info.setDataField("DF");
    info.setDataSource("DS");
    info.setDataProvider("DP");
    info.setObservationTime("OT");
    ExternalIdWithDates id = ExternalIdWithDates.of(ExternalId.of("A", "B"), LocalDate.of(2011, 6, 30), null);
    ExternalIdBundleWithDates bundle = ExternalIdBundleWithDates.of(id);
    info.setExternalIdBundle(bundle);
    HistoricalTimeSeriesInfoDocument input = new HistoricalTimeSeriesInfoDocument(info);
    HistoricalTimeSeriesInfoDocument corrected = _master.correct(input);
    lastInsertedDocumentUid = corrected.getUniqueId();
  }

  @Operation(batchSize = 100)
  public void update() {
    ManageableHistoricalTimeSeriesInfo info = new ManageableHistoricalTimeSeriesInfo();
    info.setUniqueId(lastInsertedDocumentUid);
    info.setName("Updated");
    info.setDataField("DF");
    info.setDataSource("DS");
    info.setDataProvider("DP");
    info.setObservationTime("OT");
    ExternalIdWithDates id = ExternalIdWithDates.of(ExternalId.of("A", "B"), LocalDate.of(2011, 6, 30), null);
    ExternalIdBundleWithDates bundle = ExternalIdBundleWithDates.of(id);
    info.setExternalIdBundle(bundle);
    HistoricalTimeSeriesInfoDocument input = new HistoricalTimeSeriesInfoDocument(info);
    HistoricalTimeSeriesInfoDocument updated = _master.update(input);
    lastInsertedDocumentUid = updated.getUniqueId();
  }

  @Override
  protected void seed(int count) {
    for (int i = 0; i < count; i++) {
      lastInsertedDocumentUid = add().getUniqueId();
    }
  }

}
