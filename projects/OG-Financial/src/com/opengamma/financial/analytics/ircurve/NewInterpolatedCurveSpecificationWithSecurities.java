/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import java.util.Collection;

import javax.time.calendar.LocalDate;

import com.opengamma.id.UniqueIdentifiable;

/**
 * 
 */
public class NewInterpolatedCurveSpecificationWithSecurities extends NewCurveSpecificationWithSecurities {
  private static final long serialVersionUID = 1L;

  public NewInterpolatedCurveSpecificationWithSecurities(final LocalDate curveDate, final UniqueIdentifiable curveIdentifier, final String name,
      final Collection<CurveStripWithSecurity> strips) {
    super(curveDate, curveIdentifier, name, strips);
  }

}
