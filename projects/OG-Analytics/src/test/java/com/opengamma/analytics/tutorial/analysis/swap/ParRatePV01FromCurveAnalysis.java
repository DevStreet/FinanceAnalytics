/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.tutorial.analysis.swap;

import java.util.LinkedHashMap;

import org.testng.annotations.Test;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.datasets.CalendarUSD;
import com.opengamma.analytics.financial.instrument.NotionalProvider;
import com.opengamma.analytics.financial.instrument.annuity.AdjustedDateParameters;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityCouponFixedDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinition;
import com.opengamma.analytics.financial.instrument.annuity.FixedAnnuityDefinitionBuilder;
import com.opengamma.analytics.financial.instrument.annuity.FloatingAnnuityDefinitionBuilder;
import com.opengamma.analytics.financial.instrument.annuity.OffsetAdjustedDateParameters;
import com.opengamma.analytics.financial.instrument.annuity.OffsetType;
import com.opengamma.analytics.financial.instrument.index.GeneratorSwapFixedIbor;
import com.opengamma.analytics.financial.instrument.index.GeneratorSwapFixedIborMaster;
import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.analytics.financial.instrument.payment.CouponDefinition;
import com.opengamma.analytics.financial.instrument.payment.CouponFixedDefinition;
import com.opengamma.analytics.financial.instrument.swap.SwapCouponFixedCouponDefinition;
import com.opengamma.analytics.financial.interestrate.payments.derivative.Coupon;
import com.opengamma.analytics.financial.interestrate.swap.derivative.SwapFixedCoupon;
import com.opengamma.analytics.financial.provider.calculator.discounting.PresentValueCurveSensitivityDiscountingCalculator;
import com.opengamma.analytics.financial.provider.calculator.generic.MarketQuoteSensitivityBlockCalculator;
import com.opengamma.analytics.financial.provider.curve.CurveBuildingBlockBundle;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderDiscount;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderInterface;
import com.opengamma.analytics.financial.provider.sensitivity.multicurve.MultipleCurrencyParameterSensitivity;
import com.opengamma.analytics.financial.provider.sensitivity.parameter.ParameterSensitivityParameterCalculator;
import com.opengamma.analytics.financial.util.AssertSensitivityObjects;
import com.opengamma.analytics.math.matrix.DoubleMatrix1D;
import com.opengamma.analytics.tutorial.datasets.ImpliedMulticurveSimplified;
import com.opengamma.analytics.tutorial.datasets.RecentDataSetsMulticurveStandardUsd;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.rolldate.RollConvention;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.DateUtils;
import com.opengamma.util.tuple.ObjectsPair;
import com.opengamma.util.tuple.Pair;

/**
 * Computes the bucketed PV01 with respect to market quotes for a given set of instruments using a given 
 * curve set to produce the market quotes.
 */
public class ParRatePV01FromCurveAnalysis {

  private static final ZonedDateTime VALUATION_DATE = DateUtils.getUTCDate(2014, 7, 16);
  
  private static final GeneratorSwapFixedIborMaster GENERATOR_IRS_MASTER = GeneratorSwapFixedIborMaster.getInstance();
  
  private static final double NOTIONAL_1 = 1000000; // 1m
  private static final NotionalProvider NOTIONAL_PROV_1 = new NotionalProvider() {
    @Override
    public double getAmount(final LocalDate date) {
      return NOTIONAL_1;
    }
  };
  
  /** Calculators */
  private static final PresentValueCurveSensitivityDiscountingCalculator PVCSDC = 
      PresentValueCurveSensitivityDiscountingCalculator.getInstance();
  private static final ParameterSensitivityParameterCalculator<MulticurveProviderInterface> PSC = 
      new ParameterSensitivityParameterCalculator<>(PVCSDC);
  private static final MarketQuoteSensitivityBlockCalculator<MulticurveProviderInterface> MQSBC = 
      new MarketQuoteSensitivityBlockCalculator<>(PSC);
      
  private static final double TOLERANCE_PV_DELTA = 1.0E-4;

  /** =====     USD     ===== */
  
  private static final Calendar NYC = new CalendarUSD("NYC");
  private static final GeneratorSwapFixedIbor USD6MLIBOR3M = 
      GENERATOR_IRS_MASTER.getGenerator(GeneratorSwapFixedIborMaster.USD6MLIBOR3M, NYC);
  private static final AdjustedDateParameters ADJUSTED_DATE_USDLIBOR = 
      new AdjustedDateParameters(NYC, USD6MLIBOR3M.getBusinessDayConvention());
  private static final OffsetAdjustedDateParameters OFFSET_ADJ_USDLIBOR =
      new OffsetAdjustedDateParameters(-2, OffsetType.BUSINESS, NYC, USD6MLIBOR3M.getBusinessDayConvention());
  private static final IborIndex USDLIBOR3M = USD6MLIBOR3M.getIborIndex();
  private static final Currency USD = USDLIBOR3M.getCurrency();
  
  /** Swap Fixed v LIBOR3M **/
  private static final LocalDate EFFECTIVE_DATE_2 = LocalDate.of(2019, 7, 3);
  private static final LocalDate MATURITY_DATE_2 = LocalDate.of(2024, 7, 3);
  private static final double FIXED_RATE_2 = 0.037125;
  private static final boolean PAYER_2 = true;
  private static final AnnuityDefinition<?> FIXED_LEG_GEN_2_DEFINITION = new FixedAnnuityDefinitionBuilder().
      payer(PAYER_2).currency(USD).notional(NOTIONAL_PROV_1).startDate(EFFECTIVE_DATE_2).
      endDate(MATURITY_DATE_2).dayCount(USD6MLIBOR3M.getFixedLegDayCount()).
      accrualPeriodFrequency(USD6MLIBOR3M.getFixedLegPeriod()).rate(FIXED_RATE_2).
      accrualPeriodParameters(ADJUSTED_DATE_USDLIBOR).build();
  private static final AnnuityCouponFixedDefinition FIXED_LEG_2_DEFINITION = 
      new AnnuityCouponFixedDefinition((CouponFixedDefinition[])FIXED_LEG_GEN_2_DEFINITION.getPayments(), NYC);
  private static final AnnuityDefinition<? extends CouponDefinition> IBOR_LEG_2_DEFINITION = 
       (AnnuityDefinition<? extends CouponDefinition>) new FloatingAnnuityDefinitionBuilder().payer(!PAYER_2).
       notional(NOTIONAL_PROV_1).startDate(EFFECTIVE_DATE_2).endDate(MATURITY_DATE_2).index(USDLIBOR3M).
       accrualPeriodFrequency(USDLIBOR3M.getTenor()).rollDateAdjuster(RollConvention.NONE.getRollDateAdjuster(0)).
       resetDateAdjustmentParameters(ADJUSTED_DATE_USDLIBOR).accrualPeriodParameters(ADJUSTED_DATE_USDLIBOR).
       dayCount(USDLIBOR3M.getDayCount()).fixingDateAdjustmentParameters(OFFSET_ADJ_USDLIBOR).currency(USD).build();
  private static final SwapCouponFixedCouponDefinition IRS_1_DEFINITION = 
      new SwapCouponFixedCouponDefinition(FIXED_LEG_2_DEFINITION, IBOR_LEG_2_DEFINITION);
  private static final SwapFixedCoupon<Coupon> IRS_1 = IRS_1_DEFINITION.toDerivative(VALUATION_DATE);

  /** Curves */
  private static final Pair<MulticurveProviderDiscount, CurveBuildingBlockBundle> MULTICURVE_STD_PAIR =
      RecentDataSetsMulticurveStandardUsd.getCurvesUSDOisL1L3L6(VALUATION_DATE);
  private static final MulticurveProviderDiscount MULTICURVE_STD = MULTICURVE_STD_PAIR.getFirst();
  
  private static final Pair<MulticurveProviderDiscount, CurveBuildingBlockBundle> MULTICURVE_SIMPLE_PAIR = 
      ImpliedMulticurveSimplified.getCurvesUsd(VALUATION_DATE, MULTICURVE_STD);
  private static final MulticurveProviderDiscount MULTICURVE_SIMPLE = MULTICURVE_SIMPLE_PAIR.getFirst();
  private static final CurveBuildingBlockBundle BLOCK_SIMPLE = MULTICURVE_SIMPLE_PAIR.getSecond();
  

  @Test
  /** Produce market quote sensitivities for the IRS with respect to the implied market quotes. */
  public void marketQuoteSensitivity() {
    final double[] delta = {-119447.2381, -4571285.8749, 9134616.8957, 0.0000};
    final LinkedHashMap<Pair<String, Currency>, DoubleMatrix1D> sensitivity = new LinkedHashMap<>();
    sensitivity.put(ObjectsPair.of(MULTICURVE_SIMPLE.getName(USD), USD), new DoubleMatrix1D(delta));
    final MultipleCurrencyParameterSensitivity pvpsExpected = new MultipleCurrencyParameterSensitivity(sensitivity);
    MultipleCurrencyParameterSensitivity mqsComputed = MQSBC.fromInstrument(IRS_1, MULTICURVE_SIMPLE, BLOCK_SIMPLE);
    AssertSensitivityObjects.assertEquals("Swap ON Arithmetic Average: bucketed deltas - standard curves", 
        pvpsExpected, mqsComputed, TOLERANCE_PV_DELTA);    
  }

}
