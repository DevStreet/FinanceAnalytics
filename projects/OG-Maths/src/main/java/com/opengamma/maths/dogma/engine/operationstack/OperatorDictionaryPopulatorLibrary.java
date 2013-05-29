/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.operationstack;

import com.opengamma.maths.dogma.engine.language.InfixOperator;
import com.opengamma.maths.dogma.engine.language.UnaryFunction;
import com.opengamma.maths.dogma.engine.language.VoidUnaryFunction;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGArray;

/**
 * A container for the operator dictionaries 
 */
public class OperatorDictionaryPopulatorLibrary {

  OperatorDictionaryPopulatorLibrary() {
  }

  private static OperatorDictionaryPopulatorLibrary s_instance = new OperatorDictionaryPopulatorLibrary();

  public static OperatorDictionaryPopulatorLibrary getInstance() {
    return s_instance;
  }

  private static OperatorDictionaryPopulator<InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>> operatorDictInfix = new OperatorDictionaryPopulator<InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>>(); //CSIGNORE
  private static OperatorDictionaryPopulator<UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>>> operatorDictUnary = new OperatorDictionaryPopulator<UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>>>(); //CSIGNORE
  private static OperatorDictionaryPopulator<VoidUnaryFunction<OGArray<? extends Number>>> operatorDictVoidUnary = new OperatorDictionaryPopulator<VoidUnaryFunction<OGArray<? extends Number>>>(); //CSIGNORE

  /**
   * Returns the infix operator dictionary
   * @return the infix operator dictionary
   */
  public static OperatorDictionaryPopulator<InfixOperator<OGArray<? extends Number>, OGArray<? extends Number>, OGArray<? extends Number>>> getInfixOperatorDictionary() {
    return operatorDictInfix;
  }

  /**
   * Returns the unary operator dictionary
   * @return the unary operator dictionary
   */
  public static OperatorDictionaryPopulator<UnaryFunction<OGArray<? extends Number>, OGArray<? extends Number>>> getUnaryOperatorDictionary() {
    return operatorDictUnary;
  }

  /**
   * Returns the void unary operator dictionary
   * @return the void unary operator dictionary
   */
  public static OperatorDictionaryPopulator<VoidUnaryFunction<OGArray<? extends Number>>> getVoidUnaryOperatorDictionary() {
    return operatorDictVoidUnary;
  }

}
