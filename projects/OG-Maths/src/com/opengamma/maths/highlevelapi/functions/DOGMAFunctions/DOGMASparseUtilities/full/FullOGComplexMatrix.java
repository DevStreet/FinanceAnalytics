/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy.CopyOGComplexMatrix;

/**
 * Full's OGComplexArrays
 */
public final class FullOGComplexMatrix implements FullAbstract<OGComplexMatrix> {
  private static FullOGComplexMatrix s_instance = new FullOGComplexMatrix();

  public static FullOGComplexMatrix getInstance() {
    return s_instance;
  }

  private FullOGComplexMatrix() {
  }

  private static CopyOGComplexMatrix s_copier = CopyOGComplexMatrix.getInstance();

  @Override
  public OGArray<? extends Number> full(OGComplexMatrix array1) {
    return s_copier.copy(array1);
  }

}
