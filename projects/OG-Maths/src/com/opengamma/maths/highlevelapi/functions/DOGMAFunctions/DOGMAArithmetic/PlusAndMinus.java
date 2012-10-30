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
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus.PlusMinusAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus.PlusOGDiagonalArrayOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus.PlusOGDoubleArrayOGDiagonalArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus.PlusOGDoubleArrayOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus.PlusOGDoubleArrayOGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus.PlusOGSparseArrayOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus.PlusOGSparseArrayOGSparseArray;
import com.opengamma.util.tuple.ObjectsPair;
import com.opengamma.util.tuple.Pair;

/**
 * Overloaded Plus
 */
public class PlusAndMinus {

  /**
   * hashmapped function pointers
   */
  private static Map<Pair<Class<?>, Class<?>>, PlusMinusAbstract<?, ?>> s_functionPointers = Maps.newHashMap();
  static {
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGDoubleArray.class, OGDoubleArray.class);
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGDoubleArray.class, OGSparseArray.class);
    Pair<Class<?>, Class<?>> pairOGSparseArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGSparseArray.class, OGDoubleArray.class);
    Pair<Class<?>, Class<?>> pairOGSparseArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGSparseArray.class, OGSparseArray.class);
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGDiagonalArray = Pair.<Class<?>, Class<?>>of(OGDoubleArray.class, OGDiagonalArray.class);
    Pair<Class<?>, Class<?>> pairOGDiagonalArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGDiagonalArray.class, OGDoubleArray.class);
    s_functionPointers.put(pairOGDoubleArrayOGDoubleArray, PlusOGDoubleArrayOGDoubleArray.getInstance());
    s_functionPointers.put(pairOGDoubleArrayOGSparseArray, PlusOGDoubleArrayOGSparseArray.getInstance());
    s_functionPointers.put(pairOGSparseArrayOGDoubleArray, PlusOGSparseArrayOGDoubleArray.getInstance());
    s_functionPointers.put(pairOGSparseArrayOGSparseArray, PlusOGSparseArrayOGSparseArray.getInstance());
    s_functionPointers.put(pairOGDoubleArrayOGDiagonalArray, PlusOGDoubleArrayOGDiagonalArray.getInstance());
    s_functionPointers.put(pairOGDiagonalArrayOGDoubleArray, PlusOGDiagonalArrayOGDoubleArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<? extends Number>, S extends OGArraySuper<? extends Number>> OGArraySuper<? extends Number> plus(T array1, S array2) {
    if (array1 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 1");
    }
    if (array2 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 2");
    }
    Pair<Class<?>, Class<?>> combo = Pair.<Class<?>, Class<?>>of(array1.getClass(), array2.getClass());
    PlusMinusAbstract<T, S> use = (PlusMinusAbstract<T, S>) s_functionPointers.get(combo);
    if (use == null) {
      throw new MathsExceptionNotImplemented("Adding array class " + array1.getClass().toString() + " to " + array2.getClass().toString() + " is not yet implemented");
    }
    return use.plusminus(array1, array2, 1);
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<? extends Number>, S extends OGArraySuper<? extends Number>> OGArraySuper<? extends Number> minus(T array1, S array2) {
    if (array1 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 1");
    }
    if (array2 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 2");
    }
    ObjectsPair<Class<?>, Class<?>> combo = new ObjectsPair<Class<?>, Class<?>>(array1.getClass(), array2.getClass());
    PlusMinusAbstract<T, S> use = (PlusMinusAbstract<T, S>) s_functionPointers.get(combo);
    if (use == null) {
      throw new MathsExceptionNotImplemented("Subtracting array class " + array1.getClass().toString() + " from " + array2.getClass().toString() + " is not yet implemented");
    }
    return use.plusminus(array1, array2, -1);
  }

}
