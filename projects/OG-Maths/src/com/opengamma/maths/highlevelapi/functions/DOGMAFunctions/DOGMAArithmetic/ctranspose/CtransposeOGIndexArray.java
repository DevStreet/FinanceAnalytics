/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGIndexMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Conjugate Transposes an OGIndexArray
 * TODO: At some point consider COW or at least state propagated permutations for things like this?!
 */
public final class CtransposeOGIndexArray implements CtransposeAbstract<OGIndexMatrix> {
  private static CtransposeOGIndexArray s_instance = new CtransposeOGIndexArray();

  public static CtransposeOGIndexArray getInstance() {
    return s_instance;
  }

  private CtransposeOGIndexArray() {
  }
  
  private TransposeOGIndexMatrix _transpose = TransposeOGIndexMatrix.getInstance();


  @Override
  public OGIndexMatrix ctranspose(OGIndexMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    return _transpose.transpose(array1);
  }

}
