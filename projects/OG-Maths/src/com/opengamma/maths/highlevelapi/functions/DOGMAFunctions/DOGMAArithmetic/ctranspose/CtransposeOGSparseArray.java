/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGSparseArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Conjugate transposes an OGSparseArray
 */
public final class CtransposeOGSparseArray extends CtransposeAbstract<OGSparseArray> {
  private static CtransposeOGSparseArray s_instance = new CtransposeOGSparseArray();

  public static CtransposeOGSparseArray getInstance() {
    return s_instance;
  }

  private CtransposeOGSparseArray() {
  }

  private TransposeOGSparseArray _transpose = TransposeOGSparseArray.getInstance();

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> ctranspose(OGSparseArray array1) {
    Catchers.catchNullFromArgList(array1, 1);
    return _transpose.transpose(array1);
  }
}
