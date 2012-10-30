/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd;

import com.opengamma.maths.highlevelapi.datatypes.derived.OGSvdResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd.compute;

/**
 *  Abstract for SVD calls
 *  @param <T> is an OGArraySuper type
 */
public interface SvdAbstract<T extends OGArray<? extends Number>> {
  
  OGSvdResult svd(T array1, compute these);
}
