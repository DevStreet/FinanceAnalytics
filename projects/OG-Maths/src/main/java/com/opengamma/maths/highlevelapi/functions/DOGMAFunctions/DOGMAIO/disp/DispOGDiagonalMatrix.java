/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIO.disp;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unaryvoid.Disp;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Displays OGDiagonalMatrix matrices
 */
@DOGMAMethodHook(provides = Disp.class)
public class DispOGDiagonalMatrix implements Disp<OGDiagonalMatrix> {

  @Override
  public void eval(OGDiagonalMatrix array1) {
    Catchers.catchNull(array1);
    int columns = array1.getNumberOfColumns();
    int rows = array1.getNumberOfRows();
    double[] data = array1.getData();
    String str = "OGDiagonalMatrix:\n";
    String zeroStr = String.format("%24.18f ", 0.d);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (i == j && i < data.length && j < data.length) {
          str += String.format("%24.18f ", data[i]);
        } else {
          str += zeroStr;
        }
      }
      str += String.format("\n");
    }
    System.out.println(str);
  }

}
