/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.horzcat;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Horzcat
 * @param <T> An OGArray type
 * @param <S> An OGArray type
 */
public interface HorzcatAbstract<T extends OGArray<? extends Number>, S extends OGArray<? extends Number>> {
  
  OGArray<? extends Number> horzcat(T array1, S array2);
}
