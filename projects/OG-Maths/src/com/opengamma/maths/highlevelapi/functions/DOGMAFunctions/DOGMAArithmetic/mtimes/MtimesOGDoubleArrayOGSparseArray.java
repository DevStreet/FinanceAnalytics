/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Does matrix * matrix in a mathematical sense
 */
public final class MtimesOGDoubleArrayOGSparseArray extends MtimesAbstract<OGDoubleArray, OGSparseArray> {
  private static MtimesOGDoubleArrayOGSparseArray s_instance = new MtimesOGDoubleArrayOGSparseArray();

  public static MtimesOGDoubleArrayOGSparseArray getInstance() {
    return s_instance;
  }

  private MtimesOGDoubleArrayOGSparseArray() {
  }

  private BLAS _localblas = new BLAS();

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> mtimes(OGDoubleArray array1, OGSparseArray array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);

    final int colsArray1 = array1.getNumberOfColumns();
    final int colsArray2 = array2.getNumberOfColumns();
    final int rowsArray1 = array1.getNumberOfRows();
    final int rowsArray2 = array2.getNumberOfRows();
    final double[] data1 = array1.getData();
    final double[] data2 = array2.getData();
    final int[] colPtr2 = array2.getColumnPtr();
    final int[] rowIdx2 = array2.getRowIndex();

    double[] tmp = null;
    int n = 0;
    OGArraySuper<Number> ret = null;

    if (colsArray1 == 1 && rowsArray1 == 1) { // We have scalar * sparse matrix
      final double deref = data1[0];
      n = data2.length;
      tmp = new double[n];
      System.arraycopy(data2, 0, tmp, 0, n);
      _localblas.dscal(n, deref, tmp, 1);
      ret = new OGSparseArray(colPtr2, rowIdx2, tmp, rowsArray2, colsArray2);
    } else if (colsArray2 == 1 && rowsArray2 == 1) { // We have dense matrix * sparse scalar
      final double deref = data2[0];
      n = data1.length;
      tmp = new double[n];
      System.arraycopy(data1, 0, tmp, 0, n);
      _localblas.dscal(n, deref, tmp, 1);
      ret = new OGDoubleArray(tmp, rowsArray1, colsArray1);
    } else {
      Catchers.catchBadCommute(colsArray1, "Columns in first array", rowsArray2, "Rows in second array");
      // TODO: refactor these calls into a SparseBLAS.
      if (colsArray2 == 1) { // A*x
        System.out.println("dgemv n");
        tmp = new double[rowsArray1];
        int ptr = 0;
        for (int ir = 0; ir < colsArray2; ir++) {
          for (int i = colPtr2[ir]; i < colPtr2[ir + 1]; i++) {
            _localblas.daxpy(rowsArray1, data2[ptr], data1, rowIdx2[ptr] * rowsArray1, 1, tmp, 0, 1);
            ptr++;
          }
        }
        ret = new OGDoubleArray(tmp, rowsArray1, 1);
      } else {
        final int fm = rowsArray1;
        final int fn = colsArray2;
        double[] cMatrix = new double[fm * fn];

        //form C col wise, offset daxpy
        int ptr = 0;
        for (int ir = 0; ir < colsArray2; ir++) {
          for (int i = colPtr2[ir]; i < colPtr2[ir + 1]; i++) {
            _localblas.daxpy(rowsArray1, data2[ptr], data1, rowIdx2[ptr] * rowsArray1, 1, cMatrix, ir * rowsArray1, 1);
            ptr++;
          }
        }
        ret = new OGDoubleArray(cMatrix, fm, fn);
      }
    }
    return ret;
  }
}
