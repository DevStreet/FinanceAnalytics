/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

/**
 * ZASINH(Z) computes the trigonometric inverse hyperbolic sine of the complex argument.
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * See http://www.netlib.org/slatec/fnlib/casinh.f
 * Major difference is that this is in double precision arithmetic
 */
public class ZASINH {

  /**
   * Computes the trigonometric inverse hyperbolic sine at position 'x'
   * @param x the position at which to compute the trigonometric inverse hyperbolic sine 
   * @return the value of the trigonometric inverse hyperbolic sine at position 'x'
   */
  public static double[] zasinh(double[] x) {
    double[] tmp = new double[2];
    tmp[0] = -x[1];
    tmp[1] = x[0];
    tmp = ZASIN.zasin(tmp);
    double foo = tmp[0];
    tmp[0] = tmp[1];
    tmp[1] = -foo;
    return tmp;
  }
}
