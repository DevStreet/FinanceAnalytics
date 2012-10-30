/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * Full's OGDiagonalArrays
 */
public final class FullOGDiagonalMatrix implements FullAbstract<OGDiagonalMatrix> {

  private static FullOGDiagonalMatrix s_instance = new FullOGDiagonalMatrix();

  public static FullOGDiagonalMatrix getInstance() {
    return s_instance;
  }

  private FullOGDiagonalMatrix() {
  }

  @Override
  public OGMatrix full(OGDiagonalMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();
    double[] tmp = new double[rows * cols];
    for (int i = 0; i < data.length; i++) {
      tmp[i * rows + i] = data[i];
    }
    return new OGMatrix(tmp, rows, cols);
  }

}
