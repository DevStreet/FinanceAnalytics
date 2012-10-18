/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import java.util.Arrays;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Contains information about permutation patterns in a 2D matrix
 * Underneath it's essentially a vector
 */
public class OGPermutationArray extends OGArraySuper<Number> {
  private int[] _data;
  private int _rows;
  private int _columns;

  /**
   * Construct from a row wise permutation index "p" such that "p*A" will permute A row wise
   * @param permute the indices describing the permutation  
   */
  public OGPermutationArray(int[] permute) {
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
  public int[] getData() {
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
    OGPermutationArray other = (OGPermutationArray) obj;
    if (_columns != other._columns) { // rows and cols are the same
      return false;
    }
    if (!Arrays.equals(_data, other._data)) {
      return false;
    }
    return true;
  }

}
