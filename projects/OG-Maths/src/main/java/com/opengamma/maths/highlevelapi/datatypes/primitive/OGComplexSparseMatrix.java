/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import java.util.Arrays;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.lowlevelapi.datatypes.primitive.MatrixPrimitiveUtils;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * Complex sparse matrix
 */
public class OGComplexSparseMatrix extends OGArray<ComplexType> {

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
   * @param realPart is a double[][]
   */
  public OGComplexSparseMatrix(double[][] realPart) {
    Catchers.catchNullFromArgList(realPart, 1);
    if (MatrixPrimitiveUtils.isRagged(realPart)) {
      throw new MathsExceptionIllegalArgument("Matrix representation must not be ragged, i.e. all rows must be the same length");
    }

    // check there is at least some data in the matrix.
    boolean dataOK = false;
    for (int i = 0; i < realPart.length; i++) {
      for (int j = 0; j < realPart[i].length; j++) {
        if (realPart[i][j] != 0.e0) {
          dataOK = true;
        }
      }
    }
    if (!dataOK) {
      throw new MathsExceptionIllegalArgument("Matrix representation has no non-zero values. Blank matrices are not allowed");
    }

    final int rows = realPart.length;
    final int cols = realPart[0].length;

    //get number of elements
    _els = rows * cols;

    // tmp arrays, in case we get in a fully populated matrix, intelligent design upstream should ensure that this is overkill!
    double[] dataTmp = new double[_els * 2];
    int[] colPtrTmp = new int[_els + 1];
    int[] rowIdxTmp = new int[_els];

    // we need unwind the array m into coordinate form
    int ptr = 0, dataptr = 0;
    int i;
    int localMaxEntrisInACol;
    _maxEntriesInAColumn = -1;
    double entry = 0;
    for (i = 0; i < cols; i++) {
      colPtrTmp[i] = ptr;
      localMaxEntrisInACol = 0;
      for (int j = 0; j < rows; j++) {
        entry = realPart[j][i];
        if (entry != 0.0e0) {
          rowIdxTmp[ptr] = j;
          dataTmp[dataptr] = entry;
          ptr++;
          dataptr += 2;
          localMaxEntrisInACol++;
        }
      }
      if (localMaxEntrisInACol > _maxEntriesInAColumn) {
        _maxEntriesInAColumn = localMaxEntrisInACol;
      }
    }
    colPtrTmp[i] = ptr;

    // return correct 0 to correct length of the vector buffers
    _values = Arrays.copyOfRange(dataTmp, 0, dataptr);
    _colPtr = Arrays.copyOfRange(colPtrTmp, 0, i + 1); // yes, the +1 is correct, it allows the computation of the number of elements in the final row!
    _rowIdx = Arrays.copyOfRange(rowIdxTmp, 0, ptr);
    _rows = rows;
    _cols = cols;
  }

  /**
   * Construct from two double[][] matrices, one containing the real part of the numbers the other containing the imaginary.
   * The arrays must not be ragged and must be of identical dimension. 
   * @param realPart a double[][] matrix containing the real part of the numbers
   * @param imaginaryPart a double[][] matrix containing the imaginary part of the numbers
   */
  public OGComplexSparseMatrix(double[][] realPart, double[][] imaginaryPart) {
    Catchers.catchNullFromArgList(realPart, 1);
    Catchers.catchNullFromArgList(imaginaryPart, 2);
    if (MatrixPrimitiveUtils.isRagged(realPart)) {
      throw new MathsExceptionIllegalArgument("Backing real array is ragged");
    }
    if (MatrixPrimitiveUtils.isRagged(imaginaryPart)) {
      throw new MathsExceptionIllegalArgument("Backing imaginary array is ragged");
    }
    int rows = realPart.length;
    int cols = realPart[0].length;

    if (imaginaryPart.length != rows) {
      throw new MathsExceptionIllegalArgument("Number of rows in first array (" + rows + ") does not match number of rows in second (" + imaginaryPart.length + ")");
    }
    if (imaginaryPart[0].length != cols) {
      throw new MathsExceptionIllegalArgument("Number of columns in first array (" + cols + ") does not match number of columns in second (" + imaginaryPart[0].length + ")");
    }

    // check there is at least some data in the matrix.
    boolean dataOK = false;
    for (int i = 0; i < realPart.length; i++) {
      for (int j = 0; j < realPart[i].length; j++) {
        if (realPart[i][j] != 0.e0) {
          dataOK = true;
          break;
        }
        if (imaginaryPart[i][j] != 0.e0) {
          dataOK = true;
          break;
        }
      }
    }
    if (!dataOK) {
      throw new MathsExceptionIllegalArgument("Matrix representation has no non-zero values. Blank matrices are not allowed");
    }

    //get number of elements
    _els = rows * cols;
    double[] dataTmp = new double[2 * _els];
    int[] colPtrTmp = new int[_els + 1];
    int[] rowIdxTmp = new int[_els];

    // we need unwind the array m into CSC form, note the rowIdx reflects the notion that 64bit complex representation is considered a single 128bit index
    // this is so the same data can be shoved into C without problem and the internal representation of a CSC real and CSC complex matrix is the same if the nonzero pattern is the same in each case 
    int ptr = 0, dataptr = 0;
    int i;
    int localMaxEntrisInACol;
    _maxEntriesInAColumn = -1;
    double realEntry = 0, imagEntry = 0;
    for (i = 0; i < cols; i++) {
      colPtrTmp[i] = ptr;
      localMaxEntrisInACol = 0;
      for (int j = 0; j < rows; j++) {
        realEntry = realPart[j][i];
        imagEntry = imaginaryPart[j][i];
        if (realEntry != 0.0e0 || imagEntry != 0.0e0) {
          rowIdxTmp[ptr] = j;
          dataTmp[dataptr] = realEntry;
          dataTmp[dataptr + 1] = imagEntry;
          ptr++;
          dataptr += 2;
          localMaxEntrisInACol++;
        }
      }
      if (localMaxEntrisInACol > _maxEntriesInAColumn) {
        _maxEntriesInAColumn = localMaxEntrisInACol;
      }
    }
    colPtrTmp[i] = ptr;

    // return correct 0 to correct length of the vector buffers
    _values = Arrays.copyOfRange(dataTmp, 0, dataptr);
    _colPtr = Arrays.copyOfRange(colPtrTmp, 0, i + 1); // yes, the +1 is correct, it allows the computation of the number of elements in the final row!
    _rowIdx = Arrays.copyOfRange(rowIdxTmp, 0, ptr);
    _rows = rows;
    _cols = cols;
  }

  /**
   * Construct from ComplexType[][] array
   * @param data a ComplexType[][] array
   */
  public OGComplexSparseMatrix(ComplexType[][] data) {
    Catchers.catchNullFromArgList(data, 1);
    if (MatrixPrimitiveUtils.isRagged(data)) {
      throw new MathsExceptionIllegalArgument("Backing array is ragged");
    }
    int rows = data.length;
    int cols = data[0].length;

    // check there is at least some data in the matrix.
    boolean dataOK = false;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (data[i][j].getReal() != 0.e0) {
          dataOK = true;
          break;
        }
        if (data[i][j].getImag() != 0.e0) {
          dataOK = true;
          break;
        }
      }
    }
    if (!dataOK) {
      throw new MathsExceptionIllegalArgument("Matrix representation has no non-zero values. Blank matrices are not allowed");
    }

    //get number of elements
    _els = rows * cols;
    double[] dataTmp = new double[2 * _els];
    int[] colPtrTmp = new int[_els + 1];
    int[] rowIdxTmp = new int[_els];

    // we need unwind the array m into CSC form, note the rowIdx reflects the notion that 64bit complex representation is considered a single 128bit index
    // this is so the same data can be shoved into C without problem and the internal representation of a CSC real and CSC complex matrix is the same if the nonzero pattern is the same in each case 
    int ptr = 0, dataptr = 0;
    int i;
    int localMaxEntrisInACol;
    _maxEntriesInAColumn = -1;
    double realEntry = 0, imagEntry = 0;
    for (i = 0; i < cols; i++) {
      colPtrTmp[i] = ptr;
      localMaxEntrisInACol = 0;
      for (int j = 0; j < rows; j++) {
        realEntry = data[j][i].getReal();
        imagEntry = data[j][i].getImag();
        if (realEntry != 0.0e0 || imagEntry != 0.0e0) {
          rowIdxTmp[ptr] = j;
          dataTmp[dataptr] = realEntry;
          dataTmp[dataptr + 1] = imagEntry;
          ptr++;
          dataptr += 2;
          localMaxEntrisInACol++;
        }
      }
      if (localMaxEntrisInACol > _maxEntriesInAColumn) {
        _maxEntriesInAColumn = localMaxEntrisInACol;
      }
    }
    colPtrTmp[i] = ptr;

    // return correct 0 to correct length of the vector buffers
    _values = Arrays.copyOfRange(dataTmp, 0, dataptr);
    _colPtr = Arrays.copyOfRange(colPtrTmp, 0, i + 1); // yes, the +1 is correct, it allows the computation of the number of elements in the final row!
    _rowIdx = Arrays.copyOfRange(rowIdxTmp, 0, ptr);
    _rows = rows;
    _cols = cols;
  }

  /**
   * Construct from underlying CSC representation
   * @param colPtr the columns pointers
   * @param rowIdx the row indexes
   * @param values the underlying values, interleaved [Real,Complex] pairs
   * @param rows the number of rows
   * @param columns the number of columns
   */
  public OGComplexSparseMatrix(int[] colPtr, int[] rowIdx, double[] values, int rows, int columns) {
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

    if (rowIdx.length * 2 != values.length) {
      throw new MathsExceptionIllegalArgument("Insufficient data or rowIdx values given, in complex data layout rowIdx.length*2 = data.length, however the data passed as rowIdx.length*2=" + 2 *
          rowIdx.length + "values.length=" + values.length + " .");
    }

    // check there is at least some data in the matrix.
    boolean dataOK = false;
    for (int i = 0; i < values.length; i++) {
      if (values[i] != 0.e0) {
        dataOK = true;
        break;
      }
    }
    if (!dataOK) {
      throw new MathsExceptionIllegalArgument("Matrix representation has no non-zero values. Blank matrices are not allowed");
    }

    final int vlen = values.length;
    _values = new double[vlen];
    System.arraycopy(values, 0, _values, 0, vlen);

    final int clen = colPtr.length;
    _colPtr = new int[clen];
    // check colptr is ascending

    if (colPtr[0] < 0) {
      throw new MathsExceptionIllegalArgument("Illegal value in colPtr[0], values should be zero or greater, bad value was " + colPtr[0]);
    }
    _colPtr[0] = colPtr[0];

    for (int i = 1; i < clen; i++) {
      if (colPtr[i] < colPtr[i - 1]) {
        throw new MathsExceptionIllegalArgument("Illegal value in colPtr, values should be ascending or static, descending value referenced at position " + i + ", bad value was " + colPtr[i]);
      }
      _colPtr[i] = colPtr[i];
    }

    final int rlen = rowIdx.length;
    _rowIdx = new int[rlen];
    // check rowIdx isn't out of range whilst copying in
    for (int i = 0; i < rlen; i++) {
      if (rowIdx[i] > rows || rowIdx[i] < 0) {
        throw new MathsExceptionIllegalArgument("Illegal value in rowIdx, row out of range referenced at position " + i + ", bad value was " + rowIdx[i]);
      }
      _rowIdx[i] = rowIdx[i];
    }

    _rows = rows;

    _cols = columns;

    _els = rows * columns;
  }

  /**
   * Construct from underlying CSC representation
   * @param colPtr the columns pointers
   * @param rowIdx the row indexes
   * @param realValues the underlying real part of the values
   * @param imagValues the underlying imaginary part of the values
   * @param rows the number of rows
   * @param columns the number of columns
   */
  public OGComplexSparseMatrix(int[] colPtr, int[] rowIdx, double[] realValues, double[] imagValues, int rows, int columns) {
    Catchers.catchNullFromArgList(colPtr, 1);
    Catchers.catchNullFromArgList(rowIdx, 2);
    Catchers.catchNullFromArgList(realValues, 3);
    Catchers.catchNullFromArgList(imagValues, 4);
    if (rows < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of rows specified. Value given was " + rows);
    }
    if (columns < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of columns specified. Value given was " + columns);
    }

    if (columns != colPtr.length - 1) {
      throw new MathsExceptionIllegalArgument("Columns specified does not commute with length of colPtr. Length colPtr (-1)= " + (colPtr.length - 1) + ", columns given = " + columns + ".");
    }

    if (rowIdx.length != realValues.length) {
      throw new MathsExceptionIllegalArgument("Insufficient data or rowIdx values given, they should be the same but got rowIdx.length=" + rowIdx.length + "values.length=" + realValues.length + " .");
    }

    if (realValues.length != imagValues.length) {
      throw new MathsExceptionIllegalArgument("The number of real values passed as \"realValues\" must match the number of imaginary values, passed as \"imagValues\"");
    }

    // check there is at least some data in the matrix.
    boolean dataOK = false;
    for (int i = 0; i < realValues.length; i++) {
      if (realValues[i] != 0.e0) {
        dataOK = true;
        break;
      }
      if (imagValues[i] != 0.e0) {
        dataOK = true;
        break;
      }
    }
    if (!dataOK) {
      throw new MathsExceptionIllegalArgument("Matrix representation has no non-zero values. Blank matrices are not allowed");
    }

    _values = DenseMemoryManipulation.convertTwoSinglePointersToInterleavedSinglePointer(realValues, imagValues);

    final int clen = colPtr.length;
    _colPtr = new int[clen];
    // check colptr is ascending

    if (colPtr[0] < 0) {
      throw new MathsExceptionIllegalArgument("Illegal value in colPtr[0], values should be zero or greater, bad value was " + colPtr[0]);
    }
    _colPtr[0] = colPtr[0];

    for (int i = 1; i < clen; i++) {
      if (colPtr[i] < colPtr[i - 1]) {
        throw new MathsExceptionIllegalArgument("Illegal value in colPtr, values should be ascending or static, descending value referenced at position " + i + ", bad value was " + colPtr[i]);
      }
      _colPtr[i] = colPtr[i];
    }

    final int rlen = rowIdx.length;
    _rowIdx = new int[rlen];
    // check rowIdx isn't out of range whilst copying in
    for (int i = 0; i < rlen; i++) {
      if (rowIdx[i] > rows || rowIdx[i] < 0) {
        throw new MathsExceptionIllegalArgument("Illegal value in rowIdx, row out of range referenced at position " + i + ", bad value was " + rowIdx[i]);
      }
      _rowIdx[i] = rowIdx[i];
    }

    _rows = rows;

    _cols = columns;

    _els = rows * columns;
  }

  /**
   * Gets a "full" column from the matrix at index "index"
   * @param index the column index at which to extract the data
   * @return the column requested in index
   */
  public OGComplexMatrix getFullColumn(int index) {
    if (index < 0 || index >= _cols) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[2 * _cols];
    for (int i = _colPtr[index]; i < _colPtr[index + 1]; i++) { // loops through elements of correct column
      tmp[_rowIdx[i] * 2] = _values[2 * i];
      tmp[_rowIdx[i] * 2 + 1] = _values[2 * i + 1];
    }
    return new OGComplexMatrix(tmp, _rows, 1);
  }

  /**
   * Gets a "full" row from the matrix at index "index"
   * @param index the row index at which to extract the data
   * @return the row requested in index
   */
  public OGComplexMatrix getFullRow(int index) { // getting rows in CSC form is generally bad
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[2 * _rows];
    ComplexType ctmp;
    int ptr = 0;
    for (int i = 0; i < _rows; i++) {
      ctmp = this.getEntry(index, i);
      tmp[ptr] = ctmp.getReal();
      tmp[ptr + 1] = ctmp.getImag();
      ptr += 2;
    }
    return new OGComplexMatrix(tmp, 1, _cols);
  }

  public int getNumberOfNonZeroElements() {
    return _values.length / 2;
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
  public ComplexType getEntry(int... indices) {
    if (indices.length > 2) {
      throw new MathsExceptionIllegalArgument("OGDoubleArray only has 2 indicies, more than 2 were given");
    }
    if (indices[0] >= _rows || indices[0] < 0) {
      throw new MathsExceptionIllegalArgument("Row index" + indices[0] + " requested for matrix with only " + _rows + " rows");
    }
    if (indices[1] >= _cols || indices[1] < 0) {
      throw new MathsExceptionIllegalArgument("Columns index" + indices[1] + " requested for matrix with only " + _cols + " columns");
    }
    //translate an index and see if it exists, if it doesn't then return 0
    for (int i = _colPtr[indices[1]]; i < _colPtr[indices[1] + 1]; i++) { // loops through elements of correct column
      // looks for col index
      if (_rowIdx[i] == indices[0]) {
        return new ComplexType(_values[i * 2], _values[i * 2 + 1]);
      }
    }
    return new ComplexType(0.0);
  }

  @Override
  public String toString() {
    return "\nvalues=" + Arrays.toString(_values) + "\nrowInd=" + Arrays.toString(_rowIdx) + "\ncolPtr=" + Arrays.toString(_colPtr) + "\ncols=" + _rows + "\nrows=" + _cols + "\nels=" + _els;

  }

  /**
   * Decide if this {@link OGComplexSparseMatrix} is equal to another {@link OGComplexSparseMatrix} with the addition of some numerical tolerance for floating point comparison
   * @param obj the {@link OGComplexSparseMatrix} against which a comparison is to be drawn
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
    OGComplexSparseMatrix other = (OGComplexSparseMatrix) obj;
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
    OGComplexSparseMatrix other = (OGComplexSparseMatrix) obj;
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
