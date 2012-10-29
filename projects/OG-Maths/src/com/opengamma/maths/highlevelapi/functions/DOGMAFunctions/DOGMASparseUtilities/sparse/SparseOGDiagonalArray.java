/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;

/**
 * Sparse's a sparse array
 */
public final class SparseOGDiagonalArray extends SparseAbstract<OGDiagonalArray> {

  private static SparseOGDiagonalArray s_instance = new SparseOGDiagonalArray();

  public static SparseOGDiagonalArray getInstance() {
    return s_instance;
  }

  private SparseOGDiagonalArray() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> sparse(OGDiagonalArray array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();

    final double[] tmpData = new double[data.length];
    System.arraycopy(data, 0, tmpData, 0, data.length);

    int[] tmpColPtr = new int[cols + 1];
    for (int i = 0; i < data.length; i++) {
      tmpColPtr[i] = i;
    }
    for (int i = data.length; i < cols + 1; i++) {
      tmpColPtr[i] = data.length;
    }

    int[] tmpRowIdx = new int[data.length];
    for (int i = 0; i < data.length; i++) {
      tmpRowIdx[i] = i;
    }

    return new OGSparseArray(tmpColPtr, tmpRowIdx, tmpData, rows, cols);
  }
}
