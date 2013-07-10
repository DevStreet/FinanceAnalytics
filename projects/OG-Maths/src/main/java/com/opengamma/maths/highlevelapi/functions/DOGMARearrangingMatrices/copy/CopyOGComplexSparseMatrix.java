/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.copy;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Copy;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * Copies OGComplexSparseMatrix
 */
@DOGMAMethodHook(provides = Copy.class)
public final class CopyOGComplexSparseMatrix implements Copy<OGComplexSparseMatrix, OGComplexSparseMatrix> {
  @Override
  public OGComplexSparseMatrix eval(OGComplexSparseMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    double[] values = DenseMemoryManipulation.memcpy(array1.getData());
    int[] rowIdx = DenseMemoryManipulation.memcpy(array1.getRowIndex());
    int[] colPtr = DenseMemoryManipulation.memcpy(array1.getColumnPtr());
    return new OGComplexSparseMatrix(colPtr, rowIdx, values, rows, cols);
  }

}
