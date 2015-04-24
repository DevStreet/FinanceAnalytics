/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.analytics.math.statistics.descriptive;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

public class QuantileCalculationMethodTest {
  
  public static final IndexAboveQuantileMethod QUANTILE_INDEX_ABOVE = IndexAboveQuantileMethod.DEFAULT;
  public static final NearestIndexQuantileMethod QUANTILE_NEAREST_INDEX = NearestIndexQuantileMethod.DEFAULT;
  public static final SampleInterpolationQuantileMethod QUANTILE_SAMPLE_INTERPOLATION = 
      SampleInterpolationQuantileMethod.DEFAULT;
  public static final MidwayInterpolationQuantileMethod QUANTILE_MIDWAY_INTERPOLATION = 
      MidwayInterpolationQuantileMethod.DEFAULT;

  public static final double[] SAMPLE_SORTED_100 = new double[] {
    0.0286, 0.0363, 0.0379, 0.0582, 0.0611, 0.0622, 0.0776, 0.0779, 0.0849, 0.0916,
    0.1055, 0.1358, 0.1474, 0.1544, 0.1674, 0.1740, 0.1746, 0.1841, 0.1963, 0.1982,
    0.2020, 0.2222, 0.2401, 0.2582, 0.2666, 0.2979, 0.2998, 0.3000, 0.3028, 0.3057,
    0.3508, 0.3734, 0.3781, 0.4011, 0.4197, 0.4463, 0.4481, 0.4863, 0.4878, 0.4908,
    0.4942, 0.5029, 0.5212, 0.5224, 0.5290, 0.5780, 0.5803, 0.5807, 0.5921, 0.6174,
    0.6243, 0.6278, 0.6325, 0.6343, 0.6416, 0.6423, 0.6460, 0.6504, 0.6570, 0.6666,
    0.6748, 0.6763, 0.6804, 0.6859, 0.6862, 0.7136, 0.7145, 0.7289, 0.7291, 0.7360,
    0.7444, 0.7532, 0.7543, 0.7602, 0.7714, 0.8024, 0.8053, 0.8075, 0.8190, 0.8190,
    0.8216, 0.8234, 0.8399, 0.8483, 0.8511, 0.8557, 0.8631, 0.8814, 0.8870, 0.8963,
    0.9150, 0.9157, 0.9198, 0.9275, 0.9524, 0.9570, 0.9620, 0.9716, 0.9731, 0.9813 };
  public static final int SAMPLE_SIZE_100 = SAMPLE_SORTED_100.length;
  public static final double[] SAMPLE_SORTED_123 = new double[] {
    0.0286, 0.0363, 0.0379, 0.0582, 0.0611, 0.0622, 0.0776, 0.0779, 0.0822, 0.0849,
    0.0916, 0.1055, 0.1358, 0.1474, 0.1544, 0.1674, 0.1740, 0.1746, 0.1841, 0.1963,
    0.1982, 0.2020, 0.2155, 0.2222, 0.2401, 0.2413, 0.2582, 0.2666, 0.2936, 0.2979,
    0.2998, 0.3000, 0.3028, 0.3057, 0.3076, 0.3461, 0.3508, 0.3717, 0.3734, 0.3781,
    0.4011, 0.4157, 0.4197, 0.4285, 0.4463, 0.4481, 0.4534, 0.4690, 0.4863, 0.4878,
    0.4908, 0.4942, 0.5029, 0.5083, 0.5108, 0.5212, 0.5224, 0.5290, 0.5578, 0.5780,
    0.5803, 0.5807, 0.5921, 0.6174, 0.6243, 0.6278, 0.6325, 0.6343, 0.6416, 0.6423,
    0.6460, 0.6495, 0.6504, 0.6570, 0.6666, 0.6694, 0.6748, 0.6763, 0.6793, 0.6804,
    0.6859, 0.6862, 0.7136, 0.7145, 0.7289, 0.7291, 0.7360, 0.7444, 0.7532, 0.7543,
    0.7602, 0.7714, 0.7768, 0.8024, 0.8053, 0.8075, 0.8190, 0.8190, 0.8216, 0.8234,
    0.8399, 0.8421, 0.8483, 0.8511, 0.8557, 0.8631, 0.8814, 0.8870, 0.8914, 0.8963,
    0.9077, 0.9150, 0.9157, 0.9198, 0.9275, 0.9368, 0.9524, 0.9570, 0.9600, 0.9620,
    0.9716, 0.9731, 0.9813 };
  public static final int SAMPLE_SIZE_123 = SAMPLE_SORTED_123.length;
  public static final double LEVEL = 0.95;
  public static final double TOLERANCE_QUANTILE = 1.0E-10;
  
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void discrete_wrong_quantile_large() {
    QUANTILE_INDEX_ABOVE.quantileFromSorted(1.01, SAMPLE_SORTED_100);
  }
  
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void discrete_wrong_quantile_0() {
    QUANTILE_INDEX_ABOVE.quantileFromSorted(0.0, SAMPLE_SORTED_100);
  }
  
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void interpolation_wrong_quantile_large() {
    QUANTILE_SAMPLE_INTERPOLATION.quantileFromSorted(1.01, SAMPLE_SORTED_100);
  }
  
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void interpolation_wrong_quantile_0() {
    QUANTILE_SAMPLE_INTERPOLATION.quantileFromSorted(0.0, SAMPLE_SORTED_100);
  }
  
  @Test
  public void index_above_095_100() {
    double indexDouble = LEVEL * SAMPLE_SIZE_100;
    int indexCeil = (int) Math.ceil(indexDouble);
    double quantileExpected = SAMPLE_SORTED_100[indexCeil - 1]; // Java index start at 0.
    double quantileComputed = QUANTILE_INDEX_ABOVE.quantileFromSorted(LEVEL, SAMPLE_SORTED_100);
    assertEquals(quantileComputed, quantileExpected, TOLERANCE_QUANTILE);
  }
  
  @Test
  public void index_above_095_123() {
    double indexDouble = LEVEL * SAMPLE_SIZE_100;
    int indexCeil = (int) Math.ceil(indexDouble);
    double quantileExpected = SAMPLE_SORTED_100[indexCeil - 1]; // Java index start at 0.
    double quantileComputed = QUANTILE_INDEX_ABOVE.quantileFromSorted(LEVEL, SAMPLE_SORTED_100);
    assertEquals(quantileComputed, quantileExpected);
  }
  
  /** On sample points, different methods match. */
  @Test
  public void index_nearest_095_100() {
    double quantileExpected = QUANTILE_INDEX_ABOVE.quantileFromSorted(LEVEL, SAMPLE_SORTED_100);
    double quantileComputed = QUANTILE_NEAREST_INDEX.quantileFromSorted(LEVEL, SAMPLE_SORTED_100);
    assertEquals(quantileComputed, quantileExpected, TOLERANCE_QUANTILE);
  }
  
  @Test
  public void index_nearest_0951_100() {
    double indexDouble = (LEVEL+0.001) * SAMPLE_SIZE_100;
    int indexRound = (int) Math.round(indexDouble);
    double quantileExpected = SAMPLE_SORTED_100[indexRound - 1]; // Java index start at 0.
    double quantileComputed = QUANTILE_NEAREST_INDEX.quantileFromSorted(LEVEL, SAMPLE_SORTED_100);
    assertEquals(quantileComputed, quantileExpected, TOLERANCE_QUANTILE);
  }
  
  @Test
  public void index_nearest_0001_100() {
    double level = 0.001;
    double quantileExpected = SAMPLE_SORTED_100[0];
    double quantileComputed = QUANTILE_NEAREST_INDEX.quantileFromSorted(level, SAMPLE_SORTED_100);
    assertEquals(quantileComputed, quantileExpected, TOLERANCE_QUANTILE);
  }

  /** On sample points, different methods match. */
  @Test
  public void interpolation_sample_095_100() {
    double quantileExpected = QUANTILE_NEAREST_INDEX.quantileFromSorted(LEVEL, SAMPLE_SORTED_100);
    double quantileComputed = QUANTILE_SAMPLE_INTERPOLATION.quantileFromSorted(LEVEL, SAMPLE_SORTED_100);
    assertEquals(quantileComputed, quantileExpected, TOLERANCE_QUANTILE);
  }
  
  @Test
  public void interpolation_sample_095_123() {
    double indexDouble = LEVEL * SAMPLE_SIZE_123;
    int indexCeil = (int) Math.ceil(indexDouble);
    int indexFloor = (int) Math.floor(indexDouble);
    double quantileCeil = SAMPLE_SORTED_123[indexCeil - 1]; // Java index start at 0.
    double quantileFloor = SAMPLE_SORTED_123[indexFloor - 1];
    double pi = (double) indexFloor / (double) SAMPLE_SIZE_123;
    double pi1 = (double) indexCeil / (double) SAMPLE_SIZE_123;
    double quantileExpected = quantileFloor + (LEVEL - pi) / (pi1 - pi) * (quantileCeil - quantileFloor);
    double quantileComputed = QUANTILE_SAMPLE_INTERPOLATION.quantileFromSorted(LEVEL, SAMPLE_SORTED_123);
    assertEquals(quantileComputed, quantileExpected, TOLERANCE_QUANTILE);
  }
  
  @Test
  public void interpolation_midway_095_123() {
    double correction = 0.5;
    double indexDouble = LEVEL * SAMPLE_SIZE_123 + correction;
    int indexCeil = (int) Math.ceil(indexDouble);
    int indexFloor = (int) Math.floor(indexDouble);
    double quantileCeil = SAMPLE_SORTED_123[indexCeil - 1]; // Java index start at 0.
    double quantileFloor = SAMPLE_SORTED_123[indexFloor - 1];
    double pi = (indexFloor - correction) / (double) SAMPLE_SIZE_123;
    double pi1 = (indexCeil - correction) / (double) SAMPLE_SIZE_123;
    double quantileExpected = quantileFloor + (LEVEL - pi) / (pi1 - pi) * (quantileCeil - quantileFloor);
    double quantileComputed = QUANTILE_MIDWAY_INTERPOLATION.quantileFromSorted(LEVEL, SAMPLE_SORTED_123);
    assertEquals(quantileComputed, quantileExpected, TOLERANCE_QUANTILE);
  }
  
}
