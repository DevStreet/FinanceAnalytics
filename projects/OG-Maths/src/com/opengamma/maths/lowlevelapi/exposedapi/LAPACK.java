/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi;

import com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking.LAPACKAPIInterface;
import com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking.LAPACKAbstractSuper;
import com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking.LAPACKNativeBacked;
import com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking.LAPACKNetlibBacked;
import com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking.LAPACKOGJavaBacked;

/**
 * Provides a unified interface to SLATEC
 */
public class LAPACK implements LAPACKAPIInterface {

  private LAPACKAbstractSuper _localLAPACK;

  /**
   * Backing type
   */
  public enum backing {
    /**
     * OG Java backed LAPACK
     */
    OGjava,
    /**
     * OG Native backed LAPACK
     */
    OGnative,
    /**
     * Netlib f2j backed LAPACK
     */
    Netlib
  }

  // for now plumb in netlib
  public LAPACK() {
    _localLAPACK = new LAPACKNetlibBacked();
  }

  public LAPACK(backing backedby) {
    if (backedby == backing.OGjava) {
      _localLAPACK = new LAPACKOGJavaBacked();
    } else if (backedby == backing.OGnative) {
      _localLAPACK = new LAPACKNativeBacked();
    } else if (backedby == backing.Netlib) {
      _localLAPACK = new LAPACKNetlibBacked();
    }

  }

  @Override
  public void dgesvd(char jobu, char jobvt, int m, int n, double[] A, int lda, double[] S, double[] U, int ldu, double[] VT, int ldvt, double[] WORK, int lwork, int[] info) { //CSIGNORE
    _localLAPACK.dgesvd(jobu, jobvt, m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
  }

  @Override
  public void zgesvd(char jobu, char jobvt, int m, int n, double[] A, int lda, double[] S, double[] U, int ldu, double[] VT, int ldvt, double[] WORK, int lwork, double[] RWORK, int[] info) { //CSIGNORE
    _localLAPACK.zgesvd(jobu, jobvt, m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, RWORK, info);
  }

  @Override
  public void dgetrf(int m, int n, double[] A, int lda, int[] IPIV, int[] INFO) { //CSIGNORE
    _localLAPACK.dgetrf(m, n, A, lda, IPIV, INFO);
  }

  @Override
  public void dgetrs(char trans, int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, int[] info) {
    _localLAPACK.dgetrs(trans, n, nrhs, a, lda, ipiv, b, ldb, info);
  }

  @Override
  public void dgelsd(int m, int n, int nrhs, double[] A, int lda, double[] b, int ldb, double[] s, double rcond, int[] rank, double[] work, int lwork, int[] iwork, int[] info) { // CSIGNORE
    _localLAPACK.dgelsd(m, n, nrhs, A, lda, b, ldb, s, rcond, rank, work, lwork, iwork, info);
  }

  @Override
  public void dtrtrs(char uplo, char trans, char diag, int n, int nrhs, double[] a, int lda, double[] b, int ldb, int[] info) {
    _localLAPACK.dtrtrs(uplo, trans, diag, n, nrhs, a, lda, b, ldb, info);
  }

  @Override
  public void dpotrf(char uplo, int n, double[] a, int lda, int[] info) {
    _localLAPACK.dpotrf(uplo, n, a, lda, info);
  }

  @Override
  public void dpotrs(char uplo, int n, int nrhs, double[] a, int lda, double[] b, int ldb, int[] info) {
    _localLAPACK.dpotrs(uplo, n, nrhs, a, lda, b, ldb, info);
  }

  @Override
  public int ilaenv(int ispec, char[] name, char[] opts, int n1, int n2, int n3, int n4) {
    return _localLAPACK.ilaenv(ispec, name, opts, n1, n2, n3, n4);
  }

}
