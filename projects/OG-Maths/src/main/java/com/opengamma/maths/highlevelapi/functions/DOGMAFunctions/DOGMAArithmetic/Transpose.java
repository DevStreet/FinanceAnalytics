/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic;

import java.util.HashMap;
import java.util.Map;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeAbstract;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGComplexMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGIndexMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGPermutationMatrix;
import com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic.transpose.TransposeOGSparseMatrix;
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
    s_functionPointers.put(OGMatrix.class, TransposeOGMatrix.getInstance());
    s_functionPointers.put(OGIndexMatrix.class, TransposeOGIndexMatrix.getInstance());
    s_functionPointers.put(OGSparseMatrix.class, TransposeOGSparseMatrix.getInstance());
    s_functionPointers.put(OGComplexMatrix.class, TransposeOGComplexMatrix.getInstance());
    s_functionPointers.put(OGDiagonalMatrix.class, TransposeOGDiagonalMatrix.getInstance());
    s_functionPointers.put(OGPermutationMatrix.class, TransposeOGPermutationMatrix.getInstance());
  }

  @SuppressWarnings("unchecked")
  public <T extends OGArray<? extends Number>> OGArray<? extends Number> transpose(T array1) {
    Catchers.catchNullFromArgList(array1, 1);
    TransposeAbstract<T> use = (TransposeAbstract<T>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Transpose of array " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.transpose(array1);
  }

}
