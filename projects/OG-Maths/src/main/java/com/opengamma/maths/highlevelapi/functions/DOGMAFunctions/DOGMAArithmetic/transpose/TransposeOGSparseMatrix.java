/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.Transpose;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Transposes an OGSparseArray
 */
@DOGMAMethodHook(provides = Transpose.class)
public final class TransposeOGSparseMatrix implements Transpose<OGSparseMatrix, OGSparseMatrix> {
  @Override
  public OGSparseMatrix eval(OGSparseMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int retRows = columnsArray1, retCols = rowsArray1;
    double[] data = array1.getData();
    int[] rowIdx = array1.getRowIndex();
    int[] colPtr = array1.getColumnPtr();

    // malloc for return
    int[] retColPtr = new int[rowsArray1 + 1];
    int[] retRowIdx = new int[data.length];
    double[] retData = new double[data.length];

    // used for incrementing the position in the newly arranged data for a given element based on the offsets
    // computed in the retColPtr (col ptr for transposed array).
    int[] tmpColPtr = new int[rowsArray1 + 1];

    // compute counts of elements in each row so we know what the ragged structure looks like in CSR form
    for (int i = 0; i < rowIdx.length; i++) {
      retColPtr[rowIdx[i] + 1]++;
    }
    // accumulate the counts to create the colPtr of the transposed matrix
    for (int i = 0; i < rowsArray1; i++) {
      retColPtr[i + 1] += retColPtr[i];
    }
    // memcpy the new col ptr to the temporary array so that we can use the information for indexing into the new data structure 
    System.arraycopy(retColPtr, 0, tmpColPtr, 0, retColPtr.length);

    int thiselement; // reference to old element's position in new system's indexing
    // walk original data structure and assign to new one
    for (int ir = 0; ir < columnsArray1; ir++) {
      for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
        thiselement = tmpColPtr[rowIdx[i]]++; // look up original row index in the new col ptr, increment so that we write sequentially wrt rows 
        retRowIdx[thiselement] = ir;
        retData[thiselement] = data[i];
      }
    }

    return new OGSparseMatrix(retColPtr, retRowIdx, retData, retRows, retCols);
  }
}
