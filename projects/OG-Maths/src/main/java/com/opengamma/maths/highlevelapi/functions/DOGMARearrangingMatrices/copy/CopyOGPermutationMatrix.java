/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.copy;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Copy;
import com.opengamma.maths.highlevelapi.datatypes.OGPermutationMatrix;

/**
 * Copies OGPermutationMatrix 
 */
@DOGMAMethodHook(provides = Copy.class)
public final class CopyOGPermutationMatrix implements Copy<OGPermutationMatrix, OGPermutationMatrix> {
  @Override
  public OGPermutationMatrix eval(OGPermutationMatrix array1) {
    final int n = array1.getData().length;
    final int[] tmp = new int[n];
    double[] data = array1.getData();
    for (int i = 0; i < data.length; i++) {
      tmp[i] = (int) data[i];
    }
    return new OGPermutationMatrix(tmp);
  }

}
