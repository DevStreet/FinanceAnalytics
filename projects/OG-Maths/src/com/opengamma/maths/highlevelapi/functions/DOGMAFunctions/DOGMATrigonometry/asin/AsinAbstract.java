/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.asin;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Math.asin overload
 * @param <T> An OGArray type
 */
public interface AsinAbstract<T extends OGArraySuper<? extends Number>> {
  
  OGArraySuper<? extends Number> asin(T array1);
}
