/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.core.marketdatasnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

  public VolatilityCubeData(final String definitionName, final String specificationName, final UniqueIdentifiable target, final String xLabel,
      final String yLabel, final String zLabel, final Map<VolatilityPoint<X, Y, Z>, Double> data) {
    ArgumentChecker.notNull(definitionName, "definition name");
    ArgumentChecker.notNull(specificationName, "specification name");
    ArgumentChecker.notNull(target, "target");
    ArgumentChecker.notNull(xLabel, "x label");
    ArgumentChecker.notNull(yLabel, "y label");
    ArgumentChecker.notNull(zLabel, "z label");
    _definitionName = definitionName;
    _specificationName = specificationName;
    _target = target;
    _xLabel = xLabel;
    _yLabel = yLabel;
    _zLabel = zLabel;
    _data = new HashMap<>(data);
    final List<X> xsList = new ArrayList<>();
    final List<Y> ysList = new ArrayList<>();
    final List<Z> zsList = new ArrayList<>();
    Class<X> xClazz = null;
    Class<Y> yClazz = null;
    Class<Z> zClazz = null;
    int i = 0;
    for (final Map.Entry<VolatilityPoint<X, Y, Z>, Double> entry : data.entrySet()) {
      final VolatilityPoint<X, Y, Z> key = entry.getKey();
      if (i == 0) {
        xClazz = (Class<X>) key.getXAxis().getClass();
        yClazz = (Class<Y>) key.getYAxis().getClass();
        zClazz = (Class<Z>) key.getZAxis().getClass();
      }
      xsList.add(key.getXAxis());
      ysList.add(key.getYAxis());
      zsList.add(key.getZAxis());
      i++;
    }
    _xs = (X[]) Array.newInstance(xClazz, xsList.size());
    _ys = (Y[]) Array.newInstance(yClazz, ysList.size());
    _zs = (Z[]) Array.newInstance(zClazz, zsList.size());
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
        getXLabel().equals(other.getXLabel()) &&
        getYLabel().equals(other.getYLabel()) &&
        getZLabel().equals(other.getZLabel()) &&
        _data.equals(other._data);
  }

}
