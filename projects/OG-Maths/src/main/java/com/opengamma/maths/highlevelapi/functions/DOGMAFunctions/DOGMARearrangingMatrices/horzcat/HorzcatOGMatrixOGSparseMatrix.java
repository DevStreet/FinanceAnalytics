/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.horzcat;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.binary.Horzcat;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * HCats a sparse array to a dense 
 */
@DOGMAMethodHook(provides = Horzcat.class)
public final class HorzcatOGMatrixOGSparseMatrix implements Horzcat<OGSparseMatrix, OGMatrix, OGSparseMatrix> {

  @Override
  public OGSparseMatrix eval(OGMatrix array1, OGSparseMatrix array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);
    Catchers.catchBadCommute(array1.getNumberOfRows(), "array1", array2.getNumberOfRows(), "array2");

    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    //    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();

    // return a sparse array, unwind dense into sparse
    final double[] denseData = array1.getData();
    final double[] sparseData = array2.getData();
    final int[] colPtr = array2.getColumnPtr();
    final int[] rowIdx = array2.getRowIndex();

    // malloc
    double[] tmpData = new double[denseData.length + sparseData.length];
    int[] colPtrTmp = new int[colPtr.length + columnsArray1];
    int[] rowIdxTmp = new int[denseData.length + sparseData.length];

    // copy in the data
    System.arraycopy(denseData, 0, tmpData, 0, denseData.length);
    System.arraycopy(sparseData, 0, tmpData, denseData.length, sparseData.length);

    // copy in the rowIdx 
    System.arraycopy(rowIdx, 0, rowIdxTmp, denseData.length, rowIdx.length);

    // add in the rowIdx for the dense part, update colPtr
    int ptr = 0;
    for (int i = 0; i < columnsArray1; i++) {
      colPtrTmp[i] = i * rowsArray1;
      for (int j = 0; j < rowsArray1; j++) {
        rowIdxTmp[ptr++] = j;
      }
    }

    // update and add in colPtr from sparse array
    for (int i = 0; i < colPtr.length; i++) {
      colPtrTmp[columnsArray1 + i] = denseData.length + colPtr[i];
    }

    return new OGSparseMatrix(colPtrTmp, rowIdxTmp, tmpData, rowsArray1, columnsArray1 + columnsArray2);

  }
}
