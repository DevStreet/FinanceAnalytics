/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.operationstack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionNullPointer;
import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.dogma.engine.language.VarargFunction;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Performs the operations described by an infix operation chain, InfixOpChain, type.
 */
public class RunVarargOpChain {

  /**
   * Dispatches the operation from an InfixOpChain 
   * @param ops the operations
   * @param arrayArgs the first operand
   * @return the operation performed with arr1 and arr2 as args
   */
  @SuppressWarnings("unchecked")
  public OGArray<? extends Number> dispatch(VarargOpChain ops, OGArray<? extends Number>... arrayArgs) {
    Catchers.catchNullFromArgList(ops, 1);
    Catchers.catchNullFromArgList(arrayArgs, 2);
    List<List<Converter<? super OGArray<? extends Number>>>> chain = ops.getConversionList();
    if (chain.size() != arrayArgs.length) {
      throw new MathsExceptionGeneric("Number of conversion function pointers in conversion chain does not match number of varargs");
    }
    Iterator<List<Converter<? super OGArray<? extends Number>>>> it = chain.iterator();
    List<Converter<? super OGArray<? extends Number>>> next;
    int ptr = 0;

    ArrayList<OGArray<? extends Number>> converted = new ArrayList<OGArray<? extends Number>>();

    while (it.hasNext()) {
      next = it.next();
      if (arrayArgs[ptr] == null) {
        throw new MathsExceptionNullPointer("Array passed as argument " + (ptr + 1) + " is null.");
      }
      if (next != null) {
        converted.add(walkConvertChain(next, arrayArgs[ptr]));
      } else {
        converted.add(arrayArgs[ptr]);
      }
      ptr++;
    }
    VarargFunction<OGArray<? extends Number>, OGArray<? extends Number>> m = ops.getMethod();
    if (m == null) {
      StringBuffer sb = new StringBuffer();
      sb.append("[ ");
      for (int i = 0; i < arrayArgs.length; i++) {
        sb.append(arrayArgs[i].getClass().toString());
        sb.append(" , ");
      }
      sb.append(" ]");
      throw new MathsExceptionGeneric("Instruction is missing from operation chain, problematic classes were: " + sb.toString());
    }
    return m.eval(converted.toArray((OGArray<Number>[]) Array.newInstance(OGArray.class, 0)));
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
