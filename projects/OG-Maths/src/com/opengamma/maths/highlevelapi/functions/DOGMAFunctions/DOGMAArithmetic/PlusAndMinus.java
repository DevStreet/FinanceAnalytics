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
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
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
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGMatrix.class, OGMatrix.class);
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGMatrix.class, OGSparseMatrix.class);
    Pair<Class<?>, Class<?>> pairOGSparseArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGSparseMatrix.class, OGMatrix.class);
    Pair<Class<?>, Class<?>> pairOGSparseArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGSparseMatrix.class, OGSparseMatrix.class);
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGDiagonalArray = Pair.<Class<?>, Class<?>>of(OGMatrix.class, OGDiagonalMatrix.class);
    Pair<Class<?>, Class<?>> pairOGDiagonalArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGDiagonalMatrix.class, OGMatrix.class);
    s_functionPointers.put(pairOGDoubleArrayOGDoubleArray, PlusOGDoubleArrayOGDoubleArray.getInstance());
    s_functionPointers.put(pairOGDoubleArrayOGSparseArray, PlusOGDoubleArrayOGSparseArray.getInstance());
    s_functionPointers.put(pairOGSparseArrayOGDoubleArray, PlusOGSparseArrayOGDoubleArray.getInstance());
    s_functionPointers.put(pairOGSparseArrayOGSparseArray, PlusOGSparseArrayOGSparseArray.getInstance());
    s_functionPointers.put(pairOGDoubleArrayOGDiagonalArray, PlusOGDoubleArrayOGDiagonalArray.getInstance());
    s_functionPointers.put(pairOGDiagonalArrayOGDoubleArray, PlusOGDiagonalArrayOGDoubleArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>, S extends OGArray<? extends Number>> OGArray<? extends Number> plus(T array1, S array2) {
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
  public <T extends OGArray<? extends Number>, S extends OGArray<? extends Number>> OGArray<? extends Number> minus(T array1, S array2) {
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
