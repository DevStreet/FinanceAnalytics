/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;

/**
 * Does elementwise OGSparse * OGDouble
 */
public final class TimesOGSparseArrayOGDoubleArray implements TimesAbstract<OGSparseArray, OGDoubleArray> {
  private static TimesOGSparseArrayOGDoubleArray s_instance = new TimesOGSparseArrayOGDoubleArray();

  public static TimesOGSparseArrayOGDoubleArray getInstance() {
    return s_instance;
  }

  private TimesOGSparseArrayOGDoubleArray() {
  }

  
  private static TimesOGDoubleArrayOGSparseArray s_reverse = TimesOGDoubleArrayOGSparseArray.getInstance();
  
  
  @Override
  public OGArraySuper<? extends Number> times(OGSparseArray array1, OGDoubleArray array2) {
    return s_reverse.times(array2, array1);
  }

}
