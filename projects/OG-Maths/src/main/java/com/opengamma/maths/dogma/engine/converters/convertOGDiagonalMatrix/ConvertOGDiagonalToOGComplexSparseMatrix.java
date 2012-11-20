/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters.convertOGDiagonalMatrix;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * 
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertOGDiagonalToOGComplexSparseMatrix implements Converter<OGDiagonalMatrix> {

  @Override
  public OGArray<? extends Number> convert(OGDiagonalMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();

    final double[] tmpData = DenseMemoryManipulation.convertSinglePointerToZeroInterleavedSinglePointer(data);

    int[] tmpColPtr = new int[cols + 1];
    for (int i = 0; i < data.length; i++) {
      tmpColPtr[i] = i;
    }
    for (int i = data.length; i < cols + 1; i++) {
      tmpColPtr[i] = data.length;
    }

    int[] tmpRowIdx = new int[data.length];
    for (int i = 0; i < data.length; i++) {
      tmpRowIdx[i] = i;
    }

    return new OGComplexSparseMatrix(tmpColPtr, tmpRowIdx, tmpData, rows, cols);
  }
}
