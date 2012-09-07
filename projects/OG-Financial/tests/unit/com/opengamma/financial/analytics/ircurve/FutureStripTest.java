/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

import org.testng.annotations.Test;

import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class FutureStripTest {

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

  }
}
