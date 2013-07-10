/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAComplexUtil.abs;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Abs;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.SLATEC;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * does abs
 * TODO: wire in native vector variant
 */
@DOGMAMethodHook(provides = Abs.class)
public final class AbsOGComplexMatrix implements Abs<OGMatrix, OGComplexMatrix> {

  @Override
  public OGMatrix eval(OGComplexMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final double[] dataArray1 = array1.getData();
    final int machineN = dataArray1.length;
    final int mathsN = machineN / 2;

    double[] tmp = new double[mathsN];
    int ptr = 0;
    double real, imag;
    for (int i = 0; i < machineN; i += 2) {
      real = dataArray1[i];
      imag = dataArray1[i + 1];
      // branch if both parts of the number are +/- Inf and return +Inf. 
      // SLATEC ZABS.f struggles with this as the alg self scales and so Inf/Inf can 
      // occur which needs handling as below
      if (Double.isInfinite(imag) && Double.isInfinite(real)) {
        tmp[ptr] = Double.POSITIVE_INFINITY;
      } else {
        tmp[ptr] = SLATEC.getInstance().zabs(real, imag);
      }
      ptr++;
    }
    return new OGMatrix(tmp, rowsArray1, columnsArray1);
  }
}
