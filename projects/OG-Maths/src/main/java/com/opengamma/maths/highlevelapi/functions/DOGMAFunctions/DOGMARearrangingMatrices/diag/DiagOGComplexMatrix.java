/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.diag;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Diag;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * does diag()
 */
@DOGMAMethodHook(provides = Diag.class)
public class DiagOGComplexMatrix implements Diag<OGArray<? extends Number>, OGComplexMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGComplexMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    double[] tmpr, tmpi, data;

    if (rows == 1 || cols == 1) { // we are creating a diagonal matrix
      final int len = Math.max(rows, cols);
      tmpr = new double[len];
      tmpi = new double[len];
      data = array1.getData();
      int ptr = 0;
      for (int i = 0; i < len; i++) {
        tmpr[i] = data[ptr];
        tmpi[i] = data[ptr + 1];
        ptr += 2;
      }
      return new OGComplexDiagonalMatrix(tmpr, tmpi);
    } else { // we are extracting the diagonal from a matrix
      final int dim = rows < cols ? rows : cols;
      tmpr = new double[2 * dim];
      data = array1.getData();
      int ptr = 0;
      int jmp;
      for (int i = 0; i < dim; i++) {
        jmp = 2 * (i * rows + i);
        tmpr[ptr] = data[jmp];
        tmpr[ptr + 1] = data[jmp + 1];
        ptr += 2;
      }
      return new OGComplexMatrix(tmpr, dim, 1);
    }

  }
}
