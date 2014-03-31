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
import com.opengamma.analytics.financial.instrument.payment.CouponIborCompoundingDefinition;
import com.opengamma.analytics.financial.instrument.payment.CouponIborCompoundingFlatSpreadDefinition;
import com.opengamma.analytics.financial.instrument.payment.CouponIborCompoundingSpreadDefinition;
import com.opengamma.analytics.financial.interestrate.CompoundingType;
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
public class GeneratorLegIborCompoundingTest {
  
  private static final Period PAYMENT_PERIOD = Period.ofMonths(6);
  private static final Tenor PAYMENT_TENOR = Tenor.of(PAYMENT_PERIOD);
  private static final Period COMPOSITION_PERIOD = Period.ofMonths(3);
  private static final Tenor COMPOSITION_TENOR = Tenor.of(COMPOSITION_PERIOD);
  private static final CompoundingType CMP_COMPOUNDING = CompoundingType.COMPOUNDING;
  private static final CompoundingType CMP_FLAT_COMPOUNDING = CompoundingType.FLAT_COMPOUNDING;
  private static final int SPOT_LAG = 2;
  private static final Calendar TARGET = new CalendarTarget("TARGET");
  private static final DayCount DC30U_360 = DayCounts.THIRTY_U_360;
  private static final BusinessDayConvention MOD_FOL = BusinessDayConventions.MODIFIED_FOLLOWING;
  private static final boolean IS_EOM = true;
  private static final Currency EUR = Currency.EUR;
  private static final StubType STUB = StubType.SHORT_START;
  private static final StubType STUB_CMP = StubType.LONG_START;
  private static final int PAY_LAG = 0;
  private static final IndexIborMaster MASTER_IBOR = IndexIborMaster.getInstance();
  private static final IborIndex EURIBOR3M = MASTER_IBOR.getIndex("EURIBOR3M");
  private static final IborIndex EURIBOR6M = MASTER_IBOR.getIndex("EURIBOR6M");
  private static final  String NAME = "EUREURIBOR3MCmp6M";
  private static final GeneratorLegIborCompounding GENERATOR_LEG_EURIBOR3MCmp6M = new GeneratorLegIborCompounding(NAME, EURIBOR3M, TARGET, TARGET, 
      PAYMENT_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, TARGET, STUB, false, PAY_LAG);
  private static final GeneratorLegIborCompounding GENERATOR_LEG_EURIBOR3MCmp6M_FLAT = new GeneratorLegIborCompounding(NAME, EURIBOR3M, TARGET, TARGET, 
      PAYMENT_TENOR, CMP_FLAT_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, TARGET, STUB, false, PAY_LAG);
  private static final  String NAME_FIXED = "EUR6MFixed";
  private static final GeneratorLegFixed GENERATOR_LEG_FIXED = new GeneratorLegFixed(NAME_FIXED, PAYMENT_TENOR, DC30U_360, MOD_FOL, EUR, IS_EOM, 
      SPOT_LAG, TARGET, STUB, false, PAY_LAG);
  
  private static final Calendar CALENDAR_A = new MondayToFridayCalendar("A");
  private static final Calendar CALENDAR_B = new MondayToFridayCalendar("B");
  private static final Calendar CALENDAR_C = new MondayToFridayCalendar("C");
  private static final GeneratorLegIborCompounding GENERATOR_LEG_EURIBOR3MCmp6M_CAL = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B, 
      PAYMENT_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_C, STUB, false, PAY_LAG);
  
  @Test
  public void getter() {
    assertEquals("GeneratorLegIbor: getter", NAME, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getName());
    assertEquals("GeneratorLegIbor: getter", EURIBOR3M, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getIborIndex());
    assertEquals("GeneratorLegIbor: getter", CALENDAR_A, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getDepositCalendar());
    assertEquals("GeneratorLegIbor: getter", CALENDAR_B, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getFixingCalendar());
    assertEquals("GeneratorLegIbor: getter", PAYMENT_TENOR, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getPaymentTenor());
    assertEquals("GeneratorLegIbor: getter", CMP_COMPOUNDING, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getCompoundingType());
    assertEquals("GeneratorLegIbor: getter", COMPOSITION_TENOR, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getCompositionTenor());
    assertEquals("GeneratorLegIbor: getter", STUB_CMP, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getStubTypeCompound());
    assertEquals("GeneratorLegIbor: getter", DC30U_360, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getDayCount());
    assertEquals("GeneratorLegIbor: getter", MOD_FOL, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getBusinessDayConvention());
    assertEquals("GeneratorLegIbor: getter", EUR, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getCurrency());
    assertEquals("GeneratorLegIbor: getter", IS_EOM, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.isEndOfMonth());
    assertEquals("GeneratorLegIbor: getter", SPOT_LAG, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getSpotLag());
    assertEquals("GeneratorLegIbor: getter", CALENDAR_C, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getPaymentCalendar());
    assertEquals("GeneratorLegIbor: getter", STUB, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getStubType());
    assertEquals("GeneratorLegIbor: getter", false, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.isExchangeNotional());
    assertEquals("GeneratorLegIbor: getter", PAY_LAG, GENERATOR_LEG_EURIBOR3MCmp6M_CAL.getPaymentLag());
  }


  @Test
  public void equalHash() {
    assertEquals("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL, GENERATOR_LEG_EURIBOR3MCmp6M_CAL);
    final GeneratorLegIborCompounding generatorDuplicate = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B, 
        PAYMENT_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_C, STUB, false, PAY_LAG);
    assertEquals("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL, generatorDuplicate);
    assertEquals("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.hashCode(), generatorDuplicate.hashCode());
    GeneratorLegIborCompounding generatorModified;
    generatorModified = new GeneratorLegIborCompounding(NAME, EURIBOR6M, CALENDAR_A, CALENDAR_B,
        PAYMENT_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_C, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B,
        COMPOSITION_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_C, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B, 
        PAYMENT_TENOR, CMP_FLAT_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_C, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B, 
        PAYMENT_TENOR, CMP_COMPOUNDING, PAYMENT_TENOR, STUB_CMP, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_C, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B, 
        PAYMENT_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_C, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B, 
        PAYMENT_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DayCounts.ACT_ACT_ICMA, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_C, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B, 
        PAYMENT_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, BusinessDayConventions.PRECEDING, EUR, IS_EOM, SPOT_LAG, CALENDAR_C, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B, 
        PAYMENT_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, MOD_FOL, Currency.AUD, IS_EOM, SPOT_LAG, CALENDAR_C, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B, 
        PAYMENT_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, MOD_FOL, EUR, !IS_EOM, SPOT_LAG, CALENDAR_C, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B, 
        PAYMENT_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG+1, CALENDAR_C, STUB, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B, 
        PAYMENT_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_C, STUB_CMP, false, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B, 
        PAYMENT_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_C, STUB, true, PAY_LAG);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(generatorModified));
    generatorModified = new GeneratorLegIborCompounding(NAME, EURIBOR3M, CALENDAR_A, CALENDAR_B, 
        PAYMENT_TENOR, CMP_COMPOUNDING, COMPOSITION_TENOR, STUB_CMP, DC30U_360, MOD_FOL, EUR, IS_EOM, SPOT_LAG, CALENDAR_C, STUB, false, PAY_LAG+1);
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(generatorModified));
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(null));
    assertFalse("GeneratorLegFixed: hash - equal", GENERATOR_LEG_EURIBOR3MCmp6M_CAL.equals(EUR));
  }
  
  @Test
  public void generateLegNoNotionalSpread() {
    final GeneratorAttributeIROTC attribute = new GeneratorAttributeIROTC(Tenor.SIX_MONTHS, Tenor.TWO_YEARS);
    final ZonedDateTime referenceDate = DateUtils.getUTCDate(2014, 3, 24);
    final double rate = 0.02;
    final double notional = 100000000;
    final boolean isPayer = true;
    final InstrumentDefinition<?> insGenerated = GENERATOR_LEG_EURIBOR3MCmp6M.generateInstrument(referenceDate, rate, notional, attribute, isPayer);
    assertTrue("GeneratorLegIbor: generateInstrument", insGenerated instanceof AnnuityDefinition<?>);
    final AnnuityDefinition<?> annuityIborGenerated = (AnnuityDefinition<?>) insGenerated;
    final ZonedDateTime spot = ScheduleCalculator.getAdjustedDate(referenceDate, SPOT_LAG, TARGET);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spot, attribute.getStartTenor(), MOD_FOL, TARGET, IS_EOM);
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(startDate, attribute.getEndTenor());
    final AnnuityDefinition<CouponIborCompoundingSpreadDefinition> legExpected = AnnuityDefinitionBuilder.couponIborCompoundingSpread(startDate, maturityDate, 
        PAYMENT_PERIOD, notional, rate, EURIBOR3M, STUB_CMP, isPayer, MOD_FOL, IS_EOM, TARGET, TARGET, TARGET, STUB);
    assertEquals("GeneratorLegIbor: generateInstrument", legExpected, insGenerated);
    final InstrumentDefinition<?> insGeneratedFixed = GENERATOR_LEG_FIXED.generateInstrument(referenceDate, rate, notional, attribute, isPayer);
    final AnnuityDefinition<?> annuityFixedGenerated = (AnnuityDefinition<?>) insGeneratedFixed;
    assertEquals("GeneratorLegIbor: generateInstrument", annuityFixedGenerated.getNumberOfPayments(), annuityIborGenerated.getNumberOfPayments());
    for(int loopcpn=0; loopcpn<annuityFixedGenerated.getNumberOfPayments(); loopcpn++){
      assertEquals("GeneratorLegIbor: generateInstrument", annuityFixedGenerated.getNthPayment(loopcpn).getPaymentDate(), 
          annuityIborGenerated.getNthPayment(loopcpn).getPaymentDate());
    }
  }
  
  @Test
  public void generateLegNoNotionalSpreadFlat() {
    final GeneratorAttributeIROTC attribute = new GeneratorAttributeIROTC(Tenor.SIX_MONTHS, Tenor.TWO_YEARS);
    final ZonedDateTime referenceDate = DateUtils.getUTCDate(2014, 3, 24);
    final double rate = 0.02;
    final double notional = 100000000;
    final boolean isPayer = true;
    final InstrumentDefinition<?> insGenerated = GENERATOR_LEG_EURIBOR3MCmp6M_FLAT.generateInstrument(referenceDate, rate, notional, attribute, isPayer);
    assertTrue("GeneratorLegIbor: generateInstrument", insGenerated instanceof AnnuityDefinition<?>);
    final AnnuityDefinition<?> annuityIborGenerated = (AnnuityDefinition<?>) insGenerated;
    final ZonedDateTime spot = ScheduleCalculator.getAdjustedDate(referenceDate, SPOT_LAG, TARGET);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spot, attribute.getStartTenor(), MOD_FOL, TARGET, IS_EOM);
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(startDate, attribute.getEndTenor());
    final AnnuityDefinition<CouponIborCompoundingFlatSpreadDefinition> legExpected = AnnuityDefinitionBuilder.couponIborCompoundingFlatSpread(startDate, maturityDate, 
        PAYMENT_PERIOD, notional, rate, EURIBOR3M, STUB_CMP, isPayer, MOD_FOL, IS_EOM, TARGET, TARGET, TARGET, STUB);
    assertEquals("GeneratorLegIbor: generateInstrument", legExpected, insGenerated);
    final InstrumentDefinition<?> insGeneratedFixed = GENERATOR_LEG_FIXED.generateInstrument(referenceDate, rate, notional, attribute, isPayer);
    final AnnuityDefinition<?> annuityFixedGenerated = (AnnuityDefinition<?>) insGeneratedFixed;
    assertEquals("GeneratorLegIbor: generateInstrument", annuityFixedGenerated.getNumberOfPayments(), annuityIborGenerated.getNumberOfPayments());
    for(int loopcpn=0; loopcpn<annuityFixedGenerated.getNumberOfPayments(); loopcpn++){
      assertEquals("GeneratorLegIbor: generateInstrument", annuityFixedGenerated.getNthPayment(loopcpn).getPaymentDate(), 
          annuityIborGenerated.getNthPayment(loopcpn).getPaymentDate());
    }
  }
  
  @Test
  public void generateLegNoNotionalNoSpread() {
    final GeneratorAttributeIROTC attribute = new GeneratorAttributeIROTC(Tenor.SIX_MONTHS, Tenor.TWO_YEARS);
    final ZonedDateTime referenceDate = DateUtils.getUTCDate(2014, 3, 24);
    final double notional = 100000000;
    final boolean isPayer = true;
    final InstrumentDefinition<?> insGenerated = GENERATOR_LEG_EURIBOR3MCmp6M.generateInstrument(referenceDate, notional, attribute, isPayer);
    assertTrue("GeneratorLegIbor: generateInstrument", insGenerated instanceof AnnuityDefinition<?>);
    final AnnuityDefinition<?> annuityIborGenerated = (AnnuityDefinition<?>) insGenerated;
    final ZonedDateTime spot = ScheduleCalculator.getAdjustedDate(referenceDate, SPOT_LAG, TARGET);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spot, attribute.getStartTenor(), MOD_FOL, TARGET, IS_EOM);
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(startDate, attribute.getEndTenor());
    final AnnuityDefinition<CouponIborCompoundingDefinition> legExpected = AnnuityDefinitionBuilder.couponIborCompounding(startDate, maturityDate, 
        PAYMENT_PERIOD, notional, EURIBOR3M, STUB_CMP, isPayer, MOD_FOL, IS_EOM, TARGET, TARGET, TARGET, STUB);
    assertEquals("GeneratorLegIbor: generateInstrument", legExpected, insGenerated);
    final InstrumentDefinition<?> insGeneratedFixed = GENERATOR_LEG_FIXED.generateInstrument(referenceDate, 0.0, notional, attribute, isPayer);
    final AnnuityDefinition<?> annuityFixedGenerated = (AnnuityDefinition<?>) insGeneratedFixed;
    assertEquals("GeneratorLegIbor: generateInstrument", annuityFixedGenerated.getNumberOfPayments(), annuityIborGenerated.getNumberOfPayments());
    for(int loopcpn=0; loopcpn<annuityFixedGenerated.getNumberOfPayments(); loopcpn++){
      assertEquals("GeneratorLegIbor: generateInstrument", annuityFixedGenerated.getNthPayment(loopcpn).getPaymentDate(), annuityIborGenerated.getNthPayment(loopcpn).getPaymentDate());
    }
  }
  
}
