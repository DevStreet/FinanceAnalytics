/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking;

import org.netlib.lapack.Dgelsd;
import org.netlib.lapack.Dgesvd;
import org.netlib.lapack.Dgetrf;
import org.netlib.lapack.Dtrtrs;
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

}
