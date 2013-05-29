/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;

/**
 * Computes the inverse hyperbolic cosine at position 'x'
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * http://www.netlib.org/slatec/fnlib/dacosh.f
 */
public class DACOSH {

  private static double s_dln2 = 0.69314718055994530941723212145818d;

  public static double dacosh(final double x) {
    double xmax = 1.d / Math.sqrt(D1mach.three());

    if (x < 1.d) {
      throw new MathsExceptionIllegalArgument("x must be greater than 1.d");
    }

    if (x < xmax) {
      return Math.log(x + Math.sqrt(x * x - 1.d));
    } else {
      return s_dln2 + Math.log(x);
    }
  }
}
