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
public interface MtimesAbstract<T extends OGArraySuper<? extends Number>, S extends OGArraySuper<? extends Number>> {
  
  OGArraySuper<? extends Number> mtimes(T array1, S array2);
}
