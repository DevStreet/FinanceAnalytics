/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.Mtimes;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.BLAS;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Does matrix * matrix in a mathematical sense
 */
@DOGMAMethodHook(provides = Mtimes.class)
public final class MtimesOGDiagonalMatrixOGMatrix implements Mtimes<OGArray<? extends Number>, OGDiagonalMatrix, OGMatrix> {

  private BLAS _localblas = new BLAS();

  @Override
  public OGArray<? extends Number> eval(OGDiagonalMatrix array1, OGMatrix array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);

    final int colsArray1 = array1.getNumberOfColumns();
    final int colsArray2 = array2.getNumberOfColumns();
    final int rowsArray1 = array1.getNumberOfRows();
    final int rowsArray2 = array2.getNumberOfRows();
    final double[] data1 = array1.getData();
    final double[] data2 = array2.getData();

    double[] tmp = null;
    int n = 0;
    OGArray<? extends Number> ret = null;

    if (colsArray1 == 1 && rowsArray1 == 1) { // We have scalar * matrix, scalar diagonal, matrix dense
      final double deref = data1[0];
      n = data2.length;
      tmp = new double[n];
      // quick return if NaN
      if (Double.isNaN(deref)) {
        for (int i = 0; i < n; i++) {
          tmp[i] = Double.NaN;
        }
      } else {
        System.arraycopy(data2, 0, tmp, 0, n);
        _localblas.dscal(n, deref, tmp, 1);
      }
      ret = new OGMatrix(tmp, rowsArray2, colsArray2);
    } else if (colsArray2 == 1 && rowsArray2 == 1) { // We have matrix * scalar, matrix diagonal, scalar dense
      final double deref = data2[0];
      if (Double.isNaN(deref)) { // if NaN in Dense matrix, diagonal matrix becomes all NaN, therefore returns as dense
        tmp = new double[rowsArray1 * colsArray1];
        n = tmp.length;
        for (int i = 0; i < n; i++) {
          tmp[i] = Double.NaN;
        }
        ret = new OGMatrix(tmp, rowsArray1, colsArray1);
      } else {
        n = data1.length;
        tmp = new double[n];
        System.arraycopy(data1, 0, tmp, 0, n);
        _localblas.dscal(n, deref, tmp, 1);
        ret = new OGDiagonalMatrix(tmp, rowsArray1, colsArray1);
      }

    } else {
      Catchers.catchBadCommute(colsArray1, "Columns in first array", rowsArray2, "Rows in second array");
      //TODO: write as a weird DGEMM, might swap this out to a BLAS call at some point
      final int fm = rowsArray1;
      final int fn = colsArray2;
      double[] cMatrix = new double[fm * fn];
      // all we really care about is data within the length of the diag
      final int len = data1.length;
      for (int i = 0; i < fn; i++) {
        for (int j = 0; j < len; j++) {
          cMatrix[i * fm + j] = data1[j] * data2[i * rowsArray2 + j];
        }
      }
      ret = new OGMatrix(cMatrix, fm, fn);
    }
    return ret;
  }

}
