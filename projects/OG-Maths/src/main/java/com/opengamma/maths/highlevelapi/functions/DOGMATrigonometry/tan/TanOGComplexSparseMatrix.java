/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMATrigonometry.tan;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Tan;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexSparseMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.memory.OGTypesMalloc;

/**
 * Tan() on OGComplexSparse
 */
@DOGMAMethodHook(provides = Tan.class)
public class TanOGComplexSparseMatrix implements Tan<OGComplexSparseMatrix, OGComplexSparseMatrix> {

  @Override
  public OGComplexSparseMatrix eval(OGComplexSparseMatrix array1) {
    int n = array1.getData().length;
    double[] tmp = new double[n];
    EasyIZY.vz_tan(array1.getData(), tmp);
    return OGTypesMalloc.OGComplexSparseMatrixBasedOnStructureOf(array1, tmp);
  }
}
