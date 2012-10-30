/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mldivide;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * mldivide() interface
 * @param <T> an OGArray type
 * @param <S> an OGArray type 
 */
public interface MldivideInterface<T extends OGArray<? extends Number>, S extends OGArray<? extends Number>> {
  OGArray<? extends Number> mldivide(T array1, S array2);
}
