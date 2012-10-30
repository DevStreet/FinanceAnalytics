/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tan;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Math.tan on OGDouble
 */
public final class TanOGDoubleArray implements TanAbstract<OGMatrix> {
  private static TanOGDoubleArray s_instance = new TanOGDoubleArray();

  public static TanOGDoubleArray getInstance() {
    return s_instance;
  }

  private TanOGDoubleArray() {
  }

  @Override
  public OGMatrix tan(OGMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final double[] dataArray1 = array1.getData();
    final int n = dataArray1.length;

    double[] tmp = new double[n];
    for (int i = 0; i < n; i++) {
      tmp[i] = Math.tan(dataArray1[i]);
    }
    return new OGMatrix(tmp, rowsArray1, columnsArray1);
  }

}
