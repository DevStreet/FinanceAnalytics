/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions.checkers.catchNaN;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;

/**
 * Overloaded CatchNaN
 */
public final class CatchNaN {

  private static CatchNaN s_instance = new CatchNaN();

  public static CatchNaN getInstance() {
    return s_instance;
  }

  private CatchNaN() {
  }

  
  /**
  * hashmapped function pointers
  */
  private static Map<Class<?>, CatchNaNAbstract<?>> s_functionPointers = new HashMap<Class<?>, CatchNaNAbstract<?>>();
  static {
    s_functionPointers.put(OGDoubleArray.class, CatchNaNOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseArray.class, CatchNaNOGSparseArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<? extends Number>> void catchnan(T array1) {
    CatchNaNAbstract<T> use = (CatchNaNAbstract<T>) s_functionPointers.get(array1.getClass());
    use.catchnan(array1);
  }

}
