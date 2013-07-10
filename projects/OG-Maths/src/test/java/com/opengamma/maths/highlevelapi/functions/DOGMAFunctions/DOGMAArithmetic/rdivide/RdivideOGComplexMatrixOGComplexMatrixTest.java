/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.rdivide;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.rdivide.RdivideOGComplexMatrixOGComplexMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests full rdivide full
 */
@Test(groups = TestGroup.UNIT)
public class RdivideOGComplexMatrixOGComplexMatrixTest {

  RdivideOGComplexMatrixOGComplexMatrix rdivide = new RdivideOGComplexMatrixOGComplexMatrix();
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
  public void ScalarRdivideMatrixTest() {
    rp = new double[][] { {20.7920792079207928, 10.3960396039603964, 6.9306930693069315 }, {5.1980198019801982, 4.1584158415841586, 3.4653465346534658 },
        {2.9702970297029703, 2.5990099009900991, 2.3102310231023102 }, {2.0792079207920793, 1.8901890189018904, 1.7326732673267329 } };
    ip = new double[][] { {-7.9207920792079207, -3.9603960396039604, -2.6402640264026402 }, {-1.9801980198019802, -1.5841584158415842, -1.3201320132013201 },
        {-1.1315417256011315, -0.9900990099009901, -0.8800880088008801 }, {-0.7920792079207921, -0.7200720072007201, -0.6600660066006601 } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(rdivide.eval(Single, A), 10 * D1mach.four()));
  }

  @Test
  public void MatrixRdivideScalarTest() {
    rp = new double[][] { {0.0420000000000000, 0.0840000000000000, 0.1260000000000000 }, {0.1680000000000000, 0.2100000000000000, 0.2520000000000000 },
        {0.2940000000000000, 0.3360000000000000, 0.3779999999999999 }, {0.4200000000000000, 0.4620000000000000, 0.5040000000000000 } };
    ip = new double[][] { {0.0160000000000000, 0.0320000000000000, 0.0480000000000000 }, {0.0640000000000000, 0.0800000000000000, 0.0960000000000000 },
        {0.1120000000000000, 0.1280000000000000, 0.1440000000000000 }, {0.1600000000000000, 0.1760000000000000, 0.1920000000000000 } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(rdivide.eval(A, Single), 10 * D1mach.four()));
  }

  @Test
  public void MatrixRdivideMatrixTest() {
    rp = new double[][] { {0.1000000000000000, 0.1000000000000000, 0.1000000000000000 }, {0.1000000000000000, 0.1000000000000000, 0.1000000000000000 },
        {0.1000000000000000, 0.1000000000000000, 0.1000000000000000 }, {0.1000000000000000, 0.1000000000000000, 0.1000000000000000 } };
    ip = new double[][] { {0.0000000000000000, 0.0000000000000000, 0.0000000000000000 }, {0.0000000000000000, 0.0000000000000000, 0.0000000000000000 },
        {0.0000000000000000, 0.0000000000000000, 0.0000000000000000 }, {0.0000000000000000, 0.0000000000000000, 0.0000000000000000 } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(rdivide.eval(A, B), 10 * D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void MatrixRdivideMatrixNonConformanceTest() {
    rdivide.eval(A, C);
  }
}
