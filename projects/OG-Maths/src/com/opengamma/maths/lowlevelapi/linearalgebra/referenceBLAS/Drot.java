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
 * Does DROT, See {@linkplain BLASAPIInterface}
 */
public class Drot {
  public static void drot(int n, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy, double c, double s) {
    double dtemp = 0.0d;
    int i = 0;
    int ix = 0;
    int iy = 0;
    if ((n <= 0)) {
      return;
    }
    if (((incx == 1) && (incy == 1))) {
      // *
      // *       code for both increments equal to 1
      // *
      for (i = 0; i < n; i++) {
        dtemp = c * x[i + xOffset] + s * y[i + yOffset];
        y[i + yOffset] = c * y[i + yOffset] - s * x[i + xOffset];
        x[i + xOffset] = dtemp;
      }
    } else {
      // *       code for unequal increments or equal increments not equal
      // *         to 1
      ix = 0;
      iy = 0;
      if ((incx < 0)) {
        ix = (-n + 1) * incx;
      }
      if ((incy < 0)) {
        iy = (-n + 1) * incy;
      }

      for (i = 0; i < n; i++) {
        dtemp = c * x[ix + xOffset] + s * y[iy + yOffset];
        y[iy + yOffset] = c * y[iy + yOffset] - s * x[ix + xOffset];
        x[ix + xOffset] = dtemp;
        ix = (ix + incx);
        iy = (iy + incy);
      }
    }
    return;
  }
} // End class.

