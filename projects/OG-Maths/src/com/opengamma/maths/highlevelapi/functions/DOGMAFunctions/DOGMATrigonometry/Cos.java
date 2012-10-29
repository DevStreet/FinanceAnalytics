/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry;

import java.util.Map;

import com.google.common.collect.Maps;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.cos.CosAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.cos.CosOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.cos.CosOGSparseArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Overloaded Cos
 */
public class Cos {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, CosAbstract<?>> s_functionPointers = Maps.newHashMap();
  static {
    s_functionPointers.put(OGDoubleArray.class, CosOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseArray.class, CosOGSparseArray.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<? extends Number>> OGArraySuper<? extends Number> cos(T array1) {
    Catchers.catchNullFromArgList(array1, 1);
    CosAbstract<T> use = (CosAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Cos() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.cos(array1);
  }
  
}
