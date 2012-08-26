/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class PeriodicZeroDepositStrip extends CurveStrip {
  private static final long serialVersionUID = 1L;
  private final int _periodsPerYear;

  public PeriodicZeroDepositStrip(final int periodsPerYear, final Tenor curveNodePointTime, final String configurationName) {
    super(NewStripInstrumentType.PERIODIC_ZERO_DEPOSIT, curveNodePointTime, configurationName);
    _periodsPerYear = periodsPerYear;
  }

  public int getPeriodsPerYear() {
    return _periodsPerYear;
  }

  @Override
  public int compareTo(final CurveStrip other) {
    final int result = super.compareTo(other);
    if (result != 0) {
      return result;
    }
    final PeriodicZeroDepositStrip otherStrip = (PeriodicZeroDepositStrip) other;
    return getPeriodsPerYear() - otherStrip.getPeriodsPerYear();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + _periodsPerYear;
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
    final PeriodicZeroDepositStrip other = (PeriodicZeroDepositStrip) obj;
    if (_periodsPerYear != other._periodsPerYear) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("PeriodicZeroDepositStrip[tenor=");
    sb.append(getCurveNodePointTime());
    sb.append(", periods per year=");
    sb.append(getPeriodsPerYear());
    sb.append(", configuration name=");
    sb.append(getConfigurationName());
    sb.append("]");
    return sb.toString();
  }
}
