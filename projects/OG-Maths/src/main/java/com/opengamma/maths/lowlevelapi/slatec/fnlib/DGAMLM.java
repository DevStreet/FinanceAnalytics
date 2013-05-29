/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConvergence;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;

/**
 * DGAMLM(X) provides the ability to calculate the minimum and maximum legal bounds for x in dgamma(x).
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * See http://www.netlib.org/slatec/fnlib/dgamlm.f
 */
public class DGAMLM {

  public static void dgamlm(double[] xmin, double[] xmax) {
    double alnbig, alnsml, xln, xold;
    alnsml = Math.log(D1mach.one());
    xmin[0] = -alnsml;
    int i;
    for (i = 0; i < 10; i++) {
      xold = xmin[0];
      xln = Math.log(xmin[0]);
      xmin[0] -= xmin[0] * ((xmin[0] + 0.5d) * xln - xmin[0] - 0.2258d + alnsml) / (xmin[0] * xln + 0.5d);
      if (Math.abs(xmin[0] - xold) < 0.005d) {
        break;
      }
    }
    if (i == 10) {
      throw new MathsExceptionNonConvergence("Unable to find a value for xmin");
    }
    xmin[0] = -xmin[0] + 0.01d;

    alnbig = Math.log(D1mach.two());
    xmax[0] = alnbig;
    for (i = 0; i < 10; i++) {
      xold = xmax[0];
      xln = Math.log(xmax[0]);
      xmax[0] -= xmax[0] * ((xmax[0] - 0.5d) * xln - xmax[0] + 0.9189d - alnbig) / (xmax[0] * xln - 0.5d);
      if (Math.abs(xmax[0] - xold) < 0.005d) {
        break;
      }
    }
    if (i == 10) {
      throw new MathsExceptionNonConvergence("Unable to find a value for xmax");
    }
    xmax[0] = xmax[0] - 0.01d;
    xmin[0] = Math.max(xmin[0], -xmax[0] + 1.d);
  }

}
