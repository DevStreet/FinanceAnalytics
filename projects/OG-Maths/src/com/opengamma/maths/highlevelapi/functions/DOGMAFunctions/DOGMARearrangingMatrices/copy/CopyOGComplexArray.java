/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexArray;

/**
 * Copies OGComplexArrays 
 */
public final class CopyOGComplexArray extends CopyAbstract<OGComplexArray> {
  private static CopyOGComplexArray s_instance = new CopyOGComplexArray();

  public static CopyOGComplexArray getInstance() {
    return s_instance;
  }

  private CopyOGComplexArray() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> copy(OGComplexArray array1) {
    final int n = array1.getData().length;
    final double [] tmp = new double[n];
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    System.arraycopy(array1.getData(), 0, tmp, 0, n);
    return new OGComplexArray(tmp, rows, cols);
  }

}
