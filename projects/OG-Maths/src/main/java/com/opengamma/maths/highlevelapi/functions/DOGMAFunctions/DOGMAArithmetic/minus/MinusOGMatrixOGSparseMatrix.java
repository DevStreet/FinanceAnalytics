/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.minus;

import java.util.Arrays;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Minus;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Subtracts an {@link OGMatrix} from an {@link OGSparseMatrix}    
 */
@DOGMAMethodHook(provides = Minus.class)
public final class MinusOGMatrixOGSparseMatrix implements Minus<OGMatrix, OGMatrix, OGSparseMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1, OGSparseMatrix array2) {
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;

    double[] tmp = null;

    OGMatrix retArray = null;

    // Actually subtracting arrays

    if (rowsArray1 == 1 && columnsArray1 == 1) { // Dense array is actually a single number, so we make the sparse array a OGDoubleArray and ADD
      final int n = columnsArray2 * rowsArray2;
      tmp = new double[n];
      final double singleDouble = array2.getData()[0];
      Arrays.fill(tmp, singleDouble);
      final int[] colPtr = array2.getColumnPtr();
      final int[] rowIdx = array2.getRowIndex();
      final double[] data = array2.getData();
      retRows = rowsArray2;
      retCols = columnsArray2;
      for (int ir = 0; ir < columnsArray2; ir++) {
        for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) { // loops through elements of correct column
          tmp[rowIdx[i] + ir * rowsArray2] -= data[i];
        }
      }
      retArray = new OGMatrix(tmp, retRows, retCols);

    } else if (rowsArray2 == 1 && columnsArray2 == 1) { // Sparse array is actually a single number, so we can just deref and add
      final int n = array1.getData().length;
      tmp = new double[n];
      System.arraycopy(array1.getData(), 0, tmp, 0, n);
      final double[] singleDouble = array2.getData();
      final double deref = singleDouble[0];
      for (int i = 0; i < n; i++) {
        tmp[i] = tmp[i] - deref;
      }
      retRows = rowsArray1;
      retCols = columnsArray1;
      retArray = new OGMatrix(tmp, retRows, retCols);
    } else { // Both arrays are full dimension, do sparse add    
      retRows = rowsArray1;
      retCols = columnsArray1;
      final int n = retRows * retCols;
      tmp = new double[n];
      System.arraycopy(array1.getData(), 0, tmp, 0, n);
      final int[] colPtr = array2.getColumnPtr();
      final int[] rowIdx = array2.getRowIndex();
      final double[] data = array2.getData();
      for (int ir = 0; ir < retCols; ir++) {
        for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
          tmp[rowIdx[i] + ir * retRows] -= data[i];
        }
      }
      retArray = new OGMatrix(tmp, retRows, retCols);
    }
    return retArray;
  }
}
