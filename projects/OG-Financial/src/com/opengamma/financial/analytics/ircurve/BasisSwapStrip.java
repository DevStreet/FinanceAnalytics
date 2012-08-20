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
public class BasisSwapStrip extends CurveStrip {
  private static final long serialVersionUID = 1L;

  public BasisSwapStrip(final Tenor curveNodePointTime, final String configurationName) {
    super(StripInstrumentType.BASIS_SWAP, curveNodePointTime, configurationName);
  }

}
