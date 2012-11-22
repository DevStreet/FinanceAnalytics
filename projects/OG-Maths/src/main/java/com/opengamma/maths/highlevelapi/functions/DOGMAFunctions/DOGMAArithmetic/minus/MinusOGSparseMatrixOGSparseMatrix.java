/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.minus;

import java.util.Arrays;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Minus;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Subtracts an {@link OGSparseMatrix} from an {@link OGSparseMatrix}   
 */
@DOGMAMethodHook(provides = Minus.class)
public final class MinusOGSparseMatrixOGSparseMatrix implements Minus<OGArray<? extends Number>, OGSparseMatrix, OGSparseMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGSparseMatrix array1, OGSparseMatrix array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);

    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;

    double[] tmp = null;

    OGArray<? extends Number> retArray = null;

    // Actually adding arrays

    // One array is actually a single number, so we make the an OGDoubleArray and ADD
    boolean single = false;
    OGSparseMatrix singleNumber = null, fullSparse = null;
    if ((rowsArray1 == 1 && columnsArray1 == 1) || (rowsArray2 == 1 && columnsArray2 == 1)) {
      single = true;
      if (rowsArray1 == 1) {
        singleNumber = array1;
        fullSparse = array2;
      } else {
        singleNumber = array2;
        fullSparse = array1;
      }
    }

    if (single) {
      final int n = fullSparse.getNumberOfColumns() * fullSparse.getNumberOfRows();
      tmp = new double[n];
      final double singleDouble = singleNumber.getData()[0];
      Arrays.fill(tmp, singleDouble);
      final int[] colPtr = fullSparse.getColumnPtr();
      final int[] rowIdx = fullSparse.getRowIndex();
      final double[] data = fullSparse.getData();
      retRows = fullSparse.getNumberOfRows();
      retCols = fullSparse.getNumberOfColumns();
      for (int ir = 0; ir < retCols; ir++) {
        for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) { // loops through elements of correct column
          tmp[rowIdx[i] + ir * retRows] -= data[i];
        }
      }
      retArray = new OGMatrix(tmp, retRows, retCols);
    } else { // Both arrays are full dimension, do sparse add    
      retRows = rowsArray1;
      retCols = columnsArray1;

      double[] data1 = array1.getData();
      double[] data2 = array2.getData();
      int n = data1.length + data2.length;
      tmp = new double[n]; // allocate max buffer, memcpy is prolly cheaper than precomputing intersections

      int[] colPtr1 = array1.getColumnPtr();
      int[] colPtr2 = array2.getColumnPtr();
      int[] rowIdx1 = array1.getRowIndex();
      int[] rowIdx2 = array2.getRowIndex();

      int[] newColptr = new int[n];
      int[] newRowIdx = new int[n];

      int rowFound1 = 0;
      int rowFound2 = 0;
      int ptrd1 = 0;
      int ptrd2 = 0;
      int ptr = 0;

      for (int ir = 0; ir < retCols; ir++) { // walk in columns
        newColptr[ir] = ptr;
        // this is an infuriating merge, like the mergesort alg, but with the irritation of piles of indexes and having to do arithmetic in the middle
        int i = colPtr1[ir];
        int j = colPtr2[ir];
        rowFound1 = 0;
        rowFound2 = 0;
        while (i < colPtr1[ir + 1] && j < colPtr2[ir + 1]) {
          rowFound1 = rowIdx1[i];
          rowFound2 = rowIdx2[j];
          if (rowFound1 < rowFound2) { // entry exists in stream 1
            tmp[ptr] = -data1[ptrd1];
            newRowIdx[ptr] = rowFound1;
            ptrd1++;
            i++;
          } else if (rowFound1 > rowFound2) { // entry exists in stream 2
            tmp[ptr] = -data2[ptrd2];
            newRowIdx[ptr] = rowFound2;
            ptrd2++;
            j++;
          } else { // entry exists in both streams
            tmp[ptr] = data1[ptrd1] - data2[ptrd2];
            newRowIdx[ptr] = rowFound1;
            ptrd1++;
            ptrd2++;
            i++;
            j++;
          }
          ptr++;
        } // end while

        // clean up as one col has more entries than the other.
        if (i < colPtr1[ir + 1]) {
          for (int k = i; k < colPtr1[ir + 1]; k++) {
            tmp[ptr] = -data1[ptrd1];
            newRowIdx[ptr] = rowIdx1[k];
            ptr++;
            ptrd1++;
          }
        } else {
          for (int k = j; k < colPtr2[ir + 1]; k++) {
            tmp[ptr] = -data2[ptrd2];
            newRowIdx[ptr] = rowIdx2[k];
            ptr++;
            ptrd2++;
          }
        }

      }
      newColptr[retCols] = ptr;
      retArray = new OGSparseMatrix(Arrays.copyOf(newColptr, retCols + 1), Arrays.copyOf(newRowIdx, ptr), Arrays.copyOf(tmp, ptr), retRows, retCols);
    }
    return retArray;
  }
}
