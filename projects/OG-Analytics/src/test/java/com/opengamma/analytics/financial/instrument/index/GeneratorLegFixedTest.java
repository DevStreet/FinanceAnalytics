/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
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
import com.opengamma.analytics.financial.instrument.InstrumentDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinitionBuilder;
import com.opengamma.analytics.financial.instrument.payment.CouponFixedDefinition;
import com.opengamma.analytics.financial.schedule.ScheduleCalculator;
import com.opengamma.analytics.util.time.TenorUtils;
import com.opengamma.financial.convention.StubType;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.businessday.BusinessDayConventions;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.calendar.MondayToFridayCalendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCounts;
import com.opengamma.util.money.Currency;
import com.opengamma.util.test.TestGroup;
import com.opengamma.util.time.DateUtils;
import com.opengamma.util.time.Tenor;

/**
 * Test related to fixed leg generators.
 */
@Test(groups = TestGroup.UNIT)
public class GeneratorLegFixedTest {
  
  private static final Period PAYMENT_PERIOD = Period.ofMonths(6);
  private static final Tenor PAYMENT_TENOR = Tenor.of(PAYMENT_PERIOD);
  private static final int SPOT_LAG = 2;
  private static final Calendar TARGET = new CalendarTarget("TARGET");
  private static final DayCount DC30U_360 = DayCounts.THIRTY_U_360;
  private static final BusinessDayConvention MOD_FOL = BusinessDayConventions.MODIFIED_FOLLOWING;
  private static final boolean IS_EOM = true;
  private static final Currency EUR = Currency.EUR;
  private static final StubType STUB = StubType.SHORT_START;
  private static final int PAY_LAG = 2;
  private static final  String NAME = "EUR6MFixed";
  private static final GeneratorLegFixed GENERATOR_LEG_FIXED = new GeneratorLegFixed(NAME, PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
      SPOT_LAG, TARGET, STUB, false, PAY_LAG);
  
  private static final Calendar CALENDAR = new MondayToFridayCalendar("A");

  @Test
  public void getter() {
    assertEquals("GeneratorLegFixed: getter", PAYMENT_TENOR, GENERATOR_LEG_FIXED.getPaymentTenor());
    assertEquals("GeneratorLegFixed: getter",SPOT_LAG, GENERATOR_LEG_FIXED.getSpotLag());
    assertEquals("GeneratorLegFixed: getter", DC30U_360, GENERATOR_LEG_FIXED.getDayCount());
    assertEquals("GeneratorLegFixed: getter", MOD_FOL, GENERATOR_LEG_FIXED.getBusinessDayConvention());
    assertEquals("GeneratorLegFixed: getter", EUR, GENERATOR_LEG_FIXED.getCurrency());
    assertEquals("GeneratorLegFixed: getter", IS_EOM, GENERATOR_LEG_FIXED.isEndOfMonth());
    assertEquals("GeneratorLegFixed: getter", SPOT_LAG, GENERATOR_LEG_FIXED.getSpotLag());
    assertEquals("GeneratorLegFixed: getter", TARGET, GENERATOR_LEG_FIXED.getCalendar());
    assertEquals("GeneratorLegFixed: getter", STUB, GENERATOR_LEG_FIXED.getStubType());
    assertEquals("GeneratorLegFixed: getter", false, GENERATOR_LEG_FIXED.isExchangeNotional());
    assertEquals("GeneratorLegFixed: getter", PAY_LAG, GENERATOR_LEG_FIXED.getPaymentLag());
  }

  @Test
  public void equalHash() {
    assertEquals("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED, GENERATOR_LEG_FIXED);
    final GeneratorLegFixed generatorDuplicate = new GeneratorLegFixed(NAME, PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG, TARGET, STUB, false, PAY_LAG);
    assertEquals("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED, generatorDuplicate);
    assertEquals("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED.hashCode(), generatorDuplicate.hashCode());
    GeneratorLegFixed generatorModified;
    final Tenor otherTenor = Tenor.of(Period.ofMonths(12));
    generatorModified = new GeneratorLegFixed(NAME, otherTenor, DC30U_360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG, TARGET, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED.equals(generatorModified));
    generatorModified = new GeneratorLegFixed(NAME, PAYMENT_TENOR, DayCounts.ACT_360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG, TARGET, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED.equals(generatorModified));
    generatorModified =  new GeneratorLegFixed(NAME, PAYMENT_TENOR, DC30U_360, BusinessDayConventions.PRECEDING, EUR, IS_EOM, 
        SPOT_LAG, TARGET, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED.equals(generatorModified));
    generatorModified =  new GeneratorLegFixed(NAME, PAYMENT_TENOR, DC30U_360, MOD_FOL, Currency.AUD, IS_EOM, 
        SPOT_LAG, TARGET, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED.equals(generatorModified));
    generatorModified =  new GeneratorLegFixed(NAME, PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, !IS_EOM, 
        SPOT_LAG, TARGET, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED.equals(generatorModified));
    generatorModified =  new GeneratorLegFixed(NAME, PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG+1, TARGET, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED.equals(generatorModified));
    generatorModified =  new GeneratorLegFixed(NAME, PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG, CALENDAR, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED.equals(generatorModified));
    generatorModified =  new GeneratorLegFixed(NAME, PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG, TARGET, StubType.SHORT_END, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED.equals(generatorModified));
    generatorModified =  new GeneratorLegFixed(NAME, PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG, TARGET, STUB, true, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED.equals(generatorModified));
    generatorModified =  new GeneratorLegFixed(NAME, PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG, TARGET, STUB, false, PAY_LAG+1);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED.equals(generatorModified));
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED.equals(null));
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_FIXED.equals(EUR));
  }
  
  @Test
  public void generateLegNoNotional() {
    final GeneratorAttributeIROTC attribute = new GeneratorAttributeIROTC(Tenor.SIX_MONTHS, Tenor.TWO_YEARS);
    final ZonedDateTime referenceDate = DateUtils.getUTCDate(2014, 3, 24);
    final double rate = 0.02;
    final double notional = 100000000;
    final boolean isPayer = true;
    final InstrumentDefinition<?> legGenerated = GENERATOR_LEG_FIXED.generateInstrument(referenceDate, rate, notional, attribute, isPayer);
    final ZonedDateTime spot = ScheduleCalculator.getAdjustedDate(referenceDate, SPOT_LAG, TARGET);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spot, attribute.getStartTenor(), MOD_FOL, TARGET, IS_EOM);
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(startDate, attribute.getEndTenor());
    final AnnuityDefinition<CouponFixedDefinition> legExpected = AnnuityDefinitionBuilder.couponFixed(EUR, startDate, maturityDate, 
        PAYMENT_PERIOD, TARGET, DC30U_360, MOD_FOL, IS_EOM, notional, rate, isPayer, STUB, PAY_LAG);
    assertEquals("GeneratorLegFixed: generateInstrument", legExpected, legGenerated);
    final InstrumentDefinition<?> legGenerated2 = GENERATOR_LEG_FIXED.generateInstrument(referenceDate, rate, notional, attribute);
    assertEquals("GeneratorLegFixed: generateInstrument", legGenerated, legGenerated2);
  }
  
  @Test
  public void generateLegNotional() {
    final GeneratorAttributeIROTC attribute = new GeneratorAttributeIROTC(Tenor.SIX_MONTHS, Tenor.TWO_YEARS);
    final ZonedDateTime referenceDate = DateUtils.getUTCDate(2014, 3, 24);
    final double rate = 0.02;
    final double notional = 100000000;
    final boolean isPayer = true;
    final GeneratorLegFixed genLegFixedNotional = new GeneratorLegFixed(NAME, PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
        SPOT_LAG, TARGET, STUB, true, PAY_LAG);
    final InstrumentDefinition<?> legGenerated = genLegFixedNotional.generateInstrument(referenceDate, rate, notional, attribute, isPayer);
    final ZonedDateTime spot = ScheduleCalculator.getAdjustedDate(referenceDate, SPOT_LAG, TARGET);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spot, attribute.getStartTenor(), MOD_FOL, TARGET, IS_EOM);
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(startDate, attribute.getEndTenor());
    final AnnuityDefinition<CouponFixedDefinition> legExpected = AnnuityDefinitionBuilder.couponFixedWithNotional(EUR, startDate, maturityDate, 
        PAYMENT_PERIOD, TARGET, DC30U_360, MOD_FOL, IS_EOM, notional, rate, isPayer, STUB, PAY_LAG, true, true);
    assertEquals("GeneratorLegFixed: generateInstrument", legExpected, legGenerated);
  }

}
