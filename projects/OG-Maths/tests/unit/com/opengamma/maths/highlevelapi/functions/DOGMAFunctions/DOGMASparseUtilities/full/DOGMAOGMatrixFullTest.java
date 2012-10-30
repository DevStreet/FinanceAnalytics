/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * Tests full(OGDoubleArray) 
 */
public class DOGMAOGMatrixFullTest {

  private static FullOGMatrix f = FullOGMatrix.getInstance();

  @Test
  public static void fullTest() {
    double[] data=new double[] {1.00, 4.00, 7.00, 10.00, 2.00, 5.00, 8.00, 11.00, 3.00, 6.00, 9.00, 12.00 };
    OGMatrix answer = new OGMatrix(data, 4, 3);
    OGMatrix p = new OGMatrix(data, 4, 3);    
    assertTrue(answer.equals(f.full(p)));
  }
}
