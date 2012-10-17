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
    _localLAPACK = new LAPACKNativeBacked();
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
  public void dgelsd(int m, int n, int nrhs, double[] A, int lda, double[] b, int ldb, double[] s, double rcond, int[] rank, double[] work, int lwork, int[] iwork, int[] info) { // CSIGNORE
    _localLAPACK.dgelsd(m, n, nrhs, A, lda, b, ldb, s, rcond, rank, work, lwork, iwork, info);
  }

}
