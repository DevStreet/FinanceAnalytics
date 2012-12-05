/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.docs;

import com.opengamma.maths.dogma.languagegenerator.docs.Doc;

/**
 * Required functions of a doc
 */
public class DocDefault implements Doc {
  @Override
  public String shortDescription() {
    return "No short description given.";
  }

  @Override
  public String longDescription() {
    return "No long description given.";
  }

  @Override
  public String exampleInput() {
    return "No examples given.";
  }

  @Override
  public String exampleOutput() {
    return "No examples output given.";
  } 
  
  @Override
  public String[] argDescriptions() {
    return new String[] {"No arguments described" };
  }

  @Override
  public String returnDescription() {
    return "No return value description given";
  }

}
