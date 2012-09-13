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
 * Does DSYR2, See {@linkplain BLASAPIInterface}
 */
public class Dsyr2 {
  public static void dsyr2(char uplo, int n, double alpha, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy, double[] a, int aOffset, int lda) {
    double temp1 = 0.0d;
    double temp2 = 0.0d;
    int i = 0;
    int info = 0;
    int ix = 0;
    int iy = 0;
    int j = 0;
    int jx = 0;
    int jy = 0;
    int kx = 0;
    int ky = 0;
    info = 0;
    if ((uplo != 'U' || uplo != 'u') && (uplo != 'L' || uplo != 'l')) {
      info = 1;
    } else if ((n < 0)) {
      info = 2;
    } else if ((incx == 0)) {
      info = 5;
    } else if ((incy == 0)) {
      info = 7;
    } else if ((lda < Math.max(1, n))) {
      info = 9;
    }
    if ((info != 0)) {
      Xerbla.xerbla("DSYR2", info);
    }
    // *     Quick return if possible.
    if (n == 0 || alpha == 0.0) {
      return;
    }
    // *     Set up the start points in X and Y if the increments are not both
    // *     unity.
    if (incx != 1 || incy != 1) {
      if ((incx > 0)) {
        kx = 1;
      } else {
        kx = (1 - (((n - 1)) * incx));
      }
      if ((incy > 0)) {
        ky = 1;
      } else {
        ky = (1 - (((n - 1)) * incy));
      }
      jx = kx;
      jy = ky;
    }

    // *     Start the operations. In this version the elements of A are
    // *     accessed sequentially with one pass through the triangular part
    // *     of A.
    if (uplo == 'U' || uplo == 'u') {
      // *        Form  A  when A is stored in the upper triangle.
      if (incx == 1 && incy == 1) {
        for (j = 0; j < n; j++) {
          if (x[j + xOffset] != 0.0 || y[j + yOffset] != 0.0) {
            temp1 = (alpha * y[j + yOffset]);
            temp2 = (alpha * x[j + xOffset]);
            for (i = 0; i < j; i++) {
              a[i + j * lda + aOffset] = a[i + j * lda + aOffset] + x[i + xOffset] * temp1 + y[i + yOffset] * temp2;
            }
          }
        }
      } else {
        for (j = 0; j < n; j++) {
          if (x[jx + xOffset] != 0.0 || y[jy + yOffset] != 0.0) {
            temp1 = (alpha * y[jy + yOffset]);
            temp2 = (alpha * x[jx + xOffset]);
            ix = kx;
            iy = ky;
            for (i = 0; i < j; i++) {
              a[i + j * lda + aOffset] = a[i + j * lda + aOffset] + x[ix + xOffset] * temp1 + y[iy + yOffset] * temp2;
              ix = (ix + incx);
              iy = (iy + incy);
            }
          }
          jx = (jx + incx);
          jy = (jy + incy);
        }
      }
    } else {
      // *        Form  A  when A is stored in the lower triangle.
      if (incx == 1 && incy == 1) {
        for (j = 0; j < n; j++) {
          if (x[j + xOffset] != 0.0 || y[j + yOffset] != 0.0) {
            temp1 = (alpha * y[j + yOffset]);
            temp2 = (alpha * x[j + xOffset]);
            for (i = j - 1; i < n; i++) { //stu -query?
              a[i + j * lda + aOffset] = a[i + j * lda + aOffset] + x[i + xOffset] * temp1 + y[i + yOffset] * temp2;
            }
          }
        }
      } else {
        for (j = 0; j < n; j++) {
          if (x[jx + xOffset] != 0.0 || y[jy + yOffset] != 0.0) {
            temp1 = alpha * y[jy + yOffset];
            temp2 = alpha * x[jx + xOffset];
            ix = jx;
            iy = jy;
            for (i = j - 1; i < n; i++) { //stu - query?
              a[i + j * lda + aOffset] = a[i + j * lda + aOffset] + x[ix + xOffset] * temp1 + y[iy + yOffset] * temp2;
              ix = (ix + incx);
              iy = (iy + incy);
            }
          }
          jx = (jx + incx);
          jy = (jy + incy);
        }
      }
    }

    return;
  }
} // End class.

