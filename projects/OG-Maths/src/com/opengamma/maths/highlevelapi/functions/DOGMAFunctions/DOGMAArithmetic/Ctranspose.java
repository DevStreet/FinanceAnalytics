/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMAArithmetic;

import java.util.Map;

import com.google.common.collect.Maps;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNotImplemented;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGComplexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDiagonalMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGIndexMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGPermutationMatrix;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGSparseMatrix;
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
  private static Map<Class<? extends OGArray<? extends Number>>, CtransposeAbstract<? extends OGArray<? extends Number>>> s_functionPointers = Maps.newHashMap();
  static {
    s_functionPointers.put(OGMatrix.class, CtransposeOGDoubleArray.getInstance());
    s_functionPointers.put(OGIndexMatrix.class, CtransposeOGIndexArray.getInstance());    
    s_functionPointers.put(OGSparseMatrix.class, CtransposeOGSparseArray.getInstance());
    s_functionPointers.put(OGComplexMatrix.class, CtransposeOGComplexArray.getInstance());
    s_functionPointers.put(OGPermutationMatrix.class, CtransposeOGPermutationArray.getInstance());
    s_functionPointers.put(OGDiagonalMatrix.class, CtransposeOGDiagonalArray.getInstance());   
  }

  @SuppressWarnings("unchecked")
  public OGArray<? extends Number> ctranspose(OGArray<? extends Number> array1) {
    Catchers.catchNullFromArgList(array1, 1);
    CtransposeAbstract<OGArray<? extends Number>> use = (CtransposeAbstract<OGArray<? extends Number>>) s_functionPointers.get(array1.getClass());
    if (use == null) {
      throw new MathsExceptionNotImplemented("Transpose of array " + array1.getClass().toString() + " is not yet implemented");
    }
    return use.ctranspose(array1);
  }

}
