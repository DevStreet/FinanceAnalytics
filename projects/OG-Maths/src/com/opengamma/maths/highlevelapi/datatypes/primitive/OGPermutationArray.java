/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

/**
 * 
 */
public class OGPermutationArray extends OGArraySuper<Integer> {
  private int[] _data;
  private int _rows;
  private int _columns;
  
  @Override
  public int getNumberOfRows() {
    return 0;
  }

  @Override
  public int getNumberOfColumns() {
    return 0;
  }

  @Override
  public Integer getEntry(int... indices) {
    return null;
  }

  /**
   * Gets the data.
   * @return the data
   */
  public int[] getData() {
    return _data;
  }

  /**
   * Gets the rows.
   * @return the rows
   */
  public int getRows() {
    return _rows;
  }

  /**
   * Gets the columns.
   * @return the columns
   */
  public int getColumns() {
    return _columns;
  }

}
