/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions.memory;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Hassle free dealing with mallocs
 */
public class OGTypesMalloc {

  /**
   * Construct a new matrix with the same structure as an existing matrix but a different set of data
   * @param thisMatrix the matrix from which the structure shall be copied
   * @param newData the data for the new matrix
   * @return a new matrix with the same structure as an existing matrix but a different set of data
   */
  public static OGMatrix OGMatrixBasedOnStructureOf(OGMatrix thisMatrix, double[] newData) { //CSIGNORE
    return new OGMatrix(newData, thisMatrix.getNumberOfRows(), thisMatrix.getNumberOfColumns());
  }

  /**
   * Construct a new matrix with the same structure as an existing matrix but a different set of data
   * @param thisMatrix the matrix from which the structure shall be copied
   * @param newData the data for the new matrix
   * @return a new matrix with the same structure as an existing matrix but a different set of data
   */
  public static OGComplexMatrix OGComplexMatrixBasedOnStructureOf(OGComplexMatrix thisMatrix, double[] newData) { //CSIGNORE
    return new OGComplexMatrix(newData, thisMatrix.getNumberOfRows(), thisMatrix.getNumberOfColumns());
  }

  /**
   * Construct a new matrix with the same structure as an existing matrix but a different set of data
   * @param thisMatrix the matrix from which the structure shall be copied
   * @param newData the data for the new matrix
   * @return a new matrix with the same structure as an existing matrix but a different set of data
   */
  public static OGComplexMatrix OGComplexMatrixBasedOnStructureOf(OGMatrix thisMatrix, double[] newData) { //CSIGNORE
    return new OGComplexMatrix(newData, thisMatrix.getNumberOfRows(), thisMatrix.getNumberOfColumns());
  }

  /**
   * Construct a new sparse matrix with the same nonzero structure as an existing sparse matrix but a different set of data
   * @param thisMatrix the matrix from which the structure shall be copied
   * @param newData the data for the new sparse matrix
   * @return a new sparse matrix with the same nonzero structure as an existing sparse matrix but a different set of data
   */
  public static OGSparseMatrix OGSparseMatrixBasedOnStructureOf(OGSparseMatrix thisMatrix, double[] newData) { //CSIGNORE
    return new OGSparseMatrix(thisMatrix.getColumnPtr(), thisMatrix.getRowIndex(), newData, thisMatrix.getNumberOfRows(), thisMatrix.getNumberOfColumns());
  }

  /**
   * Construct a new complex sparse matrix with the same nonzero structure as an existing sparse matrix but a different set of data
   * @param thisMatrix the matrix from which the structure shall be copied
   * @param newData the data for the new sparse matrix
   * @return a new complex sparse matrix with the same nonzero structure as an existing complex sparse matrix but a different set of data
   */
  public static OGComplexSparseMatrix OGComplexSparseMatrixBasedOnStructureOf(OGComplexSparseMatrix thisMatrix, double[] newData) { //CSIGNORE
    return new OGComplexSparseMatrix(thisMatrix.getColumnPtr(), thisMatrix.getRowIndex(), newData, thisMatrix.getNumberOfRows(), thisMatrix.getNumberOfColumns());
  }

  /**
   * Construct a new complex sparse matrix with the same nonzero structure as an existing sparse matrix but a different set of data
   * @param thisMatrix the matrix from which the structure shall be copied
   * @param newData the data for the new sparse matrix
   * @return a new complex sparse matrix with the same nonzero structure as an existing complex sparse matrix but a different set of data
   */
  public static OGComplexSparseMatrix OGComplexSparseMatrixBasedOnStructureOf(OGSparseMatrix thisMatrix, double[] newData) { //CSIGNORE
    return new OGComplexSparseMatrix(thisMatrix.getColumnPtr(), thisMatrix.getRowIndex(), newData, thisMatrix.getNumberOfRows(), thisMatrix.getNumberOfColumns());
  }

}

