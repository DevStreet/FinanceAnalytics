/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAComplexUtil.imag;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Imag;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * does imag()
 * TODO: wire in native vector variant
 */
@DOGMAMethodHook(provides = Imag.class)
public final class ImagOGMatrix implements Imag<OGMatrix, OGMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    return new OGMatrix(array1.getData(), array1.getNumberOfRows(), array1.getNumberOfColumns());
  }
}
