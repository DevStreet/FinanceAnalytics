/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAInquiry.issymmetric;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.IsSymmetric;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAInquiry.issquare.IsSquareFunction;

/**
 * Tests if a matrix is symmetrical
 */
@DOGMAMethodHook(provides = IsSymmetric.class)
public class IsSymmetricOGMatrix implements IsSymmetric<OGMatrix, OGMatrix> {

  private static final IsSquareFunction s_issquare = new IsSquareFunction();

  @Override
  public OGMatrix eval(OGMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();

    if (s_issquare.issquare(array1).getData()[0] == 0) {
      return new OGMatrix(0);
    }

    int ir;
    for (int i = 0; i < cols; i++) {
      ir = i * rows;
      for (int j = i + 1; j < rows; j++) {
        if (data[ir + j] != data[j * rows + i]) {
          return new OGMatrix(0);
        }

      }
    }
    return new OGMatrix(1);
  }
}
