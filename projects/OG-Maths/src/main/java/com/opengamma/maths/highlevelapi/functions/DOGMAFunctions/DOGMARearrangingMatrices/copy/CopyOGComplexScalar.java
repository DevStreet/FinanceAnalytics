/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Copy;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexScalar;

/**
 * Copies OGComplexScalar 
 */
@DOGMAMethodHook(provides = Copy.class)
public final class CopyOGComplexScalar implements Copy<OGComplexScalar, OGComplexScalar> {
  @Override
  public OGComplexScalar eval(OGComplexScalar array1) {
    return new OGComplexScalar(array1.getData()[0], array1.getData()[1]);
  }

}
