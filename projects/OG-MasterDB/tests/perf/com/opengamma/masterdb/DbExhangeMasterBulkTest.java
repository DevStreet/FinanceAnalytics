/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

import java.util.List;
import java.util.Set;

import javax.time.Instant;
import javax.time.TimeSource;
import javax.time.calendar.TimeZone;

import org.hsqldb.types.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.opengamma.elsql.ElSqlBundle;
import com.opengamma.id.ExternalId;
import com.opengamma.master.exchange.ExchangeSearchRequest;
import com.opengamma.masterdb.exchange.DbExchangeMaster;
import com.opengamma.util.db.DbMapSqlParameterSource;
import com.opengamma.util.test.DbTest;
import com.opengamma.util.tuple.Pair;


public class DbExhangeMasterBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbExhangeMasterBulkTest.class);

  protected DbExchangeMaster _master;
  protected Instant _version1Instant;
  protected Instant _version2Instant;


  @Factory(dataProvider = "databases", dataProviderClass = DbTest.class)
  public DbExhangeMasterBulkTest(String databaseType, String databaseVersion) {
    super(databaseType, databaseVersion);
    s_logger.info("running testcases for {}", databaseType);
  }


  @BeforeMethod
  public void setUp() throws Exception {
    super.setUp();
    ConfigurableApplicationContext context = DbMasterTestUtils.getContext(getDatabaseType());
    _master = (DbExchangeMaster) context.getBean(getDatabaseType() + "DbExchangeMaster");
    Instant now = Instant.now();
    _master.setTimeSource(TimeSource.fixed(now));
    _version1Instant = now.minusSeconds(100);
    _version2Instant = now.minusSeconds(50);
  }


  @Operation(batchSize = 100)
  public void search() {
    ExchangeSearchRequest request = new ExchangeSearchRequest();
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

  private final static Set<Pair<String, String>> idKeySet = newHashSet();

  @Override
  protected void seed(int count) {

    ElSqlBundle bundle = ElSqlBundle.of(getDbConnector().getDialect().getElSqlConfig(), DbExchangeMaster.class);

    final List<DbMapSqlParameterSource> idKeyList = newArrayList();

    for (ExternalId id : Lists.newArrayList(ExternalId.of("SchemeX", "a"), ExternalId.of("SchemeX", "b"), ExternalId.of("SchemeX", "c"))) {
      // select avoids creating unecessary id, but id may still not be used
      if (!idKeySet.contains(Pair.of(id.getScheme().getName(), id.getValue()))) {
        idKeySet.add(Pair.of(id.getScheme().getName(), id.getValue()));
        final long idKeyId = nextId("exg_idkey_seq");
        final DbMapSqlParameterSource idkeyArgs = new DbMapSqlParameterSource()
          .addValue("idkey_id", idKeyId)
          .addValue("key_scheme", id.getScheme().getName())
          .addValue("key_value", id.getValue());
        idKeyList.add(idkeyArgs);
      }
    }

    if (idKeyList.size() > 0) {
      final String sqlIdKey = bundle.getSql("InsertIdKey");

      getDbConnector().getJdbcTemplate().batchUpdate(
        sqlIdKey,
        idKeyList.toArray(new DbMapSqlParameterSource[idKeyList.size()])
      );
    }


    final List<DbMapSqlParameterSource> records = newArrayList();
    // the arguments for inserting into the idkey tables
    final List<DbMapSqlParameterSource> assocList = newArrayList();


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
        .addValue("time_zone", TimeZone.of("UTC").getID())
        .addValue("detail", new SqlLobValue(bytes, getDbConnector().getDialect().getLobHandler()), Types.BLOB);

      records.add(docArgs);

      for (ExternalId id : Lists.newArrayList(ExternalId.of("SchemeX", "a"), ExternalId.of("SchemeX", "b"), ExternalId.of("SchemeX", "c"))) {
        final DbMapSqlParameterSource assocArgs = new DbMapSqlParameterSource()
          .addValue("doc_id", recordId)
          .addValue("key_scheme", id.getScheme().getName())
          .addValue("key_value", id.getValue());
        assocList.add(assocArgs);

      }
    }

    final String sqlDoc = bundle.getSql("Insert");

    final String sqlDoc2IdKey = bundle.getSql("InsertDoc2IdKey");

    getDbConnector().getJdbcTemplate().batchUpdate(
      sqlDoc,
      records.toArray(new DbMapSqlParameterSource[records.size()])
    );
    getDbConnector().getJdbcTemplate().batchUpdate(
      sqlDoc2IdKey,
      assocList.toArray(new DbMapSqlParameterSource[assocList.size()])
    );
  }

}
