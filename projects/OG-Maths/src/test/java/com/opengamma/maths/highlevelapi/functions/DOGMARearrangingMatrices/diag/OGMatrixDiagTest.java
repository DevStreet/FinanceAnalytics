/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.diag;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.diag.DiagOGMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests Diag on OGMatrix
 */
@Test(groups = TestGroup.UNIT)
public class OGMatrixDiagTest {

  double[][] A = new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } };
  double[][] B = new double[][] { {1., 2., 3., 4. }, {5., 6., 7., 8. }, {9., 10., 11., 12. } };
  double[][] V = new double[][] { {1. }, {2. }, {3. } };
  double[][] H = new double[][] {{1., 2., 3. } };

  OGMatrix Amatrix = new OGMatrix(A);
  OGMatrix Bmatrix = new OGMatrix(B);
  OGMatrix Vmatrix = new OGMatrix(V);
  OGMatrix Hmatrix = new OGMatrix(H);

  DiagOGMatrix diag = new DiagOGMatrix();

  @Test
  public void matrix1diagTest() {
    OGMatrix answer = new OGMatrix(new double[] {1., 5., 9. }, 3, 1);
    OGArray<? extends Number> tmp = diag.eval(Amatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void matrix2diagTest() {
    OGMatrix answer = new OGMatrix(new double[] {1., 6., 11. }, 3, 1);
    OGArray<? extends Number> tmp = diag.eval(Bmatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void vvectordiagTest() {
    OGDiagonalMatrix answer = new OGDiagonalMatrix(new double[] {1,2,3});
    OGArray<? extends Number> tmp = diag.eval(Vmatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void hvectordiagTest() {
    OGDiagonalMatrix answer = new OGDiagonalMatrix(new double[] {1,2,3});
    OGArray<? extends Number> tmp = diag.eval(Hmatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

}
