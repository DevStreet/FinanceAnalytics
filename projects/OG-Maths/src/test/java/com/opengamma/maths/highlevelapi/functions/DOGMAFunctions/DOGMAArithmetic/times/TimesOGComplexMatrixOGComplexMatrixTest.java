/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests full mtimes full
 */
@Test(groups = TestGroup.UNIT)
public class TimesOGComplexMatrixOGComplexMatrixTest {

  TimesOGComplexMatrixOGComplexMatrix times = new TimesOGComplexMatrixOGComplexMatrix();
  double[][] data0 = new double[][] { {0.1, 0.2, 0.3 }, {0.4, 0.5, 0.6 }, {0.7, 0.8, 0.9 }, {1.0, 1.1, 1.2 } };
  double[][] data1 = new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } };
  double[][] data2 = new double[][] { {10., 20., 30. }, {40., 50., 60. }, {70., 80., 90. }, {100., 110., 120. } };
  double[][] data3 = new double[][] { {1., 4., 7., 10. }, {2., 5., 8., 11. }, {3., 6., 9., 12. } };
  double[][] data4 = new double[][] { {10., 40., 70., 100. }, {20., 50., 80., 110. }, {30., 60., 90., 120. } };
  OGComplexMatrix Single = new OGComplexMatrix(new double[] {10, 20 }, 1, 1);
  OGComplexMatrix A = new OGComplexMatrix(data0, data1);
  OGComplexMatrix B = new OGComplexMatrix(data1, data2);
  OGComplexMatrix C = new OGComplexMatrix(data3, data4);
  double[][] rp, ip;

  @Test
  public void ScalarMtimesMatrixTest() {
    rp = new double[][] { {-19., -38., -57. }, {-76., -95., -114. }, {-133., -152., -171. }, {-190., -209., -228. } };
    ip = new double[][] { {12., 24., 36. }, {48., 60., 72. }, {84., 96., 108. }, {120., 132., 144. } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(times.eval(Single, A), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesScalarTest() {
    rp = new double[][] { {-19., -38., -57. }, {-76., -95., -114. }, {-133., -152., -171. }, {-190., -209., -228. } };
    ip = new double[][] { {12., 24., 36. }, {48., 60., 72. }, {84., 96., 108. }, {120., 132., 144. } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(times.eval(A, Single), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesMatrixTest() {
    rp = new double[][] { {-9.9, -39.6, -89.1 }, {-158.4, -247.5, -356.4 }, {-485.1, -633.6, -801.9 }, {-990., -1197.9, -1425.6 } };
    ip = new double[][] { {2., 8., 18. }, {32., 50., 72. }, {98., 128., 162. }, {200., 242., 288. } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(times.eval(A, B), 10 * D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void MatrixMtimesMatrixNonConformanceTest() {
    times.eval(A, C);
  }
}
