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
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesInfoSearchRequest;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeriesInfo;
import com.opengamma.masterdb.historicaltimeseries.DbHistoricalTimeSeriesMaster;
import com.opengamma.masterdb.historicaltimeseries.NamedDimensionDbTable;
import com.opengamma.util.db.DbDateUtils;
import com.opengamma.util.db.DbMapSqlParameterSource;
import com.opengamma.util.test.DbTest;

/**
 * Base tests for DbSecurityMasterWorker via DbSecurityMaster.
 */
public class DbHTSMasterBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbHTSMasterBulkTest.class);

  protected DbHistoricalTimeSeriesMaster _master;
  protected Instant _version1Instant;
  protected Instant _version2Instant;

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

  @Operation(batchSize = 1)
  public void search() {
    HistoricalTimeSeriesInfoSearchRequest request = new HistoricalTimeSeriesInfoSearchRequest();
    _master.search(request);
    request.setName("dummy");
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

    ElSqlBundle bundle = ElSqlBundle.of(getDbConnector().getDialect().getElSqlConfig(), DbHistoricalTimeSeriesMaster.class);
    NamedDimensionDbTable nameTable = new NamedDimensionDbTable(getDbConnector(), "name", "hts_name", "hts_dimension_seq");
    NamedDimensionDbTable dataFieldTable = new NamedDimensionDbTable(getDbConnector(), "data_field", "hts_data_field", "hts_dimension_seq");
    NamedDimensionDbTable observationTimeTable = new NamedDimensionDbTable(getDbConnector(), "observation_time", "hts_observation_time", "hts_dimension_seq");
    NamedDimensionDbTable dataSourceTable = new NamedDimensionDbTable(getDbConnector(), "data_source", "hts_data_source", "hts_dimension_seq");
    NamedDimensionDbTable dataProviderTable = new NamedDimensionDbTable(getDbConnector(), "data_provider", "hts_data_provider", "hts_dimension_seq");

    for (int c = 0; c < count; c += CHUNK) {


      final List<DbMapSqlParameterSource> records = newArrayList();
      // the arguments for inserting into the idkey tables
      final List<DbMapSqlParameterSource> assocList = newArrayList();
      final List<DbMapSqlParameterSource> idKeyList = newArrayList();

      for (int i = 0; i < Math.min(CHUNK, count - c); i++) {


        final long docId = nextId("hts_master_seq");

        // the arguments for inserting into the table
        ManageableHistoricalTimeSeriesInfo info = new ManageableHistoricalTimeSeriesInfo();

        info.setName("Added");
        info.setDataField("DF");
        info.setDataSource("DS");
        info.setDataProvider("DP");
        info.setObservationTime("OT");
        info.setExternalIdBundle(ExternalIdBundleWithDates.of(ExternalIdWithDates.of(ExternalId.of("Scheme", randomString(6)), LocalDate.now(), LocalDate.now().plusMonths(4))));

        final DbMapSqlParameterSource docArgs = new DbMapSqlParameterSource()
          .addValue("doc_id", docId)
          .addValue("doc_oid", docId)
          .addTimestamp("ver_from_instant", Instant.now())
          .addTimestampNullFuture("ver_to_instant", null)
          .addTimestamp("corr_from_instant", Instant.now())
          .addTimestampNullFuture("corr_to_instant", null)
          .addValue("name_id", nameTable.ensure(info.getName()))
          .addValue("data_field_id", dataFieldTable.ensure(info.getDataField()))
          .addValue("data_source_id", dataSourceTable.ensure(info.getDataSource()))
          .addValue("data_provider_id", dataProviderTable.ensure(info.getDataProvider()))
          .addValue("observation_time_id", observationTimeTable.ensure(info.getObservationTime()));

        records.add(docArgs);

        final String sqlSelectIdKey = bundle.getSql("SelectIdKey");

        for (ExternalIdWithDates id : info.getExternalIdBundle()) {
          final DbMapSqlParameterSource assocArgs = new DbMapSqlParameterSource()
            .addValue("doc_id", docId)
            .addValue("key_scheme", id.getExternalId().getScheme().getName())
            .addValue("key_value", id.getExternalId().getValue())
            .addValue("valid_from", DbDateUtils.toSqlDateNullFarPast(id.getValidFrom()))
            .addValue("valid_to", DbDateUtils.toSqlDateNullFarFuture(id.getValidTo()));
          assocList.add(assocArgs);
          if (getDbConnector().getJdbcTemplate().queryForList(sqlSelectIdKey, assocArgs).isEmpty()) {
            // select avoids creating unecessary id, but id may still not be used
            final long idKeyId = nextId("hts_idkey_seq");
            final DbMapSqlParameterSource idkeyArgs = new DbMapSqlParameterSource()
              .addValue("idkey_id", idKeyId)
              .addValue("key_scheme", id.getExternalId().getScheme().getName())
              .addValue("key_value", id.getExternalId().getValue());
            idKeyList.add(idkeyArgs);
          }
        }

      }

      final String sqlDoc = bundle.getSql("Insert");
      final String sqlIdKey = bundle.getSql("InsertIdKey");
      final String sqlDoc2IdKey = bundle.getSql("InsertDoc2IdKey");

      getDbConnector().getJdbcTemplate().batchUpdate(
        sqlDoc,
        records.toArray(new DbMapSqlParameterSource[records.size()])
      );
      getDbConnector().getJdbcTemplate().batchUpdate(
        sqlIdKey,
        idKeyList.toArray(new DbMapSqlParameterSource[idKeyList.size()])
      );
      getDbConnector().getJdbcTemplate().batchUpdate(
        sqlDoc2IdKey,
        assocList.toArray(new DbMapSqlParameterSource[assocList.size()])
      );
    }
  }

}
