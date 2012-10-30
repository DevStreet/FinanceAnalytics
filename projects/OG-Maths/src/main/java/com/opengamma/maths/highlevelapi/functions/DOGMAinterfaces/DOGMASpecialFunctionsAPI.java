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
public interface DOGMASpecialFunctionsAPI {

  /**
   * Performs vectorised erf()
   * @param array1 the first array
   * @return the erf() of each element of array1
   */
  OGArray<? extends Number> erf(OGArray<? extends Number> array1);
  
  /**
   * Performs vectorised erfc()
   * @param array1 the first array
   * @return the erfc() of each element of array1
   */
  OGArray<? extends Number> erfc(OGArray<? extends Number> array1);  
  
}
