/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

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
