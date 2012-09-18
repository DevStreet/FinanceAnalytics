/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.ircurve;

import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.time.calendar.LocalDate;

import org.apache.commons.lang.ObjectUtils;

import com.opengamma.id.ExternalId;
import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.util.ArgumentChecker;

/**
 * 
 */
public abstract class NewInterpolatedCurveSpecification<IDENTIFIABLE extends UniqueIdentifiable> extends NewCurveSpecification implements CurveStripInstrumentProvider<IDENTIFIABLE> {
  private static final long serialVersionUID = 1L;
  private final String _curveType;
  private final SortedSet<CurveStripWithIdentifier> _strips = new TreeSet<CurveStripWithIdentifier>();
  private final String _interpolatorName;
  private final String _leftExtrapolatorName;
  private final String _rightExtrapolatorName;

  public NewInterpolatedCurveSpecification(final LocalDate curveDate, final IDENTIFIABLE curveIdentifier, final String name, final ExternalId region,
      final Collection<CurveStripWithIdentifier> strips, final String curveType, final String interpolatorName, final String leftExtrapolatorName,
      final String rightExtrapolatorName) {
    super(curveDate, curveIdentifier, name, region);
    ArgumentChecker.notNull(strips, "strips");
    ArgumentChecker.notNull(curveType, "curve type");
    ArgumentChecker.notNull(interpolatorName, "interpolator name");
    ArgumentChecker.notNull(leftExtrapolatorName, "left extrapolator name");
    ArgumentChecker.notNull(rightExtrapolatorName, "right extrapolator name");
    _strips.addAll(strips);
    _curveType = curveType;
    _interpolatorName = interpolatorName;
    _leftExtrapolatorName = leftExtrapolatorName;
    _rightExtrapolatorName = rightExtrapolatorName;
  }

  @Override
  public void addStrip(final CurveStripWithIdentifier strip) {
    _strips.add(strip);
  }

  @Override
  public Collection<CurveStripWithIdentifier> getStrips() {
    return Collections.unmodifiableSortedSet(_strips);
  }

  public String getCurveType() {
    return _curveType;
  }

  public String getInterpolatorName() {
    return _interpolatorName;
  }

  public String getLeftExtrapolatorName() {
    return _leftExtrapolatorName;
  }

  public String getRightExtrapolatorName() {
    return _rightExtrapolatorName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + _curveType.hashCode();
    result = prime * result + _interpolatorName.hashCode();
    result = prime * result + _leftExtrapolatorName.hashCode();
    result = prime * result + _rightExtrapolatorName.hashCode();
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
    final NewInterpolatedCurveSpecification other = (NewInterpolatedCurveSpecification) obj;
    if (!ObjectUtils.equals(_interpolatorName, other._interpolatorName)) {
      return false;
    }
    if (!ObjectUtils.equals(_curveType, other._curveType)) {
      return false;
    }
    if (!ObjectUtils.equals(_leftExtrapolatorName, other._leftExtrapolatorName)) {
      return false;
    }
    if (!ObjectUtils.equals(_rightExtrapolatorName, other._rightExtrapolatorName)) {
      return false;
    }
    return true;
  }

}
