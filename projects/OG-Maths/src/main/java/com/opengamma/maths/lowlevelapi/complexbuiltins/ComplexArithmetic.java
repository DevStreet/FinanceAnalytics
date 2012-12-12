/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.complexbuiltins;

/**
 * These are equivalent to the built in functions in other languages for handling complex arithmetic
 */
public class ComplexArithmetic {

  /**
  * Complex division 
  * @param arg0 the numerator
  * @param arg1 the denominator
  * @return arg0/arg1 the quotient
  */
  // The obvious a+bi/c+di = (ac+bd)/(cc+dd) + ((bc-ad)/(cc+dd))i
  // is fine so long as cc+dd isn't large.
  // We employ the formulation suggested by R. L. Smith to offer some protection against
  // overflow, note that improved variants are available if more is known about the hardware.
  // See  R.L. Smith. Algorithm 116: Complex division. Communications of the ACM,
  // 5(8):435, 1962.

  public static double[] divide(double[] arg0, double[] arg1) {
    double a, b, c, d, btm, div;
    double[] tmp = new double[2];
    a = arg0[0];
    b = arg0[1];
    c = arg1[0];
    d = arg1[1];

    if (Math.abs(d) < Math.abs(c)) {
      div = d / c;
      btm = c + d * div;
      tmp[0] = (a + b * div) / btm;
      tmp[1] = (b - a * div) / btm;
    } else {
      div = c / d;
      btm = d + c * div;
      tmp[0] = (b + a * div) / btm;
      tmp[1] = (-a + b * div) / btm;
    }
    return tmp;
  }

  /**
   * Complex multiplication
   * @param arg0 the first arg to multiply
   * @param arg1 the second arg to multiply
   * @return the product of (arg0, arg1)
   */
  public static double[] multiply(double[] arg0, double[] arg1) {
    double[] ret = new double[2];
    double a, b, c, d;
    a = arg0[0];
    b = arg0[1];
    c = arg1[0];
    d = arg1[1];
    ret[0] = a * c - b * d;
    ret[1] = b * c + a * d;
    return ret;
  }

}
