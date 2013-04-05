/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.horzcat;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.binary.Horzcat;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Horzcat impl for horzcatting OGDouble OGDouble
 */
@DOGMAMethodHook(provides = Horzcat.class)
public final class HorzcatOGMatrixOGMatrix implements Horzcat<OGMatrix, OGMatrix, OGMatrix> {
  @Override
  public OGMatrix eval(OGMatrix array1, OGMatrix array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);
    Catchers.catchBadCommute(array1.getNumberOfRows(), "array1", array2.getNumberOfRows(), "array2");

    final int baseRows = array1.getNumberOfRows();
    final int cols1 = array1.getNumberOfColumns();
    final int cols2 = array2.getNumberOfColumns();
    final int colCount = cols1 + cols2;

    // malloc
    double[] tmp = new double[baseRows * colCount];
    // memcpy to end
    System.arraycopy(array2.getData(), 0, tmp, cols1, cols2);
    return new OGMatrix(tmp, baseRows, colCount);
  }

}
