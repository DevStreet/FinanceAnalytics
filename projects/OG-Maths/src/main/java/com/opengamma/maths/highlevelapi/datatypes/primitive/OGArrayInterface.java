/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

/**
 * 
 * @param <T> a Number type
 */
public interface OGArrayInterface<T extends Number> {

  int getNumberOfRows();

  int getNumberOfColumns();

  T getEntry(int... indices);

  double[] getData();

  /**
   * See if obj equals another object, test is performed in a numerically fuzzy sense with tolerance "tolerance"
   * @param obj the object to test
   * @param tolerance the tolerance with which to perform the test
   * @return whether the two obj is equal to this in a fuzzy sense
   */
  boolean fuzzyequals(Object obj, double tolerance);

  OGArray<? extends Number> getColumn(int col);

}
