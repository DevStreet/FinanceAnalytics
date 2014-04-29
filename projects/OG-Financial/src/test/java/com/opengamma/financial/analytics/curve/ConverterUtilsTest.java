/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.curve;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import com.opengamma.analytics.financial.instrument.index.GeneratorSwapFixedIbor;
import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.core.id.ExternalSchemes;
import com.opengamma.financial.analytics.datasets.IndexIborMaster;
import com.opengamma.financial.convention.StubType;
import com.opengamma.financial.convention.SwapFixedLegConvention;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.businessday.BusinessDayConventions;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.calendar.MondayToFridayCalendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCounts;
import com.opengamma.id.ExternalId;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Tenor;

/**
 * Test related to the ConverterUtils: converting convention objects to OG-Analytics relevant objects.
 */
public class ConverterUtilsTest {

  /** Conventions */
  private static final DayCount THIRTYU360 = DayCounts.THIRTY_U_360;
  private static final BusinessDayConvention MOD_FOL = BusinessDayConventions.MODIFIED_FOLLOWING;
  private static final Currency USD = Currency.USD;
  private static final ExternalId US_REGION_ID = ExternalSchemes.financialRegionId("US");
  /** GeneratorSwapFixedIbor */
  private static final String GENERATOR_FIXED_IBOR_NAME = "USD6MLIBOR3M";
  private static final ExternalId FIXED_LEG_CONVENTION_ID = ExternalId.of("CONVENTION", GENERATOR_FIXED_IBOR_NAME);
  private static final int SPOT_LAG = 2;
  private static final Tenor SWAP_TENOR = Tenor.FIVE_YEARS;
  private static final SwapFixedLegConvention USDFixed6M = new SwapFixedLegConvention(GENERATOR_FIXED_IBOR_NAME, FIXED_LEG_CONVENTION_ID.toBundle(), 
      SWAP_TENOR , THIRTYU360, MOD_FOL, USD, US_REGION_ID, SPOT_LAG, true, StubType.SHORT_START, false, 0);
  private static final IborIndex USDLIBOR3M = IndexIborMaster.getInstance().getIndex("USDLIBOR3M");
  private static final Calendar NYC = new MondayToFridayCalendar("NYC");
  private static final GeneratorSwapFixedIbor GENERATOR_FIXED_IBOR = ConverterUtils.generatorSwapFixedIbor(GENERATOR_FIXED_IBOR_NAME, USDFixed6M, USDLIBOR3M, NYC);

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void nullFixedLeg() {
    ConverterUtils.generatorSwapFixedIbor(GENERATOR_FIXED_IBOR_NAME, null, USDLIBOR3M, NYC);
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void nullIndexIbor() {
    ConverterUtils.generatorSwapFixedIbor(GENERATOR_FIXED_IBOR_NAME, USDFixed6M, null, NYC);
  }
  
  @Test
  public void getter() {
    assertEquals("ConverterUtils.generatorSwapFixedIbor", GENERATOR_FIXED_IBOR_NAME, GENERATOR_FIXED_IBOR.getName());
    assertEquals("ConverterUtils.generatorSwapFixedIbor", MOD_FOL, GENERATOR_FIXED_IBOR.getBusinessDayConvention());
    assertEquals("ConverterUtils.generatorSwapFixedIbor", NYC, GENERATOR_FIXED_IBOR.getCalendar());
    assertEquals("ConverterUtils.generatorSwapFixedIbor", USD, GENERATOR_FIXED_IBOR.getCurrency());
    assertEquals("ConverterUtils.generatorSwapFixedIbor", THIRTYU360, GENERATOR_FIXED_IBOR.getFixedLegDayCount());
    assertEquals("ConverterUtils.generatorSwapFixedIbor", SWAP_TENOR.getPeriod(), GENERATOR_FIXED_IBOR.getFixedLegPeriod());
    assertEquals("ConverterUtils.generatorSwapFixedIbor", USDLIBOR3M, GENERATOR_FIXED_IBOR.getIborIndex());
    assertEquals("ConverterUtils.generatorSwapFixedIbor", SPOT_LAG, GENERATOR_FIXED_IBOR.getSpotLag());
  }
  
}
