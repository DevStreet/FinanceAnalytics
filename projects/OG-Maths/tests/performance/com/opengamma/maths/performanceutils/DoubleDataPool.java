/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.performanceutils;

import java.util.Random;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * Creates and holds a data pool and provides data from it on request.
 */
public class DoubleDataPool {
  private double[] _dataPool;
  private Random _rand = new Random();
  private static int s_defaultSize = 512;
  private int _poolSize;

  /**
   * Set the data pool to a certain size
   * @param size the size of pool to create (no of elements, not byte size)
   */
  public DoubleDataPool(int size) {
    if (size < 1) {
      throw new MathsExceptionIllegalArgument("Size must be in Z+");
    }
    _poolSize = size;
    _dataPool = new double[_poolSize];
    for (int i = 0; i < size; i++) {
      _dataPool[i] = _rand.nextDouble();
    }
  }

  /**
   * Initialise a default data pool
   */
  public DoubleDataPool() {
    this(s_defaultSize);
  }

  /**
   * return some data of length "n"
   * @param n the length of data required
   * @return the "random" data from the pool
   */
  public double[] yieldData(int n) {
    double[] data = new double[n];
    // how much do we need?
    if (_poolSize > n) { // pool > needed
      System.arraycopy(_dataPool, 0, data, 0, data.length);
    } else { // pool < needed, replicate
      int repeats = n / _poolSize; // the number of dupes
      int spares = n % _poolSize; // the number of spares
      for (int i = 0; i < repeats; i++) {
        System.arraycopy(_dataPool, 0, data, i * _poolSize, _poolSize);
      }
      if (spares != 0) { // fix up the tail data
        System.arraycopy(_dataPool, 0, data, repeats * _poolSize, spares);
      }
    }
    return data;
  }

}
