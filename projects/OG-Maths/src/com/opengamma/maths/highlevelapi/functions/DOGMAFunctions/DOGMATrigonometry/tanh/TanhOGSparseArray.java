/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tanh;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Math.tanh on OGSparse
 */
public final class TanhOGSparseArray extends TanhAbstract<OGSparseArray> {
  private static TanhOGSparseArray s_instance = new TanhOGSparseArray();

  public static TanhOGSparseArray getInstance() {
    return s_instance;
  }

  private TanhOGSparseArray() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public OGDoubleArray tanh(OGSparseArray array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] dataArray1 = array1.getData();
    final int n = rowsArray1 * columnsArray1;
    double[] tmp = new double[n]; //Math.tanh(0)=0

    for (int ir = 0; ir < columnsArray1; ir++) {
      for (int i = colPtr[ir]; i <= colPtr[ir + 1] - 1; i++) { // loops through elements of correct column
        tmp[rowIdx[i] + ir * rowsArray1] = Math.tanh(dataArray1[i]);
      }
    }

    return new OGDoubleArray(tmp, rowsArray1, columnsArray1);
  }

}
