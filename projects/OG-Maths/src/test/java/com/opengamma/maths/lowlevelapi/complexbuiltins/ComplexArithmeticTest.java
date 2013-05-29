/**
 * Copyright (C) 2013 ,present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.complexbuiltins;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.lowlevelapi.functions.FPEquals;

/**
 * Tests the {@link ComplexArithmetic} class
 */
@Test
public class ComplexArithmeticTest {

  @Test
  public void caddTest() {
    double[] input0 = new double[] {1, 2 };
    double[] input1 = new double[] {2, 4 };
    double[] expected = new double[] {3.00, 6.00 };
    double[] result;
    result = ComplexArithmetic.cadd(input0, input1);
    assertTrue(FPEquals.fuzzyEquals(result, expected));
    result = ComplexArithmetic.cadd(input1, input0);
    assertTrue(FPEquals.fuzzyEquals(result, expected));
  }

  @Test
  public void caddInlineTest() {
    double[] input0 = new double[] {1, 2, 3, 4, 5, 6, 7, 8 };
    double[] input1 = new double[] {2, 4, 6, 8, 10, 12, 14, 16 };
    double[] expected = new double[] {3.00, 6.00, 9.00, 12.00, 15.00, 18.00, 21.00, 24.00 };
    double[] result;
    result = new double[2];
    ComplexArithmetic.caddInline(input0, 0, input1, 0, result, 0);
    result = new double[8];
    for (int i = 0; i < 4; i++) {
      ComplexArithmetic.caddInline(input0, i, input1, i, result, i);
    }
    assertTrue(FPEquals.fuzzyEquals(result, expected));
  }

  @Test
  public void csubtractTest() {
    double[] input0 = new double[] {1, 2 };
    double[] input1 = new double[] {2, 4 };
    double[] expected0 = new double[] {-1.00, -2.00 };
    double[] expected1 = new double[] {1.00, 2.00 };
    double[] result;
    result = ComplexArithmetic.csubtract(input0, input1);
    assertTrue(FPEquals.fuzzyEquals(result, expected0));
    result = ComplexArithmetic.csubtract(input1, input0);
    assertTrue(FPEquals.fuzzyEquals(result, expected1));
  }

  @Test
  public void csubtractInlineTest() {
    double[] input0 = new double[] {1, 2, 3, 4, 5, 6, 7, 8 };
    double[] input1 = new double[] {2, 4, 6, 8, 10, 12, 14, 16 };
    double[] expected0 = new double[] {-1, -2, -3, -4, -5, -6, -7, -8 };
    double[] expected1 = new double[] {1, 2, 3, 4, 5, 6, 7, 8 };
    double[] result;
    result = new double[2];
    ComplexArithmetic.csubtractInline(input0, 0, input1, 0, result, 0);
    result = new double[8];
    for (int i = 0; i < 4; i++) {
      ComplexArithmetic.csubtractInline(input0, i, input1, i, result, i);
    }
    assertTrue(FPEquals.fuzzyEquals(result, expected0));
    // args reversed
    result = new double[2];
    ComplexArithmetic.csubtractInline(input1, 0, input0, 0, result, 0);
    result = new double[8];
    for (int i = 0; i < 4; i++) {
      ComplexArithmetic.csubtractInline(input1, i, input0, i, result, i);
    }
    assertTrue(FPEquals.fuzzyEquals(result, expected1));

  }

  @Test
  public void cdivideTest() {
    double[] input0 = new double[] {1, 2 };
    double[] input1 = new double[] {2, 4 };
    double[] expected0 = new double[] {0.5, 0.0 };
    double[] expected1 = new double[] {2.00, 0.00 };
    double[] result;
    result = ComplexArithmetic.cdivide(input0, input1);
    assertTrue(FPEquals.fuzzyEquals(result, expected0));
    result = ComplexArithmetic.cdivide(input1, input0);
    assertTrue(FPEquals.fuzzyEquals(result, expected1));
  }

  @Test
  public void cdivideInlineTest() {
    double[] input0 = new double[] {1, 2, 3, 4, 5, 6, 7, 8 };
    double[] input1 = new double[] {2, 4, 6, 8, 10, 12, 14, 16 };
    double[] expected0 = new double[] {0.5, 0, 0.5, 0, 0.5, 0, 0.5, 0 };
    double[] expected1 = new double[] {2, 0, 2, 0, 2, 0, 2, 0 };
    double[] result;
    result = new double[2];
    ComplexArithmetic.cdivideInline(input0, 0, input1, 0, result, 0);
    result = new double[8];
    for (int i = 0; i < 4; i++) {
      ComplexArithmetic.cdivideInline(input0, i, input1, i, result, i);
    }
    assertTrue(FPEquals.fuzzyEquals(result, expected0));
    // args reversed
    result = new double[2];
    ComplexArithmetic.cdivideInline(input1, 0, input0, 0, result, 0);
    result = new double[8];
    for (int i = 0; i < 4; i++) {
      ComplexArithmetic.cdivideInline(input1, i, input0, i, result, i);
    }
    assertTrue(FPEquals.fuzzyEquals(result, expected1));

    input0 = new double[] {2, 1, 4, 3, 6, 5, 8, 7 };
    input1 = new double[] {4, 2, 8, 6, 12, 10, 16, 14 };

    result = new double[8];
    for (int i = 0; i < 4; i++) {
      ComplexArithmetic.cdivideInline(input0, i, input1, i, result, i);
    }
    assertTrue(FPEquals.fuzzyEquals(result, expected0));
    // args reversed
    result = new double[2];
    ComplexArithmetic.cdivideInline(input1, 0, input0, 0, result, 0);
    result = new double[8];
    for (int i = 0; i < 4; i++) {
      ComplexArithmetic.cdivideInline(input1, i, input0, i, result, i);
    }
    assertTrue(FPEquals.fuzzyEquals(result, expected1));
  }

  @Test
  public void cmultiplyTest() {
    double[] input0 = new double[] {1, 2 };
    double[] input1 = new double[] {2, 4 };
    double[] expected = new double[] {-6.00, 8.00 };
    double[] result;
    result = ComplexArithmetic.cmultiply(input0, input1);
    assertTrue(FPEquals.fuzzyEquals(result, expected));
    result = ComplexArithmetic.cmultiply(input1, input0);
    assertTrue(FPEquals.fuzzyEquals(result, expected));
  }

  @Test
  public void cmultiplyInlineTest() {
    double[] input0 = new double[] {1, 2, 3, 4, 5, 6, 7, 8 };
    double[] input1 = new double[] {2, 4, 6, 8, 10, 12, 14, 16 };
    double[] expected = new double[] {-6.00, 8.00, -14.00, 48.00, -22.00, 120.00, -30.00, 224.00 };
    double[] result;
    result = new double[2];
    ComplexArithmetic.cmultiplyInline(input0, 0, input1, 0, result, 0);
    result = new double[8];
    for (int i = 0; i < 4; i++) {
      ComplexArithmetic.cmultiplyInline(input0, i, input1, i, result, i);
    }
    assertTrue(FPEquals.fuzzyEquals(result, expected));
    result = new double[8];
    for (int i = 0; i < 4; i++) {
      ComplexArithmetic.cmultiplyInline(input0[2 * i], input0[2 * i + 1], input1[2 * i], input1[2 * i + 1], result, i);
    }
    assertTrue(FPEquals.fuzzyEquals(result, expected));
  }
}
