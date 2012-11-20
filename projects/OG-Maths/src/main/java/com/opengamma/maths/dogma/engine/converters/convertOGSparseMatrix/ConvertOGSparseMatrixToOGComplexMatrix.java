/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters.convertOGSparseMatrix;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Converts from an OGSparseMatrix type to an OGMatrix type
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertOGSparseMatrixToOGComplexMatrix implements Converter<OGSparseMatrix> {

  @Override
  public OGArray<? extends Number> convert(OGSparseMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] data = array1.getData();
    double[] tmp = new double[2 * rows * cols];
    for (int ir = 0; ir < cols; ir++) {
      for (int i = colPtr[ir]; i < colPtr[ir + 1]; i++) {
        tmp[2 * (rowIdx[i] + ir * rows)] = data[i];
      }
    }
    return new OGComplexMatrix(tmp, rows, cols);
  }
}
