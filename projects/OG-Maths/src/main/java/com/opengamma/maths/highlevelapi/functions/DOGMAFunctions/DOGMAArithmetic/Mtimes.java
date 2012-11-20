/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes.MtimesAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes.MtimesOGMatrixOGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes.MtimesOGMatrixOGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes.MtimesOGSparseMatrixOGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes.MtimesOGSparseMatrixOGSparseMatrix;
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
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGMatrix.class, OGMatrix.class);
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGMatrix.class, OGSparseMatrix.class);
    Pair<Class<?>, Class<?>> pairOGSparseArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGSparseMatrix.class, OGMatrix.class);
    Pair<Class<?>, Class<?>> pairOGSparseArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGSparseMatrix.class, OGSparseMatrix.class);
    s_functionPointers.put(pairOGDoubleArrayOGSparseArray, MtimesOGMatrixOGSparseMatrix.getInstance());
    s_functionPointers.put(pairOGSparseArrayOGDoubleArray, MtimesOGSparseMatrixOGMatrix.getInstance());
    s_functionPointers.put(pairOGSparseArrayOGSparseArray, MtimesOGSparseMatrixOGSparseMatrix.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>, S extends OGArray<? extends Number>> OGArray<? extends Number> mtimes(T array1, S array2) {
    if (array1 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 1");
    }
    if (array2 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 2");
    }
    OGArray<? extends Number> ret = null;
    Pair<Class<?>, Class<?>> combo = Pair.<Class<?>, Class<?>>of(array1.getClass(), array2.getClass());
    MtimesAbstract<T, S> use = (MtimesAbstract<T, S>) s_functionPointers.get(combo);
    if (use == null) {
      OGArray<? extends Number> array1casted = GenericUpcastMapHolder.getInstance().upcast(array1);
      OGArray<? extends Number> array2casted = GenericUpcastMapHolder.getInstance().upcast(array2);
      combo = Pair.<Class<?>, Class<?>>of(array1casted.getClass(), array2casted.getClass());
      use = (MtimesAbstract<T, S>) s_functionPointers.get(combo);
      ret = mtimes(array1casted, array2casted); // call back with things we now know about
    } else {
      ret = use.mtimes(array1, array2);
    }
    return ret;
  }
}
