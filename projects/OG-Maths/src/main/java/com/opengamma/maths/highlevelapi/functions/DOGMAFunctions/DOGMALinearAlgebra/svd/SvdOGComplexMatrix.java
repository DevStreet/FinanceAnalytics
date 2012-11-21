/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd;

import com.opengamma.maths.highlevelapi.datatypes.derived.OGSvdResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose.CtransposeOGComplexMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd.compute;
import com.opengamma.maths.lowlevelapi.exposedapi.LAPACK;

/**
 * SVD for OGComplex 
 */
public final class SvdOGComplexMatrix implements SvdAbstract<OGComplexMatrix> {
  private static SvdOGComplexMatrix s_instance = new SvdOGComplexMatrix();

  public static SvdOGComplexMatrix getInstance() {
    return s_instance;
  }

  private SvdOGComplexMatrix() {
  }

  private LAPACK _localLAPACK = new LAPACK();
  private CtransposeOGComplexMatrix _cmplxtranspose = new CtransposeOGComplexMatrix();

  @Override
  public OGSvdResult svd(OGComplexMatrix array1, compute these) {
    final int m = array1.getNumberOfRows();
    final int n = array1.getNumberOfColumns();
    final int lda = Math.max(1, m);
    final int ldu = Math.max(1, m);
    final int ldvt = Math.min(m, n);
    final int lwork = Math.max(3 * Math.min(m, n) + Math.max(m, n), 5 * Math.min(m, n));
    double[] A = new double[2 * m * n]; // 2x is complex  //CSIGNORE
    System.arraycopy(array1.getData(), 0, A, 0, 2 * m * n); // else the data in A is destroyed
    double[] S = new double[Math.min(m, n)]; //CSIGNORE 
    double[] U = null; //CSIGNORE
    double[] VT = null; //CSIGNORE
    double[] WORK = new double[2 * Math.max(1, lwork)]; // 2x is complex //CSIGNORE
    double[] RWORK = new double[5 * Math.min(m, n)]; //CSIGNORE
    int[] info = new int[1];
    OGComplexMatrix resultU = null;
    OGArray<? extends Number> resultS = null;
    OGArray<? extends Number> resultV = null; // we leave this as a super type because it has to be transposed and as such is DOGMAtised and upcast, TODO: replace when we have in place transpose

    double[] pointLess = new double[1];  // this is to keep JNI happy, it doesn't consider "null" an array type
    switch (these) {
      case U:
        U = new double[lda * m];
        VT = pointLess;
        _localLAPACK.zgesvd('A', 'N', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, RWORK, info);
        resultU = new OGComplexMatrix(U, m, m);
        break;

      case S:
        U = pointLess;
        VT = pointLess;
        _localLAPACK.zgesvd('N', 'N', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, RWORK, info);
        resultS = new OGMatrix(S, n, 1);
        break;

      case V:
        U = pointLess;
        VT = new double[n * n];
        _localLAPACK.zgesvd('N', 'A', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, RWORK, info);
        resultV = _cmplxtranspose.eval(new OGComplexMatrix(VT, n, n));
        break;

      case US:
        U = new double[lda * m];
        VT = pointLess;
        _localLAPACK.zgesvd('A', 'N', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, RWORK, info);
        resultU = new OGComplexMatrix(U, m, m);
        resultS = new OGDiagonalMatrix(S, m, n);
        break;

      case UV:
        U = new double[lda * m];
        VT = new double[n * n];
        _localLAPACK.zgesvd('A', 'A', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, RWORK, info);
        resultU = new OGComplexMatrix(U, m, m);
        resultV = _cmplxtranspose.eval(new OGComplexMatrix(VT, n, n));
        break;

      case SV:
        U = pointLess;
        VT = new double[n * n];
        _localLAPACK.zgesvd('N', 'A', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, RWORK, info);
        resultS = new OGDiagonalMatrix(S, m, n);
        resultV = _cmplxtranspose.eval(new OGComplexMatrix(VT, n, n));
        break;

      case USV:
        System.out.println("m=" + m + " n=" + n);
        U = new double[lda * m * 2];
        VT = new double[ldvt * n * 2];
        _localLAPACK.zgesvd('A', 'A', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, RWORK, info);
        resultU = new OGComplexMatrix(U, m, m);
        resultS = new OGDiagonalMatrix(S, m, n);
        // rather inefficient but necessary evil for the minute
        // TODO: in place transpose of column major array 
        resultV = _cmplxtranspose.eval(new OGComplexMatrix(VT, n, n));
        break;
    }
    return new OGSvdResult(resultU, resultS, resultV);
  }

}
