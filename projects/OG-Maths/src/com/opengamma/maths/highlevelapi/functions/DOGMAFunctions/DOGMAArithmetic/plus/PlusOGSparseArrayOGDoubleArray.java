/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;

/**
 * Adds OGDoubleArrays to OGSparseArrays   
 */
public final class PlusOGSparseArrayOGDoubleArray implements PlusMinusAbstract<OGSparseArray, OGDoubleArray> {
  private static PlusOGSparseArrayOGDoubleArray s_instance = new PlusOGSparseArrayOGDoubleArray();

  public static PlusOGSparseArrayOGDoubleArray getInstance() {
    return s_instance;
  }

  private PlusOGSparseArrayOGDoubleArray() {
  }

  private static PlusOGDoubleArrayOGSparseArray s_reverse = PlusOGDoubleArrayOGSparseArray.getInstance();
  
  @Override
  public OGArraySuper<? extends Number> plusminus(OGSparseArray array1, OGDoubleArray array2, final int op) {
    return s_reverse.plusminus(array2, array1, op);
  }

}
