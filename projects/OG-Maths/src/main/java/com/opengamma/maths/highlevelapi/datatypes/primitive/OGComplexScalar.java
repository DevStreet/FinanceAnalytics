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
 * Scalar real number
 */
public class OGComplexScalar extends OGArray<Number> {

  private double[] _data = new double[2];

  /**
   * @param n a number
   */
  public OGComplexScalar(Number n) {
    Catchers.catchNullFromArgList(n, 1);
    // this is a touch horrible, should probably revisit complex types and try and work out a better way of handling whilst
    // keeping in mind the native code data layouts, std::complex and _Complex etc. 
    if (n.getClass() == ComplexType.class) {
      ComplexType tmp = (ComplexType) n;
      _data[0] = tmp.getReal();
      _data[1] = tmp.getImag();
    } else {
      try {
        _data[0] = n.doubleValue();
      } catch (Exception e) {
        throw new MathsExceptionIllegalArgument("Cannot construct complex scalar from type that has no doublevalue");
      }
    }
  }

  public OGComplexScalar(double real, double imag) {
    _data[0] = real;
    _data[1] = imag;
  }

  /**
   * Returns the backing data
   * @return the backing data
   */
  public double[] getData() {
    return _data;
  }

  @Override
  public int getNumberOfRows() {
    return 1;
  }

  @Override
  public int getNumberOfColumns() {
    return 1;
  }

  @Override
  public OGArray<? extends Number> getColumn(int index) {
    if (index != 0) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    return new OGComplexScalar(_data[0], _data[1]);
  }

  @Override
  public OGArray<? extends Number> getColumns(int... indexes) {
    Catchers.catchNullFromArgList(indexes, 1);
    final int nindex = indexes.length;
    int index;
    for (int i = 0; i < nindex; i++) {
      index = indexes[i];
      if (index != 0) {
        throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
      }
    }
    if (nindex == 1) {
      return getColumn(0);
    }
    double[] tmp = new double[2 * nindex];
    DenseMemoryManipulation.fillArrayWithInterleavedComplexValue(tmp, _data);
    return new OGComplexMatrix(tmp, 1, nindex);
  }

  @Override
  public OGArray<? extends Number> getRow(int index) {
    if (index != 0) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    return new OGComplexScalar(_data[0], _data[1]);
  }

  @Override
  public OGArray<? extends Number> getRows(int... indexes) {
    Catchers.catchNullFromArgList(indexes, 1);
    final int nindex = indexes.length;
    int index;
    for (int i = 0; i < nindex; i++) {
      index = indexes[i];
      if (index != 0) {
        throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
      }
    }
    if (nindex == 1) {
      return getRow(0);
    }
    double[] tmp = new double[2 * nindex];
    DenseMemoryManipulation.fillArrayWithInterleavedComplexValue(tmp, _data);
    return new OGComplexMatrix(tmp, nindex, 1);
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
      if (index != 0) {
        throw new MathsExceptionIllegalArgument("Invalid row index. Value given was " + index);
      }
    }
    for (int i = 0; i < ncols; i++) {
      index = columns[i];
      if (index != 0) {
        throw new MathsExceptionIllegalArgument("Invalid column index. Value given was " + index);
      }
    }

    double[] tmp = new double[nrows * ncols * 2];
    DenseMemoryManipulation.fillArrayWithInterleavedComplexValue(tmp, _data);
    if (nrows > 1 || ncols > 1) {
      return new OGComplexMatrix(tmp, nrows, ncols);
    } else {
      return new OGComplexScalar(tmp[0], tmp[1]);
    }
  }

  @Override
  public ComplexType getEntry(int... indices) {
    super.entryValidator(indices);
    return new ComplexType(_data);
  }

  @Override
  public OGArray<? extends Number> setEntry(int row, int column, Number value) {
    Catchers.catchNull(value);
    super.entryValidator(row, column);
    if (value instanceof ComplexType) {
      _data[0] = ((ComplexType) value).getReal();
      _data[1] = ((ComplexType) value).getImag();
      return this;
    } else {
      return new OGRealScalar(value.doubleValue());
    }

  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(_data);
    return result;
  }

  /**
   * Decide if this {@link OGComplexScalar} is equal to another {@link OGComplexScalar} with the addition of some numerical tolerance for floating point comparison
   * @param obj the {@link OGComplexScalar} against which a comparison is to be drawn
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
    OGComplexScalar other = (OGComplexScalar) obj;
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
    OGComplexScalar other = (OGComplexScalar) obj;
    if (!Arrays.equals(_data, other._data)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    String str = "\nOGComplexScalar: value = " + String.format("%24.18f " + (_data[1] >= 0 ? "+" : "-") + "%24.18fi, ", _data[0], Math.abs(_data[1]));
    return str;
  }

}
