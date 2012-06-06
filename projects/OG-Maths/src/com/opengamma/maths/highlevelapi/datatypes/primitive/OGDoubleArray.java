/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import java.util.Arrays;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionOutOfBounds;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * High level API.
 * Dense matrix backed array magic.
 */
public class OGDoubleArray extends OGArraySuper<Number> {

  private double[] _data;
  private int _columns;
  private int _rows;

  /**
   * Takes a row major java double[][] and turns it into an OGDoubleArray
   * @param dataIn a row major java double[][] 
   */
  public OGDoubleArray(double[][] dataIn) {
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
  public OGDoubleArray(double[] dataIn, int rows, int columns) {
    if (dataIn == null) {
      throw new MathsExceptionIllegalArgument("dataIn is null");
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
  public OGDoubleArray(double number) {
    _columns = 1;
    _rows = 1;
    _data = new double[1];
    _data[0] = number;
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
    if (indices.length > 2) {
      throw new MathsExceptionIllegalArgument("OGDoubleArray only has 2 indicies, more than 2 were given");
    }
    if (indices[0] >= _rows) {
      throw new MathsExceptionOutOfBounds("Row index" + indices[0] + " requested for matrix with only " + _rows + " rows");
    }
    if (indices[1] >= _columns) {
      throw new MathsExceptionOutOfBounds("Columns index" + indices[1] + " requested for matrix with only " + _columns + " columns");
    }
    return _data[indices[0] * _rows + indices[1]];
  }

  public OGDoubleArray getFullRow(int index) {
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[_columns];
    for (int i = 0; i < _columns; i++) {
      tmp[i] = _data[i * _rows + index];
    }
    return new OGDoubleArray(tmp, 1, _columns);
  }

  public OGDoubleArray getFullColumn(int index) {
    if (index < 0 || index >= _columns) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[_rows];
    for (int i = 0; i < _rows; i++) {
      tmp[i] = _data[i + index * _rows];
    }
    return new OGDoubleArray(tmp, _rows, 1);
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

  public OGDoubleArray getRow(int i) {
    if (i < 0) {
      throw new MathsExceptionIllegalArgument("Specified row to get is illegal. Value given was " + i);
    }
    if (i >= _rows) {
      throw new MathsExceptionIllegalArgument("Specified row to get is illegal. Value given was " + i);
    }

    double[] rowData = new double[_columns];
    for (int k = 0; k < _columns; k++) {
      rowData[k] = _data[k * _rows + i];
    }
    return new OGDoubleArray(rowData, 1, _columns);
  }

  @Override
  public String toString() {
    String str = "OGDoubleArray:" +
        "\ndata = " + Arrays.toString(_data) +
        "\nrows = " + _rows +
        "\ncols = " + _columns;
    str = str + "\n====Pretty Print====\n";
    for (int i = 0; i < _rows; i++) {
      for (int j = 0; j < _columns; j++) {
        str += String.format("%8.6f ", _data[j * _rows + i]);
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
    OGDoubleArray other = (OGDoubleArray) obj;
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
