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
 * Copy
 * @param <T> An OGArray type
 */
public abstract class FullAbstract<T extends OGArraySuper<Number>> {
  public abstract <U> OGArraySuper<U> full(T array1);
}

