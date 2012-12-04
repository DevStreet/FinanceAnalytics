/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator;


/**
 * 
 */
public interface DogmaLangTokenToCodeGenerator {
  String generateMethodCode(FullToken f);

  String generateTableCode(FullToken f);

  String generateTableCodeVariables(FullToken f);
  
  String generateEntryPointsCode(FullToken f);  
}
