/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import java.util.Arrays;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Does elementwise OGSparse * OGSparse
 */
public final class TimesOGSparseArrayOGSparseArray implements TimesAbstract<OGSparseArray, OGSparseArray> {
  private static TimesOGSparseArrayOGSparseArray s_instance = new TimesOGSparseArrayOGSparseArray();

  public static TimesOGSparseArrayOGSparseArray getInstance() {
    return s_instance;
  }

  private TimesOGSparseArrayOGSparseArray() {
  }

  private BLAS _localblas = new BLAS();

  @Override
  public OGArraySuper<? extends Number> times(OGSparseArray array1, OGSparseArray array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);
    // if either is a single number then we just mul by that
    // else ew mul.
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;

    double[] tmp = null;
    int n;
    OGArraySuper<? extends Number> ret = null;

    if (rowsArray1 == 1 && columnsArray1 == 1) { // Single valued Sparse times Sparse = scaled Sparse 
      n = array2.getData().length;
      tmp = new double[n];
      System.arraycopy(array2.getData(), 0, tmp, 0, n);
      final double[] singleDouble = array1.getData();
      final double deref = singleDouble[0];
      _localblas.dscal(n, deref, tmp, 1);
      retRows = rowsArray2;
      retCols = columnsArray2;

      ret = new OGSparseArray(array2.getColumnPtr(), array2.getRowIndex(), tmp, retRows, retCols);

    } else if (rowsArray2 == 1 && columnsArray2 == 1) { // Sparse matrix times Single valued sparse = scaled dense
      n = array1.getData().length;
      tmp = new double[n];
      System.arraycopy(array1.getData(), 0, tmp, 0, n);
      final double[] singleDouble = array2.getData();
      final double deref = singleDouble[0];
      _localblas.dscal(n, deref, tmp, 1);
      retRows = rowsArray1;
      retCols = columnsArray1;
      ret = new OGSparseArray(array1.getColumnPtr(), array1.getRowIndex(), tmp, retRows, retCols);

    } else { // ew mul. Sparse * Sparse -> Sparse scaled by Sparse entries. So intersection of
      Catchers.catchBadCommute(columnsArray1, "Columns in first array", columnsArray2, "Columns in second array");
      Catchers.catchBadCommute(rowsArray1, "Rows in first array", rowsArray2, "Rows in second array");      
      retRows = rowsArray1;
      retCols = columnsArray1;

      double[] data1 = array1.getData();
      double[] data2 = array2.getData();
      n = data1.length + data2.length;
      tmp = new double[n]; // allocate max buffer, memcpy is prolly cheaper than precmputing intersections

      // compute intersections, do this on a per col basis, means we stay higher in cache, not that java pays a lot of attention to this
      int[] colPtr1 = array1.getColumnPtr();
      int[] colPtr2 = array2.getColumnPtr();
      int[] rowIdx1 = array1.getRowIndex();
      int[] rowIdx2 = array2.getRowIndex();

      int[] newColptr = new int[n];
      int[] newRowIdx = new int[n];

      int ridx1 = 0;
      int ridx2 = 0;
      int shift = 0;
      int ptr = 0;
      for (int ir = 0; ir < retCols; ir++) { // walk in columns
        newColptr[ir] = ptr;
        shift = colPtr2[ir];
        for (int i = colPtr1[ir]; i < colPtr1[ir + 1]; i++) { // this column array1
          ridx1 = rowIdx1[i];
          for (int j = shift; j < colPtr2[ir + 1]; j++) { // this column array2
            ridx2 = rowIdx2[j];
            if (ridx1 == ridx2) { // match found, store break, shift lower index for next cycle
              tmp[ptr] = data1[i] * data2[j];
              shift = j;
              newRowIdx[ptr] = ridx2;
              ptr++;
              break;
            }
          }
        }
      }
      newColptr[retCols ] = ptr;
      ret = new OGSparseArray(Arrays.copyOf(newColptr, retCols + 1), Arrays.copyOf(newRowIdx, ptr), Arrays.copyOf(tmp, ptr), retRows, retCols);
    }
    return ret;
  }
}
