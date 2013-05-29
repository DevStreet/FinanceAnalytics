/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests full mtimes sparse sparse
 */
@Test(groups = TestGroup.UNIT)
public class MtimesOGSparseMatrixOGSparseMatrixTest {

  MtimesOGSparseMatrixOGSparseMatrix mtimes = new MtimesOGSparseMatrixOGSparseMatrix();

  OGSparseMatrix Single = new OGSparseMatrix(new double[][] {{10 } });
  OGSparseMatrix A = new OGSparseMatrix(new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } });
  OGMatrix B = new OGMatrix(new double[][] { {10., 0., 30. }, {0., 50., 0. }, {0., 0., 90. }, {100., 0., 0. } });
  OGSparseMatrix C = new OGSparseMatrix(new double[][] { {1., 0., 0., 10. }, {0., 5., 0., 0. }, {3., 0., 9., 0. } });
  OGSparseMatrix V = new OGSparseMatrix(new double[][] { {1. }, {0. }, {3 } });
  OGSparseMatrix H = new OGSparseMatrix(new double[][] {{1., 0., 0., 10. } });

  @Test
  public void ScalarMtimesMatrixTest() {
    OGMatrix answer = B;
    assertTrue(answer.fuzzyequals(DOGMA.full(mtimes.eval(Single, A)), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesScalarTest() {
    OGMatrix answer = B;
    assertTrue(answer.fuzzyequals(DOGMA.full(mtimes.eval(A, Single)), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesMatrixTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {10., 0., 27., 10. }, {0., 25., 0., 0. }, {27., 0., 81., 0. }, {10., 0., 0., 100. } });
    assertTrue(answer.fuzzyequals(DOGMA.full(mtimes.eval(A, C)), 10 * D1mach.four()));
  }

  @Test
  public void MatrixMtimesVectorTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {10. }, {0. }, {27. }, {10. } });
    assertTrue(answer.fuzzyequals(DOGMA.full(mtimes.eval(A, V)), 10 * D1mach.four()));
  }

  @Test
  public void VectorMtimesMatrixTest() {
    OGMatrix answer = new OGMatrix(new double[][] {{101., 0., 3. } });
    assertTrue(answer.fuzzyequals(DOGMA.full(mtimes.eval(H, A)), 10 * D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void MatrixMtimesMatrixNonConformanceTest() {
    mtimes.eval(A, new OGSparseMatrix(new double[][] { {1., 0. }, {0., 5. } }));
  }
}
