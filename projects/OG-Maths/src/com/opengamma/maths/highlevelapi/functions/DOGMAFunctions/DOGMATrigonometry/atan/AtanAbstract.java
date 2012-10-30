/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.atan;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Math.atan overload
 * @param <T> An OGArray type
 */
public interface AtanAbstract<T extends OGArray<? extends Number>> {
  
  OGArray<? extends Number> atan(T array1);
}
