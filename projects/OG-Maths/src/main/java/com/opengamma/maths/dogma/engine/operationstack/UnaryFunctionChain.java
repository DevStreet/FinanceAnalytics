/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.operationstack;

import java.util.List;

import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.dogma.engine.language.UnaryFunction;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Holds the information needed to perform a given operation including the transforms needed and the function to be invoked
 */
public class UnaryFunctionChain {

  private List<Converter<? super OGArray<? extends Number>>> _convertfirst;
  private UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>> _method;

  /**
   * Construct from a single infix op method
   * @param method the method to be invoked when the chain is called
   */
  public UnaryFunctionChain(UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>> method) {
    Catchers.catchNullFromArgList(method, 1);
    _method = method;
  }

  /**
   * Instantiate a full converter
   * @param convertfirst how to convert the first arg
   * @param method the method to run once converted
   */
  public UnaryFunctionChain(List<Converter<? super OGArray<? extends Number>>> convertfirst, UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>> method) {
    Catchers.catchNullFromArgList(convertfirst, 1);
    Catchers.catchNullFromArgList(method, 2);
    _convertfirst = convertfirst;
    _method = method;
  }

  /**
  * Gets the convertfirst.
  * @return the convertfirst
  */
  public List<Converter<? super OGArray<? extends Number>>> getConvertfirst() {
    return _convertfirst;
  }

  /**
   * Gets the method.
   * @return the method
   */
  public UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>> getMethod() {
    return _method;
  }

}
