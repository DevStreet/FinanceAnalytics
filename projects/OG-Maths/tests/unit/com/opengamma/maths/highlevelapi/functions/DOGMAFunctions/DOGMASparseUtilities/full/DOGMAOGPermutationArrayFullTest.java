/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationMatrix;

/**
 * tests full(permutation array) 
 */
public class DOGMAOGPermutationArrayFullTest {

  private static FullOGPermutationMatrix f = FullOGPermutationMatrix.getInstance();

  @Test
  public static void fullTest() {
    OGMatrix answer = new OGMatrix(new double[][]{{      0.00,      0.00,      0.00,      0.00,      1.00},{      0.00,      0.00,      1.00,      0.00,      0.00},{      0.00,      0.00,      0.00,      1.00,      0.00},{      0.00,      1.00,      0.00,      0.00,      0.00},{      1.00,      0.00,      0.00,      0.00,      0.00}});
    OGPermutationMatrix p = new OGPermutationMatrix(new int[] {4, 2, 3, 1, 0 });
    assertTrue(answer.equals(f.full(p)));
  }
}
