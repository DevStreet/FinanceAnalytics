/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.language;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * @param <S> arg1
 */
public interface VoidUnaryFunction<S extends OGArray<? extends Number>> extends Function {

  /**
   * @param array1 first
   */
  void eval(S array1);
}
