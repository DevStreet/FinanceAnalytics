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
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.rdivide.RdivideAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.rdivide.RdivideOGMatrixOGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.rdivide.RdivideOGMatrixOGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.rdivide.RdivideOGSparseMatrixOGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.rdivide.RdivideOGSparseMatrixOGSparseMatrix;
import com.opengamma.util.tuple.Pair;

/**
 * Does rdivide
 */
public class Rdivide {
  /**
  * hashmapped function pointers
  */
  private static Map<Pair<?, ?>, RdivideAbstract<?, ?>> s_functionPointers = Maps.newHashMap();
  static {
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGMatrix.class, OGMatrix.class);
    Pair<Class<?>, Class<?>> pairOGDoubleArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGMatrix.class, OGSparseMatrix.class);
    Pair<Class<?>, Class<?>> pairOGSparseArrayOGDoubleArray = Pair.<Class<?>, Class<?>>of(OGSparseMatrix.class, OGMatrix.class);
    Pair<Class<?>, Class<?>> pairOGSparseArrayOGSparseArray = Pair.<Class<?>, Class<?>>of(OGSparseMatrix.class, OGSparseMatrix.class);
    s_functionPointers.put(pairOGDoubleArrayOGDoubleArray, RdivideOGMatrixOGMatrix.getInstance());
    s_functionPointers.put(pairOGDoubleArrayOGSparseArray, RdivideOGMatrixOGSparseMatrix.getInstance());
    s_functionPointers.put(pairOGSparseArrayOGDoubleArray, RdivideOGSparseMatrixOGMatrix.getInstance());
    s_functionPointers.put(pairOGSparseArrayOGSparseArray, RdivideOGSparseMatrixOGSparseMatrix.getInstance());         
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>, S extends OGArray<? extends Number>> OGArray<? extends Number> rdivide(T array1, S array2) {
    if (array1 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 1");
    }
    if (array2 == null) {
      throw new MathsExceptionNullPointer("Null pointer passed in argument 2");
    }  
    Pair<Class<?>, Class<?>> combo = Pair.<Class<?>, Class<?>>of(array1.getClass(), array2.getClass());
    RdivideAbstract<T, S> use = (RdivideAbstract<T, S>) s_functionPointers.get(combo);
    if (use == null) {
      throw new MathsExceptionNotImplemented("Rdivide array class " + array1.getClass().toString() + " by " + array2.getClass().toString() + " is not yet implemented");
    }
    return use.rdivide(array1, array2);
  }

}
