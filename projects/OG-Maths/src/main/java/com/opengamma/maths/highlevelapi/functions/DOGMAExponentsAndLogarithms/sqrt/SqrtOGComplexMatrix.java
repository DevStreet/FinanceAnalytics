/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAExponentsAndLogarithms.sqrt;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Sqrt;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * does sqrt
 */
@DOGMAMethodHook(provides = Sqrt.class)
public final class SqrtOGComplexMatrix implements Sqrt<OGArray<? extends Number>, OGComplexMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGComplexMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final double[] dataArray1 = array1.getData();
    final int n = dataArray1.length;

    double[] sqrts = new double[n];

    // TODO: at some point, look at compressing the return to OGMatrix if all the complex data is real?
    EasyIZY.vz_sqrt(dataArray1, sqrts);

    return new OGComplexMatrix(sqrts, rowsArray1, columnsArray1);
  }
}
