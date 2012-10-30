/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAUtilityFunctions.dot;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.util.tuple.ObjectsPair;
import com.opengamma.util.tuple.Pair;

/**
 * 
 */
public class Dot {
  /**
   * hashmapped function pointers
   */
  private static Map<Pair<?, ?>, DotAbstract<?, ?>> s_functionPointers = new HashMap<Pair<?, ?>, DotAbstract<?, ?>>();
  static {
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGMatrix.class, OGMatrix.class);
//    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGDoubleArray.class, OGSparseArray.class);
//    Pair<Class<?>, Class<?>> pairOGSparseArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGSparseArray.class, OGDoubleArray.class);
//    Pair<Class<?>, Class<?>> pairOGSparseArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGSparseArray.class, OGSparseArray.class);    
    s_functionPointers.put(pairOGDoubleArrayOGDoubleArray, DotOGMatrixOGMatrix.getInstance());
//    s_functionPointers.put(pairOGDoubleArrayOGSparseArray, DotOGDoubleArrayOGSparseArray.getInstance());
//    s_functionPointers.put(pairOGSparseArrayOGDoubleArray, DotOGSparseArrayOGDoubleArray.getInstance());         
//    s_functionPointers.put(pairOGSparseArrayOGSparseArray, DotOGSparseArrayOGSparseArray.getInstance());        
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>, S extends OGArray<? extends Number>> OGArray<? extends Number> dot(T array1, S array2) {
    if (array1 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 1");
    }
    if (array2 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 2");
    }    
    ObjectsPair<Class<?>, Class<?>> combo = new ObjectsPair<Class<?>, Class<?>>(array1.getClass(), array2.getClass());
    DotAbstract<T, S> use = (DotAbstract<T, S>) s_functionPointers.get(combo);
    
    if (use == null) {
      throw new MathsExceptionNotImplemented("Dot array class " + array1.getClass().toString() + " and " + array2.getClass().toString() + " is not yet implemented");
    }
    return use.dot(array1, array2);
  }
}
