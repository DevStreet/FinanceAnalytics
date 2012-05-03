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
import java.util.concurrent.atomic.AtomicBoolean;

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
import com.opengamma.DataNotFoundException;
import com.opengamma.elsql.ElSqlBundle;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.UniqueId;
import com.opengamma.master.config.ConfigDocument;
import com.opengamma.master.exchange.ExchangeDocument;
import com.opengamma.master.exchange.ExchangeSearchRequest;
import com.opengamma.master.exchange.ManageableExchange;
import com.opengamma.masterdb.exchange.DbExchangeMaster;
import com.opengamma.util.db.DbMapSqlParameterSource;
import com.opengamma.util.test.DbTest;
import com.opengamma.util.tuple.Pair;


public class DbExhangeMasterBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbExhangeMasterBulkTest.class);

  protected DbExchangeMaster _master;

  private UniqueId lastInsertedDocumentUid;

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
  }

  @Override
  protected AbstractDbMaster getMaster() {
    return _master;
  }

  @Operation(batchSize = 100)
  public void search() {
    ExchangeSearchRequest request = new ExchangeSearchRequest();
    request.setName("dummy");
    _master.search(request);
  }

  private static final ExternalIdBundle BUNDLE = ExternalIdBundle.of("A", "B");
  private static final ExternalIdBundle REGION = ExternalIdBundle.of("C", "D");

  @Operation(batchSize = 100)
  public ExchangeDocument add() {
    ManageableExchange exchange = new ManageableExchange(BUNDLE, "Test", REGION, null);
    ExchangeDocument doc = new ExchangeDocument(exchange);
    return _master.add(doc);
  }

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
    ManageableExchange exchange = new ManageableExchange(BUNDLE, "Test", REGION, null);
    exchange.setUniqueId(lastInsertedDocumentUid);
    ExchangeDocument doc = new ExchangeDocument(exchange);
    doc.setUniqueId(lastInsertedDocumentUid);
    doc.setVersionFromInstant(Instant.now());
    doc.setVersionToInstant(null);
    doc.setCorrectionFromInstant(Instant.now());
    doc.setCorrectionToInstant(null);
    ExchangeDocument corrected = _master.correct(doc);
    lastInsertedDocumentUid = corrected.getUniqueId();
  }

  @Operation(batchSize = 100)
  public void update() {
    ManageableExchange exchange = new ManageableExchange(BUNDLE, "Test", REGION, null);
    exchange.setUniqueId(lastInsertedDocumentUid);
    ExchangeDocument doc = new ExchangeDocument(exchange);
    doc.setUniqueId(lastInsertedDocumentUid);
    doc.setVersionFromInstant(Instant.now());
    doc.setVersionToInstant(null);
    doc.setCorrectionFromInstant(Instant.now());
    doc.setCorrectionToInstant(null);
    ExchangeDocument updated = _master.update(doc);
    lastInsertedDocumentUid = updated.getUniqueId();
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
    for (int i = 0; i < count; i++) {      
      ExchangeDocument document = add();
      lastInsertedDocumentUid = document.getUniqueId();
    }    
  }

}
