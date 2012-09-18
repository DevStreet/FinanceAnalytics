/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.opengamma.id.ExternalId;
import com.opengamma.util.ArgumentChecker;

/**
 * 
 */
public class CurveStripWithIdentifier implements Comparable<CurveStripWithIdentifier> {
  private final CurveStrip _strip;
  private final ExternalId _identifier;

  public CurveStripWithIdentifier(final CurveStrip strip, final ExternalId identifier) {
    ArgumentChecker.notNull(strip, "strip");
    ArgumentChecker.notNull(identifier, "identifier");
    _strip = strip;
    _identifier = identifier;
  }

  public CurveStrip getStrip() {
    return _strip;
  }

  public ExternalId getIdentifier() {
    return _identifier;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _identifier.hashCode();
    result = prime * result + _strip.hashCode();
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
    final CurveStripWithIdentifier other = (CurveStripWithIdentifier) obj;
    if (!ObjectUtils.equals(_identifier, other._identifier)) {
      return false;
    }
    if (!ObjectUtils.equals(_strip, other._strip)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

  @Override
  public int compareTo(final CurveStripWithIdentifier other) {
    //TODO what to do when other is null
    final int result = getStrip().compareTo(other.getStrip());
    if (result != 0) {
      return result;
    }
    return getIdentifier().getValue().compareTo(other.getIdentifier().getValue());
  }
}
