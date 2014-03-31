/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.index;

import org.apache.commons.lang.ObjectUtils;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinitionBuilder;
import com.opengamma.analytics.financial.instrument.payment.CouponDefinition;
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
 * Class with the description of Overnight indexed leg with compounding.
 */
public class GeneratorLegONCompounding extends GeneratorInstrument<GeneratorAttributeIROTC> {

  /**
   * The overnight index.
   */
  private final IndexON _onIndex;
  /**
   * The holiday calendar associated to the index (used to compute the deposit underlying the index).
   */
  private final Calendar _indexCalendar;
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
  private final Calendar _paymentCalendar;
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
   * Constructor of the overnight indexed leg with compounding generator.
   * @param name The generator name.
   * @param onIndex The overnight index on which the coupon are indexed.
   * @param indexCalendar The calendar associated to the index (used to compute fixing periods)
   * @param paymentTenor The leg payment tenor.
   * @param dayCount The leg day count.
   * @param businessDayConvention The leg business day convention.
   * @param currency The leg currency.
   * @param endOfMonth The flag indicating if the end-of-month rule applies.
   * @param spotLag The leg spot lag in days between trade and settlement date (usually 2 or 0).
   * @param paymentCalendar The holiday calendar associated to the payments.
   * @param stubType The leg stub type.
   * @param isExchangeNotional Whether the notional exchanged (start and end).
   * @param paymentLag The payment lag in days.
   */
  public GeneratorLegONCompounding(String name, IndexON onIndex, Calendar indexCalendar, Tenor paymentTenor, DayCount dayCount, BusinessDayConvention businessDayConvention, Currency currency,
      boolean endOfMonth, int spotLag, Calendar paymentCalendar, StubType stubType, boolean isExchangeNotional, int paymentLag) {
    super(name);
    ArgumentChecker.notNull(onIndex, "overnight index");
    ArgumentChecker.notNull(indexCalendar, "calendar associated to the index");
    ArgumentChecker.notNull(dayCount, "day count");
    ArgumentChecker.notNull(businessDayConvention, "business day convention");
    ArgumentChecker.notNull(currency, "currency");
    ArgumentChecker.notNull(paymentCalendar, "payment calendar");
    ArgumentChecker.notNull(stubType, "stub type");
    _onIndex = onIndex;
    _indexCalendar = indexCalendar;
    _paymentTenor = paymentTenor;
    _dayCount = dayCount;
    _businessDayConvention = businessDayConvention;
    _currency = currency;
    _endOfMonth = endOfMonth;
    _spotLag = spotLag;
    _paymentCalendar = paymentCalendar;
    _stubType = stubType;
    _isExchangeNotional = isExchangeNotional;
    _paymentLag = paymentLag;
  }

  /**
   * Returns the leg generator overnight index.
   * @return The index
   */
  public IndexON getONIndex() {
    return _onIndex;
  }

  /**
   * Returns the leg generator index calendar.
   * @return The calendar.
   */
  public Calendar getIndexCalendar() {
    return _indexCalendar;
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
   * Returns the leg generator currency.
   * @return The currency.
   */
  public Currency getCurrency() {
    return _currency;
  }

  /**
   * Returns the leg generator fixing end-of-month rule flag.
   * @return The flag.
   */
  public boolean isEndOfMonth() {
    return _endOfMonth;
  }

  /**
   * Returns the leg generator fixing spot lag.
   * @return The spot lag.
   */
  public int getSpotLag() {
    return _spotLag;
  }

  /**
   * Returns the leg generator payment calendar.
   * @return The calendar
   */
  public Calendar getPaymentCalendar() {
    return _paymentCalendar;
  }

  /**
   * Returns the leg generator stub type.
   * @return The stub type.
   */
  public StubType getStubType() {
    return _stubType;
  }

  /**
   * Returns the leg generator payment exchange of notional flag.
   * @return The flag.
   */
  public boolean isExchangeNotional() {
    return _isExchangeNotional;
  }

  /**
   * Returns the leg generator payment lag.
   * @return The payment lag.
   */
  public int getPaymentLag() {
    return _paymentLag;
  }
  
  /**
   * {@inheritDoc}
   * The effective date is spot+startTenor. The maturity date is effective date + endTenor (all using payment calendar). The ON leg is a payer leg.
   */
  @Override
  public AnnuityDefinition<? extends CouponDefinition> generateInstrument(ZonedDateTime tradeDate, double marketQuote, double notional, GeneratorAttributeIROTC attribute) {
    return generateInstrument(tradeDate, marketQuote, notional, attribute, true);
  }

  /**
   * Generator of a ibor leg instrument with spread.
   * The effective date is spot+startTenor; the maturity date is effective date + endTenor (all using payment calendar).
   * @param date The reference date.
   * @param marketQuote The ibor leg spread.
   * @param notional The notional.
   * @param attribute The attributes.
   * @param isPayer The payer/receiver flag.
   * @return The leg.
   */
  public AnnuityDefinition<? extends CouponDefinition> generateInstrument(ZonedDateTime date, double marketQuote, double notional, GeneratorAttributeIROTC attribute, final boolean isPayer) {
    ArgumentChecker.notNull(attribute, "attribute");
    final ZonedDateTime spotDateLeg = ScheduleCalculator.getAdjustedDate(date, _spotLag, _paymentCalendar);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spotDateLeg, attribute.getStartTenor(), _businessDayConvention, _paymentCalendar, _endOfMonth);
    return generateInstrumentFromEffectiveDate(startDate, marketQuote, notional, attribute.getEndTenor(), isPayer);
  }

  /**
   * Generator of a ibor leg instrument with spread.
   * The effective date is spot+startTenor; the maturity date is effective date + endTenor (all using payment calendar).
   * @param effectiveDate The leg effective date.
   * @param spread The ibor leg spread.
   * @param notional The notional.
   * @param maturityTenor The tenor between effective date and maturity.
   * @param isPayer The payer/receiver flag.
   * @return The leg.
   */
  public AnnuityDefinition<? extends CouponDefinition> generateInstrumentFromEffectiveDate(final ZonedDateTime effectiveDate, final double spread, final double notional, 
      final Tenor maturityTenor, final boolean isPayer) {
    ArgumentChecker.notNull(maturityTenor, "attribute");
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(effectiveDate, maturityTenor);
    if (_isExchangeNotional) {
      return AnnuityDefinitionBuilder.couponONSimpleCompoundedSpreadWithNotional(effectiveDate, maturityDate, _paymentTenor.getPeriod(), _paymentCalendar, notional, spread,
          _onIndex, _indexCalendar, isPayer, _businessDayConvention, _endOfMonth, _stubType, _paymentLag, true, true);
    }
    return AnnuityDefinitionBuilder.couponONSimpleCompoundedSpread(effectiveDate, maturityDate, _paymentTenor.getPeriod(), _paymentCalendar, notional, spread, 
        _onIndex, _indexCalendar, isPayer, _businessDayConvention, isPayer, _stubType, _paymentLag);
  }

  /**
   * Generator of a overnight indexed compounding leg instrument (without spread).
   * The effective date is spot+startTenor; the maturity date is effective date + endTenor (all using payment calendar)
   * @param date The reference date.
   * @param notional The notional.
   * @param attribute The attributes.
   * @param isPayer The payer/receiver flag.
   * @return The leg.
   */
  public AnnuityDefinition<? extends CouponDefinition> generateInstrument(ZonedDateTime date, double notional, GeneratorAttributeIROTC attribute, final boolean isPayer) {
    ArgumentChecker.notNull(attribute, "attribute");
    final ZonedDateTime spotDateLeg = ScheduleCalculator.getAdjustedDate(date, _spotLag, _paymentCalendar);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spotDateLeg, attribute.getStartTenor(), _businessDayConvention, _paymentCalendar, _endOfMonth);
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(startDate, attribute.getEndTenor());
    if (_isExchangeNotional) {
      return AnnuityDefinitionBuilder.couponONSimpleCompoundedWithNotional(startDate, maturityDate, _paymentTenor.getPeriod(), _paymentCalendar, notional, _onIndex, 
          _indexCalendar, isPayer, _businessDayConvention, _endOfMonth, _stubType, _paymentLag, true, true);
    }
    return AnnuityDefinitionBuilder.couponONSimpleCompounded(startDate, maturityDate, _paymentTenor.getPeriod(), _paymentCalendar, notional, _onIndex, 
        _indexCalendar, isPayer, _businessDayConvention, _endOfMonth, _stubType, _paymentLag);
  }

  /**
   * Generator of a overnight indexed compounding leg instrument (without spread).
   * The effective date is spot+startTenor; the maturity date is effective date + endTenor (all using payment calendar)
   * @param effectiveDate The leg effective date.
   * @param notional The notional.
   * @param maturityTenor The tenor between effective date and maturity.
   * @param isPayer The payer/receiver flag.
   * @return The leg.
   */
  public AnnuityDefinition<? extends CouponDefinition> generateInstrumentFromEffectiveDate(final ZonedDateTime effectiveDate, final double notional, 
    final Tenor maturityTenor, final boolean isPayer) {
    ArgumentChecker.notNull(maturityTenor, "attribute");
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(effectiveDate, maturityTenor);
    if (_isExchangeNotional) {
      return AnnuityDefinitionBuilder.couponONSimpleCompoundedWithNotional(effectiveDate, maturityDate, _paymentTenor.getPeriod(), _paymentCalendar, notional, _onIndex, 
          _indexCalendar, isPayer, _businessDayConvention, _endOfMonth, _stubType, _paymentLag, true, true);
    }
    return AnnuityDefinitionBuilder.couponONSimpleCompounded(effectiveDate, maturityDate, _paymentTenor.getPeriod(), _paymentCalendar, notional, _onIndex, 
        _indexCalendar, isPayer, _businessDayConvention, _endOfMonth, _stubType, _paymentLag);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result +  _businessDayConvention.hashCode();
    result = prime * result +  _currency.hashCode();
    result = prime * result + _dayCount.hashCode();
    result = prime * result + (_endOfMonth ? 1231 : 1237);
    result = prime * result +  _onIndex.hashCode();
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
    GeneratorLegONCompounding other = (GeneratorLegONCompounding) obj;
    if (!ObjectUtils.equals(_businessDayConvention, other._businessDayConvention)) {
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
    if (!ObjectUtils.equals(_onIndex, other._onIndex)) {
      return false;
    }
    return true;
  }

}
