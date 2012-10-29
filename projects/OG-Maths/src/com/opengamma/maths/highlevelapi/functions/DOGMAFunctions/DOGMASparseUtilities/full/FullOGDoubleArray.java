/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy.CopyOGDoubleArray;


/**
 * Full's OGDoubleArrays
 */
public final class FullOGDoubleArray implements FullAbstract<OGDoubleArray> {
  private static FullOGDoubleArray s_instance = new FullOGDoubleArray();

  public static FullOGDoubleArray getInstance() {
    return s_instance;
  }

  private FullOGDoubleArray() {
  }

  private static CopyOGDoubleArray s_copier = CopyOGDoubleArray.getInstance();  
  
  @Override
  public OGDoubleArray full(OGDoubleArray array1) {
    return s_copier.copy(array1);
  }

}
