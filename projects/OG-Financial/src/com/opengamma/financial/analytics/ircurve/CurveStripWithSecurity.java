/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import javax.time.calendar.ZonedDateTime;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.opengamma.core.security.Security;
import com.opengamma.id.ExternalId;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.time.Tenor;

/**
 * 
 */
public class CurveStripWithSecurity implements Comparable<CurveStripWithSecurity> {
  private final CurveStrip _strip;
  private final Tenor _resolvedTenor;
  private final ZonedDateTime _maturity;
  private final ExternalId _securityIdentifier;
  private final Security _security;

  public CurveStripWithSecurity(final CurveStrip strip, final Tenor resolvedTenor, final ZonedDateTime maturity, final ExternalId securityIdentifier, final Security security) {
    ArgumentChecker.notNull(strip, "strip");
    ArgumentChecker.notNull(resolvedTenor, "resolved tenor");
    ArgumentChecker.notNull(maturity, "maturity");
    ArgumentChecker.notNull(securityIdentifier, "security identifier");
    ArgumentChecker.notNull(security, "security");
    _strip = strip;
    _resolvedTenor = resolvedTenor;
    _maturity = maturity;
    _securityIdentifier = securityIdentifier;
    _security = security;
  }

  public CurveStrip getStrip() {
    return _strip;
  }

  public Tenor getResolvedTenor() {
    return _resolvedTenor;
  }

  public ZonedDateTime getMaturity() {
    return _maturity;
  }

  public ExternalId getSecurityIdentifier() {
    return _securityIdentifier;
  }

  public Security getSecurity() {
    return _security;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _maturity.hashCode();
    result = prime * result + _resolvedTenor.hashCode();
    result = prime * result + _security.hashCode();
    result = prime * result + _securityIdentifier.hashCode();
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
    final CurveStripWithSecurity other = (CurveStripWithSecurity) obj;
    if (!ObjectUtils.equals(_strip, other._strip)) {
      return false;
    }
    if (!ObjectUtils.equals(_securityIdentifier, other._securityIdentifier)) {
      return false;
    }
    if (!ObjectUtils.equals(_security, other._security)) {
      return false;
    }
    if (!ObjectUtils.equals(_maturity, other._maturity)) {
      return false;
    }
    if (!ObjectUtils.equals(_resolvedTenor, other._resolvedTenor)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

  @Override
  public int compareTo(final CurveStripWithSecurity other) {
    //TODO check what should happen when other is null
    int result = getStrip().compareTo(other.getStrip());
    if (result != 0) {
      return result;
    }
    result = getResolvedTenor().compareTo(other.getResolvedTenor());
    if (result != 0) {
      return result;
    }
    if (getSecurity().getUniqueId() == null) {
      if (other.getSecurity().getUniqueId() == null) {
        return 0;
      }
      return -1;
    }
    result = getSecurity().getUniqueId().getValue().compareTo(other.getSecurity().getUniqueId().getValue());
    return result;
  }
}
