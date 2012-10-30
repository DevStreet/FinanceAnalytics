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
 * Does DTRSV, See {@linkplain BLASAPIInterface}
 */
public class Dtrsv {
  public static void dtrsv(char uplo, char trans, char diag, int n, double[] a, int aOffset, int lda, double[] x, int xOffset, int incx) {

    double temp = 0.0d;
    int i = 0;
    int info = 0;
    int ix = 0;
    int j = 0;
    int jx = 0;
    int kx = 0;
    boolean nounit = false;
    info = 0;
    if ((uplo != 'U' || uplo != 'u') && (uplo != 'L' || uplo != 'l')) {
      info = 1;
    } else if ((trans != 'N' || trans != 'n') && (trans != 'T' || trans != 't') && (trans != 'C' || trans != 'c')) {
      info = 2;
    } else if ((diag != 'U' || diag != 'u') && (diag != 'N' || diag != 'n')) {
      info = 3;
    } else if ((n < 0)) {
      info = 4;
    } else if ((lda < Math.max(1, n))) {
      info = 6;
    } else if ((incx == 0)) {
      info = 8;
    }
    if ((info != 0)) {
      Xerbla.xerbla("DTRSV", info);
    }
    // *     Quick return if possible.
    if ((n == 0)) {
      return;
    }

    nounit = (diag == 'N' || diag == 'n');

    // *     Set up the start point in X if the increment is not unity. This
    // *     will be  ( N - 1 )*INCX  too small for descending loops.

    if ((incx <= 0)) {
      kx = (1 - (((n - 1)) * incx));
    } else if ((incx != 1)) {
      kx = 1;
    }

    // *     Start the operations. In this version the elements of A are
    // *     accessed sequentially with one pass through A.

    if (trans == 'N' || trans == 'n') {
      // *        Form  x := inv( A )*x.
      if (uplo == 'U' || uplo == 'u') {
        if ((incx == 1)) {
          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            if (x[j + xOffset] != 0.0) {
              if (nounit) {
                x[j + xOffset] = x[j + xOffset] / a[j + j * lda + aOffset];
              }
              temp = x[j + xOffset];
              int iInc = -1;
              for (i = (j - 1); i >= 0; i += iInc) { // stu -query? bounds
                x[i + xOffset] = x[i + xOffset] - temp * a[i + j * lda + aOffset];
              }
            }
          }
        } else {
          jx = (kx + (((n - 1)) * incx)); // stu -query? subtract 1
          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            if (x[jx + xOffset] != 0.0) {
              if (nounit) {
                x[jx + xOffset] = x[jx + xOffset] / a[j + j * lda + aOffset];
              }
              temp = x[jx + xOffset];
              ix = jx;
              int iInc = -1;
              for (i = (j - 1); i >= 0; i += iInc) { // stu -query? bounds
                ix = (ix - incx);
                x[ix + xOffset] = x[ix + xOffset] - temp * a[i + j * lda + aOffset];
              }
            }
            jx = (jx - incx);
          }
        }
      } else {
        if ((incx == 1)) {
          for (j = 0; j < n; j++) {
            if (x[j + xOffset] != 0.0) {
              if (nounit) {
                x[j + xOffset] = x[j + xOffset] / a[j + j * lda + aOffset];
              }
              temp = x[j + xOffset];
              for (i = (j + 1); i < n; i++) { // stu -query? bounds
                x[i + xOffset] = x[i + xOffset] - temp * a[i + j * lda + aOffset];
              }

            }
          }
        } else {
          jx = kx;
          for (j = 0; j < n; j++) {
            if (x[jx + xOffset] != 0.0) {
              if (nounit) {
                x[jx + xOffset] = x[jx + xOffset] / a[j + j * lda + aOffset];
              }
              temp = x[jx + xOffset];
              ix = jx;

              for (i = (j + 1); i < n; i++) { // stu -query? bounds
                ix = (ix + incx);
                x[ix + xOffset] = x[ix + xOffset] - temp * a[i + j * lda + aOffset];
              }
            }
            jx = (jx + incx);
          }
        }
      }
    } else {
      // *
      // *        Form  x := inv( A' )*x.
      // *
      if (uplo == 'U' || uplo == 'u') {
        if ((incx == 1)) {
          for (j = 0; j < n; j++) {
            temp = x[j + xOffset];
            for (i = 0; i < (j - 1); i++) { // stu -query? bounds

              temp = temp - a[i + j * lda + aOffset] * x[i + xOffset];
            }
            if (nounit) {
              temp = temp / a[j + j * lda + aOffset];
            }
            x[j + xOffset] = temp;
          }
        } else {
          jx = kx;
          for (j = 0; j < n; j++) {
            temp = x[jx + xOffset];
            ix = kx;
            for (i = 0; i < (j - 1); i++) { // stu -query? bounds
              temp = temp - a[i + j * lda + aOffset] * x[ix + xOffset];
              ix = (ix + incx);
            }
            if (nounit) {
              temp = temp / a[j + j * lda + aOffset];
            }
            x[jx + xOffset] = temp;
            jx = (jx + incx);
          }
        }
      } else {
        if ((incx == 1)) {
          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            temp = x[j + xOffset];
            int iInc = -1;
            for (i = n - 1; i >= j; i += iInc) { // stu -query? bounds
              temp = temp - a[i + j * lda + aOffset] * x[i + xOffset];
            }
            if (nounit) {
              temp = temp / a[j + j * lda + aOffset];
            }
            x[j + xOffset] = temp;
          }
        } else {
          kx = (kx + (((n - 1)) * incx)); // stu- query? subtract 1?
          jx = kx;
          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            temp = x[jx + xOffset];
            ix = kx;
            int iInc = -1;
            for (i = n - 1; i >= j; i += iInc) { // stu -query? bounds
              temp = temp - a[i + j * lda + aOffset] * x[ix + xOffset];
              ix = (ix - incx);
            }
            if (nounit) {
              temp = temp / a[j + j * lda + aOffset];
            }
            x[jx + xOffset] = temp;
            jx = (jx - incx);
          }
        }
      }
    }
    return;
  }
} // End class.

