/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.eig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.arbitrary.Eig;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.LAPACK;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Does eigen decompositions on OGMatrices
 */
@DOGMAMethodHook(provides = Eig.class)
public final class EigOGMatrix {
  private static Logger s_log = LoggerFactory.getLogger(EigOGMatrix.class);

  private LAPACK _localLAPACK = LAPACK.getInstance();

  @DOGMAMethodLiteral
  public List<OGArray<? extends Number>> eig(OGMatrix array1, EIGCompute these) {
    final int m = array1.getNumberOfRows();
    final int n = array1.getNumberOfColumns();
    Catchers.catchCondition(m != n, "Matrix is not square, cannot compute eigenvalues");
    final int lda = Math.max(1, n);
    double[] A = new double[m * n];//CSIGNORE
    System.arraycopy(array1.getData(), 0, A, 0, n * n); // else the data in A is destroyed
    boolean complexResult = false;
    OGArray<? extends Number> lambda = null, v = null;

    // hold real and (possible) imaginary parts of eigenvalues
    double[] wr = new double[n];
    double[] wi = new double[n];

    // pointers and vars for later relating to eigenvectors
    int ldvl, ldvr;
    double[] vl, vr;

    // not used, this routine chucks out right eigenvectors only
    ldvl = 1;
    vl = new double[1];

    // work space, malloc occurs following stage to query
    int lwork;
    double[] work;

    // info pointer
    int[] info = new int[1];

    List<OGArray<? extends Number>> tmp = new ArrayList<>();

    // jmp on request
    switch (these) {
      case LAMBDA: // eigen values only
        ldvl = 1;
        vl = new double[1]; // not used
        ldvr = 1;
        vr = new double[1]; // not used

        // workspace query
        work = new double[1];
        _localLAPACK.dgeev('N', 'N', n, A, lda, wr, wi, vl, ldvl, vr, ldvr, work, -1, info);
        lwork = (int) work[0];
        work = new double[lwork];
        // the LAPACK  call
        _localLAPACK.dgeev('N', 'N', n, A, lda, wr, wi, vl, ldvl, vr, ldvr, work, lwork, info);
        // smell info, if it's >0 LAPACK
        if (info[0] != 0) {
          int infoderef = info[0];
          if (infoderef > 0) {
            s_log.warn("The QR alg in DGEEV failed to compute all the eigenvalues requested, at most " + infoderef + " are found.");
          }
          // infoderef < 0 should be caught be xerbla
        }
        for (int i = 0; i < n; i++) {
          if (wi[i] != 0) {
            complexResult = true;
            break;
          }
        }
        if (!complexResult) {
          lambda = new OGMatrix(wr, n, 1);
        } else {
          lambda = new OGComplexMatrix(wr, wi, n, 1);
        }
        tmp.add(lambda);
        break;
      case V: // right eigen vectors only

        //malloc space for them
        ldvr = n;
        vr = new double[2 * n * ldvr]; // we allow 2*n*ldvr in case complex results are given

        // workspace query
        work = new double[1];
        _localLAPACK.dgeev('N', 'V', n, A, lda, wr, wi, vl, ldvl, vr, ldvr, work, -1, info);
        lwork = (int) work[0];
        work = new double[lwork];
        // the LAPACK  call
        _localLAPACK.dgeev('N', 'V', n, A, lda, wr, wi, vl, ldvl, vr, ldvr, work, lwork, info);
        // smell info, if it's >0 LAPACK
        if (info[0] != 0) {
          int infoderef = info[0];
          if (infoderef > 0) {
            s_log.warn("The QR alg in DGEEV failed to compute all the eigenvalues requested, at most " + infoderef + " are found.");
          }
          // infoderef < 0 should be caught be xerbla
        }
        v = unwindRightEigenvectors(wi, vr, complexResult);
        tmp.add(v);
        break;
      case V_LAMBDA:
        //malloc space for them
        ldvr = n;
        vr = new double[n * ldvr];

        // workspace query
        work = new double[1];
        _localLAPACK.dgeev('N', 'V', n, A, lda, wr, wi, vl, ldvl, vr, ldvr, work, -1, info);
        lwork = (int) work[0];
        work = new double[lwork];
        // the LAPACK  call
        _localLAPACK.dgeev('N', 'V', n, A, lda, wr, wi, vl, ldvl, vr, ldvr, work, lwork, info);
        // smell info, if it's >0 LAPACK
        if (info[0] != 0) {
          int infoderef = info[0];
          if (infoderef > 0) {
            s_log.warn("The QR alg in DGEEV failed to compute all the eigenvalues requested, at most " + infoderef + " are found.");
          }
          // infoderef < 0 should be caught be xerbla
        }
        for (int i = 0; i < n; i++) {
          if (wi[i] != 0) {
            complexResult = true;
            break;
          }
        }
        if (!complexResult) {
          lambda = new OGDiagonalMatrix(wr);
        } else {
          lambda = new OGComplexDiagonalMatrix(wr, wi, n, n);
        }
        v = unwindRightEigenvectors(wi, vr, complexResult);
        tmp.add(v);
        tmp.add(lambda);
        break;
    }

    return tmp;
  }

  private OGArray<? extends Number> unwindRightEigenvectors(double[] wi, double[] vr, boolean complexResult) {
    OGArray<? extends Number> v;
    final int n = wi.length;
    // see if any of the eigenvalues are complex, if so then the corresponding eigenvector is also complex, form up complex conjugate pairs
    for (int i = 0; i < n; i++) {
      if (wi[i] != 0) {
        complexResult = true;
        break;
      }
    }
    if (!complexResult) {
      v = new OGMatrix(Arrays.copyOf(vr, n * n), n, n);
    } else {
      double[] cmplxtmp = new double[2 * n * n];
      for (int i = 0; i < n; i++) {
        if (wi[i] == 0) {
          for (int j = 0; j < n; j++) {
            cmplxtmp[2 * i * n + 2 * j] = vr[i * n + j];
          }
        } else if (wi[i] > 0) {
          for (int j = 0; j < n; j++) {
            cmplxtmp[2 * i * n + 2 * j] = vr[i * n + j];
            cmplxtmp[2 * i * n + 2 * j + 1] = vr[(i + 1) * n + j];
          }
        } else {
          for (int j = 0; j < n; j++) {
            cmplxtmp[2 * i * n + 2 * j] = vr[(i - 1) * n + j];
            cmplxtmp[2 * i * n + 2 * j + 1] = -vr[i * n + j];
          }

        }
      }
      v = new OGComplexMatrix(cmplxtmp, n, n);
    }
    return v;
  }

}
