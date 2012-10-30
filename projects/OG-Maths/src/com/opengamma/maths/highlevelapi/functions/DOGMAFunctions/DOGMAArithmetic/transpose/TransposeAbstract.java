/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Class holder for the transpose operation
 * @param <T> An OGArray type
 */
public interface TransposeAbstract<T extends OGArray<? extends Number>> {
  
  T transpose(T array1);
}
