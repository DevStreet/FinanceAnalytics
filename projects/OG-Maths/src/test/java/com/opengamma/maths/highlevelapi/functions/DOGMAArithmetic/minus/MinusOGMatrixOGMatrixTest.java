/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.minus;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.minus.MinusOGMatrixOGMatrix;
import com.opengamma.util.test.TestGroup;

/**
 * Test minus on OGMatrix/OGMatrix combo
 */
@Test(groups = TestGroup.UNIT)
public class MinusOGMatrixOGMatrixTest {
  static double[] _data4x3Scale1 = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  static double[] _data4x3Scale2 = new double[] {10, 40, 70, 100, 20, 50, 80, 110, 30, 60, 90, 120 };
  static OGMatrix F4x3Scale1 = new OGMatrix(_data4x3Scale1, 4, 3);
  static OGMatrix F4x3Scale2 = new OGMatrix(_data4x3Scale2, 4, 3);
  static OGMatrix F1x1Scale1 = new OGMatrix(1);
  static OGMatrix F1x1Scale2 = new OGMatrix(10);

  // null ptr etc is caught by the function pointer code

  private static MinusOGMatrixOGMatrix minus = new MinusOGMatrixOGMatrix();

  @Test
  public static void scalarFullMinusFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {9., 8., 7. }, {6., 5., 4. }, {3., 2., 1. }, {0., -1., -2. } });
    assertTrue(answer.equals(minus.eval(F1x1Scale2, F4x3Scale1)));
  }

  @Test
  public static void FullMinusScalarFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {-9., -8., -7. }, {-6., -5., -4. }, {-3., -2., -1. }, {0., 1., 2. } });
    assertTrue(answer.equals(minus.eval(F4x3Scale1, F1x1Scale2)));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteRows() {
    minus.eval(new OGMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3, 3), F4x3Scale1);
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteCols() {
    minus.eval(new OGMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8 }, 4, 2), F4x3Scale1);
  }

  @Test
  public static void FullMinusFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {-9., -18., -27. }, {-36., -45., -54. }, {-63., -72., -81. }, {-90., -99., -108. } });
    assertTrue(answer.equals(minus.eval(F4x3Scale1, F4x3Scale2)));
  }

}
