/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.io.smartImport;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.lowlevelapi.functions.MatrixPrimitiveUtils;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Attempts to identify the useful information about matrix data so that more informed decisions can be made.
 */
public class SnortDataProperties {

  /*
   * Number of rows
   */
  private int _rows;

  /*
   * Number of columns
   */
  private int _cols;

  /*
   * Number of elements
   */
  private int _els;

  /*
   * Stores number of non zeros
   */
  private int _nnz;

  /*
   * Lower bandwidth, upper bandwidth
   */
  private int _lbandwidth, _ubandwidth;

  /*
   * Threshold for sparsity
   */
  private static double s_sparseThreshold = 0.6;

  /*
   * Threshold for considering a matrix banded.
   * This is the number width of the bands in relation to the width of the matrix.
   * Not to be confused with 'bandden' which is the density of the nonzero elements in a banded matrix at which dense banded routines are preferred over generalised sparse routines.
   */
  private static double s_bandwidthRatioThreshold = 0.5;

  /*
   * Threshold at which the density of the nonzero elements in a banded matrix is considered sufficiently high to call dense banded routines in place of generalised sparse routines.
   */
  private static double s_banddenThreshold = 0.5;

  /*
   * Set a matrix type.
   */
  private MatrixType _mType;

  /**
   * The type of matrix
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

    // bandden
    int ubwidth = 0;
    int lbwidth = 0;

    // toggles
    boolean upperT = true;
    boolean lowerT = true;
    boolean identity = true;
    boolean sparse = true;
    boolean dense = true;
    boolean diagonal = true;

    int tmp;

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

        // test for lowerT still holding
        if (lowerT) {
          if (j > 0 && j > i && aMatrix[i][j] != 0) {
            lowerT = false;
          }
        }

        // test for upperT still holding
        if (upperT) {
          if (i > 0 && j < i && aMatrix[i][j] != 0) {
            upperT = false;
          }
        }

        // test for diagonal
        if (diagonal && i != j) {
          if (aMatrix[i][j] != 0) {
            diagonal = false;
          }
        }
        if (identity && i != j) {
          if (aMatrix[i][j] != 0) {
            identity = false;
          }
        }

        // increment nnzCount
        if (aMatrix[i][j] == 0) {
          nnzCount++;
        } else {
          // check band density
          if (i > j) { // lower tri
            tmp = i - j;
            if (tmp > lbwidth) {
              lbwidth = tmp;
            }
          } else if (i < j) {
            tmp = j - i;
            if (tmp > ubwidth) { // upper triangle
              ubwidth = tmp;
            }
          }
        }

      }
    }

    // set the matrix type internally, order of branches matters as some structures are subclasses of others, i.e. diagonal is also triangular!
    if (identity) {
      _mType = MatrixType.identity;
    } else if (diagonal) {
      _mType = MatrixType.diagonal;
    } else if (upperT) {
      _mType = MatrixType.upperTriangular;
    } else if (lowerT) {
      _mType = MatrixType.lowerTriangular;
    }

    // deal with sparse vs dense if nothing special found
    if (_mType == null) {
      if (nnzCount < sparseThreshold * _els) { // debug branching
        sparse = false;
      } else {
        dense = false;
      }
      if (dense) {
        _mType = MatrixType.dense;
      } else if (sparse) {
        _mType = MatrixType.sparse;
      } else {
        throw new MathsExceptionGeneric("Matrix type cannot be identified from data. This is a bug, please report it!");
      }
    }

    // set object struct properties that weren't set prior to walk.
    _nnz = nnzCount;
    _lbandwidth = lbwidth;
    _ubandwidth = ubwidth;

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
  public double getNnzToElementsRatio() {
    return (double) _nnz / _els;
  }

  /**
   * Gets the bandwidth of the matrix (the ratio of the found bands to the sum of the dimensions of the matrix).
   * @return the bandwidth ratio
   */
  public double getBandWidthRatio() {
    return (double) (_lbandwidth + _ubandwidth) / (_rows + _cols);
  }

  /**
   * Gets the density of the bands (if the matrix is not banded this measure is still accessible but rather meaningless).
   * @return the band density.
   */
  public double getBandden() {

    // TODO: Get LAPACK code for computing bandwidths so that the interpretation of bwidth etc matches.
    throw new MathsExceptionNotImplemented("band width density computation not implemented yet");
  }

  /**
   * Gets the identified matrix type
   * @return the identified matrix type
   */
  public MatrixType getMatrixType() {
    return _mType;
  }

  /**
   * Gets the sparse threshold. This is the density of nonzero element in a matrix required for the matrix to be considered sparse.
   * Essentially, if (number of non-zeros / number of elements in matrix) > sparse threshold then the  matrix is considered sparse.
   * @return the sparse threshold. Default = 0.6
   */
  public static double getSparseThreshold() {
    return s_sparseThreshold;
  }

  /**
   * Gets the bandwidth threshold ratio. This is the threshold at which a matrix is considered "banded". It is the ratio of the width of the bands in comparison to the matrix dimension.
   * @return the bandwidth threshold ratio. Default = 0.5
   */
  public static double getBandwidthRatioThreshold() {
    return s_bandwidthRatioThreshold;
  }

  /**
   * Gets the 'bandden' threshold. This relates to the common "bandden" property. It is the threshold at which a banded matrix is considered banded for the
   * purpose of using the dense routines opposed to the sparse routines. Default = 0.5;
   * @return the bandden
   */
  public static double getBanddenThreshold() {
    return s_banddenThreshold;
  }

}
