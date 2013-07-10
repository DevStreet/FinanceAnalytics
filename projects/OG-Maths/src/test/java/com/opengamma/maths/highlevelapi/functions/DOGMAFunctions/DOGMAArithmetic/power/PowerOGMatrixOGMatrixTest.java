/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.power;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.power.PowerOGMatrixOGMatrix;
import com.opengamma.util.test.TestGroup;

/**
 * Test power on OGMatrix/OGMatrix combo
 */
@Test(groups = TestGroup.UNIT)
public class PowerOGMatrixOGMatrixTest {
  static double[] _data4x3Scale1 = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  static double[] _data4x3Scale2 = new double[] {10, 40, 70, 100, 20, 50, 80, 110, 30, 60, 90, 120 };
  static OGMatrix F4x3Scale1 = new OGMatrix(_data4x3Scale1, 4, 3);
  static OGMatrix F4x3Scale2 = new OGMatrix(_data4x3Scale2, 4, 3);
  static OGMatrix F1x1Scale1 = new OGMatrix(2);
  static OGMatrix F1x1Scale2 = new OGMatrix(10);

  // null ptr etc is caught by the function pointer code

  private static PowerOGMatrixOGMatrix power = new PowerOGMatrixOGMatrix();

  @Test
  public static void scalarFullPowerFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {2., 4., 8. }, {16., 32., 64. }, {128., 256., 512. }, {1024., 2048., 4096. } });
    assertTrue(answer.equals(power.eval(F1x1Scale1, F4x3Scale1)));
  }

  @Test
  public static void FullPowerScalarFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {1., 4., 9. }, {16., 25., 36. }, {49., 64., 81. }, {100., 121., 144. } });
    assertTrue(answer.equals(power.eval(F4x3Scale1, F1x1Scale1)));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteRows() {
    power.eval(new OGMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3, 3), F4x3Scale1);
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public static void BadCommuteCols() {
    power.eval(new OGMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8 }, 4, 2), F4x3Scale1);
  }

  @Test
  public static void FullPowerFull() {
    OGMatrix answer = new OGMatrix(new double[][] { {10., 400., 27000. }, {2560000., 312500000., 46656000000. }, {8235430000000., 1677721600000000., 387420489000000000. },
        {100000000000000000000., 28531167061100000706560., 8916100448256000000000000. } });
    assertTrue(answer.equals(power.eval(F4x3Scale2, F4x3Scale1)));
  }

}
