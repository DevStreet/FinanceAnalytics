/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic;

import java.util.Map;

import com.google.common.collect.Maps;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times.TimesAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times.TimesOGDoubleArrayOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times.TimesOGDoubleArrayOGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times.TimesOGSparseArrayOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.times.TimesOGSparseArrayOGSparseArray;
import com.opengamma.util.tuple.ObjectsPair;
import com.opengamma.util.tuple.Pair;

/**
 * Element wise multiplication
 */
public class Times {
  /**
   * hashmapped function pointers
   */
  private static Map<Pair<?, ?>, TimesAbstract<?, ?>> s_functionPointers = Maps.newHashMap();
  static {
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGMatrix.class, OGMatrix.class);
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGMatrix.class, OGSparseMatrix.class);
    Pair<Class<?>, Class<?>> pairOGSparseArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGSparseMatrix.class, OGMatrix.class);
    Pair<Class<?>, Class<?>> pairOGSparseArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGSparseMatrix.class, OGSparseMatrix.class);    
    s_functionPointers.put(pairOGDoubleArrayOGDoubleArray, TimesOGDoubleArrayOGDoubleArray.getInstance());
    s_functionPointers.put(pairOGDoubleArrayOGSparseArray, TimesOGDoubleArrayOGSparseArray.getInstance());
    s_functionPointers.put(pairOGSparseArrayOGDoubleArray, TimesOGSparseArrayOGDoubleArray.getInstance());         
    s_functionPointers.put(pairOGSparseArrayOGSparseArray, TimesOGSparseArrayOGSparseArray.getInstance());        
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>, S extends OGArray<? extends Number>> OGArray<? extends Number> times(T array1, S array2) {
    if (array1 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 1");
    }
    if (array2 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 2");
    }    
    ObjectsPair<Class<?>, Class<?>> combo = new ObjectsPair<Class<?>, Class<?>>(array1.getClass(), array2.getClass());
    TimesAbstract<T, S> use = (TimesAbstract<T, S>) s_functionPointers.get(combo);
    
    if (use == null) {
      throw new MathsExceptionNotImplemented("Times array class " + array1.getClass().toString() + " and " + array2.getClass().toString() + " is not yet implemented");
    }
    return use.times(array1, array2);
  }

}
