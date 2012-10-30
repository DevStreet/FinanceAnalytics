/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

/**
 * 
 */
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Copy
 * @param <T> An OGArray type
 */
public interface CopyAbstract<T extends OGArray<? extends Number>> {
  
  T copy(T array1);
}

