/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;

/**
 * Tests adding/subtracting Diagonal and Full Arrays 
 */
public class PlusOGDiagonalArrayOGDoubleArrayTest {

  static double[] _data4x3 = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  static double[] _datadiag = new double[] {10, 20, 30 };

  static OGDoubleArray F4x3 = new OGDoubleArray(_data4x3, 4, 3);
  static OGDoubleArray F1x1 = new OGDoubleArray(10.);
  static OGDiagonalArray D4x3 = new OGDiagonalArray(_datadiag, 4, 3);
  static OGDiagonalArray D1x1 = new OGDiagonalArray(_datadiag, 1, 1);

  static OGDiagonalArray F1x1plusD4x3 = new OGDiagonalArray(_datadiag);

  // null ptr etc is caught by the function pointer code

  private static PlusOGDiagonalArrayOGDoubleArray pm = PlusOGDiagonalArrayOGDoubleArray.getInstance();

  @Test
  public static void scalarFullPlusDiag() {
    OGDoubleArray answer = new OGDoubleArray(new double[][] { {20.00, 10.00, 10.00 }, {10.00, 30.00, 10.00 }, {10.00, 10.00, 40.00 }, {10.00, 10.00, 10.00 } });
    assertTrue(answer.equals(pm.plusminus(D4x3, F1x1, +1)));
  }

  @Test
  public static void scalarFullMinusDiag() {
    OGDoubleArray answer = new OGDoubleArray(new double[][] { {0.00, 10.00, 10.00 }, {10.00, -10.00, 10.00 }, {10.00, 10.00, -20.00 }, {10.00, 10.00, 10.00 } });
    assertTrue(answer.equals(pm.plusminus(D4x3,F1x1, -1)));
  }

  @Test
  public static void FullPlusScalarDiag() {
    OGDoubleArray answer = new OGDoubleArray(new double[][] { {11.00, 12.00, 13.00 }, {14.00, 15.00, 16.00 }, {17.00, 18.00, 19.00 }, {20.00, 21.00, 22.00 } });
    assertTrue(answer.equals(pm.plusminus(D1x1,F4x3, +1)));
  }

  @Test
  public static void FullMinusScalarDiag() {
    OGDoubleArray answer = new OGDoubleArray(new double[][] { {-9.00, -8.00, -7.00 }, {-6.00, -5.00, -4.00 }, {-3.00, -2.00, -1.00 }, {0.00, 1.00, 2.00 } });
    assertTrue(answer.equals(pm.plusminus(D1x1,F4x3, -1)));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteRows() {
    pm.plusminus(new OGDiagonalArray(_datadiag, 3, 3),F4x3, +1);
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteCols() {
    pm.plusminus(new OGDiagonalArray(_datadiag, 4, 23),F4x3, +1);
  }

  @Test
  public static void FullPlusDiag() {
    OGDoubleArray answer = new OGDoubleArray(new double[][] { {11.00, 2.00, 3.00 }, {4.00, 25.00, 6.00 }, {7.00, 8.00, 39.00 }, {10.00, 11.00, 12.00 } });
    assertTrue(answer.equals(pm.plusminus(D4x3,F4x3, +1)));
  }

  @Test
  public static void FullMinusDiag() {
    OGDoubleArray answer = new OGDoubleArray(new double[][] { {-9.00, 2.00, 3.00 }, {4.00, -15.00, 6.00 }, {7.00, 8.00, -21.00 }, {10.00, 11.00, 12.00 } });
    assertTrue(answer.equals(pm.plusminus(D4x3,F4x3, -1)));
  }
}
