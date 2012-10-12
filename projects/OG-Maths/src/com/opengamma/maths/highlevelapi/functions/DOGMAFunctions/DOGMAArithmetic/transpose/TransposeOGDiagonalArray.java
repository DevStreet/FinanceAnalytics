/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Transposes an OGDiagonalArray
 * TODO: At some point consider COW or at least state propagated permutations for things like this?!
 */
public final class TransposeOGDiagonalArray extends TransposeAbstract<OGDiagonalArray> {
  private static TransposeOGDiagonalArray s_instance = new TransposeOGDiagonalArray();

  public static TransposeOGDiagonalArray getInstance() {
    return s_instance;
  }

  private TransposeOGDiagonalArray() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> transpose(OGDiagonalArray array1) {
    Catchers.catchNullFromArgList(array1, 1);
    return new OGDiagonalArray(array1.getData(), array1.getNumberOfColumns(), array1.getNumberOfRows());
  }

}
