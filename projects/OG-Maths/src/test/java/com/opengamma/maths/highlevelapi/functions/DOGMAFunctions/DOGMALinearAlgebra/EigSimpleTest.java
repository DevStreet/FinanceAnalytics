/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMALinearAlgebra.eig.EIGCompute;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * Simple eig() test
 */
@Test(groups = TestGroup.UNIT)
public class EigSimpleTest {

  private static double[][] s_data = new double[][] { {1, 2, 3 }, {4, 5, 6 }, {7, 8, 9 } };
  private static double[][] s_data2 = new double[][] { {1, 4, 5, }, {-9, -8, -9 }, {5, -3, -6 } };
  private static OGMatrix A_real_matrix = new OGMatrix(s_data);
  OGMatrix A_realMatrixWithComplexEigs = new OGMatrix(s_data2);

  @Test
  public void eigDOGMATest() {
    OGMatrix answer = new OGMatrix(new double[] {16.1168439698070394, -1.1168439698070438, -0.0000000000000003 }, 3, 1);
    OGArray<? extends Number> result = DOGMA.eig(A_real_matrix, EIGCompute.LAMBDA).get(0);
    assertTrue(answer.fuzzyequals(result, 10 * D1mach.four()));
  }

  @Test
  public void eigvectorsDOGMATest() {
    OGMatrix answer = new OGMatrix(new double[] {-0.2319706872462862, -0.5253220933012336, -0.8186734993561815, -0.7858302387420674, -0.0867513392566281, 0.6123275602288101, 0.4082482904638629,
        -0.8164965809277261, 0.4082482904638631 }, 3, 3);
    OGArray<? extends Number> result = DOGMA.eig(A_real_matrix, EIGCompute.V).get(0);
    assertTrue(answer.fuzzyequals(result, 10 * D1mach.four()));
  }

  @Test
  public void eigAllDOGMATest() {
    List<OGArray<? extends Number>> result = DOGMA.eig(A_real_matrix, EIGCompute.V_LAMBDA);
    OGArray<? extends Number> v = result.get(0);
    OGArray<? extends Number> lambda = result.get(1);
    assertTrue(DOGMA.full(DOGMA.mtimes(A_real_matrix, v)).fuzzyequals(DOGMA.full(DOGMA.mtimes(v, lambda)), 100 * D1mach.four()));
  }
  
  @Test
  public void eigComplexDOGMATest() {
    OGComplexMatrix answer = new OGComplexMatrix(new double[] {-0.6207512864450693, 1.7367967731711886, -0.6207512864450693, -1.7367967731711886, -11.7584974271098606, 0.0000000000000000 }, 3, 1);
    OGArray<? extends Number> result = DOGMA.eig(A_realMatrixWithComplexEigs, EIGCompute.LAMBDA).get(0);
    assertTrue(answer.fuzzyequals(result, 10 * D1mach.four()));
  }

  @Test
  public void eigvectorsComplexDOGMATest() {
    OGComplexMatrix answer = new OGComplexMatrix(new double[] {-0.1127441335053914, -0.1729903294627811, 0.8087899596015609, 0.0000000000000000, -0.5503961185974930, 0.0169121303506426,
        -0.1127441335053914, 0.1729903294627811, 0.8087899596015609, -0.0000000000000000, -0.5503961185974930, -0.0169121303506426, -0.4484860766496026, 0.0000000000000000, 0.5715419080822635,
        0.0000000000000000, 0.6871681645398979, 0.0000000000000000 }, 3, 3);
    OGArray<? extends Number> result = DOGMA.eig(A_realMatrixWithComplexEigs, EIGCompute.V).get(0);
    assertTrue(answer.fuzzyequals(result, 10 * D1mach.four()));
  }

  @Test
  public void eigComplexAllDOGMATest() {
    List<OGArray<? extends Number>> result = DOGMA.eig(A_realMatrixWithComplexEigs, EIGCompute.V_LAMBDA);
    OGArray<? extends Number> v = result.get(0);
    OGArray<? extends Number> lambda = result.get(1);
    assertTrue(DOGMA.full(DOGMA.mtimes(A_realMatrixWithComplexEigs, v)).fuzzyequals(DOGMA.full(DOGMA.mtimes(v, lambda)), 100 * D1mach.four()));
  }

}
