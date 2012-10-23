/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;

/**
 * Adds {@link OGDiagonalArray} to {@link OGDoubleArray} 
 */
public final class PlusOGDiagonalArrayOGDoubleArray extends PlusMinusAbstract<OGDiagonalArray, OGDoubleArray> {
  private static PlusOGDiagonalArrayOGDoubleArray s_instance = new PlusOGDiagonalArrayOGDoubleArray();

  public static PlusOGDiagonalArrayOGDoubleArray getInstance() {
    return s_instance;
  }

  private PlusOGDiagonalArrayOGDoubleArray() {
  }

  private static PlusOGDoubleArrayOGDiagonalArray s_reverse = PlusOGDoubleArrayOGDiagonalArray.getInstance();

  @SuppressWarnings("unchecked")
  @Override
  public OGArraySuper<Number> plusminus(OGDiagonalArray array1, OGDoubleArray array2, int op) {
    return s_reverse.plusminus(array2, array1, op);
  }

}
