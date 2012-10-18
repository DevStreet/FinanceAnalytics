/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import java.util.Arrays;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.datatypes.primitive.MatrixPrimitiveUtils;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

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
    Catchers.catchNullFromArgList(matrix, 1);
    if (MatrixPrimitiveUtils.isRagged(matrix)) {
      throw new MathsExceptionIllegalArgument("Matrix representation must not be ragged, i.e. all rows must be the same length");
    }

    // check there is at least some data in the matrix.
    boolean dataOK = false;
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] != 0.e0) {
          dataOK = true;
        }
      }
    }
    if (!dataOK) {
      throw new MathsExceptionIllegalArgument("Matrix representation has no non-zero values. Blank matrices are not allowed");
    }

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
  public OGSparseArray(int[] colPtr, int[] rowIdx, double[] values, int rows, int columns) {
    Catchers.catchNullFromArgList(colPtr, 1);
    Catchers.catchNullFromArgList(rowIdx, 2);
    Catchers.catchNullFromArgList(values, 3);
    if (rows < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of rows specified. Value given was " + rows);
    }
    if (columns < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of columns specified. Value given was " + columns);
    }

    if (columns != colPtr.length - 1) {
      throw new MathsExceptionIllegalArgument("Columns specified does not commute with length of colPtr. Length colPtr (-1)= " + (colPtr.length - 1) + ", columns given = " + columns + ".");
    }

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

    _cols = columns;

    _els = rows * columns;
  }

  public OGDoubleArray getFullColumn(int index) {
    if (index < 0 || index >= _cols) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[_cols];
    for (int i = _colPtr[index]; i < _colPtr[index + 1]; i++) { // loops through elements of correct column
      tmp[_rowIdx[i]] = _values[i];
    }
    return new OGDoubleArray(tmp, _rows, 1);
  }

  public OGDoubleArray getFullRow(int index) { // getting rows in CSC form is generally bad
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[_rows];
    for (int i = 0; i < _rows; i++) {
      tmp[i] = this.getEntry(index, i);
    }
    return new OGDoubleArray(tmp, 1, _cols);
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
    if (indices.length > 2) {
      throw new MathsExceptionIllegalArgument("OGDoubleArray only has 2 indicies, more than 2 were given");
    }
    if (indices[0] >= _rows) {
      throw new MathsExceptionIllegalArgument("Row index" + indices[0] + " requested for matrix with only " + _rows + " rows");
    }
    if (indices[1] >= _cols) {
      throw new MathsExceptionIllegalArgument("Columns index" + indices[1] + " requested for matrix with only " + _cols + " columns");
    }
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
    return "\nvalues=" + Arrays.toString(_values) + "\nrowInd=" + Arrays.toString(_rowIdx) + "\ncolPtr=" + Arrays.toString(_colPtr) + "\ncols=" + _rows + "\nrows=" + _cols + "\nels=" + _els;

  }

  /**
   * Decide if this {@link OGSparseArray} is equal to another {@link OGSparseArray} with the addition of some numerical tolerance for floating point comparison
   * @param obj the {@link OGSparseArray} against which a comparison is to be drawn
   * @param tolerance the tolerance for double precision comparison of the data elements in the array
   * @return true if they are considered equal in value, false otherwise
   */
  public boolean fuzzyequals(Object obj, double tolerance) {
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
    double[] objData = other.getData();
    for (int i = 0; i < _values.length; i++) {
      if (Math.abs(_values[i] - objData[i]) > tolerance) {
        return false;
      }
    }
    return true;
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
