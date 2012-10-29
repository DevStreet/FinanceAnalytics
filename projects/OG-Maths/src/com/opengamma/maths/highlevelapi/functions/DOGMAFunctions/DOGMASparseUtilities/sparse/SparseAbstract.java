/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * Sparse
 * @param <T> An OGArray type
 */
public interface SparseAbstract<T extends OGArraySuper<? extends Number>> {
  
  OGArraySuper<? extends Number> sparse(T array1);

}
