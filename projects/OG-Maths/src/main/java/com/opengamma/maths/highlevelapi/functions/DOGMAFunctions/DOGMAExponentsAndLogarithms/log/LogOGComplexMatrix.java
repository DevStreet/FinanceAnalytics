/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAExponentsAndLogarithms.log;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Log;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.ZLOG;

/**
 * does log() 
 * TODO: slatec calls to complex exp, or perhaps something more vectorised
 * Need log(a+bi) = sqrt(a^2+b^2)*cos() 
 */
@DOGMAMethodHook(provides = Log.class)
public final class LogOGComplexMatrix implements Log<OGComplexMatrix, OGComplexMatrix> {

  @Override
  public OGComplexMatrix eval(OGComplexMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final double[] dataArray1 = array1.getData();
    final int machineN = dataArray1.length;
    double[] tmp = new double[machineN];
    double[] logReal = new double[1];
    double[] logImag = new double[1];
    int[] ierr = new int[1];
    for (int i = 0; i < machineN; i += 2) {
      ZLOG.zlog(dataArray1[i], dataArray1[i + 1], logReal, logImag, ierr);
      if (ierr[0] == 0) {
        tmp[i] = logReal[0];
        tmp[i + 1] = logImag[0];
      } else {
        tmp[i] = Double.NEGATIVE_INFINITY;
        tmp[i + 1] = 0;
      }
    }
    return new OGComplexMatrix(tmp, rowsArray1, columnsArray1);
  }
}
