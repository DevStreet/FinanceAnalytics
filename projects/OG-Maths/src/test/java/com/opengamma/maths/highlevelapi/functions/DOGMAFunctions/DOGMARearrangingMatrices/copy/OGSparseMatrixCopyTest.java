/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * test copy OGSparseMatrix
 */
public class OGSparseMatrixCopyTest {

  @Test
  public void copyTest() {
    OGSparseMatrix foo = new OGSparseMatrix(new double[][] {{1, 0},{0, 4 }});
    CopyOGSparseMatrix copy = new CopyOGSparseMatrix();
    assertTrue(foo.fuzzyequals(copy.eval(foo), 1e-14));

  }

}
