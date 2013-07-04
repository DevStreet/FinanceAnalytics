/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;


/**
 * Test plus on OGMatrix/OGMatrix combo
 */
@Test
public class PlusOGMatrixOGSparseMatrixTest {
  static double[] _data4x3Scale1 = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  static double[][] _data4x3Scale2 = new double[][] { {0., 50., 0. }, {0., 0., 90. }, {100., 0., 0. }, {10., 0., 30. } };
  static OGMatrix F4x3Scale1 = new OGMatrix(_data4x3Scale1, 4, 3);
  static OGSparseMatrix F4x3Scale2 = new OGSparseMatrix(_data4x3Scale2);
  static OGSparseMatrix F1x1Scale1 = new OGSparseMatrix(new double[][] {{1 } });
  static OGMatrix F1x1Scale2 = new OGMatrix(10);

  // null ptr etc is caught by the function pointer code

  private static PlusOGMatrixOGSparseMatrix plus = new PlusOGMatrixOGSparseMatrix();

  @Test
  public static void ScalarPlusSparse() {
    OGMatrix answer = new OGMatrix(new double[][] { {10., 60., 10. }, {10., 10., 100. }, {110., 10., 10. }, {20., 10., 40. } });
    assertTrue(answer.equals(plus.eval(F1x1Scale2, F4x3Scale2)));
  }

  @Test
  public static void FullPlusScalarFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {2., 3., 4. }, {5., 6., 7. }, {8., 9., 10. }, {11., 12., 13. } });
    assertTrue(answer.equals(plus.eval(F4x3Scale1, F1x1Scale1)));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteRows() {
    plus.eval(new OGMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3, 3), F4x3Scale2);
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteCols() {
    plus.eval(new OGMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8 }, 4, 2), F4x3Scale2);
  }

  @Test
  public static void FullPlusSparseFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {1., 52., 3. }, {4., 5., 96. }, {107., 8., 9. }, {20., 11., 42. } });
    assertTrue(answer.equals(plus.eval(F4x3Scale1, F4x3Scale2)));
  }

}
