/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking;

import org.netlib.lapack.Dgecon;
import org.netlib.lapack.Dgeev;
import org.netlib.lapack.Dgels;
import org.netlib.lapack.Dgelsd;
import org.netlib.lapack.Dgesvd;
import org.netlib.lapack.Dgetrf;
import org.netlib.lapack.Dgetrs;
import org.netlib.lapack.Dlange;
import org.netlib.lapack.Dlansy;
import org.netlib.lapack.Dlantr;
import org.netlib.lapack.Dpocon;
import org.netlib.lapack.Dpotrf;
import org.netlib.lapack.Dpotrs;
import org.netlib.lapack.Dtrcon;
import org.netlib.lapack.Dtrtrs;
import org.netlib.lapack.Ilaenv;
import org.netlib.util.doubleW;
import org.netlib.util.intW;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;

/**
 * 
 */
public class LAPACKNetlibBacked extends LAPACKAbstractSuper implements LAPACKAPIInterface {

  @Override
  public void dgesvd(char jobu, char jobvt, int m, int n, double[] A, int lda, double[] S, double[] U, int ldu, double[] VT, int ldvt, double[] WORK, int lwork, int[] info) {//CSIGNORE
    intW infoderef = new intW(info[0]);
    Dgesvd.dgesvd(String.valueOf(jobu), String.valueOf(jobvt), m, n, A, 0, lda, S, 0, U, 0, ldu, VT, 0, ldvt, WORK, 0, lwork, infoderef);
    info[0] = infoderef.val;
  }

  @Override
  public void zgesvd(char jobu, char jobvt, int m, int n, double[] A, int lda, double[] S, double[] U, int ldu, double[] VT, int ldvt, double[] WORK, int lwork, double[] RWORK, int[] info) { //CSIGNORE
    throw new MathsExceptionNotImplemented("Netlib f2j LAPACK does not support complex numbers");
  }

  @Override
  public void dgetrf(int m, int n, double[] A, int lda, int[] ipiv, int[] info) {//CSIGNORE
    intW infoderef = new intW(info[0]);
    Dgetrf.dgetrf(m, n, A, 0, lda, ipiv, 0, infoderef);
    info[0] = infoderef.val;
  }

  @Override
  public void dgetrs(char trans, int n, int nrhs, double[] a, int lda, int[] ipiv, double[] b, int ldb, int[] info) {
    intW infoderef = new intW(info[0]);
    Dgetrs.dgetrs(String.valueOf(trans), n, nrhs, a, 0, lda, ipiv, 0, b, 0, ldb, infoderef);
    info[0] = infoderef.val;
  }

  @Override
  public void dgels(char trans, int m, int n, int nrhs, double[] A, int lda, double[] B, int ldb, double[] work, int lwork, int[] info) { //CSIGNORE
    intW infoderef = new intW(info[0]);
    Dgels.dgels(String.valueOf(trans), m, n, nrhs, A, 0, lda, B, 0, ldb, work, 0, lwork, null);
    info[0] = infoderef.val;
  }

  @Override
  public void dgelsd(int m, int n, int nrhs, double[] A, int lda, double[] b, int ldb, double[] s, double rcond, int[] rank, double[] work, int lwork, int[] iwork, int[] info) { //CSIGNORE
    intW rankderef = new intW(rank[0]);
    intW infoderef = new intW(info[0]);
    Dgelsd.dgelsd(m, n, nrhs, A, 0, lda, b, 0, ldb, s, 0, rcond, rankderef, work, 0, lwork, iwork, 0, infoderef);
    rank[0] = rankderef.val;
    info[0] = infoderef.val;
  }

  @Override
  public void dtrtrs(char uplo, char trans, char diag, int n, int nrhs, double[] a, int lda, double[] b, int ldb, int[] info) {
    intW infoderef = new intW(info[0]);
    Dtrtrs.dtrtrs(String.valueOf(uplo), String.valueOf(trans), String.valueOf(diag), n, nrhs, a, 0, lda, b, 0, ldb, infoderef);
    info[0] = infoderef.val;
  }

  @Override
  public void dpotrf(char uplo, int n, double[] a, int lda, int[] info) {
    intW infoderef = new intW(info[0]);
    Dpotrf.dpotrf(String.valueOf(uplo), n, a, 0, lda, infoderef);
    info[0] = infoderef.val;
  }

  @Override
  public void dpotrs(char uplo, int n, int nrhs, double[] a, int lda, double[] b, int ldb, int[] info) {
    intW infoderef = new intW(info[0]);
    Dpotrs.dpotrs(String.valueOf(uplo), n, nrhs, a, 0, lda, b, 0, ldb, infoderef);

  }

  @Override
  public int ilaenv(int ispec, char[] name, char[] opts, int n1, int n2, int n3, int n4) {
    // the byte code translated ilaenv is broken, seems to return some strange values including a -1 for ispec=9
    // a quick look at the byte code seems to show iconst_m1 is loaded and returned
    return Ilaenv.ilaenv(ispec, String.valueOf(name), String.valueOf(opts), n1, n2, n3, n4);
  }

  @Override
  public void dgeev(char jobvl, char jobvr, int n, double[] a, int lda, double[] wr, double[] wi, double[] vl, int ldvl, double[] vr, int ldvr, double[] work, int lwork, int[] info) {
    intW infoderef = new intW(info[0]);
    Dgeev.dgeev(String.valueOf(jobvl), String.valueOf(jobvr), n, a, lda, 0, wr, 0, wi, 0, vl, 0, ldvl, vr, 0, ldvr, work, 0, lwork, infoderef);
    info[0] = infoderef.val;
  }

  @Override
  public double dlansy(char norm, char uplo, int n, double[] a, int lda, double[] work) {
    return Dlansy.dlansy(String.valueOf(norm), String.valueOf(uplo), n, a, 0, lda, work, 0);
  }

  @Override
  public double dlantr(char norm, char uplo, char diag, int m, int n, double[] a, int lda, double[] work) {
    return Dlantr.dlantr(String.valueOf(norm), String.valueOf(uplo), String.valueOf(diag), m, n, a, 0, lda, work, 0);
  }

  @Override
  public double dlange(char norm, int m, int n, double[] a, int lda, double[] work) {
    return Dlange.dlange(String.valueOf(norm), m, n, a, 0, lda, work, 0);
  }

  @Override
  public void dtrcon(char norm, char uplo, char diag, int n, double[] a, int lda, double[] rcond, double[] work, int[] iwork, int[] info) {
    intW infoderef = new intW(info[0]);
    doubleW rcondderef = new doubleW(rcond[0]);
    Dtrcon.dtrcon(String.valueOf(norm), String.valueOf(uplo), String.valueOf(diag), n, a, 0, lda, rcondderef, work, 0, iwork, 0, infoderef);
    info[0] = infoderef.val;
    rcond[0] = rcondderef.val;
  }

  @Override
  public void dpocon(char uplo, int n, double[] a, int lda, double anorm, double[] rcond, double[] work, int[] iwork, int[] info) {
    intW infoderef = new intW(info[0]);
    doubleW rcondderef = new doubleW(rcond[0]);
    Dpocon.dpocon(String.valueOf(uplo), n, a, 0, lda, anorm, rcondderef, work, 0, iwork, 0, infoderef);
    info[0] = infoderef.val;
    rcond[0] = rcondderef.val;
  }

  @Override
  public void dgecon(char norm, int n, double[] a, int lda, double anorm, double[] rcond, double[] work, int[] iwork, int[] info) {
    intW infoderef = new intW(info[0]);
    doubleW rcondderef = new doubleW(rcond[0]);
    Dgecon.dgecon(String.valueOf(norm), n, a, 0, lda, anorm, rcondderef, work, 0, iwork, 0, infoderef);
    info[0] = infoderef.val;
    rcond[0] = rcondderef.val;
  }
}
