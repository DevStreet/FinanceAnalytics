/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAinterfaces;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctionCollection.DOGMAIO.orientation;

/**
 * The IO interface for DOGMA
 */
public interface DOGMAIOAPI {

  /**
   * Smart import of a matrix to an suitable storage representation
   * @param aMatrix a matrix to convert
   * @return an OGArraySuper type depending on the data in aMatrix
   */
  OGArray<? extends Number> smartImport(double[][] aMatrix);

  /**
   * @param aVector a vector
   * @param o the orientation the vector should be
   * @return an OGArraySuper type 
   */
  OGArray<? extends Number> smartImport(double[] aVector, orientation o);

}
