/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAExponentsAndLogarithms.sqrt;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * does sqrt
 */
public final class SqrtOGSparseArray implements SqrtAbstract<OGSparseArray> {
  private static SqrtOGSparseArray s_instance = new SqrtOGSparseArray();

  public static SqrtOGSparseArray getInstance() {
    return s_instance;
  }

  private SqrtOGSparseArray() {
  }

  @Override
  public OGSparseArray sqrt(OGSparseArray array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final int [] rowIdx = array1.getRowIndex();
    final int [] colPtr = array1.getColumnPtr();    
    final double[] dataArray1 = array1.getData();
    final int n = dataArray1.length;

    double[] tmp = new double[n];
    for (int i = 0; i < n; i++) {
      if (dataArray1[i] > 0) {
        tmp[i] = Math.sqrt(dataArray1[i]);
      } else {
        throw new MathsExceptionNotImplemented("Sqrt of negative numbers not implemented, awaiting complex support");
      }
    }
    
    return new OGSparseArray(colPtr, rowIdx, tmp, rowsArray1, columnsArray1);
  }
}
