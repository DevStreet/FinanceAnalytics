/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import java.util.Arrays;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;

/**
 * Adds OGSparseArrays to OGDoubleArrays   
 */
public final class PlusOGDoubleArrayOGSparseArray extends PlusMinusAbstract<OGDoubleArray, OGSparseArray> {
  private static PlusOGDoubleArrayOGSparseArray s_instance = new PlusOGDoubleArrayOGSparseArray();

  public static PlusOGDoubleArrayOGSparseArray getInstance() {
    return s_instance;
  }

  private PlusOGDoubleArrayOGSparseArray() {
  }

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> plusminus(OGDoubleArray array1, OGSparseArray array2, final int op) {
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;

    double[] tmp = null;

    OGArraySuper<Number> retArray = null;

    // Actually adding arrays

    if (rowsArray1 == 1 && columnsArray1 == 1) { // Dense array is actually a single number, so we make the sparse array a OGDoubleArray and ADD
      final int n = columnsArray2 * rowsArray2;
      tmp = new double[n];
      final double singleDouble = array2.getData()[0];
      Arrays.fill(tmp, singleDouble);
      final int[] colPtr = array2.getColumnPtr();
      final int[] rowIdx = array2.getRowIndex();
      final double[] data = array2.getData();
      retRows = rowsArray2;
      retCols = columnsArray2;
      for (int ir = 0; ir < columnsArray2; ir++) {
        for (int i = colPtr[ir]; i <= colPtr[ir + 1] - 1; i++) { // loops through elements of correct column
          tmp[rowIdx[i] + ir * rowsArray2] +=  op * data[i];
        }
      }
      retArray = new OGDoubleArray(tmp, retRows, retCols);

    } else if (rowsArray2 == 1 && columnsArray2 == 1) { // Sparse array is actually a single number, so we can just deref and add
      final int n = array1.getData().length;
      tmp = new double[n];
      System.arraycopy(array1.getData(), 0, tmp, 0, n);
      final double[] singleDouble = array2.getData();
      final double deref = singleDouble[0];
      for (int i = 0; i < n; i++) {
        tmp[i] = tmp[i] + op * deref;
      }
      retRows = rowsArray1;
      retCols = columnsArray1;
      retArray = new OGDoubleArray(tmp, retRows, retCols);
    } else { // Both arrays are full dimension, do sparse add    
      retRows = rowsArray1;
      retCols = columnsArray1;
      final int n = retRows * retCols;
      tmp = new double[n];
      System.arraycopy(array1.getData(), 0, tmp, 0, n);
      final int[] colPtr = array2.getColumnPtr();
      final int[] rowIdx = array2.getRowIndex();
      final double[] data = array2.getData();
      for (int ir = 0; ir < retCols; ir++) {
        for (int i = colPtr[ir]; i <= colPtr[ir + 1] - 1; i++) {
          tmp[rowIdx[i] + ir * retRows] += op * data[i];
        }
      }
      retArray = new OGDoubleArray(tmp, retRows, retCols);
    }
    return retArray;
  }
}
