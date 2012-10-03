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

}
