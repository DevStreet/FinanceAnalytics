/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAExponentsAndLogarithms.sqrt;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;

/**
 * Overloaded Sqrt
 */
public class Sqrt {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, SqrtAbstract<?>> s_functionPointers = new HashMap<Class<?>, SqrtAbstract<?>>();
  static {
    s_functionPointers.put(OGMatrix.class, SqrtOGMatrix.getInstance());
    s_functionPointers.put(OGSparseMatrix.class, SqrtOGSparseMatrix.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>> OGArray<? extends Number> sqrt(T array1) {
    SqrtAbstract<T> use = (SqrtAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Sqrt() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.sqrt(array1);
  }

}

