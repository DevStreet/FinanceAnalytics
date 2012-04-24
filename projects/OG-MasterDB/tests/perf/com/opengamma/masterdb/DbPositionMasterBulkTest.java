/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import java.math.BigDecimal;
import java.util.*;

import javax.time.Instant;
import javax.time.TimeSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.opengamma.elsql.ElSqlBundle;
import com.opengamma.id.ExternalId;
import com.opengamma.id.IdUtils;
import com.opengamma.id.UniqueId;
import com.opengamma.master.position.ManageablePosition;
import com.opengamma.master.position.ManageableTrade;
import com.opengamma.master.position.PositionSearchRequest;
import com.opengamma.masterdb.position.DbPositionMaster;
import com.opengamma.util.db.DbMapSqlParameterSource;
import com.opengamma.util.test.DbTest;
import com.opengamma.util.tuple.Pair;

/**
 * Base tests for DbSecurityMasterWorker via DbSecurityMaster.
 */
public class DbPositionMasterBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbPositionMasterBulkTest.class);

  protected DbPositionMaster _master;
  protected Instant _version1Instant;
  protected Instant _version2Instant;

  @Factory(dataProvider = "databases", dataProviderClass = DbTest.class)
  public DbPositionMasterBulkTest(String databaseType, String databaseVersion) {
    super(databaseType, databaseVersion);
    s_logger.info("running testcases for {}", databaseType);
  }


  @BeforeMethod
  public void setUp() throws Exception {
    super.setUp();
    ConfigurableApplicationContext context = DbMasterTestUtils.getContext(getDatabaseType());
    _master = (DbPositionMaster) context.getBean(getDatabaseType() + "DbPositionMaster");
    Instant now = Instant.now();
    _master.setTimeSource(TimeSource.fixed(now));
    _version1Instant = now.minusSeconds(100);
    _version2Instant = now.minusSeconds(50);
  }


  @Operation(batchSize = 1)
  public void search() {
    PositionSearchRequest request = new PositionSearchRequest();
    _master.search(request);
    request.setMinQuantity(BigDecimal.valueOf(123));
    _master.search(request);
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

  @Override
  protected void seed(int count) {

    final int CHUNK = 1000;

    ElSqlBundle bundle = ElSqlBundle.of(getDbConnector().getDialect().getElSqlConfig(), DbPositionMaster.class);

    for (int c = 0; c < count; c += CHUNK) {

      final List<DbMapSqlParameterSource> records = Lists.newArrayList();

      // the arguments for inserting into the pos_attribute table
      final List<DbMapSqlParameterSource> posAttrList = Lists.newArrayList();
      // the arguments for inserting into the idkey tables
      final List<DbMapSqlParameterSource> posAssocList = new ArrayList<DbMapSqlParameterSource>();

      // the arguments for inserting into the trade table
      final List<DbMapSqlParameterSource> tradeList = Lists.newArrayList();
      final List<DbMapSqlParameterSource> tradeAssocList = Lists.newArrayList();
      final List<DbMapSqlParameterSource> tradeAttributeList = Lists.newArrayList();

      final List<DbMapSqlParameterSource> idKeyList = new ArrayList<DbMapSqlParameterSource>();

      for (int i = 0; i < Math.min(CHUNK, count - c); i++) {

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        final long positionId = nextId("pos_master_seq");
        final long positionOid = positionId;
        final UniqueId positionUid = UniqueId.of(randomString(5), Long.toString(positionId), "0");
        final ManageablePosition position = new ManageablePosition(new BigDecimal(_random.nextLong()), ExternalId.of(randomString(5), randomString(10)));

        // the arguments for inserting into the position table
        final DbMapSqlParameterSource docArgs = new DbMapSqlParameterSource()
          .addValue("position_id", positionId)
          .addValue("position_oid", positionOid)
          .addTimestamp("ver_from_instant", Instant.now())
          .addTimestampNullFuture("ver_to_instant", null)
          .addTimestamp("corr_from_instant", Instant.now())
          .addTimestampNullFuture("corr_to_instant", null)
          .addValue("quantity", position.getQuantity())
          .addValue("provider_scheme", (position.getProviderId() != null ? position.getProviderId().getScheme().getName() : null))
          .addValue("provider_value", (position.getProviderId() != null ? position.getProviderId().getValue() : null));

        records.add(docArgs);

        for (Map.Entry<String, String> entry : position.getAttributes().entrySet()) {
          final long posAttrId = nextId("pos_trade_attr_seq");
          final DbMapSqlParameterSource posAttrArgs = new DbMapSqlParameterSource()
            .addValue("attr_id", posAttrId)
            .addValue("pos_id", positionId)
            .addValue("pos_oid", positionOid)
            .addValue("key", entry.getKey())
            .addValue("value", entry.getValue());
          posAttrList.add(posAttrArgs);
        }


        final Set<Pair<String, String>> schemeValueSet = Sets.newHashSet();
        for (ExternalId id : position.getSecurityLink().getAllExternalIds()) {
          final DbMapSqlParameterSource assocArgs = new DbMapSqlParameterSource()
            .addValue("position_id", positionId)
            .addValue("key_scheme", id.getScheme().getName())
            .addValue("key_value", id.getValue());
          posAssocList.add(assocArgs);
          schemeValueSet.add(Pair.of(id.getScheme().getName(), id.getValue()));
        }

        for (ManageableTrade trade : position.getTrades()) {
          final long tradeId = nextId("pos_master_seq");
          final long tradeOid = tradeId;
          final ExternalId counterpartyId = trade.getCounterpartyExternalId();

          final DbMapSqlParameterSource tradeArgs = new DbMapSqlParameterSource()
            .addValue("trade_id", tradeId)
            .addValue("trade_oid", tradeOid)
            .addValue("position_id", positionId)
            .addValue("position_oid", positionOid)
            .addValue("quantity", trade.getQuantity())
            .addDate("trade_date", trade.getTradeDate())
            .addTimeAllowNull("trade_time", trade.getTradeTime() != null ? trade.getTradeTime().toLocalTime() : null)
            .addValue("zone_offset", (trade.getTradeTime() != null ? trade.getTradeTime().getOffset().getAmountSeconds() : null))
            .addValue("cparty_scheme", counterpartyId.getScheme().getName())
            .addValue("cparty_value", counterpartyId.getValue())
            .addValue("provider_scheme", (position.getProviderId() != null ? position.getProviderId().getScheme().getName() : null))
            .addValue("provider_value", (position.getProviderId() != null ? position.getProviderId().getValue() : null))
            .addValue("premium_value", (trade.getPremium() != null ? trade.getPremium() : null))
            .addValue("premium_currency", (trade.getPremiumCurrency() != null ? trade.getPremiumCurrency().getCode() : null))
            .addDateAllowNull("premium_date", trade.getPremiumDate())
            .addTimeAllowNull("premium_time", (trade.getPremiumTime() != null ? trade.getPremiumTime().toLocalTime() : null))
            .addValue("premium_zone_offset", (trade.getPremiumTime() != null ? trade.getPremiumTime().getOffset().getAmountSeconds() : null));
          tradeList.add(tradeArgs);

          // trade attributes
          Map<String, String> attributes = new HashMap<String, String>(trade.getAttributes());
          for (Map.Entry<String, String> entry : attributes.entrySet()) {
            final long tradeAttrId = nextId("pos_trade_attr_seq");
            final DbMapSqlParameterSource tradeAttributeArgs = new DbMapSqlParameterSource()
              .addValue("attr_id", tradeAttrId)
              .addValue("trade_id", tradeId)
              .addValue("trade_oid", tradeOid)
              .addValue("key", entry.getKey())
              .addValue("value", entry.getValue());
            tradeAttributeList.add(tradeAttributeArgs);
          }

          // set the trade uniqueId
          final UniqueId tradeUid = UniqueId.of(randomString(5), Long.toString(tradeId), "0");
          IdUtils.setInto(trade, tradeUid);
          trade.setParentPositionId(positionUid);
          for (ExternalId id : trade.getSecurityLink().getAllExternalIds()) {
            final DbMapSqlParameterSource assocArgs = new DbMapSqlParameterSource()
              .addValue("trade_id", tradeId)
              .addValue("key_scheme", id.getScheme().getName())
              .addValue("key_value", id.getValue());
            tradeAssocList.add(assocArgs);
            schemeValueSet.add(Pair.of(id.getScheme().getName(), id.getValue()));
          }
        }

        final String sqlSelectIdKey = bundle.getSql("SelectIdKey");
        for (Pair<String, String> pair : schemeValueSet) {
          final DbMapSqlParameterSource idkeyArgs = new DbMapSqlParameterSource()
            .addValue("key_scheme", pair.getFirst())
            .addValue("key_value", pair.getSecond());
          if (getDbConnector().getJdbcTemplate().queryForList(sqlSelectIdKey, idkeyArgs).isEmpty()) {
            // select avoids creating unecessary id, but id may still not be used
            final long idKeyId = nextId("pos_idkey_seq");
            idkeyArgs.addValue("idkey_id", idKeyId);
            idKeyList.add(idkeyArgs);
          }
        }
      }


      final String sqlDoc = bundle.getSql("Insert");
      final String sqlIdKey = bundle.getSql("InsertIdKey");
      final String sqlPosition2IdKey = bundle.getSql("InsertPosition2IdKey");
      final String sqlTrade = bundle.getSql("InsertTrade");
      final String sqlTrade2IdKey = bundle.getSql("InsertTrade2IdKey");
      final String sqlPositionAttributes = bundle.getSql("InsertPositionAttributes");
      final String sqlTradeAttributes = bundle.getSql("InsertTradeAttributes");


      getDbConnector().getJdbcTemplate().batchUpdate(sqlDoc, records.toArray(new DbMapSqlParameterSource[records.size()]));
      getDbConnector().getJdbcTemplate().batchUpdate(sqlIdKey, idKeyList.toArray(new DbMapSqlParameterSource[idKeyList.size()]));
      getDbConnector().getJdbcTemplate().batchUpdate(sqlPosition2IdKey, posAssocList.toArray(new DbMapSqlParameterSource[posAssocList.size()]));
      getDbConnector().getJdbcTemplate().batchUpdate(sqlTrade, tradeList.toArray(new DbMapSqlParameterSource[tradeList.size()]));
      getDbConnector().getJdbcTemplate().batchUpdate(sqlTrade2IdKey, tradeAssocList.toArray(new DbMapSqlParameterSource[tradeAssocList.size()]));
      getDbConnector().getJdbcTemplate().batchUpdate(sqlPositionAttributes, posAttrList.toArray(new DbMapSqlParameterSource[posAttrList.size()]));
      getDbConnector().getJdbcTemplate().batchUpdate(sqlTradeAttributes, tradeAttributeList.toArray(new DbMapSqlParameterSource[tradeAttributeList.size()]));

    }
  }


}
