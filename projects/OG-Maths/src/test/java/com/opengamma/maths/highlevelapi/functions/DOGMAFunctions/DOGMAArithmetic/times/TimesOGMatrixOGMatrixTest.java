/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * Tests times OGMatrix OGMatrix
 */
@Test(groups = TestGroup.UNIT)
public class TimesOGMatrixOGMatrixTest {

  TimesOGMatrixOGMatrix mtimes = new TimesOGMatrixOGMatrix();

  OGMatrix Single = new OGMatrix(10);
  OGMatrix A = new OGMatrix(new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } });
  OGMatrix B = new OGMatrix(new double[][] { {10., 20., 30. }, {40., 50., 60. }, {70., 80., 90. }, {100., 110., 120. } });
  OGMatrix C = new OGMatrix(new double[][] { {1., 4., 7., 10. }, {2., 5., 8., 11. }, {3., 6., 9., 12. } });

  @Test
  public void ScalarMtimesMatrixTest() {
    OGMatrix answer = B;
    assertTrue(answer.fuzzyequals(mtimes.eval(Single, A), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesScalarTest() {
    OGMatrix answer = B;
    assertTrue(answer.fuzzyequals(mtimes.eval(A, Single), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesMatrixTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {10., 40., 90. }, {160., 250., 360. }, {490., 640., 810. }, {1000., 1210., 1440. } });
    assertTrue(answer.fuzzyequals(mtimes.eval(A, B), 10 * D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void MatrixMtimesMatrixNonConformanceTest() {
    mtimes.eval(A, C);
  }

}
