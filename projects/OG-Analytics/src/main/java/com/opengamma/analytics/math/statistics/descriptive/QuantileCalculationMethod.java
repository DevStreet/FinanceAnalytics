/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.statistics.descriptive;

/**
 * Interface to the methods to estimator quantile from sample observations.
 */
public interface QuantileCalculationMethod {
  
  /**
   * Compute the quantile estimation.
   * @param quantile The quantile level. The number is in decimal, i.e. 99% = 0.99. Should be 0 < quantile < 1.
   * @param sortedSample The sample observations. Sorted from the smallest to the largest.
   * @return The quantile estimation.
   */
  double quantileFromSorted(double quantile, double[] sortedSample);

}
