/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

/**
 * Returns a fully populated array
 */
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Full
 * @param <T> An OGArray type
 */
public interface FullAbstract<T extends OGArraySuper<? extends Number>> {
  
  OGArraySuper<? extends Number> full(T array1);
}

