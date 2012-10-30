/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.sinh;


import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Math.sinh on OGSparse
 */
public final class SinhOGSparseMatrix implements SinhAbstract<OGSparseMatrix> {
  private static SinhOGSparseMatrix s_instance = new SinhOGSparseMatrix();

  public static SinhOGSparseMatrix getInstance() {
    return s_instance;
  }

  private SinhOGSparseMatrix() {
  }

  @Override
  public OGMatrix sinh(OGSparseMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);

    final int rowsArray1 = array1.getNumberOfRows();
    final int columnsArray1 = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] dataArray1 = array1.getData();
    final int n = rowsArray1 * columnsArray1;
    double[] tmp = new double[n]; //Math.sinh(0)=0

    for (int ir = 0; ir < columnsArray1; ir++) {
      for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) { // loops through elements of correct column
        tmp[rowIdx[i] + ir * rowsArray1] = Math.sinh(dataArray1[i]);
      }
    }

    return new OGMatrix(tmp, rowsArray1, columnsArray1);
  }

}
