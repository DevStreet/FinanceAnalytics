/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Transpose for {@link OGPermutationArray}
 */
public final class TransposeOGPermutationArray extends TransposeAbstract<OGPermutationArray> {
  private static TransposeOGPermutationArray s_instance = new TransposeOGPermutationArray();

  public static TransposeOGPermutationArray getInstance() {
    return s_instance;
  }

  private TransposeOGPermutationArray() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> transpose(OGPermutationArray array1) {
    Catchers.catchNullFromArgList(array1, 1);
    // transpose of a permutation matrix is just looking up its own indices in itself a(i,j)=a(j,i) but we walk just once with compressed canonical vectors
    // so if you have permutation vector P, range=1:length(P), the range(P) gives the transpose permutation
    int[] data = array1.getData();
    int dim = array1.getRows();

    int[] tmp = new int[dim];
    for (int i = 0; i < dim; i++) {
      tmp[data[i]] = i;
    }
    return new OGPermutationArray(tmp);
  }
}
