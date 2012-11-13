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
  public ComplexType getEntry(int... indices) {
    if (indices.length > 2) {
      throw new MathsExceptionIllegalArgument("OGComplexScalar only has 2 indicies, more than 2 were given");
    }
    if (indices[0] != 0) {
      throw new MathsExceptionIllegalArgument("Row index" + indices[0] + " requested for matrix with only " + 1 + " rows");
    }
    if (indices[1] != 0) {
      throw new MathsExceptionIllegalArgument("Columns index" + indices[1] + " requested for matrix with only " + 1 + " columns");
    }
    return new ComplexType(_data);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(_data);
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
