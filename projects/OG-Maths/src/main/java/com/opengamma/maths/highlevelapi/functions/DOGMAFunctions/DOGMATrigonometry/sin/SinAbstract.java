/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.sin;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Math.sin overload
 * @param <T> An OGArray type
 */
public interface SinAbstract<T extends OGArray<? extends Number>> {
  
  OGArray<? extends Number> sin(T array1);
}
