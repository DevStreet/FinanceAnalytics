/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.OGPermutationMatrix;

/**
 * test copy OGPermutationMatrix
 */
public class OGPermutationMatrixCopyTest {

  @Test
  public void copyTest() {
    OGPermutationMatrix foo = new OGPermutationMatrix(new int[] {0, 1, 2, 3 });
    CopyOGPermutationMatrix copy = new CopyOGPermutationMatrix();
    assertTrue(foo.fuzzyequals(copy.eval(foo), 1e-14));

  }

}
