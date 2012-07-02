/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import java.util.HashMap;

import javax.time.Instant;
import javax.time.TimeSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.opengamma.DataNotFoundException;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.master.security.ManageableSecurity;
import com.opengamma.master.security.SecurityDocument;
import com.opengamma.master.security.SecuritySearchRequest;
import com.opengamma.masterdb.security.DbSecurityMaster;
import com.opengamma.util.test.DbTest;

/**
 * Base tests for DbSecurityMasterWorker via DbSecurityMaster.
 */
public class DbSecurityMasterWorkerBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbSecurityMasterWorkerBulkTest.class);

  protected DbSecurityMaster _master;

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
  }

  @Override
  protected AbstractDbMaster getMaster() {
    return _master;
  }


  private SecurityDocument lastInsertedDocument;

  @Operation(batchSize = 100)
  public void search() {
    SecuritySearchRequest request = new SecuritySearchRequest();
    _master.search(request);
    request.setExternalIdValue("B");
    _master.search(request);
  }

  @Operation(batchSize = 10)
  public SecurityDocument add() {
    ManageableSecurity security = new ManageableSecurity(null, "TestSecurity", "EQUITY", ExternalIdBundle.of("A", "B"));

    security.setName(randomString(20));
    security.setSecurityType("EQUITY");
    security.setAttributes(new HashMap<String, String>() {{
      put("foo", "bar");
      put("foo2", "bar2");
    }});
    SecurityDocument doc = new SecurityDocument();
    doc.setSecurity(security);
    return _master.add(doc);
  }

  @Operation(batchSize = 100)
  public void get() {
    _master.get(lastInsertedDocument.getUniqueId());
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
    SecurityDocument input = new SecurityDocument(lastInsertedDocument.getSecurity());
    lastInsertedDocument = _master.correct(input);
  }

  @Operation(batchSize = 100)
  public void update() {
    SecurityDocument input = new SecurityDocument(lastInsertedDocument.getSecurity());
    lastInsertedDocument = _master.update(input);
  }

  @Override
  protected void seed(int count) {
    for (int i = 0; i < count; i++) {
      lastInsertedDocument = add();
    }
  }

  ////////////////////////////

  @Test(groups = {"perftest"})
  public void testOperations() {
    testOperations(100, 100, 1);
  }

  @AfterMethod
  public void tearDown() throws Exception {
    _master = null;
    super.tearDown();
  }

//  @Override
//  protected void seed(int count) {
//
//    ElSqlBundle bundle = ElSqlBundle.of(getDbConnector().getDialect().getElSqlConfig(), DbSecurityMaster.class);
//
//
//    final List<DbMapSqlParameterSource> records = new ArrayList<DbMapSqlParameterSource>();
//
//    // the arguments for inserting into the idkey tables
//    final List<DbMapSqlParameterSource> assocList = new ArrayList<DbMapSqlParameterSource>();
//    final List<DbMapSqlParameterSource> idKeyList = new ArrayList<DbMapSqlParameterSource>();
//
//    final List<DbMapSqlParameterSource> securityDetails = new ArrayList<DbMapSqlParameterSource>();
//
//    for (int i = 0; i < count; i++) {
//
//      final long docId = nextId("sec_security_seq");
//      final long docOid = docId;
//      // the arguments for inserting into the security table
//      final DbMapSqlParameterSource docArgs = new DbMapSqlParameterSource()
//        .addValue("doc_id", docId)
//        .addValue("doc_oid", docOid)
//        .addTimestamp("ver_from_instant", Instant.now())
//        .addTimestampNullFuture("ver_to_instant", null)
//        .addTimestamp("corr_from_instant", Instant.now())
//        .addTimestampNullFuture("corr_to_instant", null)
//        .addValue("name", randomString(20))
//        .addValue("sec_type", randomString(6));
//      if (_random.nextBoolean()) {
//        docArgs.addValue("detail_type", "R");
//      } else if (_random.nextBoolean()) {
//        docArgs.addValue("detail_type", "M");
//      } else {
//        docArgs.addValue("detail_type", "D");
//      }
//
//      records.add(docArgs);
//
//      final String sqlSelectIdKey = bundle.getSql("SelectIdKey");
//      for (ExternalId id : newArrayList(ExternalId.of(randomString(5), randomString(7)), ExternalId.of(randomString(5), randomString(7)))) {
//        final DbMapSqlParameterSource assocArgs = new DbMapSqlParameterSource()
//          .addValue("doc_id", docId)
//          .addValue("key_scheme", id.getScheme().getName())
//          .addValue("key_value", id.getValue());
//        assocList.add(assocArgs);
//        if (getDbConnector().getJdbcTemplate().queryForList(sqlSelectIdKey, assocArgs).isEmpty()) {
//          // select avoids creating unnecessary id, but id may still not be used
//          final long idKeyId = nextId("sec_idkey_seq");
//          final DbMapSqlParameterSource idkeyArgs = new DbMapSqlParameterSource()
//            .addValue("idkey_id", idKeyId)
//            .addValue("key_scheme", id.getScheme().getName())
//            .addValue("key_value", id.getValue());
//          idKeyList.add(idkeyArgs);
//        }
//      }
//
//
//      // store the detail
//      final DbMapSqlParameterSource rawArgs = new DbMapSqlParameterSource()
//        .addValue("security_id", docId)
//        .addValue("raw_data", new SqlLobValue(randomBytes(100), getDbConnector().getDialect().getLobHandler()), Types.BLOB);
//      securityDetails.add(rawArgs);
//
//    }
//
//    final String sqlDoc = bundle.getSql("Insert");
//    final String sqlIdKey = bundle.getSql("InsertIdKey");
//    final String sqlDoc2IdKey = bundle.getSql("InsertDoc2IdKey");
//    final String sqlRaw = bundle.getSql("InsertRaw");
//
//    getDbConnector().getJdbcTemplate().batchUpdate(sqlDoc, records.toArray(new DbMapSqlParameterSource[records.size()]));
//    getDbConnector().getJdbcTemplate().batchUpdate(sqlIdKey, idKeyList.toArray(new DbMapSqlParameterSource[idKeyList.size()]));
//    getDbConnector().getJdbcTemplate().batchUpdate(sqlDoc2IdKey, assocList.toArray(new DbMapSqlParameterSource[assocList.size()]));
//    getDbConnector().getJdbcTemplate().batchUpdate(sqlRaw, securityDetails.toArray(new DbMapSqlParameterSource[securityDetails.size()]));
//
//  }


}
