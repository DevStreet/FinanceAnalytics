/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Checks floating point arrays are equal within some tolerance.
 */
public class FPEquals {

  private static Logger s_log = LoggerFactory.getLogger(FPEquals.class);

  private static double s_defaulttol = 100 * D1mach.four();

  public static boolean equals(double[] a, double[] b) {
    return Arrays.equals(a, b);
  }

  public static boolean fuzzyEquals(int count, double[] a, int offseta, double[] b, int offsetb, double tolerance) {
    for (int i = 0; i < count; i++) {
      if (Double.isNaN(a[i + offseta]) && !Double.isNaN(b[i + offsetb])) {
        return false;
      }
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

  public static boolean fuzzyEquals(double a, double b, double tol) {
    return fuzzyEquals(new double[] {a }, new double[] {b }, tol);
  }

  public static boolean fuzzyEquals(double a, double b) {
    return fuzzyEquals(new double[] {a }, new double[] {b }, s_defaulttol);
  }

  public static boolean fuzzyEqualsDynamicTol(int count, double[] a, int offseta, double[] b, int offsetb) {
    double tolerance;
    for (int i = 0; i < count; i++) {
      if (b[i + offsetb] == 0) {
        tolerance = 10 * D1mach.four();
      } else {
        tolerance = Math.ulp(b[i + offsetb]) > D1mach.four() ? 100 * Math.ulp(b[i + offsetb]) : 10 * D1mach.four();
      }
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

  public static boolean fuzzyEqualsDynamicTol(double a, double b) {
    return fuzzyEqualsDynamicTol(1, new double[] {a }, 0, new double[] {b }, 0);
  }

}
