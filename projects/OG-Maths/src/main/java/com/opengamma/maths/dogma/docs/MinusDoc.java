/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.docs;

import com.opengamma.maths.dogma.engine.DOGMADocumentationHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.infix.Minus;

/**
 * Documentation for the minus operation
 */
@DOGMADocumentationHook(providesDocsFor = Minus.class)
public class MinusDoc extends DocDefault {
  @Override
  public String shortDescription() {
    return "Returns the element wise subtraction of the second input argument from the first.";
  }

  @Override
  public String longDescription() {
    return "Returns the element wise subtraction of the second input argument from the first." + " This is the equivalent of the m-code operation arg0-arg1. The operation"
        + " is vectorised such that if either argument is a single number it is applied to " + "each element of the argument, whereas if both arguments are the same dimension"
        + " element wise subtraction takes place.";
  }

  @Override
  public String[] argDescriptions() {
    return new String[] {"The first argument from which to subtract.", "The second argument to be subtracted." };
  }

  @Override
  public String returnDescription() {
    return "The element wise subtraction of the second input argument from the first";
  }

  @Override
  public String exampleInput() {
    String str = "";
    str += "OGMatrix foo = new OGMatrix(new double[][]{{1,2,3,4}});" + //
        "OGArray<? extends Number> bar = minus(7, foo);" + //
        "disp(bar);";
    return str;
  }

  @Override
  public String exampleOutput() {
    return "Output [6, 5, 4, 3]";
  }
}
