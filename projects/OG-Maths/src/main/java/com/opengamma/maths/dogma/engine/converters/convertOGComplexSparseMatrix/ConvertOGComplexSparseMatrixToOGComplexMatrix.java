/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters.convertOGComplexSparseMatrix;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full.FullOGComplexSparseMatrix;

/**
 * Converts from an OGComplexSparseMatrix type to an OGComplexMatrix type
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertOGComplexSparseMatrixToOGComplexMatrix implements Converter<OGComplexSparseMatrix> {
  private static FullOGComplexSparseMatrix s_full = new FullOGComplexSparseMatrix();

  @Override
  public OGArray<? extends Number> convert(OGComplexSparseMatrix array1) {
    return s_full.eval(array1);
  }

}
