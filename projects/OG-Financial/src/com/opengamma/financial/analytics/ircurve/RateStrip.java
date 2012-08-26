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
public class RateStrip extends CurveStrip {
  private static final long serialVersionUID = 1L;

  public RateStrip(final Tenor curveNodePointTime, final String configurationName) {
    super(NewStripInstrumentType.RATE, curveNodePointTime, configurationName);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("RateStrip[tenor=");
    sb.append(getCurveNodePointTime());
    sb.append(", configuration name=");
    sb.append(getConfigurationName());
    sb.append("]");
    return sb.toString();
  }
}
