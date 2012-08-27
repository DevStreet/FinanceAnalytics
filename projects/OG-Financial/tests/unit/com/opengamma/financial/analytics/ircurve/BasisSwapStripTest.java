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
public class BasisSwapStripTest extends FinancialTestBase {
  private static final Tenor PAY_TENOR = Tenor.THREE_MONTHS;
  private static final RateType PAY_INDEX = RateType.Libor;
  private static final Tenor RECEIVE_TENOR = Tenor.SIX_MONTHS;
  private static final RateType RECEIVE_INDEX = RateType.Euribor;
  private static final Tenor TENOR = Tenor.FIVE_YEARS;
  private static final String NAME = "a";

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullPayTenor() {
    new BasisSwapStrip(null, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, NAME);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullPayIndex() {
    new BasisSwapStrip(PAY_TENOR, null, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, NAME);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullReceiveTenor() {
    new BasisSwapStrip(PAY_TENOR, PAY_INDEX, null, RECEIVE_INDEX, TENOR, NAME);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullReceiveIndex() {
    new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, null, TENOR, NAME);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullTenor() {
    new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, null, NAME);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullName() {
    new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, null);
  }

  @Test
  public void testObject() {
    final BasisSwapStrip strip = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, NAME);
    assertEquals(strip.getConfigurationName(), "a");
    assertEquals(strip.getCurveNodePointTime(), TENOR);
    assertEquals(strip.getEffectiveTenor(), TENOR);
    assertEquals(strip.getInstrumentType(), NewStripInstrumentType.BASIS_SWAP);
    assertEquals(strip.getPayIndexType(), PAY_INDEX);
    assertEquals(strip.getPayResetTenor(), PAY_TENOR);
    assertEquals(strip.getReceiveIndexType(), RECEIVE_INDEX);
    assertEquals(strip.getReceiveResetTenor(), RECEIVE_TENOR);
    BasisSwapStrip other = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, NAME);
    assertEquals(strip, other);
    assertEquals(strip.hashCode(), other.hashCode());
    other = new BasisSwapStrip(RECEIVE_TENOR, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, NAME);
    assertFalse(strip.equals(other));
    other = new BasisSwapStrip(PAY_TENOR, RECEIVE_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, NAME);
    assertFalse(strip.equals(other));
    other = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, PAY_TENOR, RECEIVE_INDEX, TENOR, NAME);
    assertFalse(strip.equals(other));
    other = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, PAY_INDEX, TENOR, NAME);
    assertFalse(strip.equals(other));
    other = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, PAY_TENOR, NAME);
    assertFalse(strip.equals(other));
    other = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, "b");
    assertFalse(strip.equals(other));
  }

  @Test
  public void testCompare() {
    final BasisSwapStrip strip = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, NAME);
    BasisSwapStrip other = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, NAME);
    assertEquals(0, strip.compareTo(other));
    other = new BasisSwapStrip(Tenor.ONE_MONTH, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, NAME);
    assertTrue(strip.compareTo(other) > 0);
    other = new BasisSwapStrip(Tenor.ONE_YEAR, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, NAME);
    assertTrue(strip.compareTo(other) < 0);
    other = new BasisSwapStrip(PAY_TENOR, RateType.CDOR, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, NAME);
    assertTrue(strip.compareTo(other) > 0);
    other = new BasisSwapStrip(PAY_TENOR, RateType.Stibor, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, NAME);
    assertTrue(strip.compareTo(other) < 0);
    other = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, Tenor.THREE_MONTHS, RECEIVE_INDEX, TENOR, NAME);
    assertTrue(strip.compareTo(other) > 0);
    other = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, Tenor.ONE_YEAR, RECEIVE_INDEX, TENOR, NAME);
    assertTrue(strip.compareTo(other) < 0);
    other = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, RateType.BBSW, TENOR, NAME);
    assertTrue(strip.compareTo(other) > 0);
    other = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, RateType.Tibor, TENOR, NAME);
    assertTrue(strip.compareTo(other) < 0);
    other = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, Tenor.TWO_YEARS, NAME);
    assertTrue(strip.compareTo(other) > 0);
    other = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, Tenor.ofYears(50), NAME);
    assertTrue(strip.compareTo(other) < 0);
  }

  @Test
  public void testCycle() {
    final BasisSwapStrip strip = new BasisSwapStrip(PAY_TENOR, PAY_INDEX, RECEIVE_TENOR, RECEIVE_INDEX, TENOR, NAME);
    assertEquals(strip, cycleObject(BasisSwapStrip.class, strip));
  }
}
