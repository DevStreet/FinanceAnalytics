/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;

/**
 * Computes the complex tangent of a double precision complex number
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * http://www.netlib.org/slatec/src/ctan.f
 */
public class ZTAN {

  private static final double s_sqeps = Math.sqrt(D1mach.four());
  private static Logger s_log = LoggerFactory.getLogger(ZTAN.class);

  public static double[] ztan(double[] z) {
    double x2 = 2. * z[0];
    double y2 = 2. * z[1];
    double sn2x = Math.sin(x2);
    double den = Math.cos(x2) + Math.cosh(y2);
    if (den == 0) {
      throw new MathsExceptionIllegalArgument("Tan() is singular (real part is pi/2 or 3*pi/2 and imaginary part is 0");
    }
    if (Math.abs(den) <= Math.max(Math.abs(x2), 1) * s_sqeps) {
      s_log.warn("SLATEC: ZTAN: Answer is to less than half precision, abs(real(z)) is too big or real(z) is too near pi/2 or 3*pi/2");
    }
    return new double[] {sn2x / den, Math.sinh(y2) / den };
  }
}
