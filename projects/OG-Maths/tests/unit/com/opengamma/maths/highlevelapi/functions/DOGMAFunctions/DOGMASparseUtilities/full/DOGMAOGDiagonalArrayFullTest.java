/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;

/**
 * tests full(diagonal array)
 */
public class DOGMAOGDiagonalArrayFullTest {

  private static FullOGDiagonalArray f = FullOGDiagonalArray.getInstance();
  
  @Test
  public static void fullTest() {
    OGDiagonalArray p = new OGDiagonalArray(new double[] {1, 2, 3 }, 4, 5);
    OGDoubleArray answer = new OGDoubleArray(new double[] {1.00, 0.00, 0.00, 0.00, 0.00, 2.00, 0.00, 0.00, 0.00, 0.00, 3.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00 }, 4, 5);
    assertTrue(answer.equals(f.full(p)));
  }

}
