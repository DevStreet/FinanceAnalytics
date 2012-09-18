/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

import java.util.Iterator;
import java.util.TreeSet;

import javax.time.calendar.ZonedDateTime;

import org.testng.annotations.Test;

import com.opengamma.core.security.Security;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCountFactory;
import com.opengamma.financial.fudgemsg.FinancialTestBase;
import com.opengamma.financial.security.cash.CashSecurity;
import com.opengamma.financial.security.deposit.PeriodicZeroDepositSecurity;
import com.opengamma.financial.security.future.InterestRateFutureSecurity;
import com.opengamma.id.ExternalId;
import com.opengamma.util.i18n.Country;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.DateUtils;
import com.opengamma.util.time.Expiry;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class CurveStripWithSecurityTest extends FinancialTestBase {
  private static final Currency CCY = Currency.USD;
  private static final ExternalId REGION = ExternalId.of("Test", Country.US.getObjectId().getValue());
  private static final ZonedDateTime NOW = DateUtils.getUTCDate(2012, 8, 1);
  private static final DayCount DC = DayCountFactory.INSTANCE.getDayCount("Act/360");
  private static final String NAME = "NAME";
  private static final RateStrip RATE1 = new RateStrip(RateType.Bankers_Acceptance, Tenor.ONE_MONTH, NAME);
  private static final ExternalId RATE_ID1 = ExternalId.of("Test", "BA1M");
  private static final ZonedDateTime RATE_MATURITY1 = DateUtils.getUTCDate(2012, 9, 1);
  private static final Tenor RATE_TENOR1 = Tenor.ONE_MONTH;
  private static final Security RATE_SECURITY1 = new CashSecurity(CCY, REGION, NOW, RATE_MATURITY1, DC, 0.04, 1);
  private static final RateStrip RATE2 = new RateStrip(RateType.Bankers_Acceptance, Tenor.TWO_MONTHS, NAME);
  private static final ExternalId RATE_ID2 = ExternalId.of("Test", "BA2M");
  private static final ZonedDateTime RATE_MATURITY2 = DateUtils.getUTCDate(2012, 10, 1);
  private static final Tenor RATE_TENOR2 = Tenor.TWO_MONTHS;
  private static final CashSecurity RATE_SECURITY2 = new CashSecurity(CCY, REGION, NOW, RATE_MATURITY2, DC, 0.045, 1);
  private static final PeriodicZeroDepositStrip DEPOSIT1 = new PeriodicZeroDepositStrip(12, Tenor.ONE_YEAR, NAME);
  private static final ExternalId DEPOSIT_ID1 = ExternalId.of("Test", "D1Y");
  private static final ZonedDateTime DEPOSIT_MATURITY1 = DateUtils.getUTCDate(2013, 8, 1);
  private static final Tenor DEPOSIT_TENOR1 = Tenor.ONE_YEAR;
  private static final Security DEPOSIT_SECURITY1 = new PeriodicZeroDepositSecurity(CCY, NOW, DEPOSIT_MATURITY1, 0.05, 12, REGION);
  private static final PeriodicZeroDepositStrip DEPOSIT2 = new PeriodicZeroDepositStrip(12, Tenor.TWO_YEARS, NAME);
  private static final ExternalId DEPOSIT_ID2 = ExternalId.of("Test", "D2Y");
  private static final ZonedDateTime DEPOSIT_MATURITY2 = DateUtils.getUTCDate(2014, 8, 1);
  private static final Tenor DEPOSIT_TENOR2 = Tenor.TWO_YEARS;
  private static final Security DEPOSIT_SECURITY2 = new PeriodicZeroDepositSecurity(CCY, NOW, DEPOSIT_MATURITY1, 0.06, 12, REGION);
  private static final RateFutureStrip FUTURE = new RateFutureStrip(FutureType.IMM, 1, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, Tenor.TWO_YEARS, NAME);
  private static final Expiry FUTURE_MATURITY = new Expiry(DateUtils.getUTCDate(2014, 8, 1));
  private static final ExternalId FUTURE_ID = ExternalId.of("Test", "IMM2Y");
  private static final Security FUTURE_SECURITY = new InterestRateFutureSecurity(FUTURE_MATURITY, "CME", "CME", CCY, 1, RATE_ID1, "IMM");
  private static final CurveStripWithSecurity STRIP1 = new CurveStripWithSecurity(RATE1, RATE_TENOR1, RATE_MATURITY1, RATE_ID1, RATE_SECURITY1);
  private static final CurveStripWithSecurity STRIP2 = new CurveStripWithSecurity(RATE2, RATE_TENOR2, RATE_MATURITY2, RATE_ID2, RATE_SECURITY2);
  private static final CurveStripWithSecurity STRIP3 = new CurveStripWithSecurity(DEPOSIT1, DEPOSIT_TENOR1, DEPOSIT_MATURITY1, DEPOSIT_ID1, DEPOSIT_SECURITY1);
  private static final CurveStripWithSecurity STRIP4 = new CurveStripWithSecurity(DEPOSIT2, DEPOSIT_TENOR2, DEPOSIT_MATURITY2, DEPOSIT_ID2, DEPOSIT_SECURITY2);
  private static final CurveStripWithSecurity STRIP5 = new CurveStripWithSecurity(FUTURE, Tenor.TWO_YEARS, FUTURE_MATURITY.getExpiry(), FUTURE_ID, FUTURE_SECURITY);

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullStrip() {
    new CurveStripWithSecurity(null, RATE_TENOR1, RATE_MATURITY1, RATE_ID1, RATE_SECURITY1);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullTenor() {
    new CurveStripWithSecurity(RATE1, null, RATE_MATURITY1, RATE_ID1, RATE_SECURITY1);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullMaturity() {
    new CurveStripWithSecurity(RATE1, RATE_TENOR1, null, RATE_ID1, RATE_SECURITY1);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullId() {
    new CurveStripWithSecurity(RATE1, RATE_TENOR1, RATE_MATURITY1, null, RATE_SECURITY1);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullSecurity() {
    new CurveStripWithSecurity(RATE1, RATE_TENOR1, RATE_MATURITY1, RATE_ID1, null);
  }

  @Test
  public void testObject() {
    assertEquals(STRIP1.getStrip(), RATE1);
    assertEquals(STRIP1.getMaturity(), RATE_MATURITY1);
    assertEquals(STRIP1.getResolvedTenor(), RATE_TENOR1);
    assertEquals(STRIP1.getSecurity(), RATE_SECURITY1);
    assertEquals(STRIP1.getSecurityIdentifier(), RATE_ID1);
    CurveStripWithSecurity other = new CurveStripWithSecurity(RATE1, RATE_TENOR1, RATE_MATURITY1, RATE_ID1, RATE_SECURITY1);
    assertEquals(STRIP1, other);
    assertEquals(STRIP1.hashCode(), other.hashCode());
    other = new CurveStripWithSecurity(RATE2, RATE_TENOR1, RATE_MATURITY1, RATE_ID1, RATE_SECURITY1);
    assertFalse(STRIP1.equals(other));
    other = new CurveStripWithSecurity(RATE1, RATE_TENOR2, RATE_MATURITY1, RATE_ID1, RATE_SECURITY1);
    assertFalse(STRIP1.equals(other));
    other = new CurveStripWithSecurity(RATE1, RATE_TENOR1, RATE_MATURITY2, RATE_ID1, RATE_SECURITY1);
    assertFalse(STRIP1.equals(other));
    other = new CurveStripWithSecurity(RATE1, RATE_TENOR1, RATE_MATURITY1, RATE_ID2, RATE_SECURITY1);
    assertFalse(STRIP1.equals(other));
    other = new CurveStripWithSecurity(RATE1, RATE_TENOR1, RATE_MATURITY1, RATE_ID1, RATE_SECURITY2);
    assertFalse(STRIP1.equals(other));
  }

  @Test
  public void testCompare() {
    final TreeSet<CurveStripWithSecurity> set = new TreeSet<CurveStripWithSecurity>();
    set.add(STRIP3);
    set.add(STRIP1);
    set.add(STRIP5);
    set.add(STRIP2);
    set.add(STRIP4);
    final Iterator<CurveStripWithSecurity> iter = set.iterator();
    assertEquals(STRIP1, iter.next());
    assertEquals(STRIP2, iter.next());
    assertEquals(STRIP3, iter.next());
    assertEquals(STRIP4, iter.next());
    assertEquals(STRIP5, iter.next());
  }

  @Test
  public void testCycle() {
    assertEquals(STRIP1, cycleObject(CurveStripWithSecurity.class, STRIP1));
    assertEquals(STRIP2, cycleObject(CurveStripWithSecurity.class, STRIP2));
    assertEquals(STRIP3, cycleObject(CurveStripWithSecurity.class, STRIP3));
    assertEquals(STRIP4, cycleObject(CurveStripWithSecurity.class, STRIP4));
    assertEquals(STRIP5, cycleObject(CurveStripWithSecurity.class, STRIP5));
  }
}
