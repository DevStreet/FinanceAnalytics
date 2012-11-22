/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Full;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;

/**
 * Full's OGSparseArrays
 */
@DOGMAMethodHook(provides = Full.class)
public final class FullOGComplexSparseMatrix implements Full<OGComplexMatrix, OGComplexSparseMatrix> {

  @Override
  public OGComplexMatrix eval(OGComplexSparseMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] data = array1.getData();
    double[] tmp = new double[2 * rows * cols];
    int idx, ptr = 0;
    for (int ir = 0; ir < cols; ir++) {
      for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
        idx = 2 * (rowIdx[i] + ir * rows);
        tmp[idx] = data[ptr++];
        tmp[idx + 1] = data[ptr++];
      }
    }
    return new OGComplexMatrix(tmp, rows, cols);
  }

}
