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
import com.opengamma.maths.commonapi.numbers.ComplexType;
import com.opengamma.maths.lowlevelapi.functions.MatrixPrimitiveUtils;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;
import com.opengamma.maths.lowlevelapi.functions.utilities.Max;

/**
 * OGComplex Array type (essentially an interleaved OGDoubleArray, byte aligned contiguous malloc of backing data required and assumed
 */
public class OGComplexMatrix extends OGArray<ComplexType> {

  private double[] _data;
  private int _columns;
  private int _rows;

  /**
   * Takes a row major java double[][] and turns it into an OGComplexArray
   * @param dataIn a row major java double[][] 
   */
  public OGComplexMatrix(double[][] dataIn) {
    Catchers.catchNullFromArgList(dataIn, 1);
    _data = DenseMemoryManipulation.convertRowMajorDoublePointerToColumnMajorZeroInterleavedSinglePointer(dataIn);
    _rows = dataIn.length;
    _columns = dataIn[0].length;
  }

  /**
   * Takes a row major java double[][] and turns it into an OGComplexArray
   * @param realPart a row major java double[][] that is to be the real part of a complex array
   * @param imaginaryPart a row major java double[][] that is to be the imaginary part of a complex array
   */
  public OGComplexMatrix(double[][] realPart, double[][] imaginaryPart) {
    Catchers.catchNullFromArgList(realPart, 1);
    Catchers.catchNullFromArgList(imaginaryPart, 2);
    if (MatrixPrimitiveUtils.isRagged(realPart)) {
      throw new MathsExceptionIllegalArgument("Backing real array is ragged");
    }
    if (MatrixPrimitiveUtils.isRagged(imaginaryPart)) {
      throw new MathsExceptionIllegalArgument("Backing imaginary array is ragged");
    }
    _data = DenseMemoryManipulation.convertTwoRowMajorDoublePointerToColumnMajorInterleavedSinglePointer(realPart, imaginaryPart);
    _rows = realPart.length;
    _columns = realPart[0].length;
  }

  /**
   * Takes a column major double[] and turns it into an OGComplexArray
   * The length of dataIn must be either:
   * <li>rows*columns in which case it is assumed the double[] provided is the real part of the data (imaginary part assumed zero)
   * <li>2*rows*columns in which case it is assumed the double[] provided is formed of interleaved real and imaginary values
   * @param dataIn the real part data backing data
   * @param rows number of rows
   * @param columns number of columns
   */
  public OGComplexMatrix(double[] dataIn, int rows, int columns) {
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
    if (!(len == dataIn.length || 2 * len == dataIn.length)) {
      throw new MathsExceptionIllegalArgument("Number of rows and columns specified does not commute with the quantity of data supplied.\n Rows=" + rows + " Columns=" + columns + " Data length=" +
          dataIn.length);
    }

    _data = new double[2 * rows * columns];
    if (len == dataIn.length) { // constructing from assumed real array
      _data = DenseMemoryManipulation.convertSinglePointerToZeroInterleavedSinglePointer(dataIn);
    } else {
      System.arraycopy(dataIn, 0, _data, 0, 2 * len);
    }
    _rows = rows;
    _columns = columns;
  }

  /**
   * Takes a column major double[] and turns it into an OGComplexArray
   * @param realData the real components of the backing data
   * @param imagData the imaginary components of the backing data 
   * @param rows number of rows
   * @param columns number of columns
   */
  public OGComplexMatrix(double[] realData, double[] imagData, int rows, int columns) {
    Catchers.catchNullFromArgList(realData, 1);
    Catchers.catchNullFromArgList(imagData, 2);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(rows, 3);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(columns, 4);
    final int realDatalen = realData.length;
    final int imagDatalen = imagData.length;
    if (realDatalen != imagDatalen) {
      throw new MathsExceptionIllegalArgument("The lengths of the data provided by realData and imagData are not the same.");
    }
    int len = rows * columns;
    if (realDatalen != len) {
      throw new MathsExceptionIllegalArgument("Number of rows and columns specified does not commute with the quantity of data supplied.\n Rows=" + rows + " Columns=" + columns + " Data length=" +
          realDatalen);
    }
    _data = DenseMemoryManipulation.convertTwoSinglePointersToInterleavedSinglePointer(realData, imagData);
    _rows = rows;
    _columns = columns;
  }

  /**
   * Construct from a row major (m * n)  array of ComplexTypes
   * @param data a non ragged array of ComplexTypes
   */
  public OGComplexMatrix(ComplexType[][] data) {
    Catchers.catchNullFromArgList(data, 1);
    final int rows = data.length;
    // check for nulls now
    for (int i = 0; i < rows; i++) {
      if (data[i] == null) {
        throw new MathsExceptionNullPointer("Row " + i + " in data points to null");
      }
    }

    final int columns = data[0].length;
    _data = new double[2 * rows * columns];
    int count = 0;
    for (int i = 0; i < rows; i++) {
      if (data[i].length != columns) {
        throw new MathsExceptionIllegalArgument("The (m*n) array presented to the constructor must have a consistent row length, row 0 has " + columns + " elements row " + i + " has " +
            data[i].length + " elements");
      }
      for (int j = 0; j < columns; j++) {
        _data[j * 2 * rows + count] = data[i][j].getReal();
        _data[j * 2 * rows + count + 1] = data[i][j].getImag();
      }
      count += 2;
    }
    _rows = rows;
    _columns = columns;
  }

  /**
   * @param number the single real number in this array
   */
  public OGComplexMatrix(double number) {
    _columns = 1;
    _rows = 1;
    _data = new double[2];
    _data[0] = number;
  }

  /**
   * @param number a complex type to enter in this array
   */
  public OGComplexMatrix(ComplexType number) {
    Catchers.catchNullFromArgList(number, 1);
    _columns = 1;
    _rows = 1;
    _data = new double[2];
    _data[0] = number.getReal();
    _data[1] = number.getImag();
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
  public ComplexType getEntry(int... indices) {
    if (indices.length > 2) {
      throw new MathsExceptionIllegalArgument("OGDoubleArray only has 2 indicies, more than 2 were given");
    }
    if (indices[0] >= _rows) {
      throw new MathsExceptionIllegalArgument("Row index" + indices[0] + " requested for matrix with only " + _rows + " rows");
    }
    if (indices[1] >= _columns) {
      throw new MathsExceptionIllegalArgument("Columns index" + indices[1] + " requested for matrix with only " + _columns + " columns");
    }
    final int jmp = 2 * (indices[1] * _rows + indices[0]);
    return new ComplexType(_data[jmp], _data[jmp + 1]);
  }

  public OGComplexMatrix getFullRow(int index) {
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    int count = 0;
    double[] tmp = new double[_columns * 2];
    int jmp;
    for (int i = 0; i < _columns; i++) {
      jmp = 2 * (i * _rows + index);
      tmp[count] = _data[jmp];
      tmp[count + 1] = _data[jmp + 1];
      count += 2;
    }
    return new OGComplexMatrix(tmp, 1, _columns);
  }

  public OGComplexMatrix getFullColumn(int index) {
    if (index < 0 || index >= _columns) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[2 * _rows];
    System.arraycopy(_data, 2 * index * _rows, tmp, 0, tmp.length);
    return new OGComplexMatrix(tmp, _rows, 1);

  }

  @Override
  public OGArray<? extends Number> getColumn(int index) {
    if (index < 0 || index >= _columns) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[2 * _rows];
    System.arraycopy(_data, index * 2 * _rows, tmp, 0, 2 * _rows);
    return new OGComplexMatrix(tmp, _rows, 1);
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
    final int twor = 2 * _rows;
    double[] tmp = new double[2 * nindex * _rows];
    if (seq) { // sequential indices requested, do single memcpy
      index = indexes[0];
      System.arraycopy(_data, index * twor, tmp, 0, nindex * twor);
    } else {
      for (int i = 0; i < nindex; i++) {
        index = indexes[i];
        System.arraycopy(_data, index * twor, tmp, i * twor, twor);
      }
    }
    return new OGComplexMatrix(tmp, _rows, nindex);
  }

  public OGComplexMatrix getRow(int index) {
    if (index < 0 || index >= _rows) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    int count = 0;
    double[] tmp = new double[_columns * 2];
    int jmp;
    for (int i = 0; i < _columns; i++) {
      jmp = 2 * (i * _rows + index);
      tmp[count] = _data[jmp];
      tmp[count + 1] = _data[jmp + 1];
      count += 2;
    }
    return new OGComplexMatrix(tmp, 1, _columns);
  }

  @Override
  public OGComplexMatrix getRows(int... indexes) {
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
    double[] tmp = new double[2 * nindex * _columns];
    if (seq) {
      int in = 2 * nindex;
      for (int i = 0; i < _columns; i++) {
        System.arraycopy(_data, 2 * (i * _rows + indexes[0]), tmp, i * in, in);
      }
    } else {
      int in, ir, twoi, twojin, twoidxj;
      for (int i = 0; i < _columns; i++) {
        twoi = 2 * i;
        in = twoi * nindex;
        ir = twoi * _rows;
        for (int j = 0; j < nindex; j++) {
          twojin = 2 * j + in;
          twoidxj = ir + 2 * indexes[j];
          tmp[twojin] = _data[twoidxj];
          tmp[twojin + 1] = _data[twoidxj + 1];
        }
      }
    }
    return new OGComplexMatrix(tmp, nindex, _columns);
  }

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

    double[] tmp = new double[nrows * ncols * 2];
    int idxi, idxj;
    if (seqCols && seqRows) { // sequential rows, with seq cols: straight memcpy loop
      int offset = 2 * (columns[0] * _rows + rows[0]);
      for (int i = 0; i < ncols; i++) {
        System.arraycopy(_data, offset + 2 * i * _rows, tmp, 2 * i * nrows, 2 * nrows);
      }
    } else {
      if (seqRows) {
        for (int i = 0; i < ncols; i++) { // seq rows, random columns, memcpy loop with dereference to column
          idxi = columns[i];
          System.arraycopy(_data, 2 * (idxi * _rows + rows[0]), tmp, 2 * i * nrows, 2 * nrows);
        }
      } else { // essentially a random intersection
        for (int i = 0; i < ncols; i++) {
          for (int j = 0; j < nrows; j++) {
            idxi = columns[i];
            idxj = rows[j];
            tmp[2 * (i * nrows + j)] = _data[2 * (idxi * _rows + idxj)];
            tmp[2 * (i * nrows + j) + 1] = _data[2 * (idxi * _rows + idxj) + 1];
          }
        }
      }
    }
    return new OGComplexMatrix(tmp, nrows, ncols);
  }

  /**
   * Gets the number of elements in the matrix (full population assumed).
   * @return the number of elements in the matrix
   */
  public int getNumberOfElements() {
    return _rows * _columns;
  }

  /**
   * Returns the backing data
   * @return the backing data
   */
  public double[] getData() {
    return _data;
  }
  
  @Override
  public OGArray<? extends Number> get(int[] linear) {
    Catchers.catchNull(linear);
    int max = Max.value(linear);
    double[] tmp;
    int len;
    if (max >= _rows * _columns) {
      throw new MathsExceptionOutOfBounds("Index requested exceeds data length.");
    } else {
      len = linear.length;
      tmp = new double[2 * len];
      int ptr = 0;
      int twoIdx;
      for (int i = 0; i < len; i++) {
        twoIdx = 2 * linear[i];
        tmp[ptr] = _data[twoIdx];
        tmp[ptr + 1] = _data[twoIdx + 1];
        ptr += 2;
      }
    }
    return new OGComplexMatrix(tmp, 1, len);
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
    OGComplexMatrix other = (OGComplexMatrix) obj;
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
    String str = "\nOGComplexArray:" + "\ndata = " + Arrays.toString(_data) + "\nrows = " + _rows + "\ncols = " + _columns;
    str = str + "\n====Pretty Print====\n";
    double imag;
    for (int i = 0; i < 2 * _rows; i += 2) {
      for (int j = 0; j < _columns - 1; j++) {
        imag = _data[j * 2 * _rows + i + 1];
        str += String.format("%24.18f " + (imag >= 0 ? "+" : "-") + "%24.18fi, ", _data[j * 2 * _rows + i], Math.abs(imag));
      }
      imag = _data[(_columns - 1) * 2 * _rows + i + 1];
      str += String.format("%24.18f " + (imag >= 0 ? "+" : "-") + "%24.18fi, ", _data[(_columns - 1) * 2 * _rows + i], Math.abs(imag));
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
    OGComplexMatrix other = (OGComplexMatrix) obj;
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
