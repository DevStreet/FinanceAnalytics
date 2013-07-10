/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.mtimes.MtimesOGComplexMatrixOGComplexMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests full mtimes full
 */
@Test(groups = TestGroup.UNIT)
public class MtimesOGComplexMatrixOGComplexMatrixTest {

  MtimesOGComplexMatrixOGComplexMatrix mtimes = new MtimesOGComplexMatrixOGComplexMatrix();
  double[][] data0 = new double[][] { {0.1, 0.2, 0.3 }, {0.4, 0.5, 0.6 }, {0.7, 0.8, 0.9 }, {1.0, 1.1, 1.2 } };
  double[][] data1 = new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } };
  double[][] data2 = new double[][] { {10., 20., 30. }, {40., 50., 60. }, {70., 80., 90. }, {100., 110., 120. } };
  double[][] data3 = new double[][] { {1., 4., 7., 10. }, {2., 5., 8., 11. }, {3., 6., 9., 12. } };
  double[][] data4 = new double[][] { {10., 40., 70., 100. }, {20., 50., 80., 110. }, {30., 60., 90., 120. } };
  OGComplexMatrix Single = new OGComplexMatrix(new double[] {10, 20 }, 1, 1);
  OGComplexMatrix A = new OGComplexMatrix(data0, data1);
  OGComplexMatrix B = new OGComplexMatrix(data1, data2);
  OGComplexMatrix C = new OGComplexMatrix(data3, data4);
  OGComplexMatrix V = new OGComplexMatrix(new double[][] { {1. }, {2. }, {3. } }, new double[][] { {10. }, {20. }, {30. } });
  OGComplexMatrix H = new OGComplexMatrix(new double[][] {{1., 4., 7., 10. } }, new double[][] {{10., 40., 70., 100. } });
  double[][] rp, ip;

  @Test
  public void ScalarMtimesMatrixTest() {
    rp = new double[][] { {-19., -38., -57. }, {-76., -95., -114. }, {-133., -152., -171. }, {-190., -209., -228. } };
    ip = new double[][] { {12., 24., 36. }, {48., 60., 72. }, {84., 96., 108. }, {120., 132., 144. } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(mtimes.eval(Single, A), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesScalarTest() {
    rp = new double[][] { {-19., -38., -57. }, {-76., -95., -114. }, {-133., -152., -171. }, {-190., -209., -228. } };
    ip = new double[][] { {12., 24., 36. }, {48., 60., 72. }, {84., 96., 108. }, {120., 132., 144. } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(mtimes.eval(A, Single), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesVectorTest() {
    rp = new double[][] { {-138.5999999999999943 }, {-316.7999999999999545 }, {-495.0000000000000000 }, {-673.2000000000000455 } };
    ip = new double[][] { {28.0000000000000000 }, {64.0000000000000000 }, {100.0000000000000000 }, {136.0000000000000000 } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(mtimes.eval(A, V), 10 * D1mach.four()));
  }

  @Test
  public void VectorMtimesMatrixTest() {
    rp = new double[][]  {{  -1643.4000000000000909,  -1861.2000000000000455,  -2079.0000000000000000}};
    ip = new double[][] {{    332.0000000000000000,    376.0000000000000000,    420.0000000000000000}};
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(mtimes.eval(H, A), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesMatrixTest() {
    rp = new double[][] { {-138.5999999999999943, -316.7999999999999545, -495., -673.2000000000000455 },
        {-316.7999999999999545, -762.2999999999999545, -1207.8000000000001819, -1653.2999999999999545 }, {-495., -1207.8000000000001819, -1920.5999999999999091, -2633.4000000000000909 },
        {-673.2000000000000455, -1653.2999999999999545, -2633.4000000000000909, -3613.5000000000000000 } };
    ip = new double[][] { {28., 64., 100., 136. }, {64., 154., 244., 334. }, {100., 244., 388., 532. }, {136., 334., 532., 730. } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(mtimes.eval(A, C), 10 * D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void MatrixMtimesMatrixNonConformanceTest() {
    mtimes.eval(A, B);
  }
}
