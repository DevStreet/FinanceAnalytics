/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMAArithmetic;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMASparseUtilities;

import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;

/**
 * 
 */
public class DogmaTest {
  final double[][] doubleMatrixData = { {1, -2, 3, -4, 5 }, {-6, 7, -8, 9, -10 }, {11, -12, 13, -14, 15 } };
  final double[][] sparseMatrixData = { {1, 0, 3, 0, 5 }, {-6, 0, -8, 0, -10 }, {0, -12, 0, -14, 0 } };
  final double[][] moreSparseMatrixData = { {0, 0, 3, 4, 0 }, {0, 7, -8, 0, -10 }, {0, -12, 0, 0, 0 } };
  final OGDoubleArray ogDoubleMatrixData = new OGDoubleArray(doubleMatrixData);
  final OGSparseArray ogSparseMatrixData = new OGSparseArray(sparseMatrixData);
  final OGSparseArray ogMoreSparseMatrixData = new OGSparseArray(moreSparseMatrixData);
  final OGDoubleArray ogFullsparseMatrixData = new OGDoubleArray(sparseMatrixData);
  BLAS bar = new BLAS();
  DOGMAArithmetic foo = new DOGMAArithmetic();
  DOGMASparseUtilities baz = new DOGMASparseUtilities();

  @Test
  public void TestSomeTest() {

    System.out.println("Input = " + ogDoubleMatrixData.toString());

    bar.daxpy(ogDoubleMatrixData.getData().length, 1e0, ogDoubleMatrixData.getData(), 1, ogDoubleMatrixData.getData(), 1);
    System.out.println("Input = " + ogDoubleMatrixData.toString());
    OGArraySuper<Number> answer = foo.plus(ogDoubleMatrixData, ogDoubleMatrixData);
    OGArraySuper<Number> answer2 = foo.plus(answer, ogDoubleMatrixData);
    System.out.println("Answer2 = " + answer2.toString());
    OGArraySuper<Number> answer3 = foo.plus(answer2, 7.);
    System.out.println("Answer3 = " + answer3.toString());
    answer3 = foo.plus(answer3, answer3);
    System.out.println("Answer3 = " + answer3.toString());

    OGArraySuper<Number> answer4 = foo.plus(answer3, answer3, answer3, answer3);
    System.out.println("Answer4 = " + answer4.toString());

    System.out.println("Sparse = " + ogSparseMatrixData.toString());
    System.out.println("Dense rep of sparse = " + ogFullsparseMatrixData.toString());
    System.out.println("Full op = " + baz.full(ogSparseMatrixData).toString());
    System.out.println("");
    System.out.println("Adding sparse to full" + foo.plus(ogDoubleMatrixData, ogSparseMatrixData));
    System.out.println("Input = " + ogDoubleMatrixData.toString());
    System.out.println("Input = " + baz.full(ogSparseMatrixData).toString());

    System.out.println("Adding full to sparse" + foo.plus(ogSparseMatrixData, ogDoubleMatrixData));
    System.out.println("Input = " + ogDoubleMatrixData.toString());
    System.out.println("Input = " + baz.full(ogSparseMatrixData).toString());

    answer3 = foo.times(ogDoubleMatrixData, new OGDoubleArray(7.e0));
    System.out.println("Times = " + answer3.toString());

    System.out.println("Sparse TIMES = ");
    answer3 = foo.times(ogDoubleMatrixData, ogSparseMatrixData);
    System.out.println("Dense Matrix = " + ogDoubleMatrixData.toString());
    System.out.println("Sparse Matrix = " + baz.full(ogSparseMatrixData).toString());
    System.out.println("Times Sparse = " + baz.full(answer3).toString());

    answer3 = foo.times(ogSparseMatrixData, ogMoreSparseMatrixData);
    System.out.println("Sparse Times Sparse = " + baz.full(answer3).toString());
    
    
    answer3 = foo.plus(ogSparseMatrixData, ogMoreSparseMatrixData);
    System.out.println("Sparse Plus Sparse = " + baz.full(answer3).toString());    
  }

}
