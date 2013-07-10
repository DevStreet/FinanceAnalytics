/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.times.TimesOGMatrixOGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests full mtimes full/sparse
 */
@Test(groups = TestGroup.UNIT)
public class TimesOGMatrixOGSparseMatrixTest {

  TimesOGMatrixOGSparseMatrix mtimes = new TimesOGMatrixOGSparseMatrix();

  OGSparseMatrix SingleSparse = new OGSparseMatrix(new double[][] {{10 } });
  OGMatrix SingleFull = new OGMatrix(new double[][] {{10 } });
  OGSparseMatrix ASparse = new OGSparseMatrix(new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } });
  OGMatrix AFull = new OGMatrix(new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } });
  OGMatrix B = new OGMatrix(new double[][] { {10., 0., 30. }, {0., 50., 0. }, {0., 0., 90. }, {100., 0., 0. } });
  OGSparseMatrix C = new OGSparseMatrix(new double[][] { {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. }, {1., 0., 3. } });

  @Test
  public void ScalarMtimesMatrixTest() {
    OGMatrix answer = B;
    assertTrue(answer.fuzzyequals(DOGMA.full(mtimes.eval(SingleFull, ASparse)), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesScalarTest() {
    OGMatrix answer = B;
    assertTrue(answer.fuzzyequals(DOGMA.full(mtimes.eval(AFull, SingleSparse)), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesMatrixTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {0., 0., 0. }, {0., 0., 0. }, {0., 0., 0. }, {10., 0., 0. } });
    assertTrue(answer.fuzzyequals(DOGMA.full(mtimes.eval(AFull, C)), 10 * D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void MatrixMtimesMatrixNonConformanceTest() {
    mtimes.eval(AFull, new OGSparseMatrix(new double[][] { {1., 0. }, {0., 5. } }));
  }
}
