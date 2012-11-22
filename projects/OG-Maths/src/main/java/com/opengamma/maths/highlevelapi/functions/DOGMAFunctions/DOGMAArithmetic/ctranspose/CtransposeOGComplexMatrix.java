/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Ctranspose;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Conjugate Transposes an OGComplexArray
 * TODO: At some point consider COW or at least state propagated permutations for things like this?!
 */
@DOGMAMethodHook(provides = Ctranspose.class)
public final class CtransposeOGComplexMatrix implements Ctranspose<OGComplexMatrix, OGComplexMatrix> {
  @Override
  public OGComplexMatrix eval(OGComplexMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int retRows = columnsArray1, retCols = rowsArray1;
    double[] data = array1.getData();
    double[] tmp = new double[2 * rowsArray1 * columnsArray1];

    int ir, irpj, ix2, jcolpix2, rA1x2 = 2 * rowsArray1;
    for (int i = 0; i < columnsArray1; i++) {
      ix2 = i * 2;
      ir = i * rA1x2;
      for (int j = 0; j < rA1x2; j += 2) {
        jcolpix2 = j * columnsArray1 + ix2;
        irpj = ir + j;
        tmp[jcolpix2] = data[irpj];
        tmp[jcolpix2 + 1] = -data[irpj + 1]; // negate imaginary
      }
    }
    return new OGComplexMatrix(tmp, retRows, retCols);
  }
}
