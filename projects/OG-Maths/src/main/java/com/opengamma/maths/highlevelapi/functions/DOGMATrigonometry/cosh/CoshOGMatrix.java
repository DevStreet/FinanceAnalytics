/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMATrigonometry.cosh;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Cosh;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.memory.OGTypesMalloc;

/**
 * Cosh() of an OGMatrix
 */
@DOGMAMethodHook(provides = Cosh.class)
public class CoshOGMatrix implements Cosh<OGMatrix, OGMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1) {
    int n = array1.getData().length;
    double[] tmp = new double[n];
    EasyIZY.vd_cosh(array1.getData(), tmp);
    return OGTypesMalloc.OGMatrixBasedOnStructureOf(array1, tmp);
  }
}
