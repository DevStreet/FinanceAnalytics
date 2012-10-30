/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAHelpers.genericconverters;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * @param <T>
 * @param <U>
 */
public interface GenericUpcast<T extends OGArray<? extends Number>, U extends OGArray<? extends Number>> {
  
  U from(T array);
}
