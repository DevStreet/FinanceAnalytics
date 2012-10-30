/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Element wise multiply
 * @param <T> An OGArray type
 * @param <S> An OGArray type
 */
public interface TimesAbstract<T extends OGArray<? extends Number>, S extends OGArray<? extends Number>> {
  
  OGArray<? extends Number> times(T array1, S array2);
}
