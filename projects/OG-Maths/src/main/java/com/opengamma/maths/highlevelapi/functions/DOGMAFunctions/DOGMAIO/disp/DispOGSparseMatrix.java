/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAIO.disp;

import java.util.Formatter;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unaryvoid.Disp;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Displays OGSparseMatrix matrices
 */
@DOGMAMethodHook(provides = Disp.class)
public class DispOGSparseMatrix implements Disp<OGSparseMatrix> {

  @Override
  public void eval(OGSparseMatrix array1) {
    Catchers.catchNull(array1);
    StringBuffer sb = new StringBuffer();
    Formatter formatter = new Formatter(sb);
    int cols = array1.getNumberOfColumns();
    int rows = array1.getNumberOfRows();
    int[] colPtr = array1.getColumnPtr();
    int[] rowIdx = array1.getRowIndex();
    double[] values = array1.getData();
    double nnz = 100 * values.length / (rows * cols);
    sb.append("\nOGSparseMatrix:\n");
    formatter.format("[nnz density = %2.0f%%. rows = %d, columns = %d]\n", nnz, rows, cols);
    for (int ir = 0; ir < cols; ir++) {
      for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
        sb.append("(");
        sb.append(rowIdx[i]);
        sb.append(",");
        sb.append(ir);
        sb.append(") = ");
        formatter.format("%24.18f", values[i]);
        sb.append("\n");
      }
    }

    System.out.println(sb.toString());

  }

}
