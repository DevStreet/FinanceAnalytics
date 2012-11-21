/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.Ctranspose;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGIndexMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Conjugate Transposes an OGIndexArray
 * TODO: At some point consider COW or at least state propagated permutations for things like this?!
 */
@DOGMAMethodHook(provides = Ctranspose.class)
public final class CtransposeOGIndexMatrix implements Ctranspose<OGIndexMatrix, OGIndexMatrix> {
  @Override
  public OGIndexMatrix eval(OGIndexMatrix array1) {
    TransposeOGIndexMatrix transpose = new TransposeOGIndexMatrix();
    Catchers.catchNullFromArgList(array1, 1);
    return transpose.eval(array1);
  }

}
