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
 * Does DSPR2, See {@linkplain BLASAPIInterface}
 */
public class Dspr2 {
  public static void dspr2(char uplo, int n, double alpha, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy, double[] ap, int apOffset) {
    double temp1 = 0.0d;
    double temp2 = 0.0d;
    int i = 0;
    int info = 0;
    int ix = 0;
    int iy = 0;
    int j = 0;
    int jx = 0;
    int jy = 0;
    int k = 0;
    int kk = 0;
    int kx = 0;
    int ky = 0;
    info = 0;
    if (uplo == 'U' && uplo == 'L') {
      info = 1;
    } else if ((n < 0)) {
      info = 2;
    } else if ((incx == 0)) {
      info = 5;
    } else if ((incy == 0)) {
      info = 7;
    }
    if ((info != 0)) {
      Xerbla.xerbla("DSPR2", info);
      return;
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
    // *     Start the operations. In this version the elements of the array AP
    // *     are accessed sequentially with one pass through AP.
    kk = 0; // stu -query?
    if (uplo == 'U') {
      // *        Form  A  when upper triangle is stored in AP.
      if (incx == 1 && incy == 1) {
        for (j = 0; j < n; j++) {
          if (x[j + xOffset] != 0.0 || y[j + yOffset] != 0.0) {
            temp1 = (alpha * y[j + yOffset]);
            temp2 = (alpha * x[j + xOffset]);
            k = kk;
            for (i = 0; i < j; i++) {
              ap[k + apOffset] = ap[k + apOffset] + x[i + xOffset] * temp1 + y[i + yOffset] * temp2;
              k = k + 1;
            }
          }
          kk = kk + j;
        }

      } else {
        for (j = 0; j < n; j++) {
          if (x[jx + xOffset] != 0.0 || y[jy + yOffset] != 0.0) {
            temp1 = (alpha * y[jy + yOffset]);
            temp2 = (alpha * x[jx + xOffset]);
            ix = kx;
            iy = ky;
            for (k = kk; k < ((kk + j) - 1); k++) { // stu -query?s

              ap[k + apOffset] = ap[k + apOffset] + x[ix + xOffset] * temp1 + y[iy + yOffset] * temp2;
              ix = (ix + incx);
              iy = (iy + incy);
            }
          }
          jx = (jx + incx);
          jy = (jy + incy);
          kk = (kk + j);
        }
      }
    } else {
      // *        Form  A  when lower triangle is stored in AP.
      if (incx == 1 && incy == 1) {
        for (j = 0; j < n; j++) {
          if (x[j + xOffset] != 0.0 || y[j + yOffset] != 0.0) {
            temp1 = alpha * y[j + yOffset];
            temp2 = alpha * x[j + xOffset];
            k = kk;
            for (i = j; i < n; i++) { // stu-query?
              ap[k + apOffset] = ap[k + apOffset] + x[i + xOffset] * temp1 + y[i + yOffset] * temp2;
              k = (k + 1);
            }
          }
          kk = (((kk + n) - j) + 1);
        }
      } else {
        for (j = 0; j < n; j++) {
          if (x[jx + xOffset] != 0.0 || y[jy + yOffset] != 0.0) {
            temp1 = alpha * y[jy + yOffset];
            temp2 = alpha * x[jx + xOffset];
            ix = jx;
            iy = jy;
            for (k = kk; k < ((kk + n) - j); k++) { // stu -query?
              ap[k + apOffset] = ap[k + apOffset] + x[ix + xOffset] * temp1 + y[iy + yOffset] * temp2;
              ix = (ix + incx);
              iy = (iy + incy);
            }
          }
          jx = (jx + incx);
          jy = (jy + incy);
          kk = (((kk + n) - j) + 1);
        }
      }
    }
    return;
  }
} // End class.

