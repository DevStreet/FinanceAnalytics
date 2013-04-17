/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.core.marketdatasnapshot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.util.ArgumentChecker;

/**
 * A bundle of data to use to build a volatility cube.
 * @param <X> The type of the x axis data
 * @param <Y> The type of the y axis data
 * @param <Z> The type of the z axis data
 */
public class VolatilityCubeData<X extends Comparable<X>, Y extends Comparable<Y>, Z extends Comparable<Z>> {
  private final String _definitionName;
  private final String _specificationName;
  private final UniqueIdentifiable _target;
  private final X[] _xs;
  private final Y[] _ys;
  private final Z[] _zs;
  private final Map<VolatilityPoint<X, Y, Z>, Double> _data;
  private final String _xLabel;
  private final String _yLabel;
  private final String _zLabel;

  public VolatilityCubeData(final String definitionName, final String specificationName, final UniqueIdentifiable target, final X[] xs, final String xLabel,
      final Y[] ys, final String yLabel, final Z[] zs, final String zLabel, final Map<VolatilityPoint<X, Y, Z>, Double> data) {
    ArgumentChecker.notNull(definitionName, "definition name");
    ArgumentChecker.notNull(specificationName, "specification name");
    ArgumentChecker.notNull(target, "target");
    ArgumentChecker.notNull(xs, "xs");
    ArgumentChecker.notNull(xLabel, "x label");
    ArgumentChecker.notNull(ys, "ys");
    ArgumentChecker.notNull(yLabel, "y label");
    ArgumentChecker.notNull(zs, "zs");
    ArgumentChecker.notNull(zLabel, "z label");
    _definitionName = definitionName;
    _specificationName = specificationName;
    _target = target;
    _xs = xs;
    _xLabel = xLabel;
    _ys = ys;
    _yLabel = yLabel;
    _zs = zs;
    _zLabel = zLabel;
    _data = new HashMap<>(data);
  }

  /**
   * Gets the definition name.
   * @return the definition name
   */
  public String getDefinitionName() {
    return _definitionName;
  }

  /**
   * Gets the specification name.
   * @return the specification name
   */
  public String getSpecificationName() {
    return _specificationName;
  }

  /**
   * Gets the target.
   * @return the target
   */
  public UniqueIdentifiable getTarget() {
    return _target;
  }

  /**
   * Gets the xs.
   * @return the xs
   */
  public X[] getXs() {
    return _xs;
  }

  /**
   * Gets the ys.
   * @return the ys
   */
  public Y[] getYs() {
    return _ys;
  }

  /**
   * Gets the zs.
   * @return the zs
   */
  public Z[] getZs() {
    return _zs;
  }

  /**
   * Gets the data.
   * @return the data
   */
  public Map<VolatilityPoint<X, Y, Z>, Double> getData() {
    return _data;
  }

  /**
   * Gets the x label.
   * @return the x label
   */
  public String getXLabel() {
    return _xLabel;
  }

  /**
   * Gets the y label.
   * @return the y label
   */
  public String getYLabel() {
    return _yLabel;
  }

  /**
   * Gets the z label.
   * @return the z label
   */
  public String getZLabel() {
    return _zLabel;
  }

  @Override
  public int hashCode() {
    return _definitionName.hashCode() ^ getSpecificationName().hashCode() ^ getTarget().hashCode();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof VolatilityCubeData)) {
      return false;
    }
    final VolatilityCubeData<?, ?, ?> other = (VolatilityCubeData<?, ?, ?>) obj;
    return getDefinitionName().equals(other.getDefinitionName()) &&
        getSpecificationName().equals(other.getSpecificationName()) &&
        getTarget().equals(other.getTarget()) &&
        Arrays.equals(getXs(), other.getXs()) &&
        Arrays.equals(getYs(), other.getYs()) &&
        getXLabel().equals(other.getXLabel()) &&
        getYLabel().equals(other.getYLabel()) &&
        _data.equals(other._data);
  }

}
