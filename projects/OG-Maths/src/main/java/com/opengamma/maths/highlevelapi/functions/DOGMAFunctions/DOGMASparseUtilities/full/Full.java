/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASparseUtilities.full;

import java.util.Map;

import com.google.common.collect.Maps;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;


/**
 * OverLoaded Full
 */
public class Full {
  /**
   * hashmapped function pointers
   */
  private static Map<Class<? extends OGArray<? extends Number>>, FullAbstract<? extends OGArray<? extends Number>>> s_functionPointers = Maps.newHashMap();
  static {
    s_functionPointers.put(OGMatrix.class, FullOGMatrix.getInstance());
    s_functionPointers.put(OGSparseMatrix.class, FullOGSparseMatrix.getInstance());
    s_functionPointers.put(OGDiagonalMatrix.class, FullOGDiagonalMatrix.getInstance());
    s_functionPointers.put(OGPermutationMatrix.class, FullOGPermutationMatrix.getInstance());
    s_functionPointers.put(OGComplexMatrix.class, FullOGComplexMatrix.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<U>, U extends Number> OGArray<? extends Number> full(T array1) {
    FullAbstract<T> use = (FullAbstract<T>) s_functionPointers.get(array1.getClass());
    return use.full(array1);
  }
}
