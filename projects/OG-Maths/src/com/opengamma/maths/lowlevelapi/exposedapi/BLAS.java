/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi;

import com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking.BLASAPIInterface;
import com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking.BLASOGJavaBacked;
import com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking.BLASReferenceJavaBacked;
import com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking.BLASNativeBacked;
import com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking.BLASAbstractSuper;

/**
 * Provides a unified interface to BLASs
 */
public class BLAS implements BLASAPIInterface {

  private BLASAbstractSuper _localBLAS;

  /**
   * Backing type
   */
  public enum backing {
    /**
     * Reference Java backed BLAS
     */
    Referencejava,
    /**
     * OG Java backed BLAS
     */
    OGjava,
    /**
     * OG Native backed BLAS
     */
    OGnative
  }

  public BLAS() {
    _localBLAS = new BLASReferenceJavaBacked();
  }

  public BLAS(backing backedby) {
    if (backedby == backing.Referencejava) {
      _localBLAS = new BLASReferenceJavaBacked();
    } else if (backedby == backing.OGjava) {
      _localBLAS = new BLASOGJavaBacked();
    } else if (backedby == backing.OGnative) {
      _localBLAS = new BLASNativeBacked();
    }
  }
  
  @Override
  public void drotg(double a, double b, double c, double s) {
    _localBLAS.drotg(a, b, c, s);
  }

  @Override
  public void drotmg(double dd1, double dd2, double dx1, double dy2, double[] dPARAM) {
    _localBLAS.drotmg(dd1, dd2, dx1, dy2, dPARAM);
  }

  @Override
  public void drot(int n, double[] x, int incx, double[] y, int incy, double c, double s) {
    _localBLAS.drot(n, x, incx, y, incy, c, s);
  }

  @Override
  public void drotm(int n, double[] x, int incx, double[] y, int incy, double[] dPARAM) {
    _localBLAS.drotm(n, x, incx, y, incy, dPARAM);
  }

  @Override
  public void dswap(int n, double[] x, int incx, double[] y, int incy) {
    _localBLAS.dswap(n, x, incx, y, incy);
  }

  @Override
  public void dscal(int n, double alpha, double[] x, int incx) {
    _localBLAS.dscal(n, alpha, x, incx);
  }

  @Override
  public void dcopy(int n, double[] x, int incx, double[] y, int incy) {
    _localBLAS.dcopy(n, x, incx, y, incy);
  }

  @Override
  public void daxpy(int n, double alpha, double[] x, int incx, double[] y, int incy) {
    _localBLAS.daxpy(n, alpha, x, incx, y, incy);
  }

  @Override
  public double ddot(int n, double[] x, int incx, double[] y, int incy) {
    return _localBLAS.ddot(n, x, incx, y, incy);
  }

  @Override
  public double dnrm2(int n, double[] x, int incx) {
    return _localBLAS.dnrm2(n, x, incx);
  }

  @Override
  public double dasum(int n, double[] x, int incx) {
    return _localBLAS.dasum(n, x, incx);
  }

  @Override
  public int idamax(int n, double[] x, int incx) {
    return _localBLAS.idamax(n, x, incx);
  }

  @Override
  public void dgemv(char trans, int m, int n, double alpha, double[] aMatrix, int lda, double[] x, int incx, double beta, double[] y, int incy) {
    _localBLAS.dgemv(trans, m, n, alpha, aMatrix, lda, x, incx, beta, y, incy);        
  }

  @Override
  public void dgemm(char transa, char transb, int m, int n, int k, double alpha, double[] aMatrix, int lda, double[] bMatrix, int ldb, double beta, double[] cMatrix, int ldc) {
    _localBLAS.dgemm(transa, transb, m, n, k, alpha, aMatrix, lda, bMatrix, ldb, beta, cMatrix, ldc);
  }


}
