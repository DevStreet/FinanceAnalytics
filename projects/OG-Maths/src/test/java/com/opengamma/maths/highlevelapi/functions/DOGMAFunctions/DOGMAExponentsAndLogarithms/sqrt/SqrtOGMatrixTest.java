/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAExponentsAndLogarithms.sqrt;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests sqrt of an OGMatrix 
 */
@Test(groups = TestGroup.UNIT)
public class SqrtOGMatrixTest {

  SqrtOGMatrix sqrt = new SqrtOGMatrix();

  double[] dataReal = new double[] {1.00, 4.00, 7.00, 10.00, 2.00, 5.00, 8.00, 11.00, 3.00, 6.00, 9.00, 12.00 };
  double[] dataSomeComplex = new double[] {-5.00, -2.00, 1.00, 4.00, -4.00, -1.00, 2.00, 5.00, -3.00, 0.00, 3.00, 6.00 };
  double[] dataAllComplex = new double[] {-1.00, -4.00, -7.00, -10.00, -2.00, -5.00, -8.00, -11.00, -3.00, -6.00, -9.00, -12.00 };
  OGMatrix realMatrix = new OGMatrix(dataReal, 4, 3);
  OGMatrix someComplexMatrix = new OGMatrix(dataSomeComplex, 4, 3);
  OGMatrix allComplexMatrix = new OGMatrix(dataAllComplex, 4, 3);

  double[][] rp, ip;

  @Test
  public void testAllReal() {
    OGMatrix answer = new OGMatrix(new double[][] { {1., 1.4142135623730951, 1.7320508075688772 }, {2., 2.2360679774997898, 2.4494897427831779 }, {2.6457513110645907, 2.8284271247461903, 3. },
        {3.1622776601683795, 3.3166247903553998, 3.4641016151377544 } });
    OGArray<? extends Number> tmp = sqrt.eval(realMatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void testSomeComplex() {
    rp = new double[][] { {0., 0., 0. }, {0., 0., 0. }, {1., 1.4142135623730951, 1.7320508075688772 }, {2., 2.2360679774997898, 2.4494897427831779 } };
    ip = new double[][] { {2.2360679774997898, 2., 1.7320508075688772 }, {1.4142135623730951, 1., 0. }, {0., 0., 0. }, {0., 0., 0. } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    OGArray<? extends Number> tmp = sqrt.eval(someComplexMatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void testAllComplex() {
    rp = new double[][] { {0., 0., 0. }, {0., 0., 0. }, {0., 0., 0. }, {0., 0., 0. } };
    ip = new double[][] { {1., 1.4142135623730951, 1.7320508075688772 }, {2., 2.2360679774997898, 2.4494897427831779 }, {2.6457513110645907, 2.8284271247461903, 3. },
        {3.1622776601683795, 3.3166247903553998, 3.4641016151377544 } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    OGArray<? extends Number> tmp = sqrt.eval(allComplexMatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }
}
