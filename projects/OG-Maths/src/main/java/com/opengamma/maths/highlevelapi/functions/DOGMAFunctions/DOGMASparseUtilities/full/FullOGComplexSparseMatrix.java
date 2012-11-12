/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;

/**
 * Full's OGSparseArrays
 */
public final class FullOGComplexSparseMatrix implements FullAbstract<OGComplexSparseMatrix> {
  private static FullOGComplexSparseMatrix s_instance = new FullOGComplexSparseMatrix();

  public static FullOGComplexSparseMatrix getInstance() {
    return s_instance;
  }

  private FullOGComplexSparseMatrix() {
  }

  @Override
  public OGComplexMatrix full(OGComplexSparseMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] data = array1.getData();
    double[] tmp = new double[2 * rows * cols];
    int dataPtr = 0;
    for (int ir = 0; ir < cols; ir++) {
      for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
        tmp[(rowIdx[i] + ir * rows) * 2] = data[dataPtr];
        tmp[(rowIdx[i] + ir * rows) * 2 + 1] = data[dataPtr + 1];
        dataPtr += 2;
      }
    }
    return new OGComplexMatrix(tmp, rows, cols);
  }

}
