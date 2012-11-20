/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters.convertOGSparseMatrix;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.memory.DenseMemoryManipulation;

/**
 * Converts from an OGSparseMatrix type to an OGMatrix type
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertOGSparseMatrixToOGComplexSparseMatrix implements Converter<OGSparseMatrix> {

  @Override
  public OGArray<? extends Number> convert(OGSparseMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final int[] colPtr = array1.getColumnPtr();
    final int[] rowIdx = array1.getRowIndex();
    final double[] data = array1.getData();
    double[] tmp = DenseMemoryManipulation.convertSinglePointerToZeroInterleavedSinglePointer(data);
    return new OGComplexSparseMatrix(colPtr, rowIdx, tmp, rows, cols);
  }

}
