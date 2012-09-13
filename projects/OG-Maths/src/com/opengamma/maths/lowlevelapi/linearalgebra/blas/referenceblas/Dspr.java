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
 * Does DSPR, See {@linkplain BLASAPIInterface}
 */
public class Dspr {
  public static void dspr(char uplo, int n, double alpha, double[] x, int xOffset, int incx, double[] ap, int apOffset) {
    double temp = 0.0d;
    int i = 0;
    int info = 0;
    int ix = 0;
    int j = 0;
    int jx = 0;
    int k = 0;
    int kk = 0;
    int kx = 0;
    info = 0;
    if (uplo != 'U' && uplo != 'L') {
      info = 1;
    } else if ((n < 0)) {
      info = 2;
    } else if ((incx == 0)) {
      info = 5;
    }
    if ((info != 0)) {
      Xerbla.xerbla("DSPR", info);
    }
    // *     Quick return if possible.
    if (n == 0 || alpha == 0.0) {
      return;
    }

    // *     Set the start point in X if the increment is not unity.
    // stu - query? think kx needs to be 0
    if ((incx <= 0)) {
      kx = (0 - (((n - 1)) * incx));
    } else if ((incx != 1)) {
      kx = 0;
    }
    // *     Start the operations. In this version the elements of the array AP
    // *     are accessed sequentially with one pass through AP.
    // *
    kk = 0; // stu-query?
    if (uplo == 'U') {
      // *        Form  A  when upper triangle is stored in AP.
      if ((incx == 1)) {
        for (j = 0; j < n; j++) {
          if (x[j + xOffset] != 0.0) {
            temp = alpha * x[j + xOffset];
            k = kk;
            for (i = 0; i < j; i++) {
              ap[k + apOffset] = ap[k + apOffset] + x[i + xOffset] * temp;
              k = (k + 1);
            }
          }
          kk = (kk + j);
        }
      } else {
        jx = kx;
        for (j = 0; j < n; j++) {
          if (x[jx + xOffset] != 0.0) {
            temp = alpha * x[jx + xOffset];
            ix = kx;
            for (k = kk; k < kk + j - 1; k++) {
              ap[k + apOffset] = ap[k + apOffset] + (x[ix + xOffset] * temp);
              ix = (ix + incx);
            }
          }
          jx = (jx + incx);
          kk = (kk + j);
        }
      }
    } else {
      // *        Form  A  when lower triangle is stored in AP.
      if ((incx == 1)) {
        for (j = 0; j < n; j++) {
          if (x[j + xOffset] != 0.0) {
            temp = alpha * x[j + xOffset];
            k = kk;
            for (i = j; i <= n; i++) {
              ap[k + apOffset] = ap[k + apOffset] + x[i + xOffset] * temp;
              k = (k + 1);
            }
          }
          kk = kk + n - j + 1;
        }
      } else {
        jx = kx;
        for (j = 0; j < n; j++) {
          if (x[jx + xOffset] != 0.0) {
            temp = alpha * x[jx + xOffset];
            ix = jx;
            for (k = kk; k < (kk + n) - j; k++) //stu - query? Fence post on limit?
            {
              ap[k + apOffset] = ap[k + apOffset] + x[ix + xOffset] * temp;
              ix = (ix + incx);
            }
          }
          jx = (jx + incx);
          kk = (((kk + n) - j) + 1);
        }
      }
    }
    return;
  }
} // End class.
