/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes;

import java.util.Arrays;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * Holds a "diagonal" array
 */
public class OGComplexDiagonalMatrix extends OGArray<ComplexType> {

  private double[] _data;
  private int _columns;
  private int _rows;

  /**
   * Places the elements of an array "diag" of length "n" on the diagonal of a matrix of dimension [n * n]
   * @param real the real part of the elements to be placed on the diagonal, the imaginary part is assumed zero
   */
  public OGComplexDiagonalMatrix(double[] real) {
    Catchers.catchNullFromArgList(real, 1);
    int n = real.length;
    _rows = n;
    _columns = n;
    _data = DenseMemoryManipulation.convertSinglePointerToZeroInterleavedSinglePointer(real);
  }

  /**
   * Places the elements of an array "diag" of length "n" on the diagonal of a matrix of dimension [n * n]
   * @param real real part of the elements to be placed on the diagonal
   * @param imag imaginary part of the elements to be placed on the diagonal
   */
  public OGComplexDiagonalMatrix(double[] real, double[] imag) {
    Catchers.catchNullFromArgList(real, 1);
    Catchers.catchNullFromArgList(imag, 2);
    if (real.length != imag.length) {
      throw new MathsExceptionIllegalArgument("On construction data does not commute, real data is length " + real.length + " whilst imaginary data is length " + imag.length);
    }
    int n = real.length;
    _rows = n;
    _columns = n;
    _data = DenseMemoryManipulation.convertTwoSinglePointersToInterleavedSinglePointer(real, imag);

  }

  /**
   * Places the elements of an array "diag" of length "n" on the diagonal of a matrix of dimension [n * n]
   * @param diag the elements to be placed on the diagonal
   */
  public OGComplexDiagonalMatrix(ComplexType[] diag) {
    Catchers.catchNullFromArgList(diag, 1);
    int n = diag.length;
    _rows = n;
    _columns = n;
    _data = new double[2 * n];
    int ptr = 0;
    for (int i = 0; i < n; i++) {
      _data[ptr] = diag[i].getReal();
      _data[ptr + 1] = diag[i].getImag();
      ptr += 2;
    }
  }

  /**
   * Places the elements of an array "diag" of length "n" as the real parts of number on the diagonal of a matrix of dimension [rows * columns]
   * @param diag real part of the elements to be placed on the diagonal
   * @param rows number of rows (if less than "n", diag is truncated to fit)
   * @param columns number of columns (if less than "n", diag is truncated to fit)
   */
  public OGComplexDiagonalMatrix(double[] diag, int rows, int columns) {
    Catchers.catchNullFromArgList(diag, 1);
    if (rows < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of rows specified. Value given was " + rows);
    }
    if (columns < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of columns specified. Value given was " + columns);
    }
    _rows = rows;
    _columns = columns;
    int len = Math.min(diag.length, Math.min(rows, columns));
    _data = new double[len * 2];
    double[] tmp = DenseMemoryManipulation.convertSinglePointerToZeroInterleavedSinglePointer(diag);
    System.arraycopy(tmp, 0, _data, 0, len * 2);
  }

  /**
   * Places the elements of an array "diag" of length "n" as the real parts of number on the diagonal of a matrix of dimension [rows * columns]
   * @param real real part of the elements to be placed on the diagonal
   * @param imag imaginary part of the elements to be placed on the diagonal 
   * @param rows number of rows (if less than "n", diag is truncated to fit)
   * @param columns number of columns (if less than "n", diag is truncated to fit)
   */
  public OGComplexDiagonalMatrix(double[] real, double[] imag, int rows, int columns) {
    Catchers.catchNullFromArgList(real, 1);
    Catchers.catchNullFromArgList(imag, 2);
    if (rows < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of rows specified. Value given was " + rows);
    }
    if (columns < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of columns specified. Value given was " + columns);
    }
    if (real.length != imag.length) {
      throw new MathsExceptionIllegalArgument("On construction data does not commute, real data is length " + real.length + " whilst imaginary data is length " + imag.length);
    }
    _rows = rows;
    _columns = columns;
    int len = Math.min(real.length, Math.min(rows, columns));
    _data = new double[len * 2];
    double[] tmp = DenseMemoryManipulation.convertTwoSinglePointersToInterleavedSinglePointer(real, imag);
    System.arraycopy(tmp, 0, _data, 0, len * 2);
  }

  /**
   * Places the "diag" as the first element on the diagonal of a matrix of dimension [rows * columns]
   * @param diag the element to be placed on in the first entry of the diagonal
   * @param rows number of rows
   * @param columns number of columns
   */
  public OGComplexDiagonalMatrix(double diag, int rows, int columns) {
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
   * Places the "diag" as the first element on the diagonal of a matrix of dimension [rows * columns]
   * @param real the real part of the element to be placed on in the first entry of the diagonal
   * @param imag the imaginary part of the element to be placed on in the first entry of the diagonal
   * @param rows number of rows
   * @param columns number of columns
   */
  public OGComplexDiagonalMatrix(double real, double imag, int rows, int columns) {
    if (rows < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of rows specified. Value given was " + rows);
    }
    if (columns < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of columns specified. Value given was " + columns);
    }
    _rows = rows;
    _columns = columns;
    _data = new double[2];
    _data[0] = real;
    _data[1] = imag;
  }

  /**
   * Places the "diag" as the first element on the diagonal of a matrix of dimension [1 * 1]
   * The imaginary part is assumed to be zero
   * @param diag the element to be placed on in the first entry of the diagonal
   */
  public OGComplexDiagonalMatrix(double diag) {
    this(diag, 1, 1);
  }

  /**
   * Places the "diag" as the first element on the diagonal of a matrix of dimension [1 * 1]
   * @param real the real part of the element to be placed on in the first entry of the diagonal
   * @param imag the imaginary part of the element to be placed on in the first entry of the diagonal
   */
  public OGComplexDiagonalMatrix(double real, double imag) {
    this(real, imag, 1, 1);
  }

  /**
   * Places the "diag" as the first element on the diagonal of a matrix of dimension [1 * 1]
   * The imaginary part is assumed to be zero
   * @param diag the element to be placed on in the first entry of the diagonal
   */
  public OGComplexDiagonalMatrix(int diag) {
    this((double) diag, 1, 1);
  }

  public int getNumberOfRows() {
    return _rows;
  }

  public int getNumberOfColumns() {
    return _columns;
  }

  @Override
  public OGArray<? extends Number> getColumn(int index) {
    if (index < 0 || index >= _columns) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    if (index >= _data.length) {
      return new OGComplexMatrix(new double[2 * _rows], _rows, 1);
    }
    double[] tmp = new double[2 * _rows];
    tmp[index * 2] = _data[index * 2];
    tmp[index * 2 + 1] = _data[index * 2 + 1];
    return new OGComplexMatrix(tmp, _rows, 1);
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
    double[] tmp = new double[2 * nindex * _rows];
    int it2, ir, twor = 2 * _rows;
    for (int i = 0; i < nindex; i++) {
      it2 = indexes[i] * 2;
      if (indexes[i] < _data.length) {
        ir = i * twor;
        tmp[ir + it2] = _data[it2];
        tmp[ir + it2 + 1] = _data[it2 + 1];
      }
    }
    return new OGComplexMatrix(tmp, _rows, nindex);
  }

  @Override
  public OGArray<? extends Number> getRow(int index) {
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[2 * _columns];
    tmp[index * 2] = _data[index * 2];
    tmp[index * 2 + 1] = _data[index * 2 + 1];
    return new OGComplexMatrix(tmp, 1, _columns);
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
    double[] tmp = new double[2 * _columns * nindex];
    int idx, twoidx, jmp;
    for (int i = 0; i < nindex; i++) {
      idx = indexes[i];
      twoidx = 2 * idx;
      jmp = (twoidx * nindex + i * 2);
      if (idx < _data.length) {
        tmp[jmp] = _data[2 * idx];
        tmp[jmp + 1] = _data[2 * idx + 1];
      }
    }
    return new OGComplexMatrix(tmp, nindex, _columns);
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
      if (requestedStart * 2 >= dataExtent) { // asking for data outside of range of nonzero diag, return
        retarr = new OGMatrix(new double[nrows * ncols], nrows, ncols);
      } else { // (requestedEnd * 2 <= dataExtent) { // entire request is in data range
        tmp = Arrays.copyOfRange(_data, 2 * requestedStart, 2 * requestedEnd);
        double[] realtmp = new double[tmp.length / 2];
        double[] imagtmp = new double[tmp.length / 2];
        for (int i = 0; i < realtmp.length; i++) {
          realtmp[i] = tmp[i * 2];
          imagtmp[i] = tmp[i * 2 + 1];
        }
        retarr = new OGComplexDiagonalMatrix(realtmp, imagtmp, nrows, ncols);
      }
    } else { // we are essentially picking bits at random *yay* TODO: this could be considerable more efficient
      tmp = new double[ncols * nrows * 2];
      int idxi, idxj;
      for (int i = 0; i < ncols; i++) {
        idxi = columns[i];
        for (int j = 0; j < nrows; j++) {
          idxj = rows[j];
          if (idxi == idxj) {
            if (idxi < dataExtent) {
              tmp[(i * nrows + j) * 2] = _data[idxi * 2];
              tmp[(i * nrows + j) * 2 + 1] = _data[idxi * 2 + 1];
            }
          }
        }
      }
      retarr = new OGComplexMatrix(tmp, nrows, ncols);
    }
    return retarr;
  }

  /**
   * Returns the backing data
   * @return the backing data
   */
  public double[] getData() {
    return _data;
  }

  /**
   * Decide if this {@link OGComplexDiagonalMatrix} is equal to another {@link OGComplexDiagonalMatrix} with the addition of some numerical tolerance for floating point comparison
   * @param obj the {@link OGComplexDiagonalMatrix} against which a comparison is to be drawn
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
    OGComplexDiagonalMatrix other = (OGComplexDiagonalMatrix) obj;
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
  public ComplexType getEntry(int... indices) {
    if (indices.length > 2) {
      throw new MathsExceptionIllegalArgument("OGDiagonalArray only has 2 indicies, more than 2 were given");
    }
    if (indices[0] >= _rows || indices[0] < 0) {
      throw new MathsExceptionIllegalArgument("Row index" + indices[0] + " requested for matrix with only " + _rows + " rows");
    }
    if (indices[1] >= _columns || indices[1] < 0) {
      throw new MathsExceptionIllegalArgument("Columns index" + indices[1] + " requested for matrix with only " + _columns + " columns");
    }
    ComplexType ret = new ComplexType(0); // default entry is 0
    final int twoup = 2 * indices[0];
    if (indices[0] == indices[1]) {
      ret = new ComplexType(_data[twoup], _data[twoup + 1]);
    }
    return ret;
  }

  @Override
  public OGArray<? extends Number> setEntry(int row, int col, Number value) {
    Catchers.catchNull(value);
    super.entryValidator(row, col);
    if (row != col) { // setting an off diag
      final int scal = 2;
      boolean isComplex = false;
      if (value instanceof ComplexType) {
        isComplex = true;
      }
      double[] tmp = new double[scal * _rows * _columns];
      int min = Math.min(_rows, _columns);
      int max = Math.max(_rows, _columns);
      int idx;
      int ptr = 0;
      for (int i = 0; i < min; i++) { // unwind
        idx = scal * (i * max + i);
        tmp[idx] = _data[ptr];
        tmp[idx + 1] = _data[ptr + 1];
        ptr += 2;
      }
      // inject type in right place
      idx = scal * (col * _rows + row);
      if (isComplex) {
        tmp[idx] = ((ComplexType) value).getReal();
        tmp[idx + 1] = ((ComplexType) value).getImag();
        return new OGComplexMatrix(tmp, _rows, _columns);
      } else {
        tmp[idx] = value.doubleValue();
        tmp[idx + 1] = 0.d;
        return new OGComplexMatrix(tmp, _rows, _columns);
      }
    } else { // setting a diag value
      if (value instanceof ComplexType) {
        _data[2 * Math.min(row, col)] = ((ComplexType) value).getReal();
        _data[2 * Math.min(row, col) + 1] = ((ComplexType) value).getImag();
        return this;
      } else {
        _data[2 * Math.min(row, col)] = value.doubleValue();
        _data[2 * Math.min(row, col) + 1] = 0.d;
        return this;
      }
    }
  }

  @Override
  public String toString() {
    String str = "OGComplexDiagonalArray:" + "\ndata = " + Arrays.toString(_data) + "\nrows = " + _rows + "\ncols = " + _columns;
    str = str + "\n====Pretty Print====\n";
    String zeroStr = String.format("%24.18f + %24.18fi ", 0.d, 0.d);
    double imag;
    for (int i = 0; i < 2 * _rows; i += 2) {
      for (int j = 0; j < 2 * _columns; j += 2) {
        if (i == j && i < _data.length && j < _data.length) {
          imag = _data[i + 1];
          str += String.format("%24.18f " + (imag >= 0 ? "+" : "-") + "%24.18fi, ", _data[i], Math.abs(imag));
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
    OGComplexDiagonalMatrix other = (OGComplexDiagonalMatrix) obj;
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
