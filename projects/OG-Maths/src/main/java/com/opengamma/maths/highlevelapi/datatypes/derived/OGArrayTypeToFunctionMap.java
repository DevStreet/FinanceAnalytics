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
/**
 * This class is a half populated hashmap from OGArray types to to genericised objects
 * @param <T> an object
 */
public class OGArrayTypeToFunctionMap<T> {

  private Map<Class<?>, T> _functionPointers;
  {
    _functionPointers = new HashMap<Class<?>, T>();
    _functionPointers.put(OGMatrix.class, null);
    _functionPointers.put(OGSparseMatrix.class, null);
  }

  public Map<Class<?>, T> getMap() {
    return _functionPointers;
  }

}
