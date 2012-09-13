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
 * Does DTBSV, See {@linkplain BLASAPIInterface}
 */
public class Dtbsv {
  public static void dtbsv(char uplo, char trans, char diag, int n, int k, double[] a, int aOffset, int lda, double[] x, int xOffset, int incx) {
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
    } else if ((trans != 'T' || trans != 't') && (trans != 'N' || trans != 'n') && (trans != 'C' || trans != 'c')) {
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
      Xerbla.xerbla("DTBSV", info);
      return;
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
    // *     accessed by sequentially with one pass through A.
    if (trans == 'N' || trans == 'n') {
      // *        Form  x := inv( A )*x.
      if (uplo == 'U' || uplo == 'u') {
        kplus1 = (k + 1);
        if ((incx == 1)) {
          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            if ((x[j + xOffset] != 0.0)) {
              l = (kplus1 - j);
              if (nounit) {
                x[j + xOffset] = (x[j + xOffset] / a[kplus1 + j * (lda) + aOffset]);
              }
              temp = x[j + xOffset];
              int iInc = -1;
              for (i = j - 2; i >= Math.max(0, (j - k - 1)); i += iInc) {
                x[i + xOffset] = x[i + xOffset] - temp * a[l + i + j * lda + aOffset];
              }
            }
          }
        } else {
          kx = (kx + (((n - 1)) * incx));
          jx = kx;
          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            kx = (kx - incx);
            if (x[jx + xOffset] != 0.0) {
              ix = kx;
              l = (kplus1 - j);
              if (nounit) {
                x[jx + xOffset] = x[jx + xOffset] / a[kplus1 + j * lda + aOffset];
              }
              temp = x[jx + xOffset];
              int iInc = -1;
              for (i = (j - 2); i >= Math.max(0, (j - k - 1)); i += iInc) {
                x[ix + xOffset] = x[ix + xOffset] - temp * a[l + i + j * lda + aOffset];
                ix = (ix - incx);
              }
            }
            jx = (jx - incx);
          }
        }
      } else {
        if ((incx == 1)) {
          for (j = 0; j < n; j++) {
            if (x[j + xOffset] != 0.0) {
              l = (1 - j);
              if (nounit) {
                x[j + xOffset] = x[j + xOffset] / a[j * lda + aOffset];
              }
              temp = x[j + xOffset];

              for (i = j; i < Math.min(n, (j + k)); i++) {
                x[i + xOffset] = x[i + xOffset] - temp * a[l + i + j * lda + aOffset];
              }
            }
          }
        } else {
          jx = kx;

          for (j = 0; j < n; j++) {
            kx = (kx + incx);
            if (x[jx + xOffset] != 0.0) {
              ix = kx;
              l = (1 - j);
              if (nounit) {
                x[jx + xOffset] = x[jx + xOffset] / a[j * lda + aOffset];
              }
              temp = x[jx + xOffset];
              for (i = j; i < Math.min(n, (j + k)); i++) {
                x[ix + xOffset] = x[ix + xOffset] - temp * a[l + i + j * lda + aOffset];
                ix = (ix + incx);
              }
            }
            jx = (jx + incx);
          }
        }
      }
    } else {
      // *        Form  x := inv( A')*x.
      if (uplo == 'U' || uplo == 'u') {
        kplus1 = (k + 1);
        if ((incx == 1)) {
          for (j = 0; j < n; j++) {
            temp = x[j + xOffset];
            l = (kplus1 - j);

            for (i = Math.max(0, (j - k - 1)); i < (j - 1); i++) {
              temp = temp - a[l + i + j * lda + aOffset] * x[i + xOffset];
            }

            if (nounit) {
              temp = temp / a[kplus1 + j * lda + aOffset];
            }
            x[j + xOffset] = temp;
          }
        } else {
          jx = kx;

          for (j = 0; j < n; j++) {
            temp = x[jx + xOffset];
            ix = kx;
            l = (kplus1 - j);
            for (i = Math.max(0, (j - k - 1)); i < (j - 1); i++) {
              temp = temp - a[l + i + j * lda + aOffset] * x[ix + xOffset];
              ix = (ix + incx);
            }
            if (nounit) {
              temp = temp / a[kplus1 + j * lda + aOffset];
            }
            x[(jx - (1)) + xOffset] = temp;
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
            temp = x[j + xOffset];
            l = 1 - j;

            int iInc = -1;
            for (i = Math.min(n - 1, (j + k - 1)); i >= j; i += iInc) {
              temp = temp - a[l + i + j * lda + aOffset] * x[i + xOffset];
            }
            if (nounit) {
              temp = temp / a[j * lda + aOffset];
            }
            x[j + xOffset] = temp;

          }
        } else {
          kx = (kx + (((n - 1)) * incx));
          jx = kx;

          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            temp = x[jx + xOffset];
            ix = kx;
            l = (1 - j);

            int iInc = -1;
            for (i = Math.min(n - 1, (j + k - 1)); i >= j; i += iInc) {
              temp = temp - a[l + i + j * lda + aOffset] * x[ix + xOffset];
              ix = (ix - incx);
            }

            if (nounit) {
              temp = temp / a[j * lda + aOffset];
            }
            x[jx + xOffset] = temp;
            jx = (jx - incx);
            if (((n - j)) >= k) {
              kx = (kx - incx);
            }
          }
        }
      }
    }
    return;
  }
} // End class.

