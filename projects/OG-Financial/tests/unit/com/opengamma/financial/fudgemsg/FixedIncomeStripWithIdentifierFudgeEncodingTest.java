/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.fudgemsg;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import com.opengamma.core.id.ExternalSchemes;
import com.opengamma.financial.analytics.ircurve.FixedIncomeStrip;
import com.opengamma.financial.analytics.ircurve.FixedIncomeStripWithIdentifier;
import com.opengamma.financial.analytics.ircurve.IndexType;
import com.opengamma.financial.analytics.ircurve.StripInstrumentTypeDeprecated;
import com.opengamma.id.ExternalId;
import com.opengamma.util.time.Tenor;

public class FixedIncomeStripWithIdentifierFudgeEncodingTest extends FinancialTestBase {

  @Test
  public void testCycle() {
    FixedIncomeStripWithIdentifier strip = new FixedIncomeStripWithIdentifier(new FixedIncomeStrip(StripInstrumentTypeDeprecated.CASH, Tenor.DAY, "DEFAULT"), ExternalId.of(ExternalSchemes.BLOOMBERG_TICKER,
        "USDR5 Curncy"));
    assertEquals(strip, cycleObject(FixedIncomeStripWithIdentifier.class, strip));
    strip = new FixedIncomeStripWithIdentifier(new FixedIncomeStrip(StripInstrumentTypeDeprecated.LIBOR, Tenor.ONE_MONTH, "DEFAULT"), ExternalId.of(ExternalSchemes.BLOOMBERG_TICKER, "US0001M Curncy"));
    assertEquals(strip, cycleObject(FixedIncomeStripWithIdentifier.class, strip));
    strip = new FixedIncomeStripWithIdentifier(new FixedIncomeStrip(StripInstrumentTypeDeprecated.EURIBOR, Tenor.ONE_MONTH, "DEFAULT"), ExternalId.of(ExternalSchemes.BLOOMBERG_TICKER, "US0001M Curncy"));
    assertEquals(strip, cycleObject(FixedIncomeStripWithIdentifier.class, strip));
    strip = new FixedIncomeStripWithIdentifier(new FixedIncomeStrip(StripInstrumentTypeDeprecated.FUTURE, Tenor.YEAR, 3, "DEFAULT"), ExternalId.of(ExternalSchemes.BLOOMBERG_TICKER, "L Z3 Comdty"));
    assertEquals(strip, cycleObject(FixedIncomeStripWithIdentifier.class, strip));
    strip = new FixedIncomeStripWithIdentifier(new FixedIncomeStrip(StripInstrumentTypeDeprecated.FRA_3M, Tenor.YEAR, "DEFAULT"), ExternalId.of(ExternalSchemes.BLOOMBERG_TICKER, "USFR01C Curncy"));
    assertEquals(strip, cycleObject(FixedIncomeStripWithIdentifier.class, strip));
    strip = new FixedIncomeStripWithIdentifier(new FixedIncomeStrip(StripInstrumentTypeDeprecated.FRA_6M, Tenor.YEAR, "DEFAULT"), ExternalId.of(ExternalSchemes.BLOOMBERG_TICKER, "USFR01C Curncy"));
    assertEquals(strip, cycleObject(FixedIncomeStripWithIdentifier.class, strip));
    strip = new FixedIncomeStripWithIdentifier(new FixedIncomeStrip(StripInstrumentTypeDeprecated.SWAP_3M, Tenor.YEAR, "DEFAULT"), ExternalId.of(ExternalSchemes.BLOOMBERG_TICKER, "USSW1 Curncy"));
    assertEquals(strip, cycleObject(FixedIncomeStripWithIdentifier.class, strip));
    strip = new FixedIncomeStripWithIdentifier(new FixedIncomeStrip(StripInstrumentTypeDeprecated.SWAP_3M, Tenor.YEAR, "DEFAULT"), ExternalId.of(ExternalSchemes.BLOOMBERG_TICKER, "USSW1 Curncy"));
    assertEquals(strip, cycleObject(FixedIncomeStripWithIdentifier.class, strip));
    strip = new FixedIncomeStripWithIdentifier(new FixedIncomeStrip(StripInstrumentTypeDeprecated.TENOR_SWAP, Tenor.YEAR, "DEFAULT"), ExternalId.of(ExternalSchemes.BLOOMBERG_TICKER, "USBG1 Curncy"));
    assertEquals(strip, cycleObject(FixedIncomeStripWithIdentifier.class, strip));
    strip = new FixedIncomeStripWithIdentifier(new FixedIncomeStrip(StripInstrumentTypeDeprecated.BASIS_SWAP, Tenor.YEAR, Tenor.SIX_MONTHS, Tenor.ONE_YEAR, IndexType.Libor, IndexType.Libor, "DEFAULT"),
        ExternalId.of(ExternalSchemes.BLOOMBERG_TICKER, "USBG1 Curncy"));
    assertEquals(strip, cycleObject(FixedIncomeStripWithIdentifier.class, strip));
    strip = new FixedIncomeStripWithIdentifier(new FixedIncomeStrip(StripInstrumentTypeDeprecated.OIS_SWAP, Tenor.YEAR, "DEFAULT"), ExternalId.of(ExternalSchemes.BLOOMBERG_TICKER, "USSO1 Curncy"));
    assertEquals(strip, cycleObject(FixedIncomeStripWithIdentifier.class, strip));
  }

}
