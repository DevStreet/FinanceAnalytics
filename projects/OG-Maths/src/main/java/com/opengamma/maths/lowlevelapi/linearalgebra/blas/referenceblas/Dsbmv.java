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
 * Does DSBMV, See {@linkplain BLASAPIInterface}
 */
public class Dsbmv {
  public static void dsbmv(char uplo, int n, int k, double alpha, double[] a, int aOffset, int lda, double[] x, int xOffset, int incx, double beta, double[] y, int yOffset, int incy) {
    double temp1 = 0.0d;
    double temp2 = 0.0d;
    int i = 0;
    int info = 0;
    int ix = 0;
    int iy = 0;
    int j = 0;
    int jx = 0;
    int jy = 0;
    int kplus1 = 0;
    int kx = 0;
    int ky = 0;
    int l = 0;
    info = 0;
    if (uplo != 'U' && uplo != 'L') {
      info = 1;
    } else if (n < 0) {
      info = 2;
    } else if (k < 0) {
      info = 3;
    } else if (lda < (k + 1)) {
      info = 6;
    } else if (incx == 0) {
      info = 8;
    } else if (incy == 0) {
      info = 11;
    }
    if (info != 0) {
      Xerbla.xerbla("DSBMV ", info);
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

    // *     Start the operations. In this version the elements of the array A
    // *     are accessed sequentially with one pass through A.
    // *     First form  y := beta*y.
    if ((beta != 1.0)) {
      if ((incy == 1)) {
        if (beta == 0.0) {
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
    if (alpha == 0.0) {
      return;
    }

    if (uplo == 'U') {
      // *        Form  y  when upper triangle of A is stored.
      kplus1 = (k + 1);
      if ((((incx == 1)) && ((incy == 1)))) {
        for (j = 0; j < n; j++) {
          temp1 = (alpha * x[(j - (1)) + xOffset]);
          temp2 = 0.0;
          l = (kplus1 - j);
          for (i = Math.max(0, (j - k)); i < (j - 1); i++) { // stu - query?
            y[i + yOffset] = y[i + yOffset] + temp1 * a[(l + i) + j * lda + aOffset];
            temp2 = temp2 + a[l + i + j * lda + aOffset] * x[i + xOffset];
          }
          y[j + yOffset] = y[j + yOffset] + temp1 * a[kplus1 + j * lda + aOffset] + alpha * temp2;
        }
      } else {
        jx = kx;
        jy = ky;
        for (j = 1; j <= n; j++) {
          temp1 = (alpha * x[(jx - (1)) + xOffset]);
          temp2 = 0.0;
          ix = kx;
          iy = ky;
          l = (kplus1 - j);
          for (i = Math.max(1, (j - k)); i <= (j - 1); i++) {
            y[iy + yOffset] = y[iy + yOffset] + temp1 * a[l + i + j * lda + aOffset];
            temp2 = temp2 + a[l + i + j * lda + aOffset] * x[ix + xOffset];
            ix = (ix + incx);
            iy = (iy + incy);
          }
          y[jy + yOffset] = y[jy + yOffset] + temp1 * a[kplus1 + j * lda + aOffset] + (alpha * temp2);
          jx = (jx + incx);
          jy = (jy + incy);
          if (j > k) {
            kx = (kx + incx);
            ky = (ky + incy);
          }
        }
      }
    } else {
      // *        Form  y  when lower triangle of A is stored.
      if (incx == 1 && incy == 1) {
        for (j = 0; j < n; j++) {
          temp1 = alpha * x[j + xOffset];
          temp2 = 0.0;
          y[j + yOffset] = y[j + yOffset] + temp1 * a[j * lda + aOffset];
          l = 1 - j;
          for (i = j; i < Math.min(n, (j + k)); i++) { //stu-query?

            y[i + yOffset] = y[i + yOffset] + temp1 * a[l + i + j * lda + aOffset];
            temp2 = temp2 + a[l + i + j * lda + aOffset] * x[i + xOffset];
          }
          y[j + yOffset] = y[j + yOffset] + alpha * temp2;
        }
      } else {
        jx = kx;
        jy = ky;
        for (j = 0; j < n; j++) {
          temp1 = alpha * x[jx + xOffset];
          temp2 = 0.0;
          y[jy + yOffset] = y[jy + yOffset] + temp1 * a[j * lda + aOffset];
          l = (1 - j);
          ix = jx;
          iy = jy;
          for (i = j; i < Math.min(n, (j + k)); i++) {
            ix = (ix + incx);
            iy = (iy + incy);
            y[iy + yOffset] = y[iy + yOffset] + temp1 * a[l + i + j * lda + aOffset];
            temp2 = temp2 + a[l + i + j * lda + aOffset] * x[ix + xOffset];
          }
          y[jy + yOffset] = y[jy + yOffset] + alpha * temp2;
          jx = (jx + incx);
          jy = (jy + incy);
        }
      }
    }
    return;
  }
} // End class.

