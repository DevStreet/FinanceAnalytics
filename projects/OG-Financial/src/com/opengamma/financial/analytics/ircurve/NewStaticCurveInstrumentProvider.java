/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import org.apache.commons.lang.ObjectUtils;

import com.opengamma.id.ExternalId;
import com.opengamma.util.ArgumentChecker;

/**
 * 
 */
public class NewStaticCurveInstrumentProvider {
  private final ExternalId _identifier;

  public NewStaticCurveInstrumentProvider(final ExternalId identifier) {
    ArgumentChecker.notNull(identifier, "identifier");
    _identifier = identifier;
  }

  public ExternalId getRateIdentifier() {
    return _identifier;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _identifier.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final NewStaticCurveInstrumentProvider other = (NewStaticCurveInstrumentProvider) obj;
    if (!ObjectUtils.equals(_identifier, other._identifier)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "StaticCurveInstrumentProvider[" + _identifier.toString() + "]";
  }
}
