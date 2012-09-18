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
public class PeriodicZeroDepositStripTest extends FinancialTestBase {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNegativePeriodsType() {
    new PeriodicZeroDepositStrip(-1, Tenor.ONE_YEAR, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullCurveNodeTime() {
    new PeriodicZeroDepositStrip(12, null, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullConfigurationName() {
    new PeriodicZeroDepositStrip(12, Tenor.ONE_YEAR, null);
  }

  @Test
  public void testObject() {
    final PeriodicZeroDepositStrip strip = new PeriodicZeroDepositStrip(6, Tenor.TEN_YEARS, "a");
    assertEquals(strip.getConfigurationName(), "a");
    assertEquals(strip.getCurveNodePointTime(), Tenor.TEN_YEARS);
    assertEquals(strip.getEffectiveTenor(), Tenor.TEN_YEARS);
    assertEquals(strip.getInstrumentType(), NewStripInstrumentType.PERIODIC_ZERO_DEPOSIT);
    assertEquals(strip.getPeriodsPerYear(), 6);
    PeriodicZeroDepositStrip other = new PeriodicZeroDepositStrip(6, Tenor.TEN_YEARS, "a");
    assertEquals(strip, other);
    assertEquals(strip.hashCode(), other.hashCode());
    other = new PeriodicZeroDepositStrip(12, Tenor.TEN_YEARS, "a");
    assertFalse(strip.equals(other));
    other = new PeriodicZeroDepositStrip(6, Tenor.FIVE_YEARS, "a");
    assertFalse(strip.equals(other));
    other = new PeriodicZeroDepositStrip(6, Tenor.TEN_YEARS, "b");
    assertFalse(strip.equals(other));
  }

  @Test
  public void testCompare() {
    final PeriodicZeroDepositStrip strip = new PeriodicZeroDepositStrip(3, Tenor.TEN_YEARS, "a");
    PeriodicZeroDepositStrip other = new PeriodicZeroDepositStrip(3, Tenor.TEN_YEARS, "a");
    assertEquals(0, strip.compareTo(other));
    other = new PeriodicZeroDepositStrip(2, Tenor.TEN_YEARS, "a");
    assertTrue(strip.compareTo(other) > 0);
    other = new PeriodicZeroDepositStrip(6, Tenor.TEN_YEARS, "a");
    assertTrue(strip.compareTo(other) < 0);
    other = new PeriodicZeroDepositStrip(3, Tenor.TWO_YEARS, "a");
    assertTrue(strip.compareTo(other) > 0);
    other = new PeriodicZeroDepositStrip(3, Tenor.ofYears(20), "a");
    assertTrue(strip.compareTo(other) < 0);
  }

  @Test
  public void testCycle() {
    final PeriodicZeroDepositStrip strip = new PeriodicZeroDepositStrip(9, Tenor.TEN_YEARS, "a");
    assertEquals(strip, cycleObject(PeriodicZeroDepositStrip.class, strip));
  }

}
