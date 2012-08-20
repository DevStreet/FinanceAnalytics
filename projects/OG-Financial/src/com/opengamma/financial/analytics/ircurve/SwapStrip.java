/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import org.apache.commons.lang.ObjectUtils;

import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class SwapStrip extends CurveStrip {
  private static final long serialVersionUID = 1L;
  private final Tenor _resetTenor;
  private final RateType _floatingIndexType;

  public SwapStrip(final Tenor resetTenor, final RateType floatingIndexType, final Tenor curveNodePointTime, final String configurationName) {
    super(StripInstrumentType.SWAP, curveNodePointTime, configurationName);
    ArgumentChecker.notNull(resetTenor, "reset tenor");
    ArgumentChecker.notNull(floatingIndexType, "floating index type");
    _resetTenor = resetTenor;
    _floatingIndexType = floatingIndexType;
  }

  public Tenor getResetTenor() {
    return _resetTenor;
  }

  public RateType getFloatingIndexType() {
    return _floatingIndexType;
  }

  public int compareTo(final SwapStrip other) {
    int result = super.compareTo(other);
    if (result != 0) {
      return result;
    }
    result = getResetTenor().getPeriod().toPeriodFields().toEstimatedDuration().compareTo(other.getResetTenor().getPeriod().toPeriodFields().toEstimatedDuration());
    if (result != 0) {
      return result;
    }
    return ObjectUtils.compare(getFloatingIndexType(), other.getFloatingIndexType());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + _resetTenor.hashCode();
    result = prime * result + _floatingIndexType.hashCode();
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
    final SwapStrip other = (SwapStrip) obj;
    if (!ObjectUtils.equals(_resetTenor, other._resetTenor)) {
      return false;
    }
    if (!ObjectUtils.equals(_floatingIndexType, other._floatingIndexType)) {
      ;
    }
    return true;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("SwapStrip[tenor=");
    sb.append(getCurveNodePointTime());
    sb.append(", reset tenor=");
    sb.append(getResetTenor());
    sb.append(", index type=");
    sb.append(getFloatingIndexType());
    sb.append(", configuration name=");
    sb.append(getConfigurationName());
    sb.append("]");
    return sb.toString();
  }
}
