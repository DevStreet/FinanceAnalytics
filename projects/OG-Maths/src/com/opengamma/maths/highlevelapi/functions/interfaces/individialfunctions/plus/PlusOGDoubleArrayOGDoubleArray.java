/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.interfaces.individialfunctions.plus;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;

/**
 * 
 */
public final class PlusOGDoubleArrayOGDoubleArray extends PlusAbstract<OGDoubleArray, OGDoubleArray> {
  private static PlusOGDoubleArrayOGDoubleArray s_instance = new PlusOGDoubleArrayOGDoubleArray();

  public static PlusOGDoubleArrayOGDoubleArray getInstance() {
    return s_instance;
  }

  private PlusOGDoubleArrayOGDoubleArray() {
  }

  private BLAS _localblas = new BLAS();

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> plus(OGDoubleArray array1, OGDoubleArray array2) {

    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;

    int n = array1.getData().length;
    double[] tmp = new double[n];
    System.arraycopy(array1.getData(), 0, tmp, 0, n);

    // Actually adding arrays
    if (rowsArray1 == 1 && columnsArray1 == 1) {
      n = array2.getData().length;
      tmp = new double[n];
      System.arraycopy(array2.getData(), 0, tmp, 0, n);
      final double[] singleDouble = array1.getData();
      final double deref = singleDouble[0];
      for (int i = 0; i < n; i++) {
        tmp[i] += deref;
      }
      retRows = rowsArray2;
      retCols = columnsArray2;
    } else if (rowsArray2 == 1 && columnsArray2 == 1) {
      n = array1.getData().length;
      tmp = new double[n];
      System.arraycopy(array1.getData(), 0, tmp, 0, n);
      final double[] singleDouble = array2.getData();
      final double deref = singleDouble[0];
      for (int i = 0; i < n; i++) {
        tmp[i] += deref;
      }
      retRows = rowsArray1;
      retCols = columnsArray1;
    } else {
      n = array1.getData().length;
      _localblas.daxpy(n, 1e0, tmp, 1, array2.getData(), 1);
      retRows = rowsArray1;
      retCols = columnsArray1;
    }
    return new OGDoubleArray(tmp, retRows, retCols);
  }
}
