/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.financial.fudgemsg.FinancialTestBase;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class RateStripTest extends FinancialTestBase {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullRateType() {
    new RateStrip(null, Tenor.ONE_YEAR, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullCurveNodeTime() {
    new RateStrip(RateType.Deposit, null, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullConfigurationName() {
    new RateStrip(RateType.Deposit, Tenor.ONE_YEAR, null);
  }

  @Test
  public void testObject() {
    final RateStrip strip = new RateStrip(RateType.Deposit, Tenor.TEN_YEARS, "a");
    assertEquals(strip.getConfigurationName(), "a");
    assertEquals(strip.getCurveNodePointTime(), Tenor.TEN_YEARS);
    assertEquals(strip.getEffectiveTenor(), Tenor.TEN_YEARS);
    assertEquals(strip.getInstrumentType(), NewStripInstrumentType.RATE);
    assertEquals(strip.getRateType(), RateType.Deposit);
    RateStrip other = new RateStrip(RateType.Deposit, Tenor.TEN_YEARS, "a");
    assertEquals(strip, other);
    assertEquals(strip.hashCode(), other.hashCode());
    other = new RateStrip(RateType.Libor, Tenor.TEN_YEARS, "a");
    assertFalse(strip.equals(other));
    other = new RateStrip(RateType.Deposit, Tenor.FIVE_YEARS, "a");
    assertFalse(strip.equals(other));
    other = new RateStrip(RateType.Deposit, Tenor.TEN_YEARS, "b");
    assertFalse(strip.equals(other));
  }

  @Test
  public void testCompare() {
    final RateStrip strip = new RateStrip(RateType.Deposit, Tenor.TEN_YEARS, "a");
    RateStrip other = new RateStrip(RateType.Deposit, Tenor.TEN_YEARS, "a");
    assertEquals(0, strip.compareTo(other));
    other = new RateStrip(RateType.CDOR, Tenor.TEN_YEARS, "a");
    assertTrue(strip.compareTo(other) > 0);
    other = new RateStrip(RateType.Libor, Tenor.TEN_YEARS, "a");
    assertTrue(strip.compareTo(other) < 0);
    other = new RateStrip(RateType.Deposit, Tenor.TWO_YEARS, "a");
    assertTrue(strip.compareTo(other) > 0);
    other = new RateStrip(RateType.Deposit, Tenor.ofYears(20), "a");
    assertTrue(strip.compareTo(other) < 0);
  }

  @Test
  public void testCycle() {
    final RateStrip strip = new RateStrip(RateType.BBSW, Tenor.TEN_YEARS, "a");
    assertEquals(strip, cycleObject(RateStrip.class, strip));
  }
}
