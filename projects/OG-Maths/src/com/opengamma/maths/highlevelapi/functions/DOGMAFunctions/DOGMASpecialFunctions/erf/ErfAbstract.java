/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialFunctions.erf;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * erf
 * @param <T> An OGArray type
 */
public interface ErfAbstract<T extends OGArray<? extends Number>> {
  
  OGArray<? extends Number> erf(T array1);
}
