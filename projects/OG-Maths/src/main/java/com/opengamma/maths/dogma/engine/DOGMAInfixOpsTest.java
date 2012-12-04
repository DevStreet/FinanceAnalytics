/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine;

import org.testng.annotations.Test;

import com.opengamma.maths.dogma.DOGMA;
import com.opengamma.maths.dogma.DogmaLanguage;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * 
 */
public class DOGMAInfixOpsTest {

  @Test
  public void plusTest() {
    double[][] data1 = new double[][] { {1, 2 }, {3, 4 } };
    double[][] data2 = new double[][] { {10, 20 }, {30, 40 } };
    double[][] data3 = new double[][] { {10, 20 }, {30, 0 } };
    double[] data4 = new double[] {100, 200 };
    OGMatrix arr1 = new OGMatrix(data1);
    OGMatrix arr2 = new OGMatrix(data2);
    OGSparseMatrix arr3 = new OGSparseMatrix(data3);
    OGDiagonalMatrix arr4 = new OGDiagonalMatrix(data4);
    OGComplexMatrix arr5 = new OGComplexMatrix(data1);
    OGComplexSparseMatrix arr6 = new OGComplexSparseMatrix(data3);
    System.out.println(DOGMAInfixOps.plus(arr1, arr2));
    System.out.println(DOGMAInfixOps.plus(arr3, arr1));
    System.out.println(DOGMAInfixOps.plus(arr1, arr3));
    System.out.println(DOGMAInfixOps.plus(arr3, arr3));
    System.out.println(DOGMAInfixOps.plus(arr4, arr3));
    System.out.println(DOGMAInfixOps.plus(arr2, arr4));
    System.out.println(DOGMAInfixOps.plus(arr2, arr5));
    System.out.println("\n\n\nFROM DOGMA..."+DogmaLanguage.plus(arr2, arr5)+"\n\n\n");
    System.out.println(DOGMAInfixOps.mtimes(arr3, arr2));   
    System.out.println("7x diag=" + DOGMAInfixOps.mtimes(7, arr4));
    System.out.println("\n\n\n"+DogmaLanguage.sin(arr5));
    DOGMA.disp(arr5);
  }

}
