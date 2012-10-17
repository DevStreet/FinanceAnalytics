/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.lu;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.derived.OGLuResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;

/**
 * Overloaded LU decomposition
 */
public class Lu {

  /**
  *
  */
  public static enum compute {
    /**
    * L compute L
    */
    L,
    /**
    * U compute U
    */
    U,
    /**
    * LU compute L and U such that L*U=A
    */
    LU,
    /**
    * LUP compute L, U and P such that L*U=P*A
    */
    LUP

  }

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, LuAbstract<?>> s_functionPointers = new HashMap<Class<?>, LuAbstract<?>>();
  static {
    s_functionPointers.put(OGDoubleArray.class, LuOGDoubleArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>> OGLuResult lu(T array1, compute these) {
    LuAbstract<T> use = (LuAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("lu() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.lu(array1, these);
  }

}
