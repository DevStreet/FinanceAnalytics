/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tan;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Math.tan overload
 * @param <T> An OGArray type
 */
public interface TanAbstract<T extends OGArray<? extends Number>> {
  
  OGArray<? extends Number> tan(T array1);
}
