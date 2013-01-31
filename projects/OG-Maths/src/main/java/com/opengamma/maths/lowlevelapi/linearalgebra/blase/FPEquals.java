/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.linearalgebra.blase;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.analytics.math.statistics.distribution.fnlib.D1MACH;

/**
 * Checks floating point arrays are equal within some tolerance.
 */
public class FPEquals {

  private static Logger s_log = LoggerFactory.getLogger(FPEquals.class);

  private static double s_defaulttol = 100 * D1MACH.four();
  private static FPEquals s_instance;
  static {
    s_instance = new FPEquals();
  }

  public static boolean equals(double[] a, double[] b) {
    return Arrays.equals(a, b);
  }

  public static boolean fuzzyEquals(int count, double[] a, int offseta, double[] b, int offsetb, double tolerance) {
    for (int i = 0; i < count; i++) {
      if (Math.abs(a[i + offseta] - b[i + offsetb]) > tolerance) {
        s_log.debug("Equality test failed between " + a[i + offseta] + " and " + b[i + offsetb] + " with tolerance = " + tolerance);
        return false;
      }
    }
    return true;
  }

  public static boolean fuzzyEquals(double[] a, double[] b, double tolerance) {
    return fuzzyEquals(a.length, a, 0, b, 0, tolerance);
  }

  public static boolean fuzzyEquals(double[] a, double[] b) {
    return fuzzyEquals(a, b, s_defaulttol);
  }

  public static boolean fuzzyEqualsDynamicTol(int count, double[] a, int offseta, double[] b, int offsetb) {
    double tolerance;
    for (int i = 0; i < count; i++) {
      tolerance = 100 * Math.max(Math.ulp(a[i + offseta]), Math.ulp(b[i + offsetb]));
      if (Math.abs(a[i + offseta] - b[i + offsetb]) > tolerance) {
        s_log.debug("Equality test failed between " + a[i + offseta] + " and " + b[i + offsetb] + " with tolerance = " + tolerance);
        return false;
      }
    }
    return true;
  }

  public static boolean fuzzyEqualsDynamicTol(double[] a, double[] b) {
    return fuzzyEqualsDynamicTol(a.length, a, 0, b, 0);
  }

  public static FPEquals getInstance() {
    return s_instance;
  }

}
