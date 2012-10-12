/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;

/**
 * Tests OGDiagonalArray conjugate transpose
 */
public class DOGMAOGDiagonalArrayCtransposeTest {

  CtransposeOGDiagonalArray t = CtransposeOGDiagonalArray.getInstance();

  int normalRows = 4;
  int normalCols = 3;
  double[] _data = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  OGDiagonalArray array1 = new OGDiagonalArray(_data, normalRows, normalCols);

  int transposedRows = 3;
  int transposedCols = 4;
  OGDiagonalArray array1tranposed = new OGDiagonalArray(_data, transposedRows, transposedCols);

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void nullInTest() {
    OGDiagonalArray tmp = null;
    t.ctranspose(tmp);
  }

  @Test
  public void testCtranspose() {
    assertTrue(array1tranposed.equals(t.ctranspose(array1)));
  }

}
