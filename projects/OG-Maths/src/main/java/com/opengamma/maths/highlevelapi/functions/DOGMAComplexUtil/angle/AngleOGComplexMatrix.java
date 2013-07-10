/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAComplexUtil.angle;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Angle;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * does angle()
 * TODO: wire in native vector variant
 */
@DOGMAMethodHook(provides = Angle.class)
public final class AngleOGComplexMatrix implements Angle<OGMatrix, OGComplexMatrix> {

  @Override
  public OGMatrix eval(OGComplexMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final double[] dataArray1 = array1.getData();
    final int machineN = dataArray1.length;
    final int mathsN = machineN / 2;

    double[] tmp = new double[mathsN];
    int ptr = 0;
    for (int i = 0; i < machineN; i += 2) {
      if (Double.isNaN(dataArray1[i]) || Double.isNaN(dataArray1[i + 1])) {
        tmp[i] = Double.NaN;
      } else {
        tmp[ptr] = Math.atan2(dataArray1[i + 1], dataArray1[i]);
      }
      ptr++;
    }
    return new OGMatrix(tmp, rowsArray1, columnsArray1);
  }
}
