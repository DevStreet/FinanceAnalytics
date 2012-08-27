/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

import org.testng.annotations.Test;

import com.opengamma.financial.fudgemsg.FinancialTestBase;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class FXSwapStripTest extends FinancialTestBase {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullPayCurrency() {
    new FXSwapStrip(null, Currency.AUD, Tenor.THREE_MONTHS, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullReceiveCurrency() {
    new FXSwapStrip(Currency.USD, null, Tenor.THREE_MONTHS, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullCurveNodeTime() {
    new FXSwapStrip(Currency.USD, Currency.AUD, null, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullConfigurationName() {
    new FXSwapStrip(Currency.USD, Currency.AUD, Tenor.ONE_YEAR, null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSameCurrencies() {
    new FXSwapStrip(Currency.USD, Currency.USD, Tenor.ONE_YEAR, "a");
  }

  @Test
  public void testObject() {
    final FXSwapStrip strip = new FXSwapStrip(Currency.USD, Currency.AUD, Tenor.THREE_MONTHS, "a");
    assertEquals(strip.getConfigurationName(), "a");
    assertEquals(strip.getCurveNodePointTime(), Tenor.THREE_MONTHS);
    assertEquals(strip.getEffectiveTenor(), Tenor.THREE_MONTHS);
    assertEquals(strip.getInstrumentType(), NewStripInstrumentType.FX_SWAP);
    assertEquals(strip.getPayCurrency(), Currency.USD);
    assertEquals(strip.getReceiveCurrency(), Currency.AUD);
    FXSwapStrip other = new FXSwapStrip(Currency.USD, Currency.AUD, Tenor.THREE_MONTHS, "a");
    assertEquals(strip, other);
    assertEquals(strip.hashCode(), other.hashCode());
    other = new FXSwapStrip(Currency.CAD, Currency.AUD, Tenor.THREE_MONTHS, "a");
    assertFalse(strip.equals(other));
    other = new FXSwapStrip(Currency.USD, Currency.CAD, Tenor.THREE_MONTHS, "a");
    assertFalse(strip.equals(other));
    other = new FXSwapStrip(Currency.USD, Currency.AUD, Tenor.SIX_MONTHS, "a");
    assertFalse(strip.equals(other));
    other = new FXSwapStrip(Currency.USD, Currency.AUD, Tenor.THREE_MONTHS, "b");
    assertFalse(strip.equals(other));
  }

  @Test
  public void testCompare() {
    final FXSwapStrip strip = new FXSwapStrip(Currency.USD, Currency.AUD, Tenor.THREE_MONTHS, "a");
    FXSwapStrip other = new FXSwapStrip(Currency.USD, Currency.AUD, Tenor.THREE_MONTHS, "a");
    assertEquals(0, strip.compareTo(other));
    other = new FXSwapStrip(Currency.USD, Currency.AUD, Tenor.THREE_MONTHS, "b");
    assertEquals(0, strip.compareTo(other));
    other = new FXSwapStrip(Currency.CAD, Currency.AUD, Tenor.THREE_MONTHS, "a");
    assertFalse(strip.compareTo(other) < 0);
    other = new FXSwapStrip(Currency.of("ZAR"), Currency.AUD, Tenor.THREE_MONTHS, "a");
    assertFalse(strip.compareTo(other) > 0);
    other = new FXSwapStrip(Currency.USD, Currency.of("AED"), Tenor.THREE_MONTHS, "a");
    assertFalse(strip.compareTo(other) < 0);
    other = new FXSwapStrip(Currency.USD, Currency.CAD, Tenor.THREE_MONTHS, "a");
    assertFalse(strip.compareTo(other) > 0);
    other = new FXSwapStrip(Currency.USD, Currency.AUD, Tenor.ONE_MONTH, "a");
    assertFalse(strip.compareTo(other) < 0);
    other = new FXSwapStrip(Currency.USD, Currency.AUD, Tenor.SIX_MONTHS, "a");
    assertFalse(strip.compareTo(other) > 0);
  }

  @Test
  public void testCycle() {
    final FXSwapStrip strip = new FXSwapStrip(Currency.AUD, Currency.USD, Tenor.ONE_YEAR, "a");
    assertEquals(strip, cycleObject(FXSwapStrip.class, strip));
  }
}
