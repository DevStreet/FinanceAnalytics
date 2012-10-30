/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy.CopyOGComplexArray;

/**
 * Full's OGComplexArrays
 */
public final class FullOGComplexArray implements FullAbstract<OGComplexMatrix> {
  private static FullOGComplexArray s_instance = new FullOGComplexArray();

  public static FullOGComplexArray getInstance() {
    return s_instance;
  }

  private FullOGComplexArray() {
  }

  private static CopyOGComplexArray s_copier = CopyOGComplexArray.getInstance();

  @Override
  public OGArray<? extends Number> full(OGComplexMatrix array1) {
    return s_copier.copy(array1);
  }

}
