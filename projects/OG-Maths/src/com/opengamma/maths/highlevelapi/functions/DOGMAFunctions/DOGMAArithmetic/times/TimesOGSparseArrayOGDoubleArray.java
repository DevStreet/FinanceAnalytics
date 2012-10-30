/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Does elementwise OGSparse * OGDouble
 */
public final class TimesOGSparseArrayOGDoubleArray implements TimesAbstract<OGSparseMatrix, OGMatrix> {
  private static TimesOGSparseArrayOGDoubleArray s_instance = new TimesOGSparseArrayOGDoubleArray();

  public static TimesOGSparseArrayOGDoubleArray getInstance() {
    return s_instance;
  }

  private TimesOGSparseArrayOGDoubleArray() {
  }

  
  private static TimesOGDoubleArrayOGSparseArray s_reverse = TimesOGDoubleArrayOGSparseArray.getInstance();
  
  
  @Override
  public OGArray<? extends Number> times(OGSparseMatrix array1, OGMatrix array2) {
    return s_reverse.times(array2, array1);
  }

}
