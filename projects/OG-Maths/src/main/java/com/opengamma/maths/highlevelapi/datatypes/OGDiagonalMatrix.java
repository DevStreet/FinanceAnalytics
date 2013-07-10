/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes;

import java.util.Arrays;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * Holds a "diagonal" array
 */
public class OGDiagonalMatrix extends OGArray<Double> {

  private double[] _data;
  private int _columns;
  private int _rows;

  /**
   * Places the elements of an array "diag" of length "n" on the diagonal of a matrix of dimension [n * n]
   * @param diag the elements to be placed on the diagonal
   */
  public OGDiagonalMatrix(double[] diag) {
    Catchers.catchNullFromArgList(diag, 1);
    int n = diag.length;
    _rows = n;
    _columns = n;
    _data = new double[n];
    System.arraycopy(diag, 0, _data, 0, n);
  }

  /**
   * Places the elements of an array "diag" of length "n" on the diagonal of a matrix of dimension [rows * columns]
   * @param diag the elements to be placed on the diagonal
   * @param rows number of rows (if less than "n", diag is truncated to fit)
   * @param columns number of columns (if less than "n", diag is truncated to fit)
   */
  public OGDiagonalMatrix(double[] diag, int rows, int columns) {
    if (diag == null) {
      throw new MathsExceptionNullPointer("dataIn is null");
    }
    if (rows < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of rows specified. Value given was " + rows);
    }
    if (columns < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of columns specified. Value given was " + columns);
    }
    Catchers.catchNullFromArgList(diag, 1);
    _rows = rows;
    _columns = columns;
    int len = Math.min(diag.length, Math.min(rows, columns));
    _data = new double[len];
    System.arraycopy(diag, 0, _data, 0, len);
  }

  /**
   * Places the "diag" as the first element on the diagonal of a matrix of dimension [rows * columns]
   * @param diag the element to be placed on in the first entry of the diagonal
   * @param rows number of rows
   * @param columns number of columns
   */
  public OGDiagonalMatrix(double diag, int rows, int columns) {
    if (rows < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of rows specified. Value given was " + rows);
    }
    if (columns < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of columns specified. Value given was " + columns);
    }
    _rows = rows;
    _columns = columns;
    int len = 1;
    _data = new double[len];
    _data[0] = diag;
  }

  /**
   * Places the "diag" as the first element on the diagonal of a matrix of dimension [1 * 1]
   * @param diag the element to be placed on in the first entry of the diagonal
   */
  public OGDiagonalMatrix(double diag) {
    this(diag, 1, 1);
  }

  /**
   * Places the "diag" as the first element on the diagonal of a matrix of dimension [1 * 1]
   * @param diag the element to be placed on in the first entry of the diagonal
   */
  public OGDiagonalMatrix(int diag) {
    this((double) diag, 1, 1);
  }

  @Override
  public OGArray<? extends Number> getColumn(int index) {
    if (index < 0 || index >= _columns) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[_rows];
    if (index < _data.length) { // data might be short if there's zeros on diag
      tmp[index] = _data[index];
    }
    return new OGMatrix(tmp, _rows, 1);
  }

  @Override
  public OGArray<? extends Number> getColumns(int... indexes) {

    Catchers.catchNullFromArgList(indexes, 1);
    final int nindex = indexes.length;
    int index;
    for (int i = 0; i < nindex; i++) {
      index = indexes[i];
      if (index < 0 || index >= _columns) {
        throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
      }
    }
    double[] tmp = new double[nindex * _rows];
    int idx, ir;
    for (int i = 0; i < nindex; i++) {
      idx = indexes[i];
      if (idx < _data.length) { // data might be short if there's zeros on diag      
        ir = i * _rows;
        tmp[ir + idx] = _data[idx];
      }
    }
    return new OGMatrix(tmp, _rows, nindex);
  }

  @Override
  public OGArray<? extends Number> getRow(int index) {
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[_columns];
    if (index < _data.length) { // data might be short if there's zeros on diag
      tmp[index] = _data[index];
    }
    return new OGMatrix(tmp, 1, _columns);
  }

  @Override
  public OGArray<? extends Number> getRows(int... indexes) {
    Catchers.catchNullFromArgList(indexes, 1);
    final int nindex = indexes.length;
    int index;
    for (int i = 0; i < nindex; i++) {
      index = indexes[i];
      if (index < 0 || index >= _columns) {
        throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
      }
    }
    double[] tmp = new double[_columns * nindex];
    int idx;
    for (int i = 0; i < nindex; i++) {
      idx = indexes[i];
      if (idx < _data.length) {
        tmp[idx * nindex + i] = _data[idx];
      }
    }
    return new OGMatrix(tmp, nindex, _columns);
  }

  @Override
  public OGArray<? extends Number> get(int[] rows, int[] columns) {
    Catchers.catchNullFromArgList(rows, 1);
    Catchers.catchNullFromArgList(columns, 1);
    final int nrows = rows.length;
    final int ncols = columns.length;
    int index;
    boolean seqRows = true, seqCols = true; //TODO: at some point we should probably check for decreasing sequences too
    for (int i = 0; i < nrows; i++) {
      index = rows[i];
      if (index < 0 || index >= _rows) {
        throw new MathsExceptionIllegalArgument("Invalid row index. Value given was " + index);
      }
      if (i > 0) {
        if (rows[i] != rows[i - 1] + 1) {
          seqRows = false;
        }
      }
    }
    for (int i = 0; i < ncols; i++) {
      index = columns[i];
      if (index < 0 || index >= _columns) {
        throw new MathsExceptionIllegalArgument("Invalid column index. Value given was " + index);
      }
      if (i > 0) {
        if (columns[i] != columns[i - 1] + 1) {
          seqCols = false;
        }
      }
    }

    OGArray<? extends Number> retarr = null;
    double[] tmp;
    int dataExtent = _data.length;
    if (seqCols && seqRows) { // its a smaller diagonal matrix, possibly!
      // how much does the current data stick out in comparison to the requested area
      int minRow = rows[0];
      int maxRow = rows[rows.length - 1];
      int minCol = columns[0];
      int maxCol = columns[columns.length - 1];
      int requestedStart = Math.max(minRow, minCol);
      int requestedEnd = Math.max(maxRow, maxCol);
      if (requestedStart >= dataExtent) { // asking for data outside of range of nonzero diag, return
        retarr = new OGMatrix(new double[nrows * ncols], nrows, ncols);
      } else { // (requestedEnd  <= dataExtent) { // entire request is in data range
        tmp = Arrays.copyOfRange(_data, requestedStart, requestedEnd);
        retarr = new OGDiagonalMatrix(tmp, nrows, ncols);
      }
    } else {
      tmp = new double[ncols * nrows];
      int idxi, idxj;
      for (int i = 0; i < ncols; i++) {
        idxi = columns[i];
        for (int j = 0; j < nrows; j++) {
          idxj = rows[j];
          if (idxi == idxj) {
            if (idxi < dataExtent) {
              tmp[i * nrows + j] = _data[idxi];
            }
          }
        }
      }
      retarr = new OGMatrix(tmp, nrows, ncols);
    }
    return retarr;
  }

  public int getNumberOfRows() {
    return _rows;
  }

  public int getNumberOfColumns() {
    return _columns;
  }

  /**
   * Returns the backing data
   * @return the backing data
   */
  public double[] getData() {
    return _data;
  }

  /**
   * Decide if this {@link OGDiagonalMatrix} is equal to another {@link OGDiagonalMatrix} with the addition of some numerical tolerance for floating point comparison
   * @param obj the {@link OGDiagonalMatrix} against which a comparison is to be drawn
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
    OGDiagonalMatrix other = (OGDiagonalMatrix) obj;
    if (_columns != other._columns) {
      return false;
    }
    if (_rows != other._rows) {
      return false;
    }
    double[] objData = other.getData();
    for (int i = 0; i < _data.length; i++) {
      if (Math.abs(_data[i] - objData[i]) > tolerance) {
        return false;
      }
    }
    return true;
  }

  @Override
  public OGArray<? extends Number> setEntry(int row, int col, Number value) {
    Catchers.catchNull(value);
    super.entryValidator(row, col);
    if (row != col) { // setting an off diag
      int scal;
      boolean isComplex = false;
      if (value instanceof ComplexType) {
        isComplex = true;
        scal = 2;
      } else {
        scal = 1;
      }
      double[] tmp = new double[scal * _rows * _columns];
      int min = Math.min(_rows, _columns);
      int max = Math.max(_rows, _columns);
      int idx;
      for (int i = 0; i < min; i++) { // unwind
        idx = scal * (i * max + i);
        tmp[idx] = _data[i];
      }
      // inject type in right place
      if (isComplex) {
        idx = scal * (col * _rows + row);
        tmp[idx] = ((ComplexType) value).getReal();
        tmp[idx + 1] = ((ComplexType) value).getImag();
        return new OGComplexMatrix(tmp, _rows, _columns);
      } else {
        tmp[(col * _rows + row)] = value.doubleValue();
        return new OGMatrix(tmp, _rows, _columns);
      }
    } else { // setting a diag value
      if (value instanceof ComplexType) {
        double[] complextmp = DenseMemoryManipulation.convertSinglePointerToZeroInterleavedSinglePointer(_data);
        int idx = 2 * row;
        complextmp[idx] = ((ComplexType) value).getReal();
        complextmp[idx + 1] = ((ComplexType) value).getImag();
        return new OGComplexDiagonalMatrix(complextmp, _rows, _columns);
      } else {
        _data[Math.min(row, col)] = value.doubleValue();
        return this;
      }
    }
  }

  @Override
  public Double getEntry(int... indices) {
    if (indices.length > 2) {
      throw new MathsExceptionIllegalArgument("OGDiagonalArray only has 2 indicies, more than 2 were given");
    }
    if (indices[0] >= _rows) {
      throw new MathsExceptionIllegalArgument("Row index" + indices[0] + " requested for matrix with only " + _rows + " rows");
    }
    if (indices[1] >= _columns) {
      throw new MathsExceptionIllegalArgument("Columns index" + indices[1] + " requested for matrix with only " + _columns + " columns");
    }
    double ret = 0; // default entry is 0
    if (indices[0] == indices[1]) {
      ret = _data[indices[0]];
    }
    return ret;
  }

  @Override
  public String toString() {
    String str = "OGDiagonalArray:" + "\ndata = " + Arrays.toString(_data) + "\nrows = " + _rows + "\ncols = " + _columns;
    str = str + "\n====Pretty Print====\n";
    String zeroStr = String.format("%24.18f ", 0.d);
    for (int i = 0; i < _rows; i++) {
      for (int j = 0; j < _columns; j++) {
        if (i == j && i < _data.length && j < _data.length) {
          str += String.format("%24.18f ", _data[i]);
        } else {
          str += zeroStr;
        }
      }
      str += String.format("\n");
    }
    return str;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _columns;
    result = prime * result + Arrays.hashCode(_data);
    result = prime * result + _rows;
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
    OGDiagonalMatrix other = (OGDiagonalMatrix) obj;
    if (_columns != other._columns) {
      return false;
    }
    if (_rows != other._rows) {
      return false;
    }
    if (!Arrays.equals(_data, other._data)) {
      return false;
    }
    return true;
  }

}
