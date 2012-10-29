/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Element wise multiply 
 */
public final class TimesOGDoubleArrayOGDoubleArray implements TimesAbstract<OGDoubleArray, OGDoubleArray> {
  private static TimesOGDoubleArrayOGDoubleArray s_instance = new TimesOGDoubleArrayOGDoubleArray();

  public static TimesOGDoubleArrayOGDoubleArray getInstance() {
    return s_instance;
  }

  private TimesOGDoubleArrayOGDoubleArray() {
  }

  private BLAS _localblas = new BLAS();

  @Override
  public OGDoubleArray times(OGDoubleArray array1, OGDoubleArray array2) {
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

    if (rowsArray1 == 1 && columnsArray1 == 1) {
      n = array2.getData().length;
      tmp = new double[n];
      System.arraycopy(array2.getData(), 0, tmp, 0, n);

      final double[] singleDouble = array1.getData();
      final double deref = singleDouble[0];
      _localblas.dscal(n, deref, tmp, 1);
      retRows = rowsArray2;
      retCols = columnsArray2;
    } else if (rowsArray2 == 1 && columnsArray2 == 1) {
      n = array1.getData().length;
      tmp = new double[n];
      System.arraycopy(array1.getData(), 0, tmp, 0, n);

      final double[] singleDouble = array2.getData();
      final double deref = singleDouble[0];
      _localblas.dscal(n, deref, tmp, 1);
      retRows = rowsArray1;
      retCols = columnsArray1;

    } else { // ew mul
      Catchers.catchBadCommute(columnsArray1, "Columns in first array", columnsArray2, "Columns in second array");
      Catchers.catchBadCommute(rowsArray1, "Rows in first array", rowsArray2, "Rows in second array");      
      retRows = rowsArray1;
      retCols = columnsArray1;
      n = array1.getData().length;
      tmp = new double[n];
      final double [] dat2 = array2.getData();
      System.arraycopy(array1.getData(), 0, tmp, 0, n);
      for (int i = 0; i < n; i++) {
        tmp[i] *= dat2[i];
      }     
    }
    return new OGDoubleArray(tmp, retRows, retCols);
  }

}
