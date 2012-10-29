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
public final class MtimesOGSparseArrayOGDoubleArray implements MtimesAbstract<OGSparseArray, OGDoubleArray> {
  private static MtimesOGSparseArrayOGDoubleArray s_instance = new MtimesOGSparseArrayOGDoubleArray();

  public static MtimesOGSparseArrayOGDoubleArray getInstance() {
    return s_instance;
  }

  private MtimesOGSparseArrayOGDoubleArray() {
  }

  private BLAS _localblas = new BLAS();

  @Override
  public OGArraySuper<? extends Number> mtimes(OGSparseArray array1, OGDoubleArray array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);

    final int colsArray1 = array1.getNumberOfColumns();
    final int colsArray2 = array2.getNumberOfColumns();
    final int rowsArray1 = array1.getNumberOfRows();
    final int rowsArray2 = array2.getNumberOfRows();
    final double[] data1 = array1.getData();
    final double[] data2 = array2.getData();
    final int[] colPtr1 = array1.getColumnPtr();
    final int[] rowIdx1 = array1.getRowIndex();

    double[] tmp = null;
    int n = 0;
    OGArraySuper<? extends Number> ret = null;

    if (colsArray1 == 1 && rowsArray1 == 1) { // We have scalar * dense matrix
      final double deref = data1[0];
      n = data2.length;
      tmp = new double[n];
      System.arraycopy(data2, 0, tmp, 0, n);
      _localblas.dscal(n, deref, tmp, 1);
      ret = new OGDoubleArray(tmp, rowsArray2, colsArray2);
    } else if (colsArray2 == 1 && rowsArray2 == 1) { // We have sparse matrix * dense scalar
      final double deref = data2[0];
      n = data1.length;
      tmp = new double[n];
      System.arraycopy(data1, 0, tmp, 0, n);
      _localblas.dscal(n, deref, tmp, 1);
      ret = new OGSparseArray(colPtr1, rowIdx1, tmp, rowsArray1, colsArray1);
    } else {
      Catchers.catchBadCommute(colsArray1, "Columns in first array", rowsArray2, "Rows in second array");
      // TODO: refactor these calls into a SparseBLAS.
      if (colsArray2 == 1) { // A*x
        tmp = new double[rowsArray1];
        int ptr = 0;
        for (int i = 0; i < colsArray1; i++) {
          for (int j = colPtr1[i]; j < colPtr1[i + 1]; j++) {
            tmp[rowIdx1[ptr]] += data1[ptr] * data2[i];
            ptr++;
          }
        }
        ret = new OGDoubleArray(tmp, rowsArray1, 1);
      } else {
        final int fm = rowsArray1;
        final int fn = colsArray2;
        double[] cMatrix = new double[fm * fn]; // will be resized

        // C = A * B
        // non-zero pattern in columns of C is given by the intersection of all rows of A with columns of B. As B is dense intersection is assumed.
        int jmp1, jmp2;
        for (int icol = 0; icol < fm; icol++) {
          jmp2 = icol * rowsArray2;
          jmp1 = icol * fm;
          int ptr = 0;
          for (int i = 0; i < colsArray1; i++) {
            for (int j = colPtr1[i]; j < colPtr1[i + 1]; j++) {
              cMatrix[jmp1 + rowIdx1[ptr]] += data1[ptr] * data2[i + jmp2];
              ptr++;
            }
          }
        }
        ret = new OGDoubleArray(cMatrix, fm, fn);

      }
    }
    return ret;
  }
}
