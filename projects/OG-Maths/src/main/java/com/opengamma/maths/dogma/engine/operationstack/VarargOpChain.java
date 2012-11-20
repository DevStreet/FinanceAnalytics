/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.operationstack;

import java.util.List;

import com.opengamma.maths.dogma.engine.converters.Converter;
import com.opengamma.maths.dogma.engine.language.VarargFunction;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Holds the information needed to perform a given operation including the transforms needed and the function to be invoked
 */
public class VarargOpChain {

  private List<List<Converter<? super OGArray<? extends Number>>>> _conversionList;
  private VarargFunction<OGArray<? extends Number>, OGArray<? extends Number>> _method;

  /**
   * Construct from a single infix op method
   * @param method the method to be invoked when the chain is called
   */
  public VarargOpChain(VarargFunction<OGArray<? extends Number>, OGArray<? extends Number>> method) {
    Catchers.catchNullFromArgList(method, 1);
    _method = method;
  }

  /**
   * Instantiate a full converter
   * @param conversionList how to convert the args
   * @param method the method to run once converted
   */
  public VarargOpChain(VarargFunction<OGArray<? extends Number>, OGArray<? extends Number>> method, List<List<Converter<? super OGArray<? extends Number>>>> conversionList) {
    Catchers.catchNullFromArgList(conversionList, 2);
    Catchers.catchNullFromArgList(method, 1);
    _conversionList = (conversionList);
    _method = method;
  }

  /**
   * Gets the method.
   * @return the method
   */
  public VarargFunction<OGArray<? extends Number>, OGArray<? extends Number>> getMethod() {
    return _method;
  }

  /**
   * Gets the conversionList.
   * @return the conversionList
   */
  public List<List<Converter<? super OGArray<? extends Number>>>> getConversionList() {
    return _conversionList;
  }

}
