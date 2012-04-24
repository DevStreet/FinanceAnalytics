/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.masterdb;

import java.util.LinkedList;

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

import com.opengamma.master.holiday.HolidaySearchRequest;
import com.opengamma.masterdb.holiday.DbHolidayMaster;
import com.opengamma.util.db.DbDateUtils;
import com.opengamma.util.test.DbTest;


public class DbHolidayMasterBulkTest extends AbstractDbBulkTest {

  private static final Logger s_logger = LoggerFactory.getLogger(DbHolidayMasterBulkTest.class);

  protected DbHolidayMaster _master;
  protected Instant _version1Instant;
  protected Instant _version2Instant;

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
    _version1Instant = now.minusSeconds(100);
    _version2Instant = now.minusSeconds(50);
  }


  protected void seed() {
    throw new RuntimeException("dummy - not used really");
  }

  private int holidayId = 1;

  @Override
  protected void seed(int count) {

    final int CHUNK = 1000;

    for (int c = 0; c < count; c += CHUNK) {

      LinkedList<Object[]> holidays = new LinkedList<Object[]>();
      LinkedList<Object[]> dates = new LinkedList<Object[]>();

      for (int i = 0; i < Math.min(CHUNK, count - c); i++) {

        holidays.add(new Object[]{
          ++holidayId, // id 
          _random.nextInt(), // oid
          DbDateUtils.toSqlTimestamp(Instant.now()), // ver_from_instant
          DbDateUtils.MAX_SQL_TIMESTAMP, // ver_from_instant
          DbDateUtils.toSqlTimestamp(Instant.now()), // corr_from_instant
          DbDateUtils.MAX_SQL_TIMESTAMP,  // corr_to_instant
          randomString(20), // name
          "BANK", // hol_type
          "provider_scheme", // provider_scheme
          "provider_value ", // provider_value
          "region_scheme", // region_scheme
          "region_value", // region_value
          "exchange_scheme", // exchange_scheme
          "exchange_value", // exchange_value
          "USD"// currency_iso      
        });

        dates.add(new Object[]{
          holidayId, // holiday_id 
          DbDateUtils.toSqlDate(LocalDate.now()), // hol_date                
        });
      }

      getDbConnector().getJdbcTemplate().batchUpdate(
        "INSERT INTO hol_holiday" +
          "  (id, oid, ver_from_instant, ver_to_instant, corr_from_instant, corr_to_instant, name, hol_type," +
          "   provider_scheme, provider_value, region_scheme, region_value, exchange_scheme, exchange_value, currency_iso)" +
          "VALUES" +
          "   (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",

        holidays
      );

      getDbConnector().getJdbcTemplate().batchUpdate(
        "INSERT INTO hol_date" +
          "    (holiday_id, hol_date)" +
          "VALUES" +
          "    (?, ?)",

        dates
      );


    }
  }

  @Operation(batchSize = 1)
  public void search() {
    HolidaySearchRequest request = new HolidaySearchRequest();
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


}
