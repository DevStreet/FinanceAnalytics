/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import java.util.Arrays;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.lowlevelapi.datatypes.primitive.MatrixPrimitiveUtils;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * The OGIndex class provides access to the typically understood notion of a matrix, i.e. A Fully populated array.
 */
public class OGIndexArray extends OGArraySuper<Integer> {
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
  public OGIndexArray(int[][] aMatrix) {
    Catchers.catchNullFromArgList(aMatrix, 1);
    _data = DenseMemoryManipulation.convertRowMajorIntPointerToColumnMajorSinglePointer(aMatrix);
    _rows = aMatrix.length;
    _columns = aMatrix[0].length;
  }

  /**
   * @param number the single number in this array
   */
  public OGIndexArray(int number) {
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
  public OGIndexArray(int[] dataIn, int rows, int columns) {
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
    if (indices.length > 2) {
      throw new MathsExceptionIllegalArgument("OGDoubleArray only has 2 indicies, more than 2 were given");
    }
    if (indices[0] >= _rows) {
      throw new MathsExceptionIllegalArgument("Row index" + indices[0] + " requested for matrix with only " + _rows + " rows");
    }
    if (indices[1] >= _columns) {
      throw new MathsExceptionIllegalArgument("Columns index" + indices[1] + " requested for matrix with only " + _columns + " columns");
    }
    return _data[indices[1] * _rows + indices[0]];
  }

  public OGIndexArray getFullRow(int index) {
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    int[] tmp = new int[_columns];
    for (int i = 0; i < _columns; i++) {
      tmp[i] = _data[i * _rows + index];
    }
    return new OGIndexArray(tmp, 1, _columns);
  }

  public OGIndexArray getFullColumn(int index) {
    if (index < 0 || index >= _columns) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    int[] tmp = new int[_rows];
    for (int i = 0; i < _rows; i++) {
      tmp[i] = _data[i + index * _rows];
    }
    return new OGIndexArray(tmp, _rows, 1);
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
   * @return _data the OGIndex data in it's native storage format
   */
  public int[] getData() {
    return Arrays.copyOf(_data, _data.length);
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
    OGIndexArray other = (OGIndexArray) obj;
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
