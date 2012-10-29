/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.cosh;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Math.cosh overload
 * @param <T> An OGArray type
 */
public interface CoshAbstract<T extends OGArraySuper<? extends Number>> {
  
  OGArraySuper<? extends Number> cosh(T array1);
}
