/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;

/**
 * 
 */
public class LAPACKOGJavaBacked extends LAPACKAbstractSuper implements LAPACKAPIInterface {

  @Override
  public void dgesvd(char jobu, char jobvt, int m, int n, double[] a, int lda, double[] s, double[] u, int ldu, double[] vt, int ldvt, double[] work, int lwork, int[] info) {
    throw new MathsExceptionNotImplemented("DGESVD is not implemented/wired up");
  }

  @Override
  public void zgesvd(char jobu, char jobvt, int m, int n, double[] a, int lda, double[] s, double[] u, int ldu, double[] vt, int ldvt, double[] work, int lwork, double[] rwork, int[] info) {
    throw new MathsExceptionNotImplemented("ZGESVD is not implemented/wired up");
  }

  @Override
  public void dgetrf(int m, int n, double[] a, int lda, int[] ipiv, int[] info) {
    throw new MathsExceptionNotImplemented("DGETRF is not implemented/wired up");
  }

  @Override
  public void dgetrs(char trans, int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, int[] info) {
    throw new MathsExceptionNotImplemented("DGETRS is not implemented/wired up");
  }

  @Override
  public void dgelsd(int m, int n, int nrhs, double[] a, int lda, double[] b, int ldb, double[] s, double rcond, int[] rank, double[] work, int lwork, int[] iwork, int[] info) {
    throw new MathsExceptionNotImplemented("DGELSD is not implemented/wired up");
  }

  @Override
  public void dtrtrs(char uplo, char trans, char diag, int n, int nrhs, double[] a, int lda, double[] b, int ldb, int[] info) {
    throw new MathsExceptionNotImplemented("DTRTRS is not implemented/wired up");
  }

  @Override
  public void dpotrf(char uplo, int n, double[] a, int lda, int[] info) {
    throw new MathsExceptionNotImplemented("DPOTRF is not implemented/wired up");
  }

  @Override
  public void dpotrs(char uplo, int n, int nrhs, double[] a, int lda, double[] b, int ldb, int[] info) {
    throw new MathsExceptionNotImplemented("DPOTRS is not implemented/wired up");
  }

  @Override
  public int ilaenv(int ispec, char[] name, char[] opts, int n1, int n2, int n3, int n4) {
    throw new MathsExceptionNotImplemented("ILAENV is not implemented/wired up");
  }

  @Override
  public void dgeev(char jobvl, char jobvr, int n, double[] a, int lda, double[] wr, double[] wi, double[] vl, int ldvl, double[] vr, int ldvr, double[] work, int lwork, int[] info) {
    throw new MathsExceptionNotImplemented("DGEEV is not implemented/wired up");
  }

  @Override
  public void dlansy(char norm, char uplo, int n, double[] a, int lda, double[] work) {
    throw new MathsExceptionNotImplemented("DLANSY is not implemented/wired up");
  }

  @Override
  public void dlantr(char norm, char uplo, char diag, int m, int n, double[] a, int lda, double[] work) {
    throw new MathsExceptionNotImplemented("DLANTR is not implemented/wired up");
  }

  @Override
  public void dlange(char norm, int m, int n, double[] a, int lda, double[] work) {
    throw new MathsExceptionNotImplemented("DLANGE is not implemented/wired up");
  }

  @Override
  public void dtrcon(char norm, char uplo, char diag, int n, double[] a, int lda, double[] rcond, double[] work, int[] iwork, int[] info) {
    throw new MathsExceptionNotImplemented("DTRCON is not implemented/wired up");
  }

  @Override
  public void dpocon(char uplo, int n, double[] a, int lda, double anorm, double[] rcond, double[] work, int[] iwork, int[] info) {
    throw new MathsExceptionNotImplemented("DPOCON is not implemented/wired up");
  }

  @Override
  public void dgecon(char norm, int n, double[] a, int lda, double anorm, double[] rcond, double[] work, int[] iwork, int[] info) {
    throw new MathsExceptionNotImplemented("DGECON is not implemented/wired up");
  }

}
