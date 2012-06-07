/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAUtilityFunctions.dot;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * 
 */
public final class DotOGDoubleArrayOGDoubleArray extends DotAbstract<OGDoubleArray, OGDoubleArray> {
  private static DotOGDoubleArrayOGDoubleArray s_instance = new DotOGDoubleArrayOGDoubleArray();

  public static DotOGDoubleArrayOGDoubleArray getInstance() {
    return s_instance;
  }

  private DotOGDoubleArrayOGDoubleArray() {
  }

  private BLAS _localblas = new BLAS();

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> dot(OGDoubleArray array1, OGDoubleArray array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);
    OGDoubleArray dot = null;
    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final int rowsArray2 = array2.getNumberOfRows();
    final int columnsArray2 = array2.getNumberOfColumns();
    final int lenArray1 = rowsArray1 * columnsArray1;
    final int lenArray2 = rowsArray2 * columnsArray2;
    final double[] dataArray1 = array1.getData();
    final double[] dataArray2 = array2.getData();

    double[] tmp = null;

    // check the commute. They must be either 2 vectors *or* the same size.
    if (lenArray1 == lenArray2) { // we have 2 vectors the same length
      dot = new OGDoubleArray(_localblas.ddot(lenArray1, dataArray1, 1, dataArray2, 1));
    } else if ((rowsArray1 == rowsArray2) && (columnsArray1 == columnsArray2)) {
      int retRows = 1;
      int retCols = columnsArray1;
      tmp = new double[columnsArray1];
      int jmp;
      for (int i = 0; i < columnsArray1; i++) {
        jmp = i * rowsArray1;
        tmp[i] = _localblas.ddot(rowsArray1, dataArray1, jmp, 1, dataArray2, jmp, 1);
      }
      dot = new OGDoubleArray(tmp, retRows, retCols);
    } else {
      Catchers.catchBadCommute("Arrays must be the same size for vectorised dot product. Array1 is [" + rowsArray1 + "," + columnsArray1 + "], Array2 is [" + rowsArray2 + "," + columnsArray2 + "]");
    }
    return dot;
  }
}
