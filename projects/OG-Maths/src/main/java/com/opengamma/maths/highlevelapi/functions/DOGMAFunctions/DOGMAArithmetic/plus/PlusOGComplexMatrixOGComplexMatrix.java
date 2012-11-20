/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.Plus;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;

/**
 * Adds an OGComplexMatrix to an OGComplexMatrix
 */
@DOGMAMethodHook(provides = Plus.class)
public class PlusOGComplexMatrixOGComplexMatrix implements Plus<OGComplexMatrix, OGComplexMatrix, OGComplexMatrix> {

  @Override
  public OGComplexMatrix eval(OGComplexMatrix array1, OGComplexMatrix array2) {
    int n = array1.getData().length;
    double[] data = new double[n];
    for (int i = 0; i < n; i++) {
      data[i] = array1.getData()[i] + array2.getData()[i];
    }
    return new OGComplexMatrix(data, array1.getNumberOfRows(), array1.getNumberOfColumns());
  }
}
