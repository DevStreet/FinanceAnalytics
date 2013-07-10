/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.diag;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.diag.DiagOGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests Diag on OGMatrix
 */
@Test(groups = TestGroup.UNIT)
public class OGSparseMatrixDiagTest {

  double[][] A = new double[][] { {1., 0., 3. }, {0., 5., 0. }, {0., 0., 9. }, {10., 0., 0. } };
  double[][] B = new double[][] { {1., 0., 3., 0. }, {5., 0., 0., 0. }, {9., 10., 0., 0. }};
  double[][] V = new double[][] { {1. }, {2. }, {3. } };
  double[][] H = new double[][] {{1., 0., 3. } };

  OGSparseMatrix Amatrix = new OGSparseMatrix(A);
  OGSparseMatrix Bmatrix = new OGSparseMatrix(B);
  OGSparseMatrix Vmatrix = new OGSparseMatrix(V);
  OGSparseMatrix Hmatrix = new OGSparseMatrix(H);

  DiagOGSparseMatrix diag = new DiagOGSparseMatrix();

  @Test
  public void matrix1diagTest() {
    OGMatrix answer = new OGMatrix(new double[] {1., 5., 9. }, 3, 1);
    OGArray<? extends Number> tmp = diag.eval(Amatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void matrix2diagTest() {
    OGMatrix answer = new OGMatrix(new double[] {1., 0., 0. }, 3, 1);
    OGArray<? extends Number> tmp = diag.eval(Bmatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

    @Test
    public void vvectordiagTest() {
      OGDiagonalMatrix answer = new OGDiagonalMatrix(new double[] {1, 2, 3 });
      OGArray<? extends Number> tmp = diag.eval(Vmatrix);
      assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
    }
    
    
  @Test
  public void hvectordiagTest() {
    OGDiagonalMatrix answer = new OGDiagonalMatrix(new double[] {1, 0, 3 });
    OGArray<? extends Number> tmp = diag.eval(Hmatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

}
