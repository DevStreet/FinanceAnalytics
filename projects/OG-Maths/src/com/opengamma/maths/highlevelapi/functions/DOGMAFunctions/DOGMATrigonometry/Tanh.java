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
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tanh.TanhAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tanh.TanhOGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.tanh.TanhOGSparseMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;


/**
 * Overloaded Tanh
 */
public class Tanh {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, TanhAbstract<?>> s_functionPointers = Maps.newHashMap();
  static {
    s_functionPointers.put(OGMatrix.class, TanhOGMatrix.getInstance());
    s_functionPointers.put(OGSparseMatrix.class, TanhOGSparseMatrix.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>> OGArray<? extends Number> tanh(T array1) {
    Catchers.catchNullFromArgList(array1, 1);
    TanhAbstract<T> use = (TanhAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Tanh() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.tanh(array1);
  }
  
}
