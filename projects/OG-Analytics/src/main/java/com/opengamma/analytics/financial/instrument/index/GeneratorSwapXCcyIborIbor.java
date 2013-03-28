/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.instrument.index;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.instrument.swap.SwapXCcyIborIborDefinition;
import com.opengamma.analytics.financial.schedule.ScheduleCalculator;
import com.opengamma.financial.convention.businessday.BusinessDayConvention;
import com.opengamma.util.ArgumentChecker;

/**
 * Class with the description of swap characteristics.
 */
public class GeneratorSwapXCcyIborIbor extends GeneratorInstrument<GeneratorAttributeFX> {

  /**
   * The Ibor index of the first leg. The spread is added to this leg.
   */
  private final IborIndex _iborIndex1;
  /**
   * The Ibor index of the second leg.
   */
  private final IborIndex _iborIndex2;
  /**
   * The business day convention associated to the swap.
   */
  private final BusinessDayConvention _businessDayConvention;
  /**
   * The flag indicating if the end-of-month rule is used for the swap.
   */
  private final boolean _endOfMonth;
  /**
   * The index spot lag in days between trade and settlement date (usually 2 or 0).
   */
  private final int _spotLag;

  // REVIEW: Do we need stubShort and stubFirst flags?
  // TODO: Add a merged calendar? [PLAT-1747]

  /**
   * Constructor from the details. The business day conventions, end-of-month and spot lag are from the first Ibor index.
   * @param name The generator name. Not null.
   * @param iborIndex1 The Ibor index of the first leg.
   * @param iborIndex2 The Ibor index of the second leg.
   */
  public GeneratorSwapXCcyIborIbor(final String name, final IborIndex iborIndex1, final IborIndex iborIndex2) {
    super(name);
    Validate.notNull(iborIndex1, "ibor index");
    Validate.notNull(iborIndex2, "ibor index");
    _iborIndex1 = iborIndex1;
    _iborIndex2 = iborIndex2;
    _businessDayConvention = iborIndex1.getBusinessDayConvention();
    _endOfMonth = iborIndex1.isEndOfMonth();
    _spotLag = iborIndex1.getSpotLag();
  }

  /**
   * Constructor from the details. The business day conventions, end-of-month and spot lag are from the Ibor index.
   * @param name The generator name. Not null.
   * @param iborIndex1 The Ibor index of the first leg.
   * @param iborIndex2 The Ibor index of the second leg.
   * @param businessDayConvention The business day convention associated to the index.
   * @param endOfMonth The end-of-month flag.
   * @param spotLag The swap spot lag (usually 2 or 0).
   */
  public GeneratorSwapXCcyIborIbor(final String name, final IborIndex iborIndex1, final IborIndex iborIndex2, final BusinessDayConvention businessDayConvention,
      final boolean endOfMonth, final int spotLag) {
    super(name);
    Validate.notNull(iborIndex1, "ibor index");
    Validate.notNull(iborIndex2, "ibor index");
    _iborIndex1 = iborIndex1;
    _iborIndex2 = iborIndex2;
    _businessDayConvention = businessDayConvention;
    _endOfMonth = endOfMonth;
    _spotLag = spotLag;
  }

  /**
   * Gets the Ibor index of the first leg.
   * @return The index.
   */
  public IborIndex getIborIndex1() {
    return _iborIndex1;
  }

  /**
   * Gets the Ibor index of the second leg.
   * @return The index.
   */
  public IborIndex getIborIndex2() {
    return _iborIndex2;
  }

  /**
   * Gets the swap generator business day convention.
   * @return The convention.
   */
  public BusinessDayConvention getBusinessDayConvention() {
    return _businessDayConvention;
  }

  /**
   * Gets the swap generator spot lag.
   * @return The lag (in days).
   */
  public int getSpotLag() {
    return _spotLag;
  }

  /**
   * Gets the swap generator end-of-month rule.
   * @return The EOM.
   */
  public Boolean isEndOfMonth() {
    return _endOfMonth;
  }

  /**
   * Generate the cross-currency swap from the spread and the FX exchange rate.
   * @param date The reference date (the effective date of the swap will be the spot lag of the generator after the reference date).
   * @param spread The spread above the index (is applied to the first leg).
   * @param notional The notional of the first leg. The second leg notional is that number multiplied by the FX rate (1 Ccy1 = x Ccy2).
   * @param attribute The FX instrument attributes. The start period is the date between the spot date and the effective period. 
   *   The end period is the period between the effective date and the maturity.
   * @return The cross-currency swap.
   */
  @Override
  public SwapXCcyIborIborDefinition generateInstrument(final ZonedDateTime date, final double spread, final double notional, final GeneratorAttributeFX attribute) {
    ArgumentChecker.notNull(date, "Reference date");
    ArgumentChecker.notNull(attribute, "Attributes");
    final ZonedDateTime spot = ScheduleCalculator.getAdjustedDate(date, _spotLag, _iborIndex1.getCalendar());
    final ZonedDateTime startDate = ScheduleCalculator.getAdjustedDate(spot, attribute.getStartPeriod(), _iborIndex1);
    final double fx = attribute.getFXMatrix().getFxRate(_iborIndex1.getCurrency(), _iborIndex2.getCurrency());
    return SwapXCcyIborIborDefinition.from(startDate, attribute.getEndPeriod(), this, notional, fx * notional, spread, true);
  }

  @Override
  public String toString() {
    return getName();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + _businessDayConvention.hashCode();
    result = prime * result + (_endOfMonth ? 1231 : 1237);
    result = prime * result + _iborIndex1.hashCode();
    result = prime * result + _iborIndex2.hashCode();
    result = prime * result + _spotLag;
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final GeneratorSwapXCcyIborIbor other = (GeneratorSwapXCcyIborIbor) obj;
    if (!ObjectUtils.equals(_businessDayConvention, other._businessDayConvention)) {
      return false;
    }
    if (_endOfMonth != other._endOfMonth) {
      return false;
    }
    if (!ObjectUtils.equals(_iborIndex2, other._iborIndex2)) {
      return false;
    }
    if (!ObjectUtils.equals(_iborIndex1, other._iborIndex1)) {
      return false;
    }
    if (_spotLag != other._spotLag) {
      return false;
    }
    return true;
  }

}
