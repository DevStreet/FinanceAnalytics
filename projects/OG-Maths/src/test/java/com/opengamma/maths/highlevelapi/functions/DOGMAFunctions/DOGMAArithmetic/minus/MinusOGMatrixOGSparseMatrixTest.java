/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.minus;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.util.test.TestGroup;

/**
 * Test minus on OGMatrix/OGMatrix combo
 */
@Test
public class MinusOGMatrixOGSparseMatrixTest {
  static double[] _data4x3Scale1 = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  static double[][] _data4x3Scale2 = new double[][] { {0., 50., 0. }, {0., 0., 90. }, {100., 0., 0. }, {10., 0., 30. } };
  static OGMatrix F4x3Scale1 = new OGMatrix(_data4x3Scale1, 4, 3);
  static OGSparseMatrix F4x3Scale2 = new OGSparseMatrix(_data4x3Scale2);
  static OGSparseMatrix F1x1Scale1 = new OGSparseMatrix(new double[][] {{1 } });
  static OGMatrix F1x1Scale2 = new OGMatrix(10);

  // null ptr etc is caught by the function pointer code

  private static MinusOGMatrixOGSparseMatrix minus = new MinusOGMatrixOGSparseMatrix();

  @Test
  public static void ScalarMinusSparse() {
    OGMatrix answer = new OGMatrix(new double[][] { {10., -40., 10. }, {10., 10., -80. }, {-90., 10., 10. }, {0., 10., -20. } });
    assertTrue(answer.equals(minus.eval(F1x1Scale2, F4x3Scale2)));
  }

  @Test
  public static void FullMinusScalarFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {0., 1., 2. }, {3., 4., 5. }, {6., 7., 8. }, {9., 10., 11. } });
    assertTrue(answer.equals(minus.eval(F4x3Scale1, F1x1Scale1)));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteRows() {
    minus.eval(new OGMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3, 3), F4x3Scale2);
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteCols() {
    minus.eval(new OGMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8 }, 4, 2), F4x3Scale2);
  }

  @Test
  public static void FullMinusSparseFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {1., -48., 3. }, {4., 5., -84. }, {-93., 8., 9. }, {0., 11., -18. } });
    assertTrue(answer.equals(minus.eval(F4x3Scale1, F4x3Scale2)));
  }

}
