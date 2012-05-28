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
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus.PlusAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus.PlusOGDoubleArrayOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus.PlusOGDoubleArrayOGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.plus.PlusOGSparseArrayOGDoubleArray;
import com.opengamma.util.tuple.ObjectsPair;
import com.opengamma.util.tuple.Pair;

/**
 * Overloaded Plus
 */
public class PlusAndMinus {

  /**
   * hashmapped function pointers
   */
  private static Map<Pair<?, ?>, PlusAbstract<?, ?>> s_functionPointers = new HashMap<Pair<?, ?>, PlusAbstract<?, ?>>();
  static {
    ObjectsPair<Class<?>, Class<?>> pairOGDoubleArrayPlusOGDoubleArray = new ObjectsPair<Class<?>, Class<?>>(OGDoubleArray.class, OGDoubleArray.class);
    ObjectsPair<Class<?>, Class<?>> pairOGDoubleArrayPlusOGSparseArray = new ObjectsPair<Class<?>, Class<?>>(OGDoubleArray.class, OGSparseArray.class);
    ObjectsPair<Class<?>, Class<?>> pairOGSparseArrayPlusOGDoubleArray = new ObjectsPair<Class<?>, Class<?>>(OGSparseArray.class, OGDoubleArray.class);    
    s_functionPointers.put(pairOGDoubleArrayPlusOGDoubleArray, PlusOGDoubleArrayOGDoubleArray.getInstance());
    s_functionPointers.put(pairOGDoubleArrayPlusOGSparseArray, PlusOGDoubleArrayOGSparseArray.getInstance());
    s_functionPointers.put(pairOGSparseArrayPlusOGDoubleArray, PlusOGSparseArrayOGDoubleArray.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>, S extends OGArraySuper<Number>> OGArraySuper<Number> plus(T array1, S array2) {
    ObjectsPair<Class<?>, Class<?>> combo = new ObjectsPair<Class<?>, Class<?>>(array1.getClass(), array2.getClass());
    PlusAbstract<T, S> use = (PlusAbstract<T, S>) s_functionPointers.get(combo);
    if (use == null) {
      throw new MathsExceptionNotImplemented("Adding array class " + array1.getClass().toString() + " to " + array2.getClass().toString() + " is not yet implemented");
    }
    return use.plus(array1, array2);
  }

}
