/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;

/**
 * test copy ogmatrix
 */
public class OGMatrixCopyTest {

  @Test
  public void copyTest() {
    OGMatrix foo = new OGMatrix(new double[] {1, 2, 3, 4 }, 2, 2);
    CopyOGMatrix copy = new CopyOGMatrix();
    assertTrue(foo.fuzzyequals(copy.eval(foo), 1e-14));

  }

}
