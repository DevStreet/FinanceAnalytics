/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIO.disp;

import java.util.Formatter;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unaryvoid.Disp;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexDiagonalMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Displays OGComplexDiagonalMatrix matrices
 */
@DOGMAMethodHook(provides = Disp.class)
public class DispOGComplexDiagonalMatrix implements Disp<OGComplexDiagonalMatrix> {

  @Override
  public void eval(OGComplexDiagonalMatrix array1) {
    Catchers.catchNull(array1);
    int columns = array1.getNumberOfColumns();
    int rows = array1.getNumberOfRows();
    double[] data = array1.getData();
    int mathsN = data.length / 2;
    double abstmp;
    StringBuffer sb = new StringBuffer();
    Formatter formatter = new Formatter(sb);
    sb.append("OGComplexDiagonalMatrix:\n");
    boolean previ = false;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (i == j && i < mathsN && j < mathsN) {
          previ = true;
          formatter.format("%24.18f", data[2 * i]);
          abstmp = (data[2 * i + 1]);
          if (abstmp < 0) {
            sb.append("    -");
          } else {
            sb.append("    +");
          }
          formatter.format("%24.18f", Math.abs(abstmp));
          sb.append("i");
        } else {
          if (previ) {
            formatter.format("%23.18f    +%24.18f", 0.d, 0.d);
          } else {
            formatter.format("%24.18f    +%24.18f", 0.d, 0.d);
          }
          previ = false;
        }
      }
      sb.append("\n");
    }
    System.out.println(sb.toString());
  }

}
