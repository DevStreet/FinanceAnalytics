/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Sparse
 * @param <T> An OGArray type
 */
public interface SparseAbstract<T extends OGArray<? extends Number>> {
  
  OGArray<? extends Number> sparse(T array1);

}
