/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class RateStrip extends CurveStrip {
  private static final long serialVersionUID = 1L;
  private final RateType _rateType;

  public RateStrip(final RateType rateType, final Tenor curveNodePointTime, final String configurationName) {
    super(NewStripInstrumentType.RATE, curveNodePointTime, configurationName);
    ArgumentChecker.notNull(rateType, "rate type");
    _rateType = rateType;
  }

  public RateType getRateType() {
    return _rateType;
  }

  @Override
  public int compareTo(final CurveStrip other) {
    final int result = super.compareTo(other);
    if (result != 0) {
      return result;
    }
    final RateStrip otherRate = (RateStrip) other;
    return getRateType().name().compareTo(otherRate.getRateType().name());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + _rateType.hashCode();
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
    final RateStrip other = (RateStrip) obj;
    if (_rateType != other._rateType) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("RateStrip[tenor=");
    sb.append(getCurveNodePointTime());
    sb.append(", rate type=");
    sb.append(getRateType());
    sb.append(", configuration name=");
    sb.append(getConfigurationName());
    sb.append("]");
    return sb.toString();
  }
}
