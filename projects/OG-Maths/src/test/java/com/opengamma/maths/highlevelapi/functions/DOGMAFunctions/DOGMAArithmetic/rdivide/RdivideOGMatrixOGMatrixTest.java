/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.rdivide;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * Tests Rdivide OGMatrix OGMatrix
 */
@Test(groups = TestGroup.UNIT)
public class RdivideOGMatrixOGMatrixTest {

  RdivideOGMatrixOGMatrix rdivide = new RdivideOGMatrixOGMatrix();

  OGMatrix Single = new OGMatrix(10);
  OGMatrix A = new OGMatrix(new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } });
  OGMatrix B1 = new OGMatrix(new double[][] { {10.0, 5.0, 3.3333333333333335 }, {2.5, 2.0, 1.6666666666666667 }, {1.4285714285714286, 1.25, 1.1111111111111112 },
      {1.0, 0.9090909090909091, 0.8333333333333334 } });
  OGMatrix B2 = new OGMatrix(new double[][] { {0.10, 0.20, 0.30 }, {0.40, 0.50, 0.60 }, {0.70, 0.80, 0.90 }, {1.00, 1.1, 1.20 } });
  OGMatrix C = new OGMatrix(new double[][] { {1., 4., 7., 10. }, {2., 5., 8., 11. }, {3., 6., 9., 12. } });

  @Test
  public void ScalarRdivideMatrixTest() {
    OGMatrix answer = B1;
    assertTrue(answer.fuzzyequals(rdivide.eval(Single, A), 100 * D1mach.four()));
  }

  @Test
  public void MatrixRdivideScalarTest() {
    OGMatrix answer = B2;
    assertTrue(answer.fuzzyequals(rdivide.eval(A, Single), 100 * D1mach.four()));
  }

  @Test
  public void MatrixRdivideMatrixTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {0.1000000000000000, 0.4000000000000000, 0.8999999999999999 }, {1.6000000000000001, 2.5000000000000000, 3.5999999999999996 },
        {4.8999999999999995, 6.4000000000000004, 8.0999999999999996 }, {10.0000000000000000, 12.0999999999999996, 14.3999999999999986 } });
    assertTrue(answer.fuzzyequals(rdivide.eval(A, B1), 10 * D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void MatrixRdivideMatrixNonConformanceTest() {
    rdivide.eval(A, C);
  }

}
