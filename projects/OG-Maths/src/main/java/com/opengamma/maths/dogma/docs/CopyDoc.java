/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.docs;

import com.opengamma.maths.dogma.engine.DOGMADocumentationHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Copy;

/**
 * Documentation for the Copy operation
 */
@DOGMADocumentationHook(providesDocsFor = Copy.class)
public class CopyDoc extends DocDefault {
  @Override
  public String shortDescription() {
    return "Returns a clean copy of the argument.";
  }
  
  @Override
  public String longDescription() {
    return "Returns a clean deep copy of the argument.";
  }  

  @Override
  public String returnDescription() {
    return "Returns a clean deep copy of the argument";
  }
  
  @Override 
  public String[] argDescriptions() {
    return new String[] {"The array to be copied."};
  }

}
