/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy.CopyOGComplexArray;

/**
 * Full's OGComplexArrays
 */
public final class FullOGComplexArray extends FullAbstract<OGComplexArray> {
  private static FullOGComplexArray s_instance = new FullOGComplexArray();

  public static FullOGComplexArray getInstance() {
    return s_instance;
  }

  private FullOGComplexArray() {
  }

  private static CopyOGComplexArray s_copier = CopyOGComplexArray.getInstance();

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> full(OGComplexArray array1) {
    return s_copier.copy(array1);
  }

}
