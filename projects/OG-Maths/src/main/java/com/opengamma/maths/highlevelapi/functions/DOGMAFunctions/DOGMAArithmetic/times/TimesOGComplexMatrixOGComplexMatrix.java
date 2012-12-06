/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Times;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Element wise multiply complex complex
 */
@DOGMAMethodHook(provides = Times.class)
public final class TimesOGComplexMatrixOGComplexMatrix implements Times<OGComplexMatrix, OGComplexMatrix, OGComplexMatrix> {

  // this is rather ugly thanks to not having complex arithmetic in java, some of this could be mitigated by BLAS calls at some point
  // we need (a+bi)*(c+di) = (ac-bd) + (bc+ad)i
  @Override
  public OGComplexMatrix eval(OGComplexMatrix array1, OGComplexMatrix array2) {
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

    if (rowsArray1 == 1 && columnsArray1 == 1) { // scalar times matrix, TODO: get this from zscal
      double[] data = array2.getData();
      int dataN = data.length;
      int mathematicalN = dataN / 2;
      tmp = new double[dataN];
      System.arraycopy(data, 0, tmp, 0, dataN);

      final double singleReal = array1.getData()[0];
      final double singleImag = array1.getData()[1];

      int ptr = 0;
      for (int i = 0; i < mathematicalN; i++) {
        // real bit
        tmp[ptr] = singleReal * data[ptr] - singleImag * data[ptr + 1];
        // imag bit
        tmp[ptr + 1] = singleImag * data[ptr] + singleReal * data[ptr + 1];
        ptr += 2;
      }
      retRows = rowsArray2;
      retCols = columnsArray2;
    } else if (rowsArray2 == 1 && columnsArray2 == 1) {
      double[] data = array1.getData();
      int dataN = data.length;
      int mathematicalN = dataN / 2;
      tmp = new double[dataN];
      System.arraycopy(data, 0, tmp, 0, dataN);
      final double singleReal = array2.getData()[0];
      final double singleImag = array2.getData()[1];
      int ptr = 0;
      for (int i = 0; i < mathematicalN; i++) {
        // real bit
        tmp[ptr] = singleReal * data[ptr] - singleImag * data[ptr + 1];
        // imag bit
        tmp[ptr + 1] = singleImag * data[ptr] + singleReal * data[ptr + 1];
        ptr += 2;
      }
      retRows = rowsArray1;
      retCols = columnsArray1;
    } else { // ew mul
      Catchers.catchBadCommute(columnsArray1, "Columns in first array", columnsArray2, "Columns in second array");
      Catchers.catchBadCommute(rowsArray1, "Rows in first array", rowsArray2, "Rows in second array");
      retRows = rowsArray1;
      retCols = columnsArray1;
      double[] data1 = array1.getData();
      double[] data2 = array2.getData();
      int dataN = data1.length;
      int mathematicalN = dataN / 2;
      tmp = new double[dataN];
      int ptr = 0;
      int ptrp1 = 0;
      for (int i = 0; i < mathematicalN; i++) {
        ptrp1 = ptr + 1;
        tmp[ptr] = data1[ptr] * data2[ptr] - data1[ptrp1] * data2[ptrp1];
        tmp[ptrp1] = data1[ptrp1] * data2[ptr] + data1[ptr] * data2[ptrp1];
        ptr += 2;
      }
    }
    return new OGComplexMatrix(tmp, retRows, retCols);
  }
}
