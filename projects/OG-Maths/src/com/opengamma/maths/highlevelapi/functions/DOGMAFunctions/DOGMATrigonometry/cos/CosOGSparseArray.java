/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.cos;

import java.util.Arrays;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Math.cos on OGSparse
 */
public final class CosOGSparseArray implements CosAbstract<OGSparseMatrix> {
  private static CosOGSparseArray s_instance = new CosOGSparseArray();

  public static CosOGSparseArray getInstance() {
    return s_instance;
  }

  private CosOGSparseArray() {
  }

  @Override
  public OGMatrix cos(OGSparseMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] dataArray1 = array1.getData();
    final int n = rowsArray1 * columnsArray1;
    double[] tmp = new double[n];
    Arrays.fill(tmp, 1.e0); //Math.cos(0)=1

    for (int ir = 0; ir < columnsArray1; ir++) {
      for (int i = colPtr[ir]; i <= colPtr[ir + 1] - 1; i++) { // loops through elements of correct column
        tmp[rowIdx[i] + ir * rowsArray1] = Math.cos(dataArray1[i]);
      }
    }

    return new OGMatrix(tmp, rowsArray1, columnsArray1);
  }

}
