/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAUtilityFunctions.dot;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Computes dot products
 * @param <S> an array
 * @param <T> another array
 */
public interface DotAbstract<S extends OGArraySuper<? extends Number>, T extends OGArraySuper<? extends Number>> {
  
  OGArraySuper<? extends Number> dot(S array1, T array2);
}
