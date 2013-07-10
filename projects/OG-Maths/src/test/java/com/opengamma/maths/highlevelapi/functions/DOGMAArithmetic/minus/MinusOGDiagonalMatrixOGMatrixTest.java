/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.minus;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.minus.MinusOGDiagonalMatrixOGMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * Tests adding/subtracting Diagonal and Full Arrays 
 */
@Test(groups = TestGroup.UNIT)
public class MinusOGDiagonalMatrixOGMatrixTest {

  static double[] _data4x3 = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  static double[] _datadiag = new double[] {10, 20, 30 };

  static OGMatrix F4x3 = new OGMatrix(_data4x3, 4, 3);
  static OGMatrix F1x1 = new OGMatrix(10.);
  static OGDiagonalMatrix D4x3 = new OGDiagonalMatrix(_datadiag, 4, 3);
  static OGDiagonalMatrix D1x1 = new OGDiagonalMatrix(_datadiag, 1, 1);

  static OGDiagonalMatrix F1x1plusD4x3 = new OGDiagonalMatrix(_datadiag);

  // null ptr etc is caught by the function pointer code

  private static MinusOGDiagonalMatrixOGMatrix minus = new MinusOGDiagonalMatrixOGMatrix();

  @Test
  public static void DiagMinusScalarFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {0., -10., -10. }, {-10., 10., -10. }, {-10., -10., 20. }, {-10., -10., -10. } });
    assertTrue(answer.fuzzyequals(minus.eval(D4x3, F1x1),10*D1mach.four()));
  }

  @Test
  public static void scalarDiagMinusFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {9., 8., 7. }, {6., 5., 4. }, {3., 2., 1. }, {0., -1., -2. } });
    assertTrue(answer.fuzzyequals(minus.eval(D1x1, F4x3),10*D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteRows() {
    minus.eval(new OGDiagonalMatrix(_datadiag, 3, 3), F4x3);
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteCols() {
    minus.eval(new OGDiagonalMatrix(_datadiag, 4, 23), F4x3);
  }

  @Test
  public static void DiagMinusFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {9., -2., -3. }, {-4., 15., -6. }, {-7., -8., 21. }, {-10., -11., -12. } });
    assertTrue(answer.equals(minus.eval(D4x3, F4x3)));
  }

}
