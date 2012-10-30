/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces;

import com.opengamma.maths.highlevelapi.datatypes.derived.OGSvdResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd.Svd.compute;

/**
 * Provides interface to the Linear Algebra calls
 */
public interface DOGMALinearAlgebraAPI {

  /**
   * Performs the singular value decomposition on OGArrays
   * @param array1 the matrix on which the singular value decomposition shall be performed
   * @param required the components of the SVD wanted by the user  
   * @return an {@link OGSvdResult} containing the components of the decomposition as specified by {@code required}
   */
  OGSvdResult svd(OGArray<? extends Number> array1, compute required);

}
