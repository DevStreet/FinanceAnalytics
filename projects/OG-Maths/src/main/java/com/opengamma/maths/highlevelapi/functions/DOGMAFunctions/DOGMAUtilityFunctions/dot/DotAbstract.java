/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAUtilityFunctions.dot;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Computes dot products
 * @param <S> an array
 * @param <T> another array
 */
public interface DotAbstract<S extends OGArray<? extends Number>, T extends OGArray<? extends Number>> {
  
  OGArray<? extends Number> dot(S array1, T array2);
}
