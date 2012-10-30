/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGDoubleArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Conjugate Transposes an OGDoubleArray
 * TODO: At some point consider COW or at least state propagated permutations for things like this?!
 */
public final class CtransposeOGDoubleArray implements CtransposeAbstract<OGMatrix> {
  private static CtransposeOGDoubleArray s_instance = new CtransposeOGDoubleArray();

  public static CtransposeOGDoubleArray getInstance() {
    return s_instance;
  }

  private CtransposeOGDoubleArray() {
  }
  
  private TransposeOGDoubleArray _transpose = TransposeOGDoubleArray.getInstance();


  @Override
  public OGMatrix ctranspose(OGMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    return _transpose.transpose(array1);
  }

}
