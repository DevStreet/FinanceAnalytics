/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.sinh;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Math.sinh overload
 * @param <T> An OGArray type
 */
public abstract class SinhAbstract<T extends OGArraySuper<Number>> {
  public abstract <U> OGArraySuper<U> sinh(T array1);
}
