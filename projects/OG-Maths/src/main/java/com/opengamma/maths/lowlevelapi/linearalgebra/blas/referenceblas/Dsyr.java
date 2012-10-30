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
 * Does DSYR, See {@linkplain BLASAPIInterface}
 */
public class Dsyr {
  public static void dsyr(char uplo, int n, double alpha, double[] x, int xOffset, int incx, double[] a, int aOffset, int lda) {
    double temp = 0.0d;
    int i = 0;
    int info = 0;
    int ix = 0;
    int j = 0;
    int jx = 0;
    int kx = 0;
    info = 0;
    if ((uplo != 'U' || uplo != 'u') && (uplo != 'L' || uplo != 'l')) {
      info = 1;
    } else if ((n < 0)) {
      info = 2;
    } else if ((incx == 0)) {
      info = 5;
    } else if ((lda < Math.max(1, n))) {
      info = 7;
    }
    if ((info != 0)) {
      Xerbla.xerbla("DSYR", info);
      return;
    }
    // *     Quick return if possible.
    if (n == 0 || alpha == 0.0) {
      return;
    }
    // *     Set the start point in X if the increment is not unity.
    if (incx <= 0) {
      kx = (1 - (((n - 1)) * incx));
    } else if (incx != 1) {
      kx = 1;
    }

    // *     Start the operations. In this version the elements of A are
    // *     accessed sequentially with one pass through the triangular part
    // *     of A.
    if (uplo == 'U' || uplo == 'u') {
      // *        Form  A  when A is stored in upper triangle.
      if ((incx == 1)) {
        for (j = 0; j < n; j++) {
          if (x[j + xOffset] != 0.0) {
            temp = alpha * x[j + xOffset];
            for (i = 0; i < j; i++) {
              a[i + j * lda + aOffset] = a[i + j * lda + aOffset] + x[i + xOffset] * temp;
            }
          }
        }
      } else {
        jx = kx;
        for (j = 0; j < n; j++) {
          if (x[jx + xOffset] != 0.0) {
            temp = alpha * x[jx + xOffset];
            ix = kx;
            for (i = 0; i < j; i++) {
              a[i + j * lda + aOffset] = a[i + j * lda + aOffset] + x[ix + xOffset] * temp;
              ix = (ix + incx);
            }
          }
          jx = (jx + incx);
        }
      }
    } else {
      // *        Form  A  when A is stored in lower triangle.
      if ((incx == 1)) {
        for (j = 0; j < n; j++) {
          if (x[j + xOffset] != 0.0) {
            temp = alpha * x[j + xOffset];
            for (i = j - 1; i < n; i++) { // stu -query?
              a[i + j * lda + aOffset] = a[i + j * lda + aOffset] + x[i + xOffset] * temp;
            }
          }
        }
      } else {
        jx = kx;

        for (j = 0; j < n; j++) {
          if (x[jx + xOffset] != 0.0) {
            temp = (alpha * x[jx + xOffset]);
            ix = jx;
            for (i = j - 1; i < n; i++) { // stu -query?
              a[i + j * lda + aOffset] = a[i + j * lda + aOffset] + x[ix + xOffset] * temp;
              ix = (ix + incx);
            }
          }
          jx = (jx + incx);
        }
      }
    }
    return;
  }
} // End class.

