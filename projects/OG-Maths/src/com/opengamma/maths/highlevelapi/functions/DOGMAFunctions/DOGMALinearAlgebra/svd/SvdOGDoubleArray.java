/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd;

import com.opengamma.maths.highlevelapi.datatypes.derived.OGSvdResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd.compute;
import com.opengamma.maths.lowlevelapi.exposedapi.LAPACK;

/**
 * SVD for OGDoubleArrays 
 */
public final class SvdOGDoubleArray extends SvdAbstract<OGDoubleArray> {
  private static SvdOGDoubleArray s_instance = new SvdOGDoubleArray();

  public static SvdOGDoubleArray getInstance() {
    return s_instance;
  }

  private SvdOGDoubleArray() {
  }

  private LAPACK _localLAPACK = new LAPACK();

  @Override
  public OGSvdResult svd(OGDoubleArray array1, compute these) {
    final int m = array1.getNumberOfRows();
    final int n = array1.getNumberOfColumns();
    final int lda = Math.max(1, m);
    final int ldu = Math.max(1, m);
    final int ldvt = Math.min(m, n);
    final int lwork = Math.max(3 * Math.min(m, n) + Math.max(m, n), 5 * Math.min(m, n));
    double[] A = array1.getData(); //CSIGNORE 
    double[] S = new double[Math.min(m, n)]; //CSIGNORE 
    double[] U = null; //CSIGNORE
    double[] VT = null; //CSIGNORE
    double[] WORK = new double[Math.max(1, lwork)]; //CSIGNORE
    int[] info = new int[1];
    OGSvdResult result = null;
    OGDoubleArray resultU = null;
    OGDoubleArray resultS = null;
    OGDoubleArray resultVT = null;
    switch (these) {
      case U:
        break;

      case S:

        break;

      case V:

        break;

      case US:

        break;

      case UV:

        break;

      case SV:

        break;

      case USV:
        U = new double[lda * m];
        VT = new double[ldvt * n];
        _localLAPACK.dgesvd('A', 'A', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultU = new OGDoubleArray(U, m, m);
        // unpack S
        double[] stmp = new double[m * n];
        for (int i = 0; i < n; i++) {
          stmp[i * (m + 1)] = S[i];
        }
        resultS = new OGDoubleArray(stmp, m, n);
        resultVT = new OGDoubleArray(VT, n, n);
        result = new OGSvdResult(resultU, resultS, resultVT);
        break;
    }

    return result;
  }
}
