/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * Tests OGDoubleArray transpose
 */
public class DOGMAOGMatrixTransposeTest {

  TransposeOGMatrix t = new TransposeOGMatrix();

  int normalRows = 4;
  int normalCols = 3;
  double[] _data = new double[] {1, 4, 7, 10, 2, 5, 8, 11, 3, 6, 9, 12 };
  OGMatrix array1 = new OGMatrix(_data, normalRows, normalCols);


  int transposedRows = 3;
  int transposedCols = 4;
  double[] _dataTransposed = new double[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };  
  OGMatrix array1tranposed = new OGMatrix(_dataTransposed, transposedRows, transposedCols);
  
  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void nullInTest() {
    OGMatrix tmp = null;
    t.eval(tmp);
  }

  @Test
  public void testTranspose() {
    assertTrue(array1tranposed.equals(t.eval(array1)));
  }

}
