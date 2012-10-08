/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMAArithmetic;

/**
 * Tests OGDoubleArray transpose
 */
public class DOGMAOGSparseArrayTransposeTest {

  DOGMAArithmetic DA = new DOGMAArithmetic();

  int normalRows = 4;
  int normalCols = 3;
  double[][] _data = new double[][] { {1.00, 2.00, 3.00 }, {4.00, 0.00, 6.00 }, {7.00, 0.00, 9.00 }, {0.00, 11.00, 12.00 } };
  OGSparseArray array1 = new OGSparseArray(_data);

  int transposedRows = 3;
  int transposedCols = 4;
  double[][] _dataTransposed = new double[][] { {1.00, 4.00, 7.00, 0.00 }, {2.00, 0.00, 0.00, 11.00 }, {3.00, 6.00, 9.00, 12.00 } };
  OGSparseArray array1transposed = new OGSparseArray(_dataTransposed);

  @Test(expectedExceptions = MathsExceptionNullPointer.class)
  public void nullInTest() {
    OGSparseArray tmp = null;
    DA.transpose(tmp);
  }

  @Test
  public void testTranspose() {
    DA.transpose(array1);
    assertTrue(array1transposed.equals(DA.transpose(array1)));
  }

}
