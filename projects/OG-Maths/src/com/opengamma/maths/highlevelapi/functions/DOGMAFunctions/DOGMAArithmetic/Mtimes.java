/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes.MtimesAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes.MtimesOGDoubleArrayOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes.MtimesOGDoubleArrayOGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes.MtimesOGSparseArrayOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes.MtimesOGSparseArrayOGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAHelpers.genericconverters.GenericUpcastMapHolder;
import com.opengamma.util.tuple.Pair;

/**
 * Full multiplication
 */
public class Mtimes {
  /**
   * hashmapped function pointers
   */
  private static Map<Pair<?, ?>, MtimesAbstract<?, ?>> s_functionPointers = new HashMap<Pair<?, ?>, MtimesAbstract<?, ?>>();
  static {
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGDoubleArray.class, OGDoubleArray.class);
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGDoubleArray.class, OGSparseArray.class);
    Pair<Class<?>, Class<?>> pairOGSparseArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGSparseArray.class, OGDoubleArray.class);
    Pair<Class<?>, Class<?>> pairOGSparseArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGSparseArray.class, OGSparseArray.class);
    s_functionPointers.put(pairOGDoubleArrayOGDoubleArray, MtimesOGDoubleArrayOGDoubleArray.getInstance());
    s_functionPointers.put(pairOGDoubleArrayOGSparseArray, MtimesOGDoubleArrayOGSparseArray.getInstance());
    s_functionPointers.put(pairOGSparseArrayOGDoubleArray, MtimesOGSparseArrayOGDoubleArray.getInstance());
    s_functionPointers.put(pairOGSparseArrayOGSparseArray, MtimesOGSparseArrayOGSparseArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<? extends Number>, S extends OGArraySuper<? extends Number>> OGArraySuper<? extends Number> mtimes(T array1, S array2) {
    if (array1 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 1");
    }
    if (array2 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 2");
    }
    OGArraySuper<? extends Number> ret = null;
    Pair<Class<?>, Class<?>> combo = Pair.<Class<?>, Class<?>>of(array1.getClass(), array2.getClass());
    MtimesAbstract<T, S> use = (MtimesAbstract<T, S>) s_functionPointers.get(combo);
    if (use == null) {
      OGArraySuper<? extends Number> array1casted = GenericUpcastMapHolder.getInstance().upcast(array1);
      OGArraySuper<? extends Number> array2casted = GenericUpcastMapHolder.getInstance().upcast(array2);
      combo = Pair.<Class<?>, Class<?>>of(array1casted.getClass(), array2casted.getClass());
      use = (MtimesAbstract<T, S>) s_functionPointers.get(combo);
      ret = mtimes(array1casted, array2casted); // call back with things we now know about
    } else {
      ret = use.mtimes(array1, array2);
    }
    return ret;
  }
}
