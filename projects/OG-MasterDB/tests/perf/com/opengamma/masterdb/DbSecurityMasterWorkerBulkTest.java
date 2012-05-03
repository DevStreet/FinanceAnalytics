/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
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
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.SecurityDocument;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.masterdb.security.DbSecurityMaster;
import com.opengamma.util.db.DbMapSqlParameterSource;
import com.opengamma.util.test.DbTest;

/**
 * Base tests for DbSecurityMasterWorker via DbSecurityMaster.
 */
public class DbSecurityMasterWorkerBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbSecurityMasterWorkerBulkTest.class);

  protected DbSecurityMaster _master;
  protected Instant _version1Instant;
  protected Instant _version2Instant;

  @Factory(dataProvider = "databases", dataProviderClass = DbTest.class)
  public DbSecurityMasterWorkerBulkTest(String databaseType, String databaseVersion) {
    super(databaseType, databaseVersion);
    s_logger.info("running testcases for {}", databaseType);
  }


  @BeforeMethod
  public void setUp() throws Exception {
    super.setUp();
    ConfigurableApplicationContext context = DbMasterTestUtils.getContext(getDatabaseType());
    _master = (DbSecurityMaster) context.getBean(getDatabaseType() + "DbSecurityMaster");
    Instant now = Instant.now();
    _master.setTimeSource(TimeSource.fixed(now));
    _version1Instant = now.minusSeconds(100);
    _version2Instant = now.minusSeconds(50);
  }

  @Override
  protected AbstractDbMaster getMaster() {
    return _master;
  }

  @Operation(batchSize = 100)
  public void search() {
    SecuritySearchRequest request = new SecuritySearchRequest();
    _master.search(request);
    request.setExternalIdValue("B");
    _master.search(request);
  }

  @Operation(batchSize = 10)
  public void insert() {
    ManageableSecurity security = new ManageableSecurity(null, "TestSecurity", "EQUITY", ExternalIdBundle.of("A", "B"));
    SecurityDocument doc = new SecurityDocument();
    doc.setSecurity(security);
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

    ElSqlBundle bundle = ElSqlBundle.of(getDbConnector().getDialect().getElSqlConfig(), DbSecurityMaster.class);


    final List<DbMapSqlParameterSource> records = new ArrayList<DbMapSqlParameterSource>();

    // the arguments for inserting into the idkey tables
    final List<DbMapSqlParameterSource> assocList = new ArrayList<DbMapSqlParameterSource>();
    final List<DbMapSqlParameterSource> idKeyList = new ArrayList<DbMapSqlParameterSource>();

    final List<DbMapSqlParameterSource> securityDetails = new ArrayList<DbMapSqlParameterSource>();

    for (int i = 0; i < count; i++) {

      final long docId = nextId("sec_security_seq");
      final long docOid = docId;
      // the arguments for inserting into the security table
      final DbMapSqlParameterSource docArgs = new DbMapSqlParameterSource()
        .addValue("doc_id", docId)
        .addValue("doc_oid", docOid)
        .addTimestamp("ver_from_instant", Instant.now())
        .addTimestampNullFuture("ver_to_instant", null)
        .addTimestamp("corr_from_instant", Instant.now())
        .addTimestampNullFuture("corr_to_instant", null)
        .addValue("name", randomString(20))
        .addValue("sec_type", randomString(6));
      if (_random.nextBoolean()) {
        docArgs.addValue("detail_type", "R");
      } else if (_random.nextBoolean()) {
        docArgs.addValue("detail_type", "M");
      } else {
        docArgs.addValue("detail_type", "D");
      }

      records.add(docArgs);

      final String sqlSelectIdKey = bundle.getSql("SelectIdKey");
      for (ExternalId id : newArrayList(ExternalId.of(randomString(5), randomString(7)), ExternalId.of(randomString(5), randomString(7)))) {
        final DbMapSqlParameterSource assocArgs = new DbMapSqlParameterSource()
          .addValue("doc_id", docId)
          .addValue("key_scheme", id.getScheme().getName())
          .addValue("key_value", id.getValue());
        assocList.add(assocArgs);
        if (getDbConnector().getJdbcTemplate().queryForList(sqlSelectIdKey, assocArgs).isEmpty()) {
          // select avoids creating unnecessary id, but id may still not be used
          final long idKeyId = nextId("sec_idkey_seq");
          final DbMapSqlParameterSource idkeyArgs = new DbMapSqlParameterSource()
            .addValue("idkey_id", idKeyId)
            .addValue("key_scheme", id.getScheme().getName())
            .addValue("key_value", id.getValue());
          idKeyList.add(idkeyArgs);
        }
      }


      // store the detail
      final DbMapSqlParameterSource rawArgs = new DbMapSqlParameterSource()
        .addValue("security_id", docId)
        .addValue("raw_data", new SqlLobValue(randomBytes(100), getDbConnector().getDialect().getLobHandler()), Types.BLOB);
      securityDetails.add(rawArgs);

    }

    final String sqlDoc = bundle.getSql("Insert");
    final String sqlIdKey = bundle.getSql("InsertIdKey");
    final String sqlDoc2IdKey = bundle.getSql("InsertDoc2IdKey");
    final String sqlRaw = bundle.getSql("InsertRaw");

    getDbConnector().getJdbcTemplate().batchUpdate(sqlDoc, records.toArray(new DbMapSqlParameterSource[records.size()]));
    getDbConnector().getJdbcTemplate().batchUpdate(sqlIdKey, idKeyList.toArray(new DbMapSqlParameterSource[idKeyList.size()]));
    getDbConnector().getJdbcTemplate().batchUpdate(sqlDoc2IdKey, assocList.toArray(new DbMapSqlParameterSource[assocList.size()]));
    getDbConnector().getJdbcTemplate().batchUpdate(sqlRaw, securityDetails.toArray(new DbMapSqlParameterSource[securityDetails.size()]));

  }


}
