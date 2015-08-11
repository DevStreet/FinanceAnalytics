/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.interpolation.data;

import java.io.Serializable;
import java.util.Arrays;

import com.opengamma.util.ArgumentChecker;

/**
 * 
 */
public class Interpolator1DQuadraticSplineDataBundle implements Interpolator1DDataBundle, Serializable {
  private final Interpolator1DDataBundle _underlyingData;
  private final double[] _a;
  private final double[] _b;

  public Interpolator1DQuadraticSplineDataBundle(final Interpolator1DDataBundle underlyingData) {
    ArgumentChecker.notNull(underlyingData, "underlying data");
    _underlyingData = underlyingData;
    final double[] x = _underlyingData.getKeys();
    final double[] h = _underlyingData.getValues();
    final int n = _underlyingData.size();
    final double[] dx = new double[n];
    dx[0] = x[0];
    for (int i = 1; i < n; i++) {
      dx[i] = x[i] - x[i - 1];
    }
    _a = new double[n + 1];
    _b = new double[n + 1];
    _a[0] = Math.sqrt(_underlyingData.firstValue() / _underlyingData.firstKey());

    for (int i = 1; i < n; i++) {
      _a[i] = _a[i - 1] + _b[i - 1] * dx[i - 1];

      final double a = Math.pow(dx[i], 3) / 3;
      final double b = _a[i] * dx[i] * dx[i];
      final double c = _a[i] * _a[i] * dx[i] + h[i - 1] - h[i];
      double root = b * b - 4 * a * c;
      ArgumentChecker.isTrue(root >= 0, "root of neg");
      root = Math.sqrt(root);
      final double temp1 = (-b + root) / 2 / a;
      _b[i] = temp1;
      // _b[i] = 2 * (h[i] - h[i - 1] - _a[i] * dx[i]) / dx[i] / dx[i];
    }
    _a[n] = _a[n - 1];
  }

  @Override
  public boolean containsKey(final Double key) {
    return _underlyingData.containsKey(key);
  }

  @Override
  public Double firstKey() {
    return _underlyingData.firstKey();
  }

  @Override
  public Double firstValue() {
    return _underlyingData.firstValue();
  }

  @Override
  public Double get(final Double key) {
    return _underlyingData.get(key);
  }

  @Override
  public InterpolationBoundedValues getBoundedValues(final Double key) {
    return _underlyingData.getBoundedValues(key);
  }

  @Override
  public double[] getKeys() {
    return _underlyingData.getKeys();
  }

  @Override
  public int getLowerBoundIndex(final Double value) {
    final double[] keys = _underlyingData.getKeys();
    final int n = _underlyingData.size();
    if (value < keys[0]) {
      return 0;
    }
    if (value > keys[n - 1]) {
      return n;
    }
    int index = Arrays.binarySearch(keys, value);
    if (index >= 0) {
      // Fast break out if it's an exact match.
      return index;
    }
    if (index < 0) {
      index = -(index + 1);
      index--;
    }
    return index;
  }

  @Override
  public Double getLowerBoundKey(final Double value) {
    return _underlyingData.getLowerBoundKey(value);
  }

  @Override
  public double[] getValues() {
    return _underlyingData.getValues();
  }

  @Override
  public Double higherKey(final Double key) {
    return _underlyingData.higherKey(key);
  }

  @Override
  public Double higherValue(final Double key) {
    return _underlyingData.higherValue(key);
  }

  @Override
  public Double lastKey() {
    return _underlyingData.lastKey();
  }

  @Override
  public Double lastValue() {
    return _underlyingData.lastValue();
  }

  @Override
  public int size() {
    return _underlyingData.size();
  }

  @Override
  public void setYValueAtIndex(final int index, final double y) {
  }

  public double getA(final int index) {
    return _a[index];
  }

  public double getB(final int index) {
    return _b[index];
  }

}
