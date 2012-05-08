/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import java.util.HashMap;
import java.util.Map;

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
import com.opengamma.core.marketdatasnapshot.MarketDataValueSpecification;
import com.opengamma.core.marketdatasnapshot.ValueSnapshot;
import com.opengamma.core.marketdatasnapshot.YieldCurveKey;
import com.opengamma.core.marketdatasnapshot.YieldCurveSnapshot;
import com.opengamma.core.marketdatasnapshot.impl.ManageableMarketDataSnapshot;
import com.opengamma.core.marketdatasnapshot.impl.ManageableUnstructuredMarketDataSnapshot;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotDocument;
import com.opengamma.master.marketdatasnapshot.MarketDataSnapshotSearchRequest;
import com.opengamma.masterdb.marketdatasnapshot.DbMarketDataSnapshotMaster;
import com.opengamma.util.test.DbTest;

/**
 * Base tests for DbSecurityMasterWorker via DbSecurityMaster.
 */
public class DbMarketDataSnapshotMasterBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbMarketDataSnapshotMasterBulkTest.class);

  protected DbMarketDataSnapshotMaster _master;

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
  }

  @Override
  protected AbstractDbMaster getMaster() {
    return _master;
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

  private MarketDataSnapshotDocument lastInsertedDocument;

  @Operation(batchSize = 100)
  public void search() {
    MarketDataSnapshotSearchRequest request = new MarketDataSnapshotSearchRequest();
    _master.search(request);
    request.setName("dummy");
    _master.search(request);
  }

  @Operation(batchSize = 100)
  public MarketDataSnapshotDocument add() {
    ManageableMarketDataSnapshot marketDataSnapshot = new ManageableMarketDataSnapshot();
    marketDataSnapshot.setName("Test");
    HashMap<YieldCurveKey, YieldCurveSnapshot> yieldCurves = new HashMap<YieldCurveKey, YieldCurveSnapshot>();
    ManageableUnstructuredMarketDataSnapshot globalValues = new ManageableUnstructuredMarketDataSnapshot();
    globalValues.setValues(new HashMap<MarketDataValueSpecification, Map<String, ValueSnapshot>>());
    marketDataSnapshot.setGlobalValues(globalValues);
    marketDataSnapshot.setYieldCurves(yieldCurves);
    MarketDataSnapshotDocument doc = new MarketDataSnapshotDocument(marketDataSnapshot);
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
    MarketDataSnapshotDocument input = new MarketDataSnapshotDocument(lastInsertedDocument.getSnapshot()) {
      @Override
      public String getName() {
        return randomString(100);
      }
    };
    lastInsertedDocument = _master.correct(input);
  }

  @Operation(batchSize = 100)
  public void update() {
    MarketDataSnapshotDocument input = new MarketDataSnapshotDocument(lastInsertedDocument.getSnapshot()) {
      @Override
      public String getName() {
        return randomString(100);
      }
    };
    lastInsertedDocument = _master.update(input);
  }

  @Override
  protected void seed(int count) {
    for (int i = 0; i < count; i++) {
      lastInsertedDocument = add();
    }
  }
}
