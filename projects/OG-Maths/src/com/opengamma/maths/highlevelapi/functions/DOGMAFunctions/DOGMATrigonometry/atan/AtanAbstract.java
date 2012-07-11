/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.atan;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Math.atan overload
 * @param <T> An OGArray type
 */
public abstract class AtanAbstract<T extends OGArraySuper<Number>> {
  public abstract <U> OGArraySuper<U> atan(T array1);
}
