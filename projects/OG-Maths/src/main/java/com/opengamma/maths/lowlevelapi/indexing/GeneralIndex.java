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
    _values = Arrays.copyOf(values, values.length);
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

}
