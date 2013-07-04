/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.vertcat;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests vertcat on OGMatrices
 */
@Test
public class OGMatrixOGMatrixVertcatTest {

  double[][] A = new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } };
  double[][] B = new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. } };
  double[][] V = new double[][] { {1. }, {2. }, {3. } };
  double[][] H = new double[][] {{1., 2., 3. } };

  OGMatrix Amatrix = new OGMatrix(A);
  OGMatrix Bmatrix = new OGMatrix(B);
  OGMatrix Vmatrix = new OGMatrix(V);
  OGMatrix Hmatrix = new OGMatrix(H);

  VertcatOGMatrixOGMatrix vertcat = new VertcatOGMatrixOGMatrix();

  @Test
  public void vertcatTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. }, {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. } });
    assertTrue(answer.fuzzyequals(vertcat.eval(Amatrix, Bmatrix), 10 * D1mach.four()));
  }

  @Test
  public void vertcatVectorTest() {
    vertcat.eval(Amatrix, Hmatrix);
    OGMatrix answer = new OGMatrix(new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. }, {1., 2., 3. } });
    assertTrue(answer.fuzzyequals(vertcat.eval(Amatrix, Hmatrix), 10 * D1mach.four()));
  }

  @Test(expectedExceptions = MathsExceptionNonConformance.class)
  public void nonConformanceTest() {
    vertcat.eval(Amatrix, Vmatrix);
  }

}
