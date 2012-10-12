/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking;

import org.netlib.lapack.Dgesvd;
import org.netlib.util.intW;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;

/**
 * 
 */
public class LAPACKNetlibBacked extends LAPACKAbstractSuper implements LAPACKAPIInterface {

  @Override
  public void dgesvd(char jobu, char jobvt, int m, int n, double[] A, int lda, double[] S, double[] U, int ldu, double[] VT, int ldvt, double[] WORK, int lwork, int[] info) {//CSIGNORE
    intW infodref = new intW(info[0]);
    Dgesvd.dgesvd(String.valueOf(jobu), String.valueOf(jobvt), m, n, A, 0, lda, S, 0, U, 0, ldu, VT, 0, ldvt, WORK, 0, lwork, infodref);
  }

  @Override
  public void zgesvd(char jobu, char jobvt, int m, int n, double[] A, int lda, double[] S, double[] U, int ldu, double[] VT, int ldvt, double[] WORK, int lwork, double[] RWORK, int[] info) { //CSIGNORE
    throw new MathsExceptionNotImplemented("Netlib f2j LAPACK does not support complex numbers");
  }

}
