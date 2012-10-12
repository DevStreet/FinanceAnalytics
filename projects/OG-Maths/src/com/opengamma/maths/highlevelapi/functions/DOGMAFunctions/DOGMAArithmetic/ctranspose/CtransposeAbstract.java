/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Class holder for the transpose operation
 * @param <T> An OGArray type
 */
public abstract class CtransposeAbstract<T extends OGArraySuper<Number>> {
  public abstract <U> OGArraySuper<U> ctranspose(T array1);
}
