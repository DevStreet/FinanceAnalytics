/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Transpose;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Transposes an OGDiagonalArray
 * TODO: At some point consider COW or at least state propagated permutations for things like this?!
 */
@DOGMAMethodHook(provides = Transpose.class)
public final class TransposeOGDiagonalMatrix implements Transpose<OGDiagonalMatrix, OGDiagonalMatrix> {

  @Override
  public OGDiagonalMatrix eval(OGDiagonalMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    return new OGDiagonalMatrix(array1.getData(), array1.getNumberOfColumns(), array1.getNumberOfRows());
  }

}
