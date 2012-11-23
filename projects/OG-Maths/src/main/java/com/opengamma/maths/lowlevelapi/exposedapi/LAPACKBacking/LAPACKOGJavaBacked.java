/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking;

/**
 * 
 */
public class LAPACKOGJavaBacked extends LAPACKAbstractSuper implements LAPACKAPIInterface {

  @Override
  public void dgesvd(char jobu, char jobvt, int m, int n, double[] a, int lda, double[] s, double[] u, int ldu, double[] vt, int ldvt, double[] work, int lwork, int[] info) {
  }

  @Override
  public void zgesvd(char jobu, char jobvt, int m, int n, double[] a, int lda, double[] s, double[] u, int ldu, double[] vt, int ldvt, double[] work, int lwork, double[] rwork, int[] info) {
  }

  @Override
  public void dgetrf(int m, int n, double[] a, int lda, int[] ipiv, int[] info) {
  }

  @Override
  public void dgetrs(char trans, int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, int[] info) {
  }

  @Override
  public void dgelsd(int m, int n, int nrhs, double[] a, int lda, double[] b, int ldb, double[] s, double rcond, int[] rank, double[] work, int lwork, int[] iwork, int[] info) {
  }

  @Override
  public void dtrtrs(char uplo, char trans, char diag, int n, int nrhs, double[] a, int lda, double[] b, int ldb, int[] info) {
  }

  @Override
  public void dpotrf(char uplo, int n, double[] a, int lda, int[] info) {
  }

  @Override
  public void dpotrs(char uplo, int n, int nrhs, double[] a, int lda, double[] b, int ldb, int[] info) {
  }

  @Override
  public int ilaenv(int ispec, char[] name, char[] opts, int n1, int n2, int n3, int n4) {
    return 0;
  }

  @Override
  public void dgeev(char jobvl, char jobvr, int n, double[] a, int lda, double[] wr, double[] wi, double[] vl, int ldvl, double[] vr, int ldvr, double[] work, int lwork, int[] info) {
  }

}
