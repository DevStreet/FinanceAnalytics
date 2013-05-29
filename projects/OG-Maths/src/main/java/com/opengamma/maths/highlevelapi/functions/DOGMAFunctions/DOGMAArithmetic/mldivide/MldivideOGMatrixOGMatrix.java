/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mldivide;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Mldivide;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.LAPACK;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Generalised linear system solver. Does the classic "backslash" operation found in a number of languages.
 */
// TODO: Check lower triangle permutation possibilities, at the minute, permutations are assumed to be upper, this means the shuffle is guaranteed inefficient
@DOGMAMethodHook(provides = Mldivide.class)
public final class MldivideOGMatrixOGMatrix implements Mldivide<OGMatrix, OGMatrix, OGMatrix> {

  private LAPACK _lapack = new LAPACK();

  private static Logger s_log = LoggerFactory.getLogger(MldivideOGMatrixOGMatrix.class);

  private boolean _debug;

  // Attempts to solve AX=B for matrices A,X,B
  @Override
  public OGMatrix eval(OGMatrix array1, OGMatrix array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);
    int len1, len2;

    // data from array 1 (A)
    int rows1 = array1.getNumberOfRows();
    int cols1 = array1.getNumberOfColumns();
    len1 = array1.getData().length;
    double[] data1 = new double[len1];
    System.arraycopy(array1.getData(), 0, data1, 0, len1);

    // data from array 2 (B)
    int rows2 = array2.getNumberOfRows();
    int cols2 = array2.getNumberOfColumns();
    len2 = array2.getData().length;
    double[] data2 = new double[len2];
    System.arraycopy(array2.getData(), 0, data2, 0, len2);

    // useful vars
    double[] rcond = new double[1]; // estimate of reciprocal condition number
    double anorm = 0; // the 1 norm of a matrix
    boolean singular = false; // is the matrix identified as singular
    boolean attemptQR = true; // should QR decomposition be attempted for singular/over determined systems prior to using SVD?

    //auxiallary vars
    double[] work = null; // lapackian double * work space
    int[] iwork = null; // lapackian int32 * work space
    int lwork; // size of work
    double[] dummywork = new double[1]; // dummy work space for use in double * workspace dimension inquiry calls
    int[] dummyiwork = new int[1]; // dummy work space for use in int * workspace dimension inquiry calls
    double[] triPtr1 = null, triPtr2 = null; // for pointer switching if a perm is needed but system is bad and needs resetting
    int[] permuteV = null; // the permutation vector, if needed

    // flow control
    boolean jmp = false; // set if matrix is triangular and singular so that other standard decompositions are skipped and a least squares result is attempted immediately

    // Approx alg: This is based loosely on my llsq library, see internal OG C code.
    // 1) check if system commutes
    // 2) check if square ? goto 3) : goto 6)
    // 3) check if triangular (or perm of), if so compute rcond, if rcond ok solve else goto 6), if solve fails goto 6)
    // 4) check if symmetric, if so, try cholesky, if decomp fails it's not s.p.d. If decomp succeeds, try solve, if fails, compute rcond and goto 6)
    // 5) Try LUP. If decomp succeeds, try solve, if fails, compute rcond and goto 6)
    // 6) rcond+1!=1 ? try QR based llsq : goto 8)
    // 7) if QR decomp succeeds return, else goto 8)
    // 8) run svd based llsq
    // 9) if svd llsq fails warn always return.

    // First...
    // check if the system is sane
    Catchers.catchBadCommute(rows1, "rows array1", rows2, "rows array2");

    // Do the alg above: 
    // LAPACK info
    int[] info = new int[1];
    // is it square?
    if (rows1 == cols1) {
      if (_debug) {
        s_log.warn("10. Matrix is square");
      }

      // is the array1 triangular or permuted triangular
      TriangularStruct tstruct = isTriangular(data1, rows1, cols1);
      char UPLO = tstruct._flagUPLO; //CSIGNORE
      char DIAG = tstruct._flagDIAG; //CSIGNORE
      if (UPLO != 'N') { // i.e. this is some breed of triangular
        if (_debug) {
          s_log.warn("20. Matrix is " + (UPLO == 'U' ? "upper" : "lower") + " triangular, " + (DIAG == 'N' ? "non-unit" : "unit") + " diagonal.");
        }
        char DATA_PERMUTATION = tstruct._flagPerm; // CSIGNORE
        if (DATA_PERMUTATION != 'N') { // we have a permutation of the data, rewrite
          if (_debug) {
            s_log.warn("25. Matrix is " + (DATA_PERMUTATION == 'R' ? "row" : "column") + " permuted triangular.");
          }
          permuteV = tstruct._perm; // the permutation
          if (DATA_PERMUTATION == 'R') {
            triPtr1 = data1; // save pointer for later in case this doesn't work out
            data1 = new double[rows1 * cols1];
            for (int i = 0; i < cols1; i++) {
              for (int j = 0; j < rows1; j++) {
                data1[i * rows1 + permuteV[j]] = triPtr1[i * rows1 + j];
              }
            }
            triPtr2 = data2; // save pointer for later in case this doesn't work out
            data2 = new double[rows2 * cols2];
            for (int i = 0; i < cols2; i++) {
              for (int j = 0; j < rows2; j++) {
                data2[i * rows2 + permuteV[j]] = triPtr2[i * rows2 + j];
              }
            }
          }
        } // end permutation branch

        // compute reciprocal condition number, if it's bad say so and least squares solve
        work = new double[3 * rows1];
        iwork = new int[rows1];
        _lapack.dtrcon('1', UPLO, DIAG, rows1, data1, rows1, rcond, work, iwork, info);

        if (rcond[0] + 1 != 1) { // rcond ~< D1MACH(4)
          _lapack.dtrtrs(UPLO, 'N', DIAG, rows1, cols2, data1, rows1, data2, rows2, info);
          if (info[0] == 0) { // triangular solve was ok
            if (_debug) {
              s_log.warn("30. Triangular solve success, returning");
            }
            return new OGMatrix(data2, rows2, cols2);
          } else {
            if (_debug) {
              s_log.warn("40. Triangular solve fail. Mark as singular.");
            }
            singular = true;
          }
        } else {
          if (_debug) {
            s_log.warn("43. Triangular condition was computed as too bad to attempt solve.");
          }
          singular = true;
        }

        if (DATA_PERMUTATION != 'N') { // reset permutation, just a pointer switch, not doing so might influence results from iterative llsq solvers
          if (_debug) {
            s_log.warn("45. Resetting permutation.");
          }
          data1 = triPtr1;
          data2 = triPtr2;
        }
        jmp = true; // nmatrix is singular, jump to least squares logic 

      } // end "this matrix is triangular"

      if (!jmp) { // if it's triangular and it failed, then it's singular and a condition number is already computed and this can be jumped!
        if (_debug) {
          s_log.warn("50. Not triangular");
        }
        // see if it's Hermitian (symmetric in the real case)
        if (isSymmetric(data1, rows1, cols1)) {
          if (_debug) {
            s_log.warn("60. Is Hermitian");
          }
          // cholesky decompose, shove in lower triangle
          _lapack.dpotrf('L', rows1, data1, rows1, info);
          if (info[0] == 0) { // Cholesky factorisation was ok, matrix is s.p.d. Factorisation is in the lower triangle, back solve based on this
            if (_debug) {
              s_log.warn("70. Cholesky success.  Computing condition based on cholesky factorisation");
            }
            work = new double[3 * rows1];
            iwork = new int[rows1];
            anorm = _lapack.dlansy('1', 'L', rows1, array1.getData(), rows1, work);
            _lapack.dpocon('L', rows1, data1, rows1, anorm, rcond, work, iwork, info);
            if (_debug) {
              s_log.warn("80. Cholesky condition estimate. " + rcond[0]);
            }
            if (1 + rcond[0] != 1) {
              if (_debug) {
                s_log.warn("90. Cholesky condition acceptable. Backsolve and return.");
              }
              _lapack.dpotrs('L', rows1, cols2, data1, rows1, data2, rows2, info);
              // info[0] will be zero. Any -ve info[0] will be handled by XERBLA
              return new OGMatrix(data2, rows2, cols2);
            } else {
              if (_debug) {
                s_log.warn("100. Cholesky condition bad. Mark as singular.");
              }
              singular = true;
            }
          } else { // factorisation failed
            if (_debug) {
              s_log.warn("110. Cholesky factorisation failed. Matrix is not s.p.d.");
            }
          } // end factorisation info==0 
        } // end symmetric condition

        if (!singular) {
          if (_debug) {
            s_log.warn("120. Non-symmetric OR not s.p.d.");
          }
          //  we're here as matrix isn't s.p.d. as Cholesky failed. Get new copy of matrix
          System.arraycopy(array1.getData(), 0, data1, 0, array1.getData().length);

          // try solving with generalised LUP solver
          int[] ipiv = new int[rows1];
          // decompose
          if (_debug) {
            s_log.warn("130. LUP attempted");
          }
          _lapack.dgetrf(rows1, cols1, data1, rows1, ipiv, info);
          if (info[0] == 0) {
            if (_debug) {
              s_log.warn("140. LUP success. Computing condition based on LUP factorisation.");
            }
            iwork = new int[rows1];
            work = new double[4 * rows1];
            anorm = _lapack.dlange('1', rows1, cols1, array1.getData(), rows1, work);
            _lapack.dgecon('1', rows1, data1, rows1, anorm, rcond, work, iwork, info);

            // if condition estimate isn't too bad then back solve
            if (1 + rcond[0] != 1) {
              // back solve dgetrs()
              _lapack.dgetrs('N', cols1, cols2, data1, cols1, ipiv, data2, cols1, info);
              if (_debug) {
                s_log.warn("150. LUP returning");
              }
              return new OGMatrix(data2, rows2, cols2);
            } else { // condition bad, mark as singular
              if (_debug) {
                s_log.warn("160. LUP failed, condition too high");
              }
              singular = true;
            }
          } else {
            if (_debug) {
              s_log.warn("170. LUP factorisation failed. Mark as singular.");
            }
            singular = true;
          }
        }
      }
    }

    // branch on rcond if computed, if cond is sky high then rcond is near zero, we use 1 here so cutoff is near 1e-16
    if (singular) {
      s_log.warn("Square matrix is singular to machine precision. RCOND estimate = " + rcond[0]);
      if (_debug) {
        s_log.warn("190. Square matrix is singular.");
      }
      if ((rcond[0] + 1) == 1) {
        if (_debug) {
          s_log.warn("200. Condition of square matrix is too bad for QR least squares.");
        }
        attemptQR = false;
      }
      // take a copy of the original data as it will have been destroyed above
      System.arraycopy(array1.getData(), 0, data1, 0, array1.getData().length);
    }

    if (attemptQR) {
      if (_debug) {
        s_log.warn("210. Attempting QR solve.");
      }
      lwork = -1;
      _lapack.dgels('N', rows1, cols1, cols2, data1, rows1, data2, rows2, dummywork, lwork, info);
      lwork = (int) dummywork[0];
      work = new double[lwork];
      _lapack.dgels('N', rows1, cols1, cols2, data1, rows1, data2, rows2, work, lwork, info);
      if (info[0] > 0) {
        if (_debug) {
          s_log.warn("220. QR solve failed");
        }
        s_log.warn("Matrix of coefficients does not have full rank. Rank is " + info[0] + ".");
        // take a copy of the original data as it will have been destroyed above
        System.arraycopy(array1.getData(), 0, data1, 0, array1.getData().length);
        System.arraycopy(array2.getData(), 0, data2, 0, array2.getData().length);        
      } else {
        if (_debug) {
          s_log.warn("230. QR solve success, returning");
        }
        // 1:cols1 from each column of data2 needs to be returned 
        double[] ref = data2;
        data2 = new double[cols1 * cols2];
        for (int i = 0; i < cols2; i++) {
          System.arraycopy(ref, i * rows1, data2, i * cols1, cols1);
        }
        return new OGMatrix(data2, cols1, cols2);
      }
    }

    if (_debug) {
      s_log.warn("240. Attempting SVD");
    }

    // so we attempt a general least squares solve
    double[] s = new double[Math.min(rows1, cols1)];
    double moorePenroseRcond = -1; // this is the definition of singular in the Moore-Penrose sense, if set to -1 machine prec is used
    int[] rank = new int[1];

    // internal calls in svd to ilaenv() to get parameters for svd call seems broken in netlib jars, will need to patch the byte code or override
    lwork = -1;
    _lapack.dgelsd(rows1, cols1, cols2, data1, Math.max(1, rows1), data2, Math.max(1, Math.max(rows1, cols1)), s, moorePenroseRcond, rank, dummywork, lwork, dummyiwork, info);
    lwork = (int) dummywork[0];
    work = new double[lwork];
    iwork = new int[dummyiwork[0]];

    // make the call to the least squares solver
    _lapack.dgelsd(rows1, cols1, cols2, data1, Math.max(1, rows1), data2, Math.max(1, Math.max(rows1, cols1)), s, moorePenroseRcond, rank, work, lwork, iwork, info);

    // handle fail on info
    if (info[0] != 0) {
      if (_debug) {
        s_log.warn("250. SVD failed");
      }
      s_log.warn("SVD Failed to converege. " + info[0] + " out of " + Math.max(rows1, cols1) + " off diagonals failed to converge to zero. RCOND = " + rcond[0]);
    } else {
      if (_debug) {
        s_log.warn("260. SVD success");
      }
    }
    if (_debug) {
      s_log.warn("270. SVD returning");
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

  private TriangularStruct isTriangular(double[] data, int rows, int cols) {
    char[] lapackVals;

    // See if it's lower triangular
    lapackVals = checkIfLowerTriangular(data, rows, cols);
    if (lapackVals[0] != 'N') {
      return new TriangularStruct(lapackVals[0], lapackVals[1], 'N', null);
    }

    // See if it's Upper or permuted upper
    PermStruct permStruct = checkIfUpperTriangularPermuteRows(data, rows, cols);
    if (permStruct._flagPerm == 'R') {
      return new TriangularStruct('U', permStruct);
    }

    // DEFAULT, not triangular in any way
    return new TriangularStruct('N', 'N', 'N', null);

  }

  //TODO: combine these two methods into a single sweep and include TODO from above regarding efficient permutations.
  /*
   * Checks if a matrix is lower triangular 
   */
  private char[] checkIfLowerTriangular(double[] data, int rows, int cols) {
    // check lower
    int ir;
    char tri = 'L';
    char diag = 'U';
    if (data[0] != 1) {
      diag = 'N';
    }
    rowloop: //CSIGNORE
    for (int i = 1; i < cols; i++) {
      ir = i * rows;
      if (data[ir + i] != 1) {  // check if diag == 1
        diag = 'N';
      }
      for (int j = 0; j < i; j++) { // check if upper triangle is empty
        if (data[ir + j] != 0) {
          tri = 'N';
          break rowloop;
        }
      }
    }
    return new char[] {tri, diag };
  }

  /*
   * Checks if a square matrix contains an upper triangular matrix or row permutation of 
   */
  private PermStruct checkIfUpperTriangularPermuteRows(double[] data, int rows, int cols) {
    int[] rowStarts = new int[rows];
    boolean[] rowTag = new boolean[rows];
    char diag = 'U';
    boolean upperTriangleIfValidIspermuted = false;
    boolean validPermutation = true;
    Arrays.fill(rowStarts, -1); // -1 is our flag
    // Check if its lower, walk rows to look for when number appears in [0...0, Number....]
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        if (rowStarts[j] == -1 && data[i * rows + j] != 0) {
          if (data[i * rows + j] != 1) {
            diag = 'N'; // not unit diagonal
          }
          rowStarts[j] = i;
        }
      }
    }
    // Check to see if the matrix is a permutation of an upper triangle
    // Do this by checking that the claimed start of rows cover all columns
    // first set flags, then check flags, complete set needed to assess whether the perm is present
    for (int i = 0; i < rows; i++) {
      if (!rowTag[rowStarts[i]]) {
        rowTag[rowStarts[i]] = true;
      }
      if (!upperTriangleIfValidIspermuted && rowStarts[i] != i) {
        upperTriangleIfValidIspermuted = true;
      }
    }
    for (int i = 0; i < rows; i++) {
      if (!rowTag[i]) {
        validPermutation = false;
        break;
      }
    }
    if (!validPermutation) {
      return new PermStruct('N', 'N', null);
    } else {
      // permutation is valid, update struct. foo(rowStarts) = 1: length(rowStarts) gives direct perm
      return new PermStruct('R', diag, rowStarts);
    }

  }

  ///////////// Auxiliary structs
  // Really just want structs, here. Use public and avoid function calls

  // This holds the return struct from deciding if a matrix is a permutation of triangular 
  private class PermStruct {
    public char _flagPerm; // CSIGNORE
    public char _flagDiag; // CSIGNORE
    public int[] _perm; // CSIGNORE

    public PermStruct(char flagPerm, char flagDiag, int[] perm) {
      _flagPerm = flagPerm;
      _flagDiag = flagDiag;
      _perm = perm;
    };
  }

  // This holds the return struct from deciding if a matrix is in some way triangular
  private class TriangularStruct {
    public char _flagUPLO; // CSIGNORE
    public char _flagDIAG; // CSIGNORE
    public char _flagPerm; // CSIGNORE
    public int[] _perm; // CSIGNORE

    public TriangularStruct(char flagUPLO, char flagDIAG, char flagPERM, int[] perm) {
      _flagUPLO = flagUPLO;
      _flagDIAG = flagDIAG;
      _flagPerm = flagPERM;
      _perm = perm;
    };

    public TriangularStruct(char flagUPLO, PermStruct p) {
      _flagUPLO = flagUPLO;
      _flagDIAG = p._flagDiag;
      _flagPerm = p._flagPerm;
      _perm = p._perm;
    };
  }
}
