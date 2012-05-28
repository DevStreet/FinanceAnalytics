/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * 
 */
public interface DOGMASparseUtilitiesAPI {
  /* ADD */

  /**
   * Returns a full matrix. I.e. one will all the entries filled.
   * @param array a matrix to turn into a full matrix
   * @return a full matrix
   */
  OGArraySuper<Number> full(OGArraySuper<Number> array);

}
