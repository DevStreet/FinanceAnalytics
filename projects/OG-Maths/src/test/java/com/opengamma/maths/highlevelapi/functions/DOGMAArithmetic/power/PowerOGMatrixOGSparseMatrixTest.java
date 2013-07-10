/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.power;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.power.PowerOGMatrixOGSparseMatrix;
import com.opengamma.util.test.TestGroup;

/**
 * Test power on OGMatrix/OGMatrix combo
 */
@Test(groups = TestGroup.UNIT)
public class PowerOGMatrixOGSparseMatrixTest {
  OGSparseMatrix SingleSparse = new OGSparseMatrix(new double[][] {{2 } });
  OGMatrix SingleFull = new OGMatrix(new double[][] {{2 } });
  OGSparseMatrix ASparse = new OGSparseMatrix(new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } });
  OGMatrix AFull = new OGMatrix(new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } });
  OGMatrix B = new OGMatrix(new double[][] { {10., 0., 30. }, {0., 50., 0. }, {0., 0., 90. }, {100., 0., 0. } });
  OGSparseMatrix C = new OGSparseMatrix(new double[][] { {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. }, {1., 0., 3. } });

  // null ptr etc is caught by the function pointer code

  private static PowerOGMatrixOGSparseMatrix power = new PowerOGMatrixOGSparseMatrix();

  @Test
  public void ScalarFullPowerSparse() {
    OGMatrix answer = new OGMatrix(new double[][] { {2., 1., 8. }, {1., 32., 1. }, {1., 1., 512. }, {1024., 1., 1. } });
    assertTrue(answer.equals(power.eval(SingleFull, ASparse)));
  }

  @Test
  public void FullPowerScalarSparse() {
    OGMatrix answer = new OGMatrix(new double[][] { {1., 0., 9. }, {0., 25., 0. }, {0., 0., 81. }, {100., 0., 0. } });
    assertTrue(answer.equals(power.eval(AFull, SingleSparse)));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void BadCommuteRows() {
    power.eval(new OGMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3, 3), ASparse);
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void BadCommuteCols() {
    power.eval(new OGMatrix(new double[] {1, 2, 3, 4, 5, 6, 7, 8 }, 4, 2), ASparse);
  }

  @Test
  public void FullPowerFullSparse() {
    OGMatrix answer = new OGMatrix(new double[][] { {1., 0., 1. }, {1., 1., 0. }, {0., 1., 1. }, {10., 1., 0. } });
    assertTrue(answer.equals(power.eval(AFull, C)));
  }

}
