/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.matrixinfo;

/**
 * Enumerated Matrix Types
 */
public enum MatrixTypeInfo {

  //CSOFF
  Number("Number", 0),  //
  ComplexNumber("Complex Number", 1),  //
  DiagonalMatrix("Diagonal Matrix", 2),  //
  ComplexDiagonalMatrix("Complex Diagonal Matrix", 3),  //
  PermutationMatrix("Permutation Matrix", 4),  //
  IndexMatrix("Index Matrix", 5),  //
  SparseMatrix("Sparse Matrix", 6),  //
  ComplexSparseMatrix("Complex Sparse Matrix", 7),  //
  Matrix("Matrix", 8),  //
  ComplexMatrix("Complex Matrix", 9);
  //CSON

  private final String _humanReadable;
  private final int _index;

  /**
   * 
   */
  MatrixTypeInfo(String humanReadable, int index) {
    _humanReadable = humanReadable;
    _index = index;
  }

  /**
   * Gets the human readable string
   * @return the human readable string
   */
  public String getHumanReadableString() {
    return _humanReadable;
  }

  /**
   * Gets the index.
   * @return the index
   */
  public int getIndex() {
    return _index;
  }

}
