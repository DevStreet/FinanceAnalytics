/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.rdivide;

import java.util.Arrays;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;

/**
 * Does elementwise OGSparse * OGSparse
 */
public final class RdivideOGSparseArrayOGSparseArray extends RdivideAbstract<OGSparseArray, OGSparseArray> {
  private static RdivideOGSparseArrayOGSparseArray s_instance = new RdivideOGSparseArrayOGSparseArray();

  public static RdivideOGSparseArrayOGSparseArray getInstance() {
    return s_instance;
  }

  private RdivideOGSparseArrayOGSparseArray() {
  }

  private BLAS _localblas = new BLAS();

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> rdivide(OGSparseArray array1, OGSparseArray array2) {

    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;
    int[] colPtr1 = array1.getColumnPtr();
    int[] colPtr2 = array2.getColumnPtr();
    int[] rowIdx1 = array1.getRowIndex();
    int[] rowIdx2 = array2.getRowIndex();
    double[] data1 = array1.getData();
    double[] data2 = array2.getData();

    double[] tmp = null;
    int n;
    int denseOffset;
    int ptr = 0;
    OGArraySuper<Number> ret = null;

    if (rowsArray1 == 1 && columnsArray1 == 1) { // Single valued Sparse rdiv Sparse  
      retRows = rowsArray2;
      retCols = columnsArray2;
      n = rowsArray2 * columnsArray2;
      tmp = new double[n];
      final double deref = data1[0];
      Arrays.fill(tmp, Double.POSITIVE_INFINITY);

      for (int ir = 0; ir < retCols; ir++) {
        denseOffset = ir * retRows;
        for (int i = colPtr2[ir]; i < colPtr2[ir + 1]; i++) {
          tmp[rowIdx2[i] + denseOffset] = deref / data2[ptr];
          ptr++;
        }
      }
      ret = new OGDoubleArray(tmp, retRows, retCols);

    } else if (rowsArray2 == 1 && columnsArray2 == 1) { // Sparse matrix rdiv Single valued sparse. 0's ignored
      retRows = rowsArray1;
      retCols = columnsArray1;
      final double deref = data2[0];
      n = data1.length;
      tmp = new double[n];
      System.arraycopy(data1, 0, tmp, 0, n);
      for (int i = 0; i < n; i++) {
        tmp[i] /= deref;
      }
      ret = new OGSparseArray(colPtr1, rowIdx1, tmp, retRows, retCols);
    } else { // Sparse rdiv Sparse 
      retRows = rowsArray1;
      retCols = columnsArray1;

      // most entries will be 0/0 = NaN, so malloc full NaN
      n = retRows * retCols;
      tmp = new double[n];

      Arrays.fill(tmp, Double.NaN);

      int rowFound1 = 0;
      int rowFound2 = 0;
      int ptrd1 = 0;
      int ptrd2 = 0;

      for (int ir = 0; ir < retCols; ir++) { // walk in columns
        int i = colPtr1[ir];
        int j = colPtr2[ir];
        rowFound1 = 0;
        rowFound2 = 0;
        denseOffset = ir * retRows;
        // this is a merge with arithmetic going on at the same time
        while (i < colPtr1[ir + 1] && j < colPtr2[ir + 1]) {
          rowFound1 = rowIdx1[i];
          rowFound2 = rowIdx2[j];
          if (rowFound1 < rowFound2) { // entry exists in stream 1
            tmp[denseOffset + rowFound1] = data1[ptrd1] / 0.e0;
            ptrd1++;
            i++;
          } else if (rowFound1 > rowFound2) { // entry exists in stream 2
            tmp[denseOffset + rowFound2] = 0.e0 / data2[ptrd2];
            ptrd2++;
            j++;
          } else { // entry exists in both streams
            tmp[denseOffset + rowFound1] = data1[ptrd1] / data2[ptrd2];
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
            tmp[denseOffset + rowIdx1[k]] = data1[ptrd1] / 0.e0;
            ptr++;
            ptrd1++;
          }
        } else {
          for (int k = j; k < colPtr2[ir + 1]; k++) {
            tmp[denseOffset + rowIdx2[k]] = 0.e0 / data2[ptrd2];
            ptr++;
            ptrd2++;
          }
        }

      }

      ret = new OGDoubleArray(tmp, retRows, retCols);
    }
    return ret;
  }
}
