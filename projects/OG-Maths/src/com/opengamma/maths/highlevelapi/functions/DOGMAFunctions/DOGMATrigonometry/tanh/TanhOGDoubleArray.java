/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tanh;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Math.tanh on OGDouble
 */
public final class TanhOGDoubleArray implements TanhAbstract<OGMatrix> {
  private static TanhOGDoubleArray s_instance = new TanhOGDoubleArray();

  public static TanhOGDoubleArray getInstance() {
    return s_instance;
  }

  private TanhOGDoubleArray() {
  }

  @Override
  public OGMatrix tanh(OGMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final double[] dataArray1 = array1.getData();
    final int n = dataArray1.length;

    double[] tmp = new double[n];
    for (int i = 0; i < n; i++) {
      tmp[i] = Math.tanh(dataArray1[i]);
    }
    return new OGMatrix(tmp, rowsArray1, columnsArray1);
  }

}
