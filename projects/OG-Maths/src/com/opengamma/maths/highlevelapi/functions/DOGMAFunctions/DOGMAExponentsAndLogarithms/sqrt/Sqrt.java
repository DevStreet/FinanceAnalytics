/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAExponentsAndLogarithms.sqrt;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;

/**
 * Overloaded Sqrt
 */
public class Sqrt {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, SqrtAbstract<?>> s_functionPointers = new HashMap<Class<?>, SqrtAbstract<?>>();
  static {
    s_functionPointers.put(OGDoubleArray.class, SqrtOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseArray.class, SqrtOGSparseArray.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>> OGArraySuper<Number> sqrt(T array1) {
    SqrtAbstract<T> use = (SqrtAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Sqrt() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.sqrt(array1);
  }

}

