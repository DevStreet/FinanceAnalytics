/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.copy;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.copy.CopyOGComplexMatrix;

/**
 * test copy ogcomplexmatrix
 */
public class OGComplexMatrixCopyTest {

  @Test
  public void copyTest() {
    OGComplexMatrix foo = new OGComplexMatrix(new double[] {1, 2, 3, 4 }, new double[] {10, 20, 30, 40 }, 2, 2);
    CopyOGComplexMatrix copy = new CopyOGComplexMatrix();
    assertTrue(foo.fuzzyequals(copy.eval(foo), 1e-14));

  }

}
