/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking;

import com.opengamma.maths.nativewrappers.OGBLASRawWrapper;

/**
 * Native library backed BLAS
 */
public class BLASNativeBacked extends BLASAbstractSuper implements BLASAPIInterface {

  @Override
  public void drotg(double[] a, double[] b, double[] c, double[] s) {
    OGBLASRawWrapper.drotg(a, b, c, s);
  }

  @Override
  public void drotmg(double[] dd1, double[] dd2, double[] dx1, double[] dy2, double[] dPARAM) {
    OGBLASRawWrapper.drotmg(dd1, dd2, dx1, dy2, dPARAM);
  }

  @Override
  public void drot(int n, double[] x, int incx, double[] y, int incy, double c, double s) {
    OGBLASRawWrapper.drot(new int[] {n }, x, new int[] {incx }, y, new int[] {incy }, new double[] {c }, new double[] {s });
  }

  @Override
  public void drotm(int n, double[] x, int incx, double[] y, int incy, double[] dPARAM) {
    OGBLASRawWrapper.drotm(new int[] {n }, x, new int[] {incx }, y, new int[] {incy }, dPARAM);
  }

  @Override
  public void dswap(int n, double[] x, int incx, double[] y, int incy) {
    OGBLASRawWrapper.dswap(new int[] {n }, x, new int[] {incx }, y, new int[] {incy });
  }

  @Override
  public void dscal(int n, double alpha, double[] x, int incx) {
    OGBLASRawWrapper.dscal(new int[] {n }, new double[] {alpha }, x, new int[] {incx });
  }

  @Override
  public void dcopy(int n, double[] x, int incx, double[] y, int incy) {
    OGBLASRawWrapper.dcopy(new int[] {n }, x, new int[] {incx }, y, new int[] {incy });
  }

  @Override
  public void daxpy(int n, double alpha, double[] x, int incx, double[] y, int incy) {
    OGBLASRawWrapper.daxpy(new int[] {n }, new double[] {alpha }, x, new int[] {incx }, y, new int[] {incy });
  }

  @Override
  public void daxpy(int n, double alpha, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy) {
    OGBLASRawWrapper.daxpy(new int[] {n }, new double[] {alpha }, x, xOffset, new int[] {incx }, y, yOffset, new int[] {incy });
  }

  @Override
  public double ddot(int n, double[] x, int incx, double[] y, int incy) {
    return OGBLASRawWrapper.ddot(new int[] {n }, x, new int[] {incx }, y, new int[] {incy });
  }

  @Override
  public double ddot(int n, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy) {
    return OGBLASRawWrapper.ddot(new int[] {n }, x, xOffset, new int[] {incx }, y, yOffset, new int[] {incy });
  }

  @Override
  public double dnrm2(int n, double[] x, int incx) {
    return OGBLASRawWrapper.dnrm2(new int[] {n }, x, new int[] {incx });
  }

  @Override
  public double dasum(int n, double[] x, int incx) {
    return OGBLASRawWrapper.dasum(new int[] {n }, x, new int[] {incx });
  }

  @Override
  public int idamax(int n, double[] x, int incx) {
    return OGBLASRawWrapper.idamax(new int[] {n }, x, new int[] {incx }); // TODO: decide on what's best! - 1; // Fortran is 1 based
  }

  @Override
  public void dgemv(char trans, int m, int n, double alpha, double[] aMatrix, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    OGBLASRawWrapper.dgemv(new char[] {trans }, new int[] {m }, new int[] {n }, new double[] {alpha }, aMatrix, new int[] {lda }, x, new int[] {incx }, new double[] {beta }, y, new int[] {incy });
  }

  @Override
  public void dgbmv(char trans, int m, int n, int kl, int ku, double alpha, double[] aMatrix, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    OGBLASRawWrapper.dgbmv(new char[] {trans }, new int[] {m }, new int[] {n }, new int[] {kl }, new int[] {ku }, new double[] {alpha }, aMatrix, new int[] {lda }, x, new int[] {incx },
        new double[] {beta }, y, new int[] {incy });
  }

  @Override
  public void dsymv(char uplo, int n, double alpha, double[] aMatrix, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    OGBLASRawWrapper.dsymv(new char[] {uplo }, new int[] {n }, new double[] {alpha }, aMatrix, new int[] {lda }, x, new int[] {incx }, new double[] {beta }, y, new int[] {incy });
  }

  @Override
  public void dsbmv(char uplo, int n, int k, double alpha, double[] aMatrix, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    OGBLASRawWrapper.dsbmv(new char[] {uplo }, new int[] {n }, new int[] {k }, new double[] {alpha }, aMatrix, new int[] {lda }, x, new int[] {incx }, new double[] {beta }, y, new int[] {incy });
  }

  @Override
  public void dspmv(char uplo, int n, double alpha, double[] aMatrix, double[] x, int incx, double beta, double[] y, int incy) {
    OGBLASRawWrapper.dspmv(new char[] {uplo }, new int[] {n }, new double[] {alpha }, aMatrix, x, new int[] {incx }, new double[] {beta }, y, new int[] {incy });
  }

  @Override
  public void dtrmv(char uplo, char trans, char diag, int n, double[] aMatrix, int lda, double[] x, int incx) {
    OGBLASRawWrapper.dtrmv(new char[] {uplo }, new char[] {trans }, new char[] {diag }, new int[] {n }, aMatrix, new int[] {lda }, x, new int[] {incx });
  }

  @Override
  public void dtbmv(char uplo, char trans, char diag, int n, int k, double[] aMatrix, int lda, double[] x, int incx) {
    OGBLASRawWrapper.dtbmv(new char[] {uplo }, new char[] {trans }, new char[] {diag }, new int[] {n }, new int[] {k }, aMatrix, new int[] {lda }, x, new int[] {incx });
  }

  @Override
  public void dtpmv(char uplo, char trans, char diag, int n, double[] aMatrix, double[] x, int incx) {
    OGBLASRawWrapper.dtpmv(new char[] {uplo }, new char[] {trans }, new char[] {diag }, new int[] {n }, aMatrix, x, new int[] {incx });
  }

  @Override
  public void dtrsv(char uplo, char trans, char diag, int n, double[] aMatrix, int lda, double[] x, int incx) {
    OGBLASRawWrapper.dtrsv(new char[] {uplo }, new char[] {trans }, new char[] {diag }, new int[] {n }, aMatrix, new int[] {lda }, x, new int[] {incx });
  }

  @Override
  public void dtbsv(char uplo, char trans, char diag, int n, int k, double[] aMatrix, int lda, double[] x, int incx) {
    OGBLASRawWrapper.dtbsv(new char[] {uplo }, new char[] {trans }, new char[] {diag }, new int[] {n }, new int[] {k }, aMatrix, new int[] {lda }, x, new int[] {incx });
  }

  @Override
  public void dtpsv(char uplo, char trans, char diag, int n, double[] aMatrix, double[] x, int incx) {
    OGBLASRawWrapper.dtpsv(new char[] {uplo }, new char[] {trans }, new char[] {diag }, new int[] {n }, aMatrix, x, new int[] {incx });
  }

  @Override
  public void dger(int m, int n, double alpha, double[] x, int incx, double[] y, int incy, double[] aMatrix, int lda) {
    OGBLASRawWrapper.dger(new int[] {m }, new int[] {n }, new double[] {alpha }, x, new int[] {incx }, y, new int[] {incy }, aMatrix, new int[] {lda });
  }

  @Override
  public void dsyr(char uplo, int n, double alpha, double[] x, int incx, double[] aMatrix, int lda) {
    OGBLASRawWrapper.dsyr(new char[] {uplo }, new int[] {n }, aMatrix, x, new int[] {incx }, aMatrix, new int[] {lda });
  }

  @Override
  public void dspr(char uplo, int n, double alpha, double[] x, int incx, double[] aMatrix) {
    OGBLASRawWrapper.dspr(new char[] {uplo}, new int[] {n}, aMatrix, x, new int[] {incx}, aMatrix);
  }  
  
  @Override
  public void dgemm(char transa, char transb, int m, int n, int k, double alpha, double[] aMatrix, int lda, double[] bMatrix, int ldb, double beta, double[] cMatrix, int ldc) {
    OGBLASRawWrapper.dgemm(new char[] {transa }, new char[] {transb }, new int[] {m }, new int[] {n }, new int[] {k }, new double[] {alpha }, aMatrix, new int[] {lda }, bMatrix, new int[] {ldb },
        new double[] {beta }, cMatrix, new int[] {ldc });
  }

}
