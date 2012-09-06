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
 * Does DSWAP, See {@linkplain BLASAPIInterface}
 */
public class Dswap {
  public static void dswap(int n, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy) {
    double dtemp = 0.0d;
    int i = 0;
    int ix = 0;
    int iy = 0;
    int m = 0;
    int mp1 = 0;
    if (n <= 0) {
      return;
    }
    if (incx == 1 && incy == 1) {
      m = n % 3;
      if (m != 0) {
        for (i = 0; i < m; i++) {
          dtemp = x[i + xOffset];
          x[i + xOffset] = y[i + yOffset];
          y[i + yOffset] = dtemp;
        }
        if (n < 3) {
          return;
        }
      }
      mp1 = m;
      int iInc = 3;
      for (i = mp1; i < n; i += iInc) {
        dtemp = x[i + xOffset];
        x[i + xOffset] = y[i + yOffset];
        y[i + yOffset] = dtemp;
        dtemp = x[i + 1 + xOffset];
        x[i + 1 + xOffset] = y[i + 1 + yOffset];
        y[i + 1 + yOffset] = dtemp;
        dtemp = x[i + 2 + xOffset];
        x[i + 2 + xOffset] = y[i + 2 + yOffset];
        y[i + 2 + yOffset] = dtemp;
      }
    } else {
      // *       code for unequal increments or equal increments not equal
      // *         to 1
      ix = 0;
      iy = 0;
      if ((incx < 0)) {
        ix = (-n + 1) * incx; // stu -query?
      }
      if ((incy < 0)) {
        iy = (-n + 1) * incy; // stu -query?
      }
      {
        for (i = 0; i < n; i++) {
          dtemp = x[ix + xOffset];
          x[ix + xOffset] = y[iy + yOffset];
          y[iy + yOffset] = dtemp;
          ix = (ix + incx);
          iy = (iy + incy);
        }
      }
    }
    return;
  }
} // End class.

