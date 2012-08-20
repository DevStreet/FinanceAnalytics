/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import java.io.Serializable;

import org.apache.commons.lang.ObjectUtils;

import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
public abstract class CurveStrip implements Serializable, Comparable<CurveStrip> {
  private static final long serialVersionUID = 1L;
  private final StripInstrumentType _instrumentType;
  private final Tenor _curveNodePointTime;
  private final String _configurationName;

  public CurveStrip(final StripInstrumentType instrumentType, final Tenor curveNodePointTime, final String configurationName) {
    ArgumentChecker.notNull(instrumentType, "instrument type");
    ArgumentChecker.notNull(curveNodePointTime, "curve node point time");
    ArgumentChecker.notNull(configurationName, "configuration name");
    _instrumentType = instrumentType;
    _curveNodePointTime = curveNodePointTime;
    _configurationName = configurationName;
  }

  /**
   * Gets the instrument type used to construct this strip.
   * 
   * @return the instrument type, not null
   */
  public StripInstrumentType getInstrumentType() {
    return _instrumentType;
  }

  /**
   * Gets the curve node point in time.
   * 
   * @return a tenor representing the time of the curve node point, not null
   */
  public Tenor getCurveNodePointTime() {
    return _curveNodePointTime;
  }

  /**
   * Gets the name of the convention used to resolve this strip definition into a security.
   * 
   * @return the name, not null
   */
  public String getConfigurationName() {
    return _configurationName;
  }

  /**
   * Calculates the tenor of a strip. For all instruments except futures, this is the same as that entered on construction.
   * For futures, this is the start tenor + (3 * future number)
   * @return The effective tenor of the strip
   */
  public Tenor getEffectiveTenor() {
    return new Tenor(getCurveNodePointTime().getPeriod());
  }

  @Override
  public int compareTo(final CurveStrip other) {
    final int result = getEffectiveTenor().getPeriod().toPeriodFields().toEstimatedDuration().compareTo(other.getEffectiveTenor().getPeriod().toPeriodFields().toEstimatedDuration());
    if (result != 0) {
      return result;
    }
    return getInstrumentType().ordinal() - other.getInstrumentType().ordinal();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof CurveStrip)) {
      return false;
    }
    final CurveStrip other = (CurveStrip) obj;
    return ObjectUtils.equals(_curveNodePointTime, other._curveNodePointTime) &&
        ObjectUtils.equals(_configurationName, other._configurationName) &&
        _instrumentType == other._instrumentType;
  }

  @Override
  public int hashCode() {
    return _curveNodePointTime.hashCode();
  }
}
