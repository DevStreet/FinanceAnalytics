/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.sin;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;

/**
 * 
 */
@DOGMAMethodHook(provides = Sin.class)
public class SinOGComplexMatrix implements Sin<OGComplexMatrix, OGComplexMatrix> {

  @Override
  public OGComplexMatrix eval(OGComplexMatrix array1) {
    int n = array1.getData().length;
    double[] tmp = new double[n];
    double[] data = array1.getData();
    for (int i = 0; i < n; i += 2) {
      tmp[i] = Math.sin(data[i]) * Math.cosh(data[i + 1]);
      tmp[i + 1] = Math.cos(data[i]) * Math.sinh(data[i + 1]);
    }
    return new OGComplexMatrix(tmp, array1.getNumberOfRows(), array1.getNumberOfColumns());
  }
}
