/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.acos;

import java.util.Arrays;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Math.acos on OGSparse
 */
public final class AcosOGSparseArray implements AcosAbstract<OGSparseMatrix> {
  private static AcosOGSparseArray s_instance = new AcosOGSparseArray();

  public static AcosOGSparseArray getInstance() {
    return s_instance;
  }

  private AcosOGSparseArray() {
  }

  @Override
  public OGMatrix acos(OGSparseMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] dataArray1 = array1.getData();
    final int n = rowsArray1 * columnsArray1;
    final double val = Math.acos(0);
    double[] tmp = new double[n];
    Arrays.fill(tmp, val);

    for (int ir = 0; ir < columnsArray1; ir++) {
      for (int i = colPtr[ir]; i <= colPtr[ir + 1] - 1; i++) { // loops through elements of correct column
        tmp[rowIdx[i] + ir * rowsArray1] = Math.acos(dataArray1[i]);
      }
    }

    return new OGMatrix(tmp, rowsArray1, columnsArray1);
  }

}
