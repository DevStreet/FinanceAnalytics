/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.atan.AtanAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.atan.AtanOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.atan.AtanOGSparseArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Overloaded Atan
 */
public class Atan {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, AtanAbstract<?>> s_functionPointers = new HashMap<Class<?>, AtanAbstract<?>>();
  static {
    s_functionPointers.put(OGDoubleArray.class, AtanOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseArray.class, AtanOGSparseArray.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>> OGArraySuper<Number> atan(T array1) {
    Catchers.catchNullFromArgList(array1, 1);
    AtanAbstract<T> use = (AtanAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Tan() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.atan(array1);
  }
  
}
