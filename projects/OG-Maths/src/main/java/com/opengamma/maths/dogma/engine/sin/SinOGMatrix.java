/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.sin;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * 
 */
@DOGMAMethodHook(provides = Sin.class)
public class SinOGMatrix implements Sin<OGMatrix, OGMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1) {
    int n = array1.getData().length;
    double[] tmp = new double[n];
    double[] data = array1.getData();
    for (int i = 0; i < n; i++) {
      tmp[i] = Math.sin(data[i]);
    }
    return new OGMatrix(tmp, array1.getNumberOfRows(), array1.getNumberOfColumns());
  }
}
