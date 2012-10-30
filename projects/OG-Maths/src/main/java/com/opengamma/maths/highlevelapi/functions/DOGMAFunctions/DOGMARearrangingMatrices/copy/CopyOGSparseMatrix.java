/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;


import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * Copies sparse arrays
 */
public final class CopyOGSparseMatrix implements CopyAbstract<OGSparseMatrix> {
  private static CopyOGSparseMatrix s_instance = new CopyOGSparseMatrix();

  public static CopyOGSparseMatrix getInstance() {
    return s_instance;
  }

  private CopyOGSparseMatrix() {
  }

  @Override
  public OGSparseMatrix copy(OGSparseMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    double [] values = DenseMemoryManipulation.memcpy(array1.getData());
    int [] rowIdx = DenseMemoryManipulation.memcpy(array1.getRowIndex());
    int [] colPtr = DenseMemoryManipulation.memcpy(array1.getColumnPtr());    
    return new OGSparseMatrix(colPtr, rowIdx, values, rows, cols);
  }

}
