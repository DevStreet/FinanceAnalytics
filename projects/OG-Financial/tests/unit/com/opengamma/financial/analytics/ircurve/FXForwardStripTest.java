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
public class FXForwardStripTest extends FinancialTestBase {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullPayCurrency() {
    new FXForwardStrip(null, Currency.AUD, Tenor.THREE_MONTHS, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullReceiveCurrency() {
    new FXForwardStrip(Currency.USD, null, Tenor.THREE_MONTHS, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullCurveNodeTime() {
    new FXForwardStrip(Currency.USD, Currency.AUD, null, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullConfigurationName() {
    new FXForwardStrip(Currency.USD, Currency.AUD, Tenor.ONE_YEAR, null);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSameCurrencies() {
    new FXForwardStrip(Currency.USD, Currency.USD, Tenor.ONE_YEAR, "a");
  }

  @Test
  public void testObject() {
    final FXForwardStrip strip = new FXForwardStrip(Currency.USD, Currency.AUD, Tenor.THREE_MONTHS, "a");
    assertEquals(strip.getConfigurationName(), "a");
    assertEquals(strip.getCurveNodePointTime(), Tenor.THREE_MONTHS);
    assertEquals(strip.getEffectiveTenor(), Tenor.THREE_MONTHS);
    assertEquals(strip.getInstrumentType(), NewStripInstrumentType.FX_FORWARD);
    assertEquals(strip.getPayCurrency(), Currency.USD);
    assertEquals(strip.getReceiveCurrency(), Currency.AUD);
    FXForwardStrip other = new FXForwardStrip(Currency.USD, Currency.AUD, Tenor.THREE_MONTHS, "a");
    assertEquals(strip, other);
    assertEquals(strip.hashCode(), other.hashCode());
    other = new FXForwardStrip(Currency.CAD, Currency.AUD, Tenor.THREE_MONTHS, "a");
    assertFalse(strip.equals(other));
    other = new FXForwardStrip(Currency.USD, Currency.CAD, Tenor.THREE_MONTHS, "a");
    assertFalse(strip.equals(other));
    other = new FXForwardStrip(Currency.USD, Currency.AUD, Tenor.SIX_MONTHS, "a");
    assertFalse(strip.equals(other));
    other = new FXForwardStrip(Currency.USD, Currency.AUD, Tenor.THREE_MONTHS, "b");
    assertFalse(strip.equals(other));
  }

  @Test
  public void testCompare() {
    final FXForwardStrip strip = new FXForwardStrip(Currency.USD, Currency.AUD, Tenor.THREE_MONTHS, "a");
    FXForwardStrip other = new FXForwardStrip(Currency.USD, Currency.AUD, Tenor.THREE_MONTHS, "a");
    assertEquals(0, strip.compareTo(other));
    other = new FXForwardStrip(Currency.USD, Currency.AUD, Tenor.THREE_MONTHS, "b");
    assertEquals(0, strip.compareTo(other));
    other = new FXForwardStrip(Currency.CAD, Currency.AUD, Tenor.THREE_MONTHS, "a");
    assertFalse(strip.compareTo(other) < 0);
    other = new FXForwardStrip(Currency.of("ZAR"), Currency.AUD, Tenor.THREE_MONTHS, "a");
    assertFalse(strip.compareTo(other) > 0);
    other = new FXForwardStrip(Currency.USD, Currency.of("AED"), Tenor.THREE_MONTHS, "a");
    assertFalse(strip.compareTo(other) < 0);
    other = new FXForwardStrip(Currency.USD, Currency.CAD, Tenor.THREE_MONTHS, "a");
    assertFalse(strip.compareTo(other) > 0);
    other = new FXForwardStrip(Currency.USD, Currency.AUD, Tenor.ONE_MONTH, "a");
    assertFalse(strip.compareTo(other) < 0);
    other = new FXForwardStrip(Currency.USD, Currency.AUD, Tenor.SIX_MONTHS, "a");
    assertFalse(strip.compareTo(other) > 0);
  }

  @Test
  public void testCycle() {
    final FXForwardStrip strip = new FXForwardStrip(Currency.AUD, Currency.USD, Tenor.ONE_YEAR, "a");
    assertEquals(strip, cycleObject(FXForwardStrip.class, strip));
  }
}
