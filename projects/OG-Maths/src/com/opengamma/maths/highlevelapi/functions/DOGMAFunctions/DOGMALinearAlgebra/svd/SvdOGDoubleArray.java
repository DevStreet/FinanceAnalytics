/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd;

import com.opengamma.maths.highlevelapi.datatypes.derived.OGSvdResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGDoubleArray;
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
  private TransposeOGDoubleArray _transpose = TransposeOGDoubleArray.getInstance();

  @Override
  public OGSvdResult svd(OGDoubleArray array1, compute these) {
    final int m = array1.getNumberOfRows();
    final int n = array1.getNumberOfColumns();
    final int lda = Math.max(1, m);
    final int ldu = Math.max(1, m);
    final int ldvt = Math.min(m, n);
    final int lwork = Math.max(3 * Math.min(m, n) + Math.max(m, n), 5 * Math.min(m, n));
    double[] A = new double[m * n];//CSIGNORE
    System.arraycopy(array1.getData(), 0, A, 0, m * n); // else the data in A is destroyed
    double[] S = new double[Math.min(m, n)]; //CSIGNORE 
    double[] U = null; //CSIGNORE
    double[] VT = null; //CSIGNORE
    double[] WORK = new double[Math.max(1, lwork)]; //CSIGNORE
    int[] info = new int[1];
    OGDoubleArray resultU = null;
    OGArraySuper<Number> resultS = null;
    OGArraySuper<Number> resultV = null;
    double[] pointLess = new double[1];  // this is to keep JNI happy, it doesn't consider "null" an array type
    switch (these) {
      case U:
        U = new double[lda * m];
        VT = pointLess;
        _localLAPACK.dgesvd('A', 'N', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultU = new OGDoubleArray(U, m, m);
        break;

      case S:
        U = pointLess;
        VT = pointLess;
        _localLAPACK.dgesvd('N', 'N', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultS = new OGDoubleArray(S, n, 1);
        break;

      case V:
        U = pointLess;
        VT = new double[n * n];
        _localLAPACK.dgesvd('N', 'A', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultV = _transpose.transpose(new OGDoubleArray(VT, n, n));
        break;

      case US:
        U = new double[lda * m];
        VT = pointLess;
        _localLAPACK.dgesvd('A', 'N', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultU = new OGDoubleArray(U, m, m);
        resultS = new OGDiagonalArray(S, m, n);
        break;

      case UV:
        U = new double[lda * m];
        VT = new double[n * n];
        _localLAPACK.dgesvd('A', 'A', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultU = new OGDoubleArray(U, m, m);
        resultV = _transpose.transpose(new OGDoubleArray(VT, n, n));
        break;

      case SV:
        U = pointLess;
        VT = new double[n * n];
        _localLAPACK.dgesvd('A', 'A', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultS = new OGDiagonalArray(S, m, n);
        resultV = _transpose.transpose(new OGDoubleArray(VT, n, n));
        break;

      case USV:
        U = new double[lda * m];
        VT = new double[ldvt * n];
        _localLAPACK.dgesvd('A', 'A', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultU = new OGDoubleArray(U, m, m);
        resultS = new OGDiagonalArray(S, m, n);
        // rather inefficient but necessary evil for the minute
        // TODO: in place transpose of column major array 
        resultV = _transpose.transpose(new OGDoubleArray(VT, n, n));
        break;
    }
    return new OGSvdResult(resultU, resultS, resultV);
  }

}
