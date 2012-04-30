/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import javax.time.Instant;
import javax.time.TimeSource;

import org.hsqldb.types.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.opengamma.elsql.ElSqlBundle;
import com.opengamma.engine.view.ViewDefinition;
import com.opengamma.master.config.ConfigSearchRequest;
import com.opengamma.masterdb.config.DbConfigMaster;
import com.opengamma.util.db.DbMapSqlParameterSource;
import com.opengamma.util.test.DbTest;


public class DbConfigMasterBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbConfigMasterBulkTest.class);

  protected DbConfigMaster _master;
  protected Instant _version1Instant;
  protected Instant _version2Instant;


  @Factory(dataProvider = "databases", dataProviderClass = DbTest.class)
  public DbConfigMasterBulkTest(String databaseType, String databaseVersion) {
    super(databaseType, databaseVersion);
    s_logger.info("running testcases for {}", databaseType);
  }


  @BeforeMethod
  public void setUp() throws Exception {
    super.setUp();
    ConfigurableApplicationContext context = DbMasterTestUtils.getContext(getDatabaseType());
    _master = (DbConfigMaster) context.getBean(getDatabaseType() + "DbConfigMaster");
    Instant now = Instant.now();
    _master.setTimeSource(TimeSource.fixed(now));
    _version1Instant = now.minusSeconds(100);
    _version2Instant = now.minusSeconds(50);
  }


  @Operation(batchSize = 100)
  public void search() {
    ConfigSearchRequest request = new ConfigSearchRequest();
    request.setType(ViewDefinition.class);
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

    SqlParameterSource[] records = new SqlParameterSource[count];

    for (int i = 0; i < count; i++) {

      byte[] bytes = randomBytes(512);

      final DbMapSqlParameterSource docArgs = new DbMapSqlParameterSource()
        .addValue("doc_id", ++recordId)
        .addValue("doc_oid", _random.nextInt())
        .addTimestamp("ver_from_instant", Instant.now())
        .addTimestampNullFuture("ver_to_instant", null)
        .addTimestamp("corr_from_instant", Instant.now())
        .addTimestampNullFuture("corr_to_instant", null)
        .addValue("name", randomString(20))
        .addValue("config_type", randomString(20))
        .addValue("config", new SqlLobValue(bytes, getDbConnector().getDialect().getLobHandler()), Types.BLOB);

      records[i] = docArgs;

    }

    final String sqlDoc = ElSqlBundle.of(getDbConnector().getDialect().getElSqlConfig(), DbConfigMaster.class).getSql("Insert");

    getDbConnector().getJdbcTemplate().batchUpdate(
      sqlDoc,
      records
    );

  }
}
