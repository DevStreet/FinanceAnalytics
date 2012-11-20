/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters.convertOGDiagonalMatrix;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;

/**
 * 
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertOGDiagonalToOGComplexMatrix implements Converter<OGDiagonalMatrix> {
  @Override
  public OGArray<? extends Number> convert(OGDiagonalMatrix array1) {
    final int rows = array1.getNumberOfRows();
    final int cols = array1.getNumberOfColumns();
    final double[] data = array1.getData();
    double[] tmp = new double[2 * rows * cols];
    for (int i = 0; i < data.length; i++) {
      tmp[2 * (i * rows + i)] = data[i];
    }
    return new OGComplexMatrix(tmp, rows, cols);
  }
}
