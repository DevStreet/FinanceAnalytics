/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.copy.CopyOGDiagonalMatrix;

/**
 * test copy OGDiagonalMatrix
 */
public class OGDiagonalMatrixCopyTest {

  @Test
  public void copyTest() {
    OGDiagonalMatrix foo = new OGDiagonalMatrix(new double[] {1,2,3,4,5},5,9);
    CopyOGDiagonalMatrix copy = new CopyOGDiagonalMatrix();
    assertTrue(foo.fuzzyequals(copy.eval(foo), 1e-14));

  }

}
