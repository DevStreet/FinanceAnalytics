/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

/**
 * 
 */
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Copy
 * @param <T> An OGArray type
 */
public abstract class CopyAbstract<T extends OGArraySuper<Number>> {
  public abstract <U> OGArraySuper<U> copy(T array1);
}

