/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialFunctions.erfc;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.DERFC;

/**
 * Does erfc on OGDoubleArray
 */
public final class ErfcOGDoubleArray extends ErfcAbstract<OGDoubleArray> {
  private static ErfcOGDoubleArray s_instance = new ErfcOGDoubleArray();

  public static ErfcOGDoubleArray getInstance() {
    return s_instance;
  }

  private ErfcOGDoubleArray() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> erfc(OGDoubleArray array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final double[] dataArray1 = array1.getData();
    final int n = dataArray1.length;
    //TODO: check what SLATEC impl does with NaN/Inf & deal with underflow issues, exception currently thrown
    double[] tmp = new double[n];
    for (int i = 0; i < n; i++) {
      tmp[i] = DERFC.getErfc(dataArray1[i]);
    }
    return new OGDoubleArray(tmp, rowsArray1, columnsArray1);
  }
}
