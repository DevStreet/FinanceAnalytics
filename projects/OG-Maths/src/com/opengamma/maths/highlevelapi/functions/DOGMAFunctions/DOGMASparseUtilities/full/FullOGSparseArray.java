/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;

/**
 * Full's OGSparseArrays
 */
public final class FullOGSparseArray extends FullAbstract<OGSparseArray> {
  private static FullOGSparseArray s_instance = new FullOGSparseArray();

  public static FullOGSparseArray getInstance() {
    return s_instance;
  }

  private FullOGSparseArray() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> full(OGSparseArray array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] data = array1.getData();
    double[] tmp = new double[rows * cols];
    for (int ir = 0; ir < cols; ir++) {
      for (int i = colPtr[ir]; i <= colPtr[ir + 1] - 1; i++) {
        tmp[rowIdx[i] + ir * rows] = data[i];
      }
    }
    return new OGDoubleArray(tmp, rows, cols);
  }

}
