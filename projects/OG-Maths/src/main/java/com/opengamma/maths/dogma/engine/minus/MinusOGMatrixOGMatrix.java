/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.minus;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * 
 */
@DOGMAMethodHook(provides = Minus.class)
public class MinusOGMatrixOGMatrix implements Minus<OGMatrix, OGMatrix, OGMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1, OGMatrix array2) {
    int n = array1.getData().length;
    double[] data = new double[n];
    for (int i = 0; i < n; i++) {
      data[i] = array1.getData()[i] + array2.getData()[i];
    }
    return new OGMatrix(data, array1.getNumberOfRows(), array1.getNumberOfColumns());
  }
}
