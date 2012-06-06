/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAExponentsAndLogarithms.sqrt;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Sqrt
 * @param <T> An OGArray type
 */
public abstract class SqrtAbstract<T extends OGArraySuper<Number>> {
  public abstract <U> OGArraySuper<U> sqrt(T array1);
}
