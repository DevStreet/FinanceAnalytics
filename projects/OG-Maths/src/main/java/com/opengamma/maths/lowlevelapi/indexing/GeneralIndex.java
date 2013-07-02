/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import java.util.Arrays;

import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Generalised index 
 */
public class GeneralIndex implements IndexItem {

  private int[] _values;

  public GeneralIndex(int[] values) {
    Catchers.catchNull(values);
    for (int i = 0; i < values.length; i++) {
      Catchers.catchCondition(values[i] < 0, "Indicies must be >= 0");
    }
    _values = Arrays.copyOf(values, values.length);
  }

  public GeneralIndex(int value) {
    _values = new int[] {value };
  }

  /**
   * Gets the values.
   * @return the values
   */
  public final int[] getValues() {
    return _values;
  }

  @Override
  public String toString() {
    return "GeneralIndex [_values=" + Arrays.toString(_values) + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(_values);
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
    GeneralIndex other = (GeneralIndex) obj;
    if (!Arrays.equals(_values, other._values)) {
      return false;
    }
    return true;
  }

  @Override
  public int[] linearise(int leadingDimension) {
    return _values;
  }

}
