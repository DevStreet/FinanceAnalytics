/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexMatrix;

/**
 * test copy OGIndexMatrix
 */
public class OGIndexMatrixCopyTest {

  @Test
  public void copyTest() {
    OGIndexMatrix foo = new OGIndexMatrix(new int[] {1, 2, 3, 4 }, 2, 2);
    CopyOGIndexMatrix copy = new CopyOGIndexMatrix();
    assertTrue(foo.fuzzyequals(copy.eval(foo), 1e-14));

  }

}
