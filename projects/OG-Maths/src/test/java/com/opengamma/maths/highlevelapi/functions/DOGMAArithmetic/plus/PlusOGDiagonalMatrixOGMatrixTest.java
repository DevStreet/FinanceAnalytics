/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.plus;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.minus.MinusOGDiagonalMatrixOGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.plus.PlusOGDiagonalMatrixOGMatrix;

/**
 * Tests adding/subtracting Diagonal and Full Arrays 
 */
public class PlusOGDiagonalMatrixOGMatrixTest {

  static double[] _data4x3 = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  static double[] _datadiag = new double[] {10, 20, 30 };

  static OGMatrix F4x3 = new OGMatrix(_data4x3, 4, 3);
  static OGMatrix F1x1 = new OGMatrix(10.);
  static OGDiagonalMatrix D4x3 = new OGDiagonalMatrix(_datadiag, 4, 3);
  static OGDiagonalMatrix D1x1 = new OGDiagonalMatrix(_datadiag, 1, 1);

  static OGDiagonalMatrix F1x1plusD4x3 = new OGDiagonalMatrix(_datadiag);

  // null ptr etc is caught by the function pointer code

  private static PlusOGDiagonalMatrixOGMatrix plus = new PlusOGDiagonalMatrixOGMatrix();
  private static MinusOGDiagonalMatrixOGMatrix minus = new MinusOGDiagonalMatrixOGMatrix();

  @Test
  public static void scalarDiagPlusFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {20.00, 10.00, 10.00 }, {10.00, 30.00, 10.00 }, {10.00, 10.00, 40.00 }, {10.00, 10.00, 10.00 } });
    assertTrue(answer.equals(plus.eval(D4x3, F1x1)));
  }

  @Test
  public static void scalarDiagMinusFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {0., -10., -10. }, {-10., 10., -10. }, {-10., -10., 20. }, {-10., -10., -10. }});
    assertTrue(answer.fuzzyequals(minus.eval(D4x3, F1x1),1e-15));
  }

  @Test
  public static void ScalarDiagPlusFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {11.00, 12.00, 13.00 }, {14.00, 15.00, 16.00 }, {17.00, 18.00, 19.00 }, {20.00, 21.00, 22.00 } });
    assertTrue(answer.equals(plus.eval(D1x1, F4x3)));
  }

  @Test
  public static void ScalarDiagMinusFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {9., 8., 7. }, {6., 5., 4. }, {3., 2., 1. }, {0., -1., -2. }});
    assertTrue(answer.fuzzyequals(minus.eval(D1x1, F4x3),1e-15));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteRows() {
    plus.eval(new OGDiagonalMatrix(_datadiag, 3, 3), F4x3);
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteCols() {
    plus.eval(new OGDiagonalMatrix(_datadiag, 4, 23), F4x3);
  }

  @Test
  public static void FullPlusDiag() {
    OGMatrix answer = new OGMatrix(new double[][] { {11.00, 2.00, 3.00 }, {4.00, 25.00, 6.00 }, {7.00, 8.00, 39.00 }, {10.00, 11.00, 12.00 } });
    assertTrue(answer.equals(plus.eval(D4x3, F4x3)));
  }

  @Test
  public static void FullMinusDiag() {
    OGMatrix answer = new OGMatrix(new double[][] { {9., -2., -3. }, {-4., 15., -6. }, {-7., -8., 21. }, {-10., -11., -12. } });
    assertTrue(answer.equals(minus.eval(D4x3, F4x3)));
  }
}
