/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIO.disp;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unaryvoid.Disp;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGRealScalar;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Displays OGRealScalar matrices
 */
@DOGMAMethodHook(provides = Disp.class)
public class DispOGRealScalar implements Disp<OGRealScalar> {

  @Override
  public void eval(OGRealScalar array1) {
    Catchers.catchNull(array1);
    double[] data = array1.getData();
    String str = "\nOGRealScalar:\n" + String.format("%24.18f", data[0]);
    System.out.println(str);
  }

}
