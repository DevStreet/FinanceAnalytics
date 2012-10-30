/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Conjugate Transposes an OGDiagonalArray
 * TODO: At some point consider COW or at least state propagated permutations for things like this?!
 */
public final class CtransposeOGDiagonalArray implements CtransposeAbstract<OGDiagonalArray> {
  private static CtransposeOGDiagonalArray s_instance = new CtransposeOGDiagonalArray();

  public static CtransposeOGDiagonalArray getInstance() {
    return s_instance;
  }

  private CtransposeOGDiagonalArray() {
  }

  @Override
  public OGDiagonalArray ctranspose(OGDiagonalArray array1) {
    Catchers.catchNullFromArgList(array1, 1);
    return new OGDiagonalArray(array1.getData(), array1.getNumberOfColumns(), array1.getNumberOfRows());
  }

}
