/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;

/**
 * Element wise multiply 
 */
public class TimesOGDoubleArrayOGDoubleArray extends TimesAbstract<OGDoubleArray, OGDoubleArray> {
  private static TimesOGDoubleArrayOGDoubleArray s_instance = new TimesOGDoubleArrayOGDoubleArray();

  public static TimesOGDoubleArrayOGDoubleArray getInstance() {
    return s_instance;
  }

  private TimesOGDoubleArrayOGDoubleArray() {
  }

  @Override
  public <U> OGArraySuper<U> times(OGDoubleArray array1, OGDoubleArray array2) {
    return null;
  }

}
