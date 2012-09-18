/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import java.util.Collection;

import javax.time.calendar.LocalDate;

import com.opengamma.id.ExternalId;
import com.opengamma.util.money.Currency;

/**
 * 
 */
public class NewInterpolatedYieldCurveSpecification extends NewInterpolatedCurveSpecification<Currency> {
  private static final long serialVersionUID = 1L;
  /** Interpolated yield curve type */
  public static final String CURVE_TYPE = "Interpolated Yield Curve";

  public NewInterpolatedYieldCurveSpecification(final LocalDate curveDate, final Currency curveIdentifier, final String name, final ExternalId region,
      final Collection<CurveStripWithIdentifier> strips, final String interpolatorName, final String leftExtrapolatorName, final String rightExtrapolatorName) {
    super(curveDate, curveIdentifier, name, region, strips, CURVE_TYPE, interpolatorName, leftExtrapolatorName, rightExtrapolatorName);
  }

}
