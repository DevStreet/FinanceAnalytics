/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMASparseUtilities.sparse.SparseOGComplexMatrix;

/**
 * tests complex matrix to complex sparse type
 */
public class DOGMAOGComplexMatrixSparseTest {

  private SparseOGComplexMatrix s_d2s = new SparseOGComplexMatrix();
  double[][] re = new double[][] { {0, 0, 88, 46 }, {0, 28, 57, 0 }, {92, 57, 80, 35 }, {0, 0, 3, 0 }, {0, 74, 0, 0 }, {79, 46, 96, 19 } };
  double[][] im = new double[][] { {0, 81, 26, 92 }, {0, 26, 0, 0 }, {0, 60, 0, 51 }, {31, 0, 56, 0 }, {47, 2, 0, 0 }, {0, 0, 92, 0 } };
  private OGComplexSparseMatrix sparseAnswer = new OGComplexSparseMatrix(re, im);

  @Test
  public void denseToSparseTest() {
    OGComplexMatrix p = new OGComplexMatrix(re, im);
    assertTrue(sparseAnswer.equals(s_d2s.eval(p)));
  }

}
