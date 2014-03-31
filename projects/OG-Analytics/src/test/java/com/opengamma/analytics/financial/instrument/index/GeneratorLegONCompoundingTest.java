/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.index;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;
import org.threeten.bp.Period;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.datasets.CalendarTarget;
import com.opengamma.analytics.financial.instrument.InstrumentDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinitionBuilder;
import com.opengamma.analytics.financial.instrument.payment.CouponDefinition;
import com.opengamma.analytics.financial.instrument.payment.CouponONDefinition;
import com.opengamma.analytics.financial.instrument.payment.CouponONSpreadDefinition;
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
 * Test related to Ibor leg generators.
 */
@Test(groups = TestGroup.UNIT)
public class GeneratorLegONCompoundingTest {
  
  private static final Period PAYMENT_PERIOD = Period.ofYears(1);
  private static final Tenor PAYMENT_TENOR = Tenor.of(PAYMENT_PERIOD);
  private static final int SPOT_LAG = 2;
  private static final Calendar TARGET = new CalendarTarget("TARGET");
  private static final DayCount DC30U_360 = DayCounts.THIRTY_U_360;
  private static final BusinessDayConvention MOD_FOL = BusinessDayConventions.MODIFIED_FOLLOWING;
  private static final boolean IS_EOM = true;
  private static final Currency EUR = Currency.EUR;
  private static final StubType STUB = StubType.SHORT_START;
  private static final int PAY_LAG = 1;
  private static final IndexONMaster MASTER_ON = IndexONMaster.getInstance();
  private static final IndexON EONIA = MASTER_ON.getIndex("EONIA");
  private static final  String NAME = "EUREONIACmp1Y";
  private static final GeneratorLegONCompounding GENERATOR_LEG_EONIA1Y = new GeneratorLegONCompounding(NAME, EONIA, TARGET, 
      PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, TARGET, STUB, false, PAY_LAG);
  private static final GeneratorLegONCompounding GENERATOR_LEG_EONIA1Y_NOT = new GeneratorLegONCompounding(NAME + "Not", EONIA, TARGET, 
      PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, TARGET, STUB, true, PAY_LAG);
  private static final  String NAME_FIXED = "EUR6MFixed";
  private static final GeneratorLegFixed GENERATOR_LEG_FIXED = new GeneratorLegFixed(NAME_FIXED, PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
      SPOT_LAG, TARGET, STUB, false, PAY_LAG);
  private static final GeneratorLegFixed GENERATOR_LEG_FIXED_NOT = new GeneratorLegFixed(NAME_FIXED + "NOT", PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
      SPOT_LAG, TARGET, STUB, true, PAY_LAG);
  
  private static final Calendar CALENDAR_A = new MondayToFridayCalendar("A");
  private static final Calendar CALENDAR_B = new MondayToFridayCalendar("B");
  private static final GeneratorLegONCompounding GENERATOR_LEG_EONIA1Y_CAL = new GeneratorLegONCompounding(NAME, EONIA, CALENDAR_A, 
      PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_B, STUB, false, PAY_LAG);
  
  @Test
  public void getter() {
    assertEquals("GeneratorLegIbor: getter", NAME, GENERATOR_LEG_EONIA1Y.getName());
    assertEquals("GeneratorLegIbor: getter", EONIA, GENERATOR_LEG_EONIA1Y.getONIndex());
    assertEquals("GeneratorLegIbor: getter", TARGET, GENERATOR_LEG_EONIA1Y.getIndexCalendar());
    assertEquals("GeneratorLegIbor: getter", PAYMENT_TENOR, GENERATOR_LEG_EONIA1Y.getPaymentTenor());
    assertEquals("GeneratorLegIbor: getter", DC30U_360, GENERATOR_LEG_EONIA1Y.getDayCount());
    assertEquals("GeneratorLegIbor: getter", MOD_FOL, GENERATOR_LEG_EONIA1Y.getBusinessDayConvention());
    assertEquals("GeneratorLegIbor: getter", EUR, GENERATOR_LEG_EONIA1Y.getCurrency());
    assertEquals("GeneratorLegIbor: getter", IS_EOM, GENERATOR_LEG_EONIA1Y.isEndOfMonth());
    assertEquals("GeneratorLegIbor: getter", SPOT_LAG, GENERATOR_LEG_EONIA1Y.getSpotLag());
    assertEquals("GeneratorLegIbor: getter", TARGET, GENERATOR_LEG_EONIA1Y.getPaymentCalendar());
    assertEquals("GeneratorLegIbor: getter", STUB, GENERATOR_LEG_EONIA1Y.getStubType());
    assertEquals("GeneratorLegIbor: getter", false, GENERATOR_LEG_EONIA1Y.isExchangeNotional());
    assertEquals("GeneratorLegIbor: getter", PAY_LAG, GENERATOR_LEG_EONIA1Y.getPaymentLag());
    assertEquals("GeneratorLegIbor: getter", CALENDAR_A, GENERATOR_LEG_EONIA1Y_CAL.getIndexCalendar());
    assertEquals("GeneratorLegIbor: getter", CALENDAR_B, GENERATOR_LEG_EONIA1Y_CAL.getPaymentCalendar());
  }


  @Test
  public void equalHash() {
    assertEquals("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y, GENERATOR_LEG_EONIA1Y);
    final GeneratorLegONCompounding generatorDuplicate = new GeneratorLegONCompounding(NAME, EONIA, CALENDAR_A, 
        PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_B, STUB, false, PAY_LAG);
    assertEquals("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL, generatorDuplicate);
    assertEquals("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL.hashCode(), generatorDuplicate.hashCode());
    GeneratorLegONCompounding generatorModified;
    generatorModified = new GeneratorLegONCompounding(NAME, MASTER_ON.getIndex("FED FUND"), CALENDAR_A, 
        PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_B, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL.equals(generatorModified));
    final Tenor otherTenor = Tenor.of(Period.ofMonths(6));
    generatorModified = new GeneratorLegONCompounding(NAME, EONIA, CALENDAR_A, 
        otherTenor, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_B, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegONCompounding(NAME, EONIA, CALENDAR_A, 
        PAYMENT_TENOR, DayCounts.ACT_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_B, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegONCompounding(NAME, EONIA, CALENDAR_A, 
        PAYMENT_TENOR, DC30U_360, BusinessDayConventions.PRECEDING, EUR, IS_EOM, SPOT_LAG, CALENDAR_B, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegONCompounding(NAME, EONIA, CALENDAR_A, 
        PAYMENT_TENOR, DC30U_360, MOD_FOL, Currency.AUD, IS_EOM, SPOT_LAG, CALENDAR_B, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegONCompounding(NAME, EONIA, CALENDAR_A, 
        PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, !IS_EOM, SPOT_LAG, CALENDAR_B, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegONCompounding(NAME, EONIA, CALENDAR_A, 
        PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG+1, CALENDAR_B, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegONCompounding(NAME, EONIA, CALENDAR_A, 
        PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_B, StubType.LONG_END, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegONCompounding(NAME, EONIA, CALENDAR_A, 
        PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_B, STUB, !false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegONCompounding(NAME, EONIA, CALENDAR_A, 
        PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_B, STUB, false, PAY_LAG+1);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL.equals(generatorModified));
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL.equals(null));
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EONIA1Y_CAL.equals(EUR));
  }
  
  @Test
  public void generateLegNoNotionalSpread() {
    final GeneratorAttributeIROTC attribute = new GeneratorAttributeIROTC(Tenor.SIX_MONTHS, Tenor.TWO_YEARS);
    final ZonedDateTime referenceDate = DateUtils.getUTCDate(2014, 3, 24);
    final double rate = 0.02;
    final double notional = 100000000;
    final boolean isPayer = true;
    final InstrumentDefinition<?> insGenerated = GENERATOR_LEG_EONIA1Y.generateInstrument(referenceDate, rate, notional, attribute, isPayer);
    assertTrue("GeneratorLegIbor: generateInstrument", insGenerated instanceof AnnuityDefinition<?>);
    final AnnuityDefinition<?> annuityIborGenerated = (AnnuityDefinition<?>) insGenerated;
    final ZonedDateTime spot = ScheduleCalculator.getAdjustedDate(referenceDate, SPOT_LAG, TARGET);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spot, attribute.getStartTenor(), MOD_FOL, TARGET, IS_EOM);
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(startDate, attribute.getEndTenor());
    final AnnuityDefinition<CouponONSpreadDefinition> legExpected = AnnuityDefinitionBuilder.couponONSimpleCompoundedSpread(startDate, maturityDate, 
        PAYMENT_PERIOD, TARGET, notional, rate, EONIA, TARGET, isPayer, MOD_FOL, IS_EOM, STUB, PAY_LAG);
    assertEquals("GeneratorLegIbor: generateInstrument", legExpected, insGenerated);
    final InstrumentDefinition<?> insGeneratedFixed = GENERATOR_LEG_FIXED.generateInstrument(referenceDate, rate, notional, attribute, isPayer);
    final AnnuityDefinition<?> annuityFixedGenerated = (AnnuityDefinition<?>) insGeneratedFixed;
    assertEquals("GeneratorLegIbor: generateInstrument", annuityFixedGenerated.getNumberOfPayments(), annuityIborGenerated.getNumberOfPayments());
    for(int loopcpn=0; loopcpn<annuityFixedGenerated.getNumberOfPayments(); loopcpn++){
      assertEquals("GeneratorLegIbor: generateInstrument", annuityFixedGenerated.getNthPayment(loopcpn).getPaymentDate(), annuityIborGenerated.getNthPayment(loopcpn).getPaymentDate());
    }
  }
  
  @Test
  public void generateLegNoNotionalNoSpread() {
    final GeneratorAttributeIROTC attribute = new GeneratorAttributeIROTC(Tenor.SIX_MONTHS, Tenor.TWO_YEARS);
    final ZonedDateTime referenceDate = DateUtils.getUTCDate(2014, 3, 24);
    final double notional = 100000000;
    final boolean isPayer = true;
    final InstrumentDefinition<?> insGenerated = GENERATOR_LEG_EONIA1Y.generateInstrument(referenceDate, notional, attribute, isPayer);
    assertTrue("GeneratorLegIbor: generateInstrument", insGenerated instanceof AnnuityDefinition<?>);
    final AnnuityDefinition<?> annuityIborGenerated = (AnnuityDefinition<?>) insGenerated;
    final ZonedDateTime spot = ScheduleCalculator.getAdjustedDate(referenceDate, SPOT_LAG, TARGET);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spot, attribute.getStartTenor(), MOD_FOL, TARGET, IS_EOM);
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(startDate, attribute.getEndTenor());
    final AnnuityDefinition<CouponONDefinition> legExpected = AnnuityDefinitionBuilder.couponONSimpleCompounded(startDate, maturityDate, 
        PAYMENT_PERIOD, TARGET, notional, EONIA, TARGET, isPayer, MOD_FOL, IS_EOM, STUB, PAY_LAG);
    assertEquals("GeneratorLegIbor: generateInstrument", legExpected, insGenerated);
    final InstrumentDefinition<?> insGeneratedFixed = GENERATOR_LEG_FIXED.generateInstrument(referenceDate, 0.0, notional, attribute, isPayer);
    final AnnuityDefinition<?> annuityFixedGenerated = (AnnuityDefinition<?>) insGeneratedFixed;
    assertEquals("GeneratorLegIbor: generateInstrument", annuityFixedGenerated.getNumberOfPayments(), annuityIborGenerated.getNumberOfPayments());
    for(int loopcpn=0; loopcpn<annuityFixedGenerated.getNumberOfPayments(); loopcpn++){
      assertEquals("GeneratorLegIbor: generateInstrument", annuityFixedGenerated.getNthPayment(loopcpn).getPaymentDate(), annuityIborGenerated.getNthPayment(loopcpn).getPaymentDate());
    }
  }
  
  @Test
  public void generateLegNotionalSpread() {
    final GeneratorAttributeIROTC attribute = new GeneratorAttributeIROTC(Tenor.SIX_MONTHS, Tenor.TWO_YEARS);
    final ZonedDateTime referenceDate = DateUtils.getUTCDate(2014, 3, 24);
    final double rate = 0.02;
    final double notional = 100000000;
    final boolean isPayer = true;
    final InstrumentDefinition<?> insGenerated = GENERATOR_LEG_EONIA1Y_NOT.generateInstrument(referenceDate, rate, notional, attribute, isPayer);
    assertTrue("GeneratorLegIbor: generateInstrument", insGenerated instanceof AnnuityDefinition<?>);
    final ZonedDateTime spot = ScheduleCalculator.getAdjustedDate(referenceDate, SPOT_LAG, TARGET);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spot, attribute.getStartTenor(), MOD_FOL, TARGET, IS_EOM);
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(startDate, attribute.getEndTenor());
    final AnnuityDefinition<?> annuityIborGenerated = (AnnuityDefinition<?>) insGenerated;
    final AnnuityDefinition<CouponDefinition> legExpected = AnnuityDefinitionBuilder.couponONSimpleCompoundedSpreadWithNotional(startDate, maturityDate, PAYMENT_PERIOD, TARGET, 
        notional, rate, EONIA, TARGET, isPayer, MOD_FOL, isPayer, STUB, PAY_LAG, true, true);
    assertEquals("GeneratorLegIbor: generateInstrument", legExpected, insGenerated);
    final InstrumentDefinition<?> insGeneratedFixed = GENERATOR_LEG_FIXED_NOT.generateInstrument(referenceDate, rate, notional, attribute, isPayer);
    final AnnuityDefinition<?> annuityFixedGenerated = (AnnuityDefinition<?>) insGeneratedFixed;
    assertEquals("GeneratorLegIbor: generateInstrument", annuityFixedGenerated.getNumberOfPayments(), annuityIborGenerated.getNumberOfPayments());
    for(int loopcpn=0; loopcpn<annuityFixedGenerated.getNumberOfPayments(); loopcpn++){
      assertEquals("GeneratorLegIbor: generateInstrument - " + loopcpn, annuityFixedGenerated.getNthPayment(loopcpn).getPaymentDate(), 
          annuityIborGenerated.getNthPayment(loopcpn).getPaymentDate());
    }
  }
  
  @Test
  public void generateLegNotionalNoSpread() {
    final GeneratorAttributeIROTC attribute = new GeneratorAttributeIROTC(Tenor.SIX_MONTHS, Tenor.ONE_YEAR);
    final ZonedDateTime referenceDate = DateUtils.getUTCDate(2014, 3, 24);
    final double notional = 100000000;
    final boolean isPayer = true;
    final InstrumentDefinition<?> insGenerated = GENERATOR_LEG_EONIA1Y_NOT.generateInstrument(referenceDate, notional, attribute, isPayer);
    assertTrue("GeneratorLegIbor: generateInstrument", insGenerated instanceof AnnuityDefinition<?>);
    final ZonedDateTime spot = ScheduleCalculator.getAdjustedDate(referenceDate, SPOT_LAG, TARGET);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spot, attribute.getStartTenor(), MOD_FOL, TARGET, IS_EOM);
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(startDate, attribute.getEndTenor());
    final AnnuityDefinition<?> annuityIborGenerated = (AnnuityDefinition<?>) insGenerated;
    final AnnuityDefinition<CouponDefinition> legExpected = AnnuityDefinitionBuilder.couponONSimpleCompoundedWithNotional(startDate, maturityDate, PAYMENT_PERIOD, TARGET, 
        notional, EONIA, TARGET, isPayer, MOD_FOL, IS_EOM, STUB, PAY_LAG, true, true);
    assertEquals("GeneratorLegIbor: generateInstrument", legExpected, insGenerated);
    final InstrumentDefinition<?> insGeneratedFixed = GENERATOR_LEG_FIXED_NOT.generateInstrument(referenceDate, 0.0, notional, attribute, isPayer);
    final AnnuityDefinition<?> annuityFixedGenerated = (AnnuityDefinition<?>) insGeneratedFixed;
    assertEquals("GeneratorLegIbor: generateInstrument", annuityFixedGenerated.getNumberOfPayments(), annuityIborGenerated.getNumberOfPayments());
    for(int loopcpn=0; loopcpn<annuityFixedGenerated.getNumberOfPayments(); loopcpn++){
      assertEquals("GeneratorLegIbor: generateInstrument", annuityFixedGenerated.getNthPayment(loopcpn).getPaymentDate(), annuityIborGenerated.getNthPayment(loopcpn).getPaymentDate());
    }
  }
  
}
