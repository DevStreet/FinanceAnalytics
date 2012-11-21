/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * DOGMA is the OpenGamma Maths Assembly language. 
 * It's rather like Octave, but due to the lack of operator overloading and syntax definition there are compromises!
 */
public interface DOGMAArithmeticAPI {

  /* SUBTRACT */
  /**
   * Subtracts OGArray arrays.
   * If more than two arrays are given the additions are applied from left to right and accumulated.
   * e.g. (((array[0]-array[1])-array[2])-array[3])... 
   * @param array the arrays to subtract
   * @return array1 - array2 
   */
  OGArray<? extends Number> minus(OGArray<? extends Number>... array);

  /**
   * Subtracts an OGArraySuper<? extends Number> from an OGSparseArray
   * @param array1 the first array
   * @param array2 the second array
   * @return array1 - array2
   */
  OGArray<? extends Number> minus(OGArray<? extends Number> array1, OGArray<? extends Number> array2);

  /**
   * Subtracts a number to an OGArraySuper<? extends Number> array
   * @param array1 the first array
   * @param number the number
   * @return array1 minus a number
   */
  OGArray<? extends Number> minus(OGArray<? extends Number> array1, double number);

  /**
   * Subtracts a number to an OGArraySuper<? extends Number> array
   * @param array1 the first array
   * @param number the number
   * @return array1 minus a number 
   */
  OGArray<? extends Number> minus(OGArray<? extends Number> array1, int number);

  /* LDIVIDE */
  /**
   * Element by element left division
   * @param array1 the first array
   * @param array2 the second array
   * @return array1 ldivided element-wise by array2. i.e. array1.\array2
   */
  OGArray<? extends Number> ldivide(OGArray<? extends Number> array1, OGArray<? extends Number> array2);

  /**
   * Matrix left division
   * @param array1 the first array
   * @param array2 the second array
   * @return array1\array2
   */
  OGArray<? extends Number> mldivide(OGArray<? extends Number> array1, OGArray<? extends Number> array2);

  /* MRDIVIDE */
  /**
   * Right division, which effectively returns x = inverse(A)*b
   * If A is not square a minimum norm solution is returned.
   * @param matrixA the first array
   * @param vectorb the second array
   * @return A\b
   */
  OGArray<? extends Number> mrdivide(OGArray<? extends Number> matrixA, OGArray<? extends Number> vectorb);

  /* POWER */
  /**
   * Element by element raise to a power
   * @param array1 the array
   * @param array2 scalar the power
   * @return array1 raised element by element to the power array2
   */
  OGArray<? extends Number> power(OGArray<? extends Number> array1, OGArray<? extends Number> array2);

  /**
   * Raise a matrix to a power
   * @param array1 the first array
   * @param array2 scalar
   * @return array1 raised to the power array2
   */
  OGArray<? extends Number> mpower(OGArray<? extends Number> array1, OGArray<? extends Number> array2);

}
