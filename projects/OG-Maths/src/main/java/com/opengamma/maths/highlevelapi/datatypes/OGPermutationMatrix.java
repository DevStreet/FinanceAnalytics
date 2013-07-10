/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes;

import java.util.Arrays;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Contains information about permutation patterns in a 2D matrix
 * Underneath it's essentially a vector
 */
public class OGPermutationMatrix extends OGArray<Integer> {
  private int[] _data;
  private int _rows;
  private int _columns;

  /**
   * Construct from a row wise permutation index "p" such that "p*A" will permute A row wise
   * @param permute the indices describing the permutation  
   */
  public OGPermutationMatrix(int[] permute) {
    Catchers.catchNullFromArgList(permute, 1);
    final int len = permute.length;
    byte[] tmp = new byte[len];

    // check the permutation is sane
    for (int i = 0; i < len; i++) {
      if (permute[i] >= len) {
        throw new MathsExceptionIllegalArgument("The vector permute contains references to permutation indicies out of the dimension of the vector, cannot permute element " + permute[i] +
            " of an array of length " + len + ".");
      }
      if (permute[i] < 0) {
        throw new MathsExceptionIllegalArgument("The vector permute contains negative permutation indicies.");
      }
      if (tmp[permute[i]] == 1) {
        throw new MathsExceptionIllegalArgument("The vector permute contains repeated permutation indicies and shouldn't!");
      } else {
        tmp[permute[i]] = 1;
      }
    }

    _data = new int[len];
    System.arraycopy(permute, 0, _data, 0, len);
    _rows = len;
    _columns = len;
  }

  /**
   * Gets the data.
   * @return the data
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

  @Override
  public int getNumberOfRows() {
    return _rows;
  }

  @Override
  public int getNumberOfColumns() {
    return _columns;
  }

  @Override
  public Integer getEntry(int... indices) {
    if (indices.length > 2) {
      throw new MathsExceptionIllegalArgument("OGPermutationArray only has 2 indicies, more than 2 were given");
    }
    if (indices[0] >= _rows) {
      throw new MathsExceptionIllegalArgument("Row index" + indices[0] + " requested for matrix with only " + _rows + " rows");
    }
    if (indices[1] >= _columns) {
      throw new MathsExceptionIllegalArgument("Columns index" + indices[1] + " requested for matrix with only " + _columns + " columns");
    }
    int ret = 0; // default entry is 0
    if (_data[indices[0]] == indices[1]) {
      ret = 1;
    }
    return ret;
  }

  @Override
  public OGArray<? extends Number> getColumn(int index) {
    if (index < 0 || index >= _columns) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    double[] tmp = new double[_rows];
    for (int k = 0; k < _rows; k++) {
      if (_data[k] == index) {
        tmp[k] = 1;
        break;
      }
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
      ir = i * _rows;
      for (int k = 0; k < _rows; k++) {
        if (_data[k] == idx) {
          tmp[ir + k] = 1;
          break;
        }
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
    tmp[index] = _data[index];
    return new OGMatrix(tmp, 1, _columns);
  }

  @Override
  public OGArray<? extends Number> getRows(int... indexes) {
    Catchers.catchNullFromArgList(indexes, 1);
    final int nindex = indexes.length;
    int index;
    for (int i = 0; i < nindex; i++) {
      index = indexes[i];
      if (index < 0 || index >= _rows) {
        throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
      }
    }
    double[] tmp = new double[nindex * _columns];
    int idx;
    for (int i = 0; i < nindex; i++) {
      idx = indexes[i];
      tmp[i + _data[idx] * nindex] = 1;
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
    for (int i = 0; i < nrows; i++) {
      index = rows[i];
      if (index < 0 || index >= _rows) {
        throw new MathsExceptionIllegalArgument("Invalid row index. Value given was " + index);
      }
    }
    for (int i = 0; i < ncols; i++) {
      index = columns[i];
      if (index < 0 || index >= _columns) {
        throw new MathsExceptionIllegalArgument("Invalid column index. Value given was " + index);
      }
    }
    //TODO: at some point optimise this, need to get on with something else ATM
    double[] tmp = new double[_rows * _columns];
    for (int i = 0; i < _rows; i++) {
      for (int j = 0; j < _columns; j++) {
        if (j == _data[i]) {
          tmp[i * _rows + j] = 1;
        }
      }
    }
    return new OGMatrix(tmp, _rows, _columns).get(rows, columns);
  }

  @Override
  public OGArray<? extends Number> setEntry(int rowidx, int colidx, Number num) {
    throw new MathsExceptionNotImplemented("setEntry not implemented for OGPermutationMatrix");
  }
  
  @Override
  public String toString() {
    String str = "OGPermutationArray:" + "\ndata = " + Arrays.toString(_data) + "\nrows = " + _rows + "\ncols = " + _columns;
    str = str + "\n====Pretty Print====\n";
    String zeroStr = String.format("%2d ", 0);
    String oneStr = String.format("%2d ", 1);

    for (int i = 0; i < _rows; i++) {
      for (int j = 0; j < _columns; j++) {
        if (j == _data[i]) {
          str += oneStr;
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

  /**
   * Decide if this {@link OGPermutationMatrix} is equal to another {@link OGPermutationMatrix} with the addition of some numerical tolerance for floating point comparison
   * @param obj the {@link OGPermutationMatrix} against which a comparison is to be drawn
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
    OGPermutationMatrix other = (OGPermutationMatrix) obj;
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
    OGPermutationMatrix other = (OGPermutationMatrix) obj;
    if (_columns != other._columns) { // rows and cols are the same
      return false;
    }
    if (!Arrays.equals(_data, other._data)) {
      return false;
    }
    return true;
  }


}
