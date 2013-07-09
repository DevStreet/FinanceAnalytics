/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse;

import java.util.Arrays;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Sparse;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;

/**
 * Sparse's a OGComplexMatrix
 */
@DOGMAMethodHook(provides = Sparse.class)
public final class SparseOGComplexMatrix implements Sparse<OGComplexSparseMatrix, OGComplexMatrix> {

  @Override
  public OGComplexSparseMatrix eval(OGComplexMatrix array1) {
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
    double[] dataTmp = new double[2 * els];
    int[] colPtrTmp = new int[els + 1];
    int[] rowIdxTmp = new int[els];

    // we need unwind the array m into coordinate form
    int ptr = 0, dataptr = 0;
    int i;
    double realentry = 0;
    double cmplxentry = 0;
    int ir;
    for (i = 0; i < cols; i++) {
      colPtrTmp[i] = ptr;
      ir = i * rows;
      for (int j = 0; j < rows; j++) {
        realentry = data[2 * (ir + j)];
        cmplxentry = data[2 * (ir + j) + 1];
        if (realentry != 0.0e0 || cmplxentry != 0.e0) {
          rowIdxTmp[ptr] = j;
          dataTmp[dataptr] = realentry;
          dataTmp[dataptr + 1] = cmplxentry;
          ptr++;
          dataptr += 2;
        }
      }
    }
    colPtrTmp[i] = ptr;

    // return correct 0 to correct length of the vector buffers
    values = Arrays.copyOfRange(dataTmp, 0, dataptr);
    colPtr = Arrays.copyOfRange(colPtrTmp, 0, i + 1); // yes, the +1 is correct, it allows the computation of the number of elements in the final row!
    rowIdx = Arrays.copyOfRange(rowIdxTmp, 0, ptr);
    return new OGComplexSparseMatrix(colPtr, rowIdx, values, rows, cols);
  }

}
