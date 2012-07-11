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
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.sinh.SinhAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.sinh.SinhOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.sinh.SinhOGSparseArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Overloaded Sinh
 */
public class Sinh {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, SinhAbstract<?>> s_functionPointers = new HashMap<Class<?>, SinhAbstract<?>>();
  static {
    s_functionPointers.put(OGDoubleArray.class, SinhOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseArray.class, SinhOGSparseArray.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>> OGArraySuper<Number> sinh(T array1) {
    Catchers.catchNullFromArgList(array1, 1);
    SinhAbstract<T> use = (SinhAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Sinh() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.sinh(array1);
  }
  
}
