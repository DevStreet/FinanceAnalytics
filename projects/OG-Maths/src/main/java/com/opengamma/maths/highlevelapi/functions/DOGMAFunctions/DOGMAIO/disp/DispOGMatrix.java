/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIO.disp;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unaryvoid.Disp;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Displays matrices
 */
@DOGMAMethodHook(provides = Disp.class)
public class DispOGMatrix implements Disp<OGMatrix> {

  @Override
  public void eval(OGMatrix array1) {
    Catchers.catchNull(array1);
    double[] data = array1.getData();
    int rows = array1.getNumberOfRows();
    int columns = array1.getNumberOfColumns();
    String str = "\nOGMatrix:\n";
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        str += String.format("%24.18f ", data[j * rows + i]);
      }
      str += String.format("\n");
    }
    System.out.println(str);
  }

}
