/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArraySuper;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGComplexArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGDiagonalArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGDoubleArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGPermutationArray;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGSparseArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Overloaded Transpose
 */
public class Transpose {

  /**
   * hashmapped function pointers
   */
  private static Map<Class<?>, TransposeAbstract<?>> s_functionPointers = new HashMap<Class<?>, TransposeAbstract<?>>();
  static {
    s_functionPointers.put(OGDoubleArray.class, TransposeOGDoubleArray.getInstance());
    s_functionPointers.put(OGSparseArray.class, TransposeOGSparseArray.getInstance());
    s_functionPointers.put(OGComplexArray.class, TransposeOGComplexArray.getInstance());
    s_functionPointers.put(OGDiagonalArray.class, TransposeOGDiagonalArray.getInstance());
    s_functionPointers.put(OGPermutationArray.class, TransposeOGPermutationArray.getInstance());    
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArraySuper<Number>> OGArraySuper<Number> transpose(T array1) {
    Catchers.catchNullFromArgList(array1, 1);
    TransposeAbstract<T> use = (TransposeAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Transpose of array " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.transpose(array1);
  }

}
