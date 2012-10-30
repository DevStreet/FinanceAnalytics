/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.datatypes.derived;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.util.tuple.ObjectsPair;
import com.opengamma.util.tuple.Pair;

/**
 * This class is a half populated hashmap from OGArray types pairs to genericised objects
 * @param <T> an object
 */
public class OGArrayTypePairsToFunctionMap<T> {
  private Map<Pair<?, ?>, T> _functionPointers;
  {
    ObjectsPair<Class<?>, Class<?>> pairOGDoubleArrayOGDoubleArray = new ObjectsPair<Class<?>, Class<?>>(OGMatrix.class, OGMatrix.class);
    ObjectsPair<Class<?>, Class<?>> pairOGDoubleArrayOGSparseArray = new ObjectsPair<Class<?>, Class<?>>(OGMatrix.class, OGSparseMatrix.class);
    ObjectsPair<Class<?>, Class<?>> pairOGSparseArrayOGDoubleArray = new ObjectsPair<Class<?>, Class<?>>(OGSparseMatrix.class, OGMatrix.class);
    _functionPointers = new HashMap<Pair<?, ?>, T>();
    _functionPointers.put(pairOGDoubleArrayOGDoubleArray, null);
    _functionPointers.put(pairOGDoubleArrayOGSparseArray, null);
    _functionPointers.put(pairOGSparseArrayOGDoubleArray, null);
  }

  Map<Pair<?, ?>, T> getMap() {
    return _functionPointers;
  }

}
