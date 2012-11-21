/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.Times;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Does elementwise OGDouble * OGSparse
 */
@DOGMAMethodHook(provides = Times.class)
public final class TimesOGMatrixOGSparseMatrix implements Times<OGArray<? extends Number>, OGMatrix, OGSparseMatrix> {

  private BLAS _localblas = new BLAS();

  @Override
  public OGArray<? extends Number> eval(OGMatrix array1, OGSparseMatrix array2) {
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
    OGArray<? extends Number> ret = null;

    if (rowsArray1 == 1 && columnsArray1 == 1) { // Single valued DenseMatrix times Sparse = scaled Sparse 
      n = array2.getData().length;
      tmp = new double[n];
      System.arraycopy(array2.getData(), 0, tmp, 0, n);
      final double[] singleDouble = array1.getData();
      final double deref = singleDouble[0];
      _localblas.dscal(n, deref, tmp, 1);
      retRows = rowsArray2;
      retCols = columnsArray2;

      ret = new OGSparseMatrix(array2.getColumnPtr(), array2.getRowIndex(), tmp, retRows, retCols);

    } else if (rowsArray2 == 1 && columnsArray2 == 1) { // Dense matrix times Single valued sparse = scaled dense
      n = array1.getData().length;
      tmp = new double[n];
      System.arraycopy(array1.getData(), 0, tmp, 0, n);
      final double[] singleDouble = array2.getData();
      final double deref = singleDouble[0];
      _localblas.dscal(n, deref, tmp, 1);
      retRows = rowsArray1;
      retCols = columnsArray1;
      ret = new OGMatrix(tmp, retRows, retCols);

    } else { // ew mul. Dense * Sparse -> Sparse scaled by dense entries
      Catchers.catchBadCommute(columnsArray1, "Columns in first array", columnsArray2, "Columns in second array");
      Catchers.catchBadCommute(rowsArray1, "Rows in first array", rowsArray2, "Rows in second array");
      retRows = rowsArray1;
      retCols = columnsArray1;
      n = array2.getData().length;
      tmp = new double[n];
      final double[] denseData = array1.getData();
      final double[] sparseData = array2.getData();
      // walk sparse array, look up in dense and mul()
      final int[] colPtr = array2.getColumnPtr();
      final int[] rowIdx = array2.getRowIndex();
      int denseOffset = 0;
      int ptr = 0;
      for (int ir = 0; ir < retCols; ir++) {
        denseOffset = ir * retRows;
        for (int i = colPtr[ir]; i <= colPtr[ir + 1] - 1; i++) {
          tmp[ptr] = sparseData[ptr] * denseData[rowIdx[i] + denseOffset];
          ptr++;
        }
      }
      ret = new OGSparseMatrix(array2.getColumnPtr(), array2.getRowIndex(), tmp, retRows, retCols);
    }
    return ret;
  }
}
