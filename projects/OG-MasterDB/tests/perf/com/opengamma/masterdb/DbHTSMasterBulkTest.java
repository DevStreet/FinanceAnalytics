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
import javax.time.calendar.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.google.common.collect.Lists;
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
import com.opengamma.util.paging.PagingRequest;
import com.opengamma.util.test.DbTest;
import com.opengamma.util.tuple.Pair;

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

  @Operation(batchSize = 1)
  public void search() {
    HistoricalTimeSeriesInfoSearchRequest request = new HistoricalTimeSeriesInfoSearchRequest();
    request.setPagingRequest(PagingRequest.FIRST_PAGE);
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

  private static Set<Pair<String, String>> idKeySet = null;

  private void seedOnce() {
    if (idKeySet == null) {
      idKeySet = newHashSet();
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
    }
  }

  @Override
  protected void seed(int count) {

    seedOnce();

    NamedDimensionDbTable nameTable = new NamedDimensionDbTable(getDbConnector(), "name", "hts_name", "hts_dimension_seq");
    NamedDimensionDbTable dataFieldTable = new NamedDimensionDbTable(getDbConnector(), "data_field", "hts_data_field", "hts_dimension_seq");
    NamedDimensionDbTable observationTimeTable = new NamedDimensionDbTable(getDbConnector(), "observation_time", "hts_observation_time", "hts_dimension_seq");
    NamedDimensionDbTable dataSourceTable = new NamedDimensionDbTable(getDbConnector(), "data_source", "hts_data_source", "hts_dimension_seq");
    NamedDimensionDbTable dataProviderTable = new NamedDimensionDbTable(getDbConnector(), "data_provider", "hts_data_provider", "hts_dimension_seq");


    final List<DbMapSqlParameterSource> records = newArrayList();
    // the arguments for inserting into the idkey tables
    final List<DbMapSqlParameterSource> assocList = newArrayList();

    for (int i = 0; i < count; i++) {


      final long docId = nextId("hts_master_seq");

      // the arguments for inserting into the table
      ManageableHistoricalTimeSeriesInfo info = new ManageableHistoricalTimeSeriesInfo();

      info.setName("Added");
      info.setDataField("DF");
      info.setDataSource("DS");
      info.setDataProvider("DP");
      info.setObservationTime("OT");
            
      info.setExternalIdBundle(ExternalIdBundleWithDates.of(
        ExternalIdWithDates.of(ExternalId.of("SchemeX", "a"), LocalDate.now(), LocalDate.now().plusMonths(4)),
        ExternalIdWithDates.of(ExternalId.of("SchemeX", "b"), LocalDate.now(), LocalDate.now().plusMonths(4)),
        ExternalIdWithDates.of(ExternalId.of("SchemeX", "c"), LocalDate.now(), LocalDate.now().plusMonths(4))
      ));

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

      for (ExternalIdWithDates id : info.getExternalIdBundle()) {
        final DbMapSqlParameterSource assocArgs = new DbMapSqlParameterSource()
          .addValue("doc_id", docId)
          .addValue("key_scheme", id.getExternalId().getScheme().getName())
          .addValue("key_value", id.getExternalId().getValue())
          .addValue("valid_from", DbDateUtils.toSqlDateNullFarPast(id.getValidFrom()))
          .addValue("valid_to", DbDateUtils.toSqlDateNullFarFuture(id.getValidTo()));
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
