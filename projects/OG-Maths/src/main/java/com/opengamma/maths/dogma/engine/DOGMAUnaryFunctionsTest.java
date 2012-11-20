/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * 
 */
public class DOGMAUnaryFunctionsTest {

  @Test
  public void sinTest() {
    double[][] data1 = new double[][] { {1, 2 }, {3, 4 } };
    double[][] data1cmplx = new double[][] { {10, -20 }, {30, -40 } };
    double[][] data2 = new double[][] { {10, 20 }, {30, 40 } };
    double[][] data3 = new double[][] { {10, 20 }, {30, 0 } };
    double[] data4 = new double[] {100, 200 };
    OGMatrix arr1 = new OGMatrix(data1);
    OGMatrix arr2 = new OGMatrix(data2);
    OGSparseMatrix arr3 = new OGSparseMatrix(data3);
    OGDiagonalMatrix arr4 = new OGDiagonalMatrix(data4);
    OGComplexMatrix arr5 = new OGComplexMatrix(data1, data1cmplx);
    System.out.println(DOGMAUnaryOps.sin(arr1));
    System.out.println(DOGMAUnaryOps.sin(arr3));
    System.out.println(DOGMAUnaryOps.sin(arr4));
    System.out.println(DOGMAUnaryOps.sin(arr5));
  }

}
