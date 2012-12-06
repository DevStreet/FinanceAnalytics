/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.engine.language;

import java.util.HashSet;
import java.util.Set;

/**
 * Holds the Function interfaces
 */
public class FunctionInterfaceRegister {

  public FunctionInterfaceRegister() {
  };

  private static FunctionInterfaceRegister s_instance;

  public static FunctionInterfaceRegister getInstance() {
    return s_instance;
  };

  private static Set<Class<?>> s_registeredFunctionInterfaces = new HashSet<Class<?>>();

  static {
    s_registeredFunctionInterfaces.add(InfixOperator.class);
    s_registeredFunctionInterfaces.add(UnaryFunction.class);
    s_registeredFunctionInterfaces.add(ArbitraryFunction.class);
    s_registeredFunctionInterfaces.add(VoidUnaryFunction.class);    
  }

  public static Set<Class<?>> getRegisteredFunctionInterfaces() {
    return s_registeredFunctionInterfaces;
  }

}
