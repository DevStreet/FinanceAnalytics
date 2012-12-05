/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.languagegenerator.docs;

/**
 * Required functions of a doc
 */
public interface Doc {

  String shortDescription();

  String longDescription();

  String exampleInput();
  
  String exampleOutput();  

  String[] argDescriptions();

  String returnDescription();
}
