/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.asin;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Math.asin overload
 * @param <T> An OGArray type
 */
public interface AsinAbstract<T extends OGArray<? extends Number>> {
  
  OGArray<? extends Number> asin(T array1);
}
