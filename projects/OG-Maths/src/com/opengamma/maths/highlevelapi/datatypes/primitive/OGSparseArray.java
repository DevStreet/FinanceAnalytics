/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.primitive;

/**
 * 
 */
public class OGSparseArray extends OGArraySuper<Double> {

  @Override
  public int getNumberOfRows() {
    return 0;
  }

  @Override
  public int getNumberOfColumns() {
    return 0;
  }

  @Override
  public Double getEntry(int... indices) {
    return null;
  }

}
