/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;

/**
 * Copies OGDoubleArrays 
 */
public final class CopyOGDoubleArray implements CopyAbstract<OGDoubleArray> {
  private static CopyOGDoubleArray s_instance = new CopyOGDoubleArray();

  public static CopyOGDoubleArray getInstance() {
    return s_instance;
  }

  private CopyOGDoubleArray() {
  }

  @Override
  public OGDoubleArray copy(OGDoubleArray array1) {
    final int n = array1.getData().length;
    final double [] tmp = new double[n];
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    System.arraycopy(array1.getData(), 0, tmp, 0, n);
    return new OGDoubleArray(tmp, rows, cols);
  }

}
