/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.rdivide.RdivideAbstract;
import com.opengamma.util.tuple.ObjectsPair;
import com.opengamma.util.tuple.Pair;

/**
 * Does fastmath style rdivide
 */
public class RdivideFastMath {
  /**
  * hashmapped function pointers
  */
  private static Map<Pair<?, ?>, RdivideAbstract<?, ?>> s_functionPointers = new HashMap<Pair<?, ?>, RdivideAbstract<?, ?>>();
  static {
    ObjectsPair<Class<?>, Class<?>> pairOGDoubleArrayOGDoubleArray = new ObjectsPair<Class<?>, Class<?>>(OGDoubleArray.class, OGDoubleArray.class);
    ObjectsPair<Class<?>, Class<?>> pairOGDoubleArrayOGSparseArray = new ObjectsPair<Class<?>, Class<?>>(OGDoubleArray.class, OGSparseArray.class);
    ObjectsPair<Class<?>, Class<?>> pairOGSparseArrayOGDoubleArray = new ObjectsPair<Class<?>, Class<?>>(OGSparseArray.class, OGDoubleArray.class);
    ObjectsPair<Class<?>, Class<?>> pairOGSparseArrayOGSparseArray = new ObjectsPair<Class<?>, Class<?>>(OGSparseArray.class, OGSparseArray.class);
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>, S extends OGArraySuper<Number>> OGArraySuper<Number> rdivide(T array1, S array2) {
    ObjectsPair<Class<?>, Class<?>> combo = new ObjectsPair<Class<?>, Class<?>>(array1.getClass(), array2.getClass());
    RdivideAbstract<T, S> use = (RdivideAbstract<T, S>) s_functionPointers.get(combo);
    if (use == null) {
      throw new MathsExceptionNotImplemented("Adding array class " + array1.getClass().toString() + " to " + array2.getClass().toString() + " is not yet implemented");
    }
    return use.rdivide(array1, array2);
  }

}
