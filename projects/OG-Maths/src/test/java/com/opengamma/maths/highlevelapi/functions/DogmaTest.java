/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions;

import org.testng.annotations.Test;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMAArithmetic;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMASparseUtilities;

import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;

/**
 * 
 */
public class DogmaTest {
  final double[][] doubleMatrixData3by5 = { {1, -2, 3, -4, 5 }, {-6, 7, -8, 9, -10 }, {11, -12, 13, -14, 15 } };
  final double[][] doubleMatrixData4by5 = { {1, -2, 3, -4, 5 }, {-6, 7, -8, 9, -10 }, {11, -12, 13, -14, 15 }, {-16, 17, -18, 19, -20 } };
  final double[][] doubleMatrixData5by2 = { {1, 2 }, {3, 4 }, {5, 6 }, {7, 8 }, {9, 10 } };
  final double[][] sparseMatrixData3by5 = { {1, 0, 3, 0, 5 }, {-6, 0, -8, 0, -10 }, {0, -12, 0, -14, 0 } };
  final double[][] moreSparseMatrixData3by5 = { {0, 0, 3, 4, 0 }, {0, 7, -8, 0, -10 }, {0, -12, 0, 0, 0 } };
  final double[][] sparseMatrixData5by4 = { {1, 0, 3, 0 }, {0, 6, 0, 8 }, {0, 0, 0, 12 }, {0, 14, 15, 0 }, {0, 0, 0, 0 } };
  final OGMatrix ogDoubleMatrixData3by5 = new OGMatrix(doubleMatrixData3by5);
  final OGMatrix ogDoubleMatrixData4by5 = new OGMatrix(doubleMatrixData4by5);
  final OGMatrix ogDoubleMatrixData5by2 = new OGMatrix(doubleMatrixData5by2);
  final OGSparseMatrix ogSparseMatrixData3by5 = new OGSparseMatrix(sparseMatrixData3by5);
  final OGSparseMatrix ogMoreSparseMatrixData = new OGSparseMatrix(moreSparseMatrixData3by5);
  final OGSparseMatrix ogSparseMatrixData5by4 = new OGSparseMatrix(sparseMatrixData5by4);
  final OGMatrix ogFullsparseMatrixData = new OGMatrix(sparseMatrixData3by5);
  BLAS bar = new BLAS();
  DOGMAArithmetic foo = new DOGMAArithmetic();
  DOGMASparseUtilities baz = new DOGMASparseUtilities();

  @Test
  public void TestSomeTest() {

    System.out.println("Input = " + ogDoubleMatrixData3by5.toString());

    System.out.println("Input = " + ogDoubleMatrixData3by5.toString());
    OGArray<? extends Number> answer = foo.plus(ogDoubleMatrixData3by5, ogDoubleMatrixData3by5);
    OGArray<? extends Number> answer2 = foo.plus(answer, ogDoubleMatrixData3by5);
    System.out.println("Answer2 = " + answer2.toString());
    OGArray<? extends Number> answer3 = foo.plus(answer2, 7.);
    System.out.println("Answer3 = " + answer3.toString());
    answer3 = foo.plus(answer3, answer3);
    System.out.println("Answer3 = " + answer3.toString());

    OGArray<? extends Number> answer4 = foo.plus(answer3, answer3, answer3, answer3);
    System.out.println("Answer4 = " + answer4.toString());

    System.out.println("Sparse = " + ogSparseMatrixData3by5.toString());
    System.out.println("Dense rep of sparse = " + ogFullsparseMatrixData.toString());
    System.out.println("Full op = " + baz.full(ogSparseMatrixData3by5).toString());
    System.out.println("");
    System.out.println("Adding sparse to full" + foo.plus(ogDoubleMatrixData3by5, ogSparseMatrixData3by5));
    System.out.println("Input = " + ogDoubleMatrixData3by5.toString());
    System.out.println("Input = " + baz.full(ogSparseMatrixData3by5).toString());

    System.out.println("Adding full to sparse" + foo.plus(ogSparseMatrixData3by5, ogDoubleMatrixData3by5));
    System.out.println("Input = " + ogDoubleMatrixData3by5.toString());
    System.out.println("Input = " + baz.full(ogSparseMatrixData3by5).toString());

//    answer3 = foo.times(ogDoubleMatrixData3by5, new OGMatrix(7.e0));
//    System.out.println("Times = " + answer3.toString());
//
//    System.out.println("Sparse TIMES = ");
//    answer3 = foo.times(ogDoubleMatrixData3by5, ogSparseMatrixData3by5);
//    System.out.println("Dense Matrix = " + ogDoubleMatrixData3by5.toString());
//    System.out.println("Sparse Matrix = " + baz.full(ogSparseMatrixData3by5).toString());
//    System.out.println("Times Sparse = " + baz.full(answer3).toString());
//
//    answer3 = foo.times(ogSparseMatrixData3by5, ogMoreSparseMatrixData);
//    System.out.println("Sparse Times Sparse = " + baz.full(answer3).toString());

    answer3 = foo.plus(ogSparseMatrixData3by5, ogMoreSparseMatrixData);
    System.out.println("Sparse Plus Sparse = " + baz.full(answer3).toString());

    System.out.println("Sparse minus Sparse = " + baz.full(foo.minus(answer3, ogMoreSparseMatrixData)).toString());

    answer3 = foo.rdivide(ogDoubleMatrixData3by5, new OGMatrix(10));
    System.out.println("rdiv = " + answer3.toString());

    answer3 = foo.rdivide(new OGMatrix(10), ogDoubleMatrixData3by5);
    System.out.println("rdiv = " + answer3.toString());

    answer3 = foo.rdivide(new OGMatrix(10), ogFullsparseMatrixData);
    System.out.println("rdiv = " + answer3.toString());

    System.out.println("Dense / Sparse");
    answer3 = foo.rdivide(ogDoubleMatrixData3by5, new OGSparseMatrix(new double[][] {{10 } }));
    System.out.println("rdiv full d single s = " + answer3.toString());

    answer3 = foo.rdivide(new OGMatrix(10), ogSparseMatrixData3by5);
    System.out.println("rdiv single d full s = " + answer3.toString());

    answer3 = foo.rdivide(ogDoubleMatrixData3by5, ogSparseMatrixData3by5);
    System.out.println("rdiv full d full s = " + answer3.toString());

    System.out.println("Sparse/Dense");
    answer3 = foo.rdivide(new OGSparseMatrix(new double[][] {{10 } }), ogDoubleMatrixData3by5);
    System.out.println("rdiv single s full d = " + baz.full(answer3).toString());

    answer3 = foo.rdivide(ogSparseMatrixData3by5, new OGMatrix(10));
    System.out.println("rdiv full s single d = " + baz.full(answer3).toString());

    answer3 = foo.rdivide(ogSparseMatrixData3by5, ogDoubleMatrixData3by5);
    System.out.println("rdiv full s full d = " + baz.full(answer3).toString());

    System.out.println("Sparse/Sparse");
    answer3 = foo.rdivide(new OGSparseMatrix(new double[][] {{10 } }), ogSparseMatrixData3by5);
    System.out.println("rdiv single s full s = " + baz.full(answer3).toString() + "Class is" + answer3.getClass().toString());

    answer3 = foo.rdivide(ogSparseMatrixData3by5, new OGSparseMatrix(new double[][] {{10 } }));
    System.out.println("rdiv full s single s = " + baz.full(answer3).toString() + "Class is" + answer3.getClass().toString());
    System.out.println("CHECK THIS" + (answer3).toString());

    answer3 = foo.rdivide(ogSparseMatrixData3by5, ogMoreSparseMatrixData);
    System.out.println("rdiv full s full s = " + baz.full(answer3).toString());

    System.out.println("MTIMES");
    System.out.println("Dense * Dense");
    System.out.println("Dense == " + ogDoubleMatrixData3by5.toString());
//    answer3 = foo.mtimes(new OGMatrix(new double[][] {{10 } }), ogDoubleMatrixData3by5);
//    System.out.println("mtimes single d full d = " + answer3.toString());
//
//    answer3 = foo.mtimes(new OGMatrix(new double[][] {{10, 20, 30 } }), ogDoubleMatrixData3by5);
//    System.out.println("mtimes row vector d full d = " + answer3.toString());
//
//    answer3 = foo.mtimes(ogDoubleMatrixData3by5, new OGMatrix(new double[][] { {10 }, {20 }, {30 }, {40 }, {50 } }));
//    System.out.println("mtimes full d col vector d = " + answer3.toString());
//
//    answer3 = foo.mtimes(ogDoubleMatrixData3by5, ogDoubleMatrixData5by2);
//    System.out.println("mtimes full d full d = " + answer3.toString());
//
//    System.out.println("Dense * Sparse");
//    answer3 = foo.mtimes(ogDoubleMatrixData3by5, new OGSparseMatrix(new double[][] {{10 } }));
//    System.out.println("mtimes full d single s= " + answer3.toString());
//
//    answer3 = foo.mtimes(new OGMatrix(new double[][] {{10 } }), ogSparseMatrixData3by5);
//    System.out.println("mtimes single d full s = " + baz.full(answer3).toString());    
//    
//    answer3 = foo.mtimes(ogDoubleMatrixData3by5, new OGSparseMatrix(new double[][] { {10 }, {0 }, {30 }, {0 }, {50 } }));
//    System.out.println("mtimes full d vector s = " + answer3.toString());
//
//    System.out.println("ogSparseMatrixData5by4" + ogSparseMatrixData5by4.toString());
//    answer3 = foo.mtimes(ogDoubleMatrixData3by5, ogSparseMatrixData5by4);
//    System.out.println("mtimes full d full s = " + answer3.toString());
//
//    System.out.println("Sparse * Dense");
//    answer3 = foo.mtimes(ogSparseMatrixData5by4, new OGMatrix(new double[][] {{10 } }));
//    System.out.println("mtimes full s single d = " + baz.full(answer3).toString());
//
//    answer3 = foo.mtimes(new OGSparseMatrix(new double[][] {{10 } }), ogDoubleMatrixData3by5);
//    System.out.println("mtimes single s full d = " + baz.full(answer3).toString());
//    
//    answer3 = foo.mtimes(ogSparseMatrixData5by4, new OGMatrix(new double[][] { {10 }, {20 }, {30 }, {40 } }));
//    System.out.println("mtimes full s vector d = " + baz.full(answer3).toString());
//
//    answer3 = foo.mtimes(ogSparseMatrixData5by4, ogDoubleMatrixData4by5);
//    System.out.println("mtimes full s full d = " + answer3.toString());
//    
//    
//    
//    System.out.println("Sparse * Sparse");
//    answer3 = foo.mtimes(ogSparseMatrixData5by4, new OGSparseMatrix(new double[][] {{10 } }));
//    System.out.println("mtimes full s single s = " + baz.full(answer3).toString());
//
//    answer3 = foo.mtimes(ogSparseMatrixData5by4, new OGSparseMatrix(new double[][] { {0 }, {20 }, {30 }, {0 } }));
//    System.out.println("mtimes full s vector s = " + baz.full(answer3).toString());   
//
//    answer3 = foo.mtimes(ogSparseMatrixData3by5, ogSparseMatrixData5by4);
//    System.out.println("mtimes full s full s = " + baz.full(answer3).toString());    
  }

}
