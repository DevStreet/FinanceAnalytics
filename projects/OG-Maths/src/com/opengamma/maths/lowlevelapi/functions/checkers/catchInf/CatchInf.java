/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.functions.checkers.catchInf;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;

/**
 * Overloaded CatchInf
 */
public final class CatchInf {

  private static CatchInf s_instance = new CatchInf();

  public static CatchInf getInstance() {
    return s_instance;
  }

  private CatchInf() {
  }

  
  /**
  * hashmapped function pointers
  */
  private static Map<Class<?>, CatchInfAbstract<?>> s_functionPointers = new HashMap<Class<?>, CatchInfAbstract<?>>();
  static {
    s_functionPointers.put(OGDoubleArray.class, CatchInfOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseArray.class, CatchInfOGSparseArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<? extends Number>> void catchinf(T array1) {
    CatchInfAbstract<T> use = (CatchInfAbstract<T>) s_functionPointers.get(array1.getClass());
    use.catchinf(array1);
  }

}
