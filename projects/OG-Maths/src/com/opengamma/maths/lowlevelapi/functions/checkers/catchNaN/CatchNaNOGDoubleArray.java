/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions.checkers.catchNaN;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionEncounteredNaN;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.iss.IsNaN;

/**
 * Catches NaN in OGDoubleArray
 */
public final class CatchNaNOGDoubleArray extends CatchNaNAbstract<OGMatrix> {
  private static CatchNaNOGDoubleArray s_instance = new CatchNaNOGDoubleArray();

  public static CatchNaNOGDoubleArray getInstance() {
    return s_instance;
  }

  private CatchNaNOGDoubleArray() {
  }

  @Override
  public void catchnan(OGMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    boolean ret;
    final double[] data = array1.getData();
    ret = IsNaN.any(data);
    //NaN found, deal with it...
    if (ret) {
      int badrow = 0, badcol = 0;
      final int rows = array1.getNumberOfRows();
      final int columns = array1.getNumberOfColumns();
      boolean[] nans = IsNaN.getBooleans(data);
      int jmp = 0;
      for (int i = 0; i < columns; i++) {
        jmp = i * rows;
        for (int j = 0; j < rows; j++) {
          if (nans[jmp + j] == true) {
            badrow = j;
            badcol = i;
            break;
          }
        }
      }
      throw new MathsExceptionEncounteredNaN(badrow, badcol);      
    }
  }
}
