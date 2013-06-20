/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

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
    if (offset > 0) {
      throw new RuntimeException("Invalid offset. End() can only have negative offsets, i.e. arrays may not grow.");
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




}
