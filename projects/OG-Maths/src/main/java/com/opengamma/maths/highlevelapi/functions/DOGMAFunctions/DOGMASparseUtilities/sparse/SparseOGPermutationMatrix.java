/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Sparse;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Sparse's permutation arrays
 */
@DOGMAMethodHook(provides = Sparse.class)
public final class SparseOGPermutationMatrix implements Sparse<OGSparseMatrix, OGPermutationMatrix> {

  @Override
  public OGSparseMatrix eval(OGPermutationMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] data = array1.getData();

    final double[] tmpData = new double[data.length];
    for (int i = 0; i < data.length; i++) {
      tmpData[i] = 1;
    }

    int[] tmpColPtr = new int[cols + 1];
    for (int i = 0; i < data.length; i++) {
      tmpColPtr[i] = i;
    }
    tmpColPtr[cols] = rows;

    int[] tmpRowIdx = new int[data.length];
    for (int i = 0; i < rows; i++) {
      tmpRowIdx[i] = data[i];
    }

    return new OGSparseMatrix(tmpColPtr, tmpRowIdx, tmpData, rows, cols);
  }
}
