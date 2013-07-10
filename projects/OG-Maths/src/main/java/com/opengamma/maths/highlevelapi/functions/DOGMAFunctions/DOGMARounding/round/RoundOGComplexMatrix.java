/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARounding.round;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Round;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;

/**
 * Round's OGComplexMatrix's 
 */
@DOGMAMethodHook(provides = Round.class)
public final class RoundOGComplexMatrix implements Round<OGComplexMatrix, OGComplexMatrix> {
  @Override
  public OGComplexMatrix eval(OGComplexMatrix array1) {
    double[] tmp = com.opengamma.maths.lowlevelapi.functions.utilities.Round.stateless(array1.getData());
    return new OGComplexMatrix(tmp, array1.getNumberOfRows(), array1.getNumberOfColumns());
  }

}
