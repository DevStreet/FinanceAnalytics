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
    if (indices.length > 2) {
      throw new MathsExceptionIllegalArgument("OGRealScalar only has 2 indicies, more than 2 were given");
    }
    if (indices[0] != 0) {
      throw new MathsExceptionIllegalArgument("Row index" + indices[0] + " requested for matrix with only " + 1 + " rows");
    }
    if (indices[1] != 0) {
      throw new MathsExceptionIllegalArgument("Columns index" + indices[1] + " requested for matrix with only " + 1 + " columns");
    }
    return _data[0];
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
