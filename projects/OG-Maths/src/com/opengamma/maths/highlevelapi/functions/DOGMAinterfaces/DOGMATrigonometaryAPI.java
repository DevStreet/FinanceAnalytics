/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * DOGMA Utility functions
 */
public interface DOGMATrigonometaryAPI {

  /**
   * Performs vectorised sin()
   * @param array1 the first array
   * @return the sin() of each element of array1
   */
  OGArraySuper<Number> sin(OGArraySuper<Number> array1);
  
  /**
   * Performs vectorised cos()
   * @param array1 the first array
   * @return the cos() of each element of array1
   */
  OGArraySuper<Number> cos(OGArraySuper<Number> array1);
  
  /**
   * Performs vectorised tan()
   * @param array1 the first array
   * @return the tan() of each element of array1
   */
  OGArraySuper<Number> tan(OGArraySuper<Number> array1);
  
  /**
   * Performs vectorised asin()
   * @param array1 the first array
   * @return the asin() of each element of array1
   */
  OGArraySuper<Number> asin(OGArraySuper<Number> array1);
  
  /**
   * Performs vectorised acos()
   * @param array1 the first array
   * @return the acos() of each element of array1
   */
  OGArraySuper<Number> acos(OGArraySuper<Number> array1);  

  /**
   * Performs vectorised atan()
   * @param array1 the first array
   * @return the atan() of each element of array1
   */
  OGArraySuper<Number> atan(OGArraySuper<Number> array1);  

  /**
   * Performs vectorised sinh()
   * @param array1 the first array
   * @return the sinh() of each element of array1
   */
  OGArraySuper<Number> sinh(OGArraySuper<Number> array1);
  
  /**
   * Performs vectorised cosh()
   * @param array1 the first array
   * @return the cosh() of each element of array1
   */
  OGArraySuper<Number> cosh(OGArraySuper<Number> array1);
  
  /**
   * Performs vectorised tanh()
   * @param array1 the first array
   * @return the tanh() of each element of array1
   */
  OGArraySuper<Number> tanh(OGArraySuper<Number> array1);
  
}
