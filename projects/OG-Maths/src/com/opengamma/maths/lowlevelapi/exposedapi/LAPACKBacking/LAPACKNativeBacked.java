/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.exposedapi.LAPACKBacking;

import com.opengamma.maths.nativewrappers.OGLAPACKRawWrapper;

/**
 * Native library backed LAPACK
 */
public class LAPACKNativeBacked extends LAPACKAbstractSuper implements LAPACKAPIInterface {

  @Override
  public void dgesvd(char jobu, char jobvt, int m, int n, double[] A, int lda, double[] S, double[] U, int ldu, double[] VT, int ldvt, double[] WORK, int lwork, int[] info) { // CSIGNORE
    OGLAPACKRawWrapper.dgesvd(new char[] {jobu }, new char[] {jobvt }, new int[] {m }, new int[] {n }, A, new int[] {lda }, S, U, new int[] {ldu }, VT, new int[] {ldvt }, WORK, new int[] {lwork },
        info);
  }

  @Override
  public void zgesvd(char jobu, char jobvt, int m, int n, double[] A, int lda, double[] S, double[] U, int ldu, double[] VT, int ldvt, double[] WORK, int lwork, double[] RWORK, int[] info) { // CSIGNORE
    OGLAPACKRawWrapper.zgesvd(new char[] {jobu }, new char[] {jobvt }, new int[] {m }, new int[] {n }, A, new int[] {lda }, S, U, new int[] {ldu }, VT, new int[] {ldvt }, WORK, new int[] {lwork },
        RWORK, info);
  }

}
