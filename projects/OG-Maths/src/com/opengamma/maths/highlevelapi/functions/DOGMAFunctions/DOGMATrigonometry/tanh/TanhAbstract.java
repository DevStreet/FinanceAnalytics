/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tanh;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Math.tanh overload
 * @param <T> An OGArray type
 */
public interface TanhAbstract<T extends OGArraySuper<? extends Number>> {
  
  OGArraySuper<? extends Number> tanh(T array1);
}
