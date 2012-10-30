/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;

/**
 * Tests full(OGDoubleArray) 
 */
public class DOGMAOGComplexArrayFullTest {

  private static FullOGComplexMatrix f = FullOGComplexMatrix.getInstance();

  @Test
  public static void fullTest() {
    double[][] realdata = new double[][] { {1.00, 2.00, 3.00 }, {4.00, 5.00, 6.00 }, {7.00, 8.00, 9.00 }, {10.00, 11.00, 12.00 } };
    double[][] imagdata = new double[][] { {10.00, 20.00, 30.00 }, {40.00, 50.00, 60.00 }, {70.00, 80.00, 90.00 }, {100.00, 110.00, 120.00 } };
    OGComplexMatrix answer = new OGComplexMatrix(realdata, imagdata);
    OGComplexMatrix p = new OGComplexMatrix(realdata, imagdata);
    assertTrue(answer.equals(f.full(p)));
  }
}
