/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import java.util.Arrays;

/**
 * 
 */
public class OGSparseArray extends OGArraySuper<Number> {

  private double[] _values;
  private int[] _colPtr;
  private int[] _rowIdx;
  private int _els;
  private int _rows;
  private int _cols;
  private int _maxEntriesInAColumn;

  /* constructors */

  /**
   * Construct from double[][]
   * @param matrix is a double[][]
   */
  public OGSparseArray(double[][] matrix) {

    final int rows = matrix.length;
    final int cols = matrix[0].length;

    //get number of elements
    _els = rows * cols;

    // tmp arrays, in case we get in a fully populated matrix, intelligent design upstream should ensure that this is overkill!
    double[] dataTmp = new double[_els];
    int[] colPtrTmp = new int[_els + 1];
    int[] rowIdxTmp = new int[_els];

    // we need unwind the array m into coordinate form
    int ptr = 0;
    int i;
    int localMaxEntrisInACol;
    _maxEntriesInAColumn = -1;
    double entry = 0;
    for (i = 0; i < cols; i++) {
      colPtrTmp[i] = ptr;
      localMaxEntrisInACol = 0;
      for (int j = 0; j < rows; j++) {
        entry = matrix[j][i];
        if (entry != 0.0e0) {
          rowIdxTmp[ptr] = j;
          dataTmp[ptr] = entry;
          ptr++;
          localMaxEntrisInACol++;
        }
      }
      if (localMaxEntrisInACol > _maxEntriesInAColumn) {
        _maxEntriesInAColumn = localMaxEntrisInACol;
      }
    }
    colPtrTmp[i] = ptr;

    // return correct 0 to correct length of the vector buffers
    _values = Arrays.copyOfRange(dataTmp, 0, ptr);
    _colPtr = Arrays.copyOfRange(colPtrTmp, 0, i + 1); // yes, the +1 is correct, it allows the computation of the number of elements in the final row!
    _rowIdx = Arrays.copyOfRange(rowIdxTmp, 0, ptr);
    _rows = rows;
    _cols = cols;
  }
  
  /**
   * Construct from underlying CSC representation
   * @param colPtr the columns pointers
   * @param rowIdx the row indexes
   * @param values the underlying values
   * @param rows the number of rows
   * @param columns the number of columns
   */
  public OGSparseArray(int [] colPtr, int [] rowIdx, double[] values, int rows, int columns) {
    final int vlen = values.length;
    _values = new double[vlen];
    System.arraycopy(values, 0, _values, 0, vlen);
    
    final int clen = colPtr.length;
    _colPtr = new int[clen];
    System.arraycopy(colPtr, 0, _colPtr, 0, clen);
    
    final int rlen = rowIdx.length;
    _rowIdx = new int[rlen];
    System.arraycopy(rowIdx, 0, _rowIdx, 0, rlen);
    
    _rows = rows;
    //TODO: Check columns == colPtr.length - 1
    _cols = columns;
  }
  

  public double[] getFullColumn(int index) {
    double[] tmp = new double[_cols];
    for (int i = _colPtr[index]; i <= _colPtr[index + 1] - 1; i++) { // loops through elements of correct column
      tmp[_rowIdx[i]] = _values[i];
    }
    return tmp;
  }

  public double[] getFullRow(int index) { // getting rows in CSC form is generally bad
    double[] tmp = new double[_rows];
    for (int i = 0; i < _rows; i++) {
      tmp[i] = this.getEntry(index, i);
    }
    return tmp;
  }

  public int getNumberOfNonZeroElements() {
    return _values.length;
  }

  /**
   *  Gets the row indexes that can be looked up by the column pointer
   *  @return _rowIdx, the row indexes
   */
  public int[] getRowIndex() {
    return _rowIdx;
  }

  /**
   * Gets the column pointer (not actually a pointer, but would be in C)
   * @return _colPtr, the column pointer
   */
  public int[] getColumnPtr() {
    return _colPtr;
  }
  
  /**
   * Gets the non-zero values in the matrix, i.e. the values that are worth storing
   * @return _values, the non-zero values
   */
  public double[] getNonZeroElements() {
    return _values;
  }
  
  /**
   * Gets the data backing
   * @return _values, the non-zero values
   */
  public double[] getData() {
    return _values;
  }  
  
  @Override
  public int getNumberOfRows() {
    return _rows;
  }

  @Override
  public int getNumberOfColumns() {
    return _cols;
  }

  @Override
  public Double getEntry(int... indices) {
    //translate an index and see if it exists, if it doesn't then return 0
    for (int i = _colPtr[indices[1]]; i <= _colPtr[indices[1] + 1] - 1; i++) { // loops through elements of correct column
      // looks for col index
      if (_rowIdx[i] == indices[0]) {
        return _values[i];
      }
    }
    return 0.0;
  }

  @Override
  public String toString() {
    return "\nvalues=" + Arrays.toString(_values) +
        "\nrowInd=" + Arrays.toString(_rowIdx) +
        "\ncolPtr=" + Arrays.toString(_colPtr) +
        "\nels=" + _els;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(_rowIdx);
    result = prime * result + _cols;
    result = prime * result + _els;
    result = prime * result + Arrays.hashCode(_colPtr);
    result = prime * result + _rows;
    result = prime * result + Arrays.hashCode(_values);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    OGSparseArray other = (OGSparseArray) obj;
    if (_cols != other.getNumberOfColumns()) {
      return false;
    }
    if (_rows != other.getNumberOfRows()) {
      return false;
    }
    if (!Arrays.equals(_colPtr, other.getColumnPtr())) {
      return false;
    }
    if (!Arrays.equals(_rowIdx, other.getRowIndex())) {
      return false;
    }
    if (!Arrays.equals(_values, other.getData())) {
      return false;
    }
    return true;
  }

}
