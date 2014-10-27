/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.interestrate;

import org.threeten.bp.ZonedDateTime;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.analytics.financial.instrument.payment.CouponFixedAccruedCompoundingDefinition;
import com.opengamma.analytics.financial.instrument.payment.CouponFixedCompoundingDefinition;
import com.opengamma.analytics.financial.instrument.payment.CouponFixedDefinition;
import com.opengamma.analytics.financial.instrument.payment.CouponIborDefinition;
import com.opengamma.analytics.util.time.TimeCalculator;
import com.opengamma.timeseries.DoubleTimeSeries;
import com.opengamma.util.money.CurrencyAmount;

/**
 * 
 */
public class CouponAccruedInterestVisitor {
  
  public CurrencyAmount visitCouponFixedDefinition(final CouponFixedDefinition payment, final ZonedDateTime date) {
    if (date.isAfter(payment.getPaymentDate()) || !date.isAfter(payment.getAccrualStartDate())) {
      CurrencyAmount.of(payment.getCurrency(), 0.0);
    }
    ZonedDateTime referenceDate = date.isAfter(payment.getAccrualEndDate()) ? payment.getAccrualEndDate() : date;
    double yearFraction = TimeCalculator.getTimeBetween(payment.getAccrualStartDate(), referenceDate);
    return CurrencyAmount.of(payment.getCurrency(), payment.getNotional() * payment.getRate() * yearFraction);
  }

  public CurrencyAmount visitCouponFixedAccruedCompoundingDefinition(
      final CouponFixedAccruedCompoundingDefinition payment, final ZonedDateTime date) {
    if (date.isAfter(payment.getPaymentDate()) || !date.isAfter(payment.getAccrualStartDate())) {
      CurrencyAmount.of(payment.getCurrency(), 0.0);
    }
    ZonedDateTime referenceDate = date.isAfter(payment.getAccrualEndDate()) ? payment.getAccrualEndDate() : date;
    double yearFraction = TimeCalculator.getTimeBetween(payment.getAccrualStartDate(), referenceDate);
    return CurrencyAmount.of(payment.getCurrency(),
        payment.getNotional() * Math.pow(1 + payment.getRate(), yearFraction));
  }

  public CurrencyAmount visitCouponFixedCompoundingDefinition(final CouponFixedCompoundingDefinition payment,
      final ZonedDateTime date) {
    if (date.isAfter(payment.getPaymentDate()) || !date.isAfter(payment.getAccrualStartDate())) {
      CurrencyAmount.of(payment.getCurrency(), 0.0);
    }
    ZonedDateTime referenceDate = date.isAfter(payment.getAccrualEndDate()) ? payment.getAccrualEndDate() : date;
    double notionalAccrued = payment.getNotional();
    double[] accFactors = payment.getPaymentAccrualFactors();
    double accSum = 0;
    double yearFraction = TimeCalculator.getTimeBetween(payment.getAccrualStartDate(), referenceDate);
    int i = 0;
    while (accSum < yearFraction) {
      notionalAccrued *= 1.0 + accFactors[i] * payment.getRate();
      accSum += accFactors[i];
      ++i;
    }
    notionalAccrued *= 1.0 + (yearFraction - accSum) * payment.getRate();

    return CurrencyAmount.of(payment.getCurrency(), notionalAccrued);
  }

  //  Undefined for xccy's
  //  public CurrencyAmount visitCouponFixedFxResetDefinition(final CouponFixedCompoundingDefinition payment,
  //      final ZonedDateTime date) {
  //    
  //  }

  public CurrencyAmount visitCouponIborDefinition(final CouponIborDefinition payment,
      final DoubleTimeSeries<ZonedDateTime> indexFixingTimeSeries, final ZonedDateTime date) {
    if (date.isAfter(payment.getPaymentDate()) || !date.isAfter(payment.getAccrualStartDate())) {
      CurrencyAmount.of(payment.getCurrency(), 0.0);
    }
    ZonedDateTime referenceDate = date.isAfter(payment.getAccrualEndDate()) ? payment.getAccrualEndDate() : date;
    final Double fixedRate = indexFixingTimeSeries.getValue(payment.getFixingDate());
    if (fixedRate == null) { // index rate should be fixed if the reference date is in the accrual period
      throw new OpenGammaRuntimeException("Could not get fixing value for date " + payment.getFixingDate());
    }
    double yearFraction = TimeCalculator.getTimeBetween(payment.getAccrualStartDate(), referenceDate);
    return CurrencyAmount.of(payment.getCurrency(), payment.getNotional() * fixedRate * yearFraction);
  }

}
