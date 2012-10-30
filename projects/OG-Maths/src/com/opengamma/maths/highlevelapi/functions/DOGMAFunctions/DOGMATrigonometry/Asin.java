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
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.asin.AsinAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.asin.AsinOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMATrigonometry.asin.AsinOGSparseArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Overloaded Asin
 */
public class Asin {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, AsinAbstract<? extends OGArray<? extends Number>>> s_functionPointers = Maps.newHashMap();
  static {
    s_functionPointers.put(OGMatrix.class, AsinOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseMatrix.class, AsinOGSparseArray.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>> OGArray<? extends Number> asin(T array1) {
    Catchers.catchNullFromArgList(array1, 1);
    AsinAbstract<T> use = (AsinAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Asin() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.asin(array1);
  }
  
}
