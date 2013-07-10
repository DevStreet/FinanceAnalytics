/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGComplexSparseMatrix;

/**
 * test copy OGComplexSparseMatrix
 */
public class OGComplexSparseMatrixCopyTest {

  @Test
  public void copyTest() {
    OGComplexSparseMatrix foo = new OGComplexSparseMatrix(new double[][] {{1, 0},{0, 4 }}, new double[][] {{0, 20},{ 0, 40 }});
    CopyOGComplexSparseMatrix copy = new CopyOGComplexSparseMatrix();
    assertTrue(foo.fuzzyequals(copy.eval(foo), 1e-14));

  }

}
