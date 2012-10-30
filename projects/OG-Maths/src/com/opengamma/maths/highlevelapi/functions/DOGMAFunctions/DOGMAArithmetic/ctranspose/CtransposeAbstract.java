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
public interface CtransposeAbstract<T extends OGArraySuper<? extends Number>> {
  
  T ctranspose(T array1);
}
