/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.io.smartImport;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.datatypes.primitive.MatrixPrimitiveUtils;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Attempts to identify the useful information about matrix data so that more informed decisions can be made.
 */
public class SnortDataProperties {

  /**
   * Number of rows
   */
  private int _rows;

  /**
   * Number of columns
   */
  private int _cols;

  /**
   * Number of elements
   */
  private int _els;

  /**
   * Stores number of non zeros
   */
  private int _nnz;

  /**
   * Threshold for sparsity
   */
  private static double s_sparseThreshold = 0.6;

  /**
   * Set a matrix type.
   */
  private MatrixType _mType;

  /**
   * The type of triangular
   */
  public enum MatrixType {
    /**
     * Upper triangular
     */
    upperTriangular,
    /**
     * Lower triangular
     */
    lowerTriangular,
    /**
     * Identity
     */
    identity,
    /**
     * Diagonal 
     */
    diagonal,
    /**
     * Sparse
     */
    sparse,
    /**
     * Dense
     */
    dense,
  }

  /**
   * @param aMatrix a matrix to test
   * @param sparseThreshold the threshold used for deciding whether a matrix should be considered sparse (given as a decimal between 0 and 1)
   */
  SnortDataProperties(double[][] aMatrix, double sparseThreshold) {
    Catchers.catchNull(aMatrix, "aMatrix");
    if (MatrixPrimitiveUtils.isRagged(aMatrix)) {
      throw new MathsExceptionIllegalArgument("Array aMatrix, arg[1], cannot be ragged (i.e. all the row pointers must have the same length)");
    }
    if (sparseThreshold < 0 || sparseThreshold > 1) {
      throw new MathsExceptionIllegalArgument("sparseThreshold, arg[2], must have a value between 0 and 1");
    }

    _rows = aMatrix.length;
    _cols = aMatrix[0].length;
    _els = _rows * _cols;

    // counts nnz
    int nnzCount = 0;

    // toggles
    boolean upperT = true;
    boolean lowerT = true;
    boolean identity = true;
    boolean sparse = true;
    boolean dense = true;

    if (!MatrixPrimitiveUtils.isSquare(aMatrix)) {
      upperT = false;
      lowerT = false;
      identity = false;
    }

    for (int i = 0; i < _rows; i++) {
      // test for identity
      if (identity) {
        if (aMatrix[i][i] != 1) {
          identity = false;
        }
      }

      for (int j = 0; j < _cols; j++) {

        // test for lowerT
        if (lowerT) {
          if (i > 0 && j < i && aMatrix[i][j] != 0) {
            lowerT = false;
          }
        }

        // test for upperT
        if (upperT) {
          if (j > 0 && j > i && aMatrix[i][j] != 0) {
            upperT = false;
          }
        }

        // increment nnzCount
        if (aMatrix[i][j] == 0) {
          nnzCount++;
        }
      }
    }

    // deal with sparse vs dense
    if (nnzCount < sparseThreshold) {
      sparse = false;
    } else {
      dense = false;
    }

    // set the matrix type internally
    if (dense) {
      _mType = MatrixType.dense;
    } else if (sparse) {
      _mType = MatrixType.sparse;
    } else if (upperT) {
      _mType = MatrixType.upperTriangular;
    } else if (lowerT) {
      _mType = MatrixType.lowerTriangular;
    } else if (identity) {
      _mType = MatrixType.identity;
    } else {
      _mType = MatrixType.diagonal;
    }
  }

  /**
   * @param aMatrix a matrix to test
   */
  public SnortDataProperties(double[][] aMatrix) {
    this(aMatrix, s_sparseThreshold);
  }

  /**
   * Gets the number of non-zeros.
   * @return the number of non-zeros 
   */
  public int getNnz() {
    return _nnz;
  }

  /**
   * Gets the number of elements in the matrix (rows * cols).
   * @return the number of elements in the matrix
   */
  public int getNElements() {
    return _els;
  }

  /**
   * Gets the ratio of nnz to elements
   * @return the number of elements in the matrix
   */
  public double getNnzToelementsRatio() {
    return (double) _nnz / _els;
  }

  /**
   * Gets the identified matrix type
   * @return the identified matrix type
   */
  public MatrixType getMatrixType() {
    return _mType;
  }

}
