/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters.convertOGDiagonalMatrix;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.highlevelapi.datatypes.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMASparseUtilities.sparse.SparseOGDiagonalMatrix;

/**
 * 
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertOGDiagonalToOGSparseMatrix implements Converter<OGDiagonalMatrix> {
  private static SparseOGDiagonalMatrix s_sparse = new SparseOGDiagonalMatrix();

  @Override
  public OGArray<? extends Number> convert(OGDiagonalMatrix array1) {
    return s_sparse.eval(array1);
  }
}
