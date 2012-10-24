/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;

/**
 * tests full(diagonal array)
 */
public class DOGMAOGSparseArrayFullTest {

  private static FullOGSparseArray f = FullOGSparseArray.getInstance();

  @Test
  public static void fullTest() {
    double[][] data = new double[][] { {1.00, 2.00, 0.00 }, {4.00, 0.00, 6.00 }, {0.00, 8.00, 0.00 }, {0.00, 11.00, 12.00 } };
    OGSparseArray p = new OGSparseArray(data);
    OGDoubleArray answer = new OGDoubleArray(data);
    assertTrue(answer.equals(f.full(p)));
  }

}
