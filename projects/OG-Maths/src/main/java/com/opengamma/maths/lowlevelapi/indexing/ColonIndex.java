/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.indexing;

/**
 * The colon index type
 */
public class ColonIndex implements IndexItem {
  private static ColonIndex s_instance;
  static {
    s_instance = new ColonIndex();
  }

  /**
   * Get the instance
   * @return the colon index instance
   */
  public static ColonIndex getInstance() {
    return s_instance;
  }

  @Override
  public int[] linearise(int leadingDimension) {
    return new LinearIndex(0, leadingDimension).expand().getValues();
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return 31;
  };

  @Override
  public String toString() {
    return ":";
  };

}
