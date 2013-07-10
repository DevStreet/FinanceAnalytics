/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.power;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests Power sparse sparse
 */
@Test(groups = TestGroup.UNIT)
public class PowerOGSparseMatrixOGSparseMatrixTest {

  PowerOGSparseMatrixOGSparseMatrix power = new PowerOGSparseMatrixOGSparseMatrix();

  OGSparseMatrix Single = new OGSparseMatrix(new double[][] {{10 } });
  OGSparseMatrix A = new OGSparseMatrix(new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } });
  OGMatrix B = new OGMatrix(new double[][] { {10., 0., 30. }, {0., 50., 0. }, {0., 0., 90. }, {100., 0., 0. } });
  OGSparseMatrix C = new OGSparseMatrix(new double[][] { {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. }, {1., 0., 3. } });
  OGSparseMatrix D = new OGSparseMatrix(new double[][] { {0., 0., 0. }, {0., 0., 0. }, {-10., 0., 0. }, {-1., 0., -3. } });

  OGMatrix foo = new OGMatrix(new double[][] { {1., 0., 1. }, {1., 1., 0. }, {0., 1., 1. }, {10., 1., 0. } });

  @Test
  public void ScalarPowerMatrixTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {10., 1., 1000. }, {1., 100000., 1. }, {1., 1., 1000000000. }, {10000000000., 1., 1. } });
    assertTrue(answer.fuzzyequals(DOGMA.full(power.eval(Single, A)), 10 * D1mach.four()));
  }

  @Test
  public void MatrixPowerScalarTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {1., 0., 59049. }, {0., 9765625., 0. }, {0., 0., 3486784401. }, {10000000000., 0., 0. } });
    assertTrue(answer.fuzzyequals(DOGMA.full(power.eval(A, Single)), 10 * D1mach.four()));
  }

  @Test
  public void MatrixPowerMatrixTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {1., 0., 1. }, {1., 1., 0. }, {0., 1., 1. }, {10., 1., 0. } });
    assertTrue(answer.fuzzyequals(DOGMA.full(power.eval(A, C)), 10 * D1mach.four()));
  }

  @Test
  public void MatrixPowerMatrixMoreBranchesTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {1., 1., 1. }, {1., 1., 1. }, {Double.POSITIVE_INFINITY, 1., 1. }, {0.1000000000000000, 1., Double.POSITIVE_INFINITY } });
    assertTrue(answer.fuzzyequals(DOGMA.full(power.eval(A, D)), 10 * D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void MatrixPowerMatrixNonConformanceRowsTest() {
    power.eval(A, new OGSparseMatrix(new double[][] { {1., 0. }, {0., 5. } }));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void MatrixPowerMatrixNonConformanceColsTest() {
    power.eval(A, new OGSparseMatrix(new double[][] { {1., 0. }, {1., 0. }, {1., 0. }, {0., 5. } }));
  }

}
