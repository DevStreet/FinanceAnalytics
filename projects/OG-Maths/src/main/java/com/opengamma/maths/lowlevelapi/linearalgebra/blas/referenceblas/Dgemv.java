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
 * Does DGEMV, See {@linkplain BLASAPIInterface}
 */
public class Dgemv {
  public static void dgemv(char trans, int m, int n, double alpha, double[] aMatrix, int aMatrixOffset, int lda, double[] x,
      int xOffset, int incx, double beta, double[] y, int yOffset, int incy) {
    double temp = 0.0d;
    int i = 0;
    int info = 0;
    int ix = 0;
    int iy = 0;
    int j = 0;
    int jx = 0;
    int jy = 0;
    int kx = 0;
    int ky = 0;
    int lenx = 0;
    int leny = 0;
    info = 0;

    if (trans != 'N' && trans != 'T' && trans != 'C') {
      info = 1;
    } else if ((m < 0)) {
      info = 2;
    } else if ((n < 0)) {
      info = 3;
    } else if ((lda < Math.max(1, m))) {
      info = 6;
    } else if ((incx == 0)) {
      info = 8;
    } else if ((incy == 0)) {
      info = 11;
    }
    if ((info != 0)) {
      Xerbla.xerbla("DGEMV ", info);
    }
    // *     Quick return if possible.
    if (m == 0 || n == 0 || (alpha == 0.0 && beta == 1.0)) {
      return;
    }
    // *     Set  LENX  and  LENY, the lengths of the vectors x and y, and set
    // *     up the start points in  X  and  Y.
    if (trans == 'N') {
      lenx = n;
      leny = m;
    } else {
      lenx = m;
      leny = n;
    }

    if (incx > 0) {
      kx = 0;
    } else {
      kx = (1 - (((lenx - 1)) * incx)); //stu-query?
    }
    if ((incy > 0)) {
      ky = 0;
    } else {
      ky = (1 - (((leny - 1)) * incy)); //stu-query?
    }

    // *     Start the operations. In this version the elements of A are
    // *     accessed sequentially with one pass through A.
    // *     First form  y := beta*y.
    if (beta != 1.0) {
      if ((incy == 1)) {
        if ((beta == 0.0)) {
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
        if ((beta == 0.0)) {
          for (i = 0; i < leny; i++) {
            y[iy + yOffset] = 0.0;
            iy = iy + incy;
          }
        } else {
          for (i = 0; i < leny; i++) {
            y[iy + yOffset] = beta * y[iy + yOffset];
            iy = (iy + incy);
          }
        }
      }
    }
    if ((alpha == 0.0)) {
      return;
    }
    if (trans == 'N') {
      // *        Form  y := alpha*A*x + y.
      jx = kx;
      if (incy == 1) {
        for (j = 0; j < n; j++) {
          if (x[jx + xOffset] != 0.0) {
            temp = alpha * x[jx + xOffset];
            for (i = 0; i < m; i++) {
              y[i + yOffset] = y[i + yOffset] + temp * aMatrix[i + j * lda + aMatrixOffset];
            }
          }
          jx = (jx + incx);
        }
      } else {
        for (j = 0; j < n; j++) {
          if (x[jx + xOffset] != 0.0) {
            temp = alpha * x[jx + xOffset];
            iy = ky;
            for (i = 0; i < m; i++) {
              y[iy + yOffset] = y[iy + yOffset] + temp * aMatrix[i + j * lda + aMatrixOffset];
              iy = (iy + incy);
            }
          }
          jx = (jx + incx);
        }
      }
    } else {
      // *        Form  y := alpha*A'*x + y.
      jy = ky;
      if ((incx == 1)) {
        for (j = 0; j < n; j++) {
          temp = 0.0;
          for (i = 0; i < m; i++) {
            temp = temp + aMatrix[i + j * lda + aMatrixOffset] * x[i + xOffset];
          }
          y[jy + yOffset] = y[jy + yOffset] + (alpha * temp);
          jy = (jy + incy);
        }
      } else {
        for (j = 0; j < n; j++) {
          temp = 0.0;
          ix = kx;
          for (i = 0; i < m; i++) {
            temp = temp + aMatrix[i + j * lda + aMatrixOffset] * x[ix + xOffset];
            ix = (ix + incx);
          }
          y[jy + yOffset] = y[jy + yOffset] + (alpha * temp);
          jy = (jy + incy);
        }
      }
    }
    return;
  }
} // End class.

