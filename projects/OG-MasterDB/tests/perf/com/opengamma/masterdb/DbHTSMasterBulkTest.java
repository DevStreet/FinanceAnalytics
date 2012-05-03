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

import com.opengamma.elsql.ElSqlBundle;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundleWithDates;
import com.opengamma.id.ExternalIdWithDates;
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
  protected Instant _version1Instant;
  protected Instant _version2Instant;

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
    _version1Instant = now.minusSeconds(100);
    _version2Instant = now.minusSeconds(50);
  }

  @Override
  protected AbstractDbMaster getMaster() {
    return _master;
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
  public void insert() {
    ManageableHistoricalTimeSeriesInfo info = new ManageableHistoricalTimeSeriesInfo();
    info.setName("Added");
    info.setDataField("DF");
    info.setDataSource("DS");
    info.setDataProvider("DP");
    info.setObservationTime("OT");
    ExternalIdWithDates id = ExternalIdWithDates.of(ExternalId.of("A", "B"+randomString(100)), LocalDate.of(2011, 6, 30), null);
    ExternalIdBundleWithDates bundle = ExternalIdBundleWithDates.of(id);
    info.setExternalIdBundle(bundle);
    HistoricalTimeSeriesInfoDocument doc = new HistoricalTimeSeriesInfoDocument(info);
    _master.add(doc);
  }

  @Test(groups = {"perftest"})
  public void testOperations() {
    testOperations(100, 100, 0);
  }

  @AfterMethod
  public void tearDown() throws Exception {
    _master = null;
    super.tearDown();
  }

  @Override
  protected void seed(int count) {
    for (int i = 0; i < count; i++) {
      ManageableHistoricalTimeSeriesInfo info = new ManageableHistoricalTimeSeriesInfo();
      info.setName("Added");
      info.setDataField("DF");
      info.setDataSource("DS");
      info.setDataProvider("DP");
      info.setObservationTime("OT");
      ExternalIdWithDates id = ExternalIdWithDates.of(ExternalId.of(randomString(5), randomString(100)), LocalDate.of(2011, 6, 30), null);
      ExternalIdBundleWithDates bundle = ExternalIdBundleWithDates.of(id);
      info.setExternalIdBundle(bundle);
      HistoricalTimeSeriesInfoDocument doc = new HistoricalTimeSeriesInfoDocument(info);
      _master.add(doc);      
    }
  }

}
