/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.index;

import org.apache.commons.lang.ObjectUtils;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.instrument.annuity.AnnuityCouponFixedDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinitionBuilder;
import com.opengamma.analytics.financial.schedule.ScheduleCalculator;
import com.opengamma.analytics.util.time.TenorUtils;
import com.opengamma.financial.convention.StubType;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Tenor;

/**
 * Class with the description of swap characteristics.
 */
public class GeneratorLegFixed extends GeneratorInstrument<GeneratorAttributeIROTC> {

  /**
   * The leg payment tenor.
   */
  private final Tenor _paymentTenor;
  /**
   * The leg day count.
   */
  private final DayCount _dayCount;
  /**
   * The leg business day convention.
   */
  private final BusinessDayConvention _businessDayConvention;
  /**
   * The leg currency.
   */
  private final Currency _currency;
  /**
   * The flag indicating if the end-of-month rule applies.
   */
  private final boolean _endOfMonth;
  /**
   * The leg spot lag in days between trade and settlement date (usually 2 or 0).
   */
  private final int _spotLag;
  /**
   * The holiday calendar associated to the payments.
   */
  private final Calendar _calendar;
  /**
   * The leg stub type.
   */
  private final StubType _stubType;
  /**
   * Whether the notional exchanged (start and end).
   */
  private final boolean _isExchangeNotional;
  /**
   * The payment lag in days.
   */
  private final int _paymentLag;
  
  
  /**
   * Constructor of the fixed leg generator.
   * @param name The generator name.
   * @param paymentTenor The leg payment tenor.
   * @param dayCount The leg day count.
   * @param businessDayConvention The leg business day convention.
   * @param currency The leg currency.
   * @param endOfMonth The flag indicating if the end-of-month rule applies.
   * @param spotLag The leg spot lag in days between trade and settlement date (usually 2 or 0).
   * @param calendar The holiday calendar associated to the payments.
   * @param stubType The leg stub type.
   * @param isExchangeNotional Whether the notional exchanged (start and end).
   * @param paymentLag The payment lag in days.
   */
  public GeneratorLegFixed(String name, Tenor paymentTenor, DayCount dayCount, BusinessDayConvention businessDayConvention, Currency currency, boolean endOfMonth, 
      int spotLag, Calendar calendar, StubType stubType, boolean isExchangeNotional, int paymentLag) {
    super(name);
    ArgumentChecker.notNull(paymentTenor, "payment tenor");
    ArgumentChecker.notNull(dayCount, "day count");
    ArgumentChecker.notNull(businessDayConvention, "business day convention");
    ArgumentChecker.notNull(currency, "currency");
    ArgumentChecker.notNull(calendar, "calendar");
    ArgumentChecker.notNull(stubType, "stub type");
    _paymentTenor = paymentTenor;
    _dayCount = dayCount;
    _businessDayConvention = businessDayConvention;
    _currency = currency;
    _endOfMonth = endOfMonth;
    _spotLag = spotLag;
    _calendar = calendar;
    _stubType = stubType;
    _isExchangeNotional = isExchangeNotional;
    _paymentLag = paymentLag;
  }

  /**
   * Returns the leg generator currency.
   * @return The currency.
   */
  public Currency getCurrency() {
    return _currency;
  }

  /**
   * Returns the leg generator stub type.
   * @return The stub type.
   */
  public StubType getStubType() {
    return _stubType;
  }

  /**
   * Returns the leg generator exchange notion flag.
   * @return The flag.
   */
  public boolean isExchangeNotional() {
    return _isExchangeNotional;
  }

  /**
   * Returns the leg generator payment lag.
   * @return The lag.
   */
  public int getPaymentLag() {
    return _paymentLag;
  }

  /**
   * Returns the leg generator payment tenor.
   * @return The tenor.
   */
  public Tenor getPaymentTenor() {
    return _paymentTenor;
  }

  /**
   * Returns the leg generator day count.
   * @return The day count.
   */
  public DayCount getDayCount() {
    return _dayCount;
  }

  /**
   * Returns the leg generator business day convention.
   * @return The business day convention.
   */
  public BusinessDayConvention getBusinessDayConvention() {
    return _businessDayConvention;
  }

  /**
   * Returns the leg generator end-of-month flag.
   * @return The flag.
   */
  public boolean isEndOfMonth() {
    return _endOfMonth;
  }

  /**
   * Returns the leg generator spot lag.
   * @return The  spot lag.
   */
  public int getSpotLag() {
    return _spotLag;
  }

  /**
   * Returns the leg generator calendar.
   * @return The calendar.
   */
  public Calendar getCalendar() {
    return _calendar;
  }

  /**
   * {@inheritDoc}
   * The effective date is spot+startTenor. The maturity date is effective date + endTenor. The fixed leg is a payer leg.
   */
  @Override
  public AnnuityCouponFixedDefinition generateInstrument(ZonedDateTime date, double marketQuote, double notional, GeneratorAttributeIROTC attribute) {
    return generateInstrument(date, marketQuote, notional, attribute, true);
  }
  
  /**
   * Generator of a fixed leg instrument.
   * The effective date is spot+startTenor. The maturity date is effective date + endTenor.
   * @param tradeDate The reference date.
   * @param marketQuote The fixed leg coupon.
   * @param notional The notional.
   * @param attribute The attributes.
   * @param isPayer The payer/receiver flag.
   * @return The leg.
   */
  public AnnuityCouponFixedDefinition generateInstrument(ZonedDateTime tradeDate, double marketQuote, double notional, GeneratorAttributeIROTC attribute, final boolean isPayer) {
    ArgumentChecker.notNull(attribute, "attribute");
    final ZonedDateTime spotDateLeg = ScheduleCalculator.getAdjustedDate(tradeDate, _spotLag, _calendar);
    final ZonedDateTime effectiveDate = ScheduleCalculator.getAdjustedDate(spotDateLeg, attribute.getStartTenor(), _businessDayConvention, _calendar, _endOfMonth);
    return generateInstrumentFromEffectiveDate(effectiveDate, marketQuote, notional, attribute.getEndTenor(), isPayer);
  }
  
  /**
   * Generator of a fixed leg instrument.
   * The maturity date is effective date + maturityTenor.
   * @param effectiveDate The leg effective date.
   * @param marketQuote The fixed leg coupon.
   * @param notional The notional.
   * @param maturityTenor The tenor between effective date and maturity.
   * @param isPayer The payer/receiver flag.
   * @return The leg.
   */
  public AnnuityCouponFixedDefinition generateInstrumentFromEffectiveDate(ZonedDateTime effectiveDate, double marketQuote, double notional, final Tenor maturityTenor, 
      final boolean isPayer) {
    ArgumentChecker.notNull(maturityTenor, "attribute");
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(effectiveDate, maturityTenor);
    if (_isExchangeNotional) {
      return AnnuityDefinitionBuilder.couponFixedWithNotional(_currency, effectiveDate, maturityDate, _paymentTenor.getPeriod(), _calendar, _dayCount, _businessDayConvention, 
          _endOfMonth, notional, marketQuote, isPayer, _stubType, _paymentLag, true, true);
    }
    return AnnuityDefinitionBuilder.couponFixed(_currency, effectiveDate, maturityDate, _paymentTenor.getPeriod(), _calendar, _dayCount, _businessDayConvention, 
        _endOfMonth, notional, marketQuote, isPayer, _stubType, _paymentLag);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result +  _businessDayConvention.hashCode();
    result = prime * result +  _calendar.hashCode();
    result = prime * result +  _currency.hashCode();
    result = prime * result +  _dayCount.hashCode();
    result = prime * result + (_endOfMonth ? 1231 : 1237);
    result = prime * result + (_isExchangeNotional ? 1231 : 1237);
    result = prime * result + _paymentLag;
    result = prime * result +  _paymentTenor.hashCode();
    result = prime * result + _spotLag;
    result = prime * result +  _stubType.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    GeneratorLegFixed other = (GeneratorLegFixed) obj;
    if (!ObjectUtils.equals(_businessDayConvention, other._businessDayConvention)) {
      return false;
    }
    if (!ObjectUtils.equals(_calendar, other._calendar)) {
      return false;
    }
    if (!ObjectUtils.equals(_currency, other._currency)) {
      return false;
    }
    if (!ObjectUtils.equals(_dayCount, other._dayCount)) {
      return false;
    }
    if (_endOfMonth != other._endOfMonth) {
      return false;
    }
    if (_isExchangeNotional != other._isExchangeNotional) {
      return false;
    }
    if (_paymentLag != other._paymentLag) {
      return false;
    }
    if (!ObjectUtils.equals(_paymentTenor, other._paymentTenor)) {
      return false;
    }
    if (_spotLag != other._spotLag) {
      return false;
    }
    if (_stubType != other._stubType) {
      return false;
    }
    return true;
  }

}
