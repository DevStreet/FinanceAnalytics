/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.language;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * @param <R> ret
 * @param <S> arg1
 * @param <T> arg2
 */
public interface InfixOperator<R extends OGArray<? extends Number>, S extends OGArray<? extends Number>, T extends OGArray<? extends Number>> extends Function {

  /**
   * @param array1 first
   * @param array2 second 
   * @return ret
   */
  R eval(S array1, T array2);
}
