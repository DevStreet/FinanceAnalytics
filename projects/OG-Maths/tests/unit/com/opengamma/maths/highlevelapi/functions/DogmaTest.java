/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMAArithmetic;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;


/**
 * 
 */
public class DogmaTest {
  final double[][] doubleMatrixData = { {1, -2, 3, -4, 5 }, {-6, 7, -8, 9, -10 }, {11, -12, 13, -14, 15 } };
  final OGDoubleArray ogDoubleMatrixData = new OGDoubleArray(doubleMatrixData);
  
  
  @Test
  public void TestSomeTest() {
    DOGMAArithmetic foo = new DOGMAArithmetic();
    System.out.println("Input = "+ogDoubleMatrixData.toString());
    BLAS bar = new BLAS();
    bar.daxpy(ogDoubleMatrixData.getData().length, 1e0, ogDoubleMatrixData.getData(), 1, ogDoubleMatrixData.getData(), 1);
    System.out.println("Input = "+ogDoubleMatrixData.toString());
    OGArraySuper<Number> answer = foo.plus(ogDoubleMatrixData, ogDoubleMatrixData);
    OGArraySuper<Number> answer2 = foo.plus(answer, ogDoubleMatrixData);           
    System.out.println("Answer = "+answer2.toString());
    OGArraySuper<Number> answer3 = foo.plus(answer2, 7.);
    System.out.println("Answer = "+answer3.toString());    
  }

}
