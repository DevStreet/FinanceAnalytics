/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mldivide;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * helper for mldivide so it can be correctly worked out by the compiler before type erasure ruins everything
 */
public final class MldivideOGMatrixOGMatrixHelper {

  private static Logger s_log = LoggerFactory.getLogger(MldivideOGMatrixOGMatrix.class);

  public static Logger getLogger() {
    return s_log;
  }

  public boolean isSymmetric(double[] data, int rows, int cols) {
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

  public TriangularStruct isTriangular(double[] data, int rows, int cols) {
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
  public char[] checkIfLowerTriangular(double[] data, int rows, int cols) {
    // check lower
    int ir;
    char tri = 'L';
    char diag = 'U';
    if (data[0] != 1) {
      diag = 'N';
    }
    rowloop:
    //CSIGNORE
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
  /**
   * Holds permutation flags
   */
  public class PermStruct {
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
  /**
   * Holds triangualar info flags
   */
  public class TriangularStruct {
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
