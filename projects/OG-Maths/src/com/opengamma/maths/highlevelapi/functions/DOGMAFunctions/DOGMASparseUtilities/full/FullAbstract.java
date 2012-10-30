/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

/**
 * Returns a fully populated array
 */
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Full
 * @param <T> An OGArray type
 */
public interface FullAbstract<T extends OGArray<? extends Number>> {
  
  OGArray<? extends Number> full(T array1);
}

