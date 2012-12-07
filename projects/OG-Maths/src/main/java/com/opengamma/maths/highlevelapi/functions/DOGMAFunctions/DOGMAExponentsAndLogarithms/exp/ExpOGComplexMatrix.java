/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAExponentsAndLogarithms.exp;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Exp;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * does exp 
 * TODO: slatec calls to complex exp, or perhaps something more vectorised
 * Need exp(a+bi) = exp(a)*(cos(b) + i*sin(b))
 */
@DOGMAMethodHook(provides = Exp.class)
public final class ExpOGComplexMatrix implements Exp<OGComplexMatrix, OGComplexMatrix> {

  @Override
  public OGComplexMatrix eval(OGComplexMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final double[] dataArray1 = array1.getData();
    final int machineN = dataArray1.length;
    final int mathsN = machineN / 2;

    double[] tmp = new double[machineN];
    int ptr = 0;
    double expA;
    for (int i = 0; i < mathsN; i++) {
      expA = Math.exp(dataArray1[ptr]);
      tmp[ptr] = expA * Math.cos(dataArray1[ptr + 1]);
      tmp[ptr + 1] = expA * Math.sin(dataArray1[ptr + 1]);
      ptr += 2;
    }
    return new OGComplexMatrix(tmp, rowsArray1, columnsArray1);

  }
}
