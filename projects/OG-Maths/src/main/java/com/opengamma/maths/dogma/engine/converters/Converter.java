/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.converters;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * Interface for OGArray type -> OGArray type converters
 * @param <T> an OGArray type 
 */
public interface Converter<T extends OGArray<? extends Number>> {
  OGArray<? extends Number> convert(T array1);
}
