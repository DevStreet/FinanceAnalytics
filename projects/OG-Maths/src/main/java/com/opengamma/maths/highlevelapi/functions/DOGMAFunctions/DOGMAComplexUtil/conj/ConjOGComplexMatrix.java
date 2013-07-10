/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAComplexUtil.conj;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Conj;
import com.opengamma.maths.highlevelapi.datatypes.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * does conj()
 * TODO: wire in native vector variant
 */
@DOGMAMethodHook(provides = Conj.class)
public final class ConjOGComplexMatrix implements Conj<OGComplexMatrix, OGComplexMatrix> {

  @Override
  public OGComplexMatrix eval(OGComplexMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final double[] dataArray1 = array1.getData();
    final int machineN = dataArray1.length;

    double[] tmp = new double[machineN];
    System.arraycopy(dataArray1, 0, tmp, 0, machineN);
    for (int i = 1; i < machineN; i += 2) {
      tmp[i] = -tmp[i];
    }
    return new OGComplexMatrix(tmp, rowsArray1, columnsArray1);
  }
}
