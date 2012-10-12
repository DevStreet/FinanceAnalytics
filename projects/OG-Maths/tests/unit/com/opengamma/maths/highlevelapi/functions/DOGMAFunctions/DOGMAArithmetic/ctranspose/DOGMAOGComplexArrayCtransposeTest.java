/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexArray;


/**
 * Tests OGComplexArray conjugate transpose
 */
public class DOGMAOGComplexArrayCtransposeTest {

  CtransposeOGComplexArray ct = CtransposeOGComplexArray.getInstance();

  int normalRows = 4;
  int normalCols = 3;
  double[] _realdata = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  double[] _imagdata = new double[] {10, -40, 70, -100, 20, -50, 80, -110, 30, -60, 90, -120 };
  OGComplexArray array1 = new OGComplexArray(_realdata, _imagdata, normalRows, normalCols);

  int transposedRows = 3;
  int transposedCols = 4;
  double[] _realdataTransposed = new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
  double[] _imagdataTransposed = new double[] {-10, -20, -30, 40, 50, 60, -70, -80, -90, 100, 110, 120 };
  OGComplexArray array1tranposed = new OGComplexArray(_realdataTransposed, _imagdataTransposed, transposedRows, transposedCols);

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void nullInTest() {
    OGComplexArray tmp = null;
    ct.ctranspose(tmp);
  }

  @Test
  public void testTranspose() {
    assertTrue(array1tranposed.equals(ct.ctranspose(array1)));
  }

}
