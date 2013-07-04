/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.rdivide;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests full rdivide full/sparse
 */
@Test
public class RdivideOGSparseMatrixOGMatrixTest {

  RdivideOGSparseMatrixOGMatrix rdivide = new RdivideOGSparseMatrixOGMatrix();

  OGSparseMatrix SingleSparse = new OGSparseMatrix(new double[][] {{10 } });
  OGMatrix SingleFull = new OGMatrix(new double[][] {{10 } });
  OGSparseMatrix ASparse = new OGSparseMatrix(new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } });
  OGMatrix AFull = new OGMatrix(new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } });
  OGMatrix B = new OGMatrix(new double[][] { {10., 0., 30. }, {0., 50., 0. }, {0., 0., 90. }, {100., 0., 0. } });
  OGSparseMatrix CSparse = new OGSparseMatrix(new double[][] { {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. }, {1., 0., 3. } });
  OGMatrix CFull = new OGMatrix(new double[][] { {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. }, {1., 0., 3. } });

  @Test
  public void ScalarRdivideMatrixTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {10.0000000000000000, Double.POSITIVE_INFINITY, 3.3333333333333335 }, {Double.POSITIVE_INFINITY, 2.0000000000000000, Double.POSITIVE_INFINITY },
        {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1.1111111111111112 }, {1.0000000000000000, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY } });
    ;
    assertTrue(answer.fuzzyequals(DOGMA.full(rdivide.eval(SingleSparse, AFull)), 10 * D1mach.four()));
  }

  @Test
  public void MatrixRdivideScalarTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {0.1000000000000000, 0.0000000000000000, 0.3000000000000000 }, {0.0000000000000000, 0.5000000000000000, 0.0000000000000000 },
        {0.0000000000000000, 0.0000000000000000, 0.9000000000000000 }, {1.0000000000000000, 0.0000000000000000, 0.0000000000000000 } });
    assertTrue(answer.fuzzyequals(DOGMA.full(rdivide.eval(ASparse, SingleFull)), 10 * D1mach.four()));
  }

  @Test
  public void MatrixRdivideMatrixTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {Double.POSITIVE_INFINITY, 0.0000000000000000, Double.POSITIVE_INFINITY }, {Double.NaN, Double.POSITIVE_INFINITY, 0.0000000000000000 },
        {0.0000000000000000, Double.NaN, Double.POSITIVE_INFINITY }, {10.0000000000000000, Double.NaN, 0.0000000000000000 } });
    assertTrue(answer.fuzzyequals(DOGMA.full(rdivide.eval(ASparse, CFull)), 10 * D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void MatrixRdivideMatrixNonConformanceTest() {
    rdivide.eval(new OGSparseMatrix(new double[][] { {1., 0. }, {0., 5. } }), AFull);
  }
}
