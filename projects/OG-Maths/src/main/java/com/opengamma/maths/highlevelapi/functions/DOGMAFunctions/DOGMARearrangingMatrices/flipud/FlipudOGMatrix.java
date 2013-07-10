/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.flipud;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Flipud;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.memory.OGTypesMalloc;

/**
 * does flipud
 */
@DOGMAMethodHook(provides = Flipud.class)
public class FlipudOGMatrix implements Flipud<OGMatrix, OGMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();
    final int n = data.length;
    double[] tmp = new double[n];
    int rm1 = rows - 1;
    int ir;
    for (int i = 0; i < cols; i++) {
      ir = i * rows;
      for (int j = 0; j < rows; j++) {
        tmp[ir + j] = data[ir + rm1 - j];
      }
    }
    return OGTypesMalloc.OGMatrixBasedOnStructureOf(array1, tmp);
  }
}
