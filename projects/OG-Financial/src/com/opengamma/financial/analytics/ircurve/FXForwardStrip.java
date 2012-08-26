/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import org.apache.commons.lang.ObjectUtils;

import com.opengamma.util.money.Currency;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class FXForwardStrip extends CurveStrip {
  private static final long serialVersionUID = 1L;
  private final Currency _payCurrency;
  private final Currency _receiveCurrency;

  public FXForwardStrip(final Currency payCurrency, final Currency receiveCurrency, final Tenor curveNodePointTime, final String configurationName) {
    super(StripInstrumentType.FX_FORWARD, curveNodePointTime, configurationName);
    _payCurrency = payCurrency;
    _receiveCurrency = receiveCurrency;
  }

  public Currency getPayCurrency() {
    return _payCurrency;
  }

  public Currency getReceiveCurrency() {
    return _receiveCurrency;
  }

  @Override
  public int compareTo(final CurveStrip other) {
    int result = super.compareTo(other);
    if (result != 0) {
      return result;
    }
    final FXForwardStrip otherStrip = (FXForwardStrip) other;
    result = getPayCurrency().getCode().compareTo(otherStrip.getPayCurrency().getCode());
    if (result != 0) {
      return result;
    }
    return getReceiveCurrency().compareTo(otherStrip.getReceiveCurrency());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + _payCurrency.hashCode();
    result = prime * result + _receiveCurrency.hashCode();
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
    final FXForwardStrip other = (FXForwardStrip) obj;
    if (!ObjectUtils.equals(_payCurrency, other._payCurrency)) {
      return false;
    }
    if (!ObjectUtils.equals(_receiveCurrency, other._receiveCurrency)) {
      return false;
    }
    return true;
  }

}
