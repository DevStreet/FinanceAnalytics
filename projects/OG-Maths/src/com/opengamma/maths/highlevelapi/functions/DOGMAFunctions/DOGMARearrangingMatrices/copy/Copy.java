/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Overloaded Copy
 */
public class Copy {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, CopyAbstract<?>> s_functionPointers = new HashMap<Class<?>, CopyAbstract<?>>();
  static {
    s_functionPointers.put(OGMatrix.class, CopyOGMatrix.getInstance());
    s_functionPointers.put(OGSparseMatrix.class, CopyOGSparseMatrix.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>> OGArray<? extends Number> copy(T array1) {
    CopyAbstract<T> use = (CopyAbstract<T>) s_functionPointers.get(array1.getClass());
    return use.copy(array1);
  }

}

