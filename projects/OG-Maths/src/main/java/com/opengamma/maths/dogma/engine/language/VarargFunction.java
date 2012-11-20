/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.language;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * @param <R> ret
 * @param <S> args
 */
public interface VarargFunction<R extends OGArray<? extends Number>, S extends OGArray<? extends Number>> extends Function {

  /**
   * @param arrays first
    * @return ret
   */
  R eval(S... arrays);
}
