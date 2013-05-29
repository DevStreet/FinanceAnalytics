/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionOverflow;
import com.opengamma.maths.lowlevelapi.functions.MathE;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;

/**
 * Computes the base 'e' (natural) logarithm of the true complete Gamma function at the absolute value of position 'x'
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * http://www.netlib.org/slatec/fnlib/dlngam.f
 */
public class DLNGAM {

  private static Logger s_log = LoggerFactory.getLogger(DLNGAM.class);

  private static double s_sq2pil = 0.91893853320467274178032973640562d;
  private static double s_sqpi2l = 0.225791352644727432363097614947441d;
  private static double s_pi = 3.14159265358979323846264338327950d;

  private static double s_xmax, s_dxrel;
  static {
    double temp = 1.d / Math.log(D1mach.two());
    s_xmax = temp * D1mach.two();
    s_dxrel = Math.sqrt(D1mach.four());
  }

  /**
   * Computes the base 'e' (natural) logarithm of the true complete Gamma function at the absolute value of position 'x'
   * @param x the position at which the base 'e' (natural) logarithm of the true complete Gamma function for the absolute value of 'x' will be computed
   * @return the base 'e' (natural) logarithm of the true complete Gamma function at the absolute value of position 'x'
   */
  public static double dlngam(final double x) {
    double y = Math.abs(x);

    if (y < 10.d) {
      return Math.log(Math.abs(DGAMMA.dgamma(x)));
    } else {
      if (y > s_xmax) {
        throw new MathsExceptionOverflow("SLATEC: DLNGAM: abs(x) so large dlngam overflows");
      }
      double dlngam = 0;
      if (x > 0.d) {
        dlngam = s_sq2pil + (x - 0.5d) * Math.log(x) - x + D9LGMC.d9lgmc(y);
        return dlngam;
      }
      double sinpiy = Math.abs(Math.sin(s_pi * y));
      if (sinpiy == 0.d) {
        throw new MathsExceptionIllegalArgument("x is a negative integer, lngam(x) goes to inf");
      }
      if (Math.abs((x - MathE.trunc(x - 0.5d)) / x) < s_dxrel) {
        s_log.warn("SLATEC: DLNGAM: Answer less than half precision as x is too near a negative integer");
      }

      dlngam = s_sqpi2l + (x - 0.5d) * Math.log(y) - x - Math.log(sinpiy) - D9LGMC.d9lgmc(y);
      return dlngam;
    }

  }

}
