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
import com.opengamma.maths.lowlevelapi.functions.memory.OGTypesMalloc;
import com.opengamma.maths.lowlevelapi.functions.memory.SparseMemoryManipulation;

/**
 * Does elementwise OGSparse .^ OGSparse
 */
@DOGMAMethodHook(provides = Power.class)
public final class PowerOGSparseMatrixOGSparseMatrix implements Power<OGArray<? extends Number>, OGSparseMatrix, OGSparseMatrix> {

  @Override
  public OGArray<? extends Number> eval(OGSparseMatrix array1, OGSparseMatrix array2) {
    Catchers.catchNullFromArgList(array1, 1);
    Catchers.catchNullFromArgList(array2, 2);
    // if either is a single number then we just mul by that
    // else ew mul.
    int rowsArray1 = array1.getNumberOfRows();
    int columnsArray1 = array1.getNumberOfColumns();
    int rowsArray2 = array2.getNumberOfRows();
    int columnsArray2 = array2.getNumberOfColumns();
    int retRows = 0, retCols = 0;

    double[] tmp = null;
    int n;
    OGArray<? extends Number> ret = null;

    if (rowsArray1 == 1 && columnsArray1 == 1) { // Single valued Sparse .^ Sparse = scaled Sparse, fill in 1
      n = columnsArray2 * rowsArray2;
      tmp = new double[n];
      final double[] data = array2.getData();
      retRows = rowsArray2;
      retCols = columnsArray2;
      final double single = array1.getData()[0];
      Arrays.fill(tmp, 1.e0); // x^0=1, this is the backing array      
      double[] buf = new double[data.length];
      if (single != 0) {
        Arrays.fill(buf, single); // replicate constant
        EasyIZY.vd_pow(buf, data, buf); // compute constant ^ data
      } // else we have 0^real = 0, buf is memset to 0 at malloc
      ret = SparseMemoryManipulation.createFullSparseMatrixWithNewFillValueInANDNewValuesBasedOnStructureOf(array2, buf, 1);
    } else if (rowsArray2 == 1 && columnsArray2 == 1) { // Sparse array is actually a single number
      final double deref = array2.getData()[0];
      if (deref == 0) { // A.^0 = ones();       
        tmp = new double[rowsArray1 * columnsArray1];
        Arrays.fill(tmp, 1.e0);
        ret = new OGMatrix(tmp, rowsArray1, columnsArray1);
      } else {
        n = array1.getData().length;
        tmp = new double[n];
        EasyIZY.vd_powx(array1.getData(), deref, tmp);
        retRows = rowsArray1;
        retCols = columnsArray1;
        ret = OGTypesMalloc.OGSparseMatrixBasedOnStructureOf(array1, tmp);
      }

    } else { // matrix.^matrix
      Catchers.catchBadCommute(columnsArray1, "Columns in first array", columnsArray2, "Columns in second array");
      Catchers.catchBadCommute(rowsArray1, "Rows in first array", rowsArray2, "Rows in second array");
      retRows = rowsArray1;
      retCols = columnsArray1;

      double[] data1 = array1.getData();
      double[] data2 = array2.getData();
      n = retRows * retCols;
      tmp = new double[n]; 

      // The cases for A.*B
      // 1) Zero entry in B, regardless of A -> 1
      // 2) Zero entry in A, 0 entry in B -> 1
      // 3) Zero entry in A, positive entry in B -> 0
      // 4) Zero entry in A, negative entry in B -> Inf
      // 5) Nonzero entry in A and nonzero entry in B -> A.^B

      Catchers.catchBadCommute(rowsArray1, "rows in first array", rowsArray2, "rows in second array");
      Catchers.catchBadCommute(columnsArray1, "columns in first array", columnsArray2, "columns in second array");
      retRows = rowsArray1;
      retCols = columnsArray1;

      int[] colPtr1 = array1.getColumnPtr();
      int[] colPtr2 = array2.getColumnPtr();
      int[] rowIdx1 = array1.getRowIndex();
      int[] rowIdx2 = array2.getRowIndex();

      int rowFound1 = 0;
      int rowFound2 = 0;
      int ptrd1 = 0;
      int ptrd2 = 0;

      // default condition is cond 1)
      Arrays.fill(tmp, 1);

      for (int ir = 0; ir < retCols; ir++) { // walk in columns
        int i = colPtr1[ir];
        int j = colPtr2[ir];
        rowFound1 = 0;
        rowFound2 = 0;
        while (i < colPtr1[ir + 1] && j < colPtr2[ir + 1]) {
          rowFound1 = rowIdx1[i];
          rowFound2 = rowIdx2[j];
          if (rowFound1 < rowFound2) { // entry exists in stream 1 and not in stream 2
            tmp[rowIdx1[i] + ir * rowsArray2] = 1.e0; //  cond 1)
            ptrd1++;
            i++;
          } else if (rowFound1 > rowFound2) { // entry exists in stream 2, not in stream one cond 2-4)
            // would prefer branch IF DATA2[PTRD2] 10, 20, 30 ;-)
            if (data2[ptrd2] < 0) { // cond 4)
              tmp[rowIdx2[j] + ir * rowsArray2] = Double.POSITIVE_INFINITY;
            } else if (data2[ptrd2] > 0) {
              tmp[rowIdx2[j] + ir * rowsArray2] = 0.; // cond 3)
            } else {
              tmp[rowIdx2[j] + ir * rowsArray2] = 1; // cond 2)
            }
            ptrd2++;
            j++;
          } else { // entry exists in both streams
            tmp[rowIdx1[i] + ir * rowsArray2] = Math.pow(data1[ptrd1], data2[ptrd2]); // cond 5)
            ptrd1++;
            ptrd2++;
            i++;
            j++;
          }
        } // end while

        // clean up as one col has more entries than the other.
        if (i < colPtr1[ir + 1]) {
          for (int k = i; k < colPtr1[ir + 1]; k++) {
            tmp[rowIdx1[k] + ir * rowsArray1] = 1; // cond 2)
            ptrd1++;
          }
        } else {
          for (int k = j; k < colPtr2[ir + 1]; k++) {
            if (data2[ptrd2] < 0) { // cond 4)
              tmp[rowIdx2[k] + ir * rowsArray2] = Double.POSITIVE_INFINITY;
            } else if (data2[ptrd2] > 0) {
              tmp[rowIdx2[k] + ir * rowsArray2] = 0.; // cond 3)
            } else {
              tmp[rowIdx2[k] + ir * rowsArray2] = 1; // cond 2)
            }
            ptrd2++;
          }
        }

      }
      ret = new OGMatrix(tmp, retRows, retCols);
    }
    return ret;
  }
}
