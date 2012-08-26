/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.fudgemsg;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import com.opengamma.financial.analytics.ircurve.FixedIncomeStrip;
import com.opengamma.financial.analytics.ircurve.IndexType;
import com.opengamma.financial.analytics.ircurve.StripInstrumentTypeDeprecated;
import com.opengamma.util.time.Tenor;

public class FixedIncomeStripFudgeEncodingTest extends FinancialTestBase {

  @Test
  public void testCycle() {
    FixedIncomeStrip strip = new FixedIncomeStrip(StripInstrumentTypeDeprecated.CASH, Tenor.DAY, "DEFAULT");
    assertEquals(strip, cycleObject(FixedIncomeStrip.class, strip));
    strip = new FixedIncomeStrip(StripInstrumentTypeDeprecated.FUTURE, Tenor.YEAR, 3, "DEFAULT");
    assertEquals(strip, cycleObject(FixedIncomeStrip.class, strip));
    strip = new FixedIncomeStrip(StripInstrumentTypeDeprecated.LIBOR, Tenor.DAY, "DEFAULT");
    assertEquals(strip, cycleObject(FixedIncomeStrip.class, strip));
    strip = new FixedIncomeStrip(StripInstrumentTypeDeprecated.EURIBOR, Tenor.DAY, "DEFAULT");
    assertEquals(strip, cycleObject(FixedIncomeStrip.class, strip));
    strip = new FixedIncomeStrip(StripInstrumentTypeDeprecated.FRA_3M, Tenor.SIX_MONTHS, "DEFAULT");
    assertEquals(strip, cycleObject(FixedIncomeStrip.class, strip));
    strip = new FixedIncomeStrip(StripInstrumentTypeDeprecated.FRA_6M, Tenor.NINE_MONTHS, "DEFAULT");
    assertEquals(strip, cycleObject(FixedIncomeStrip.class, strip));
    strip = new FixedIncomeStrip(StripInstrumentTypeDeprecated.FUTURE, Tenor.YEAR, 3, "DEFAULT");
    assertEquals(strip, cycleObject(FixedIncomeStrip.class, strip));
    strip = new FixedIncomeStrip(StripInstrumentTypeDeprecated.SWAP_3M, Tenor.THREE_YEARS, "DEFAULT");
    assertEquals(strip, cycleObject(FixedIncomeStrip.class, strip));
    strip = new FixedIncomeStrip(StripInstrumentTypeDeprecated.SWAP_6M, Tenor.THREE_YEARS, "DEFAULT");
    assertEquals(strip, cycleObject(FixedIncomeStrip.class, strip));
    strip = new FixedIncomeStrip(StripInstrumentTypeDeprecated.SWAP_12M, Tenor.THREE_YEARS, "DEFAULT");
    assertEquals(strip, cycleObject(FixedIncomeStrip.class, strip));
    strip = new FixedIncomeStrip(StripInstrumentTypeDeprecated.TENOR_SWAP, Tenor.THREE_YEARS, "DEFAULT");
    assertEquals(strip, cycleObject(FixedIncomeStrip.class, strip));
    strip = new FixedIncomeStrip(StripInstrumentTypeDeprecated.BASIS_SWAP, Tenor.THREE_YEARS, Tenor.THREE_MONTHS, Tenor.SIX_MONTHS, IndexType.Libor, IndexType.Libor, "DEFAULT");
    assertEquals(strip, cycleObject(FixedIncomeStrip.class, strip));
    strip = new FixedIncomeStrip(StripInstrumentTypeDeprecated.OIS_SWAP, Tenor.THREE_YEARS, "DEFAULT");
    assertEquals(strip, cycleObject(FixedIncomeStrip.class, strip));
  }

}
