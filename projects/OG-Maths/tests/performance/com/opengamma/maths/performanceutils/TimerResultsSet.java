/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.performanceutils;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * A blob containing "n" and "t", "n" being size, "t" a corresponding time
 */
public class TimerResultsSet {
  private int[] _ns;
  private long[] _ts;

  /**
   * Gets the n Sizes.
   * @return the n Sizes
   */
  public int[] getSizes() {
    return _ns;
  }

  /**
   * Gets the times.
   * @return the times
   */
  public long[] getTimes() {
    return _ts;
  }

  /**
   * Create a new results set from sizes and times
   * @param ns an array of sizes
   * @param ts an array of timing results
   */
  public TimerResultsSet(int[] ns, long[] ts) {
    if (ns == null) {
      throw new MathsExceptionIllegalArgument("ns cannot be null");
    }
    if (ts == null) {
      throw new MathsExceptionIllegalArgument("ts cannot be null");
    }    
    if (ns.length != ts.length) {
      throw new MathsExceptionIllegalArgument("For a results set the number of sizes and number of times must be the same. Was given " + ns.length + " lengths and " + ts.length + "times.");
    }
    
    _ns = new int[ns.length];
    System.arraycopy(ns, 0, _ns, 0, ns.length);
    _ts = new long[ts.length];
    System.arraycopy(ts, 0, _ts, 0, ts.length);
  }

}
