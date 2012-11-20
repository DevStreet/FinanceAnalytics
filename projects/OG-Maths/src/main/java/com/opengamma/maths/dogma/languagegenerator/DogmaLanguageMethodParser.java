/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.opengamma.maths.dogma.engine.language.InfixOperator;
import com.opengamma.maths.dogma.engine.language.UnaryFunction;
import com.opengamma.maths.dogma.engine.operationstack.OperatorDictionaryPopulator;

/**
 * 
 */
public class DogmaLanguageMethodParser {

  private static DogmaLanguageMethodParser s_instance = new DogmaLanguageMethodParser();

  DogmaLanguageMethodParser() {
  }

  public static DogmaLanguageMethodParser getInstance() {
    return s_instance;
  }

  private static Map<Class<?>, TypeToken> s_interfaceMap = new HashMap<Class<?>, TypeToken>();
  private static OperatorDictionaryPopulator<FullToken> s_infixops;
  private static Method[] s_m;
  private static List<FullToken> s_tokens = new ArrayList<FullToken>();
  private static FullToken s_atoken;

  static {
    s_infixops = new OperatorDictionaryPopulator<FullToken>();
    s_m = UnaryFunction.class.getDeclaredMethods();
    s_interfaceMap.put(UnaryFunction.class, new TypeToken(s_m[0].getReturnType().toString(), s_m[0].getParameterTypes()));
    s_m = InfixOperator.class.getDeclaredMethods();
    //    System.out.println("writing out generics " + s_m[0].getReturnType().getGenericInterfaces()[0].toString());
    s_interfaceMap.put(InfixOperator.class, new TypeToken(s_m[0].getReturnType().toString(), s_m[0].getParameterTypes()));
    parseFunctions();
  }

  // this is a bit of a kludge, and by bit I mean a lot
  private static void parseFunctions() {
    Map<Class<?>, Set<FullToken>> opsmap = s_infixops.getOperationsMap();
    Set<Class<?>> keyset = opsmap.keySet();
    for (Class<?> key : keyset) {
      s_atoken = new FullToken(key.getCanonicalName(), key.getSimpleName(), s_interfaceMap.get(key.getInterfaces()[0]), key.getInterfaces()[0]);
      s_tokens.add(s_atoken);
    }
  }

  List<FullToken> getTokens() {
    return s_tokens;
  }



  @Override
  public String toString() {
    return s_infixops.getOperationsMap().keySet().toString();
  }

  @Test
  public void aTest() {
    parseFunctions();
  }

}
