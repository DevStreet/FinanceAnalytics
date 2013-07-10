/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.cos;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Cos;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.memory.OGTypesMalloc;

/**
 * Cos OGComplexMatrix
 */
@DOGMAMethodHook(provides = Cos.class)
public class CosOGComplexMatrix implements Cos<OGArray<? extends Number>, OGComplexMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGComplexMatrix array1) {
    int n = array1.getData().length;
    double[] tmp = new double[n];
    EasyIZY.vz_cos(array1.getData(), tmp);
    return OGTypesMalloc.OGComplexMatrixBasedOnStructureOf(array1, tmp);
  }

}
