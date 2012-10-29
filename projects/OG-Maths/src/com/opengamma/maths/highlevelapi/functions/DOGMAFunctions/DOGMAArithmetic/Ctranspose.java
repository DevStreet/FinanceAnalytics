/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic;

import java.util.Map;

import com.google.common.collect.Maps;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose.CtransposeAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose.CtransposeOGComplexArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose.CtransposeOGDiagonalArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose.CtransposeOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose.CtransposeOGIndexArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose.CtransposeOGPermutationArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.ctranspose.CtransposeOGSparseArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Overloaded Conjugate Transpose
 * TODO: possibly rewrite as an extension to transpose() to save repetition 
 */
public class Ctranspose {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<? extends OGArraySuper<? extends Number>>, CtransposeAbstract<? extends OGArraySuper<? extends Number>>> s_functionPointers = Maps.newHashMap();
  static {
    s_functionPointers.put(OGDoubleArray.class, CtransposeOGDoubleArray.getInstance());
    s_functionPointers.put(OGIndexArray.class, CtransposeOGIndexArray.getInstance());    
    s_functionPointers.put(OGSparseArray.class, CtransposeOGSparseArray.getInstance());
    s_functionPointers.put(OGComplexArray.class, CtransposeOGComplexArray.getInstance());
    s_functionPointers.put(OGPermutationArray.class, CtransposeOGPermutationArray.getInstance());
    s_functionPointers.put(OGDiagonalArray.class, CtransposeOGDiagonalArray.getInstance());   
  }

  @SuppressWarnings("unchecked")
  public OGArraySuper<? extends Number> ctranspose(OGArraySuper<? extends Number> array1) {
    Catchers.catchNullFromArgList(array1, 1);
    CtransposeAbstract<OGArraySuper<? extends Number>> use = (CtransposeAbstract<OGArraySuper<? extends Number>>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Transpose of array " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.ctranspose(array1);
  }

}
