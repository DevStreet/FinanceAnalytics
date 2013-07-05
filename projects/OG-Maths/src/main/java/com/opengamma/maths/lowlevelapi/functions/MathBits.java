/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions;

//import org.testng.annotations.DataProvider;

/**
 * 
 */
public class MathBits {

  // extreme values
  private static final long s_minPositiveNormal = 0x0010000000000000L;

  private static final long s_minNegativeNormal = 0x8010000000000000L;

  private static final long s_maxPositiveNormal = 0x7fefffffffffffffL;

  private static final long s_maxNegativeNormal = 0xffefffffffffffffL;

  // subnormals
  private static final long s_maxPositiveSubNormal = 0x000fffffffffffffL;

  private static final long s_maxNegativeSubNormal = 0x800fffffffffffffL;

  private static final long s_minPositiveSubNormal = 0x0000000000000001L;

  private static final long s_minNegativeSubNormal = 0x8000000000000001L;

  // zero
  private static final long s_positiveZero = 0x0000000000000000L;

  private static final long s_negativeZero = 0x8000000000000000L;

  // infinity
  private static final long s_positiveInfinity = 0x7ff0000000000000L;

  private static final long s_negativeInfinity = 0xfff0000000000000L;

  // small near +/- 1
  private static final long s_plusOne = 0x3ff0000000000000L;

  private static final long s_minusOne = 0xbff0000000000000L;

  private static final long s_smallestPositiveNormalMorePositiveThanOne = 0x3ff0000000000001L;

  private static final long s_smallestNegativeNormalMoreNegativeThanMinusOne = 0xbff0000000000001L;

  private static final long s_largestPositiveNormalMoreNegativeThanOne = 0x3fefffffffffffffL;
  
  private static final long s_largestPositiveNormalMorePositiveThanMinusOne = 0xbfefffffffffffffL;
  
  // nan
  private static final long s_positiveSignallingNaN = 0x7ff0000000000001L;

  private static final long s_negativeSignallingNaN = 0xfff0000000000001L;

  private static final long s_positiveQuietNaN = 0x7ff8000000000000L;

  private static final long s_negativeQuietNaN = 0xfff8000000000000L;

  /**
   * Returns double precision +1.
   * @return double precision value +1
   */
  public static double getPlusOne() {
    return Double.longBitsToDouble(s_plusOne);
  }

  /**
   * Returns double precision -1.
   * @return double precision value -1
   */
  public static double getMinusOne() {
    return Double.longBitsToDouble(s_minusOne);
  }

  /**
   * Returns the smallest positive normal number more positive than +1.
   * @return the smallest positive normal number more positive than +1
   */
  public static double getSmallestPositiveNormalMorePositiveThanOne() {
    return Double.longBitsToDouble(s_smallestPositiveNormalMorePositiveThanOne);
  }

  /**
   * Returns the smallest negative normal number more negative than -1.
   * @return the smallest negative normal number more negative than -1
   */
  public static double getSmallestNegativeNormalMoreNegativeThanMinusOne() {
    return Double.longBitsToDouble(s_smallestNegativeNormalMoreNegativeThanMinusOne);
  }
  
  /**
   * Returns the largest positive normal number more negative than +1.
   * @return the largest positive normal number more negative than +1
   */
  public static double getLargestPositiveNormalMoreNegativeThanOne() {
    return Double.longBitsToDouble(s_largestPositiveNormalMoreNegativeThanOne);
  }

  /**
   * Returns the largest negative normal number more positive than -1.
   * @return the largest negative normal number more positive than -1
   */
  public static double getLargestNegativeNormalMorePositiveThanMinusOne() {
    return Double.longBitsToDouble(s_largestPositiveNormalMorePositiveThanMinusOne);
  }

  /**
   * Returns the maximum positive subnormal number.
   * @return the maximum positive subnormal number.
   */
  public static double getMaximumPositiveSubnormal() {
    return Double.longBitsToDouble(s_maxPositiveSubNormal);
  }

  /**
   * Returns the maximum negative subnormal number.
   * @return the maximum negative subnormal number.
   */
  public static double getMaximumNegativeSubnormal() {
    return Double.longBitsToDouble(s_maxNegativeSubNormal);
  }

  /**
   * Returns the minimum positive subnormal number.
   * @return the minimum positive subnormal number
   */
  public static double getMinimumPositiveSubnormal() {
    return Double.longBitsToDouble(s_minPositiveSubNormal);
  }

  /**
   * Returns the minimum negative subnormal number.
   * @return the minimum negative subnormal number
   */
  public static double getMinimumNegativeSubnormal() {
    return Double.longBitsToDouble(s_minNegativeSubNormal);
  }

  /**
   * Returns positive zero.
   * @return positive zero
   */
  public static double getPositiveZero() {
    return Double.longBitsToDouble(s_positiveZero);
  }

  /**
   * Returns negative zero.
   * @return negative zero
   */
  public static double getNegativeZero() {
    return Double.longBitsToDouble(s_negativeZero);
  }

  /**
   * Returns positive infinity.
   * @return positive infinity
   */
  public static double getPositiveInfinity() {
    return Double.longBitsToDouble(s_positiveInfinity);
  }

  /**
   * Returns negative infinity.
   * @return negative infinity
   */
  public static double getNegativeInfinity() {
    return Double.longBitsToDouble(s_negativeInfinity);
  }

  /**
   * Returns the minimum positive normal number.
   * @return the minimum positive normal number
   */
  public static double getMinimumPositiveNormal() {
    return Double.longBitsToDouble(s_minPositiveNormal);
  }

  /**
   * Returns the minimum negative normal number.
   * @return the minimum negative normal number
   */
  public static double getsMinimumNegativeNormal() {
    return Double.longBitsToDouble(s_minNegativeNormal);
  }

  /**
   * Returns the maximum positive normal number.
   * @return the maximum positive normal number.
   */
  public static double getsMaximumPositiveNormal() {
    return Double.longBitsToDouble(s_maxPositiveNormal);
  }

  /**
   * Returns the maximum positive negative number.
   * @return the maximum positive negative number.
   */
  public static double getsMaximumNegativeNormal() {
    return Double.longBitsToDouble(s_maxNegativeNormal);
  }

  /**
   * Returns a positive signalling NaN.
   * @return a positive signalling NaN.
   */
  public static double getPositiveSignallingNaN() {
    return Double.longBitsToDouble(s_positiveSignallingNaN);
  }

  /**
   * Returns a negative signalling NaN.
   * @return a negative signalling NaN.
   */
  public static double getNegativeSignallingNaN() {
    return Double.longBitsToDouble(s_negativeSignallingNaN);
  }

  /**
   * Returns a positive quiet NaN.
   * @return a positive quiet NaN.
   */
  public static double getPositiveQuietNaN() {
    return Double.longBitsToDouble(s_positiveQuietNaN);
  }

  /**
   * Returns a negative quiet NaN.
   * @return a negative quiet NaN.
   */
  public static double getNegativeQuietNaN() {
    return Double.longBitsToDouble(s_negativeQuietNaN);
  }

  /**
   * Returns a signalling NaN.
   * @return a signalling NaN.
   */
  public static double getSignallingNaN() {
    return Double.longBitsToDouble(s_positiveSignallingNaN);
  }

  /**
   * Returns a quiet NaN.
   * @return a quiet NaN.
   */
  public static double getQuietNaN() {
    return Double.longBitsToDouble(s_positiveQuietNaN);
  }

  //@DataProvider(name = "mathBits")
  public Object[][] mathBitsDataProvider() {
    Object[][] tmp = new Object[20][1];
    tmp[0][0] = getPlusOne();
    tmp[1][0] = getMinusOne();
    tmp[2][0] = getSmallestPositiveNormalMorePositiveThanOne();
    tmp[3][0] = getSmallestNegativeNormalMoreNegativeThanMinusOne();
    tmp[4][0] = getMaximumPositiveSubnormal();
    tmp[5][0] = getMaximumNegativeSubnormal();
    tmp[6][0] = getMinimumPositiveSubnormal();
    tmp[7][0] = getMinimumNegativeSubnormal();
    tmp[8][0] = getPositiveZero();
    tmp[9][0] = getNegativeZero();
    tmp[10][0] = getPositiveInfinity();
    tmp[11][0] = getNegativeInfinity();
    tmp[12][0] = getMinimumPositiveNormal();
    tmp[13][0] = getsMinimumNegativeNormal();
    tmp[14][0] = getsMaximumPositiveNormal();
    tmp[15][0] = getsMaximumNegativeNormal();
    tmp[16][0] = getPositiveSignallingNaN();
    tmp[17][0] = getNegativeSignallingNaN();
    tmp[18][0] = getPositiveQuietNaN();
    tmp[19][0] = getNegativeQuietNaN();
    tmp[20][0] = getSignallingNaN();
    tmp[21][0] = getQuietNaN();
    return tmp;
  }

}
