/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.swap;

import org.threeten.bp.Period;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.instrument.InstrumentDefinitionVisitor;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityCouponFixedDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityCouponIborDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinitionBuilder;
import com.opengamma.analytics.financial.instrument.index.GeneratorLegFixed;
import com.opengamma.analytics.financial.instrument.index.GeneratorLegIbor;
import com.opengamma.analytics.financial.instrument.index.GeneratorSwapFixedIbor;
import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.analytics.financial.instrument.index.IndexSwap;
import com.opengamma.analytics.financial.instrument.payment.CouponFixedDefinition;
import com.opengamma.analytics.financial.instrument.payment.CouponIborDefinition;
import com.opengamma.analytics.financial.instrument.payment.PaymentDefinition;
import com.opengamma.analytics.financial.interestrate.annuity.derivative.Annuity;
import com.opengamma.analytics.financial.interestrate.payments.derivative.Coupon;
import com.opengamma.analytics.financial.interestrate.payments.derivative.CouponFixed;
import com.opengamma.analytics.financial.interestrate.swap.derivative.SwapFixedCoupon;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.timeseries.precise.zdt.ZonedDateTimeDoubleTimeSeries;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;

/**
 * Class describing a fixed for ibor-like payments swap. Both legs are in the same currency.
 */
public class SwapFixedIborDefinition extends SwapDefinition {

  /**
   * Constructor of the fixed-ibor swap from its two legs. This constructor is intended to be used when there is an initial floating
   * rate defined in the swap contract - the stream of payments on the floating leg then consists of a {@link CouponFixedDefinition} and
   * then a series of {@link CouponIborDefinition}.
   * @param fixedLeg The fixed leg.
   * @param iborLeg The ibor leg.
   */
  public SwapFixedIborDefinition(final AnnuityCouponFixedDefinition fixedLeg, final AnnuityDefinition<? extends PaymentDefinition> iborLeg) {
    super(fixedLeg, iborLeg);
    ArgumentChecker.isTrue(iborLeg instanceof AnnuityCouponIborDefinition, "iborLeg should be of the type AnnuityCouponIborDefinition");
    ArgumentChecker.isTrue(fixedLeg.getCurrency().equals(iborLeg.getCurrency()), "legs should have the same currency");
  }

  /**
   * Constructor of the fixed-ibor swap from its two legs.
   * @param fixedLeg The fixed leg.
   * @param iborLeg The ibor leg.
   */
  public SwapFixedIborDefinition(final AnnuityCouponFixedDefinition fixedLeg, final AnnuityCouponIborDefinition iborLeg) {
    super(fixedLeg, iborLeg);
    ArgumentChecker.isTrue(fixedLeg.getCurrency().equals(iborLeg.getCurrency()), "legs should have the same currency");
  }

  /**
   * Vanilla swap builder from the settlement date, a CMS index and other details of a swap.
   * @param settlementDate The settlement date.
   * @param swapIndex The swap index from which the swap is constructed.
   * @param notional The swap notional
   * @param fixedRate The swap fixed rate.
   * @param isPayer The payer flag of the fixed leg.
   * @param calendar The holiday calendar for the ibor index.
   * @return The vanilla swap.
   */
  public static SwapFixedIborDefinition from(final ZonedDateTime settlementDate, final IndexSwap swapIndex, final double notional, final double fixedRate, final boolean isPayer,
      final Calendar calendar) {
    ArgumentChecker.notNull(swapIndex, "swap index");
    return from(settlementDate, swapIndex.getTenor(), swapIndex.getGenerator(), notional, fixedRate, isPayer);
  }

  /**
   * Vanilla swap builder from the settlement date, a CMS index and other details of a swap.
   * @param settlementDate The settlement date.
   * @param tenor The swap total tenor.
   * @param generator The swap generator.
   * @param notional The swap notional
   * @param fixedRate The swap fixed rate.
   * @param isPayer The payer flag of the fixed leg.
   * @return The vanilla swap.
   */
  public static SwapFixedIborDefinition from(final ZonedDateTime settlementDate, final Period tenor, final GeneratorSwapFixedIbor generator, final double notional, 
      final double fixedRate, final boolean isPayer) {
    ArgumentChecker.notNull(settlementDate, "settlement date");
    ArgumentChecker.notNull(tenor, "Tenor");
    final ZonedDateTime maturityDate = settlementDate.plus(tenor);
    return from(settlementDate, maturityDate, generator, notional, fixedRate, isPayer);
  }

  /**
   * Vanilla swap builder from the settlement date, a swap generator and other details of a swap.
   * @param settlementDate The settlement date.
   * @param maturityDate The swap maturity date.
   * @param generator The swap generator.
   * @param notional The swap notional (identical on both legs)
   * @param fixedRate The swap fixed rate.
   * @param isPayer The payer flag of the fixed leg.
   * @return The vanilla swap.
   */
  public static SwapFixedIborDefinition from(final ZonedDateTime settlementDate, final ZonedDateTime maturityDate, final GeneratorSwapFixedIbor generator, final double notional,
      final double fixedRate, final boolean isPayer) {
    return from(settlementDate, maturityDate, generator, notional, notional, fixedRate, isPayer);
  }

  /**
   * Vanilla swap builder from the settlement date, a swap generator and other details of a swap.
   * @param settlementDate The settlement date.
   * @param maturityDate The swap maturity date.
   * @param generator The swap generator.
   * @param notionalFixed The fixed leg notional.
   * @param notionalIbor The ibor leg notional.
   * @param fixedRate The swap fixed rate.
   * @param isPayer The payer flag of the fixed leg.
   * @return The vanilla swap.
   */
  public static SwapFixedIborDefinition from(final ZonedDateTime settlementDate, final ZonedDateTime maturityDate, final GeneratorSwapFixedIbor generator, final double notionalFixed,
      final double notionalIbor, final double fixedRate, final boolean isPayer) {
    ArgumentChecker.notNull(settlementDate, "Settlement date");
    ArgumentChecker.notNull(maturityDate, "Maturity date");
    ArgumentChecker.notNull(generator, "Swap generator");
    final GeneratorLegFixed genFix = generator.getFixedLegGenerator();
    final GeneratorLegIbor genIbor = generator.getIborLegGenerator();
    final AnnuityCouponFixedDefinition fixedLeg = AnnuityDefinitionBuilder.couponFixed(genFix.getCurrency(), settlementDate, maturityDate, genFix.getPaymentTenor().getPeriod(), 
        genFix.getCalendar(), genFix.getDayCount(), genFix.getBusinessDayConvention(), genFix.isEndOfMonth(), notionalFixed, fixedRate, isPayer, genFix.getStubType(), genFix.getPaymentLag());
    final AnnuityCouponIborDefinition iborLeg = AnnuityDefinitionBuilder.couponIbor(settlementDate, maturityDate, genIbor.getPaymentTenor().getPeriod(), notionalIbor, 
        genIbor.getIborIndex(), !isPayer, genIbor.getDayCount(), genIbor.getBusinessDayConvention(), genIbor.isEndOfMonth(), genIbor.getPaymentCalendar(), genIbor.getFixingCalendar(), 
        genIbor.getDepositCalendar(), genIbor.getStubType(), genIbor.getPaymentLag());
    return new SwapFixedIborDefinition(fixedLeg, iborLeg);
  }

  /**
   * Vanilla swap builder from the all the details on the fixed and ibor leg. The currency is the currency of the Ibor index.
   * @param settlementDate The settlement date.
   * @param maturityDate The swap maturity date.
   * @param fixedLegPeriod The payment period for the fixed leg.
   * @param fixedLegDayCount The fixed leg day count.
   * @param fixedLegBusinessDayConvention The fixed leg business day convention.
   * @param fixedLegEOM The fixed leg end-of-month rule application.
   * @param fixedLegNotional The fixed leg notional.
   * @param fixedLegRate The fixed leg rate.
   * @param iborLegPeriod The Ibor leg payment period.
   * @param iborLegDayCount The Ibor leg day count convention.
   * @param iborLegBusinessDayConvention The Ibor leg business day convention.
   * @param iborLegEOM The Ibor leg end-of-month.
   * @param iborLegNotional The Ibor leg notional.
   * @param iborIndex The Ibor index.
   * @param isPayer The payer flag for the fixed leg.
   * @param calendar The calendar.
   * @return The swap.
   */
  public static SwapFixedIborDefinition from(final ZonedDateTime settlementDate, final ZonedDateTime maturityDate, final Period fixedLegPeriod, final DayCount fixedLegDayCount,
      final BusinessDayConvention fixedLegBusinessDayConvention, final boolean fixedLegEOM, final double fixedLegNotional, final double fixedLegRate, final Period iborLegPeriod,
      final DayCount iborLegDayCount, final BusinessDayConvention iborLegBusinessDayConvention, final boolean iborLegEOM, final double iborLegNotional,
      final IborIndex iborIndex, final boolean isPayer, final Calendar calendar) {
    ArgumentChecker.notNull(iborIndex, "Ibor index");
    final AnnuityCouponFixedDefinition fixedLeg = AnnuityCouponFixedDefinition.from(iborIndex.getCurrency(), settlementDate, maturityDate, fixedLegPeriod, calendar, fixedLegDayCount,
        fixedLegBusinessDayConvention, fixedLegEOM, fixedLegNotional, fixedLegRate, isPayer);
    final AnnuityCouponIborDefinition iborLeg = AnnuityCouponIborDefinition.from(settlementDate, maturityDate, iborLegPeriod, iborLegNotional, iborIndex, !isPayer, iborLegBusinessDayConvention,
        iborLegEOM, iborLegDayCount, calendar);
    return new SwapFixedIborDefinition(fixedLeg, iborLeg);
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
  public AnnuityCouponIborDefinition getIborLeg() {
    return (AnnuityCouponIborDefinition) getSecondLeg();
  }

  /**
   * Return the currency of the swap.
   * @return The currency.
   */
  public Currency getCurrency() {
    return getFixedLeg().getCurrency();
  }

  /**
   * Creates a new swap. The coupon in the new swap have start accrual date after or on the given date.
   * @param trimDate The date.
   * @return The trimmed swap.
   */
  public SwapFixedIborDefinition trimStart(final ZonedDateTime trimDate) {
    final AnnuityCouponFixedDefinition fixedLegTrimmed = getFixedLeg().trimStart(trimDate);
    final AnnuityCouponIborDefinition iborLegTrimmed = getIborLeg().trimStart(trimDate);
    return new SwapFixedIborDefinition(fixedLegTrimmed, iborLegTrimmed);
  }

  /**
   * {@inheritDoc}
   * @deprecated Use the method that does not take yield curve names
   */
  @Deprecated
  @SuppressWarnings("unchecked")
  @Override
  public SwapFixedCoupon<Coupon> toDerivative(final ZonedDateTime date, final String... yieldCurveNames) {
    final Annuity<CouponFixed> fixedLeg = getFixedLeg().toDerivative(date, yieldCurveNames);
    final Annuity<? extends Coupon> iborLeg = getIborLeg().toDerivative(date, yieldCurveNames);
    return new SwapFixedCoupon<>(fixedLeg, (Annuity<Coupon>) iborLeg);
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
    final Annuity<CouponFixed> fixedLeg = getFixedLeg().toDerivative(date, yieldCurveNames);
    final Annuity<? extends Coupon> iborLeg = getIborLeg().toDerivative(date, indexDataTS[0], yieldCurveNames);
    return new SwapFixedCoupon<>(fixedLeg, (Annuity<Coupon>) iborLeg);
  }

  @SuppressWarnings("unchecked")
  @Override
  public SwapFixedCoupon<Coupon> toDerivative(final ZonedDateTime date) {
    final Annuity<CouponFixed> fixedLeg = getFixedLeg().toDerivative(date);
    final Annuity<? extends Coupon> iborLeg = getIborLeg().toDerivative(date);
    return new SwapFixedCoupon<>(fixedLeg, (Annuity<Coupon>) iborLeg);
  }

  @SuppressWarnings("unchecked")
  @Override
  public SwapFixedCoupon<Coupon> toDerivative(final ZonedDateTime date, final ZonedDateTimeDoubleTimeSeries[] indexDataTS) {
    ArgumentChecker.notNull(indexDataTS, "index data time series array");
    final Annuity<CouponFixed> fixedLeg = getFixedLeg().toDerivative(date);
    final Annuity<? extends Coupon> iborLeg = getIborLeg().toDerivative(date, indexDataTS[0]);
    return new SwapFixedCoupon<>(fixedLeg, (Annuity<Coupon>) iborLeg);
  }

  @Override
  public <U, V> V accept(final InstrumentDefinitionVisitor<U, V> visitor, final U data) {
    ArgumentChecker.notNull(visitor, "visitor");
    return visitor.visitSwapFixedIborDefinition(this, data);
  }

  @Override
  public <V> V accept(final InstrumentDefinitionVisitor<?, V> visitor) {
    ArgumentChecker.notNull(visitor, "visitor");
    return visitor.visitSwapFixedIborDefinition(this);
  }

}
