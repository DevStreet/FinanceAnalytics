/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMARearrangingMatrices.copy;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.highlevelapi.datatypes.derived.OGArrayTypeToFunctionMap;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;

/**
 * Overloaded Copy
 */
public class Copy {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, CopyAbstract<?>> s_functionPointers = new HashMap<Class<?>, CopyAbstract<?>>();
  static {
    s_functionPointers.put(OGDoubleArray.class, CopyOGDoubleArray.getInstance());
  }

  private static OGArrayTypeToFunctionMap<CopyAbstract<?>> s_ptr = new OGArrayTypeToFunctionMap<CopyAbstract<?>>();
  {
    Map<Class<?>, CopyAbstract<?>> theMap = s_ptr.getMap();
    theMap.put(OGDoubleArray.class, CopyOGDoubleArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>> OGArraySuper<Number> copy(T array1) {
    CopyAbstract<T> use = (CopyAbstract<T>) s_functionPointers.get(array1.getClass());
    return use.copy(array1);
  }

}
