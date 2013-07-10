/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd;

import java.util.ArrayList;
import java.util.List;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.arbitrary.SVD;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.LAPACK;

/**
 * SVD for OGDoubleArrays 
 */
@DOGMAMethodHook(provides = SVD.class)
public final class SvdOGMatrix {

  private LAPACK _localLAPACK = LAPACK.getInstance();
  private TransposeOGMatrix _transpose = new TransposeOGMatrix();

  @DOGMAMethodLiteral
  public List<OGArray<? extends Number>> svd(OGMatrix array1) {
    return svd(array1, SVDCompute.USV);
  }

  @DOGMAMethodLiteral
  public List<OGArray<? extends Number>> svd(OGMatrix array1, SVDCompute these) {
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
    OGMatrix resultU = null;
    OGArray<? extends Number> resultS = null;
    OGArray<? extends Number> resultV = null;
    double[] pointLess = new double[1];  // this is to keep JNI happy, it doesn't consider "null" an array type
    switch (these) {
      case U:
        U = new double[lda * m];
        VT = pointLess;
        _localLAPACK.dgesvd('A', 'N', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultU = new OGMatrix(U, m, m);
        break;

      case S:
        U = pointLess;
        VT = pointLess;
        _localLAPACK.dgesvd('N', 'N', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultS = new OGMatrix(S, n, 1);
        break;

      case V:
        U = pointLess;
        VT = new double[n * n];
        _localLAPACK.dgesvd('N', 'A', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultV = _transpose.eval(new OGMatrix(VT, n, n));
        break;

      case US:
        U = new double[lda * m];
        VT = pointLess;
        _localLAPACK.dgesvd('A', 'N', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultU = new OGMatrix(U, m, m);
        resultS = new OGDiagonalMatrix(S, m, n);
        break;

      case UV:
        U = new double[lda * m];
        VT = new double[n * n];
        _localLAPACK.dgesvd('A', 'A', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultU = new OGMatrix(U, m, m);
        resultV = _transpose.eval(new OGMatrix(VT, n, n));
        break;

      case SV:
        U = pointLess;
        VT = new double[n * n];
        _localLAPACK.dgesvd('A', 'A', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultS = new OGDiagonalMatrix(S, m, n);
        resultV = _transpose.eval(new OGMatrix(VT, n, n));
        break;

      case USV:
        U = new double[lda * m];
        VT = new double[ldvt * n];
        _localLAPACK.dgesvd('A', 'A', m, n, A, lda, S, U, ldu, VT, ldvt, WORK, lwork, info);
        resultU = new OGMatrix(U, m, m);
        resultS = new OGDiagonalMatrix(S, m, n);
        // rather inefficient but necessary evil for the minute
        // TODO: in place transpose of column major array 
        resultV = _transpose.eval(new OGMatrix(VT, n, n));
        break;
    }
    List<OGArray<? extends Number>> tmp = new ArrayList<>(3);
    tmp.add(resultU);
    tmp.add(resultS);
    tmp.add(resultV);
    return tmp;
  }

}
