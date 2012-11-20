/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.operationstack;

import java.util.List;

import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.dogma.engine.language.InfixOperator;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Holds the information needed to perform a given operation including the transforms needed and the function to be invoked
 */
public class InfixOpChain {

  private List<Converter<? super OGArray<? extends Number>>> _convertfirst;
  private List<Converter<? super OGArray<? extends Number>>> _convertsecond;
  private InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>> _method;

  /**
   * Construct from a single infix op method
   * @param method the method to be invoked when the chain is called
   */
  public InfixOpChain(InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>> method) {
    Catchers.catchNullFromArgList(method, 1);
    _method = method;
  }

  /**
   * Instantiate a full converter
   * @param convertfirst how to convert the first arg
   * @param method the method to run once converted
   */
  public InfixOpChain(List<Converter<? super OGArray<? extends Number>>> convertfirst, InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>> method) {
    Catchers.catchNullFromArgList(convertfirst, 1);
    Catchers.catchNullFromArgList(method, 2);
    _convertfirst = convertfirst;
    _method = method;
  }

  /**
   * Instantiate a full converter
   * @param convertfirst how to convert the first arg
   * @param convertsecond how to convert the second arg
   * @param method the method to run once converted
   */
  public InfixOpChain(List<Converter<? super OGArray<? extends Number>>> convertfirst,
      List<Converter<? super OGArray<? extends Number>>> convertsecond, InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>> method) {
    Catchers.catchNullFromArgList(convertfirst, 1);
    Catchers.catchNullFromArgList(convertsecond, 2);
    Catchers.catchNullFromArgList(method, 3);
    _convertfirst = convertfirst;
    _convertsecond = convertsecond;
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
   * Gets the convertsecond.
   * @return the convertsecond
   */
  public List<Converter<? super OGArray<? extends Number>>> getConvertsecond() {
    return _convertsecond;
  }

  /**
   * Gets the method.
   * @return the method
   */
  public InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>> getMethod() {
    return _method;
  }

}
