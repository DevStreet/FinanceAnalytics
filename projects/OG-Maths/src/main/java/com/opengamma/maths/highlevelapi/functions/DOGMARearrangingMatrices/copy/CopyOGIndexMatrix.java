/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.copy;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Copy;
import com.opengamma.maths.highlevelapi.datatypes.OGIndexMatrix;

/**
 * Copies OGIndexMatrix 
 */
@DOGMAMethodHook(provides = Copy.class)
public final class CopyOGIndexMatrix implements Copy<OGIndexMatrix, OGIndexMatrix> {
  @Override
  public OGIndexMatrix eval(OGIndexMatrix array1) {
    final int n = array1.getData().length;
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] tmp = new int[n];
    double[] data = array1.getData();
    for (int i = 0; i < data.length; i++) {
      tmp[i] = (int) data[i];
    }
    return new OGIndexMatrix(tmp, rows, cols);
  }

}
