/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.index;

import org.apache.commons.lang.ObjectUtils;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinition;
import com.opengamma.analytics.financial.instrument.annuity.AnnuityDefinitionBuilder;
import com.opengamma.analytics.financial.instrument.payment.CouponDefinition;
import com.opengamma.analytics.financial.interestrate.CompoundingType;
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
 * Class with the description of Ibor-indexed leg conventions.
 */
public class GeneratorLegIborCompounding extends GeneratorInstrument<GeneratorAttributeIROTC> {

  /**
   * The ibor index.
   */
  private final IborIndex _iborIndex;
  /**
   * The holiday calendar associated to the index (used to compute the deposit underlying the index).
   */
  private final Calendar _depositCalendar;
  /**
   * The holiday calendar associated to the index (used to compute the fixing date).
   */
  private final Calendar _fixingCalendar;
  /**
   * The leg payment tenor.
   */
  private final Tenor _paymentTenor;
  /**
   * The compounding type.
   */
  private CompoundingType _compoundingType;
  /**
   * The composition tenor.
   * This is the tenor of the sub-periods compounded into the payment tenor.
   */
  private Tenor _compositionTenor;
  /**
   * The stub type for the composition.
   */
  private StubType _stubTypeCompound;
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
   * Constructor of the ibor leg generator.
   * @param name The generator name.
   * @param iborIndex The underlying Ibor index.
   * @param depositCalendar The holiday calendar associated to the index (used to compute the deposit underlying the index).
   * @param fixingCalendar The holiday calendar associated to the index (used to compute the fixing date).
   * @param paymentTenor The leg payment tenor.
   * @param compoundingType The compounding type.
   * @param compositionTenor The composition tenor, i.e. the sub-period length on which the composition is based.
   * @param stubTypeCompound The stub type for the compositions sub-periods.
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
  public GeneratorLegIborCompounding(String name, IborIndex iborIndex, Calendar depositCalendar, Calendar fixingCalendar, Tenor paymentTenor, 
      CompoundingType compoundingType, Tenor compositionTenor, StubType stubTypeCompound, DayCount dayCount, BusinessDayConvention businessDayConvention, 
      Currency currency, boolean endOfMonth, int spotLag, Calendar paymentCalendar, StubType stubType, boolean isExchangeNotional, int paymentLag) {
    super(name);
    ArgumentChecker.notNull(iborIndex, "ibor index");
    ArgumentChecker.notNull(depositCalendar, "depositCalendar");
    ArgumentChecker.notNull(fixingCalendar, "fixing calendar");
    ArgumentChecker.notNull(compoundingType, "compounding type");
    ArgumentChecker.notNull(compositionTenor, "compopsotion tenor");
    ArgumentChecker.notNull(stubTypeCompound, "stub type of the composition sub-periods");
    ArgumentChecker.notNull(dayCount, "day count");
    ArgumentChecker.notNull(businessDayConvention, "business day convention");
    ArgumentChecker.notNull(currency, "currency");
    ArgumentChecker.notNull(paymentCalendar, "payment calendar");
    ArgumentChecker.notNull(stubType, "stub type");
    _iborIndex = iborIndex;
    _depositCalendar = depositCalendar;
    _fixingCalendar = fixingCalendar;
    _paymentTenor = paymentTenor;
    _compoundingType = compoundingType;
    _compositionTenor = compositionTenor;
    _stubTypeCompound = stubTypeCompound;
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
   * Returns the leg generator Ibor index.
   * @return The index
   */
  public IborIndex getIborIndex() {
    return _iborIndex;
  }

  /**
   * Returns the leg generator deposit calendar.
   * @return The calendar
   */
  public Calendar getDepositCalendar() {
    return _depositCalendar;
  }

  /**
   * Returns the leg generator fixing calendar.
   * @return The calendar
   */
  public Calendar getFixingCalendar() {
    return _fixingCalendar;
  }

  /**
   * Returns the leg generator payment tenor.
   * @return The tenor.
   */
  public Tenor getPaymentTenor() {
    return _paymentTenor;
  }

  /**
   * Returns the leg generator compounding type.
   * @return The tenor.
   */
  public CompoundingType getCompoundingType() {
    return _compoundingType;
  }

  /**
   * Returns the leg generator composition tenor.
   * @return The tenor.
   */
  public Tenor getCompositionTenor() {
    return _compositionTenor;
  }

  /**
   * Returns the leg generator stub type for the composition sub-period.
   * @return The tenor.
   */
  public StubType getStubTypeCompound() {
    return _stubTypeCompound;
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
   * The effective date is spot+startTenor. The maturity date is effective date + endTenor (all using payment calendar). The ibor leg is a payer leg.
   */
  @Override
  public AnnuityDefinition<? extends CouponDefinition> generateInstrument(ZonedDateTime tradeDate, double marketQuote, double notional, GeneratorAttributeIROTC attribute) {
    return generateInstrument(tradeDate, marketQuote, notional, attribute, true);
  }

  /**
   * Generator of a ibor leg instrument with spread.
   * The effective date is spot+startTenor; the maturity date is effective date + endTenor (all using payment calendar).
   * @param tradeDate The reference date.
   * @param marketQuote The ibor leg spread.
   * @param notional The notional.
   * @param attribute The attributes.
   * @param isPayer The payer/receiver flag.
   * @return The leg.
   */
  public AnnuityDefinition<? extends CouponDefinition> generateInstrument(ZonedDateTime tradeDate, double marketQuote, double notional, GeneratorAttributeIROTC attribute, final boolean isPayer) {
    ArgumentChecker.notNull(attribute, "attribute");
    final ZonedDateTime spotDateLeg = ScheduleCalculator.getAdjustedDate(tradeDate, _spotLag, _paymentCalendar);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spotDateLeg, attribute.getStartTenor(), _businessDayConvention, _paymentCalendar, _endOfMonth);
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(startDate, attribute.getEndTenor());
    if (_isExchangeNotional) {
      throw new OpenGammaRuntimeException("Exchange of notional not supported for legs Ibor compounding legs.");
    }
    // TODO: [PLAT-????] Payment lag not implemented for couponIborCompounding
    if (_compoundingType.equals(CompoundingType.COMPOUNDING)) {
      return AnnuityDefinitionBuilder.couponIborCompoundingSpread(startDate, maturityDate, _paymentTenor.getPeriod(), notional, marketQuote, _iborIndex, 
          _stubTypeCompound, isPayer, _businessDayConvention, _endOfMonth, _paymentCalendar, _fixingCalendar, _depositCalendar, _stubType); 
    } else if (_compoundingType.equals(CompoundingType.FLAT_COMPOUNDING)) {
      return AnnuityDefinitionBuilder.couponIborCompoundingFlatSpread(startDate, maturityDate, _paymentTenor.getPeriod(), notional, marketQuote, _iborIndex, 
          _stubTypeCompound, isPayer, _businessDayConvention, _endOfMonth, _paymentCalendar, _fixingCalendar, _depositCalendar, _stubType); 
    }
    throw new OpenGammaRuntimeException("Compounding type " + _compoundingType + " not supported.");
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
      throw new OpenGammaRuntimeException("Exchange of notional not supported for legs Ibor compounding legs.");
    }
    // TODO: [PLAT-????] Payment lag not implemented for couponIborCompounding
    if (_compoundingType.equals(CompoundingType.COMPOUNDING)) {
      return AnnuityDefinitionBuilder.couponIborCompoundingSpread(effectiveDate, maturityDate, _paymentTenor.getPeriod(), notional, spread, _iborIndex, 
          _stubTypeCompound, isPayer, _businessDayConvention, _endOfMonth, _paymentCalendar, _fixingCalendar, _depositCalendar, _stubType); 
    } else if (_compoundingType.equals(CompoundingType.FLAT_COMPOUNDING)) {
      return AnnuityDefinitionBuilder.couponIborCompoundingFlatSpread(effectiveDate, maturityDate, _paymentTenor.getPeriod(), notional, spread, _iborIndex, 
          _stubTypeCompound, isPayer, _businessDayConvention, _endOfMonth, _paymentCalendar, _fixingCalendar, _depositCalendar, _stubType); 
    }
    throw new OpenGammaRuntimeException("Compounding type " + _compoundingType + " not supported.");
  }

  /**
   * Generator of a ibor leg instrument (without spread).
   * The effective date is spot+startTenor; the maturity date is effective date + endTenor (all using payment calendar)
   * @param tradeDate The reference date.
   * @param notional The notional.
   * @param attribute The attributes.
   * @param isPayer The payer/receiver flag.
   * @return The leg.
   */
  public AnnuityDefinition<? extends CouponDefinition> generateInstrument(ZonedDateTime tradeDate, double notional, GeneratorAttributeIROTC attribute, final boolean isPayer) {
    ArgumentChecker.notNull(attribute, "attribute");
    final ZonedDateTime spotDateLeg = ScheduleCalculator.getAdjustedDate(tradeDate, _spotLag, _paymentCalendar);
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spotDateLeg, attribute.getStartTenor(), _businessDayConvention, _paymentCalendar, _endOfMonth);
    final ZonedDateTime maturityDate = TenorUtils.adjustDateByTenor(startDate, attribute.getEndTenor());
    if (_isExchangeNotional) {
      throw new OpenGammaRuntimeException("Exchange of notional not supported for legs Ibor compounding legs.");
    }
    return AnnuityDefinitionBuilder.couponIborCompounding(startDate, maturityDate, _paymentTenor.getPeriod(), notional, _iborIndex, 
          _stubTypeCompound, isPayer, _businessDayConvention, _endOfMonth, _paymentCalendar, _fixingCalendar, _depositCalendar, _stubType); 
  }

  /**
   * Generator of a ibor leg instrument (without spread).
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
      throw new OpenGammaRuntimeException("Exchange of notional not supported for legs Ibor compounding legs.");
    }
    return AnnuityDefinitionBuilder.couponIborCompounding(effectiveDate, maturityDate, _paymentTenor.getPeriod(), notional, _iborIndex, 
          _stubTypeCompound, isPayer, _businessDayConvention, _endOfMonth, _paymentCalendar, _fixingCalendar, _depositCalendar, _stubType); 
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result +  _businessDayConvention.hashCode();
    result = prime * result +  _currency.hashCode();
    result = prime * result + _dayCount.hashCode();
    result = prime * result + (_endOfMonth ? 1231 : 1237);
    result = prime * result +  _iborIndex.hashCode();
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
    GeneratorLegIborCompounding other = (GeneratorLegIborCompounding) obj;
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
    if (!ObjectUtils.equals(_compositionTenor, other._compositionTenor)) {
      return false;
    }
    if (!ObjectUtils.equals(_compoundingType, other._compoundingType)) {
      return false;
    }
    if (_spotLag != other._spotLag) {
      return false;
    }
    if (_stubType != other._stubType) {
      return false;
    }
    if (_stubTypeCompound != other._stubTypeCompound) {
      return false;
    }
    if (!ObjectUtils.equals(_iborIndex, other._iborIndex)) {
      return false;
    }
    return true;
  }

}
