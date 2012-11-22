/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Mtimes;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Does matrix * matrix in a mathematical sense
 */
@DOGMAMethodHook(provides = Mtimes.class)
public final class MtimesOGSparseMatrixOGMatrix implements Mtimes<OGArray<? extends Number>, OGSparseMatrix, OGMatrix> {

  private BLAS _localblas = new BLAS();
  
  @Override
  public OGArray<? extends Number> eval(OGSparseMatrix array1, OGMatrix array2) {
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
    OGArray<? extends Number> ret = null;

    if (colsArray1 == 1 && rowsArray1 == 1) { // We have scalar * dense matrix
      final double deref = data1[0];
      n = data2.length;
      tmp = new double[n];
      System.arraycopy(data2, 0, tmp, 0, n);
      _localblas.dscal(n, deref, tmp, 1);
      ret = new OGMatrix(tmp, rowsArray2, colsArray2);
    } else if (colsArray2 == 1 && rowsArray2 == 1) { // We have sparse matrix * dense scalar
      final double deref = data2[0];
      n = data1.length;
      tmp = new double[n];
      System.arraycopy(data1, 0, tmp, 0, n);
      _localblas.dscal(n, deref, tmp, 1);
      ret = new OGSparseMatrix(colPtr1, rowIdx1, tmp, rowsArray1, colsArray1);
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
        ret = new OGMatrix(tmp, rowsArray1, 1);
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
        ret = new OGMatrix(cMatrix, fm, fn);

      }
    }
    return ret;
  }
}
