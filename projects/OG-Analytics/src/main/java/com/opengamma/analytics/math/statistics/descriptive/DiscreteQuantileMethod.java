/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.statistics.descriptive;

import com.opengamma.util.ArgumentChecker;

/**
 * Implementation of a quantile estimator.
 * The estimation is one of the sorted sample data. It's index is given by the smallest integer above (Math.ceil) the 
 * quantile multiplied by the size of the sample. The Java index is the above index minus 1 (array index start at 0 and not 1).
 * <p> Reference: Value-At-Risk, OpenGamma Documentation 31, Version 0.1, April 2015.
 */
public abstract class DiscreteQuantileMethod implements QuantileCalculationMethod {

  @Override
  public double quantileFromSorted(double quantile, double[] sortedSample) {
    ArgumentChecker.isTrue(quantile > 0, "Quantile should be above 0.");
    ArgumentChecker.isTrue(quantile < 1, "Quantile should be below 1.");
    int sampleSize = sortedSample.length;
    int index = index(quantile * sampleSize);
    return sortedSample[index - 1];
  }
  
  /**
   * Internal method computing the index for a give quantile multiply by sample size.
   * @param quantileSize The quantile * sample size product.
   * @return The index in the sample.
   */
  abstract int index(double quantileSize);

}
