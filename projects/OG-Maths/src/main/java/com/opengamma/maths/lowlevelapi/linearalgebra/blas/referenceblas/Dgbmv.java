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
 * Does DGBMV, See {@linkplain BLASAPIInterface}
 */
public class Dgbmv {

  public static void dgbmv(char trans, int m, int n, int kl, int ku, double alpha, double[] a, int aOffset, int lda, double[] x, int xOffset, int incx, double beta, double[] y, int yOffset,
      int incy) {
    double temp = 0.0d;
    int i = 0;
    int info = 0;
    int ix = 0;
    int iy = 0;
    int j = 0;
    int jx = 0;
    int jy = 0;
    int k = 0;
    int kup1 = 0;
    int kx = 0;
    int ky = 0;
    int lenx = 0;
    int leny = 0;
    info = 0;
    if (trans != 'N' && trans != 'T' && trans != 'C') {
      info = 1;
    } else if (m < 0) {
      info = 2;
    } else if (n < 0) {
      info = 3;
    } else if (kl < 0) {
      info = 4;
    } else if (ku < 0) {
      info = 5;
    } else if (lda < (kl + ku) + 1) {
      info = 8;
    } else if (incx == 0) {
      info = 10;
    } else if (incy == 0) {
      info = 13;
    }
    if (info != 0) {
      Xerbla.xerbla("DGBMV ", info);
      return;
    }

    if ((m == 0) || (n == 0) || (alpha == 0.0) && (beta == 1.0)) {
      return;
    }

    if (trans == 'N') {
      lenx = n;
      leny = m;
    } else {
      lenx = m;
      leny = n;
    }
    if ((incx > 0)) {
      kx = 1;
    } else {
      kx = 1 - (lenx - 1) * incx;
    }
    if (incy > 0) {
      ky = 1;
    } else {
      ky = 1 - (leny - 1) * incy;
    }
    // *
    // *     Start the operations. In this version the elements of A are
    // *     accessed sequentially with one pass through the band part of A.
    // *
    // *     First form  y := beta*y.
    // *
    if (beta != 1.0) {
      if (incy == 1) {
        if (beta == 0.0) {
          for (i = 0; i < leny; i++) {
            y[i + yOffset] = 0.0;
          }
        } else {
          for (i = 0; i < leny; i++) {
            y[i + yOffset] = beta * y[i + yOffset];
          }
        }
      } else {
        iy = ky;
        if (beta == 0.0) {
          for (i = 0; i < leny; i++) {
            y[iy + yOffset] = 0.0;
            iy = (iy + incy);
          }
        } else {
          for (i = 0; i < leny; i++) {
            y[iy + yOffset] = beta * y[iy + yOffset];
            iy = (iy + incy);
          }
        }
      }
    }

    if (alpha == 0.0) {
      return;
    }

    kup1 = (ku + 1);

    if (trans == 'N') {
      // *        Form  y := alpha*A*x + y.
      jx = kx;
      if (incy == 1) {
        for (j = 0; j < n; j++) {
          if (x[jx + xOffset] != 0.0) {
            temp = alpha * x[jx + xOffset];
            k = kup1 - j;
            for (i = Math.max(0, j - ku); i < Math.min(m, j + kl); i++) {
              y[i + yOffset] = y[i + yOffset] + temp * a[k + i + j * lda + aOffset];
            }
          }
          jx = (jx + incx);
        }              //  Close for() loop. 
      } else {
        for (j = 0; j < n; j++) {
          if (x[jx + xOffset] != 0.0) {
            temp = alpha * x[jx + xOffset];
            iy = ky;
            k = kup1 - j;
            for (i = Math.max(0, j - ku); i < Math.min(m, j + kl); i++) {
              y[iy + yOffset] = y[iy + yOffset] + temp * a[k + i + j * lda + aOffset];
              iy = (iy + incy);
            }
          }
          jx = (jx + incx);
          if (j > ku) {
            ky = (ky + incy);
          }
        }
      }
    } else {
      // *        Form  y := alpha*A'*x + y.
      jy = ky;
      if (incx == 1) {
        for (j = 0; j < n; j++) {
          temp = 0.0;
          k = (kup1 - j);
          for (i = Math.max(0, (j - ku)); i < Math.min(m, (j + kl)); i++) {
            temp = temp + a[(k + i) + j * lda + aOffset] * x[i + xOffset];
          }
          y[(jy) + yOffset] = (y[jy + yOffset] + (alpha * temp));
          jy = (jy + incy);
        }
      } else {
        for (j = 0; j < n; j++) {
          temp = 0.0;
          ix = kx;
          k = (kup1 - j);
          for (i = Math.max(0, (j - ku)); i < Math.min(m, (j + kl)); i++) {
            temp = (temp + (a[(k + i) + j * lda + aOffset] * x[ix + xOffset]));
            ix = (ix + incx);
          }
          y[(jy) + yOffset] = y[jy + yOffset] + alpha * temp;
          jy = jy + incy;
          if ((j > ku)) {
            kx = (kx + incx);
          }
        }
      }
    }
    return;

  } // End class.

} // end class
