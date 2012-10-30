/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.rdivide;

import java.util.Arrays;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Does rdivide on OGDouble pairings
 */
public final class RdivideOGDoubleArrayOGDoubleArray implements RdivideAbstract<OGMatrix, OGMatrix> {
  private static RdivideOGDoubleArrayOGDoubleArray s_instance = new RdivideOGDoubleArrayOGDoubleArray();

  public static RdivideOGDoubleArrayOGDoubleArray getInstance() {
    return s_instance;
  }

  private RdivideOGDoubleArrayOGDoubleArray() {
  }

  @Override
  public OGMatrix rdivide(OGMatrix array1, OGMatrix array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);

    // if either is a single number then we just div
    // else ew div
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;

    double[] tmp = null;
    int n;

    if (rowsArray1 == 1 && columnsArray1 == 1) { // single / matrix  
      n = array2.getData().length;
      tmp = new double[n];
      double[] data = array2.getData();
      final double[] singleDouble = array1.getData();
      final double deref = singleDouble[0];
      Arrays.fill(tmp, deref);
      for (int i = 0; i < n; i++) {
        tmp[i] /= data[i];
      }
      retRows = rowsArray2;
      retCols = columnsArray2;

    } else if (rowsArray2 == 1 && columnsArray2 == 1) { // matrix / single
      n = array1.getData().length;
      tmp = new double[n];
      System.arraycopy(array1.getData(), 0, tmp, 0, n);

      final double[] singleDouble = array2.getData();
      final double deref = singleDouble[0];
      for (int i = 0; i < n; i++) {
        tmp[i] /= deref;
      }
      retRows = rowsArray1;
      retCols = columnsArray1;

    } else { // matrix/matrix
      Catchers.catchBadCommute(rowsArray1, "Rows in arg 1", rowsArray2, "Rows in arg 2");
      Catchers.catchBadCommute(columnsArray1, "Columns in arg 1", columnsArray2, "Columns in arg 2");
      retRows = rowsArray1;
      retCols = columnsArray1;
      n = array1.getData().length;
      tmp = new double[n];
      final double[] dat2 = array2.getData();
      System.arraycopy(array1.getData(), 0, tmp, 0, n);
      for (int i = 0; i < n; i++) {
        tmp[i] /= dat2[i];
      }
    }
    return new OGMatrix(tmp, retRows, retCols);
  }
}
