/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAComplexUtil.angle;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Angle;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * does angle()
 * TODO: wire in native vector variant
 */
@DOGMAMethodHook(provides = Angle.class)
public final class AngleOGMatrix implements Angle<OGMatrix, OGMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final double[] dataArray1 = array1.getData();
    final int machineN = dataArray1.length;

    double[] tmp = new double[machineN];
    for (int i = 0; i < machineN; i++) {
      if (Double.isNaN(dataArray1[i])) {
        tmp[i] = Double.NaN;
      } else {
        tmp[i] = Math.atan2(0, dataArray1[i]);
      }
    }
    return new OGMatrix(tmp, rowsArray1, columnsArray1);
  }
}
