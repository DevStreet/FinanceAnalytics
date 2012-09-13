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
 * Does DTPMV, See {@linkplain BLASAPIInterface}
 */
public class Dtpmv {
  public static void dtpmv(char uplo, char trans, char diag, int n, double[] ap, int apOffset, double[] x, int xOffset, int incx) {
    double temp = 0.0d;
    int i = 0;
    int info = 0;
    int ix = 0;
    int j = 0;
    int jx = 0;
    int k = 0;
    int kk = 0;
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
    } else if ((incx == 0)) {
      info = 7;
    }
    if ((info != 0)) {
      Xerbla.xerbla("DTPMV", info);
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

    // *     Start the operations. In this version the elements of AP are
    // *     accessed sequentially with one pass through AP.

    if (trans == 'N' || trans == 'n') {
      // *        Form  x:= A*x.
      if (uplo == 'U' || uplo == 'u') {
        kk = 1;  // stu - query? perhaps 0?
        if ((incx == 1)) {
          for (j = 0; j < n; j++) {
            if (x[j + xOffset] != 0.0) {
              temp = x[j + xOffset];
              k = kk;
              for (i = 0; i < (j - 1); i++) {
                x[i + xOffset] = x[i + xOffset] + temp * ap[k + apOffset];
                k = (k + 1);
              }
              if (nounit) {
                x[j + xOffset] = x[j + xOffset] * ap[kk + j - 1 + apOffset];
              }
            }
            kk = (kk + j);
          }
        } else {
          jx = kx;
          for (j = 0; j < n; j++) {
            if (x[jx + xOffset] != 0.0) {
              temp = x[jx + xOffset];
              ix = kx;
              for (k = kk - 1; k < ((kk + j) - 2); k++) { // stu -query?
                x[ix + xOffset] = x[ix + xOffset] + temp * ap[k + apOffset];
                ix = (ix + incx);
              }
              if (nounit) {
                x[jx + xOffset] = x[jx + xOffset] * ap[kk + j - 1 + apOffset];
              }
            }
            jx = (jx + incx);
            kk = (kk + j);
          }
        }
      } else {
        kk = (((n * ((n + 1)))) / 2);
        if ((incx == 1)) {
          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            if (x[j + xOffset] != 0.0) {
              temp = x[j + xOffset];
              k = kk;
              int iInc = -1;
              for (i = n - 1; i >= j; i += iInc) {
                x[i + xOffset] = x[i + xOffset] + temp * ap[k + apOffset];
                k = (k - 1);
              }
              if (nounit) {
                x[j + xOffset] = x[j + xOffset] * ap[(kk - n) + j + apOffset];
              }
            }
            kk = kk - (((n - j) + 1));
          }
        } else {
          kx = (kx + (((n - 1)) * incx));
          jx = kx;
          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            if (x[jx + xOffset] != 0.0) {
              temp = x[jx + xOffset];
              ix = kx;
              int kInc = -1;
              for (k = kk - 1; k >= (kk - ((n - ((j + 1)))) - 1); k += kInc) { // stu -query?
                x[ix + xOffset] = x[ix + xOffset] + temp * ap[k + apOffset];
                ix = (ix - incx);
              }
              if (nounit) {
                x[jx + xOffset] = x[jx + xOffset] * ap[kk - n + j + apOffset];
              }
            }
            jx = (jx - incx);
            kk = (kk - (((n - j) + 1)));
          }
        }
      }
    } else {
      // *        Form  x := A'*x.
      if (uplo == 'U' || uplo == 'u') {
        kk = (((n * ((n + 1)))) / 2);
        if ((incx == 1)) {
          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            temp = x[j + xOffset];
            if (nounit) {
              temp = temp * ap[kk + apOffset];
            }
            k = (kk - 1);
            int iInc = -1;
            for (i = (j - 2); i >= 0; i += iInc) {
              temp = temp + ap[k + apOffset] * x[i + xOffset];
              k = (k - 1);
            }
            x[j + xOffset] = temp;
            kk = (kk - j);
          }
        } else {
          jx = (kx + (((n - 1)) * incx));
          int jInc = -1;
          for (j = n - 1; j >= 0; j += jInc) {
            temp = x[jx + xOffset];
            ix = jx;
            if (nounit) {
              temp = temp * ap[kk + apOffset];
            }
            int kInc = -1;
            for (k = (kk - 2); k >= (kk - j); k += kInc) {
              ix = (ix - incx);
              temp = temp + ap[k + apOffset] * x[ix + xOffset];
            }
            x[jx + xOffset] = temp;
            jx = (jx - incx);
            kk = (kk - j);
          }
        }
      } else {
        kk = 1; // stu - query? perhaps 0?
        if ((incx == 1)) {
          for (j = 0; j < n; j++) {
            temp = x[j + xOffset];
            if (nounit) {
              temp = temp * ap[kk + apOffset];
            }
            k = (kk + 1);
            for (i = j; i < n; i++) {
              temp = temp + ap[k + apOffset] * x[i + xOffset];
              k = (k + 1);
            }
            x[j + xOffset] = temp;
            kk = (kk + (((n - j) + 1)));
          }
        } else {
          jx = kx;
          for (j = 0; j < n; j++) {
            temp = x[jx + xOffset];
            ix = jx;
            if (nounit) {
              temp = temp * ap[kk + apOffset];
            }

            for (k = kk; k < (kk + n) - j; k++) {
              ix = (ix + incx);
              temp = temp + ap[k + apOffset] * x[ix + xOffset];
            }
            x[jx + xOffset] = temp;
            jx = (jx + incx);
            kk = (kk + (((n - j) + 1)));
          }
        }
      }
    }
    return;
  }
} // End class.
