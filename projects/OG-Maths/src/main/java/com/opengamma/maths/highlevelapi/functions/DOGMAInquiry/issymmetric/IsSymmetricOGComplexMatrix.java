/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAInquiry.issymmetric;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.IsSymmetric;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAInquiry.issquare.IsSquareFunction;

/**
 * Tests if a matrix is symmetrical
 */
@DOGMAMethodHook(provides = IsSymmetric.class)
public class IsSymmetricOGComplexMatrix implements IsSymmetric<OGMatrix, OGComplexMatrix> {

  private static final IsSquareFunction s_issquare = new IsSquareFunction();

  @Override
  public OGMatrix eval(OGComplexMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();

    if (s_issquare.issquare(array1).getData()[0] == 0) {
      return new OGMatrix(0);
    }

    int ir, irpj, jrpi;
    for (int i = 0; i < cols; i++) {
      ir = i * rows;
      for (int j = i + 1; j < rows; j++) {
        jrpi = 2 * (j * rows + i);
        irpj = 2 * (ir + j);
        if (data[irpj] != data[jrpi]) {
          return new OGMatrix(0);
        }
        if (data[irpj + 1] != data[jrpi + 1]) {
          return new OGMatrix(0);
        }
      }
    }
    return new OGMatrix(1);
  }
}
