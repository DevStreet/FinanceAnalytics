/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Adds OGDoubleArrays to OGSparseArrays   
 */
public final class PlusOGSparseArrayOGDoubleArray implements PlusMinusAbstract<OGSparseMatrix, OGMatrix> {
  private static PlusOGSparseArrayOGDoubleArray s_instance = new PlusOGSparseArrayOGDoubleArray();

  public static PlusOGSparseArrayOGDoubleArray getInstance() {
    return s_instance;
  }

  private PlusOGSparseArrayOGDoubleArray() {
  }

  private static PlusOGDoubleArrayOGSparseArray s_reverse = PlusOGDoubleArrayOGSparseArray.getInstance();
  
  @Override
  public OGArray<? extends Number> plusminus(OGSparseMatrix array1, OGMatrix array2, final int op) {
    return s_reverse.plusminus(array2, array1, op);
  }

}
