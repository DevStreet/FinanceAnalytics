/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.minus;

import java.util.Arrays;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Minus;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Subtracts an {@link OGMatrix} from an {@link OGDiagonalMatrix} 
 */
@DOGMAMethodHook(provides = Minus.class)
public final class MinusOGMatrixOGDiagonalMatrix implements Minus<OGMatrix, OGMatrix, OGDiagonalMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1, OGDiagonalMatrix array2) {

    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;

    double[] tmp = null;

    OGMatrix retArray = null;

    // Actually adding arrays
    if (rowsArray1 == 1 && columnsArray1 == 1) { // Dense array is actually a single number, so we make the diag array a OGDoubleArray and ADD 
      final int n = columnsArray2 * rowsArray2;
      tmp = new double[n];
      final double singleDouble = array1.getData()[0];
      Arrays.fill(tmp, singleDouble);
      final double[] data = array2.getData();
      retRows = rowsArray2;
      retCols = columnsArray2;
      for (int i = 0; i < data.length; i++) {
        tmp[i * rowsArray2 + i] -= data[i];
      }
      retArray = new OGMatrix(tmp, retRows, retCols);
    } else if (rowsArray2 == 1 && columnsArray2 == 1) { // diagonal array is actually a single number, so we can just deref and add
      final int n = array1.getData().length;
      tmp = new double[n];
      System.arraycopy(array1.getData(), 0, tmp, 0, n);
      final double singleDouble = array2.getData()[0];
      for (int i = 0; i < n; i++) {
        tmp[i] -= singleDouble;
      }
      retRows = rowsArray1;
      retCols = columnsArray1;
      retArray = new OGMatrix(tmp, retRows, retCols);
    } else { // Both arrays are full dimension, do diagonal add 
      Catchers.catchBadCommute(rowsArray1, "rows in first array", rowsArray2, "rows in second array");
      Catchers.catchBadCommute(columnsArray1, "columns in first array", columnsArray2, "columns in second array");
      retRows = rowsArray1;
      retCols = columnsArray1;
      final int n = retRows * retCols;
      tmp = new double[n];
      System.arraycopy(array1.getData(), 0, tmp, 0, n);
      final double[] data = array2.getData();
      for (int i = 0; i < data.length; i++) {
        tmp[i * rowsArray1 + i] -= data[i];
      }
      retArray = new OGMatrix(tmp, retRows, retCols);
    }
    return retArray;
  }

}
