/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Conjugate transposes an OGSparseArray
 */
public final class CtransposeOGSparseMatrix implements CtransposeAbstract<OGSparseMatrix> {
  private static CtransposeOGSparseMatrix s_instance = new CtransposeOGSparseMatrix();

  public static CtransposeOGSparseMatrix getInstance() {
    return s_instance;
  }

  private CtransposeOGSparseMatrix() {
  }

  private TransposeOGSparseMatrix _transpose = new TransposeOGSparseMatrix();

  @Override
  public OGSparseMatrix ctranspose(OGSparseMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    return _transpose.eval(array1);
  }
}
