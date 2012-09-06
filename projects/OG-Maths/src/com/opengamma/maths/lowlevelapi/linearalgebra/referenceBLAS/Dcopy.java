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
 * Does DCOPY, See {@linkplain BLASAPIInterface}
 */
public class Dcopy {

  public static void dcopy(int n, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy) {

    int i = 0;
    int ix = 0;
    int iy = 0;
    int m = 0;
    int mp1 = 0;
    if ((n <= 0)) {
      return;
    }
    if (((incx == 1) && (incy == 1))) {
      m = n % 7;
      if (m != 0) {
        for (i = 0; i < m; i++) {
          y[i + yOffset] = x[i + xOffset];
        }
        if (n < 7) {
          return;
        }
      }
      mp1 = m;
      int iInc = 7;
      for (i = mp1; i < n; i += iInc) {
        y[i + yOffset] = x[i + xOffset];
        y[i + 1 + yOffset] = x[i + 1 + xOffset];
        y[i + 2 + yOffset] = x[i + 2 + xOffset];
        y[i + 3 + yOffset] = x[i + 3 + xOffset];
        y[i + 4 + yOffset] = x[i + 4 + xOffset];
        y[i + 5 + yOffset] = x[i + 5 + xOffset];
        y[i + 6 + yOffset] = x[i + 6 + xOffset];
      }
    } else {
      ix = 0;
      iy = 0;
      if ((incx < 0)) { // stu-query?
        ix = (-n + 1) * incx;
      }
      if ((incy < 0)) {
        iy = (-n + 1) * incy;
      }
      for (i = 0; i < n; i++) {
        y[iy + yOffset] = x[ix + xOffset];
        ix = ix + incx;
        iy = iy + incy;
      }
    }
    return;
  }
}
