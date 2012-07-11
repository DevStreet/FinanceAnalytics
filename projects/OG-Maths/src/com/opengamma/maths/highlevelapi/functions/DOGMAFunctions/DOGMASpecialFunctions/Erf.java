/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialFunctions;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialFunctions.erf.ErfAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialFunctions.erf.ErfOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialFunctions.erf.ErfOGSparseArray;

/**
 * Overloaded Erf
 */
public class Erf {
  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, ErfAbstract<?>> s_functionPointers = new HashMap<Class<?>, ErfAbstract<?>>();
  static {
    s_functionPointers.put(OGDoubleArray.class, ErfOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseArray.class, ErfOGSparseArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>> OGArraySuper<Number> erf(T array1) {
    ErfAbstract<T> use = (ErfAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Erf() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.erf(array1);
  }

}
