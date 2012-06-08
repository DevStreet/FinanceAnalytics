/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;

/**
 * The IO interface for DOGMA
 */
public interface DOGMAIOAPI {

  /**
   * Smart import of a matrix to an suitable storage representation
   * @param aMatrix a matrix to convert
   * @return an OGArraySuper type depending on the data in aMatrix
   */
  OGArraySuper<Number> smartImport(double[][] aMatrix);

}
