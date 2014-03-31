/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.index;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

import org.testng.annotations.Test;
import org.threeten.bp.Period;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.datasets.CalendarTarget;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityCouponFixedDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinition;
import com.opengamma.analytics.financial.instrument.payment.CouponDefinition;
import com.opengamma.analytics.financial.instrument.swap.SwapDefinition;
import com.opengamma.financial.convention.StubType;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.businessday.BusinessDayConventions;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCounts;
import com.opengamma.util.money.Currency;
import com.opengamma.util.test.TestGroup;
import com.opengamma.util.time.DateUtils;
import com.opengamma.util.time.Tenor;

/**
 * Tests related to the construction of Fixed/ON (OIS) generators.
 */
@Test(groups = TestGroup.UNIT)
public class GeneratorSwapFixedONCompoundingTest {

  private static final Period PAYMENT_PERIOD_FIXED = Period.ofMonths(12);
  private static final Tenor PAYMENT_TENOR_FIXED = Tenor.of(PAYMENT_PERIOD_FIXED);
  private static final int SPOT_LAG = 2;
  private static final Calendar TARGET = new CalendarTarget("TARGET");
  private static final DayCount DC30U_360 = DayCounts.THIRTY_U_360;
  private static final BusinessDayConvention MOD_FOL = BusinessDayConventions.MODIFIED_FOLLOWING;
  private static final boolean IS_EOM = true;
  private static final Currency EUR = Currency.EUR;
  private static final StubType STUB = StubType.SHORT_START;
  private static final int PAY_LAG = 2;
  private static final Period PAYMENT_PERIOD_ON = Period.ofMonths(6);
  private static final Tenor PAYMENT_TENOR_ON = Tenor.of(PAYMENT_PERIOD_ON);
  private static final DayCount ACT360 = DayCounts.ACT_360;
  private static final IndexONMaster MASTER_ON = IndexONMaster.getInstance();
  private static final IndexON EONIA = MASTER_ON.getIndex("EONIA");
  
  private static final  String NAME_LEG_FIXED = "EUR1YFixed";
  private static final GeneratorLegFixed GENERATOR_LEG_FIXED = new GeneratorLegFixed(NAME_LEG_FIXED, PAYMENT_TENOR_FIXED, DC30U_360, MOD_FOL, EUR, IS_EOM, 
      SPOT_LAG, TARGET, STUB, false, PAY_LAG);
  private static final  String NAME_LEG_IBOR = "EUREONIA1Y";
  private static final GeneratorLegONCompounding GENERATOR_LEG_EONIA = new GeneratorLegONCompounding(NAME_LEG_IBOR, EONIA, TARGET, PAYMENT_TENOR_ON, ACT360, 
      MOD_FOL, EUR, IS_EOM, SPOT_LAG, TARGET, STUB, IS_EOM, PAY_LAG);
  
  private static final  String NAME = "EUR1YEONIA1Y";
  private static final GeneratorSwapFixedONCompounding EUR1YEONIA1Y = new GeneratorSwapFixedONCompounding(NAME, GENERATOR_LEG_FIXED, GENERATOR_LEG_EONIA);

  
  @Test
  public void getter() {
    assertEquals("GeneratorSwapFixedIbor: getter", NAME, EUR1YEONIA1Y.getName());
    assertEquals("GeneratorSwapFixedIbor: getter", GENERATOR_LEG_FIXED, EUR1YEONIA1Y.getFixedLegGenerator());
    assertEquals("GeneratorSwapFixedIbor: getter", GENERATOR_LEG_EONIA, EUR1YEONIA1Y.getONLegGenerator());
  }

  @Test
  public void equalHash() {
    assertEquals("GeneratorSwapFixedIbor: equal - hash", EUR1YEONIA1Y, EUR1YEONIA1Y);
    final GeneratorSwapFixedONCompounding generatorDuplicate = new GeneratorSwapFixedONCompounding(NAME, GENERATOR_LEG_FIXED, GENERATOR_LEG_EONIA);
    assertEquals("GeneratorSwapFixedIbor: equal - hash", EUR1YEONIA1Y, generatorDuplicate);
    assertEquals("GeneratorSwapFixedIbor: equal - hash", EUR1YEONIA1Y.hashCode(), generatorDuplicate.hashCode());
    GeneratorSwapFixedONCompounding generatorModified;
    generatorModified = new GeneratorSwapFixedONCompounding("Bad name", GENERATOR_LEG_FIXED, GENERATOR_LEG_EONIA);
    assertFalse("GeneratorSwapFixedIbor: equal - hash", EUR1YEONIA1Y.equals(generatorModified));
    final GeneratorLegFixed genFixedOther = new GeneratorLegFixed(NAME_LEG_FIXED, Tenor.TWO_YEARS, DC30U_360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG, TARGET, STUB, false, PAY_LAG);
    generatorModified = new GeneratorSwapFixedONCompounding(NAME, genFixedOther, GENERATOR_LEG_EONIA);
    assertFalse("GeneratorSwapFixedIbor: equal - hash", EUR1YEONIA1Y.equals(generatorModified));
    final GeneratorLegONCompounding genONOther = new GeneratorLegONCompounding(NAME_LEG_IBOR, EONIA, TARGET, PAYMENT_TENOR_ON, DC30U_360, 
        MOD_FOL, EUR, IS_EOM, SPOT_LAG, TARGET, STUB, IS_EOM, PAY_LAG);
    generatorModified = new GeneratorSwapFixedONCompounding(NAME, GENERATOR_LEG_FIXED, genONOther);
    assertFalse("GeneratorSwapFixedIbor: equal - hash", EUR1YEONIA1Y.equals(generatorModified));
    assertFalse("GeneratorSwapFixedIbor: equal - hash", EUR1YEONIA1Y.equals(null));
    assertFalse("GeneratorSwapFixedIbor: equal - hash", EUR1YEONIA1Y.equals(EUR));
  }
  
  @Test
  public void generateSwapNoNotional() {
    final GeneratorAttributeIROTC attribute = new GeneratorAttributeIROTC(Tenor.SIX_MONTHS, Tenor.TWO_YEARS);
    final ZonedDateTime referenceDate = DateUtils.getUTCDate(2014, 3, 24);
    final double rate = 0.02;
    final double notional = 100000000;
    final SwapDefinition swapGenerated = EUR1YEONIA1Y.generateInstrument(referenceDate, rate, notional, attribute);
    final AnnuityCouponFixedDefinition legFixedGenerated = GENERATOR_LEG_FIXED.generateInstrument(referenceDate, rate, notional, attribute, true);
    final AnnuityDefinition<? extends CouponDefinition> legIborGenerated = GENERATOR_LEG_EONIA.generateInstrument(referenceDate, notional, attribute, false);
    final SwapDefinition swapExpected = new SwapDefinition(legFixedGenerated, legIborGenerated);
    assertEquals("GeneratorSwapFixedIbor: generateInstrument", swapExpected, swapGenerated);
  }

}
