/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

import com.opengamma.maths.commonapi.numbers.ComplexType;

/**
 * 
 */
public class OGComplexArray extends OGArraySuper<ComplexType> {

  @Override
  public int getNumberOfRows() {
    return 0;
  }

  @Override
  public int getNumberOfColumns() {
    return 0;
  }

  @Override
  public ComplexType getEntry(int... indices) {
    return null;
  }

}
