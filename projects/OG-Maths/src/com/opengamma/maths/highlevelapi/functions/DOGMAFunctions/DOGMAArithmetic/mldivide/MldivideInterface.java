/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mldivide;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * 
 */
public interface MldivideInterface<T extends OGArraySuper<? extends Number>, S extends OGArraySuper<? extends Number>> {
  public <U extends Number> OGArraySuper<U > mldivide(T array1, S array2);
}
