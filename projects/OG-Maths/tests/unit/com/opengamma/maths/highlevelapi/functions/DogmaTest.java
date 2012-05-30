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
  final double[][] doubleMatrixData3by5 = { {1, -2, 3, -4, 5 }, {-6, 7, -8, 9, -10 }, {11, -12, 13, -14, 15 } };
  final double[][] doubleMatrixData5by2 = { {1, 2},{3, 4},{5,6},{ 7, 8},{ 9, 10 }};  
  final double[][] sparseMatrixData = { {1, 0, 3, 0, 5 }, {-6, 0, -8, 0, -10 }, {0, -12, 0, -14, 0 } };
  final double[][] moreSparseMatrixData = { {0, 0, 3, 4, 0 }, {0, 7, -8, 0, -10 }, {0, -12, 0, 0, 0 } };
  final OGDoubleArray ogDoubleMatrixData3by5 = new OGDoubleArray(doubleMatrixData3by5);
  final OGDoubleArray ogDoubleMatrixData5by2 = new OGDoubleArray(doubleMatrixData5by2);  
  final OGSparseArray ogSparseMatrixData = new OGSparseArray(sparseMatrixData);
  final OGSparseArray ogMoreSparseMatrixData = new OGSparseArray(moreSparseMatrixData);
  final OGDoubleArray ogFullsparseMatrixData = new OGDoubleArray(sparseMatrixData);
  BLAS bar = new BLAS();
  DOGMAArithmetic foo = new DOGMAArithmetic();
  DOGMASparseUtilities baz = new DOGMASparseUtilities();

  @Test
  public void TestSomeTest() {

    System.out.println("Input = " + ogDoubleMatrixData3by5.toString());

    System.out.println("Input = " + ogDoubleMatrixData3by5.toString());
    OGArraySuper<Number> answer = foo.plus(ogDoubleMatrixData3by5, ogDoubleMatrixData3by5);
    OGArraySuper<Number> answer2 = foo.plus(answer, ogDoubleMatrixData3by5);
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
    System.out.println("Adding sparse to full" + foo.plus(ogDoubleMatrixData3by5, ogSparseMatrixData));
    System.out.println("Input = " + ogDoubleMatrixData3by5.toString());
    System.out.println("Input = " + baz.full(ogSparseMatrixData).toString());

    System.out.println("Adding full to sparse" + foo.plus(ogSparseMatrixData, ogDoubleMatrixData3by5));
    System.out.println("Input = " + ogDoubleMatrixData3by5.toString());
    System.out.println("Input = " + baz.full(ogSparseMatrixData).toString());

    answer3 = foo.times(ogDoubleMatrixData3by5, new OGDoubleArray(7.e0));
    System.out.println("Times = " + answer3.toString());

    System.out.println("Sparse TIMES = ");
    answer3 = foo.times(ogDoubleMatrixData3by5, ogSparseMatrixData);
    System.out.println("Dense Matrix = " + ogDoubleMatrixData3by5.toString());
    System.out.println("Sparse Matrix = " + baz.full(ogSparseMatrixData).toString());
    System.out.println("Times Sparse = " + baz.full(answer3).toString());

    answer3 = foo.times(ogSparseMatrixData, ogMoreSparseMatrixData);
    System.out.println("Sparse Times Sparse = " + baz.full(answer3).toString());
    
    
    answer3 = foo.plus(ogSparseMatrixData, ogMoreSparseMatrixData);
    System.out.println("Sparse Plus Sparse = " + baz.full(answer3).toString());
    
    System.out.println("Sparse minus Sparse = " + baz.full(foo.minus(answer3,ogMoreSparseMatrixData)).toString());
    
    answer3 = foo.rdivide(ogDoubleMatrixData3by5, new OGDoubleArray(10));
    System.out.println("rdiv = " + answer3.toString());
    
    answer3 = foo.rdivide(new OGDoubleArray(10), ogDoubleMatrixData3by5);
    System.out.println("rdiv = " + answer3.toString());
    
    answer3 = foo.rdivide(new OGDoubleArray(10), ogFullsparseMatrixData);
    System.out.println("rdiv = " + answer3.toString());
    
    System.out.println("Dense / Sparse");
    answer3 = foo.rdivide(ogDoubleMatrixData3by5, new OGSparseArray(new double [][] {{10}}));
    System.out.println("rdiv full d single s = " + answer3.toString());
    
    answer3 = foo.rdivide(new OGDoubleArray(10), ogSparseMatrixData);
    System.out.println("rdiv single d full s = " + answer3.toString());
    
    answer3 = foo.rdivide(ogDoubleMatrixData3by5, ogSparseMatrixData);
    System.out.println("rdiv full d full s = " + answer3.toString());
    
    System.out.println("Sparse/Dense");
    answer3 = foo.rdivide(new OGSparseArray(new double [][] {{10}}),ogDoubleMatrixData3by5);
    System.out.println("rdiv single s full d = " + baz.full(answer3).toString());
    
    answer3 = foo.rdivide(ogSparseMatrixData, new OGDoubleArray(10));
    System.out.println("rdiv full s single d = " + baz.full(answer3).toString());
    
    answer3 = foo.rdivide(ogSparseMatrixData, ogDoubleMatrixData3by5);
    System.out.println("rdiv full s full d = " + baz.full(answer3).toString());          
        
    System.out.println("Sparse/Sparse");
    answer3 = foo.rdivide(new OGSparseArray(new double [][] {{10}}),ogSparseMatrixData);
    System.out.println("rdiv single s full s = " + baz.full(answer3).toString() + "Class is" + answer3.getClass().toString());
    
    answer3 = foo.rdivide(ogSparseMatrixData, new OGSparseArray(new double [][] {{10}}));
    System.out.println("rdiv full s single s = " + baz.full(answer3).toString() + "Class is" + answer3.getClass().toString());   
    
    answer3 = foo.rdivide(ogSparseMatrixData, ogMoreSparseMatrixData);
    System.out.println("rdiv full s full s = " + baz.full(answer3).toString());         
   
    System.out.println("MTIMES");
    System.out.println("Dense * Dense");
    System.out.println("Dense == "+ogDoubleMatrixData3by5.toString());    
    answer3 = foo.mtimes(new OGDoubleArray(new double [][] {{10}}),ogDoubleMatrixData3by5);
    System.out.println("mtimes single d full d = " + answer3.toString());
    
    answer3 = foo.mtimes(new OGDoubleArray(new double [][] {{10,20,30}}),ogDoubleMatrixData3by5);
    System.out.println("mtimes row vector d full d = " + answer3.toString());
    
    answer3 = foo.mtimes(ogDoubleMatrixData3by5, new OGDoubleArray(new double [][] {{10},{20},{30},{40},{50}}));
    System.out.println("mtimes full d col vector d = " + answer3.toString());        

    answer3 = foo.mtimes(ogDoubleMatrixData3by5, ogDoubleMatrixData5by2);
    System.out.println("mtimes full d full d = " + answer3.toString());    
    
    
  }

}
