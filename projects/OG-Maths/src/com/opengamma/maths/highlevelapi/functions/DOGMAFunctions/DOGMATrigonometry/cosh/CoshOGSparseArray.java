/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.cosh;

import java.util.Arrays;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Math.cosh on OGSparse
 */
public final class CoshOGSparseArray implements CoshAbstract<OGSparseArray> {
  private static CoshOGSparseArray s_instance = new CoshOGSparseArray();

  public static CoshOGSparseArray getInstance() {
    return s_instance;
  }

  private CoshOGSparseArray() {
  }

  @Override
  public OGDoubleArray cosh(OGSparseArray array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] dataArray1 = array1.getData();
    final int n = rowsArray1 * columnsArray1;
    double[] tmp = new double[n];
    Arrays.fill(tmp, 1.e0); //Math.cosh(0)=1

    for (int ir = 0; ir < columnsArray1; ir++) {
      for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) { // loops through elements of correct column
        tmp[rowIdx[i] + ir * rowsArray1] = Math.cosh(dataArray1[i]);
      }
    }

    return new OGDoubleArray(tmp, rowsArray1, columnsArray1);
  }

}
