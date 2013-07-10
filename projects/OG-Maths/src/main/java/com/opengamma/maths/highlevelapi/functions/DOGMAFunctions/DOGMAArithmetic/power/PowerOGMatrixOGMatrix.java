/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.power;

import java.util.Arrays;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.binary.Power;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Power {@link OGMatrix} to {@link OGMatrix}
 */
@DOGMAMethodHook(provides = Power.class)
public final class PowerOGMatrixOGMatrix implements Power<OGMatrix, OGMatrix, OGMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1, OGMatrix array2) {
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;

    int n = array1.getData().length;
    double[] tmp = new double[n];
    System.arraycopy(array1.getData(), 0, tmp, 0, n);
    if (rowsArray1 == 1 && columnsArray1 == 1) { // array 1 is scalar, i.e. X.^Y
      n = array2.getData().length;
      tmp = new double[n];
      Arrays.fill(tmp, array1.getData()[0]);
      EasyIZY.vd_pow(tmp, array2.getData(), tmp);
      retRows = rowsArray2;
      retCols = columnsArray2;
    } else if (rowsArray2 == 1 && columnsArray2 == 1) {
      n = array1.getData().length;
      tmp = new double[n];
      EasyIZY.vd_powx(array1.getData(), array2.getData()[0], tmp);
      retRows = rowsArray1;
      retCols = columnsArray1;
    } else {
      Catchers.catchBadCommute(rowsArray1, "rows in first array", rowsArray2, "rows in second array");
      Catchers.catchBadCommute(columnsArray1, "columns in first array", columnsArray2, "columns in second array");
      n = array1.getData().length;
      tmp = new double[n];
      EasyIZY.vd_pow(array1.getData(), array2.getData(), tmp);
      retRows = rowsArray1;
      retCols = columnsArray1;
    }
    return new OGMatrix(tmp, retRows, retCols);
  }
}
