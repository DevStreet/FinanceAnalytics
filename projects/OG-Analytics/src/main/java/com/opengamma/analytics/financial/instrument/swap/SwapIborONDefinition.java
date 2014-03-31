/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.swap;

import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.instrument.annuity.AnnuityCouponIborSpreadDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityCouponONDefinition;
import com.opengamma.analytics.financial.interestrate.annuity.derivative.Annuity;
import com.opengamma.analytics.financial.interestrate.payments.derivative.Coupon;
import com.opengamma.analytics.financial.interestrate.swap.derivative.Swap;
import com.opengamma.timeseries.precise.zdt.ZonedDateTimeDoubleTimeSeries;
import com.opengamma.util.ArgumentChecker;

/**
 * Class describing an Ibor for overnight swap. Both legs are in the same currency.
 * The payment dates on the ibor leg a slightly different from the FixedIbor swap due to the lag in payment at the end of each coupon.
 * @deprecated Use the generic SwapDefinition
 */
@Deprecated
public class SwapIborONDefinition extends SwapDefinition {

  /**
   * Constructor of the fixed-OIS swap from its two legs.
   * @param iborLeg The Ibor leg.
   * @param oisLeg The OIS leg.
   */
  public SwapIborONDefinition(final AnnuityCouponIborSpreadDefinition iborLeg, final AnnuityCouponONDefinition oisLeg) {
    super(iborLeg, oisLeg);
    ArgumentChecker.isTrue(iborLeg.getCurrency() == oisLeg.getCurrency(), "Legs should have the same currency");
  }

  /**
   * The Ibor leg of the swap.
   * @return The leg.
   */
  public AnnuityCouponIborSpreadDefinition getIborLeg() {
    return (AnnuityCouponIborSpreadDefinition) getFirstLeg();
  }

  /**
   * The ON leg of the swap.
   * @return The leg.
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
  public Swap<Coupon, Coupon> toDerivative(final ZonedDateTime date, final String... yieldCurveNames) {
    // Curves should be: discounting, ibor, ois
    final Annuity<? extends Coupon> iborLeg = this.getIborLeg().toDerivative(date, yieldCurveNames);
    final Annuity<? extends Coupon> oisLeg = (Annuity<? extends Coupon>) this.getOISLeg().toDerivative(date, new String[] {yieldCurveNames[0], yieldCurveNames[2] });
    return new Swap<>((Annuity<Coupon>) iborLeg, (Annuity<Coupon>) oisLeg);
  }

  /**
   * {@inheritDoc}
   * @deprecated Use the method that does not take yield curve names
   */
  @Deprecated
  @SuppressWarnings("unchecked")
  @Override
  public Swap<Coupon, Coupon> toDerivative(final ZonedDateTime date, final ZonedDateTimeDoubleTimeSeries[] indexDataTS, final String... yieldCurveNames) {
    // Curves should be: discounting, ibor, ois
    ArgumentChecker.notNull(indexDataTS, "index data time series array");
    ArgumentChecker.isTrue(indexDataTS.length > 1, "index data time series must contain at least two elements");
    final Annuity<? extends Coupon> iborLeg = this.getIborLeg().toDerivative(date, indexDataTS[0], yieldCurveNames);
    final Annuity<? extends Coupon> oisLeg = this.getOISLeg().toDerivative(date, indexDataTS[1], new String[] {yieldCurveNames[0], yieldCurveNames[2] });
    return new Swap<>((Annuity<Coupon>) iborLeg, (Annuity<Coupon>) oisLeg);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Swap<Coupon, Coupon> toDerivative(final ZonedDateTime date) {
    final Annuity<? extends Coupon> iborLeg = this.getIborLeg().toDerivative(date);
    final Annuity<? extends Coupon> oisLeg = (Annuity<? extends Coupon>) this.getOISLeg().toDerivative(date);
    return new Swap<>((Annuity<Coupon>) iborLeg, (Annuity<Coupon>) oisLeg);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Swap<Coupon, Coupon> toDerivative(final ZonedDateTime date, final ZonedDateTimeDoubleTimeSeries[] indexDataTS) {
    ArgumentChecker.notNull(indexDataTS, "index data time series array");
    ArgumentChecker.isTrue(indexDataTS.length > 1, "index data time series must contain at least two elements");
    final Annuity<? extends Coupon> iborLeg = this.getIborLeg().toDerivative(date, indexDataTS[0]);
    final Annuity<? extends Coupon> oisLeg = this.getOISLeg().toDerivative(date, indexDataTS[1]);
    return new Swap<>((Annuity<Coupon>) iborLeg, (Annuity<Coupon>) oisLeg);
  }
}
