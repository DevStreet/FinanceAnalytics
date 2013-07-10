/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMARounding.round;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Round;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;

/**
 * Round's OGMatrix's 
 */
@DOGMAMethodHook(provides = Round.class)
public final class RoundOGMatrix implements Round<OGMatrix, OGMatrix> {
  @Override
  public OGMatrix eval(OGMatrix array1) {
    double[] tmp = com.opengamma.maths.lowlevelapi.functions.utilities.Round.stateless(array1.getData());
    return new OGMatrix(tmp, array1.getNumberOfRows(), array1.getNumberOfColumns());
  }

}
