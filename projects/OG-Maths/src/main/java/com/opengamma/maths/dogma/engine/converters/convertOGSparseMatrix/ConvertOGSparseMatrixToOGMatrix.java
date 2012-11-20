/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters.convertOGSparseMatrix;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full.FullOGSparseMatrix;

/**
 * Converts from an OGSparseMatrix type to an OGMatrix type
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertOGSparseMatrixToOGMatrix implements Converter<OGSparseMatrix> {
  private static FullOGSparseMatrix s_full = FullOGSparseMatrix.getInstance();
  
  @Override
  public OGArray<? extends Number> convert(OGSparseMatrix array1) {
    return s_full.full(array1);
  }
}
