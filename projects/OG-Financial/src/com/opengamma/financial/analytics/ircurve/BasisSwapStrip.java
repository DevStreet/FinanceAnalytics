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
public class BasisSwapStrip extends CurveStrip {
  private static final long serialVersionUID = 1L;
  private final Tenor _payTenor;
  private final RateType _payIndexType;
  private final Tenor _receiveTenor;
  private final RateType _receiveIndexType;

  public BasisSwapStrip(final Tenor payTenor, final RateType payIndexType, final Tenor receiveTenor, final RateType receiveIndexType,
      final Tenor curveNodePointTime, final String configurationName) {
    super(NewStripInstrumentType.BASIS_SWAP, curveNodePointTime, configurationName);
    ArgumentChecker.notNull(payTenor, "pay tenor");
    ArgumentChecker.notNull(payIndexType, "pay index type");
    ArgumentChecker.notNull(receiveTenor, "receive tenor");
    ArgumentChecker.notNull(receiveIndexType, "receive index type");
    _payTenor = payTenor;
    _payIndexType = payIndexType;
    _receiveTenor = receiveTenor;
    _receiveIndexType = receiveIndexType;
  }

  public Tenor getPayResetTenor() {
    return _payTenor;
  }

  public RateType getPayIndexType() {
    return _payIndexType;
  }

  public Tenor getReceiveResetTenor() {
    return _receiveTenor;
  }

  public RateType getReceiveIndexType() {
    return _receiveIndexType;
  }

  @Override
  public int compareTo(final CurveStrip other) {
    int result = super.compareTo(other);
    if (result != 0) {
      return result;
    }
    final BasisSwapStrip otherBasisSwap = (BasisSwapStrip) other;
    result = getPayResetTenor().getPeriod().toPeriodFields().toEstimatedDuration().
        compareTo(otherBasisSwap.getPayResetTenor().getPeriod().toPeriodFields().toEstimatedDuration());
    if (result != 0) {
      return result;
    }
    result = getReceiveResetTenor().getPeriod().toPeriodFields().toEstimatedDuration().
        compareTo(otherBasisSwap.getReceiveResetTenor().getPeriod().toPeriodFields().toEstimatedDuration());
    if (result != 0) {
      return result;
    }
    result = getPayIndexType().name().compareTo(otherBasisSwap.getPayIndexType().name());
    if (result != 0) {
      return result;
    }
    return getReceiveIndexType().name().compareTo(otherBasisSwap.getReceiveIndexType().name());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + _payIndexType.hashCode();
    result = prime * result + _payTenor.hashCode();
    result = prime * result + _receiveIndexType.hashCode();
    result = prime * result + _receiveTenor.hashCode();
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
    final BasisSwapStrip other = (BasisSwapStrip) obj;
    if (!ObjectUtils.equals(_payTenor, other._payTenor)) {
      return false;
    }
    if (!ObjectUtils.equals(_receiveTenor, other._receiveTenor)) {
      return false;
    }
    if (_payIndexType != other._payIndexType) {
      return false;
    }
    if (_receiveIndexType != other._receiveIndexType) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("BasisSwapStrip[tenor=");
    sb.append(getCurveNodePointTime());
    sb.append(", pay reset tenor=");
    sb.append(getPayResetTenor());
    sb.append(", pay index type=");
    sb.append(getPayIndexType());
    sb.append(", receive reset tenor=");
    sb.append(getReceiveResetTenor());
    sb.append(", receive index type=");
    sb.append(getReceiveIndexType());
    sb.append(", configuration name=");
    sb.append(getConfigurationName());
    sb.append("]");
    return sb.toString();
  }
}
