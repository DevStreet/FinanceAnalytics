/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy.CopyOGMatrix;


/**
 * Full's OGDoubleArrays
 */
public final class FullOGMatrix implements FullAbstract<OGMatrix> {
  private static FullOGMatrix s_instance = new FullOGMatrix();

  public static FullOGMatrix getInstance() {
    return s_instance;
  }

  private FullOGMatrix() {
  }

  private static CopyOGMatrix s_copier = CopyOGMatrix.getInstance();  
  
  @Override
  public OGMatrix full(OGMatrix array1) {
    return s_copier.copy(array1);
  }

}
