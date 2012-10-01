/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;

/**
 * Tests D9LGMC from SLATEC
 */
public class D9LGMCTest {
  private static double[] x = new double[] {10.000000000000000, 100.00000000000000, 1000.0000000000000, 10000.000000000000, 100000.00000000000, 1000000.0000000000, 10000000.000000000,
      100000000.00000000,
      1000000000.0000000 };

  private static double[] answer = new double[] {8.33056343336287079E-003, 8.33330555634914779E-004, 8.33333305555563529E-005, 8.33333333055555525E-006, 8.33333333330555698E-007,
      8.33333333333305723E-008, 8.33333333333333185E-009, 8.33333333333333351E-010, 8.33333333333333299E-011 };

  private double fp_limit = 1e-7; // routine casts to float() so we can only assume the answer is correct to the safe cast level

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void invalidInputTest() {
    D9LGMC.d9lgmc(9);
  }

  @Test
  public void okInputTest() {
    D9LGMC.d9lgmc(10);
  }

  @Test
  public void deliberateUnderflowTest() {
    D9LGMC.d9lgmc(1e307);
  }

  @Test
  public void numberRangeTest() {
    for (int i = 0; i < x.length; i++) {
      assertTrue(Math.abs(D9LGMC.d9lgmc(x[i]) - answer[i]) < fp_limit);
    }
  }
}
