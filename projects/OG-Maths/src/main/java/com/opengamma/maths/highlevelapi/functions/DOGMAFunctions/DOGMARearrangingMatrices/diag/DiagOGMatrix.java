/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.diag;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Diag;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * does diag()
 */
@DOGMAMethodHook(provides = Diag.class)
public class DiagOGMatrix implements Diag<OGArray<? extends Number>, OGMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    double[] tmp;
    if (rows == 1 || cols == 1) { // we are creating a diagonal matrix
      return new OGDiagonalMatrix(array1.getData());
    } else { // we are extracting the diagonal from a matrix
      final int dim = rows < cols ? rows : cols;
      tmp = new double[dim];
      double[] data = array1.getData();
      for (int i = 0; i < dim; i++) {
        tmp[i] = data[i * rows + i];
      }
      return new OGMatrix(tmp, dim, 1);
    }

  }

}
