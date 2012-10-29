/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse;

import java.util.Arrays;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;

/**
 * Sparse's a OGDoubleArray
 */
public final class SparseOGDoubleArray implements SparseAbstract<OGDoubleArray> {

  private static SparseOGDoubleArray s_instance = new SparseOGDoubleArray();

  public static SparseOGDoubleArray getInstance() {
    return s_instance;
  }

  private SparseOGDoubleArray() {
  }

  @Override
  public OGSparseArray sparse(OGDoubleArray array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();
    double[] values;
    int[] colPtr;
    int[] rowIdx;
    int els;
    //get number of elements
    els = rows * cols;

    // tmp arrays, in case we get in a fully populated matrix, intelligent design upstream should ensure that this is overkill!
    double[] dataTmp = new double[els];
    int[] colPtrTmp = new int[els + 1];
    int[] rowIdxTmp = new int[els];

    // we need unwind the array m into coordinate form
    int ptr = 0;
    int i;
//    int localMaxEntrisInACol;
    double entry = 0;
    int ir;
    for (i = 0; i < cols; i++) {
      colPtrTmp[i] = ptr;
//      localMaxEntrisInACol = 0;
      ir = i * rows;
      for (int j = 0; j < rows; j++) {
        entry = data[ir + j];
        if (entry != 0.0e0) {
          rowIdxTmp[ptr] = j;
          dataTmp[ptr] = entry;
          ptr++;
//          localMaxEntrisInACol++;
        }
      }

    }
    colPtrTmp[i] = ptr;

    // return correct 0 to correct length of the vector buffers
    values = Arrays.copyOfRange(dataTmp, 0, ptr);
    colPtr = Arrays.copyOfRange(colPtrTmp, 0, i + 1); // yes, the +1 is correct, it allows the computation of the number of elements in the final row!
    rowIdx = Arrays.copyOfRange(rowIdxTmp, 0, ptr);
    return new OGSparseArray(colPtr, rowIdx, values, rows, cols);
  }

}
