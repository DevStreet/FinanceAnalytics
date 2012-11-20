/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.operationstack;

import java.util.List;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.dogma.engine.language.UnaryFunction;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Performs the operations described by an infix operation chain, InfixOpChain, type.
 */
public class RunUnaryFunctionChain {

  /**
   * Dispatches the operation from an InfixOpChain 
   * @param ops the operations
   * @param arr1 the first operand
   * @return ops(arr1)
   */
  public OGArray<? extends Number> dispatch(UnaryFunctionChain ops, OGArray<? extends Number> arr1) {
    if (ops == null) {
      throw new MathsExceptionGeneric("Invalid operation (or operation is not implemented) for " + arr1.getClass().toString());
    }
    Catchers.catchNullFromArgList(arr1, 2);
    OGArray<? extends Number> converted1;
    UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>> m = ops.getMethod();
    if (m == null) {
      throw new MathsExceptionGeneric("Instruction is missing from operation chain, problematic class was: First operand: " + arr1.getClass().toString());
    }
    List<Converter<? super OGArray<? extends Number>>> chain1 = ops.getConvertfirst();
    if (chain1 != null) {
      converted1 = walkConvertChain(chain1, arr1, m);
    } else {
      converted1 = arr1;
    }
    return m.eval(converted1);
  }

  // not strictly needed as a list, might be in the future though
  private OGArray<? extends Number> walkConvertChain(List<Converter<? super OGArray<? extends Number>>> chain, OGArray<? extends Number> array1,
      UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>> m) {
    int n = chain.size();
    Converter<? super OGArray<? extends Number>> c = chain.get(0);
    if (c == null) {
      throw new MathsExceptionGeneric("Conversion impossible for class: " + array1.getClass().toString() + ", encountered whilst attempting to run " + m.getClass().toString());
    }
    OGArray<? extends Number> ret = c.convert(array1);
    for (int i = 1; i < n; i++) {
      c = chain.get(i);
      if (c == null) {
        throw new MathsExceptionGeneric("Conversion impossible for class: " + array1.getClass().toString());
      }
      ret = c.convert(ret);
    }
    return ret;
  }

}
