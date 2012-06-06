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
 * Does elementwise OGDouble / OGSparse
 */
public final class RdivideOGDoubleArrayOGSparseArray extends RdivideAbstract<OGDoubleArray, OGSparseArray> {
  private static RdivideOGDoubleArrayOGSparseArray s_instance = new RdivideOGDoubleArrayOGSparseArray();

  public static RdivideOGDoubleArrayOGSparseArray getInstance() {
    return s_instance;
  }

  private RdivideOGDoubleArrayOGSparseArray() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> rdivide(OGDoubleArray array1, OGSparseArray array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);
    // if either is a single number then we just mul by that
    // else ew mul.
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;
    final int[] colPtr = array2.getColumnPtr();
    final int[] rowIdx = array2.getRowIndex();
    final double[] denseData = array1.getData();
    final double[] sparseData = array2.getData();
    int ptr = 0;
    double[] tmp = null;
    int n;
    int denseOffset = 0;
    OGArraySuper<Number> ret = null;

    if (rowsArray1 == 1 && columnsArray1 == 1) { // Single valued DenseMatrix rdivide Sparse
      n = rowsArray2 * columnsArray2;
      retRows = rowsArray2;
      retCols = columnsArray2;
      tmp = new double[n];
      final double deref = denseData[0];
      if (deref != deref) {
        Arrays.fill(tmp, Double.NaN);
      } else {
        Arrays.fill(tmp, Double.POSITIVE_INFINITY);
      }
      for (int ir = 0; ir < retCols; ir++) {
        denseOffset = ir * retRows;
        for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
          tmp[denseOffset + rowIdx[i]] = deref / sparseData[ptr];
          ptr++;
        }
      }
      ret = new OGDoubleArray(tmp, retRows, retCols);
    } else if (rowsArray2 == 1 && columnsArray2 == 1) { // Dense matrix rdiv Single valued sparse 
      n = denseData.length;
      tmp = new double[n];
      System.arraycopy(denseData, 0, tmp, 0, n);
      final double deref = sparseData[0];
      for (int i = 0; i < n; i++) {
        tmp[i] /= deref;
      }
      retRows = rowsArray1;
      retCols = columnsArray1;
      ret = new OGDoubleArray(tmp, retRows, retCols);
    } else { // Dense / Sparse -> Infs and divisions
      Catchers.catchBadCommute(rowsArray1, "Rows in arg 1", rowsArray2, "Rows in arg 2");
      Catchers.catchBadCommute(columnsArray1, "Columns in arg 1", columnsArray2, "Columns in arg 2");
      retRows = rowsArray1;
      retCols = columnsArray1;
      n = array1.getData().length;
      tmp = new double[n];
      // whizz through array1, look for NaN, if it is present then we set tmp accordingly, this is so IEEE NaN/number -> NaN is implemented correctly.
      // at the same time copysign of full matrix entry to inf so that sign is preserved
      for (int i = 0; i < denseData.length; i++) {
        tmp[i] = Math.copySign(Double.POSITIVE_INFINITY, denseData[i]);
        if (denseData[i] != denseData[i]) {
          tmp[i] = Double.NaN;
        }
      }
      for (int ir = 0; ir < retCols; ir++) {
        denseOffset = ir * retRows;
        for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
          tmp[rowIdx[i] + denseOffset] = denseData[rowIdx[i] + denseOffset] / sparseData[ptr];
          ptr++;
        }
      }
      ret = new OGDoubleArray(tmp, retRows, retCols);
    }
    return ret;
  }
}
