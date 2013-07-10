/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAArithmetic.uminus;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Uminus;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
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
    double[] data = array1.getData();
    double[] tmp = new double[data.length];
    EasyIZY.vz_negate(data, tmp);
    return new OGComplexMatrix(tmp, rowsArray1, columnsArray1);
  }

}
