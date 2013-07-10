/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.power;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests complex full Power full
 */
@Test(groups = TestGroup.UNIT)
public class PowerOGComplexMatrixOGComplexMatrixTest {

  PowerOGComplexMatrixOGComplexMatrix power = new PowerOGComplexMatrixOGComplexMatrix();
  double[][] data0 = new double[][] { {0.1, 0.2, 0.3 }, {0.4, 0.5, 0.6 }, {0.7, 0.8, 0.9 }, {1.0, 1.1, 1.2 } };
  double[][] data1 = new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } };
  double[][] data2 = new double[][] { {10., 20., 30. }, {40., 50., 60. }, {70., 80., 90. }, {100., 110., 120. } };
  double[][] data3 = new double[][] { {1., 4., 7., 10. }, {2., 5., 8., 11. }, {3., 6., 9., 12. } };
  double[][] data4 = new double[][] { {10., 40., 70., 100. }, {20., 50., 80., 110. }, {30., 60., 90., 120. } };
  OGComplexMatrix Single = new OGComplexMatrix(new double[] {2, 3 }, 1, 1);
  OGComplexMatrix A = new OGComplexMatrix(data0, data1);
  OGComplexMatrix B = new OGComplexMatrix(data1, data2);
  OGComplexMatrix C = new OGComplexMatrix(data3, data4);
  double[][] rp, ip;

  @Test
  public void ScalarPowerMatrixTest() {
    rp = new double[][] { {0.0803726294368825, -0.1681104817520347, -0.0415728200860884 }, {0.0237504069321919, 0.0113436929673260, -0.0024760913251520 },
        {-0.0024515686888827, 0.0000541687712231, 0.0004525148550522 }, {0.0000629334448175, -0.0000718025117280, -0.0000229347549060 } };
    ip = new double[][] { {0.4178160376465139, 0.0671619471330996, -0.0648412730832937 }, {-0.0225812545759001, 0.0081083861106520, 0.0053912691600894 },
        {-0.0006012401879276, -0.0010726279704341, -0.0000635773490347 }, {0.0001839580849995, 0.0000410797975467, -0.0000266985495977 } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(power.eval(Single, A), 10 * D1mach.four()));
  }

  @Test
  public void MatrixPowerScalarTest() {
    rp = new double[][] { {-0.0120278100072717, 0.0155917555834292, 0.1100673062077760 }, {0.1317001766330131, 0.0209325626253578, -0.2028046867702204 },
        {-0.4844823053143246, -0.7625660962579450, -0.9834378137368495 }, {-1.1069054513988232, -1.1070595078250725, -0.9709588961692465 } };
    ip = new double[][] { {0.0022435608389809, -0.0463910079516964, -0.0033214737258765 }, {0.1448403219065644, 0.3051646106246708, 0.3910035349722569 },
        {0.3531442577167155, 0.1779647011082333, -0.1226516908969175 }, {-0.5213234214534311, -0.9829562501353317, -1.4701887318769953 } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(power.eval(A, Single), 10 * D1mach.four()));
  }

  @Test
  public void MatrixPowerMatrixTest() {
    rp = new double[][] { {-0.2236696395131135, 0.0961167743119796, -0.0110882472318674 }, {-0.0114773978710708, 0.0004512850678059, 0.0016153342280783 },
        {0.0005417877327942, 0.0000372937731929, -0.0000530768692767 }, {-0.0000369714645024, -0.0000165882026830, -0.0000061972182965 } };
    ip = new double[][] { {0.1834472539948915, 0.0011972064861044, -0.0317781549643589 }, {0.0041129203595968, 0.0045069828054794, 0.0005820644297196 },
        {-0.0003801549217325, -0.0002561080017431, -0.0000876641329671 }, {-0.0000177996200170, 0.0000004782679236, 0.0000027326767557 } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    assertTrue(answer.fuzzyequals(power.eval(B, A), 10 * D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void MatrixPowerMatrixNonConformanceTest() {
    power.eval(A, C);
  }
}
