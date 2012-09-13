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
 * Does DGER, See {@linkplain BLASAPIInterface}
 */
public class Dger {
  public static void dger(int m, int n, double alpha, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy, double[] a, int aOffset, int lda) {
    double temp = 0.0d;
    int i = 0;
    int info = 0;
    int ix = 0;
    int j = 0;
    int jy = 0;
    int kx = 0;
    info = 0;
    if ((m < 0)) {
      info = 1;
    } else if ((n < 0)) {
      info = 2;
    } else if ((incx == 0)) {
      info = 5;
    } else if ((incy == 0)) {
      info = 7;
    } else if ((lda < Math.max(1, m))) {
      info = 9;
    }
    if ((info != 0)) {
      Xerbla.xerbla("DGER  ", info);
    }
    // *     Quick return if possible.
    if (m == 0 || n == 0 || alpha == 0.0) {
      return;
    }
    // *     Start the operations. In this version the elements of A are
    // *     accessed sequentially with one pass through A.

    if (incy > 0) {
      jy = 1;
    } else {
      jy = (1 - (((n - 1)) * incy)); //stu-query?
    }
    if ((incx == 1)) {
      for (j = 0; j < n; j++) {
        if (y[jy + yOffset] != 0.0) {
          temp = alpha * y[jy + yOffset];
          for (i = 0; i < m; i++) {
            a[i + j * lda + aOffset] = a[i + j * lda + aOffset] + x[i + xOffset] * temp;
          }
        }
        jy = (jy + incy);
      }
    } else {
      if ((incx > 0)) {
        kx = 1;
      } else {
        kx = 1 - (((m - 1)) * incx); //stu-query?
      }
      for (j = 0; j < n; j++) {
        if (y[jy + yOffset] != 0.0) {
          temp = alpha * y[jy + yOffset];
          ix = kx;
          for (i = 0; i <= m; i++) {
            a[i + j * lda + aOffset] = a[i + j * lda + aOffset] + x[ix + xOffset] * temp;
            ix = (ix + incx);
          }
        }
        jy = (jy + incy);
      }
    }
    return;
  }
} // End class.
