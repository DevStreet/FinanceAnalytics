/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.minus;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.minus.MinusOGMatrixOGDiagonalMatrix;
import com.opengamma.util.test.TestGroup;

/**
 * Tests subtracting Full Arrays and Diagonal Arrays in that order 
 */
@Test(groups = TestGroup.UNIT)
public class MinusOGMatrixOGDiagonalMatrixTest {

  static double[] _data4x3 = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  static double[] _datadiag = new double[] {10, 20, 30 };

  static OGMatrix F4x3 = new OGMatrix(_data4x3, 4, 3);
  static OGMatrix F1x1 = new OGMatrix(10.);
  static OGDiagonalMatrix D4x3 = new OGDiagonalMatrix(_datadiag, 4, 3);
  static OGDiagonalMatrix D1x1 = new OGDiagonalMatrix(_datadiag, 1, 1);

  static OGDiagonalMatrix F1x1plusD4x3 = new OGDiagonalMatrix(_datadiag);

  // null ptr etc is caught by the function pointer code

  private static MinusOGMatrixOGDiagonalMatrix minus = new MinusOGMatrixOGDiagonalMatrix();

  @Test
  public static void scalarFullMinusDiag() {
    OGMatrix answer = new OGMatrix(new double[][] { {0.00, 10.00, 10.00 }, {10.00, -10.00, 10.00 }, {10.00, 10.00, -20.00 }, {10.00, 10.00, 10.00 } });
    assertTrue(answer.equals(minus.eval(F1x1, D4x3)));
  }

  @Test
  public static void FullMinusScalarDiag() {
    OGMatrix answer = new OGMatrix(new double[][] { {-9.00, -8.00, -7.00 }, {-6.00, -5.00, -4.00 }, {-3.00, -2.00, -1.00 }, {0.00, 1.00, 2.00 } });
    assertTrue(answer.equals(minus.eval(F4x3, D1x1)));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteRows() {
    minus.eval(F4x3, new OGDiagonalMatrix(_datadiag, 3, 3));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteCols() {
    minus.eval(F4x3, new OGDiagonalMatrix(_datadiag, 4, 23));
  }

  @Test
  public static void FullMinusDiag() {
    OGMatrix answer = new OGMatrix(new double[][] { {-9., 2., 3. }, {4., -15., 6. }, {7., 8., -21. }, {10., 11., 12. } });
    assertTrue(answer.equals(minus.eval(F4x3, D4x3)));
  }
}
