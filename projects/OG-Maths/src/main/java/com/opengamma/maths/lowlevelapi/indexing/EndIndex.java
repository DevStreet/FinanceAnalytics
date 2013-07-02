/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * holds an "end - foo" statement
 */
public class EndIndex implements IndexItem {

  private int _offset;
  private int _start;
  private int _step;

  public EndIndex(int start) {
    this(start, 1, 0);
  }

  public EndIndex(int start, int step) {
    this(start, step, 0);
  }

  public EndIndex(int start, int step, int offset) {
    if (start < 0) {
      throw new MathsExceptionIllegalArgument("Invalid start. Start must be >=0.");
    }
    if (step < 1) {
      throw new MathsExceptionIllegalArgument("Invalid step. Step must be >=1.");
    }
    if (offset > 0) {
      throw new MathsExceptionIllegalArgument("Invalid offset. End() can only have negative offsets, i.e. arrays may not grow.");
    }
    _offset = (offset);
    _step = step;
    _start = start;
  }

  /**
   * Gets the start.
   * @return the start
   */
  public final int getStart() {
    return _start;
  }

  /**
   * Gets the step.
   * @return the step
   */
  public final int getStep() {
    return _step;
  }

  /**
   * Gets the offset.
   * @return the offset
   */
  public int getOffset() {
    return _offset;
  }

  @Override
  public String toString() {
    return "EndIndex [_offset=" + _offset + ", _start=" + _start + ", _step=" + _step + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + _offset;
    result = prime * result + _start;
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
    EndIndex other = (EndIndex) obj;
    if (_start != other._start) {
      return false;
    }
    if (_step != other._step) {
      return false;
    }
    if (_offset != other._offset) {
      return false;
    }
    return true;
  }

  @Override
  public int[] linearise(int leadingDimension) {
    return new LinearIndex(_start, _step, leadingDimension + _offset).expand().getValues();
  }

}
