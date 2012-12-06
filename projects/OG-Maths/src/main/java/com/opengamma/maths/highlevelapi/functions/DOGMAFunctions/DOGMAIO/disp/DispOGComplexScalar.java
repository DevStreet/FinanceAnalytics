/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIO.disp;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unaryvoid.Disp;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexScalar;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Displays OGComplexScalar matrices
 */
@DOGMAMethodHook(provides = Disp.class)
public class DispOGComplexScalar implements Disp<OGComplexScalar> {

  @Override
  public void eval(OGComplexScalar array1) {
    Catchers.catchNull(array1);
    double[] data = array1.getData();
    String str = "\nOGComplexScalar:\n" + String.format("%24.18f " + (data[1] >= 0 ? "   +" : "   -") + "%24.18fi, ", data[0], Math.abs(data[1]));
    System.out.println(str);
  }

}
