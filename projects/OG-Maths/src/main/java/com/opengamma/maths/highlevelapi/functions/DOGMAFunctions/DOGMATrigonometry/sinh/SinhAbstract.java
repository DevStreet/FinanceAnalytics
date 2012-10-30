/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.sinh;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Math.sinh overload
 * @param <T> An OGArray type
 */
public interface SinhAbstract<T extends OGArray<? extends Number>> {
  
  OGArray<? extends Number> sinh(T array1);
}
