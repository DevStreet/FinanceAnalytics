/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialMatrices;

import static org.testng.Assert.assertTrue;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;

/**
 * Tests the Wilkinson matrix generator
 */
public class WilkinsonTest {
  static double[] wilk1 = new double[] {0 };
  static double[] wilk2 = new double[] {0.5, 1, 0.5, 1 };
  static double[] wilk3 = new double[] {1, 1, 0, 1, 0, 1, 0, 1, 1 };
  static double[] wilk4 = new double[] {1.5, 1, 0, 0, 1, 0.5, 1, 0, 0, 1, 0.5, 1, 0, 0, 1, 1.5 };
  static double[] wilk9 = new double[] {4, 1, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0,
      0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 1, 3, 1, 0, 0, 0, 0, 0, 0, 0, 1, 4 };
  static double[] wilk10 = new double[] {4.5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3.5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2.5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1.5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0.5, 1, 0, 0, 0,
      0, 0, 0, 0, 0, 1, 0.5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1.5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2.5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3.5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4.5 };

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void badNTest() {
    WilkinsonFunction.wilkinson(-1);
  }

  @Test(expectedExceptions = MathsExceptionIllegalArgument.class)
  public void zeroNTest() {
    WilkinsonFunction.wilkinson(0);
  }

  @Test
  public static void simple1x1Test() {
    final int n = 1;
    OGMatrix tmp = WilkinsonFunction.wilkinson(n);
    assertArrayEquals(tmp.getData(), wilk1, 10 * D1mach.four());
    assertTrue(tmp.getNumberOfColumns() == n);
    assertTrue(tmp.getNumberOfRows() == n);
  }

  @Test
  public static void simple4x4Test() {
    final int n = 4;
    OGMatrix tmp = WilkinsonFunction.wilkinson(n);
    assertArrayEquals(tmp.getData(), wilk4, 10 * D1mach.four());
    assertTrue(tmp.getNumberOfColumns() == n);
    assertTrue(tmp.getNumberOfRows() == n);
  }

  @Test
  public static void simple9x9Test() {
    final int n = 9;
    OGMatrix tmp = WilkinsonFunction.wilkinson(n);
    assertArrayEquals(tmp.getData(), wilk9, 10 * D1mach.four());
    assertTrue(tmp.getNumberOfColumns() == n);
    assertTrue(tmp.getNumberOfRows() == n);
  }

  @Test
  public static void simple10x10Test() {
    final int n = 10;
    OGMatrix tmp = WilkinsonFunction.wilkinson(n);
    assertArrayEquals(tmp.getData(), wilk10, 10 * D1mach.four());
    assertTrue(tmp.getNumberOfColumns() == n);
    assertTrue(tmp.getNumberOfRows() == n);
  }

}
