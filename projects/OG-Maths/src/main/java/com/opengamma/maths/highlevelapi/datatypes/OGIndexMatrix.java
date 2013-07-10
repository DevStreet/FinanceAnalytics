/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes;

import java.util.Arrays;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * The OGIndex class provides access to the typically understood notion of a matrix, i.e. A Fully populated array.
 */
public class OGIndexMatrix extends OGArray<Integer> {
  private int[] _data;
  private int _rows;
  private int _columns;

  /**
   * Constructors
   */
  // construct from array
  /**
   * Constructs from an array of arrays representation
   * @param aMatrix is an n columns x m rows matrix stored as a row major array of arrays
   */
  public OGIndexMatrix(int[][] aMatrix) {
    Catchers.catchNullFromArgList(aMatrix, 1);
    _data = DenseMemoryManipulation.convertRowMajorIntPointerToColumnMajorSinglePointer(aMatrix);
    _rows = aMatrix.length;
    _columns = aMatrix[0].length;
  }

  /**
   * @param number the single number in this array
   */
  public OGIndexMatrix(int number) {
    _columns = 1;
    _rows = 1;
    _data = new int[1];
    _data[0] = number;
  }

  /**
   * Takes a column major int[] and turns it into an OGIndexArray
   * @param dataIn the backing data
   * @param rows number of rows
   * @param columns number of columns
   */
  public OGIndexMatrix(int[] dataIn, int rows, int columns) {
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
    _data = new int[len];
    System.arraycopy(dataIn, 0, _data, 0, len);
    _rows = rows;
    _columns = columns;
  }

  /*
   * Methods
   */
  /**
   * Gets the number of elements in the matrix (full population assumed).
   * @return the number of elements in the matrix
   */
  public int getNumberOfElements() {
    return _data.length;
  }

  /**
   * @param indices The index of the entry within the matrix to be returned.
   * If a single index is given, it assumes ind2sub behaviour (index = i*rows+j) and returns that index
   * If a pair of indices are given, it assumes standard lookup behaviour and returns the index at the given matrix "coordinate".
   * @return the entry at index specified
   *    */
  @Override
  public Integer getEntry(int... indices) {
    if (indices.length != 2) {
      throw new MathsExceptionIllegalArgument("OGIndexArray only has 2 indicies, more than 2 were given");
    }
    if (indices[0] >= _rows) {
      throw new MathsExceptionIllegalArgument("Row index" + indices[0] + " requested for matrix with only " + _rows + " rows");
    }
    if (indices[1] >= _columns) {
      throw new MathsExceptionIllegalArgument("Columns index" + indices[1] + " requested for matrix with only " + _columns + " columns");
    }
    return _data[indices[1] * _rows + indices[0]];
  }

  @Override
  public OGArray<? extends Number> setEntry(int rowidx, int colidx, Number num) {
    throw new MathsExceptionNotImplemented("setEntry not implemented for OGIndexMatrix");
  }

  public OGIndexMatrix getFullRow(int index) {
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    int[] tmp = new int[_columns];
    for (int i = 0; i < _columns; i++) {
      tmp[i] = _data[i * _rows + index];
    }
    return new OGIndexMatrix(tmp, 1, _columns);
  }

  @Override
  public OGIndexMatrix getRow(int index) {
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    int[] tmp = new int[_columns];
    for (int i = 0; i < _columns; i++) {
      tmp[i] = _data[i * _rows + index];
    }
    return new OGIndexMatrix(tmp, 1, _columns);
  }

  @Override
  public OGIndexMatrix getRows(int... indexes) {
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
    int[] tmp = new int[nindex * _columns];
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
    return new OGIndexMatrix(tmp, nindex, _columns);
  }

  public OGIndexMatrix getFullColumn(int index) {
    if (index < 0 || index >= _columns) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    int[] tmp = new int[_rows];
    for (int i = 0; i < _rows; i++) {
      tmp[i] = _data[i + index * _rows];
    }
    return new OGIndexMatrix(tmp, _rows, 1);
  }

  @Override
  public OGArray<? extends Number> getColumn(int index) {
    if (index < 0 || index >= _columns) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    int[] tmp = new int[_rows];
    System.arraycopy(_data, index * _rows, tmp, 0, _rows);
    return new OGIndexMatrix(tmp, _rows, 1);
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
    int[] tmp = new int[nindex * _rows];
    if (seq) { // sequential indices requested, do single memcpy
      index = indexes[0];
      System.arraycopy(_data, index * _rows, tmp, 0, nindex * _rows);
    } else {
      for (int i = 0; i < nindex; i++) {
        index = indexes[i];
        System.arraycopy(_data, index * _rows, tmp, i * _rows, _rows);
      }
    }
    return new OGIndexMatrix(tmp, _rows, nindex);
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

    int[] tmp = new int[nrows * ncols];
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
    return new OGIndexMatrix(tmp, nrows, ncols);
  }

  /**
   * Gets the number of rows
   * @return _rows the number of rows
   */
  public int getNumberOfRows() {
    return _rows;
  }

  /**
   * Gets the number of columns
   * @return _cols the number of columns
   */
  public int getNumberOfColumns() {
    return _columns;
  }

  /**
   * Gets the data
   * @return _data the OGIndex data as double[]
   */
  public double[] getData() {
    double[] tmp = new double[_data.length];
    for (int i = 0; i < _data.length; i++) {
      tmp[i] = _data[i];
    }
    return tmp;
  }

  /**
   * Get as int data
   * @return the data as int[]
   */
  public int[] getIntData() {
    return _data;
  }

  /**
   * ToString for pretty printing
   * @return A string representation of the matrix
   */
  @Override
  public String toString() {
    String str = "OGIndexArray:" + "\ndata = " + Arrays.toString(_data) + "\nrows = " + _rows + "\ncols = " + _columns;
    str = str + "\n====Pretty Print====\n";
    for (int i = 0; i < _rows; i++) {
      for (int j = 0; j < _columns; j++) {
        str += String.format("%24d ", _data[j * _rows + i]);
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

  /**
   * Decide if this {@link OGIndexMatrix} is equal to another {@link OGIndexMatrix} with the addition of some numerical tolerance for floating point comparison
   * @param obj the {@link OGIndexMatrix} against which a comparison is to be drawn
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
    OGIndexMatrix other = (OGIndexMatrix) obj;
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
    OGIndexMatrix other = (OGIndexMatrix) obj;
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
