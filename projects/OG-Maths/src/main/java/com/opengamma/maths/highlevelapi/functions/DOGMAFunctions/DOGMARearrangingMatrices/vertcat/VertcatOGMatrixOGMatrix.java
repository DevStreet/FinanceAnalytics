/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.vertcat;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNonConformance;
import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.binary.Vertcat;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * 
 */
@DOGMAMethodHook(provides = Vertcat.class)
public class VertcatOGMatrixOGMatrix implements Vertcat<OGMatrix, OGMatrix, OGMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1, OGMatrix array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);

    final int rows1 = array1.getNumberOfRows();
    final int cols1 = array1.getNumberOfColumns();
    final double[] data1 = array1.getData();

    final int rows2 = array2.getNumberOfRows();
    final int cols2 = array2.getNumberOfColumns();
    final double[] data2 = array2.getData();

    if (cols1 != cols2) {
      throw new MathsExceptionNonConformance(rows1, cols1, rows2, cols2);
    }

    final int rowCount = (rows1 + rows2);
    // malloc
    double[] tmp = new double[(rows1 + rows2) * cols1];

    int rci;
    for (int i = 0; i < cols1; i++) {
      rci = rowCount * i;
      System.arraycopy(data1, rows1 * i, tmp, rci, rows1);
      System.arraycopy(data2, rows2 * i, tmp, rci + rows1, rows2);
    }

    return new OGMatrix(tmp, rowCount, cols1);
  }

}
