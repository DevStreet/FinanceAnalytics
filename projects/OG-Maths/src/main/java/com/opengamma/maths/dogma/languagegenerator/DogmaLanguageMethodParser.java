/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import com.opengamma.maths.dogma.engine.language.Function;
import com.opengamma.maths.dogma.engine.operationstack.OperatorDictionaryPopulator;

/**
 * Creates "tokens" from the methods found by the operation dictionary populating class OperatorDictionaryPopulator.
 */
public class DogmaLanguageMethodParser {

  private static DogmaLanguageMethodParser s_instance = new DogmaLanguageMethodParser();

  DogmaLanguageMethodParser() {
  }

  public static DogmaLanguageMethodParser getInstance() {
    return s_instance;
  }

  private static OperatorDictionaryPopulator<Function> s_infixops;
  private static List<FullToken> s_tokens = new ArrayList<FullToken>();
  private static FullToken s_atoken;

  static {
    s_infixops = new OperatorDictionaryPopulator<Function>();
    parseFunctions();
  }

  // this is a bit of a kludge, and by bit I mean a lot
  private static void parseFunctions() {
    Map<Class<?>, Set<Function>> opsmap = s_infixops.getOperationsMap();
    System.out.println(opsmap.toString());
    Set<Class<?>> keyset = opsmap.keySet();
    for (Class<?> key : keyset) {
      s_atoken = new FullToken(key.getSimpleName(), key.getCanonicalName(), key, opsmap.get(key));
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
