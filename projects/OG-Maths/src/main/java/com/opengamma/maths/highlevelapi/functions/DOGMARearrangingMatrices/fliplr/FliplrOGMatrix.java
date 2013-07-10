/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.fliplr;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Fliplr;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.functions.memory.OGTypesMalloc;

/**
 * does fliplr
 */
@DOGMAMethodHook(provides = Fliplr.class)
public class FliplrOGMatrix implements Fliplr<OGMatrix, OGMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();
    final int n = data.length;
    double[] tmp = new double[n];
    for (int i = 0; i < cols; i++) {
      System.arraycopy(data, (cols - i - 1) * rows, tmp, i * rows, rows);
    }
    return OGTypesMalloc.OGMatrixBasedOnStructureOf(array1, tmp);
  }

}
