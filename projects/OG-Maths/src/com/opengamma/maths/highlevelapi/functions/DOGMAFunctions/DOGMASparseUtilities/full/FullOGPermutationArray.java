/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationMatrix;

/**
 * Full's OGPermutationArrays
 */
public final class FullOGPermutationArray implements FullAbstract<OGPermutationMatrix> {

  private static FullOGPermutationArray s_instance = new FullOGPermutationArray();

  public static FullOGPermutationArray getInstance() {
    return s_instance;
  }

  private FullOGPermutationArray() {
  }

  @Override
  public OGMatrix full(OGPermutationMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] data = array1.getData();
    double[] tmp = new double[rows * cols];
    for (int i = 0; i < cols; i++) {
      tmp[i + data[i] * rows] = 1;
    }
    return new OGMatrix(tmp, rows, cols);
  }

}
