/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAExponentsAndLogarithms.sqrt;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Sqrt;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * does sqrt on sparse matrices
 */
@DOGMAMethodHook(provides = Sqrt.class)
public final class SqrtOGSparseMatrix implements Sqrt<OGArray<? extends Number>, OGSparseMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGSparseMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final int[] rowIdx = array1.getRowIndex();
    final int[] colPtr = array1.getColumnPtr();
    final double[] dataArray1 = array1.getData();
    final int n = dataArray1.length;

    double[] tmp = new double[n];
    double[] cmplxTmp;
    boolean isCmplx = false;
    int i;
    for (i = 0; i < n; i++) {
      if (dataArray1[i] >= 0) {
        tmp[i] = Math.sqrt(dataArray1[i]);
      } else {
        isCmplx = true;
        break;
      }
    }
    if (isCmplx) {
      cmplxTmp = DenseMemoryManipulation.convertSinglePointerToZeroInterleavedSinglePointer(tmp);
      for (int j = i; j < n; j++) {
        if (dataArray1[j] >= 0) {
          cmplxTmp[2 * j] = Math.sqrt(dataArray1[j]);
        } else {
          cmplxTmp[2 * j + 1] = Math.sqrt(-dataArray1[j]);
        }
      }
      return new OGComplexSparseMatrix(colPtr, rowIdx, cmplxTmp, rowsArray1, columnsArray1);
    } else {
      return new OGSparseMatrix(colPtr, rowIdx, tmp, rowsArray1, columnsArray1);
    }
  }
}
