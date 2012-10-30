/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;

/**
 * Copies OGComplexArrays 
 */
public final class CopyOGComplexMatrix implements CopyAbstract<OGComplexMatrix> {
  private static CopyOGComplexMatrix s_instance = new CopyOGComplexMatrix();

  public static CopyOGComplexMatrix getInstance() {
    return s_instance;
  }

  private CopyOGComplexMatrix() {
  }

  @Override
  public OGComplexMatrix copy(OGComplexMatrix array1) {
    final int n = array1.getData().length;
    final double [] tmp = new double[n];
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    System.arraycopy(array1.getData(), 0, tmp, 0, n);
    return new OGComplexMatrix(tmp, rows, cols);
  }

}
