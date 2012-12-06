/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Plus;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Adds an OGComplexMatrix to an OGComplexMatrix
 * TODO: scalar adds
 */
@DOGMAMethodHook(provides = Plus.class)
public class PlusOGComplexMatrixOGComplexMatrix implements Plus<OGComplexMatrix, OGComplexMatrix, OGComplexMatrix> {

  private BLAS _localblas = new BLAS();

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
      System.arraycopy(array2.getData(), 0, tmp, 0, n);
      final double singleRealDouble = array1.getData()[0];
      final double singleImagDouble = array1.getData()[1];
      for (int i = 0; i < n; i += 2) {
        tmp[i] += singleRealDouble;
        tmp[i + 1] += singleImagDouble;
      }
      retRows = rowsArray2;
      retCols = columnsArray2;
    } else if (rowsArray2 == 1 && columnsArray2 == 1) {
      n = array1.getData().length;
      tmp = new double[n];
      System.arraycopy(array1.getData(), 0, tmp, 0, n);
      final double singleRealDouble = array2.getData()[0];
      final double singleImagDouble = array2.getData()[1];
      for (int i = 0; i < n; i += 2) {
        tmp[i] += singleRealDouble;
        tmp[i + 1] += singleImagDouble;
      }
      retRows = rowsArray1;
      retCols = columnsArray1;
    } else {
      Catchers.catchBadCommute(rowsArray1, "rows in first array", rowsArray2, "rows in second array");
      Catchers.catchBadCommute(columnsArray1, "columns in first array", columnsArray2, "columns in second array");
      n = array1.getData().length;
      tmp = new double[n];
      System.arraycopy(array1.getData(), 0, tmp, 0, n);
      _localblas.daxpy(n, 1, array2.getData(), 1, tmp, 1);
      retRows = rowsArray1;
      retCols = columnsArray1;
    }
    return new OGComplexMatrix(tmp, retRows, retCols);
  }
}
