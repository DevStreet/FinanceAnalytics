/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi;

import com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking.LAPACKAPIInterface;
import com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking.LAPACKAbstractSuper;
import com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking.LAPACKNativeBacked;

/**
 * Provides a unified interface to SLATEC
 */
public final class LAPACK implements LAPACKAPIInterface {

  private static class LAPACKRef {
    public static final LAPACK s_instance = new LAPACK();
  }

  public static LAPACK getInstance() {
    return LAPACKRef.s_instance;
  }

  private LAPACKAbstractSuper _localLAPACK;

  private LAPACK() {
    _localLAPACK = new LAPACKNativeBacked();
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
  public void dgels(char trans, int m, int n, int nrhs, double[] A, int lda, double[] B, int ldb, double[] work, int lwork, int[] info) { // CSIGNORE
    _localLAPACK.dgels(trans, m, n, nrhs, A, lda, B, ldb, work, lwork, info);
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

  @Override
  public void dgeev(char jobvl, char jobvr, int n, double[] a, int lda, double[] wr, double[] wi, double[] vl, int ldvl, double[] vr, int ldvr, double[] work, int lwork, int[] info) {
    _localLAPACK.dgeev(jobvl, jobvr, n, a, lda, wr, wi, vl, ldvl, vr, ldvr, work, lwork, info);
  }

  @Override
  public double dlansy(char norm, char uplo, int n, double[] a, int lda, double[] work) {
    return _localLAPACK.dlansy(norm, uplo, n, a, lda, work);
  }

  @Override
  public double dlantr(char norm, char uplo, char diag, int m, int n, double[] a, int lda, double[] work) {
    return _localLAPACK.dlantr(norm, uplo, diag, m, n, a, lda, work);
  }

  @Override
  public double dlange(char norm, int m, int n, double[] a, int lda, double[] work) {
    return _localLAPACK.dlange(norm, m, n, a, lda, work);
  }

  @Override
  public void dtrcon(char norm, char uplo, char diag, int n, double[] a, int lda, double[] rcond, double[] work, int[] iwork, int[] info) {
    _localLAPACK.dtrcon(norm, uplo, diag, n, a, lda, rcond, work, iwork, info);
  }

  @Override
  public void dpocon(char uplo, int n, double[] a, int lda, double anorm, double[] rcond, double[] work, int[] iwork, int[] info) {
    _localLAPACK.dpocon(uplo, n, a, lda, anorm, rcond, work, iwork, info);
  }

  @Override
  public void dgecon(char norm, int n, double[] a, int lda, double anorm, double[] rcond, double[] work, int[] iwork, int[] info) {
    _localLAPACK.dgecon(norm, n, a, lda, anorm, rcond, work, iwork, info);
  }

}
