/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Times;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Element wise multiply 
 */
@DOGMAMethodHook(provides = Times.class)
public final class TimesOGMatrixOGMatrix implements Times<OGMatrix, OGMatrix, OGMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1, OGMatrix array2) {
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
      final double deref = array1.getData()[0];
      EasyIZY.vd_mulx(array2.getData(), deref, tmp);
      retRows = rowsArray2;
      retCols = columnsArray2;
    } else if (rowsArray2 == 1 && columnsArray2 == 1) {
      n = array1.getData().length;
      tmp = new double[n];
      final double deref = array2.getData()[0];
      EasyIZY.vd_mulx(array1.getData(), deref, tmp);
      retRows = rowsArray1;
      retCols = columnsArray1;

    } else { // ew mul
      Catchers.catchBadCommute(columnsArray1, "Columns in first array", columnsArray2, "Columns in second array");
      Catchers.catchBadCommute(rowsArray1, "Rows in first array", rowsArray2, "Rows in second array");
      retRows = rowsArray1;
      retCols = columnsArray1;
      n = array1.getData().length;
      tmp = new double[n];
      EasyIZY.vd_mul(array1.getData(), array2.getData(), tmp);
    }
    return new OGMatrix(tmp, retRows, retCols);
  }

}
