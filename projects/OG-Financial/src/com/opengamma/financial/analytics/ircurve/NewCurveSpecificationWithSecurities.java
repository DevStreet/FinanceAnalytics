/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.time.calendar.LocalDate;

import org.apache.commons.lang.ObjectUtils;

import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.util.ArgumentChecker;

/**
 * 
 */
public abstract class NewCurveSpecificationWithSecurities implements Serializable {
  private static final long serialVersionUID = 1L;
  private final LocalDate _curveDate;
  private final UniqueIdentifiable _curveIdentifier;
  private final String _name;
  private final SortedSet<CurveStripWithSecurity> _strips = new TreeSet<CurveStripWithSecurity>();

  public NewCurveSpecificationWithSecurities(final LocalDate curveDate, final UniqueIdentifiable curveIdentifier, final String name, final Collection<CurveStripWithSecurity> strips) {
    ArgumentChecker.notNull(curveDate, "curve date");
    ArgumentChecker.notNull(curveIdentifier, "curve identifier");
    ArgumentChecker.notNull(strips, "strips");
    _curveDate = curveDate;
    _curveIdentifier = curveIdentifier;
    _name = name;
    _strips.addAll(strips);
  }

  public void addStrip(final CurveStripWithSecurity strip) {
    ArgumentChecker.notNull(strip, "strip");
    _strips.add(strip);
  }

  public LocalDate getCurveDate() {
    return _curveDate;
  }

  public UniqueIdentifiable getCurveIdentifier() {
    return _curveIdentifier;
  }

  public String getName() {
    return _name;
  }

  public Set<CurveStripWithSecurity> getStrips() {
    return Collections.unmodifiableSet(_strips);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _curveDate.hashCode();
    result = prime * result + _curveIdentifier.hashCode();
    result = prime * result + ((_name == null) ? 0 : _name.hashCode());
    result = prime * result + _strips.hashCode();
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
    final NewCurveSpecificationWithSecurities other = (NewCurveSpecificationWithSecurities) obj;
    if (!ObjectUtils.equals(_name, other._name)) {
      return false;
    }
    if (!ObjectUtils.equals(_curveDate, other._curveDate)) {
      return false;
    }
    if (!ObjectUtils.equals(_curveIdentifier, other._curveIdentifier)) {
      return false;
    }
    if (!ObjectUtils.equals(_strips, other._strips)) {
      return false;
    }
    return true;
  }

}
