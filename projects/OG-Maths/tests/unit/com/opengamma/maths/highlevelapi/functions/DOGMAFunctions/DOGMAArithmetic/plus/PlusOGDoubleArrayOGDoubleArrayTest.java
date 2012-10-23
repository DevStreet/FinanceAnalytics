/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;

/**
 * Test plus/minus on DoubleArray/DoubleArray combo
 */
public class PlusOGDoubleArrayOGDoubleArrayTest {
  static double[] _data4x3Scale1 = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  static double[] _data4x3Scale2 = new double[] {10, 40, 70, 100, 20, 50, 80, 110, 30, 60, 90, 120 };
  static OGDoubleArray F4x3Scale1 = new OGDoubleArray(_data4x3Scale1, 4, 3);
  static OGDoubleArray F4x3Scale2 = new OGDoubleArray(_data4x3Scale2, 4, 3);
  static OGDoubleArray F1x1Scale1 = new OGDoubleArray(1);
  static OGDoubleArray F1x1Scale2 = new OGDoubleArray(10);

  // null ptr etc is caught by the function pointer code

  private static PlusOGDoubleArrayOGDoubleArray pm = PlusOGDoubleArrayOGDoubleArray.getInstance();

  @Test
  public static void scalarFullPlusFull() {
    OGDoubleArray answer = new OGDoubleArray(new double[][] { {11.00, 12.00, 13.00 }, {14.00, 15.00, 16.00 }, {17.00, 18.00, 19.00 }, {20.00, 21.00, 22.00 } });
    assertTrue(answer.equals(pm.plusminus(F1x1Scale2, F4x3Scale1, +1)));
  }

  @Test
  public static void scalarFullMinusFull() {
    OGDoubleArray answer = new OGDoubleArray(new double[][] { {-9.00, -8.00, -7.00 }, {-6.00, -5.00, -4.00 }, {-3.00, -2.00, -1.00 }, {0.00, 1.00, 2.00 } });
    assertTrue(answer.equals(pm.plusminus(F1x1Scale2, F4x3Scale1, -1)));
  }

  @Test
  public static void FullPlusScalarFull() {
    OGDoubleArray answer = new OGDoubleArray(new double[][] { {11.00, 12.00, 13.00 }, {14.00, 15.00, 16.00 }, {17.00, 18.00, 19.00 }, {20.00, 21.00, 22.00 } });
    assertTrue(answer.equals(pm.plusminus(F4x3Scale1, F1x1Scale2, +1)));
  }

  @Test
  public static void FullMinusScalarFull() {
    OGDoubleArray answer = new OGDoubleArray(new double[][] { {-9.00, -8.00, -7.00 }, {-6.00, -5.00, -4.00 }, {-3.00, -2.00, -1.00 }, {0.00, 1.00, 2.00 } });
    assertTrue(answer.equals(pm.plusminus(F4x3Scale1, F1x1Scale2, -1)));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteRows() {
    pm.plusminus(new OGDoubleArray(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3, 3), F4x3Scale1, +1);
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteCols() {
    pm.plusminus(new OGDoubleArray(new double[] {1, 2, 3, 4, 5, 6, 7, 8}, 4, 2), F4x3Scale1, +1);
  }

  @Test
  public static void FullPlusFull() {
    OGDoubleArray answer = new OGDoubleArray(new double[][] { {11.00, 22.00, 33.00 }, {44.00, 55.00, 66.00 }, {77.00, 88.00, 99.00 }, {110.00, 121.00, 132.00 } });
    assertTrue(answer.equals(pm.plusminus(F4x3Scale1, F4x3Scale2, +1)));
  }

  @Test
  public static void FullMinusFull() {
    OGDoubleArray answer = new OGDoubleArray(new double[][] { {-9.00, -18.00, -27.00 }, {-36.00, -45.00, -54.00 }, {-63.00, -72.00, -81.00 }, {-90.00, -99.00, -108.00 } });
    assertTrue(answer.equals(pm.plusminus(F4x3Scale1, F4x3Scale2, -1)));
  }

}
