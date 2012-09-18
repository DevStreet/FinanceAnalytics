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

import org.testng.annotations.Test;

import com.opengamma.financial.fudgemsg.FinancialTestBase;
import com.opengamma.id.ExternalId;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class CurveStripWithIdentifierTest extends FinancialTestBase {
  private static final String NAME = "NAME";
  private static final RateStrip RATE = new RateStrip(RateType.Bankers_Acceptance, Tenor.ONE_MONTH, NAME);
  private static final ExternalId RATE_ID = ExternalId.of("Test", "BA1M");
  private static final SwapStrip SWAP1 = new SwapStrip(Tenor.THREE_MONTHS, RateType.Libor, Tenor.TEN_YEARS, NAME);
  private static final ExternalId SWAP_ID1 = ExternalId.of("Test", "SW10Y");
  private static final SwapStrip SWAP2 = new SwapStrip(Tenor.THREE_MONTHS, RateType.Libor, Tenor.FIVE_YEARS, NAME);
  private static final ExternalId SWAP_ID2 = ExternalId.of("Test", "SW5Y");
  private static final SwapStrip SWAP3 = new SwapStrip(Tenor.THREE_MONTHS, RateType.Libor, Tenor.FIVE_YEARS, NAME);
  private static final ExternalId SWAP_ID3 = ExternalId.of("Test", "SS5Y");
  private static final CurveStripWithIdentifier STRIP1 = new CurveStripWithIdentifier(RATE, RATE_ID);
  private static final CurveStripWithIdentifier STRIP2 = new CurveStripWithIdentifier(SWAP1, SWAP_ID1);
  private static final CurveStripWithIdentifier STRIP3 = new CurveStripWithIdentifier(SWAP2, SWAP_ID2);
  private static final CurveStripWithIdentifier STRIP4 = new CurveStripWithIdentifier(SWAP3, SWAP_ID3);

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullStrip() {
    new CurveStripWithIdentifier(null, RATE_ID);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNullId() {
    new CurveStripWithIdentifier(RATE, null);
  }

  @Test
  public void testObject() {
    assertEquals(STRIP1.getIdentifier(), RATE_ID);
    assertEquals(STRIP1.getStrip(), RATE);
    CurveStripWithIdentifier other = new CurveStripWithIdentifier(new RateStrip(RateType.Bankers_Acceptance, Tenor.ONE_MONTH, NAME), ExternalId.of("Test", "BA1M"));
    assertEquals(STRIP1, other);
    assertEquals(STRIP1.hashCode(), other.hashCode());
    other = new CurveStripWithIdentifier(SWAP1, RATE_ID);
    assertFalse(STRIP1.equals(other));
    other = new CurveStripWithIdentifier(RATE, SWAP_ID1);
    assertFalse(STRIP1.equals(other));
  }

  @Test
  public void testCompare() {
    final TreeSet<CurveStripWithIdentifier> set = new TreeSet<CurveStripWithIdentifier>();
    set.add(STRIP3);
    set.add(STRIP1);
    set.add(STRIP2);
    set.add(STRIP4);
    final Iterator<CurveStripWithIdentifier> iter = set.iterator();
    assertEquals(STRIP1, iter.next());
    assertEquals(STRIP4, iter.next());
    assertEquals(STRIP3, iter.next());
    assertEquals(STRIP2, iter.next());
  }

  @Test
  public void testCycle() {
    assertEquals(STRIP1, cycleObject(CurveStripWithIdentifier.class, STRIP1));
    assertEquals(STRIP2, cycleObject(CurveStripWithIdentifier.class, STRIP2));
    assertEquals(STRIP3, cycleObject(CurveStripWithIdentifier.class, STRIP3));
    assertEquals(STRIP4, cycleObject(CurveStripWithIdentifier.class, STRIP4));
  }
}
