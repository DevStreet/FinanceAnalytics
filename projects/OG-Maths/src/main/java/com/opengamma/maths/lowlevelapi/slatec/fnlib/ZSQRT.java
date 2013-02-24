/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import com.opengamma.maths.commonapi.MathsConstants;

/**
 * ZSQRT(Z) computes the square root of the complex argument.
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * See http://www.netlib.org/slatec/fnlib/zsqrt.f
 * Major difference is the interface here is not a void call
 */
public class ZSQRT {
  private static double s_drt = 7.071067811865475244008443621e-1;
  private static double s_dpi = MathsConstants.pi;

  public static double[] zsqrt(double[] z) {
    double ar = z[0];
    double ai = z[1];
    double br = 0;
    double bi = 0;
    double zm = ZABS.zabs(ar, ai);
    double dtheta = 0;
    zm = Math.sqrt(zm);
    if (ar == 0.d) {
      if (ai > 0.d) {
        br = zm * s_drt;
        bi = zm * s_drt;
      } else if (ai < 0.d) {
        br = zm * s_drt;
        bi = -zm * s_drt;
      }
      return new double[] {br, bi };
    } else if (ai == 0.d) {
      if (ar > 0.d) {
        br = Math.sqrt(ar);
      } else {
        bi = Math.sqrt(Math.abs(ar));
      }
      return new double[] {br, bi };
    } else {
      dtheta = Math.atan(ai / ar);
      if (dtheta < 0.d) {
        if (ar < 0.d) {
          dtheta += s_dpi;
        }
      } else {
        if (ar < 0.d) {
          dtheta -= s_dpi;
        }
      }
    }
    dtheta /= 2d;
    br = zm * Math.cos(dtheta);
    bi = zm * Math.sin(dtheta);
    return new double[] {br, bi };
  }
}
