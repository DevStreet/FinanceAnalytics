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
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.acos.AcosAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.acos.AcosOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.acos.AcosOGSparseArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Overloaded Acos
 */
public class Acos {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, AcosAbstract<?>> s_functionPointers = Maps.newHashMap();
  static {
    s_functionPointers.put(OGMatrix.class, AcosOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseMatrix.class, AcosOGSparseArray.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>> OGArray<? extends Number> acos(T array1) {
    Catchers.catchNullFromArgList(array1, 1);
    AcosAbstract<T> use = (AcosAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Acos() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.acos(array1);
  }

}
