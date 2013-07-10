/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMATrigonometry.cos;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Cos;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.memory.OGTypesMalloc;

/**
 * Cos OGMatrix
 */
@DOGMAMethodHook(provides = Cos.class)
public class CosOGMatrix implements Cos<OGArray<? extends Number>, OGMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGMatrix array1) {
    int n = array1.getData().length;
    double[] tmp = new double[n];
    EasyIZY.vd_cos(array1.getData(), tmp);
    return OGTypesMalloc.OGMatrixBasedOnStructureOf(array1, tmp);
  }

}
