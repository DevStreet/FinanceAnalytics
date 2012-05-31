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
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes.MtimesAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes.MtimesOGDoubleArrayOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.mtimes.MtimesOGDoubleArrayOGSparseArray;
import com.opengamma.util.tuple.ObjectsPair;
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
    ObjectsPair<Class<?>, Class<?>> pairOGDoubleArrayOGDoubleArray = new ObjectsPair<Class<?>, Class<?>>(OGDoubleArray.class, OGDoubleArray.class);
    ObjectsPair<Class<?>, Class<?>> pairOGDoubleArrayOGSparseArray = new ObjectsPair<Class<?>, Class<?>>(OGDoubleArray.class, OGSparseArray.class);
    ObjectsPair<Class<?>, Class<?>> pairOGSparseArrayOGDoubleArray = new ObjectsPair<Class<?>, Class<?>>(OGSparseArray.class, OGDoubleArray.class);
    ObjectsPair<Class<?>, Class<?>> pairOGSparseArrayOGSparseArray = new ObjectsPair<Class<?>, Class<?>>(OGSparseArray.class, OGSparseArray.class);    
    s_functionPointers.put(pairOGDoubleArrayOGDoubleArray, MtimesOGDoubleArrayOGDoubleArray.getInstance());
    s_functionPointers.put(pairOGDoubleArrayOGSparseArray, MtimesOGDoubleArrayOGSparseArray.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>, S extends OGArraySuper<Number>> OGArraySuper<Number> mtimes(T array1, S array2) {
    ObjectsPair<Class<?>, Class<?>> combo = new ObjectsPair<Class<?>, Class<?>>(array1.getClass(), array2.getClass());
    MtimesAbstract<T, S> use = (MtimesAbstract<T, S>) s_functionPointers.get(combo);
    if (use == null) {
      throw new MathsExceptionNotImplemented("Mtimes array class " + array1.getClass().toString() + " and " + array2.getClass().toString() + " is not yet implemented");
    }
    return use.mtimes(array1, array2);
  }

}
