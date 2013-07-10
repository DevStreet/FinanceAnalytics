/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.mtimes;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.mtimes.MtimesOGSparseMatrixOGMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests full mtimes sparse/full
 */
@Test(groups = TestGroup.UNIT)
public class MtimesOGSparseMatrixOGMatrixTest {

  MtimesOGSparseMatrixOGMatrix mtimes = new MtimesOGSparseMatrixOGMatrix();

  OGSparseMatrix SingleSparse = new OGSparseMatrix(new double[][] {{10 } });
  OGMatrix SingleFull = new OGMatrix(new double[][] {{10 } });
  OGSparseMatrix ASparse = new OGSparseMatrix(new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } });
  OGMatrix AFull = new OGMatrix(new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } });
  OGMatrix B = new OGMatrix(new double[][] { {10., 0., 30. }, {0., 50., 0. }, {0., 0., 90. }, {100., 0., 0. } });
  OGMatrix C = new OGMatrix(new double[][] { {1., 0., 0., 10. }, {0., 5., 0., 0. }, {3., 0., 9., 0. } });
  OGMatrix V = new OGMatrix(new double[][] { {1. }, {0. }, {3 } });
  OGSparseMatrix H = new OGSparseMatrix(new double[][] {{1., 0., 0., 10. } });

  @Test
  public void ScalarMtimesMatrixTest() {
    OGMatrix answer = B;
    assertTrue(answer.fuzzyequals(DOGMA.full(mtimes.eval(SingleSparse, AFull)), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesScalarTest() {
    OGMatrix answer = B;
    assertTrue(answer.fuzzyequals(DOGMA.full(mtimes.eval(ASparse, SingleFull)), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesMatrixTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {10., 0., 27., 10. }, {0., 25., 0., 0. }, {27., 0., 81., 0. }, {10., 0., 0., 100. } });
    assertTrue(answer.fuzzyequals(DOGMA.full(mtimes.eval(ASparse, C)), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesVectorTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {10. }, {0. }, {27. }, {10. } });
    assertTrue(answer.fuzzyequals(DOGMA.full(mtimes.eval(ASparse, V)), 10 * D1mach.four()));
  }

  @Test
  public void VectorMtimesMatrixTest() {
    OGMatrix answer = new OGMatrix(new double[][] {{101., 0., 3. } });
    assertTrue(answer.fuzzyequals(DOGMA.full(mtimes.eval(H, AFull)), 10 * D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void MatrixMtimesMatrixNonConformanceTest() {
    mtimes.eval(ASparse, new OGMatrix(new double[][] { {1., 0. }, {0., 5. } }));
  }
}
