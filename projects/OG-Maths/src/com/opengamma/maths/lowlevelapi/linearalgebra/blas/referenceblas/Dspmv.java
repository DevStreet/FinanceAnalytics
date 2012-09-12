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
 * Does DSPMV, See {@linkplain BLASAPIInterface}
 */
public class Dspmv {
  public static void dspmv(char uplo, int n, double alpha, double[] ap, int apOffset, double[] x, int xOffset, int incx, double beta, double[] y, int yOffset, int incy) {
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
    if ((uplo != 'U') && (uplo != 'L')) {
      info = 1;
    } else if ((n < 0)) {
      info = 2;
    } else if ((incx == 0)) {
      info = 6;
    } else if ((incy == 0)) {
      info = 9;
    }
    if ((info != 0)) {
      Xerbla.xerbla("DSPMV ", info);
      return;
    }
    // *     Quick return if possible.
    if (n == 0 || (alpha == 0.0 && beta == 1.0)) {
      return;
    }
    // *     Set up the start points in  X  and  Y.
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

    // *     Start the operations. In this version the elements of the array AP
    // *     are accessed sequentially with one pass through AP.
    // *     First form  y := beta*y.
    if ((beta != 1.0)) {
      if ((incy == 1)) {
        if ((beta == 0.0)) {
          for (i = 0; i < n; i++) {
            y[i + yOffset] = 0.0;
          }
        } else {
          for (i = 0; i < n; i++) {
            y[i + yOffset] = beta * y[i + yOffset];
          }
        }
      } else {
        iy = ky;
        if (beta == 0.0) {
          for (i = 0; i < n; i++) {
            y[iy + yOffset] = 0.0;
            iy = (iy + incy);
          }
        } else {
          for (i = 0; i < n; i++) {
            y[iy + yOffset] = beta * y[iy + yOffset];
            iy = (iy + incy);
          }

        }
      }
    }
    if ((alpha == 0.0)) {
      return;
    }

    kk = 0; // stu - query?
    if (uplo == 'U') {
      // *        Form  y  when AP contains the upper triangle.
      if (incx == 1 && incy == 1) {
        for (j = 0; j < n; j++) {
          temp1 = alpha * x[j + xOffset];
          temp2 = 0.0;
          k = kk;
          for (i = 0; i < (j - 1); i++) {
            y[i + yOffset] = y[i + yOffset] + temp1 * ap[k + apOffset];
            temp2 = temp2 + ap[k + apOffset] * x[i + xOffset];
            k = (k + 1);
          }
          y[j + yOffset] = y[j + yOffset] + temp1 * ap[kk + j + apOffset] + alpha * temp2;
          kk = kk + j;
        }
      } else {
        jx = kx;
        jy = ky;
        for (j = 0; j < n; j++) {
          temp1 = alpha * x[jx + xOffset];
          temp2 = 0.0;
          ix = kx;
          iy = ky;
          for (k = kk; k < ((kk + j) - 2); k++) {
            y[iy + yOffset] = y[iy + yOffset] + temp1 * ap[k + apOffset];
            temp2 = temp2 + ap[k + apOffset] * x[ix + xOffset];
            ix = (ix + incx);
            iy = (iy + incy);
          }
          y[jy + yOffset] = y[jy + yOffset] + temp1 * ap[kk + j + apOffset] + alpha * temp2;
          jx = (jx + incx);
          jy = (jy + incy);
          kk = (kk + j);
        }

      }

    } else {
      // *        Form  y  when AP contains the lower triangle.
      if (incx == 1 && incy == 1) {
        for (j = 0; j < n; j++) {
          temp1 = alpha * x[j + xOffset];
          temp2 = 0.0;
          y[j + yOffset] = y[j + yOffset] + temp1 * ap[kk + apOffset];
          k = kk + 1;
          for (i = j; i < n; i++) {
            y[i + yOffset] = y[i + yOffset] + temp1 * ap[k + apOffset];
            temp2 = temp2 + ap[k + apOffset] * x[i + xOffset];
            k = (k + 1);
          }
          y[j + yOffset] = y[j + yOffset] + (alpha * temp2);
          kk = kk + (n - j) + 1;
        }
      } else {
        jx = kx;
        jy = ky;
        for (j = 0; j < n; j++) {
          temp1 = alpha * x[jx + xOffset];
          temp2 = 0.0;
          y[jy + yOffset] = y[jy + yOffset] + temp1 * ap[kk + apOffset];
          ix = jx;
          iy = jy;
          for (k = kk; k < (kk + n) - j; k++) {
            ix = (ix + incx);
            iy = (iy + incy);
            y[iy + yOffset] = y[iy + yOffset] + temp1 * ap[k + apOffset];
            temp2 = temp2 + ap[k + apOffset] * x[ix + xOffset];
          }
          y[jy + yOffset] = y[jy + yOffset] + alpha * temp2;
          jx = (jx + incx);
          jy = (jy + incy);
          kk = kk + (n - j) + 1;
        }
      }
    }
    return;
  }
} // End class.

