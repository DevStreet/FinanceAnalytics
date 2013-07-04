/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.diag;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;


/**
 * tests Diag on OGMatrix
 */
@Test
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
