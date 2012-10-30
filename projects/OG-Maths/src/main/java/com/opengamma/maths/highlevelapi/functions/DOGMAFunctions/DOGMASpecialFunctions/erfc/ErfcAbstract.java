/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialFunctions.erfc;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * erfc
 * @param <T> An OGArray type
 */
public interface ErfcAbstract<T extends OGArray<? extends Number>> {
 
  OGArray<? extends Number> erfc(T array1);
}
