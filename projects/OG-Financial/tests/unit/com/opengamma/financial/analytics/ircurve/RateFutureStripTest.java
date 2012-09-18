/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

import javax.time.calendar.Period;

import org.testng.annotations.Test;

import com.opengamma.financial.fudgemsg.FinancialTestBase;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class RateFutureStripTest extends FinancialTestBase {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullFutureType() {
    new RateFutureStrip(null, 2, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, Tenor.FIVE_YEARS, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullFutureTenor() {
    new RateFutureStrip(FutureType.IMM, 2, null, Tenor.THREE_MONTHS, Tenor.FIVE_YEARS, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullRateTenor() {
    new RateFutureStrip(FutureType.IMM, 2, Tenor.THREE_MONTHS, null, Tenor.FIVE_YEARS, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullTenor() {
    new RateFutureStrip(FutureType.IMM, 2, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, null, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullName() {
    new RateFutureStrip(FutureType.IMM, 2, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, Tenor.FIVE_YEARS, null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNegativeFutureNumber() {
    new RateFutureStrip(null, -2, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, Tenor.FIVE_YEARS, "a");
  }

  @Test
  public void testObject() {
    final RateFutureStrip strip = new RateFutureStrip(FutureType.IMM, 1, Tenor.THREE_MONTHS, Tenor.ONE_MONTH, Tenor.ONE_YEAR, "a");
    assertEquals(strip.getConfigurationName(), "a");
    assertEquals(strip.getCurveNodePointTime(), Tenor.ONE_YEAR);
    assertEquals(strip.getFutureTenor(), strip.getFutureTenor());
    assertEquals(strip.getFutureType(), strip.getFutureType());
    assertEquals(strip.getInstrumentType(), NewStripInstrumentType.FUTURE);
    assertEquals(strip.getNumberOfFuturesAfterTenor(), 1);
    assertEquals(strip.getRateTenor(), Tenor.ONE_MONTH);
    RateFutureStrip other = new RateFutureStrip(FutureType.IMM, 1, Tenor.THREE_MONTHS, Tenor.ONE_MONTH, Tenor.ONE_YEAR, "a");
    assertEquals(strip, other);
    assertEquals(strip.hashCode(), other.hashCode());
    other = new RateFutureStrip(FutureType.BA, 1, Tenor.THREE_MONTHS, Tenor.ONE_MONTH, Tenor.ONE_YEAR, "a");
    assertFalse(other.equals(strip));
    other = new RateFutureStrip(FutureType.IMM, 2, Tenor.THREE_MONTHS, Tenor.ONE_MONTH, Tenor.ONE_YEAR, "a");
    assertFalse(other.equals(strip));
    other = new RateFutureStrip(FutureType.IMM, 1, Tenor.TWO_MONTHS, Tenor.ONE_MONTH, Tenor.ONE_YEAR, "a");
    assertFalse(other.equals(strip));
    other = new RateFutureStrip(FutureType.IMM, 1, Tenor.THREE_MONTHS, Tenor.TWO_MONTHS, Tenor.ONE_YEAR, "a");
    assertFalse(other.equals(strip));
    other = new RateFutureStrip(FutureType.IMM, 1, Tenor.THREE_MONTHS, Tenor.ONE_MONTH, Tenor.TWO_YEARS, "a");
    assertFalse(other.equals(strip));
    other = new RateFutureStrip(FutureType.IMM, 1, Tenor.THREE_MONTHS, Tenor.ONE_MONTH, Tenor.ONE_YEAR, "b");
    assertFalse(other.equals(strip));
  }

  @Test
  public void testEffectiveTenor() {
    RateFutureStrip strip = new RateFutureStrip(FutureType.IMM, 1, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, new Tenor(Period.ZERO), "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.THREE_MONTHS);
    strip = new RateFutureStrip(FutureType.IMM, 2, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, new Tenor(Period.ZERO), "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.SIX_MONTHS);
    strip = new RateFutureStrip(FutureType.IMM, 8, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, new Tenor(Period.ZERO), "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.TWO_YEARS);
    strip = new RateFutureStrip(FutureType.IMM, 1, Tenor.ONE_MONTH, Tenor.THREE_MONTHS, new Tenor(Period.ZERO), "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.ONE_MONTH);
    strip = new RateFutureStrip(FutureType.IMM, 2, Tenor.ONE_MONTH, Tenor.THREE_MONTHS, new Tenor(Period.ZERO), "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.TWO_MONTHS);
    strip = new RateFutureStrip(FutureType.IMM, 8, Tenor.ONE_MONTH, Tenor.THREE_MONTHS, new Tenor(Period.ZERO), "a");
    assertEquals(strip.getEffectiveTenor(), new Tenor(Period.ofMonths(8)));
    strip = new RateFutureStrip(FutureType.IMM, 1, Tenor.THREE_MONTHS, Tenor.ONE_MONTH, new Tenor(Period.ZERO), "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.THREE_MONTHS);
    strip = new RateFutureStrip(FutureType.IMM, 2, Tenor.THREE_MONTHS, Tenor.ONE_MONTH, new Tenor(Period.ZERO), "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.SIX_MONTHS);
    strip = new RateFutureStrip(FutureType.IMM, 8, Tenor.THREE_MONTHS, Tenor.ONE_MONTH, new Tenor(Period.ZERO), "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.TWO_YEARS);
    strip = new RateFutureStrip(FutureType.IMM, 1, Tenor.ONE_MONTH, Tenor.ONE_MONTH, new Tenor(Period.ZERO), "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.ONE_MONTH);
    strip = new RateFutureStrip(FutureType.IMM, 2, Tenor.ONE_MONTH, Tenor.ONE_MONTH, new Tenor(Period.ZERO), "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.TWO_MONTHS);
    strip = new RateFutureStrip(FutureType.IMM, 8, Tenor.ONE_MONTH, Tenor.ONE_MONTH, new Tenor(Period.ZERO), "a");
    assertEquals(strip.getEffectiveTenor(), new Tenor(Period.ofMonths(8)));
    strip = new RateFutureStrip(FutureType.IMM, 1, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, Tenor.FIVE_MONTHS, "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.EIGHT_MONTHS);
    strip = new RateFutureStrip(FutureType.IMM, 2, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, Tenor.FIVE_MONTHS, "a");
    assertEquals(strip.getEffectiveTenor(), new Tenor(Period.ofMonths(11)));
    strip = new RateFutureStrip(FutureType.IMM, 8, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, Tenor.FIVE_MONTHS, "a");
    assertEquals(strip.getEffectiveTenor(), new Tenor(Period.ofMonths(5).plusYears(2)));
    strip = new RateFutureStrip(FutureType.IMM, 1, Tenor.THREE_MONTHS, Tenor.ONE_MONTH, Tenor.FIVE_MONTHS, "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.EIGHT_MONTHS);
    strip = new RateFutureStrip(FutureType.IMM, 2, Tenor.THREE_MONTHS, Tenor.ONE_MONTH, Tenor.FIVE_MONTHS, "a");
    assertEquals(strip.getEffectiveTenor(), new Tenor(Period.ofMonths(11)));
    strip = new RateFutureStrip(FutureType.IMM, 8, Tenor.THREE_MONTHS, Tenor.ONE_MONTH, Tenor.FIVE_MONTHS, "a");
    assertEquals(strip.getEffectiveTenor(), new Tenor(Period.ofMonths(5).plusYears(2)));
    strip = new RateFutureStrip(FutureType.IMM, 1, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, Tenor.ONE_YEAR, "a");
    assertEquals(strip.getEffectiveTenor(), new Tenor(Period.ofMonths(3).plusYears(1)));
    strip = new RateFutureStrip(FutureType.IMM, 2, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, Tenor.ONE_YEAR, "a");
    assertEquals(strip.getEffectiveTenor(), new Tenor(Period.ofMonths(6).plusYears(1)));
    strip = new RateFutureStrip(FutureType.IMM, 8, Tenor.THREE_MONTHS, Tenor.THREE_MONTHS, Tenor.ONE_YEAR, "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.ofYears(3));
    strip = new RateFutureStrip(FutureType.IMM, 1, Tenor.ONE_MONTH, Tenor.ONE_MONTH, Tenor.ONE_YEAR, "a");
    assertEquals(strip.getEffectiveTenor(), new Tenor(Period.ofMonths(1).plusYears(1)));
    strip = new RateFutureStrip(FutureType.IMM, 2, Tenor.ONE_MONTH, Tenor.ONE_MONTH, Tenor.ONE_YEAR, "a");
    assertEquals(strip.getEffectiveTenor(), new Tenor(Period.ofMonths(2).plusYears(1)));
    strip = new RateFutureStrip(FutureType.IMM, 12, Tenor.ONE_MONTH, Tenor.ONE_MONTH, Tenor.ONE_YEAR, "a");
    assertEquals(strip.getEffectiveTenor(), Tenor.TWO_YEARS);
  }

  @Test
  public void testCycle() {
    final RateFutureStrip strip = new RateFutureStrip(FutureType.IMM, 3, Tenor.ONE_MONTH, Tenor.THREE_MONTHS, Tenor.FIVE_YEARS, "a");
    assertEquals(strip, cycleObject(RateFutureStrip.class, strip));
  }
}
