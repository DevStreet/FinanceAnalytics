/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

/**
 * 
 */
public class OGDiagonalArray extends OGArraySuper<Integer> {

  public int getNumberOfRows() {
    return 0;
  }

  public int getNumberOfColumns() {
    return 0;
  }

  @Override
  public Integer getEntry(int... indices) {
    return null;
  }

}
