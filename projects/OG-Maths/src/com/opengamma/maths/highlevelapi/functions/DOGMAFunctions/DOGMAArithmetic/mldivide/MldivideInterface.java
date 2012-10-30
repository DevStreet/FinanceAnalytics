/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mldivide;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * 
 */
public interface MldivideInterface<T extends OGArray<? extends Number>, S extends OGArray<? extends Number>> {
  public <U extends Number> OGArray<U > mldivide(T array1, S array2);
}
