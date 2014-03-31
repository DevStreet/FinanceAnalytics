/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.index;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import com.opengamma.analytics.financial.interestrate.CompoundingType;
import com.opengamma.financial.convention.StubType;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.calendar.MondayToFridayCalendar;
import com.opengamma.util.money.Currency;
import com.opengamma.util.test.TestGroup;
import com.opengamma.util.time.Tenor;

/**
 * Test.
 */
@Test(groups = TestGroup.UNIT)
public class GeneratorSwapIborCompoundedIborTest {

  private static final Calendar NYC = new MondayToFridayCalendar("NYC");
  private static final IndexIborMaster IBOR_MASTER = IndexIborMaster.getInstance();
  private static final IborIndex USDLIBOR3M = IBOR_MASTER.getIndex("USDLIBOR3M");
  private static final IborIndex USDLIBOR6M = IBOR_MASTER.getIndex("USDLIBOR6M");
  private static final boolean IS_EOM = true;
  private static final Currency USD = Currency.USD;
  private static final StubType STUB = StubType.SHORT_START;
  private static final StubType STUB_CMP = StubType.SHORT_START;
  private static final int SPOT_LAG = 2;
  private static final int PAY_LAG = 0;
  private static final  String NAME_LEG_USDLIBOR3M = "USDLIBOR3MLeg";
  private static final GeneratorLegIborCompounding USDLIBOR3MCmp6M_LEG = new GeneratorLegIborCompounding(NAME_LEG_USDLIBOR3M, USDLIBOR3M, NYC, NYC, 
      Tenor.of(USDLIBOR6M.getTenor()), CompoundingType.FLAT_COMPOUNDING, Tenor.of(USDLIBOR3M.getTenor()), STUB_CMP, USDLIBOR3M.getDayCount(), 
      USDLIBOR3M.getBusinessDayConvention(), USD, IS_EOM, SPOT_LAG, NYC, STUB, false, PAY_LAG);
  private static final  String NAME_LEG_USDLIBOR6M = "USDLIBOR6MLeg";
  private static final GeneratorLegIbor USDLIBOR6M_LEG = new GeneratorLegIbor(NAME_LEG_USDLIBOR6M, USDLIBOR6M, NYC, NYC, Tenor.of(USDLIBOR6M.getTenor()), 
      USDLIBOR6M.getDayCount(), USDLIBOR6M.getBusinessDayConvention(), USD, IS_EOM, SPOT_LAG, NYC, STUB, false, PAY_LAG);
  
  private static final  String NAME_LEG_SWAP_GEN = "USDLIBOR3MCmp6MLIBOR6M";
  private static final GeneratorSwapIborCompoundingIbor USDLIBOR3MCmp6MLIBOR6M = new GeneratorSwapIborCompoundingIbor(NAME_LEG_SWAP_GEN, USDLIBOR3MCmp6M_LEG, USDLIBOR6M_LEG);

  @Test
  /**
   * Tests the getter for the swap generator.
   */
  public void getter() {
    assertEquals("GeneratorSwapIborCompoundedIbor: getter", NAME_LEG_SWAP_GEN, USDLIBOR3MCmp6MLIBOR6M.getName());
    assertEquals("GeneratorSwapIborCompoundedIbor: getter", USDLIBOR3MCmp6M_LEG, USDLIBOR3MCmp6MLIBOR6M.getIborCompoundingLegGenerator());
    assertEquals("GeneratorSwapIborCompoundedIbor: getter", USDLIBOR6M_LEG, USDLIBOR3MCmp6MLIBOR6M.getIborLegGenerator());
  }

}
