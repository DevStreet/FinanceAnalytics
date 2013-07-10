/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMASparseUtilities.full;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Full;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGPermutationMatrix;

/**
 * Full's OGPermutationArrays
 */
@DOGMAMethodHook(provides = Full.class)
public final class FullOGPermutationMatrix implements Full<OGMatrix, OGPermutationMatrix> {

  @Override
  public OGMatrix eval(OGPermutationMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] data = array1.getIntData();
    double[] tmp = new double[rows * cols];
    for (int i = 0; i < cols; i++) {
      tmp[i + data[i] * rows] = 1;
    }
    return new OGMatrix(tmp, rows, cols);
  }

}
