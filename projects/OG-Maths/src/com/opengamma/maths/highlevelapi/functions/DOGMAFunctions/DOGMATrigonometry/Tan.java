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
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tan.TanAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tan.TanOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tan.TanOGSparseArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Overloaded Tan
 */
public class Tan {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, TanAbstract<?>> s_functionPointers = new HashMap<Class<?>, TanAbstract<?>>();
  static {
    s_functionPointers.put(OGDoubleArray.class, TanOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseArray.class, TanOGSparseArray.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>> OGArraySuper<Number> tan(T array1) {
    Catchers.catchNullFromArgList(array1, 1);
    TanAbstract<T> use = (TanAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Tan() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.tan(array1);
  }
  
}
