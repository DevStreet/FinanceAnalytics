/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;

/**
 * DOGMA is the OpenGamma Maths Assembly language. 
 * It's rather like Octave, but due to the lack of operator overloading and syntax definition there are compromises!
 */
public interface DOGMAArithmeticAPI {

  /* ADD */
  /**
   * Adds OGArray arrays.
   * If more than two arrays are given the additions are applied from left to right and accumulated.
   * e.g. (((array[0]+array[1])+array[2])+array[3])... 
   * @param array the arrays to add together
   * @return the sum of the arrays  
   */
  OGArraySuper<Number> plus(OGArraySuper<Number>... array);
  
  /**
   * Adds two OGDoubleArray arrays
   * @param array1 the first array
   * @param array2 the second array
   * @return the sum of the arrays 
   */
  OGArraySuper<Number> plus(OGArraySuper<Number> array1, OGArraySuper<Number> array2);  

  /* SUBTRACT */
  /**
   * Subtracts OGArray arrays.
   * If more than two arrays are given the additions are applied from left to right and accumulated.
   * e.g. (((array[0]-array[1])-array[2])-array[3])... 
   * @param array the arrays to subtract
   * @return array1 - array2 
   */
  OGArraySuper<Number> minus(OGArraySuper<Number>... array);

  /**
   * Subtracts an OGDoubleArray from an OGSparseArray
   * @param array1 the first array
   * @param array2 the second array
   * @return array1 - array2
   */
  OGArraySuper<Number> minus(OGArraySuper<Number> array1, OGArraySuper<Number> array2);

   /* DIVIDE */
  /**
   * Element by element left division
   * @param array1 the first array
   * @param array2 the second array
   * @return array1 ldivided element-wise by array2
   */
  OGDoubleArray ldivide(OGDoubleArray array1, OGDoubleArray array2);

  /**
   * Matrix left division
   * @param array1 the first array
   * @param array2 the second array
   * @return array1\array2
   */
  OGDoubleArray mldivide(OGDoubleArray array1, OGDoubleArray array2);

  /**
   * Element by element right division
   * @param array1 the first array
   * @param array2 the second array
   * @return array1 rdivided element-wise by array2
   */
  OGDoubleArray rdivide(OGDoubleArray array1, OGDoubleArray array2);  
  
  /**
   * Right division, which effectively returns x = inverse(A)*b
   * If A is not square a minimum norm solution is returned.
   * @param matrixA the first array
   * @param vectorb the second array
   * @return A\b
   */
  OGDoubleArray mrdivide(OGDoubleArray matrixA, OGDoubleArray vectorb);
  
  
  
  /* MULTIPLY */
  /**
   * Element wise multiplication. For more than two inputs it returns the multiplications
   * applied and accumulated from left to right, e.g.
   * (((array[0]*array[1])*array[2])*array[3])... 
   * @param array the arrays to multiply
   * @return the cumulative product of array multiplication
   */
  OGDoubleArray times(OGDoubleArray...array);

  /**
   * Matrix multiplication. For more than two inputs it returns the multiplications
   * applied and accumulated from left to right, e.g.
   * (((array[0]*array[1])*array[2])*array[3])... 
   * @param array the arrays to multiply
   * @return the cumulative product of array multiplication
   */
  OGDoubleArray mtimes(OGDoubleArray... array);

  /* POWER */
  /**
   * Element by element raise to a power
   * @param array1 the array
   * @param array2 scalar the power
   * @return array1 raised element by element to the power array2
   */
  OGDoubleArray power(OGDoubleArray array1, OGDoubleArray array2);  
  
  /**
   * Raise a matrix to a power
   * @param array1 the first array
   * @param array2 scalar
   * @return array1 raised to the power array2
   */
  OGDoubleArray mpower(OGDoubleArray array1, OGDoubleArray array2);

 

/* TRANSPOSE */
  /**
   * Transpose of an array
   * @param array the array to transpose
   * @return the transpose of the array
   */
  OGDoubleArray tranpose(OGDoubleArray array);
  
}
