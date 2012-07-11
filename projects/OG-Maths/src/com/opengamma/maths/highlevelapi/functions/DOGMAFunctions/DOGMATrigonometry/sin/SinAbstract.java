/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.sin;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Math.sin overload
 * @param <T> An OGArray type
 */
public abstract class SinAbstract<T extends OGArraySuper<Number>> {
  public abstract <U> OGArraySuper<U> sin(T array1);
}
