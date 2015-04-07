/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.statistics.descriptive;

/**
 * Implementation of a quantile estimator.
 * The quantile is linearly interpolated between two sample values. The probability dimension 
 * <i>p<subscript>i</subscript> on which the interpolation take place (X axis) varies between actual
 * implementation of the abstract class. For each probability <i>p<subscript>i</subscript></i>, the distribution 
 * value is the sample value with same index. 
 * The index used above are the Java index plus 1.
 * <p> Reference: Value-At-Risk, OpenGamma Documentation 31, Version 0.1, April 2015.
 */
public abstract class InterpolationQuantileMethod implements QuantileCalculationMethod {

  @Override
  public double quantileFromSorted(double quantile, double[] sortedSample) {
    int sampleSize = sortedSample.length;
    double adjustedQuantile = quantile * sampleSize + indexCorrection();
    int lowerIndex = (int) Math.floor(adjustedQuantile);
    int upperIndex = (int) Math.ceil(adjustedQuantile);
    double lowerWeight = adjustedQuantile - lowerIndex;
    double upperWeight = 1.0d - lowerWeight;
    return lowerWeight * sortedSample[lowerIndex - 1] + upperWeight * sortedSample[upperIndex - 1];
  }
  
  abstract double indexCorrection();

}
