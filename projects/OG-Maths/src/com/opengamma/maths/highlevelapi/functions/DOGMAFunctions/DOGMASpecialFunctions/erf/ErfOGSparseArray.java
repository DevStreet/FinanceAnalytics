/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialFunctions.erf;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.DERF;

/**
 * Erf on Sparse
 */
public final class ErfOGSparseArray implements ErfAbstract<OGSparseArray> {
  private static ErfOGSparseArray s_instance = new ErfOGSparseArray();

  public static ErfOGSparseArray getInstance() {
    return s_instance;
  }

  private ErfOGSparseArray() {
  }

  @Override
  public OGSparseArray erf(OGSparseArray array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final int[] rowIdx = array1.getRowIndex();
    final int[] colPtr = array1.getColumnPtr();
    final double[] dataArray1 = array1.getData();
    final int n = dataArray1.length;

    double[] tmp = new double[n];
    for (int i = 0; i < n; i++) {
      tmp[i] = DERF.derf(dataArray1[i]);
    }

    return new OGSparseArray(colPtr, rowIdx, tmp, rowsArray1, columnsArray1);
  }
}
