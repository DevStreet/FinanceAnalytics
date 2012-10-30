/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import java.util.Map;

import com.google.common.collect.Maps;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;


/**
 * OverLoaded Full
 */
public class Full {
  /**
   * hashmapped function pointers
   */
  private static Map<Class<? extends OGArraySuper<? extends Number>>, FullAbstract<? extends OGArraySuper<? extends Number>>> s_functionPointers = Maps.newHashMap();
  static {
    s_functionPointers.put(OGDoubleArray.class, FullOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseArray.class, FullOGSparseArray.getInstance());
    s_functionPointers.put(OGDiagonalArray.class, FullOGDiagonalArray.getInstance());
    s_functionPointers.put(OGPermutationArray.class, FullOGPermutationArray.getInstance());
    s_functionPointers.put(OGComplexArray.class, FullOGComplexArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<U>, U extends Number> OGArraySuper<? extends Number> full(T array1) {
    FullAbstract<T> use = (FullAbstract<T>) s_functionPointers.get(array1.getClass());
    return use.full(array1);
  }
}
