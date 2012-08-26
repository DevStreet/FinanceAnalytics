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
public class OISStripTest extends FinancialTestBase {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullCurveNodeTime() {
    new OISStrip(null, "a");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullConfigurationName() {
    new OISStrip(Tenor.ONE_YEAR, null);
  }

  @Test
  public void testObject() {
    final OISStrip strip = new OISStrip(Tenor.TEN_YEARS, "a");
    assertEquals(strip.getConfigurationName(), "a");
    assertEquals(strip.getCurveNodePointTime(), Tenor.TEN_YEARS);
    assertEquals(strip.getEffectiveTenor(), Tenor.TEN_YEARS);
    assertEquals(strip.getInstrumentType(), NewStripInstrumentType.OIS);
    OISStrip other = new OISStrip(Tenor.TEN_YEARS, "a");
    assertEquals(strip, other);
    assertEquals(strip.hashCode(), other.hashCode());
    other = new OISStrip(Tenor.FIVE_YEARS, "a");
    assertFalse(strip.equals(other));
    other = new OISStrip(Tenor.TEN_YEARS, "b");
    assertFalse(strip.equals(other));
  }

  @Test
  public void testCompare() {
    final OISStrip strip = new OISStrip(Tenor.TEN_YEARS, "a");
    OISStrip other = new OISStrip(Tenor.TEN_YEARS, "a");
    assertEquals(0, strip.compareTo(other));
    other = new OISStrip(Tenor.TWO_YEARS, "a");
    assertTrue(strip.compareTo(other) > 0);
    other = new OISStrip(Tenor.ofYears(20), "a");
    assertTrue(strip.compareTo(other) < 0);
  }

  @Test
  public void testCycle() {
    final OISStrip strip = new OISStrip(Tenor.TEN_YEARS, "a");
    assertEquals(strip, cycleObject(OISStrip.class, strip));
  }
}
