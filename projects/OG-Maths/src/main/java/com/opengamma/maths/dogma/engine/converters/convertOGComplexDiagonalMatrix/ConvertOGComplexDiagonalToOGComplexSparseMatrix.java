/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters.convertOGComplexDiagonalMatrix;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;

/**
 * 
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertOGComplexDiagonalToOGComplexSparseMatrix implements Converter<OGDiagonalMatrix> {

  @Override
  public OGArray<? extends Number> convert(OGDiagonalMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();

    final int len = data.length;
    final int halflen = len / 2;
    final double[] tmpData = new double[len];
    System.arraycopy(data, 0, tmpData, 0, len);

    int[] tmpColPtr = new int[cols + 1];
    for (int i = 0; i < halflen; i++) {
      tmpColPtr[i] = i;
    }
    for (int i = halflen; i < cols + 1; i++) {
      tmpColPtr[i] = halflen;
    }

    int[] tmpRowIdx = new int[halflen];
    for (int i = 0; i < halflen; i++) {
      tmpRowIdx[i] = i;
    }

    return new OGComplexSparseMatrix(tmpColPtr, tmpRowIdx, tmpData, rows, cols);
  }
}
