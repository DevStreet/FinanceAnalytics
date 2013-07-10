/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMARearrangingMatrices.diag;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Diag;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * does diag()
 */
@DOGMAMethodHook(provides = Diag.class)
public class DiagOGSparseMatrix implements Diag<OGArray<? extends Number>, OGSparseMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGSparseMatrix array1) {
    Catchers.catchNullFromArgList(array1, 1);
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();
    double[] tmp;
    final int[] rowIdx = array1.getRowIndex();
    final int[] colPtr = array1.getColumnPtr();
    if (rows == 1 || cols == 1) { // we are creating a diagonal matrix
      final int dim = rows > cols ? rows : cols;
      // unwind the data 
      tmp = new double[dim];
      if (cols == 1) { // row pointer dictates location of elements
        for (int i = 0; i < rowIdx.length; i++) {
          tmp[rowIdx[i]] = data[i];
        }
      } else { // col ptr dictates location of elements
        int ptr = 0;
        for (int i = 0; i < colPtr.length - 1; i++) {
          if (colPtr[i + 1] - colPtr[i] > 0) {
            tmp[i] = data[ptr];
            ptr++;
          }
        }
      }
      return new OGDiagonalMatrix(tmp);
    } else { // we are extracting the diagonal from a matrix
      final int dim = rows < cols ? rows : cols;
      tmp = new double[dim];
      for (int ir = 0; ir < cols; ir++) {
        for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
          if (rowIdx[i] == ir) {
            tmp[ir] = data[i];
          }
        }
      }
      return new OGMatrix(tmp, dim, 1);
    }

  }
}
