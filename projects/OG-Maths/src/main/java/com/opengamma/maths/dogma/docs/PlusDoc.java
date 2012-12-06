/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.docs;

import com.opengamma.maths.dogma.engine.DOGMADocumentationHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Plus;

/**
 * Documentation for the plus operation
 */
@DOGMADocumentationHook(providesDocsFor = Plus.class)
public class PlusDoc extends DocDefault {
  @Override
  public String shortDescription() {
    return "Returns the element wise sum of the input arguments.";
  }

  @Override
  public String longDescription() {
    return "Returns the element wise addition of the input arguments." + " This is the equivalent of the m-code operation arg0+arg1. The operation"
        + " is vectorised such that if either argument is a single number it is applied to " + "each element of the argument, whereas if both arguments are the same dimension"
        + " element wise addition takes place.";
  }

  @Override
  public String[] argDescriptions() {
    return new String[] {"The first argument to add.", "The second argument to add." };
  }

  @Override
  public String returnDescription() {
    return "The element wise sum of the input arguments";
  }

  @Override
  public String exampleInput() {
    String str = "";
    str += "OGMatrix foo = new OGMatrix(new double[][]{{1,2,3,4}});" + //
        "OGArray<? extends Number> bar = plus(7, foo);" + //
        "disp(bar);";
    return str;
  }

  @Override
  public String exampleOutput() {
    return "Output [8, 9, 10, 11]";
  }
}
