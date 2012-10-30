/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * DOGMA Utility functions
 */
public interface DOGMATrigonometaryAPI {

  /**
   * Performs vectorised sin()
   * @param array1 the first array
   * @return the sin() of each element of array1
   */
  OGArray<? extends Number> sin(OGArray<? extends Number> array1);
  
  /**
   * Performs vectorised cos()
   * @param array1 the first array
   * @return the cos() of each element of array1
   */
  OGArray<? extends Number> cos(OGArray<? extends Number> array1);
  
  /**
   * Performs vectorised tan()
   * @param array1 the first array
   * @return the tan() of each element of array1
   */
  OGArray<? extends Number> tan(OGArray<? extends Number> array1);
  
  /**
   * Performs vectorised asin()
   * @param array1 the first array
   * @return the asin() of each element of array1
   */
  OGArray<? extends Number> asin(OGArray<? extends Number> array1);
  
  /**
   * Performs vectorised acos()
   * @param array1 the first array
   * @return the acos() of each element of array1
   */
  OGArray<? extends Number> acos(OGArray<? extends Number> array1);  

  /**
   * Performs vectorised atan()
   * @param array1 the first array
   * @return the atan() of each element of array1
   */
  OGArray<? extends Number> atan(OGArray<? extends Number> array1);  

  /**
   * Performs vectorised sinh()
   * @param array1 the first array
   * @return the sinh() of each element of array1
   */
  OGArray<? extends Number> sinh(OGArray<? extends Number> array1);
  
  /**
   * Performs vectorised cosh()
   * @param array1 the first array
   * @return the cosh() of each element of array1
   */
  OGArray<? extends Number> cosh(OGArray<? extends Number> array1);
  
  /**
   * Performs vectorised tanh()
   * @param array1 the first array
   * @return the tanh() of each element of array1
   */
  OGArray<? extends Number> tanh(OGArray<? extends Number> array1);
  
}
