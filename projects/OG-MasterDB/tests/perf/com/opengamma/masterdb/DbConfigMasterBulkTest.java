/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import java.io.ByteArrayOutputStream;

import javax.time.Instant;
import javax.time.TimeSource;

import org.fudgemsg.FudgeContext;
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

import com.opengamma.DataNotFoundException;
import com.opengamma.elsql.ElSqlBundle;
import com.opengamma.engine.view.ViewDefinition;
import com.opengamma.id.ExternalId;
import com.opengamma.id.UniqueId;
import com.opengamma.master.config.ConfigDocument;
import com.opengamma.master.config.ConfigSearchRequest;
import com.opengamma.masterdb.config.DbConfigMaster;
import com.opengamma.util.db.DbDateUtils;
import com.opengamma.util.db.DbMapSqlParameterSource;
import com.opengamma.util.fudgemsg.OpenGammaFudgeContext;
import com.opengamma.util.test.DbTest;


public class DbConfigMasterBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbConfigMasterBulkTest.class);

  protected DbConfigMaster _master;
  protected Instant _now;

  /**
   * The Fudge context.
   */
  protected static final FudgeContext FUDGE_CONTEXT = OpenGammaFudgeContext.getInstance();


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
    _now = Instant.now();
    _master.setTimeSource(TimeSource.fixed(_now));    
  }

  @Override
  protected AbstractDbMaster getMaster() {
    return _master;
  }

  @Operation(batchSize = 100)
  public void insert() {
    ConfigDocument<ExternalId> doc = new ConfigDocument<ExternalId>(ExternalId.class);
    doc.setName("TestConfig" + randomString(100));
    doc.setValue(ExternalId.of("A" + randomString(100), "B" + randomString(100)));
    _master.add(doc);
  }

  @Operation(batchSize = 100)
  public void search() {
    ConfigSearchRequest request = new ConfigSearchRequest();
    request.setType(ViewDefinition.class);
    _master.search(request);
    request.setName("dummy");
    _master.search(request);
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
    ConfigDocument<ExternalId> doc = new ConfigDocument<ExternalId>(ExternalId.class);
    doc.setName("TestConfig" + randomString(100));
    doc.setValue(ExternalId.of("A" + randomString(100), "B" + randomString(100)));
    doc.setUniqueId(lastInsertedDocumentUid);
    doc.setVersionFromInstant(Instant.now());
    doc.setVersionToInstant(null);
    doc.setCorrectionFromInstant(Instant.now());
    doc.setCorrectionToInstant(null);
    ConfigDocument corrected = _master.correct(doc);
    lastInsertedDocumentUid = corrected.getUniqueId();
  }

  @Operation(batchSize = 100)
  public void update() {
    ConfigDocument<ExternalId> doc = new ConfigDocument<ExternalId>(ExternalId.class);
    doc.setName("TestConfig" + randomString(100));
    doc.setValue(ExternalId.of("A" + randomString(100), "B" + randomString(100)));
    doc.setUniqueId(lastInsertedDocumentUid);
    ConfigDocument updated =_master.update(doc);
    lastInsertedDocumentUid = updated.getUniqueId();
  }


  @Test(groups = {"perftest"})
  public void testOperations() {
    testOperations(100, 100, 10);
  }

  @AfterMethod
  public void tearDown() throws Exception {
    _master = null;
    super.tearDown();
  }

  @Override
  protected void seed(int count) {

    SqlParameterSource[] records = new SqlParameterSource[count];

    for (int i = 0; i < count; i++) {

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      FUDGE_CONTEXT.writeObject(randomString(512), baos);
      byte[] bytes = baos.toByteArray();

      final long docId = nextId("cfg_config_seq");

      final long docOid = docId;

      lastInsertedDocumentUid = UniqueId.of(getMaster().getUniqueIdScheme(), docOid + "", (docId - docOid) + "");

      final DbMapSqlParameterSource docArgs = new DbMapSqlParameterSource()
        .addValue("doc_id", docId)
        .addValue("doc_oid", docOid)
        .addTimestamp("ver_from_instant", _now)
        .addTimestampNullFuture("ver_to_instant", null)//DbDateUtils.MAX_INSTANT)
        .addTimestamp("corr_from_instant", _now)
        .addTimestampNullFuture("corr_to_instant", null)//, DbDateUtils.MAX_INSTANT)
        .addValue("name", randomString(20))
        .addValue("config_type", String.class.getName())
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
