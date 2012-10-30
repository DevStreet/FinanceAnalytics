/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry;

import java.util.Map;

import com.google.common.collect.Maps;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tan.TanAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tan.TanOGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tan.TanOGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Overloaded Tan
 */
public class Tan {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, TanAbstract<?>> s_functionPointers = Maps.newHashMap();
  static {
    s_functionPointers.put(OGMatrix.class, TanOGMatrix.getInstance());
    s_functionPointers.put(OGSparseMatrix.class, TanOGSparseMatrix.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>> OGArray<? extends Number> tan(T array1) {
    Catchers.catchNullFromArgList(array1, 1);
    TanAbstract<T> use = (TanAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Tan() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.tan(array1);
  }
  
}
