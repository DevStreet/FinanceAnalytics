/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import java.util.Arrays;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.functions.MatrixPrimitiveUtils;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * 
 */
public class OGSparseMatrix extends OGArray<Double> {

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
  public OGSparseMatrix(double[][] matrix) {
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
  public OGSparseMatrix(int[] colPtr, int[] rowIdx, double[] values, int rows, int columns) {
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

    if (rowIdx.length != values.length) {
      throw new MathsExceptionIllegalArgument("Insufficient data or rowIdx values given, they should be the same but got rowIdx.length=" + rowIdx.length + "values.length=" + values.length + " .");
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

  public OGMatrix getFullColumn(int index) {
    if (index < 0 || index >= _cols) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[_rows];
    for (int i = _colPtr[index]; i < _colPtr[index + 1]; i++) { // loops through elements of correct column
      tmp[_rowIdx[i]] = _values[i];
    }
    return new OGMatrix(tmp, _rows, 1);
  }

  @Override
  public OGArray<? extends Number> getColumns(int... indexes) {
    Catchers.catchNullFromArgList(indexes, 1);
    final int nindex = indexes.length;
    int index;
    boolean seq = true;
    for (int i = 0; i < nindex; i++) {
      index = indexes[i];
      if (index < 0 || index >= _cols) {
        throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
      }
      if (i > 0) {
        if (indexes[i] != indexes[i - 1] + 1) {
          seq = false;
        }
      }
    }
    double[] dataTmp = null;
    int[] colPtrTmp = null, rowIdxTmp = null;

    int start;
    int end;

    if (seq) {
      end = _colPtr[indexes[nindex - 1] + 1];
      start = _colPtr[indexes[0]];
      dataTmp = new double[(end - start)];
      System.arraycopy(_values, start, dataTmp, 0, (end - start));
      rowIdxTmp = new int[end - start];
      System.arraycopy(_rowIdx, start, rowIdxTmp, 0, end - start);
      colPtrTmp = new int[nindex + 1];
      System.arraycopy(_colPtr, indexes[0], colPtrTmp, 0, nindex + 1);
      for (int i = 0; i <= nindex; i++) {
        colPtrTmp[i] -= _colPtr[indexes[0]];
      }
      return new OGSparseMatrix(colPtrTmp, rowIdxTmp, dataTmp, _rows, nindex);
    } else {
      dataTmp = new double[_rows * nindex];
      rowIdxTmp = new int[_rows * nindex];
      int jmp = 0;
      colPtrTmp = new int[nindex + 1];
      for (int i = 0; i < nindex; i++) {
        colPtrTmp[i] = jmp;
        index = indexes[i];
        start = _colPtr[index];
        end = _colPtr[index + 1];
        System.arraycopy(_values, start, dataTmp, jmp, (end - start));
        System.arraycopy(_rowIdx, start, rowIdxTmp, jmp, end - start);
        jmp += (end - start);
      }
      colPtrTmp[nindex] = jmp;
      return new OGSparseMatrix(colPtrTmp, Arrays.copyOf(rowIdxTmp, jmp), Arrays.copyOf(dataTmp, jmp), _rows, nindex);
    }

  }

  public OGMatrix getFullRow(int index) { // getting rows in CSC form is generally bad
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[_cols];
    for (int i = 0; i < _cols; i++) {
      tmp[i] = this.getEntry(index, i);
    }
    return new OGMatrix(tmp, 1, _cols);
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
    for (int i = _colPtr[indices[1]]; i < _colPtr[indices[1] + 1]; i++) { // loops through elements of correct column
      // looks for col index
      if (_rowIdx[i] == indices[0]) {
        return _values[i];
      }
    }
    return 0.0;
  }

  @Override
  public OGArray<? extends Number> getColumn(int index) {
    if (index < 0 || index >= _cols) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] dataTmp;
    int[] colPtrTmp, rowIdxTmp;
    int start = _colPtr[index];
    int end = _colPtr[index + 1];
    dataTmp = new double[end - start];
    System.arraycopy(_values, start, dataTmp, 0, end - start);
    rowIdxTmp = new int[end - start];
    System.arraycopy(_rowIdx, start, rowIdxTmp, 0, end - start);
    colPtrTmp = new int[2];
    colPtrTmp[0] = 0;
    colPtrTmp[1] = end - start;

    return new OGSparseMatrix(colPtrTmp, rowIdxTmp, dataTmp, _rows, 1);
  }

  @Override
  public OGSparseMatrix getRow(int index) { // getting rows in CSC form is generally bad
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] dataTmp = new double[_cols];
    int[] colPtrTmp = new int[_cols + 1];
    int dataptr = 0;
    int idxptr = 0;
    for (int i = 0; i < _cols; i++) {
      colPtrTmp[i] = idxptr;
      for (int j = _colPtr[i]; j < _colPtr[i + 1]; j++) { // loops through elements of correct column
        if (_rowIdx[j] == index) {
          dataTmp[dataptr] = _values[j];
          dataptr++;
          idxptr++;
        }
      }
    }
    int[] rowIdxTmp = new int[idxptr];
    colPtrTmp[_cols] = idxptr; // tie up end
    return new OGSparseMatrix(colPtrTmp, rowIdxTmp, Arrays.copyOf(dataTmp, dataptr), 1, _cols);
  }

  @Override
  public OGSparseMatrix getRows(int... indexes) { // getting rows in CSC form is generally bad
    Catchers.catchNullFromArgList(indexes, 1);
    final int nindex = indexes.length;
    int index;
    for (int i = 0; i < nindex; i++) {
      index = indexes[i];
      if (index < 0 || index >= _cols) {
        throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
      }
    }
    double[] dataTmp = new double[_cols * nindex];
    int[] rowIdxTmp = new int[_cols * nindex];
    int[] colPtrTmp = new int[_cols + 1];
    int idxptr = 0;
    for (int i = 0; i < _cols; i++) {
      colPtrTmp[i] = idxptr;
      for (int k = 0; k < nindex; k++) {
        for (int j = _colPtr[i]; j < _colPtr[i + 1]; j++) { // loops through elements of correct column
          if (_rowIdx[j] == indexes[k]) {
            dataTmp[idxptr] = _values[j];
            rowIdxTmp[idxptr] = k;
            idxptr++;
          }
        }
      }
    }
    colPtrTmp[_cols] = idxptr; // tie up end
    return new OGSparseMatrix(colPtrTmp, Arrays.copyOf(rowIdxTmp, idxptr), Arrays.copyOf(dataTmp, idxptr), nindex, _cols);
  }

  @Override
  public OGArray<? extends Number> get(int[] rows, int[] columns) {
    Catchers.catchNullFromArgList(rows, 1);
    Catchers.catchNullFromArgList(columns, 1);
    final int nrows = rows.length;
    final int ncols = columns.length;
    int index;
    for (int i = 0; i < nrows; i++) {
      index = rows[i];
      if (index < 0 || index >= _rows) {
        throw new MathsExceptionIllegalArgument("Invalid row index. Value given was " + index);
      }
    }

    for (int i = 0; i < ncols; i++) {
      index = columns[i];
      if (index < 0 || index >= _cols) {
        throw new MathsExceptionIllegalArgument("Invalid column index. Value given was " + index);
      }
    }

    double[] dataTmp = new double[ncols * nrows];
    int[] colPtrTmp = new int[ncols + 1];
    int[] rowIdxTmp = new int[ncols * nrows];
    int idxi, idxj, idxptr = 0;
    for (int i = 0; i < ncols; i++) {
      colPtrTmp[i] = idxptr;
      idxi = columns[i];
      for (int j = 0; j < nrows; j++) {
        idxj = rows[j];
        for (int k = _colPtr[idxi]; k < _colPtr[idxi + 1]; k++) {
          if (_rowIdx[k] == idxj) {
            dataTmp[idxptr] = _values[k];
            rowIdxTmp[idxptr] = j;
            idxptr++;
          }
        }
      }
    }
    colPtrTmp[ncols] = idxptr; // tie up end
    return new OGSparseMatrix(colPtrTmp, Arrays.copyOf(rowIdxTmp, idxptr), Arrays.copyOf(dataTmp, idxptr), nrows, ncols);
  }

  @Override
  public String toString() {
    return "\nvalues=" + Arrays.toString(_values) + "\nrowInd=" + Arrays.toString(_rowIdx) + "\ncolPtr=" + Arrays.toString(_colPtr) + "\ncols=" + _cols + "\nrows=" + _rows + "\nels=" + _els;
  }

  /**
   * Decide if this {@link OGSparseMatrix} is equal to another {@link OGSparseMatrix} with the addition of some numerical tolerance for floating point comparison
   * @param obj the {@link OGSparseMatrix} against which a comparison is to be drawn
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
    OGSparseMatrix other = (OGSparseMatrix) obj;
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
    OGSparseMatrix other = (OGSparseMatrix) obj;
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
