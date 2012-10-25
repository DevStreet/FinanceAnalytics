/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;

/**
 * OverLoaded Sparse
 */
public class Sparse {
  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, SparseAbstract<?>> s_functionPointers = new HashMap<Class<?>, SparseAbstract<?>>();
  static {
    s_functionPointers.put(OGDoubleArray.class, SparseOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseArray.class, SparseOGSparseArray.getInstance());
    s_functionPointers.put(OGDiagonalArray.class, SparseOGDiagonalArray.getInstance());
//    s_functionPointers.put(OGPermutationArray.class, SparseOGPermutationArray.getInstance());
//    s_functionPointers.put(OGComplexArray.class, SparseOGComplexArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>> OGArraySuper<Number> full(T array1) {
    SparseAbstract<T> use = (SparseAbstract<T>) s_functionPointers.get(array1.getClass());
    return use.sparse(array1);
  }
}
