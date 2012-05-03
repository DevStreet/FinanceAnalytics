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

import com.opengamma.master.holiday.HolidayDocument;
import com.opengamma.master.holiday.HolidaySearchRequest;
import com.opengamma.master.holiday.ManageableHoliday;
import com.opengamma.masterdb.holiday.DbHolidayMaster;
import com.opengamma.util.money.Currency;
import com.opengamma.util.test.DbTest;


public class DbHolidayMasterBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbHolidayMasterBulkTest.class);

  protected DbHolidayMaster _master;

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

      ManageableHoliday holiday = new ManageableHoliday(Currency.USD, Arrays.asList(LocalDate.of(2010, 6, 9)));
      HolidayDocument doc = new HolidayDocument(holiday);
      doc.setName(randomString(20));
      _master.add(doc);
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
  public void insert() {
    ManageableHoliday holiday = new ManageableHoliday(Currency.USD, Arrays.asList(LocalDate.of(2010, 6, 9)));
    HolidayDocument doc = new HolidayDocument(holiday);
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


}
