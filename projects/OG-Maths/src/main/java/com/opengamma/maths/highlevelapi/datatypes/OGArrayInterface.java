/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes;

import com.opengamma.maths.lowlevelapi.indexing.OneDIndex;
import com.opengamma.maths.lowlevelapi.indexing.TwoDIndex;

/**
 * 
 * @param <T> a Number type
 */
public interface OGArrayInterface<T extends Number> {

  int getNumberOfRows();

  int getNumberOfColumns();

  T getEntry(int... indices);

  OGArray<? extends Number> setEntry(int rowidx, int colidx, Number num);

  double[] getData();

  /**
   * See if obj equals another object, test is performed in a numerically fuzzy sense with tolerance "tolerance"
   * @param obj the object to test
   * @param tolerance the tolerance with which to perform the test
   * @return whether the two obj is equal to this in a fuzzy sense
   */
  boolean fuzzyequals(Object obj, double tolerance);

  /**
   * Gets a column of the matrix
   * @param index the column to get
   * @return the column
   */
  OGArray<? extends Number> getColumn(int index);

  /**
   * Gets specified columns of the matrix
   * @param index the columns to get
   * @return the columns
   */
  OGArray<? extends Number> getColumns(int... index);

  /**
   * Gets a row of the matrix
   * @param index the row to get
   * @return the row
   */
  OGArray<? extends Number> getRow(int index);

  /**
   * Gets specified rows of the matrix
   * @param index the rows to get
   * @return the rows
   */
  OGArray<? extends Number> getRows(int... index);

  /**
   * Gets a matrix formed from the intersection of data "rows" and "columns"
   * @param rows the rows to get
   * @param columns the columns to get
   * @return the intersection matrix
   */
  OGArray<? extends Number> get(int[] rows, int[] columns);

  /**
   * Gets a matrix based on "linear" column major indexing into the data
   * @param linear the rows to get
   * @return the linearised look up into the data
   */
  OGArray<? extends Number> get(int[] linear);

  /**
   * Gets the indicies specified assuming linear access 
   * @param index the indicies to get
   * @return the values at the requested indicies
   */
  OGArray<? extends Number> get(OneDIndex index);

  /**
   * Gets the indicies specified assuming linear access 
   * @param index the indicies to get
   * @return the values at the requested indicies
   */
  OGArray<? extends Number> get(TwoDIndex index);

  /**
   * Gets the indicies specified 
   * @param index the indicies to get
   * @return the values at the requested indicies
   */
  OGArray<? extends Number> get(String index);

}
