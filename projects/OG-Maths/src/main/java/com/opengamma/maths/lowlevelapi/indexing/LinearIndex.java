/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * Linear index representation
 */
public class LinearIndex implements IndexItem {

  private int _low, _step, _high;

  /**
   * Linear index from low:high inclusive at a given step
   * @param low low value
   * @param step the step
   * @param high high value
   */
  public LinearIndex(int low, int step, int high) {
    checkRangeIsValid(low, step, high);
    _low = low;
    _step = step;
    _high = high;
  }

  /**
   * Linear index from low:high inclusive
   * @param low low value
   * @param high high value
   */
  public LinearIndex(int low, int high) {
    this(low, 1, high);
  }

  /**
   * Gets the low bound.
   * @return the low bound
   */
  public final int getLow() {
    return _low;
  }

  /**
   * Gets the step.
   * @return the step
   */
  public final int getStep() {
    return _step;
  }

  /**
   * Gets the high bound.
   * @return the high bound
   */
  public final int getHigh() {
    return _high;
  }

  /**
   * Expands the index to a general index
   * @return a general index representation of the vector
   */
  public GeneralIndex expand() {
    int start = _high > _low ? _low : _high;
    int end = _high < _low ? _low : _high;
    int count = 1 + ((end - start) / Math.abs(_step));
    int[] vals = new int[count];
    int v = _low;
    for (int i = 0; i < count; i++) {
      vals[i] = v;
      v += _step;
    }
    return new GeneralIndex(vals);
  }

  @Override
  public String toString() {
    return "LinearIndex [_low=" + _low + ", _step=" + _step + ", _high=" + _high + "]";
  }

  private void checkRangeIsValid(int a, int b, int c) {
    if (a < 0 || c < 0) {
      throw new MathsExceptionIllegalArgument("Indices cannot be negative");
    }
    if (a < c && b < 1) {
      throw new MathsExceptionIllegalArgument("Index representation is empty. Tried to construct index as: \"" + a + ":" + b + ":" + c + "\"");
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _high;
    result = prime * result + _low;
    result = prime * result + _step;
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
    LinearIndex other = (LinearIndex) obj;
    if (_high != other._high) {
      return false;
    }
    if (_low != other._low) {
      return false;
    }
    if (_step != other._step) {
      return false;
    }
    return true;
  }

  @Override
  public int[] linearise(int leadingDimension) {
    return this.expand().getValues();
  }

}
