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
public class SwapStripTest extends FinancialTestBase {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullTenor() {
    new SwapStrip(null, RateType.BBSW, Tenor.EIGHT_MONTHS, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullRateType() {
    new SwapStrip(Tenor.THREE_MONTHS, null, Tenor.ONE_YEAR, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullCurveNodeTime() {
    new SwapStrip(Tenor.THREE_MONTHS, RateType.BBSW, null, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullConfigurationName() {
    new SwapStrip(Tenor.THREE_MONTHS, RateType.BBSW, Tenor.ONE_YEAR, null);
  }

  @Test
  public void testObject() {
    final SwapStrip strip = new SwapStrip(Tenor.THREE_MONTHS, RateType.Libor, Tenor.TEN_YEARS, "a");
    assertEquals(strip.getConfigurationName(), "a");
    assertEquals(strip.getCurveNodePointTime(), Tenor.TEN_YEARS);
    assertEquals(strip.getEffectiveTenor(), Tenor.TEN_YEARS);
    assertEquals(strip.getFloatingIndexType(), RateType.Libor);
    assertEquals(strip.getInstrumentType(), NewStripInstrumentType.SWAP);
    assertEquals(strip.getResetTenor(), Tenor.THREE_MONTHS);
    SwapStrip other = new SwapStrip(Tenor.THREE_MONTHS, RateType.Libor, Tenor.TEN_YEARS, "a");
    assertEquals(strip, other);
    assertEquals(strip.hashCode(), other.hashCode());
    other = new SwapStrip(Tenor.SIX_MONTHS, RateType.Libor, Tenor.TEN_YEARS, "a");
    assertFalse(strip.equals(other));
    other = new SwapStrip(Tenor.THREE_MONTHS, RateType.Euribor, Tenor.TEN_YEARS, "a");
    assertFalse(strip.equals(other));
    other = new SwapStrip(Tenor.THREE_MONTHS, RateType.Libor, Tenor.FIVE_YEARS, "a");
    assertFalse(strip.equals(other));
    other = new SwapStrip(Tenor.THREE_MONTHS, RateType.Libor, Tenor.TEN_YEARS, "b");
    assertFalse(strip.equals(other));
  }

  @Test
  public void testCompare() {
    final SwapStrip strip = new SwapStrip(Tenor.THREE_MONTHS, RateType.Libor, Tenor.TEN_YEARS, "a");
    SwapStrip other = new SwapStrip(Tenor.THREE_MONTHS, RateType.Libor, Tenor.TEN_YEARS, "a");
    assertEquals(0, strip.compareTo(other));
    other = new SwapStrip(Tenor.THREE_MONTHS, RateType.Libor, Tenor.TEN_YEARS, "b");
    assertEquals(0, strip.compareTo(other));
    other = new SwapStrip(Tenor.SIX_MONTHS, RateType.Libor, Tenor.TEN_YEARS, "a");
    assertTrue(strip.compareTo(other) < 0);
    other = new SwapStrip(Tenor.ONE_MONTH, RateType.Libor, Tenor.TEN_YEARS, "a");
    assertTrue(strip.compareTo(other) > 0);
    other = new SwapStrip(Tenor.THREE_MONTHS, RateType.Euribor, Tenor.TEN_YEARS, "a");
    assertTrue(strip.compareTo(other) > 0);
    other = new SwapStrip(Tenor.THREE_MONTHS, RateType.Stibor, Tenor.TEN_YEARS, "a");
    assertTrue(strip.compareTo(other) < 0);
    other = new SwapStrip(Tenor.THREE_MONTHS, RateType.Libor, Tenor.TWO_YEARS, "a");
    assertTrue(strip.compareTo(other) > 0);
    other = new SwapStrip(Tenor.THREE_MONTHS, RateType.Libor, Tenor.ofYears(20), "a");
    assertTrue(strip.compareTo(other) < 0);
  }

  @Test
  public void testCycle() {
    final SwapStrip strip = new SwapStrip(Tenor.THREE_MONTHS, RateType.Libor, Tenor.TEN_YEARS, "a");
    assertEquals(strip, cycleObject(SwapStrip.class, strip));
  }
}
