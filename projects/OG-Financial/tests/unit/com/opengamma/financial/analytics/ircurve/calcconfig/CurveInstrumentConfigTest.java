/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve.calcconfig;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.opengamma.financial.analytics.ircurve.StripInstrumentTypeDeprecated;
import com.opengamma.financial.fudgemsg.FinancialTestBase;

/**
 * 
 */
public class CurveInstrumentConfigTest extends FinancialTestBase {
  private static final String FUNDING_CURVE_NAME = "Funding";
  private static final String FORWARD_3M_CURVE_NAME = "Forward3M";
  private static final CurveInstrumentConfig FUNDING_CONFIG;
  private static final CurveInstrumentConfig FORWARD_CONFIG;

  static {
    final String[] fundingOnly = new String[] {FUNDING_CURVE_NAME};
    final String[] forward3MOnly = new String[] {FORWARD_3M_CURVE_NAME};
    final String[] fundingForward3M = new String[] {FUNDING_CURVE_NAME, FORWARD_3M_CURVE_NAME};
    final Map<StripInstrumentTypeDeprecated, String[]> fundingConfig = new HashMap<StripInstrumentTypeDeprecated, String[]>();
    fundingConfig.put(StripInstrumentTypeDeprecated.CASH, fundingOnly);
    fundingConfig.put(StripInstrumentTypeDeprecated.OIS_SWAP, new String[] {FUNDING_CURVE_NAME, FUNDING_CURVE_NAME});
    final Map<StripInstrumentTypeDeprecated, String[]> forward3MConfig = new HashMap<StripInstrumentTypeDeprecated, String[]>();
    forward3MConfig.put(StripInstrumentTypeDeprecated.LIBOR, forward3MOnly);
    forward3MConfig.put(StripInstrumentTypeDeprecated.FUTURE, fundingForward3M);
    forward3MConfig.put(StripInstrumentTypeDeprecated.FRA_3M, fundingForward3M);
    forward3MConfig.put(StripInstrumentTypeDeprecated.SWAP_6M, fundingForward3M);
    FUNDING_CONFIG = new CurveInstrumentConfig(fundingConfig);
    FORWARD_CONFIG = new CurveInstrumentConfig(forward3MConfig);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testWrongStrip() {
    FUNDING_CONFIG.getExposuresForInstrument(StripInstrumentTypeDeprecated.BANKERS_ACCEPTANCE);
  }

  @Test
  public void test() {
    final Map<StripInstrumentTypeDeprecated, String[]> fundingConfig = new HashMap<StripInstrumentTypeDeprecated, String[]>();
    fundingConfig.put(StripInstrumentTypeDeprecated.CASH, new String[] {FUNDING_CURVE_NAME} );
    fundingConfig.put(StripInstrumentTypeDeprecated.OIS_SWAP, new String[] {FUNDING_CURVE_NAME, FUNDING_CURVE_NAME});
    final CurveInstrumentConfig config = new CurveInstrumentConfig(fundingConfig);
    assertFalse(FUNDING_CONFIG.equals(FORWARD_CONFIG));
    assertEquals(FUNDING_CONFIG, config);
  }

  @Test
  public void testBuilder() {
    assertEquals(FUNDING_CONFIG, cycleObject(CurveInstrumentConfig.class, FUNDING_CONFIG));
    assertEquals(FORWARD_CONFIG, cycleObject(CurveInstrumentConfig.class, FORWARD_CONFIG));
  }
}
