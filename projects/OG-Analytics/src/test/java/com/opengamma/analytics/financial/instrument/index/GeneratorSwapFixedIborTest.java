/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
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
import com.opengamma.analytics.financial.instrument.swap.SwapFixedIborDefinition;
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
 * Tests related to the generators of fixed/ibor swaps.
 */
@Test(groups = TestGroup.UNIT)
public class GeneratorSwapFixedIborTest {

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
  private static final Period PAYMENT_PERIOD_IBOR = Period.ofMonths(3);
  private static final Tenor PAYMENT_TENOR_IBOR = Tenor.of(PAYMENT_PERIOD_IBOR);
  private static final DayCount ACT360 = DayCounts.ACT_360;
  private static final IndexIborMaster MASTER_IBOR = IndexIborMaster.getInstance();
  private static final IborIndex EURIBOR3M = MASTER_IBOR.getIndex("EURIBOR3M");
  
  private static final  String NAME_LEG_FIXED = "EUR1YFixed";
  private static final GeneratorLegFixed GENERATOR_LEG_FIXED = new GeneratorLegFixed(NAME_LEG_FIXED, PAYMENT_TENOR_FIXED, DC30U_360, MOD_FOL, EUR, IS_EOM, 
      SPOT_LAG, TARGET, STUB, false, PAY_LAG);
  private static final  String NAME_LEG_IBOR = "EUREURIBOR3M";
  private static final GeneratorLegIbor GENERATOR_LEG_EURIBOR3M = new GeneratorLegIbor(NAME_LEG_IBOR, EURIBOR3M, TARGET, TARGET, PAYMENT_TENOR_IBOR, ACT360, 
      MOD_FOL, EUR, IS_EOM, SPOT_LAG, TARGET, STUB, false, PAY_LAG);
  
  private static final  String NAME = "EUR1YEURIBOR3M";
  private static final GeneratorSwapFixedIbor EUR1YEURIBOR3M = new GeneratorSwapFixedIbor(NAME, GENERATOR_LEG_FIXED, GENERATOR_LEG_EURIBOR3M);
  
  @Test
  public void getter() {
    assertEquals("GeneratorSwapFixedIbor: getter", NAME, EUR1YEURIBOR3M.getName());
    assertEquals("GeneratorSwapFixedIbor: getter", GENERATOR_LEG_FIXED, EUR1YEURIBOR3M.getFixedLegGenerator());
    assertEquals("GeneratorSwapFixedIbor: getter", GENERATOR_LEG_EURIBOR3M, EUR1YEURIBOR3M.getIborLegGenerator());
  }

  @Test
  public void equalHash() {
    assertEquals("GeneratorSwapFixedIbor: equal - hash", EUR1YEURIBOR3M, EUR1YEURIBOR3M);
    final GeneratorSwapFixedIbor generatorDuplicate = new GeneratorSwapFixedIbor(NAME, GENERATOR_LEG_FIXED, GENERATOR_LEG_EURIBOR3M);
    assertEquals("GeneratorSwapFixedIbor: equal - hash", EUR1YEURIBOR3M, generatorDuplicate);
    assertEquals("GeneratorSwapFixedIbor: equal - hash", EUR1YEURIBOR3M.hashCode(), generatorDuplicate.hashCode());
    GeneratorSwapFixedIbor generatorModified;
    generatorModified = new GeneratorSwapFixedIbor("Bad name", GENERATOR_LEG_FIXED, GENERATOR_LEG_EURIBOR3M);
    assertFalse("GeneratorSwapFixedIbor: equal - hash", EUR1YEURIBOR3M.equals(generatorModified));
    final GeneratorLegFixed genFixedOther = new GeneratorLegFixed(NAME_LEG_FIXED, PAYMENT_TENOR_IBOR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG, TARGET, STUB, false, PAY_LAG);
    generatorModified = new GeneratorSwapFixedIbor(NAME, genFixedOther, GENERATOR_LEG_EURIBOR3M);
    assertFalse("GeneratorSwapFixedIbor: equal - hash", EUR1YEURIBOR3M.equals(generatorModified));
    final GeneratorLegIbor genIborOther = new GeneratorLegIbor(NAME_LEG_IBOR, EURIBOR3M, TARGET, TARGET, PAYMENT_TENOR_IBOR, ACT360, 
        MOD_FOL, EUR, IS_EOM, SPOT_LAG, TARGET, STUB, false, PAY_LAG+1);
    generatorModified = new GeneratorSwapFixedIbor(NAME, GENERATOR_LEG_FIXED, genIborOther);
    assertFalse("GeneratorSwapFixedIbor: equal - hash", EUR1YEURIBOR3M.equals(generatorModified));
    assertFalse("GeneratorSwapFixedIbor: equal - hash", EUR1YEURIBOR3M.equals(null));
    assertFalse("GeneratorSwapFixedIbor: equal - hash", EUR1YEURIBOR3M.equals(EUR));
  }
  
  @Test
  public void generateSwapNoNotional() {
    final GeneratorAttributeIROTC attribute = new GeneratorAttributeIROTC(Tenor.SIX_MONTHS, Tenor.TWO_YEARS);
    final ZonedDateTime referenceDate = DateUtils.getUTCDate(2014, 3, 24);
    final double rate = 0.02;
    final double notional = 100000000;
    final SwapFixedIborDefinition swapGenerated = EUR1YEURIBOR3M.generateInstrument(referenceDate, rate, notional, attribute);
    final AnnuityCouponFixedDefinition legFixedGenerated = GENERATOR_LEG_FIXED.generateInstrument(referenceDate, rate, notional, attribute, true);
    final AnnuityDefinition<? extends CouponDefinition> legIborGenerated = GENERATOR_LEG_EURIBOR3M.generateInstrument(referenceDate, notional, attribute, false);
    final SwapFixedIborDefinition swapExpected = new SwapFixedIborDefinition(legFixedGenerated, legIborGenerated);
    assertEquals("GeneratorSwapFixedIbor: generateInstrument", swapExpected, swapGenerated);
  }
  
  // TODO: notional exchange

}
