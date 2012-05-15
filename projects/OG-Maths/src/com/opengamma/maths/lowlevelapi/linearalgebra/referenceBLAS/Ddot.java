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
package com.opengamma.maths.lowlevelapi.linearalgebra.referenceBLAS;

import com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking.BLASAPIInterface;

/**
 * Does DDOT, See {@linkplain BLASAPIInterface}
 */
public class Ddot {

  public static double ddot(int n, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy) {

    double dtemp = 0.0d;
    int i = 0;
    int ix = 0;
    int iy = 0;
    int m = 0;
    int mp1 = 0;
    dtemp = 0.0e0;
    if ((n <= 0)) {
      return 0; // stu - this should be an exception, or just simply not occur
    }

    if (incx == 1 && incy == 1) {
      m = n % 5;
      if (m != 0) {
        for (i = 0; i < m; i++) {
          dtemp = dtemp + x[i + xOffset] * y[i + yOffset];
        }
        if (n < 5) {
          return dtemp;
        }
      }
      mp1 = m;
      int iInc = 5;
      for (i = mp1; i < n; i += iInc) {
        dtemp = dtemp + x[i + xOffset] * y[i + yOffset] + x[i + 1 + xOffset] * y[i + 1 + yOffset] + x[i + 2 + xOffset] * y[i + 2 + yOffset] + x[i + 3 + xOffset] *
            y[i + 3 + yOffset] + x[i + 4 + xOffset] * y[i + 4 + yOffset];
      }
    } else {
      ix = 0;
      iy = 0;
      if ((incx < 0)) {
        ix = (-n + 1) * incx;
      }
      if ((incy < 0)) {
        iy = (-n + 1) * incy;
      }

      for (i = 0; i < n; i++) {
        dtemp = dtemp + x[ix + xOffset] * y[iy + yOffset];
        ix = ix + incx;
        iy = iy + incy;
      }
    }
    return dtemp;
  }
}
