/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions.checkers.catchInf;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionEncounteredInf;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.iss.IsInf;

/**
 * 
 */
public final class CatchInfOGSparseArray extends CatchInfAbstract<OGSparseMatrix> {
  private static CatchInfOGSparseArray s_instance = new CatchInfOGSparseArray();

  public static CatchInfOGSparseArray getInstance() {
    return s_instance;
  }

  private CatchInfOGSparseArray() {
  }

  @Override
  public void catchinf(OGSparseMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    boolean ret;
    final double[] data = array1.getData();
    ret = IsInf.any(data);
    //Inf found, deal with it...
    if (ret) {
      final int[] colPtr = array1.getColumnPtr();
      final int[] rowIdx = array1.getRowIndex();
      int badrow = 0, badcol = 0;
      final int columns = array1.getNumberOfColumns();
      for (int ir = 0; ir < columns; ir++) {
        for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) { // loops through elements of correct column
          if (Double.isInfinite(data[i])) {
            badrow = rowIdx[i];
            badcol = ir;
            break;
          }
        }
      }
      throw new MathsExceptionEncounteredInf(badrow, badcol);
    }
  }

}
