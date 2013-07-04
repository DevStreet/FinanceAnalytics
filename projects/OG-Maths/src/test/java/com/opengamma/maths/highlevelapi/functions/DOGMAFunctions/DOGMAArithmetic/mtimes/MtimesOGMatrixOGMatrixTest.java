/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;


/**
 * tests full mtimes full
 */
@Test
public class MtimesOGMatrixOGMatrixTest {

  MtimesOGMatrixOGMatrix mtimes = new MtimesOGMatrixOGMatrix();

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
    OGMatrix answer = new OGMatrix(new double[][] { {14., 32., 50., 68. }, {32., 77., 122., 167. }, {50., 122., 194., 266. }, {68., 167., 266., 365. } });
    assertTrue(answer.fuzzyequals(mtimes.eval(A, C), 10 * D1mach.four()));
  }
  
  @Test(expectedExceptions=MathsExceptionNonConformance.class)
  public void MatrixMtimesMatrixNonConformanceTest() {
    mtimes.eval(A, B);
  }
}
