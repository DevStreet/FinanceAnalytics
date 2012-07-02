/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import java.util.Arrays;

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

import com.opengamma.DataNotFoundException;
import com.opengamma.id.UniqueId;
import com.opengamma.master.holiday.HolidayDocument;
import com.opengamma.master.holiday.HolidaySearchRequest;
import com.opengamma.master.holiday.ManageableHoliday;
import com.opengamma.masterdb.holiday.DbHolidayMaster;
import com.opengamma.util.money.Currency;
import com.opengamma.util.test.DbTest;


public class DbHolidayMasterBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbHolidayMasterBulkTest.class);

  protected DbHolidayMaster _master;

  private UniqueId lastInsertedDocumentUid;

  @Factory(dataProvider = "databases", dataProviderClass = DbTest.class)
  public DbHolidayMasterBulkTest(String databaseType, String databaseVersion) {
    super(databaseType, databaseVersion);
    s_logger.info("running testcases for {}", databaseType);
  }


  @BeforeMethod
  public void setUp() throws Exception {
    super.setUp();
    ConfigurableApplicationContext context = DbMasterTestUtils.getContext(getDatabaseType());
    _master = (DbHolidayMaster) context.getBean(getDatabaseType() + "DbHolidayMaster");
    Instant now = Instant.now();
    _master.setTimeSource(TimeSource.fixed(now));
  }

  @Override
  protected AbstractDbMaster getMaster() {
    return _master;
  }

  protected void seed() {
    throw new RuntimeException("dummy - not used really");
  }


  @Override
  protected void seed(int count) {
    for (int i = 0; i < count; i++) {
      lastInsertedDocumentUid = add().getUniqueId();
    }
  }

  @Operation(batchSize = 100)
  public void search() {
    HolidaySearchRequest request = new HolidaySearchRequest();
    _master.search(request);
    request.setName("dummy");
    _master.search(request);
  }

  @Operation(batchSize = 100)
  public HolidayDocument add() {
    ManageableHoliday holiday = new ManageableHoliday(Currency.USD, Arrays.asList(LocalDate.of(2010, 6, 9)));
    HolidayDocument doc = new HolidayDocument(holiday);
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
    ManageableHoliday holiday = new ManageableHoliday(Currency.USD, Arrays.asList(LocalDate.of(2010, 6, 9)));
    holiday.setUniqueId(lastInsertedDocumentUid);
    HolidayDocument doc = new HolidayDocument(holiday);
    HolidayDocument corrected = _master.correct(doc);
    lastInsertedDocumentUid = corrected.getUniqueId();
  }

  @Operation(batchSize = 100)
  public void update() {
    ManageableHoliday holiday = new ManageableHoliday(Currency.USD, Arrays.asList(LocalDate.of(2010, 6, 9)));
    holiday.setUniqueId(lastInsertedDocumentUid);
    HolidayDocument doc = new HolidayDocument(holiday);
    HolidayDocument updated = _master.update(doc);
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


}
