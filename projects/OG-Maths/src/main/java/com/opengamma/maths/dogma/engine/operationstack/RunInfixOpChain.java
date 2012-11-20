/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.operationstack;

import java.util.List;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.dogma.engine.language.InfixOperator;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Performs the operations described by an infix operation chain, InfixOpChain, type.
 */
public class RunInfixOpChain {

  /**
   * Dispatches the operation from an InfixOpChain 
   * @param ops the operations
   * @param arr1 the first operand
   * @param arr2 the second operand 
   * @return the operation performed with arr1 and arr2 as args
   */
  public OGArray<? extends Number> dispatch(InfixOpChain ops, OGArray<? extends Number> arr1, OGArray<? extends Number> arr2) {
    Catchers.catchNullFromArgList(ops, 1);
    Catchers.catchNullFromArgList(arr1, 2);
    Catchers.catchNullFromArgList(arr2, 3);
    OGArray<? extends Number> converted1;
    OGArray<? extends Number> converted2;
    List<Converter<? super OGArray<? extends Number>>> chain1 = ops.getConvertfirst();
    List<Converter<? super OGArray<? extends Number>>> chain2 = ops.getConvertsecond();
    if (chain1 != null) {
      converted1 = walkConvertChain(chain1, arr1);
    } else {
      converted1 = arr1;
    }
    if (chain2 != null) {
      converted2 = walkConvertChain(chain2, arr2);
    } else {
      converted2 = arr2;
    }
    InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>> m = ops.getMethod();
    if (m == null) {
      throw new MathsExceptionGeneric("Instruction is missing from operation chain, problematic classes were: First operand: " + arr2.getClass().toString() +
          " Second operand: " + arr2.getClass().toString());
    }
    return m.eval(converted1, converted2);
  }

  // not strictly needed as a list, might be in the future though
  private OGArray<? extends Number> walkConvertChain(List<Converter<? super OGArray<? extends Number>>> chain, OGArray<? extends Number> array1) {
    int n = chain.size();
    Converter<? super OGArray<? extends Number>> c = chain.get(0);
    if (c == null) {
      throw new MathsExceptionGeneric("Conversion impossible for class: " + array1.getClass().toString());
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
