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
public class RateFutureStrip extends CurveStrip {
  private static final long serialVersionUID = 1L;
  private final FutureType _futureType;
  private final int _nthFutureFromTenor;
  private final Tenor _futureTenor;
  private final Tenor _rateTenor;

  public RateFutureStrip(final FutureType futureType, final int nthFutureFromTenor, final Tenor futureTenor, final Tenor rateTenor, final Tenor curveNodePointTime,
      final String configurationName) {
    super(NewStripInstrumentType.FUTURE, curveNodePointTime, configurationName);
    ArgumentChecker.notNull(futureType, "future type");
    ArgumentChecker.notNull(futureTenor, "future tenor");
    ArgumentChecker.notNull(rateTenor, "rate tenor");
    ArgumentChecker.isTrue(nthFutureFromTenor > 0, "Future number must be greater than 0");
    _futureType = futureType;
    _nthFutureFromTenor = nthFutureFromTenor;
    _futureTenor = futureTenor;
    _rateTenor = rateTenor;
  }

  public FutureType getFutureType() {
    return _futureType;
  }

  public int getNumberOfFuturesAfterTenor() {
    return _nthFutureFromTenor;
  }

  public Tenor getFutureTenor() {
    return _futureTenor;
  }

  public Tenor getRateTenor() {
    return _rateTenor;
  }

  //TODO do we want the end of the rate rather than the future?
  @Override
  public Tenor getEffectiveTenor() {
    final int months = (int) getFutureTenor().getPeriod().totalMonths();
    return new Tenor(getCurveNodePointTime().getPeriod().plusMonths(months * getNumberOfFuturesAfterTenor()));
  }

  @Override
  public int compareTo(final CurveStrip other) {
    int result = super.compareTo(other);
    if (result != 0) {
      return result;
    }
    final RateFutureStrip otherFuture = (RateFutureStrip) other;
    result = getNumberOfFuturesAfterTenor() - otherFuture.getNumberOfFuturesAfterTenor();
    if (result != 0) {
      return result;
    }
    result = getFutureTenor().getPeriod().toPeriodFields().toEstimatedDuration().compareTo(otherFuture.getFutureTenor().getPeriod().toPeriodFields().toEstimatedDuration());
    if (result != 0) {
      return result;
    }
    return getRateTenor().getPeriod().toPeriodFields().toEstimatedDuration().compareTo(otherFuture.getRateTenor().getPeriod().toPeriodFields().toEstimatedDuration());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + _futureType.hashCode();
    result = prime * result + _futureTenor.hashCode();
    result = prime * result + _nthFutureFromTenor;
    result = prime * result + _rateTenor.hashCode();
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
    final RateFutureStrip other = (RateFutureStrip) obj;
    if (_nthFutureFromTenor != other._nthFutureFromTenor) {
      return false;
    }
    if (_futureType != other._futureType) {
      return false;
    }
    if (!ObjectUtils.equals(_futureTenor, other._futureTenor)) {
      return false;
    }
    if (!ObjectUtils.equals(_rateTenor, other._rateTenor)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("RateFutureStrip[");
    sb.append(getCurveNodePointTime());
    sb.append(", future type=");
    sb.append(getFutureType());
    sb.append(", future tenor=");
    sb.append(getFutureTenor());
    sb.append(", rate tenor=");
    sb.append(getRateTenor());
    sb.append(", configuration name=");
    sb.append(getConfigurationName());
    sb.append("]");
    return sb.toString();
  }
}
