/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.copy;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Copy;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexDiagonalMatrix;

/**
 * Copies OGComplexDiagonalMatrix 
 */
@DOGMAMethodHook(provides = Copy.class)
public final class CopyOGComplexDiagonalMatrix implements Copy<OGComplexDiagonalMatrix, OGComplexDiagonalMatrix> {
  @Override
  public OGComplexDiagonalMatrix eval(OGComplexDiagonalMatrix array1) {
    final double[] data = array1.getData();
    final int n = data.length;
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    int len = n / 2;
    double[] real = new double[len];
    double[] imag = new double[len];
    int jmp = 0;
    for (int i = 0; i < len; i++) {
      real[i] = data[jmp];
      imag[i] = data[jmp + 1];
      jmp += 2;
    }
    return new OGComplexDiagonalMatrix(real, imag, rows, cols);
  }
}
