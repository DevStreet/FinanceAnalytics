/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMALinearAlgebra.svd;

import java.util.Map;

import com.google.common.collect.Maps;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.derived.OGSvdResult;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;

/**
 * Overloaded Svd
 */
public class Svd {

  /**
  *
  */
  public static enum compute {
    /**
    * U compute U
    */
    U,
    /**
    * S compute S
    */
    S,
    /**
    * V compute V
    */
    V,
    /**
    * US compute U and S
    */    
    US,
    /**
    * UV compute U and V
    */    
    UV,
    /**
    * V compute S and V
    */    
    SV,
    /**
    *  compute U, S and V
    */
    USV
  }

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, SvdAbstract<? extends OGArray<? extends Number>>> s_functionPointers = Maps.newHashMap();
  static {
    s_functionPointers.put(OGMatrix.class, SvdOGDoubleArray.getInstance());
    s_functionPointers.put(OGComplexMatrix.class, SvdOGComplexArray.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>> OGSvdResult svd(T array1, compute these) {
    SvdAbstract<T> use = (SvdAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("svd() on array class " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.svd(array1, these);
  }

}
