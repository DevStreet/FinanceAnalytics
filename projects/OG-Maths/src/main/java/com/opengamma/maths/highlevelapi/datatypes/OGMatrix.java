/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes;

import java.util.Arrays;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionOutOfBounds;
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * High level API.
 * Dense matrix backed array magic.
 */
public class OGMatrix extends OGArray<Double> {

  private double[] _data;
  private int _columns;
  private int _rows;

  /**
   * Takes a row major java double[][] and turns it into an OGDoubleArray
   * @param dataIn a row major java double[][] 
   */
  public OGMatrix(double[][] dataIn) {
    Catchers.catchNullFromArgList(dataIn, 1);
    _data = DenseMemoryManipulation.convertRowMajorDoublePointerToColumnMajorSinglePointer(dataIn);
    _rows = dataIn.length;
    _columns = dataIn[0].length;
  }

  /**
   * Takes a column major double[] and turns it into an OGDoubleArray
   * @param dataIn the backing data
   * @param rows number of rows
   * @param columns number of columns
   */
  public OGMatrix(double[] dataIn, int rows, int columns) {
    if (dataIn == null) {
      throw new MathsExceptionNullPointer("dataIn is null");
    }
    if (rows < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of rows specified. Value given was " + rows);
    }
    if (columns < 1) {
      throw new MathsExceptionIllegalArgument("Illegal number of columns specified. Value given was " + columns);
    }
    int len = rows * columns;
    if (len != dataIn.length) {
      throw new MathsExceptionIllegalArgument("Number of rows and columns specified does not commute with the quantity of data supplied");
    }
    _data = new double[len];
    System.arraycopy(dataIn, 0, _data, 0, len);
    _rows = rows;
    _columns = columns;
  }

  /**
   * @param number the single number in this array
   */
  public OGMatrix(double number) {
    _columns = 1;
    _rows = 1;
    _data = new double[1];
    _data[0] = number;
  }

  /**
   * Takes a double[] and turns it into an OGMatrix as a single row
   * @param dataIn the backing data
   */
  public OGMatrix(double[] dataIn) {
    if (dataIn == null) {
      throw new MathsExceptionNullPointer("dataIn is null");
    }
    int len = dataIn.length;
    _data = new double[len];
    System.arraycopy(dataIn, 0, _data, 0, len);
    _rows = 1;
    _columns = len;
  }

  @Override
  public int getNumberOfRows() {
    return _rows;
  }

  @Override
  public int getNumberOfColumns() {
    return _columns;
  }

  @Override
  public Double getEntry(int... indices) {
    super.entryValidator(indices);
    if (indices.length == 1) {
      return _data[indices[0]];
    } else { // 2d mode
      return _data[indices[1] * _rows + indices[0]];
    }
  }

  public OGArray<? extends Number> setEntry(int row, int column, Number value) {
    Catchers.catchNull(value);
    super.entryValidator(row, column);
    if (value instanceof ComplexType) {
      double[] complextmp = DenseMemoryManipulation.convertSinglePointerToZeroInterleavedSinglePointer(_data);
      int idx = 2 * (column * _rows + row);
      complextmp[idx] = ((ComplexType) value).getReal();
      complextmp[idx + 1] = ((ComplexType) value).getImag();
      return new OGComplexMatrix(complextmp, _rows, _columns);
    } else {
      _data[column * _rows + row] = value.doubleValue();
      return this;
    }
  }

  public OGMatrix getFullRow(int index) {
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[_columns];
    for (int i = 0; i < _columns; i++) {
      tmp[i] = _data[i * _rows + index];
    }
    return new OGMatrix(tmp, 1, _columns);
  }

  @Override
  public OGMatrix getRow(int index) {
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[_columns];
    for (int i = 0; i < _columns; i++) {
      tmp[i] = _data[i * _rows + index];
    }
    return new OGMatrix(tmp, 1, _columns);
  }

  @Override
  public OGMatrix getRows(int... indexes) {
    Catchers.catchNullFromArgList(indexes, 1);
    final int nindex = indexes.length;
    int index;
    boolean seq = true;
    for (int i = 0; i < nindex; i++) {
      index = indexes[i];
      if (index < 0 || index >= _rows) {
        throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
      }
      if (i > 0) {
        if (indexes[i] != indexes[i - 1] + 1) {
          seq = false;
        }
      }
    }
    double[] tmp = new double[nindex * _columns];
    if (seq) {
      for (int i = 0; i < _columns; i++) {
        System.arraycopy(_data, i * _rows + indexes[0], tmp, i * nindex, nindex);
      }
    } else {
      int in, ir;
      for (int i = 0; i < _columns; i++) {
        in = i * nindex;
        ir = i * _rows;
        for (int j = 0; j < nindex; j++) {
          tmp[in + j] = _data[ir + indexes[j]];
        }
      }
    }
    return new OGMatrix(tmp, nindex, _columns);
  }

  public OGMatrix getFullColumn(int index) {
    if (index < 0 || index >= _columns) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[_rows];
    for (int i = 0; i < _rows; i++) {
      tmp[i] = _data[i + index * _rows];
    }
    return new OGMatrix(tmp, _rows, 1);
  }

  @Override
  public OGArray<? extends Number> getColumn(int index) {
    if (index < 0 || index >= _columns) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[_rows];
    System.arraycopy(_data, index * _rows, tmp, 0, _rows);
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
      if (index < 0 || index >= _columns) {
        throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
      }
      if (i > 0) {
        if (indexes[i] != indexes[i - 1] + 1) {
          seq = false;
        }
      }
    }
    double[] tmp = new double[nindex * _rows];
    if (seq) { // sequential indices requested, do single memcpy
      index = indexes[0];
      System.arraycopy(_data, index * _rows, tmp, 0, nindex * _rows);
    } else {
      for (int i = 0; i < nindex; i++) {
        index = indexes[i];
        System.arraycopy(_data, index * _rows, tmp, i * _rows, _rows);
      }
    }
    return new OGMatrix(tmp, _rows, nindex);
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

    double[] tmp = new double[nrows * ncols];
    int idxi, idxj;
    if (seqCols && seqRows) { // sequential rows, with seq cols: straight memcpy loop
      int offset = columns[0] * _rows + rows[0];
      for (int i = 0; i < ncols; i++) {
        System.arraycopy(_data, offset + i * _rows, tmp, i * nrows, nrows);
      }
    } else {
      if (seqRows) {
        for (int i = 0; i < ncols; i++) { // seq rows, random columns, memcpy loop with dereference to column
          idxi = columns[i];
          System.arraycopy(_data, (idxi * _rows + rows[0]), tmp, i * nrows, nrows);
        }
      } else { // essentially a random intersection
        for (int i = 0; i < ncols; i++) {
          for (int j = 0; j < nrows; j++) {
            idxi = columns[i];
            idxj = rows[j];
            tmp[i * nrows + j] = _data[idxi * _rows + idxj];
          }
        }
      }
    }
    return new OGMatrix(tmp, nrows, ncols);
  }

  public OGArray<? extends Number> get(int[] indicies) {
    Catchers.catchNull(indicies);
    int len = indicies.length;
    int maxIdx = _data.length;
    double[] tmp = new double[len];
    int locidx;
    for (int i = 0; i < len; i++) {
      locidx = indicies[i];
      if (locidx < 0 || locidx > maxIdx) {
        throw new MathsExceptionOutOfBounds("Invalid index :" + indicies[i]);
      } else {
        tmp[i] = _data[locidx];
      }
    }
    return new OGMatrix(tmp, 1, len);
  }

  /**
   * Gets the number of elements in the matrix (full population assumed).
   * @return the number of elements in the matrix
   */
  public int getNumberOfElements() {
    return _data.length;
  }

  /**
   * Returns the backing data
   * @return the backing data
   */
  public double[] getData() {
    return _data;
  }

  /**
   * Decide if this {@link OGMatrix} is equal to another {@link OGMatrix} with the addition of some numerical tolerance for floating point comparison
   * @param obj the {@link OGMatrix} against which a comparison is to be drawn
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
    OGMatrix other = (OGMatrix) obj;
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
  public String toString() {
    String str = "OGMatrix:" + "\ndata = " + Arrays.toString(_data) + "\nrows = " + _rows + "\ncols = " + _columns;
    str = str + "\n====Pretty Print====\n";
    for (int i = 0; i < _rows; i++) {
      for (int j = 0; j < _columns; j++) {
        str += String.format("%24.18f ", _data[j * _rows + i]);
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
    OGMatrix other = (OGMatrix) obj;
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
