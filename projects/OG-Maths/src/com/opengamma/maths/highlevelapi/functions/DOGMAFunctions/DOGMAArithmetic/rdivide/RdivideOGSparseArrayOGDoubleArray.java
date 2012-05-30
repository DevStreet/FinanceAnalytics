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
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Does elementwise OGSparse / OGDouble
 */
public final class RdivideOGSparseArrayOGDoubleArray extends RdivideAbstract<OGSparseArray, OGDoubleArray> {
  private static RdivideOGSparseArrayOGDoubleArray s_instance = new RdivideOGSparseArrayOGDoubleArray();

  public static RdivideOGSparseArrayOGDoubleArray getInstance() {
    return s_instance;
  }

  private RdivideOGSparseArrayOGDoubleArray() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> rdivide(OGSparseArray array1, OGDoubleArray array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);
    // if either is a single number then we just div by that
    // else ew div.
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] denseData = array2.getData();
    final double[] sparseData = array1.getData();
    int ptr = 0;
    double[] tmp = null;
    int n;
    int denseOffset = 0;
    OGArraySuper<Number> ret = null;

    if (rowsArray1 == 1 && columnsArray1 == 1) { // Single valued SparseMatrix rdivide dense
      n = rowsArray2 * columnsArray2;
      tmp = new double[n];
      final double deref = sparseData[0];
      Arrays.fill(tmp, deref);
      for (int i = 0; i < n; i++) {
        tmp[i] /= denseData[i];
      }
      retRows = rowsArray2;
      retCols = columnsArray2;
      ret = new OGDoubleArray(tmp, retRows, retCols);
    } else if (rowsArray2 == 1 && columnsArray2 == 1) { // Sparse matrix rdivide single dense
      n = sparseData.length;
      tmp = new double[n];
      System.arraycopy(sparseData, 0, tmp, 0, n);
      final double deref = denseData[0];
      for (int i = 0; i < n; i++) {
        tmp[i] /= deref;
      }
      retRows = rowsArray1;
      retCols = columnsArray1;
      ret = new OGSparseArray(colPtr, rowIdx, tmp, rowsArray1, columnsArray1);
    } else { // Sparse/Dense, ignore division in sparse 0 spaces 
      Catchers.catchBadCommute(rowsArray1, "Rows in arg 1", rowsArray2, "Rows in arg 2");
      Catchers.catchBadCommute(columnsArray1, "Columns in arg 1", columnsArray2, "Columns in arg 2");
      retRows = rowsArray1;
      retCols = columnsArray1;
      n = sparseData.length;
      tmp = new double[n];
      System.arraycopy(sparseData, 0, tmp, 0, n);
      for (int ir = 0; ir < retCols; ir++) {
        denseOffset = ir * retRows;
        for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
          tmp[ptr] = sparseData[ptr] / denseData[rowIdx[i] + denseOffset];
          ptr++;
        }
      }
      ret = new OGSparseArray(colPtr, rowIdx, tmp, rowsArray1, columnsArray1);
    }
    return ret;
  }

}
