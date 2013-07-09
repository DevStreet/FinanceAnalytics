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

/**
 * Scalar real number
 */
public class OGRealScalar extends OGArray<Number> {

  private double[] _data = new double[1];

  /**
   * @param n a number
   */
  public OGRealScalar(Number n) {
    Catchers.catchNullFromArgList(n, 1);
    try {
      _data[0] = n.doubleValue();
    } catch (Exception e) {
      throw new MathsExceptionIllegalArgument("Cannot construct real scalar from type that has no doublevalue");
    }
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
  public Number getEntry(int... indices) {
    super.entryValidator(indices);
    return _data[0];
  }

  @Override
  public OGArray<? extends Number> setEntry(int row, int column, Number value) {
    Catchers.catchNull(value);
    super.entryValidator(row, column);
    if (value instanceof ComplexType) {
      return new OGComplexScalar(((ComplexType) value).getReal(), ((ComplexType) value).getImag());
    } else {
      _data[0] = value.doubleValue();
      return this;
    }
  }

  @Override
  public OGArray<? extends Number> getColumn(int index) {
    if (index != 0) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    return new OGRealScalar(_data[0]);
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
    double[] tmp = new double[nindex];
    Arrays.fill(tmp, _data[0]);
    return new OGMatrix(tmp, 1, nindex);
  }

  @Override
  public OGArray<? extends Number> getRow(int index) {
    if (index != 0) {
      throw new MathsExceptionIllegalArgument("Invalid index. Value given was " + index);
    }
    return new OGRealScalar(_data[0]);
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
    double[] tmp = new double[nindex];
    Arrays.fill(tmp, _data[0]);
    return new OGMatrix(tmp, nindex, 1);
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

    double[] tmp = new double[nrows * ncols];
    Arrays.fill(tmp, _data[0]);
    if (nrows > 1 || ncols > 1) {
      return new OGMatrix(tmp, nrows, ncols);
    } else {
      return new OGRealScalar(tmp[0]);
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
   * Decide if this {@link OGRealScalar} is equal to another {@link OGRealScalar} with the addition of some numerical tolerance for floating point comparison
   * @param obj the {@link OGRealScalar} against which a comparison is to be drawn
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
    OGRealScalar other = (OGRealScalar) obj;
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
    OGRealScalar other = (OGRealScalar) obj;
    if (!Arrays.equals(_data, other._data)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    String str = "\nOGRealScalar: value = " + String.format("%24.18f\n", _data[0]);
    return str;
  }

}
