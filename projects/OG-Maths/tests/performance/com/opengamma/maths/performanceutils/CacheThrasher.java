/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.performanceutils;

import java.util.Random;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * Thrashes the cache so try and reduce latent occupancy of data between runs. 
 */
public class CacheThrasher {
  // based on single core of an i7 
  private static int _L1cacheSizeKb = 32; // CSIGNORE // data only, assume instruction will be flushed on calls/problem is memory bound
  private static int _L2cacheSizeKb = 256; // CSIGNORE // assume per core
  private static int _L3cacheSizeKb = 8192; // CSIGNORE // assumed unified

  private int _oneKb = 1 << 20; // 1Kb
  private int _cacheMultiplier = 3; // how many times should our data set fit in cache?

  private static Random s_rand = new Random();

  private static double[] s_L1cacheFiller;// CSIGNORE // some data we can replicate so save calling Rand all the time

  private static int s_bytesPerDouble = (Double.SIZE) / (Byte.SIZE);

  private static double s_pointlessVarToStopJIT;

  public CacheThrasher() {
    this(_L1cacheSizeKb, _L2cacheSizeKb, _L3cacheSizeKb);
  }

  /**
   * As tech moves we'll need to update defaults/generate thrashers based on larger parameters
   * @param L1cacheSize the L1 Cache size in Kb.
   * @param L2cacheSize the L2 Cache size in Kb.
   * @param L3cacheSize the L3 Cache size in Kb.
   */
  public CacheThrasher(int L1cacheSize, int L2cacheSize, int L3cacheSize) { // CSIGNORE
    if (L1cacheSize < 1) {
      throw new MathsExceptionIllegalArgument("L1cacheSize must be greater than zero");
    }
    if (L2cacheSize < 1) {
      throw new MathsExceptionIllegalArgument("L2cacheSize must be greater than zero");
    }
    if (L3cacheSize < 1) {
      throw new MathsExceptionIllegalArgument("L3cacheSize must be greater than zero");
    }
    if (L1cacheSize > L2cacheSize || L1cacheSize > L3cacheSize || L2cacheSize > L3cacheSize) {
      throw new MathsExceptionIllegalArgument("The cache sizes given do not represent a hierarchical cache model, are you sure this is right?!");
    }
    _L1cacheSizeKb = L1cacheSize;
    _L2cacheSizeKb = L2cacheSize;
    _L3cacheSizeKb = L3cacheSize;
    int fillLen = _L1cacheSizeKb * _oneKb / s_bytesPerDouble;
    s_L1cacheFiller = new double[fillLen];
    for (int i = 0; i < s_L1cacheFiller.length; i++) {
      s_L1cacheFiller[i] = s_rand.nextDouble();
    }
  }

  /**
   * Thrashes the L1 cache
   */
  public void thrashL1() {
    thrash(_L1cacheSizeKb);
  }

  /**
   * Thrashes the L2 cache
   */
  public void thrashL2() {
    thrash(_L2cacheSizeKb);
  }

  /**
   * Thrashes the L3 cache
   */
  public void thrashL3() {
    thrash(_L3cacheSizeKb);
  }

  private void thrash(int cacheSize) {
    s_pointlessVarToStopJIT = 0;
    int cacheFiller = cacheSize * _oneKb / s_bytesPerDouble;
    double[] data = new double[_cacheMultiplier * cacheFiller];
    int copiesNeeded = data.length / s_L1cacheFiller.length;
    for (int i = 0; i < copiesNeeded; i++) {
      System.arraycopy(s_L1cacheFiller, 0, data, i * s_L1cacheFiller.length, s_L1cacheFiller.length);
    }
    if (data.length % s_L1cacheFiller.length != 0) {
      System.arraycopy(s_L1cacheFiller, 0, data, copiesNeeded * s_L1cacheFiller.length, data.length / s_L1cacheFiller.length);
    }
    // some totally pointless work to drag data through cache
    double acc = 0;
    for (int i = 0; i < data.length; i++) {
      acc += data[i];
    }
    for (int i = data.length / 2; i < data.length; i++) {
      acc -= data[i];
    }
    for (int i = data.length / 2; i > 0; i--) {
      acc += data[i];
    }
    // write back to class scope var to stop JIT removing pointless work
    s_pointlessVarToStopJIT += acc;
  }

  /**
   * Dummy function, this utterly pointless function helps stop stuff getting JITted when we want it to run!
   * @return s_pointlessVarToStopJIT
   */
  double getpointlessVarToStopJIT() {
    return s_pointlessVarToStopJIT;
  }

}
