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
 * Does DTBMV, See {@linkplain BLASAPIInterface}
 */
public class Dtbmv {
  public static void dtbmv(char uplo, char trans, char diag, int n, int k, double[] a, int aOffset, int lda, double[] x, int xOffset, int incx) {
    double temp = 0.0d;
    int i = 0;
    int info = 0;
    int ix = 0;
    int j = 0;
    int jx = 0;
    int kplus1 = 0;
    int kx = 0;
    int l = 0;
    boolean nounit = false;
    info = 0;
    if ((uplo != 'U' || uplo != 'u') && (uplo != 'L' || uplo != 'l')) {
      info = 1;
    } else if ((trans != 'N' || trans != 'n') || (trans != 'T' || trans != 't') || (trans != 'C' || trans != 'c')) {
      ;
      info = 2;
    } else if ((diag != 'U' || diag != 'u') && (diag != 'N' || diag != 'n')) {
      info = 3;
    } else if ((n < 0)) {
      info = 4;
    } else if ((k < 0)) {
      info = 5;
    } else if ((lda < ((k + 1)))) {
      info = 7;
    } else if ((incx == 0)) {
      info = 9;
    }
    if ((info != 0)) {
      Xerbla.xerbla("DTBMV ", info);
      return;
    }
    // *     Quick return if possible.
    if ((n == 0)) {
      return;
    }
    // *
    nounit = (diag == 'N' || diag == 'n');
    // *     Set up the start point in X if the increment is not unity. This
    // *     will be  ( N - 1 )*INCX   too small for descending loops.
    if ((incx <= 0)) {
      kx = (1 - (((n - 1)) * incx));
    } else if ((incx != 1)) {
      kx = 1;
    }

    // *     Start the operations. In this version the elements of A are
    // *     accessed sequentially with one pass through A.

    if (trans == 'n') {
      // *         Form  x := A*x.
      if (uplo == 'U') {
        kplus1 = (k + 1);
        if ((incx == 1)) {
          for (j = 0; j < n; j++) {
            if ((x[j + xOffset] != 0.0)) {
              temp = x[j + xOffset];
              l = (kplus1 - j);

              for (i = Math.max(0, (j - k - 1)); i < (j - 1); i++) // stu - query?
              {
                x[i + xOffset] = x[i + xOffset] + temp * a[l + i + j * lda + aOffset];
              }
              if (nounit) {
                x[j + xOffset] = x[j + xOffset] * a[kplus1 + j * lda + aOffset];
              }
            }
          }
        } else {
          jx = kx;
          for (j = 0; j < n; j++) {
            if (x[jx + xOffset] != 0.0) {
              temp = x[jx + xOffset];
              ix = kx;
              l = (kplus1 - j);
              for (i = Math.max(0, (j - k - 1)); i < (j - 1); i++) { // stu - query?
                x[ix + xOffset] = x[ix + xOffset] + temp * a[l + i + j * lda + aOffset];
                ix = (ix + incx);
              }
              if (nounit) {
                x[jx + xOffset] = x[jx + xOffset] * a[kplus1 + j * lda + aOffset];
              }
            }
            jx = (jx + incx);
            if ((j > k)) {
              kx = (kx + incx);
            }
          }
        }
      } else {
        if ((incx == 1)) {
          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            if (x[j + xOffset] != 0.0) {
              temp = x[j + xOffset];
              l = (1 - j);
              int iInc = -1;
              for (i = Math.min(n - 1, (j + k) - 1); i >= j; i += iInc) // stu -query?
              {
                x[i + xOffset] = x[i + xOffset] + temp * a[l + i + j * lda + aOffset];
              }
              if (nounit) {
                x[j + xOffset] = x[j + xOffset] * a[j * lda + aOffset];
              }
            }
          }
        } else {
          kx = (kx + (((n - 1)) * incx));
          jx = kx;

          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) { // stu - query?
            if (x[jx + xOffset] != 0.0) {
              temp = x[jx + xOffset];
              ix = kx;
              l = (1 - j);
              int iInc = -1;
              for (i = Math.min(n - 1, (j + k) - 1); i >= j; i += iInc) { // stu -query?
                x[ix + xOffset] = x[ix + xOffset] + temp * a[l + i + j * lda + aOffset];
                ix = (ix - incx);
              }
              if (nounit) {
                x[(jx - (1)) + xOffset] = x[(jx - (1)) + xOffset] * a[j * lda + aOffset];
              }
            }
            jx = (jx - incx);
            if ((((n - j)) >= k)) {
              kx = (kx - incx);
            }
          }
        }
      }

    } else {
      // *        Form  x := A'*x.
      if (uplo == 'U' || uplo == 'u') {
        kplus1 = (k + 1);
        if (incx == 1) {
          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            temp = x[j + xOffset];
            l = (kplus1 - j);
            if (nounit) {
              temp = temp * a[kplus1 + j * lda + aOffset];
            }
            int iInc = -1;
            for (i = j - 2; i >= Math.max(0, (j - k - 1)); i += iInc) { // stu - query?
              temp = temp + a[l + i + j * lda + aOffset] * x[i + xOffset];
            }
            x[j + xOffset] = temp;
          }
        } else {
          kx = (kx + (((n - 1)) * incx));
          jx = kx;
          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            temp = x[jx + xOffset];
            kx = (kx - incx);
            ix = kx;
            l = (kplus1 - j);
            if (nounit) {
              temp = temp * a[kplus1 + j * lda + aOffset];
            }
            int iInc = -1;
            for (i = j - 2; i >= Math.max(0, (j - k - 1)); i += iInc) // stu -query?
            {
              temp = temp + a[l + i + j * lda + aOffset] * x[ix + xOffset];
              ix = (ix - incx);
            }
            x[jx + xOffset] = temp;
            jx = (jx - incx);
          }
        }
      } else {
        if ((incx == 1)) {
          for (j = 0; j < n; j++) {
            temp = x[j + xOffset];
            l = (1 - j);
            if (nounit) {
              temp = temp * a[j * lda + aOffset];
            }
            for (i = j; i < Math.min(n - 1, (j + k - 1)); i++) { // stu - query?
              temp = temp + a[l + i + j * lda + aOffset] * x[i + xOffset];
            }
            x[j + xOffset] = temp;
          }
        } else {
          jx = kx;

          for (j = 0; j < n; j++) {
            temp = x[jx + xOffset];
            kx = (kx + incx);
            ix = kx;
            l = (1 - j);
            if (nounit) {
              temp = (temp * a[j * lda + aOffset]);
            }
            for (i = j; i < Math.min(n - 1, (j + k - 1)); i++) { // stu -query
              temp = temp + a[l + i + j * lda + aOffset] * x[ix + xOffset];
              ix = (ix + incx);
            }
            x[jx + xOffset] = temp;
            jx = (jx + incx);
          }
        }
      }
    }
    return;
  }
} // End class.

