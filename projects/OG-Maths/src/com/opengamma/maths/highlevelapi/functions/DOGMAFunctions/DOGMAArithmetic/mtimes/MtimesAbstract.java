/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Mathematical multiply
 * @param <T> An OGArray type
 * @param <S> An OGArray type
 */
public abstract class MtimesAbstract<T extends OGArraySuper<Number>, S extends OGArraySuper<Number>> {
  public abstract <U> OGArraySuper<U> mtimes(T array1, S array2);
}
