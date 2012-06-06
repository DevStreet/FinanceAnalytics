/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * DOGMA exponents and logarithms 
 */
public interface DOGMAExponentsAndLogarithmsAPI {
  /**
   * Computes the element wise square root of a matrix
   * @param array1 the array
   * @return the element wise square root of the array
   */
  OGArraySuper<Number> sqrt(OGArraySuper<Number> array1);
  
}
