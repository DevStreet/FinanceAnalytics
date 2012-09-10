/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.performanceutils;

import java.util.Arrays;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * Tests the double data pool supplier
 */
public class DoubleDataPoolTest {

  @Test
  public void defaultConstructorTest() {
    new DoubleDataPool();
  }

  @Test
  public void sizedConstructorTest() {
    new DoubleDataPool(10);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void sizedConstructorBadSizeTest() {
    new DoubleDataPool(-10);
  }

  @Test
  public void dataSpanTest() {
    DoubleDataPool pool = new DoubleDataPool(4);
    double[] tmp = pool.yieldData(9);
    double[] tmp41 = new double[4];
    double[] tmp42 = new double[4];
    System.arraycopy(tmp, 0, tmp41, 0, 4);
    System.arraycopy(tmp, 4, tmp42, 0, 4);
    assert (Arrays.equals(tmp41, tmp42)); // loop segments
    assert (tmp[8]==tmp41[0]); // loop cascade
  }

}
