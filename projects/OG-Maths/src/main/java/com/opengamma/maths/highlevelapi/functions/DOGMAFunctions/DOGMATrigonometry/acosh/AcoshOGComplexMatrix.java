/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.acosh;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Acosh;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.memory.OGTypesMalloc;

/**
 * Acosh() of an OGComplexMatrix
 */
@DOGMAMethodHook(provides = Acosh.class)
public class AcoshOGComplexMatrix implements Acosh<OGArray<? extends Number>, OGComplexMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGComplexMatrix array1) {
    double[] data = array1.getData();
    double[] tmp = new double[data.length];
    EasyIZY.vz_acosh(data, tmp);
    return OGTypesMalloc.OGComplexMatrixBasedOnStructureOf(array1, tmp);
  }
}
