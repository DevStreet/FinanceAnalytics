/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.power;

import java.util.Arrays;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.binary.Power;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.exposedapi.EasyIZY;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Power {@link OGMatrix} to {@link OGSparseMatrix}    
 */
@DOGMAMethodHook(provides = Power.class)
public final class PowerOGMatrixOGSparseMatrix implements Power<OGArray<? extends Number>, OGMatrix, OGSparseMatrix> {

  @Override
  public OGMatrix eval(OGMatrix array1, OGSparseMatrix array2) {
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;

    double[] tmp = null;

    OGMatrix retArray = null;

    if (rowsArray1 == 1 && columnsArray1 == 1) { // Dense array is actually a single number
      final int n = columnsArray2 * rowsArray2;
      tmp = new double[n];
      final int[] colPtr = array2.getColumnPtr();
      final int[] rowIdx = array2.getRowIndex();
      final double[] data = array2.getData();
      retRows = rowsArray2;
      retCols = columnsArray2;
      final double single = array1.getData()[0];
      Arrays.fill(tmp, 1.e0); // x^0=1, this is the backing array      
      double[] buf = new double[data.length];
      Arrays.fill(buf, single); // replicate constant
      EasyIZY.vd_pow(buf, data, buf); // compute constant ^ data
      for (int ir = 0; ir < columnsArray2; ir++) { // write to backing array
        for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
          tmp[rowIdx[i] + ir * rowsArray2] = buf[i];
        }
      }
      retArray = new OGMatrix(tmp, retRows, retCols);

    } else if (rowsArray2 == 1 && columnsArray2 == 1) { // Sparse array is actually a single number
      final int n = array1.getData().length;
      tmp = new double[n];
      final double deref = array2.getData()[0];
      EasyIZY.vd_powx(array1.getData(), deref, tmp);
      retRows = rowsArray1;
      retCols = columnsArray1;
      retArray = new OGMatrix(tmp, retRows, retCols);

    } else { // Both arrays are full dimension, do sparse pow    
      Catchers.catchBadCommute(rowsArray1, "rows in first array", rowsArray2, "rows in second array");
      Catchers.catchBadCommute(columnsArray1, "columns in first array", columnsArray2, "columns in second array");
      retRows = rowsArray1;
      retCols = columnsArray1;
      final int n = retRows * retCols;
      tmp = new double[n];
      Arrays.fill(tmp, 1); // x^0=1
      final int[] colPtr = array2.getColumnPtr();
      final int[] rowIdx = array2.getRowIndex();
      final double[] denseData = array1.getData();
      final double[] sparseData = array2.getData();
      for (int ir = 0; ir < retCols; ir++) {
        for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
          tmp[rowIdx[i] + ir * retRows] = Math.pow(denseData[rowIdx[i] + ir * retRows], sparseData[i]);
        }
      }
      retArray = new OGMatrix(tmp, retRows, retCols);
    }

    return retArray;
  }
}
