/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;


/**
 * OverLoaded Full
 */
public class Full {
  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, FullAbstract<?>> s_functionPointers = new HashMap<Class<?>, FullAbstract<?>>();
  static {
    s_functionPointers.put(OGDoubleArray.class, FullOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseArray.class, FullOGSparseArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>> OGArraySuper<Number> full(T array1) {
    FullAbstract<T> use = (FullAbstract<T>) s_functionPointers.get(array1.getClass());
    return use.full(array1);
  }
}
