/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Transposes an OGIndexArray
 * TODO: At some point consider COW or at least state propagated permutations for things like this?!
 */
public final class TransposeOGIndexMatrix implements TransposeAbstract<OGIndexMatrix> {
  private static TransposeOGIndexMatrix s_instance = new TransposeOGIndexMatrix();

  public static TransposeOGIndexMatrix getInstance() {
    return s_instance;
  }

  private TransposeOGIndexMatrix() {
  }

  @Override
  public OGIndexMatrix transpose(OGIndexMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int retRows = columnsArray1, retCols = rowsArray1;
    int[] data = array1.getData();
    int[] tmp = new int[rowsArray1 * columnsArray1];

    int ir;
    for (int i = 0; i < columnsArray1; i++) {
      ir = i * rowsArray1;
      for (int j = 0; j < rowsArray1; j++) {
        tmp[j * columnsArray1 + i] = data[ir + j];
      }
    }
    return new OGIndexMatrix(tmp, retRows, retCols);
  }

}
