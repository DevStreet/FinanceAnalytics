/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.diag;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;
import com.opengamma.util.test.TestGroup;

/**
 * tests Diag on OGMatrix
 */
@Test(groups = TestGroup.UNIT)
public class OGComplexMatrixDiagTest {

  double[][] data0 = new double[][] { {0.1, 0.2, 0.3 }, {0.4, 0.5, 0.6 }, {0.7, 0.8, 0.9 }, {1.0, 1.1, 1.2 } };
  double[][] data1 = new double[][] { {1., 2., 3. }, {4., 5., 6. }, {7., 8., 9. }, {10., 11., 12. } };
  double[][] data2 = new double[][] { {0.1, 0.2, 0.3, 0.4 }, {0.5, 0.6, 0.7, 0.8 }, {0.9, 1.0, 1.1, 1.2 } };
  double[][] data3 = new double[][] { {1., 2., 3., 4. }, {5., 6., 7., 8. }, {9., 10., 11., 12. } };
  double[][] Vr = new double[][] { {1. }, {2. }, {3. } };
  double[][] Hr = new double[][] {{1., 2., 3. } };
  double[][] Vi = new double[][] { {10. }, {20. }, {30. } };
  double[][] Hi = new double[][] {{10., 20., 30. } };
  OGComplexMatrix Single = new OGComplexMatrix(new double[] {10, 20 }, 1, 1);
  OGComplexMatrix Amatrix = new OGComplexMatrix(data0, data1);
  OGComplexMatrix Bmatrix = new OGComplexMatrix(data2, data3);
  OGComplexMatrix Vmatrix = new OGComplexMatrix(Vr, Vi);
  OGComplexMatrix Hmatrix = new OGComplexMatrix(Hr, Hi);
  double[][] rp, ip;

  DiagOGComplexMatrix diag = new DiagOGComplexMatrix();

  @Test
  public void matrix1diagTest() {
    rp = new double[][] { {0.1 }, {0.5 }, {0.9 } };
    ip = new double[][] { {1. }, {5. }, {9. } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    OGArray<? extends Number> tmp = diag.eval(Amatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void matrix2diagTest() {
    rp = new double[][] { {0.1 }, {0.6 }, {1.1 } };
    ip = new double[][] { {1.0 }, {6.0 }, {11.0 } };
    OGComplexMatrix answer = new OGComplexMatrix(rp, ip);
    OGArray<? extends Number> tmp = diag.eval(Bmatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void vvectordiagTest() {
    OGComplexDiagonalMatrix answer = new OGComplexDiagonalMatrix(new double[] {1, 2, 3 }, new double[] {10, 20, 30 });
    OGArray<? extends Number> tmp = diag.eval(Vmatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

  @Test
  public void hvectordiagTest() {
    OGComplexDiagonalMatrix answer = new OGComplexDiagonalMatrix(new double[] {1, 2, 3 }, new double[] {10, 20, 30 });
    OGArray<? extends Number> tmp = diag.eval(Hmatrix);
    assertTrue(answer.fuzzyequals(tmp, 10 * D1mach.four()));
  }

}
