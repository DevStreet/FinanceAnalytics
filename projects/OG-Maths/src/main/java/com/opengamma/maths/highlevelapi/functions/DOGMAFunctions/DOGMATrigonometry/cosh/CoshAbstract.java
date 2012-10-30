/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.cosh;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Math.cosh overload
 * @param <T> An OGArray type
 */
public interface CoshAbstract<T extends OGArray<? extends Number>> {
  
  OGArray<? extends Number> cosh(T array1);
}
