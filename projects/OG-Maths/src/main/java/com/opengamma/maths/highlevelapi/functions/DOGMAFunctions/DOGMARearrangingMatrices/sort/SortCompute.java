/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.sort;

/**
 * Enum to describe what the sort() function should be computing
 */
public enum SortCompute {

  /**
   * Compute the sorted "values"
   */
  values,

  /**
   * Compute the sorted order of the keys based on "values"
   */
  keys,

  /**
   * Compute by sorting the "values" and permuting the "keys" by the same order
   */
  valuesAndKeys

}
