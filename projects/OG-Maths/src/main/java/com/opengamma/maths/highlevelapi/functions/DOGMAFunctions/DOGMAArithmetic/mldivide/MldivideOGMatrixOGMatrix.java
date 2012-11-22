/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mldivide;

import java.util.Arrays;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;
import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Mldivide;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.LAPACK;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Generalised linear system solver. Does the classic "backslash" operation found in a number of languages.
 */
@DOGMAMethodHook(provides = Mldivide.class)
public class MldivideOGMatrixOGMatrix implements Mldivide<OGMatrix, OGMatrix, OGMatrix> {

  private LAPACK _lapack = new LAPACK();

  @Override
  public OGMatrix eval(OGMatrix array1, OGMatrix array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);
    int len;
    int rows1 = array1.getNumberOfRows();
    int cols1 = array1.getNumberOfColumns();
    len = array1.getData().length;
    double[] data1 = new double[len];
    System.arraycopy(array1.getData(), 0, data1, 0, len);

    int rows2 = array2.getNumberOfRows();
    int cols2 = array2.getNumberOfColumns();
    len = array2.getData().length;
    double[] data2 = new double[len];
    System.arraycopy(array2.getData(), 0, data2, 0, len);

    // Approx alg:
    // if the array1 is triangular call dtrtrs()
    // if the array is square with positive diag \in R and Hermitian try cholesky via dpotrf 
    // if success, back solve with dpotrs, else fall through
    // if cholesky failed or matrix is not suitable for cholesky try and solve with dgetrf() via LU and back substitution
    // if matrix is not square or LAPACK flagged failure from earlier solve with SVD via dgelsd

    // FIXME: not happy with above alg. Better attempt might be if not square check condition, try QR, check condition of R, SVD is last resort.
    // Can probably wire up the QR into SVD using LAPACK internals

    // First...
    // check if the system is sane
    Catchers.catchBadCommute(rows1, "rows array1", rows2, "rows array2");

    // Do the alg above: 

    // LAPACK info
    int[] info = new int[1];
    // is it square?
    if (rows1 == cols1) {
      // is the array1 is triangular (or permuted triangular - might do this?
      char[] lapackVals = triangular(data1, rows1, cols1);
      char tri = lapackVals[0];
      char diag = lapackVals[1];
      if (tri != 'N') {
        _lapack.dtrtrs(tri, 'N', diag, rows1, cols2, data1, rows1, data2, rows2, info);
        if (info[0] == 0) { // triangular solve was ok
          return new OGMatrix(data2, rows2, cols2);
        }
      } else { // see if it's Hermitian (symmetric in the real case)
        if (isSymmetric(data1, rows1, cols1)) {
          // cholesky decompose, shove in lower triangle
          _lapack.dpotrf('L', rows1, data1, rows1, info);
          if (info[0] == 0) { // cholesky factorisation was ok, its in the lower triangle, back solve based on this 
            _lapack.dpotrf('L', rows1, data1, rows1, info);
            if (info[0] == 0) {  // went ok with backsolve so return 
              return new OGMatrix(data2, rows2, cols2);
            }
          }
        } else { // try solving with generalised LUP solver
          int[] ipiv = new int[rows1];
          // decompose
          _lapack.dgetrf(rows1, cols1, data1, rows1, ipiv, info);
          if (info[0] == 0) {
            // back solve dgetrs()
            _lapack.dgetrs('N', cols1, cols2, data1, cols1, ipiv, data2, cols1, info);
            if (info[0] == 0) {
              return new OGMatrix(data2, rows2, cols2);
            }
          }
        }
      }
    }

    // if we got here we either have a rectangular system, or something broke in LAPACK (i.e. info!=0)
    //TODO: if(info!=0) find out why, is singular compute the rcond and shove it in a log

    // so we attempt a general least squares solve
    double[] s = new double[Math.min(rows1, cols1)];
    double rcond = -1; // this is the definition of singular in the Moore-Penrose sense, if set to -1 machine prec is used
    int[] rank = new int[1];
    int lwork;
    int m = rows1;
    int n = cols1;
    // call ilaenv() to get parameters for svd calls, seems broken in netlib jars, will need to patch the byte code or override
    //    int smlsiz = _lapack.ilaenv(9, new String("dgelsd").toCharArray(), new char[] {' ' }, 0, 0, 0, 0);
    int smlsiz = 25;
    if (smlsiz < 0) {
      throw new MathsExceptionGeneric("ILAENV called with incorrect value at position " + (-smlsiz));
    }
    int smlsizep1 = (smlsiz + 1);
    int smlsizep1sqrd = smlsizep1 * smlsizep1;
    double loginner = Math.min(m, n) / smlsizep1;
    double logbit = Math.log(loginner) / Math.log(2); // no log2() in java
    int nlvl = Math.max(0, (int) (logbit) + 1);

    // TODO: bung in ilaenv() call to 6 so that the algorithm switch size is used, as in QR vs givens for bidiag...

    // stu - before your eyes go funny, the difference is in the placement of the m's and n's, this is computing work space depending on sizes
    if (rows1 >= cols1) {
      lwork = 12 * n + 2 * n * smlsiz + 8 * n * nlvl + n * cols2 + smlsizep1sqrd;
    } else {
      lwork = 12 * m + 2 * m * smlsiz + 8 * m * nlvl + m * cols2 + smlsizep1sqrd;
    }

    // malloc work space
    double[] work = new double[Math.max(1, lwork)];
    int minmn = Math.min(m, n);
    int[] iwork = new int[3 * minmn * nlvl + 11 * minmn];
    // make the call to the least squares solver
    _lapack.dgelsd(rows1, cols1, cols2, data1, Math.max(1, rows1), data2, Math.max(1, Math.max(rows1, cols1)), s, rcond, rank, work, lwork, iwork, info);

    // handle fail on info
    if (info[0] != 0) {
      // LOG it broke
    }
    return new OGMatrix(Arrays.copyOf(data2, cols1 * cols2), cols1, cols2);
  }

  private boolean isSymmetric(double[] data, int rows, int cols) {
    int ir;
    for (int i = 0; i < cols; i++) {
      ir = i * rows;
      for (int j = 0; j < rows; j++) {
        if (data[ir + j] != data[j * rows + i]) {
          return false;
        }
      }
    }
    return true;
  }

  private char[] triangular(double[] data, int rows, int cols) {
    char[] lapackVals;
    lapackVals = checklower(data, rows, cols);
    if (lapackVals[0] != 'N') {
      return lapackVals;
    } else {
      return checkupper(data, rows, cols);
    }
  }

  private char[] checklower(double[] data, int rows, int cols) {
    // check lower
    int ir;
    char tri = 'L';
    char diag = 'U';
    rowloop:
    for (int i = 0; i < cols; i++) {
      ir = i * rows;
      if (data[ir * i] != 1) {
        diag = 'N';
      }
      for (int j = i + 1; j < rows; j++) {
        if (data[ir + j] != 0) {
          tri = 'N';
          break rowloop;
        }
      }
    }
    return new char[] {tri, diag };
  }

  private char[] checkupper(double[] data, int rows, int cols) {
    // check upper
    int ir;
    char tri = 'U';
    char diag = 'U';
    if (data[0] != 1) {
      diag = 'N';
    }
    rowloop:
    for (int i = 1; i < cols; i++) {
      ir = i * rows;
      if (data[ir * i] != 1) {
        diag = 'N';
      }
      for (int j = 0; j < i; j++) {
        if (data[ir + j] != 0) {
          tri = 'N';
          break rowloop;
        }
      }
    }
    return new char[] {tri, diag };
  }

}
