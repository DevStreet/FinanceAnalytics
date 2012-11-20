/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters.convertOGComplexDiagonalMatrix;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;

/**
 * 
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertOGComplexDiagonalToOGComplexMatrix implements Converter<OGComplexDiagonalMatrix> {
  @Override
  public OGArray<? extends Number> convert(OGComplexDiagonalMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();
    double[] tmp = new double[2 * rows * cols];
    for (int i = 0; i < data.length; i += 2) {
      tmp[(i * rows + i)] = data[i];
      tmp[(i * rows + i) + 1] = data[i + 1];
    }
    return new OGComplexMatrix(tmp, rows, cols);
  }

}
