/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.docs;

import com.opengamma.maths.dogma.engine.DOGMADocumentationHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Times;

/**
 * Documentation for the Times operation
 */
@DOGMADocumentationHook(providesDocsFor = Times.class)
public class TimesDoc extends DocDefault {
  @Override
  public String shortDescription() {
    return "Returns the element wise multiplication of the input arguments.";
  }

  @Override
  public String longDescription() {
    return "Returns the element wise multiplication of the input arguments." + " This is the equivalent of the m-code operation arg0.*arg1. The operation"
        + " is vectorised such that if either argument is a single number it is applied as a scaling, whereas if both arguments are the same dimension element wise multiplication takes place.";
  }

  @Override
  public String[] argDescriptions() {
    return new String[] {"The first argument to multiply.", "The second argument to multiply" };
  }

  @Override
  public String exampleInput() {
    String str = "";
    str += "OGMatrix foo = new OGMatrix(new double[][]{{1,2,3,4}});" + //
        "OGArray<? extends Number> bar = times(7, foo);" + //
        "disp(bar);";
    return str;
  }

  @Override
  public String exampleOutput() {
    return "Output [7, 14, 21, 28]";
  }
}
