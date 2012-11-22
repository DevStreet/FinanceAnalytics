/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;


/**
 * tests sparse on diag type
 */
public class DOGMAOGDiagonalMatrixSparseTest {

  private static SparseOGDiagonalMatrix s_d2s = new SparseOGDiagonalMatrix();
  static double[] _data = new double[] {1, 2, 3, 4, 5 };
  private static double[][] denseAnswer = { {1.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000 },
      {0.0000000000000000, 2.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000 },
      {0.0000000000000000, 0.0000000000000000, 3.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000 },
      {0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 4.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000 },
      {0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 5.0000000000000000, 0.0000000000000000, 0.0000000000000000 },
      {0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000, 0.0000000000000000 } };

  private static OGSparseMatrix sparseAnswer = new OGSparseMatrix(denseAnswer);

  @Test
  public static void diagToSparseTest() {
    OGDiagonalMatrix p = new OGDiagonalMatrix(_data, 6, 7);
    assertTrue(sparseAnswer.equals(s_d2s.eval(p)));
  }

}
