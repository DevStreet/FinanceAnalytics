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
public abstract class DotAbstract<S extends OGArraySuper<Number>, T extends OGArraySuper<Number>> {
  public abstract <U> OGArraySuper<U> dot(S array1, T array2);
}
