/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import javax.time.Instant;
import javax.time.TimeSource;

import org.hsqldb.types.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.opengamma.elsql.ElSqlBundle;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotSearchRequest;
import com.opengamma.masterdb.marketdatasnapshot.DbMarketDataSnapshotMaster;
import com.opengamma.util.db.DbMapSqlParameterSource;
import com.opengamma.util.test.DbTest;

/**
 * Base tests for DbSecurityMasterWorker via DbSecurityMaster.
 */
public class DbMarketDataSnapshotMasterBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbMarketDataSnapshotMasterBulkTest.class);

  protected DbMarketDataSnapshotMaster _master;
  protected Instant _version1Instant;
  protected Instant _version2Instant;

  @Factory(dataProvider = "databases", dataProviderClass = DbTest.class)
  public DbMarketDataSnapshotMasterBulkTest(String databaseType, String databaseVersion) {
    super(databaseType, databaseVersion);
    s_logger.info("running testcases for {}", databaseType);
  }


  @BeforeMethod
  public void setUp() throws Exception {
    super.setUp();
    ConfigurableApplicationContext context = DbMasterTestUtils.getContext(getDatabaseType());
    _master = (DbMarketDataSnapshotMaster) context.getBean(getDatabaseType() + "DbMarketDataSnapshotMaster");
    Instant now = Instant.now();
    _master.setTimeSource(TimeSource.fixed(now));
    _version1Instant = now.minusSeconds(100);
    _version2Instant = now.minusSeconds(50);
  }

  @Operation(batchSize = 100)
  public void search() {
    MarketDataSnapshotSearchRequest request = new MarketDataSnapshotSearchRequest();
    _master.search(request);
    request.setName("dummy");
    _master.search(request);
  }

  @Test(groups = {"perftest"})
  public void testOperations() {
    testOperations(100, 1000, 1000000);
  }

  @AfterMethod
  public void tearDown() throws Exception {
    _master = null;
    super.tearDown();
  }

  private int recordId = 1;

  @Override
  protected void seed(int count) {
    ElSqlBundle bundle = ElSqlBundle.of(getDbConnector().getDialect().getElSqlConfig(), DbMarketDataSnapshotMaster.class);

    final List<DbMapSqlParameterSource> records = newArrayList();

    for (int i = 0; i < count; i++) {

      byte[] bytes = randomBytes(100);

      final DbMapSqlParameterSource docArgs = new DbMapSqlParameterSource().addValue("doc_id", ++recordId)
        .addValue("doc_oid", recordId)
        .addTimestamp("ver_from_instant", Instant.now())
        .addTimestampNullFuture("ver_to_instant", null)
        .addTimestamp("corr_from_instant", Instant.now())
        .addTimestampNullFuture("corr_to_instant", null)
        .addValue("name", randomString(20))
        .addValue("detail", new SqlLobValue(bytes, getDbConnector().getDialect().getLobHandler()), Types.BLOB);

      records.add(docArgs);

    }

    final String sqlDoc = bundle.getSql("Insert");

    getDbConnector().getJdbcTemplate().batchUpdate(
      sqlDoc,
      records.toArray(new DbMapSqlParameterSource[records.size()])
    );
  }
}
