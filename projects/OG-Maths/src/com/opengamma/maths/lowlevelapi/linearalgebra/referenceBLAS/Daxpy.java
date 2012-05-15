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
 * Does DAXPY, See {@linkplain BLASAPIInterface}
 */
public class Daxpy {
  public static void daxpy(int n, double alpha, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy) {

    int i = 0;
    int ix = 0;
    int iy = 0;
    int m = 0;
    int mp1 = 0;
    if (n <= 0) {
      return;
    }
    if (alpha == 0.0e0) {
      return;
    }

    if ((incx == 1 && incy == 1)) {
      m = (n) % (4);
      if (m != 0) {
        for (i = 1; i <= m; i++) {
          y[(i - (1)) + yOffset] = (y[(i - (1)) + yOffset] + (alpha * x[(i - (1)) + xOffset]));
        }
      }
      if ((n < 4)) {
        return;
      }
      int iInc = 4;
      mp1 = m; //stu-query?
      for (i = mp1; i < n; i += iInc) {
        y[(i - (1)) + yOffset] = (y[(i - (1)) + yOffset] + (alpha * x[(i - (1)) + xOffset]));
        y[((i + 1) - (1)) + yOffset] = (y[((i + 1) - (1)) + yOffset] + (alpha * x[((i + 1) - (1)) + xOffset]));
        y[((i + 2) - (1)) + yOffset] = (y[((i + 2) - (1)) + yOffset] + (alpha * x[((i + 2) - (1)) + xOffset]));
        y[((i + 3) - (1)) + yOffset] = (y[((i + 3) - (1)) + yOffset] + (alpha * x[((i + 3) - (1)) + xOffset]));

      }
    } else {
      ix = 1;
      iy = 1;
      if ((incx < 0)) { //stu-query?s
        ix = (-n + 1) * incx + 1;
      }
      if ((incy < 0)) {
        ix = (-n + 1) * incy + 1;
      }
      for (i = 0; i < n; i++) {
        y[iy + yOffset] = y[iy + yOffset] + alpha * x[ix + xOffset];
        ix = ix + incx;
        iy = iy + incy;
      }
    }
    return;
  }
}
