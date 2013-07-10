/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * Test plus on OGComplexMatrix/OGComplexMatrix combo
 */
@Test(groups = TestGroup.UNIT)
public class PlusOGComplexMatrixOGComplexMatrixTest {
  static double[] _data4x3Scale1 = new double[] {1, 2, 4, 5, 7, 8, 10, 11, 2, 3, 5, 6, 8, 9, 11, 12, 3, 4, 6, 7, 9, 10, 12, 13 };
  static double[] _data4x3Scale2 = new double[] {10.00, 20.00, 40.00, 50.00, 70.00, 80.00, 100.00, 110.00, 20.00, 30.00, 50.00, 60.00, 80.00, 90.00, 110.00, 120.00, 30.00, 40.00, 60.00, 70.00, 90.00,
      100.00, 120.00, 130.00 };
  static OGComplexMatrix F4x3Scale1 = new OGComplexMatrix(_data4x3Scale1, 4, 3);
  static OGComplexMatrix F4x3Scale2 = new OGComplexMatrix(_data4x3Scale2, 4, 3);
  static OGComplexMatrix F1x1Scale1 = new OGComplexMatrix(new double[] {1, 2 }, 1, 1);
  static OGComplexMatrix F1x1Scale2 = new OGComplexMatrix(new double[] {10, 20 }, 1, 1);

  // null ptr etc is caught by the function pointer code

  private static PlusOGComplexMatrixOGComplexMatrix plus = new PlusOGComplexMatrixOGComplexMatrix();

  @Test
  public static void scalarFullPlusFull() {
    OGComplexMatrix answer = new OGComplexMatrix(new double[][] { {11., 12., 13. }, {14., 15., 16. }, {17., 18., 19. }, {20., 21., 22. } }, new double[][] { {22., 23., 24. }, {25., 26., 27. },
        {28., 29., 30. }, {31., 32., 33. } });
    assertTrue(answer.equals(plus.eval(F1x1Scale2, F4x3Scale1)));
  }

  @Test
  public static void FullPlusScalarFull() {
    OGComplexMatrix answer = new OGComplexMatrix(new double[][] { {11., 12., 13. }, {14., 15., 16. }, {17., 18., 19. }, {20., 21., 22. } }, new double[][] { {22., 23., 24. }, {25., 26., 27. },
        {28., 29., 30. }, {31., 32., 33. } });
    assertTrue(answer.equals(plus.eval(F4x3Scale1, F1x1Scale2)));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteRows() {
    plus.eval(new OGComplexMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3, 3), F4x3Scale1);
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteCols() {
    plus.eval(new OGComplexMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8 }, 4, 2), F4x3Scale1);
  }

  @Test
  public static void FullPlusFull() {
    OGComplexMatrix answer = new OGComplexMatrix(new double[][] { {11., 22., 33. }, {44., 55., 66. }, {77., 88., 99. }, {110., 121., 132. } }, new double[][] { {22., 33., 44. }, {55., 66., 77. },
        {88., 99., 110. }, {121., 132., 143. } });
    assertTrue(answer.fuzzyequals(plus.eval(F4x3Scale1, F4x3Scale2), 10 * D1mach.four()));
  }

}
