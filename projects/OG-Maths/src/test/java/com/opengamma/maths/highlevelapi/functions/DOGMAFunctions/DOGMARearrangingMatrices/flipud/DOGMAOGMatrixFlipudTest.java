/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.flipud;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests flipud on OGMatrix
 */
@Test(groups = TestGroup.UNIT)
public class DOGMAOGMatrixFlipudTest {

  double[][] A = new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } };
  double[][] V = new double[][] { {1. }, {2. }, {3. } };
  double[][] H = new double[][] {{1., 2., 3. } };

  OGMatrix Amatrix = new OGMatrix(A);
  OGMatrix Vmatrix = new OGMatrix(V);
  OGMatrix Hmatrix = new OGMatrix(H);

  FlipudOGMatrix flipud = new FlipudOGMatrix();

  @Test
  public void matrixflipudTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {10., 11., 12. }, {7., 8., 9. }, {4., 5., 6. }, {1., 2., 3. } });
    OGArray<? extends Number> tmp = flipud.eval(Amatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void vvectorflipudTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {3. }, {2. }, {1. } });
    OGArray<? extends Number> tmp = flipud.eval(Vmatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void hvectorflipudTest() {
    OGMatrix answer = Hmatrix;
    OGArray<? extends Number> tmp = flipud.eval(Hmatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

}
