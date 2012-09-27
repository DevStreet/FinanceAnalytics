/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialFunctions.erf;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.DERF;

/**
 * Does erf on OGDoubleArray
 */
public final class ErfOGDoubleArray extends ErfAbstract<OGDoubleArray> {
  private static ErfOGDoubleArray s_instance = new ErfOGDoubleArray();

  public static ErfOGDoubleArray getInstance() {
    return s_instance;
  }

  private ErfOGDoubleArray() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> erf(OGDoubleArray array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final double[] dataArray1 = array1.getData();
    final int n = dataArray1.length;

    double[] tmp = new double[n];
    for (int i = 0; i < n; i++) {
      tmp[i] = DERF.derf(dataArray1[i]);
    }
    return new OGDoubleArray(tmp, rowsArray1, columnsArray1);
  }
}
