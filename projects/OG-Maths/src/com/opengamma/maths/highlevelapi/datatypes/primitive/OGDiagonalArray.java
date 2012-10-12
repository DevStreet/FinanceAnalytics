/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import java.util.Arrays;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionOutOfBounds;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Holds a "diagonal" array
 */
public class OGDiagonalArray extends OGArraySuper<Number> {

  private double[] _data;
  private int _columns;
  private int _rows;

  /**
   * Places the elements of an array "diag" of length "n" on the diagonal of a matrix of dimension [n * n]
   * @param diag the elements to be placed on the diagonal
   */
  public OGDiagonalArray(double[] diag) {
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
  public OGDiagonalArray(double[] diag, int rows, int columns) {
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
  public OGDiagonalArray(double diag, int rows, int columns) {
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
  public OGDiagonalArray(double diag) {
    this(diag, 1, 1);
  }

  /**
   * Places the "diag" as the first element on the diagonal of a matrix of dimension [1 * 1]
   * @param diag the element to be placed on in the first entry of the diagonal
   */
  public OGDiagonalArray(int diag) {
    this((double) diag, 1, 1);
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
   * Decide if this {@link OGDiagonalArray} is equal to another {@link OGDiagonalArray} with the addition of some numerical tolerance for floating point comparison
   * @param obj the {@link OGDiagonalArray} against which a comparison is to be drawn
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
    OGDiagonalArray other = (OGDiagonalArray) obj;
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
  public Double getEntry(int... indices) {
    if (indices.length > 2) {
      throw new MathsExceptionIllegalArgument("OGDoubleArray only has 2 indicies, more than 2 were given");
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
    OGDiagonalArray other = (OGDiagonalArray) obj;
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
