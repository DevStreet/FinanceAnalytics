/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.cos;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Math.cos overload
 * @param <T> An OGArray type
 */
public interface CosAbstract<T extends OGArraySuper<? extends Number>> {
  
  OGArraySuper<? extends Number> cos(T array1);
}
