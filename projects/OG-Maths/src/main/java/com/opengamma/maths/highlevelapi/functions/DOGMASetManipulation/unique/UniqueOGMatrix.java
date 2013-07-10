/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMASetManipulation.unique;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Unique;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;

/**
 * Uniques OGMatrix's 
 */
@DOGMAMethodHook(provides = Unique.class)
public final class UniqueOGMatrix implements Unique<OGMatrix, OGMatrix> {
  @Override
  public OGMatrix eval(OGMatrix array1) {
    final double[] tmp = com.opengamma.maths.lowlevelapi.functions.utilities.Unique.bitwise(array1.getData());
    return new OGMatrix(tmp, tmp.length, 1);
  }
}
