/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAInquiry.issymmetric;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.IsSymmetric;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAInquiry.issquare.IsSquareFunction;

/**
 * Tests if a matrix is symmetrical
 */
@DOGMAMethodHook(provides = IsSymmetric.class)
public class IsSymmetricOGComplexSparseMatrix implements IsSymmetric<OGMatrix, OGComplexSparseMatrix> {

  private static final IsSquareFunction s_issquare = new IsSquareFunction();

  @Override
  public OGMatrix eval(OGComplexSparseMatrix array1) {
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();

    if (s_issquare.issquare(array1).getData()[0] == 0) {
      return new OGMatrix(0);
    }

    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();

    int twoi, twok;
    for (int ir = 0; ir < cols; ir++) {
      for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
        for (int k = colPtr[rowIdx[i]]; k < colPtr[rowIdx[i] + 1]; k++) {
          if (rowIdx[k] == ir) {
            twoi = 2 * i;
            twok = 2 * k;
            if (data[twoi] != data[twok] || data[twoi + 1] != data[twok + 1]) {
              return new OGMatrix(0);
            }
          }
        }
      }
    }

    return new OGMatrix(1);
  }
}
