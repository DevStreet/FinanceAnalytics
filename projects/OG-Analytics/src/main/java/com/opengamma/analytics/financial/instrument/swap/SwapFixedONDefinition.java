/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.swap;

import org.threeten.bp.Period;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.instrument.NotionalProvider;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityCouponFixedDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityCouponONDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinitionBuilder;
import com.opengamma.analytics.financial.instrument.index.GeneratorLegFixed;
import com.opengamma.analytics.financial.instrument.index.GeneratorLegONCompounding;
import com.opengamma.analytics.financial.instrument.index.GeneratorSwapFixedONCompounding;
import com.opengamma.analytics.financial.interestrate.annuity.derivative.Annuity;
import com.opengamma.analytics.financial.interestrate.payments.derivative.Coupon;
import com.opengamma.analytics.financial.interestrate.payments.derivative.CouponFixed;
import com.opengamma.analytics.financial.interestrate.swap.derivative.SwapFixedCoupon;
import com.opengamma.timeseries.precise.zdt.ZonedDateTimeDoubleTimeSeries;
import com.opengamma.util.ArgumentChecker;

/**
 * Class describing a fixed for ON rate swap. Both legs are in the same currency.
 * The payment dates on the fixed leg a slightly different from the FixedIbor swap due to the lag in payment at the end of each coupon.
 */
public class SwapFixedONDefinition extends SwapDefinition {

  /**
   * Constructor of the fixed-OIS swap from its two legs.
   * @param fixedLeg The fixed leg.
   * @param oisLeg The OIS leg.
   */
  public SwapFixedONDefinition(final AnnuityCouponFixedDefinition fixedLeg, final AnnuityCouponONDefinition oisLeg) {
    super(fixedLeg, oisLeg);
    ArgumentChecker.isTrue(fixedLeg.getCurrency() == oisLeg.getCurrency(), "Legs should have the same currency");
  }

  /**
   * Builder of OIS swap from financial description (start date and tenor).
   * @param settlementDate The annuity settlement or first fixing date.
   * @param tenorAnnuity The total tenor of the annuity.
   * @param notional The annuity notional.
   * @param generator The OIS generator. The notional exchange flag is not taken into account.
   * @param fixedRate The rate of the swap fixed leg.
   * @param isPayer The flag indicating if the annuity is paying (true) or receiving (false).
   * @return The swap.
   */
  public static SwapFixedONDefinition from(final ZonedDateTime settlementDate, final Period tenorAnnuity, final double notional, 
      final GeneratorSwapFixedONCompounding generator, final double fixedRate, final boolean isPayer) {
    final ZonedDateTime maturityDate = settlementDate.plus(tenorAnnuity);
    return from(settlementDate, maturityDate, notional, generator, fixedRate, isPayer);
  }

  /**
   * Builder of OIS swap from financial description (start date and end date).
   * @param settlementDate The annuity settlement or first fixing date.
   * @param endFixingPeriodDate  The end date of the OIS accrual period. Also called the maturity date of the annuity even if the actual payment can take place one or two days later. Not null.
   * @param notional The annuity notional.
   * @param generator The OIS generator. The notional exchange flag is not taken into account.
   * @param fixedRate The rate of the swap fixed leg.
   * @param isPayer The flag indicating if the annuity is paying (true) or receiving (false).
   * @return The swap.
   */
  public static SwapFixedONDefinition from(final ZonedDateTime settlementDate, final ZonedDateTime endFixingPeriodDate, final double notional, 
      final GeneratorSwapFixedONCompounding generator, final double fixedRate, final boolean isPayer) {
    return from(settlementDate, endFixingPeriodDate, notional, notional, generator, fixedRate, isPayer);
  }

  /**
   * Builder of OIS swap from financial description (start date and end date, the fixed leg and floating leg notionals can be different).
   * @param settlementDate The annuity settlement or first fixing date.
   * @param endFixingPeriodDate  The end date of the OIS accrual period. Also called the maturity date of the annuity even if the actual payment can take place one or two days later. Not null.
   * @param notionalFixed The notional of the fixed leg.
   * @param notionalOIS The notional of the OIS leg.
   * @param generator The OIS generator. The notional exchange flag is not taken into account.
   * @param fixedRate The rate of the swap fixed leg.
   * @param isPayer The flag indicating if the annuity is paying (true) or receiving (false).
   * @return The swap.
   */
  public static SwapFixedONDefinition from(final ZonedDateTime settlementDate, final ZonedDateTime endFixingPeriodDate, final double notionalFixed, final double notionalOIS,
      final GeneratorSwapFixedONCompounding generator, final double fixedRate, final boolean isPayer) {
    ArgumentChecker.notNull(settlementDate, "Settlement date");
    ArgumentChecker.notNull(endFixingPeriodDate, "Maturity date");
    ArgumentChecker.notNull(generator, "Swap generator");
    final GeneratorLegFixed genFix = generator.getFixedLegGenerator();
    final GeneratorLegONCompounding genON = generator.getONLegGenerator();
    final AnnuityCouponFixedDefinition fixedLeg = AnnuityDefinitionBuilder.couponFixed(genFix.getCurrency(), settlementDate, endFixingPeriodDate, genFix.getPaymentTenor().getPeriod(), 
        genFix.getCalendar(), genFix.getDayCount(), genFix.getBusinessDayConvention(), genFix.isEndOfMonth(), notionalFixed, fixedRate, isPayer, genFix.getStubType(), genFix.getPaymentLag());
    final AnnuityCouponONDefinition onLeg = AnnuityDefinitionBuilder.couponONSimpleCompounded(settlementDate, endFixingPeriodDate, genON.getPaymentTenor().getPeriod(), 
        genON.getPaymentCalendar(), notionalOIS, genON.getONIndex(), genON.getIndexCalendar(), isPayer, genON.getBusinessDayConvention(), genON.isEndOfMonth(), genON.getStubType(), 
        genON.getPaymentLag());
    return new SwapFixedONDefinition(fixedLeg, onLeg);
  }

  /**
   * Builder of OIS swap from financial description (start date and end date, the fixed leg and floating leg notionals can be different).
   * @param settlementDate The annuity settlement or first fixing date.
   * @param endFixingPeriodDate  The end date of the OIS accrual period. Also called the maturity date of the annuity even if the actual payment can take place one or two days later. Not null.
   * @param notionalFixed The notional of the fixed leg.
   * @param notionalOIS The notional of the OIS leg.
   * @param generator The OIS generator. The notional exchange flag is not taken into account.
   * @param fixedRate The rate of the swap fixed leg.
   * @param isPayer The flag indicating if the annuity is paying (true) or receiving (false).
   * @return The swap.
   */
  public static SwapFixedONDefinition from(final ZonedDateTime settlementDate, final ZonedDateTime endFixingPeriodDate, final NotionalProvider notionalFixed, 
      final NotionalProvider notionalOIS, final GeneratorSwapFixedONCompounding generator, final double fixedRate, final boolean isPayer) {
    ArgumentChecker.notNull(settlementDate, "Settlement date");
    ArgumentChecker.notNull(endFixingPeriodDate, "Maturity date");
    ArgumentChecker.notNull(generator, "Swap generator");
    final GeneratorLegFixed genFix = generator.getFixedLegGenerator();
    final GeneratorLegONCompounding genON = generator.getONLegGenerator();
    final AnnuityCouponFixedDefinition fixedLeg = AnnuityDefinitionBuilder.couponFixed(genFix.getCurrency(), settlementDate, endFixingPeriodDate, genFix.getPaymentTenor().getPeriod(), 
        genFix.getCalendar(), genFix.getDayCount(), genFix.getBusinessDayConvention(), genFix.isEndOfMonth(), notionalFixed, fixedRate, isPayer, genFix.getStubType(), genFix.getPaymentLag());
    final AnnuityCouponONDefinition onLeg = AnnuityDefinitionBuilder.couponONSimpleCompounded(settlementDate, endFixingPeriodDate, genON.getPaymentTenor().getPeriod(), 
        genON.getPaymentCalendar(), notionalOIS, genON.getONIndex(), genON.getIndexCalendar(), isPayer, genON.getBusinessDayConvention(), genON.isEndOfMonth(), genON.getStubType(), 
        genON.getPaymentLag());
    return new SwapFixedONDefinition(fixedLeg, onLeg);
  }

  /**
   * The fixed leg of the swap.
   * @return Fixed leg.
   */
  public AnnuityCouponFixedDefinition getFixedLeg() {
    return (AnnuityCouponFixedDefinition) getFirstLeg();
  }

  /**
   * The Ibor leg of the swap.
   * @return Ibor leg.
   */
  public AnnuityCouponONDefinition getOISLeg() {
    return (AnnuityCouponONDefinition) getSecondLeg();
  }

  /**
   * {@inheritDoc}
   * @deprecated Use the method that does not take yield curve names
   */
  @Deprecated
  @SuppressWarnings("unchecked")
  @Override
  public SwapFixedCoupon<Coupon> toDerivative(final ZonedDateTime date, final String... yieldCurveNames) {
    final Annuity<CouponFixed> fixedLeg = this.getFixedLeg().toDerivative(date, yieldCurveNames);
    final Annuity<? extends Coupon> oisLeg = (Annuity<? extends Coupon>) this.getOISLeg().toDerivative(date, yieldCurveNames);
    return new SwapFixedCoupon<>(fixedLeg, (Annuity<Coupon>) oisLeg);
  }

  /**
   * {@inheritDoc}
   * @deprecated Use the method that does not take yield curve names
   */
  @Deprecated
  @SuppressWarnings("unchecked")
  @Override
  public SwapFixedCoupon<Coupon> toDerivative(final ZonedDateTime date, final ZonedDateTimeDoubleTimeSeries[] indexDataTS, final String... yieldCurveNames) {
    ArgumentChecker.notNull(indexDataTS, "index data time series array");
    // ArgumentChecker.isTrue(indexDataTS.length > 0, "index data time series must contain at least one element");
    final Annuity<CouponFixed> fixedLeg = this.getFixedLeg().toDerivative(date, yieldCurveNames);
    final Annuity<? extends Coupon> oisLeg = this.getOISLeg().toDerivative(date, indexDataTS[0], yieldCurveNames);
    return new SwapFixedCoupon<>(fixedLeg, (Annuity<Coupon>) oisLeg);
  }

  @SuppressWarnings("unchecked")
  @Override
  public SwapFixedCoupon<Coupon> toDerivative(final ZonedDateTime date) {
    final Annuity<CouponFixed> fixedLeg = this.getFixedLeg().toDerivative(date);
    final Annuity<? extends Coupon> oisLeg = (Annuity<? extends Coupon>) this.getOISLeg().toDerivative(date);
    return new SwapFixedCoupon<>(fixedLeg, (Annuity<Coupon>) oisLeg);
  }

  @SuppressWarnings("unchecked")
  @Override
  public SwapFixedCoupon<Coupon> toDerivative(final ZonedDateTime date, final ZonedDateTimeDoubleTimeSeries[] indexDataTS) {
    ArgumentChecker.notNull(indexDataTS, "index data time series array");
    // ArgumentChecker.isTrue(indexDataTS.length > 0, "index data time series must contain at least one element");
    final Annuity<CouponFixed> fixedLeg = this.getFixedLeg().toDerivative(date);
    final Annuity<? extends Coupon> oisLeg = this.getOISLeg().toDerivative(date, indexDataTS[0]);
    return new SwapFixedCoupon<>(fixedLeg, (Annuity<Coupon>) oisLeg);
  }
}
