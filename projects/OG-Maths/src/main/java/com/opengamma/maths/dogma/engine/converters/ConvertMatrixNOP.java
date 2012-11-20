/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters;

import com.opengamma.maths.dogma.engine.DOGMAConverterHook;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * 
 */
@DOGMAConverterHook(provides = Converter.class)
public final class ConvertMatrixNOP implements Converter<OGArray<? extends Number>> {
  @Override
  public OGArray<? extends Number> convert(OGArray<? extends Number> array1) {
    return array1;
  }
}
