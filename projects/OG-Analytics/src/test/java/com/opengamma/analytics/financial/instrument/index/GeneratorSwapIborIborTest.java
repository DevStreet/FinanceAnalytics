/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.index;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

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
public class GeneratorSwapIborIborTest {

  private static final Calendar NYC = new MondayToFridayCalendar("NYC");
  private static final IndexIborMaster IBOR_MASTER = IndexIborMaster.getInstance();
  private static final IborIndex USDLIBOR3M = IBOR_MASTER.getIndex("USDLIBOR3M");
  private static final IborIndex USDLIBOR6M = IBOR_MASTER.getIndex("USDLIBOR6M");
  private static final boolean IS_EOM = true;
  private static final Currency USD = Currency.USD;
  private static final StubType STUB = StubType.SHORT_START;
  private static final int SPOT_LAG = 2;
  private static final int PAY_LAG = 0;
  private static final  String NAME_LEG_USDLIBOR3M = "USDLIBOR3MLeg";
  private static final GeneratorLegIbor USDLIBOR3M_LEG = new GeneratorLegIbor(NAME_LEG_USDLIBOR3M, USDLIBOR3M, NYC, NYC, Tenor.of(USDLIBOR3M.getTenor()), 
      USDLIBOR3M.getDayCount(), USDLIBOR3M.getBusinessDayConvention(), USD, IS_EOM, SPOT_LAG, NYC, STUB, false, PAY_LAG);
  private static final  String NAME_LEG_USDLIBOR6M = "USDLIBOR6MLeg";
  private static final GeneratorLegIbor USDLIBOR6M_LEG = new GeneratorLegIbor(NAME_LEG_USDLIBOR6M, USDLIBOR6M, NYC, NYC, Tenor.of(USDLIBOR6M.getTenor()), 
      USDLIBOR6M.getDayCount(), USDLIBOR6M.getBusinessDayConvention(), USD, IS_EOM, SPOT_LAG, NYC, STUB, false, PAY_LAG);
  
  private static final GeneratorSwapIborIbor USDLIBOR3MLIBOR6M = new GeneratorSwapIborIbor("USDLIBOR3MLIBOR6M", USDLIBOR3M_LEG, USDLIBOR6M_LEG);

  @Test
  /**
   * Tests the getter for the swap generator.
   */
  public void getter() {
    assertEquals("GeneratorSwapIborIbor: getter", USDLIBOR3M_LEG, USDLIBOR3MLIBOR6M.getFirstIborLegGenerator());
    assertEquals("GeneratorSwapIborIbor: getter", USDLIBOR6M_LEG, USDLIBOR3MLIBOR6M.getSecondIborLegGenerator());
  }

}
