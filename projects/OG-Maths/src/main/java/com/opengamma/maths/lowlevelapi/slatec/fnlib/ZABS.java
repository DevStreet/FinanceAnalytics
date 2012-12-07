/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;


/**
 * Computes the absolute value or magnitude of a double precision complex number
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * http://www.netlib.org/slatec/src/zabs.f
 */
public class ZABS {

  public static double zabs(double zr, double zi) {
    double u, v, q, s;
    u = Math.abs(zr);
    v = Math.abs(zi);
    s = u + v;
    s = s * 1.0d;
    if (s == 0.0d) {
      return 0.0d;
    }
    if (u <= v) {
      q = u / v;
      return v * Math.sqrt(1.d + q * q);
    } else {
      q = v / u;
      return u * Math.sqrt(1.d + q * q);
    }
  }
}
