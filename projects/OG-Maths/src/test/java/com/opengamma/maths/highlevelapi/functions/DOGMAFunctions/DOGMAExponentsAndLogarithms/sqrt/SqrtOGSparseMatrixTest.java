/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAExponentsAndLogarithms.sqrt;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests sqrt of an OGMatrix 
 */
@Test(groups = TestGroup.UNIT)
public class SqrtOGSparseMatrixTest {

  SqrtOGSparseMatrix sqrt = new SqrtOGSparseMatrix();

  double[][] dataReal = new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } };
  double[][] dataSomeComplex = new double[][] { {-5., 0., -3. }, {0., -1., 0. }, {0., 0., 3. }, {4., 0., 0. } };
  double[][] dataAllComplex = new double[][] { {-1., 0., -3. }, {0., -5., 0. }, {0., 0., -9. }, {-10., 0., 0. } };
  OGSparseMatrix realMatrix = new OGSparseMatrix(dataReal);
  OGSparseMatrix someComplexMatrix = new OGSparseMatrix(dataSomeComplex);
  OGSparseMatrix allComplexMatrix = new OGSparseMatrix(dataAllComplex);

  double[][] rp, ip;

  @Test
  public void testAllReal() {
    OGSparseMatrix answer = new OGSparseMatrix(new double[][] { {1., 0., 1.7320508075688772 }, {0., 2.2360679774997898, 0. }, {0., 0., 3. }, {3.1622776601683795, 0., 0. } });
    OGArray<? extends Number> tmp = sqrt.eval(realMatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void testSomeComplex() {
    rp = new double[][] { {0., 0., 0. }, {0., 0., 0. }, {0., 0., 1.7320508075688772 }, {2., 0., 0. } };
    ip = new double[][] { {2.2360679774997898, 0., 1.7320508075688772 }, {0., 1., 0. }, {0., 0., 0. }, {0., 0., 0. } };
    OGComplexSparseMatrix answer = new OGComplexSparseMatrix(rp, ip);
    OGArray<? extends Number> tmp = sqrt.eval(someComplexMatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void testAllComplex() {
    rp = new double[][] { {0., 0., 0. }, {0., 0., 0. }, {0., 0., 0. }, {0., 0., 0. } };
    ip = new double[][] { {1., 0., 1.7320508075688772 }, {0., 2.2360679774997898, 0. }, {0., 0., 3. }, {3.1622776601683795, 0., 0. } };
    OGComplexSparseMatrix answer = new OGComplexSparseMatrix(rp, ip);
    OGArray<? extends Number> tmp = sqrt.eval(allComplexMatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }
}
