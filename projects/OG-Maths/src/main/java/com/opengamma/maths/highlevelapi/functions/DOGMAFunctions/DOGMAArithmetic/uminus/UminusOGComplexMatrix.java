/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.uminus;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Uminus;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Unary minus on an OGComplexMatrix
 */
//TODO: At some point consider COW or at least state propagated permutations for things like this?!
@DOGMAMethodHook(provides = Uminus.class)
public final class UminusOGComplexMatrix implements Uminus<OGComplexMatrix, OGComplexMatrix> {

  @Override
  public OGComplexMatrix eval(OGComplexMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int retRows = columnsArray1, retCols = rowsArray1;
    double[] data = array1.getData();
    double[] tmp = new double[rowsArray1 * columnsArray1];

    final int len = data.length;
    for (int i = 0; i < len; i++) {
      tmp[i] = -data[i];
    }
    return new OGComplexMatrix(tmp, retRows, retCols);
  }

}
