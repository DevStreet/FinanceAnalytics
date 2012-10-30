/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
/**
* This code is a translation of the BLAS code provided by netlib.org.
* It has been manually edited based on the results of the f2j project.
* 
*/
package com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas;

import com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking.BLASAPIInterface;

/**
 * Does DNRM2, See {@linkplain BLASAPIInterface}
 */
public class Dnrm2 {
  public static double dnrm2(int n, double[] x, int xOffset, int incx) {
    double absxi = 0.0d;
    double norm = 0.0d;
    double scale = 0.0d;
    double ssq = 0.0d;
    int ix = 0;
    if (((n < 1) || (incx < 1))) {
      norm = 0.0;
    } else if (n == 1) {
      norm = Math.abs(x[0 + xOffset]);
    } else {
      scale = 0.0;
      ssq = 1.0;
      // *        The following loop is equivalent to this call to the LAPACK
      // *        auxiliary routine:
      // *        CALL DLASSQ( N, X, INCX, SCALE, SSQ )
      for (ix = 0; ix < n * incx; ix += incx) {
        if (x[ix + xOffset] != 0.0) {
          absxi = Math.abs(x[ix + xOffset]);
          if (scale < absxi) {
            ssq = 1.0 + ssq * (scale / absxi) * (scale / absxi);
            scale = absxi;
          } else {
            ssq = ssq + (absxi / scale) * (absxi / scale);
          }
        }
      }
      norm = scale * Math.sqrt(ssq);
    }
    return norm;
  }
} // End class.

