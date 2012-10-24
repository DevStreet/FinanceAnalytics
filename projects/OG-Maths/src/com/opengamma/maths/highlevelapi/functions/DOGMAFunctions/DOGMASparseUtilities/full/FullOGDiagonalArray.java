/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;

/**
 * Full's OGDiagonalArrays
 */
public final class FullOGDiagonalArray extends FullAbstract<OGDiagonalArray> {

  private static FullOGDiagonalArray s_instance = new FullOGDiagonalArray();

  public static FullOGDiagonalArray getInstance() {
    return s_instance;
  }

  private FullOGDiagonalArray() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> full(OGDiagonalArray array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();
    double[] tmp = new double[rows * cols];
    for (int i = 0; i < data.length; i++) {
      tmp[i * rows + i] = data[i];
    }
    return new OGDoubleArray(tmp, rows, cols);
  }

}
