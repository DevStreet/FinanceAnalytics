/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.fliplr;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.fliplr.FliplrOGMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests fliplr on OGMatrix
 */
@Test(groups = TestGroup.UNIT)
public class DOGMAOGMatrixFliplrTest {

  double[][] A = new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } };
  double[][] V = new double[][] { {1. }, {2. }, {3. } };
  double[][] H = new double[][] {{1., 2., 3. } };

  OGMatrix Amatrix = new OGMatrix(A);
  OGMatrix Vmatrix = new OGMatrix(V);
  OGMatrix Hmatrix = new OGMatrix(H);

  FliplrOGMatrix fliplr = new FliplrOGMatrix();

  @Test
  public void matrixfliplrTest() {
    OGMatrix answer = new OGMatrix(new double[][] { {3., 2., 1. }, {6., 5., 4. }, {9., 8., 7. }, {12., 11., 10. } });
    OGArray<? extends Number> tmp = fliplr.eval(Amatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void vvectorfliplrTest() {
    OGMatrix answer = Vmatrix;
    OGArray<? extends Number> tmp = fliplr.eval(Vmatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void hvectorfliplrTest() {
    OGMatrix answer = new OGMatrix(new double[][] {{3., 2., 1. } });
    OGArray<? extends Number> tmp = fliplr.eval(Hmatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

}
