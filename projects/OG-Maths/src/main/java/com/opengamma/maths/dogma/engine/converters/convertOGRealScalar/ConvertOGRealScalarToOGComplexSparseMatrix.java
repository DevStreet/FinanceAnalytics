/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters.convertOGRealScalar;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexSparseMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGRealScalar;

/**
 * 
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertOGRealScalarToOGComplexSparseMatrix implements Converter<OGRealScalar> {
  @Override
  public OGArray<? extends Number> convert(OGRealScalar array1) {
    return new OGComplexSparseMatrix(new double[][] {{array1.getData()[0]}});
  }
}
