/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.plus;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Plus;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Adds an OGComplexMatrix to an OGComplexMatrix
 * TODO: scalar adds
 */
@DOGMAMethodHook(provides = Plus.class)
public class PlusOGComplexMatrixOGComplexMatrix implements Plus<OGComplexMatrix, OGComplexMatrix, OGComplexMatrix> {

  @Override
  public OGComplexMatrix eval(OGComplexMatrix array1, OGComplexMatrix array2) {
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;

    int n;
    double[] tmp;
    // Actually adding arrays
    if (rowsArray1 == 1 && columnsArray1 == 1) { // array 1 is scalar, i.e. X+iY
      n = array2.getData().length;
      tmp = new double[n];
      EasyIZY.vz_addx(array2.getData(), array1.getData(), tmp);
      retRows = rowsArray2;
      retCols = columnsArray2;
    } else if (rowsArray2 == 1 && columnsArray2 == 1) {
      n = array1.getData().length;
      tmp = new double[n];
      EasyIZY.vz_addx(array1.getData(), array2.getData(), tmp);
      retRows = rowsArray1;
      retCols = columnsArray1;
    } else {
      Catchers.catchBadCommute(rowsArray1, "rows in first array", rowsArray2, "rows in second array");
      Catchers.catchBadCommute(columnsArray1, "columns in first array", columnsArray2, "columns in second array");
      n = array1.getData().length;
      tmp = new double[n];
      EasyIZY.vz_add(array1.getData(), array2.getData(), tmp);
      retRows = rowsArray1;
      retCols = columnsArray1;
    }
    return new OGComplexMatrix(tmp, retRows, retCols);
  }
}
