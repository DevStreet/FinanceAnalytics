/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import java.util.Collection;

import com.opengamma.id.UniqueIdentifiable;

/**
 * @param <IDENTIFIABLE> The type of the curve identifier 
 */
public interface CurveStripInstrumentProvider<IDENTIFIABLE extends UniqueIdentifiable> {

  Collection<CurveStripWithIdentifier> getStrips();

  void addStrip(CurveStripWithIdentifier strip);
}
