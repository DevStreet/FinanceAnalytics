/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.copy;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGComplexDiagonalMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.copy.CopyOGComplexDiagonalMatrix;

/**
 * test copy OGComplexDiagonalMatrix
 */
public class OGComplexDiagonalMatrixCopyTest {

  @Test
  public void copyTest() {

    OGComplexDiagonalMatrix foo = new OGComplexDiagonalMatrix(new double[] {1, 2, 3, 4, 5 }, new double[] {6, 7, 8, 9, 10 }, 5, 9);
    CopyOGComplexDiagonalMatrix copy = new CopyOGComplexDiagonalMatrix();
    assertTrue(foo.fuzzyequals(copy.eval(foo), 1e-14));

  }
}
