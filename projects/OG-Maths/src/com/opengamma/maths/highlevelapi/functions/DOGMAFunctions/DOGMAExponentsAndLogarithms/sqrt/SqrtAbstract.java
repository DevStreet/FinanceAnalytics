/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAExponentsAndLogarithms.sqrt;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Sqrt
 * @param <T> An OGArray type
 */
public interface SqrtAbstract<T extends OGArray<? extends Number>> {
  
  T sqrt(T array1);
}
