/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters.convertOGDiagonalMatrix;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;

/**
 * 
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertOGDiagonalToOGComplexDiagonalMatrix implements Converter<OGDiagonalMatrix> {
  @Override
  public OGArray<? extends Number> convert(OGDiagonalMatrix array1) {
    return new OGComplexDiagonalMatrix(array1.getData(), array1.getNumberOfRows(), array1.getNumberOfColumns());
  }
}
