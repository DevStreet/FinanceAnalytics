/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters.convertOGDiagonalMatrix;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full.FullOGDiagonalMatrix;

/**
 * 
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertOGDiagonalToOGMatrix implements Converter<OGDiagonalMatrix> {
  private static FullOGDiagonalMatrix s_full = FullOGDiagonalMatrix.getInstance();
  @Override
  public OGArray<? extends Number> convert(OGDiagonalMatrix array1) {
    return s_full.full(array1);
  }
}
