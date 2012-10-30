/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.sparse;

import java.util.Map;

import com.google.common.collect.Maps;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * OverLoaded Sparse
 */
public class Sparse {
  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, SparseAbstract<? extends OGArray<? extends Number>>> s_functionPointers = Maps.newHashMap();
  static {
    s_functionPointers.put(OGMatrix.class, SparseOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseMatrix.class, SparseOGSparseArray.getInstance());
    s_functionPointers.put(OGDiagonalMatrix.class, SparseOGDiagonalArray.getInstance());
//    s_functionPointers.put(OGPermutationArray.class, SparseOGPermutationArray.getInstance());
//    s_functionPointers.put(OGComplexArray.class, SparseOGComplexArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>> OGArray<? extends Number> sparse(T array1) {
    SparseAbstract<T> use = (SparseAbstract<T>) s_functionPointers.get(array1.getClass());
    return use.sparse(array1);
  }
}
