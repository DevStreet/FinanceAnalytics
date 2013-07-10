/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.copy;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Copy;
import com.opengamma.maths.highlevelapi.datatypes.OGRealScalar;

/**
 * Copies OGRealScalar 
 */
@DOGMAMethodHook(provides = Copy.class)
public final class CopyOGRealScalar implements Copy<OGRealScalar, OGRealScalar> {
  @Override
  public OGRealScalar eval(OGRealScalar array1) {
    return new OGRealScalar(array1.getData()[0]);
  }

}
