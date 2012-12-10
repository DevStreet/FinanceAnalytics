/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.docs;

import com.opengamma.maths.dogma.engine.DOGMADocumentationHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Abs;

/**
 * Documentation for the Abs function
 */
@DOGMADocumentationHook(providesDocsFor = Abs.class)
public class AbsDoc extends DocDefault {
  @Override
  public String shortDescription() {
    return "Returns the absolute value of the argument.";
  }

  @Override
  public String longDescription() {
    return "Returns the absolute value of the argument for both real and complex numbers."
        + " In the case of real numbers this is simply the magnitude of the number. In the case of complex number a+ib it is by definition the square root of (a^2 + b^2)";
  }

  @Override
  public String[] argDescriptions() {
    return new String[] {"The argument for which the absolute values are to be computed." };
  }

  @Override
  public String returnDescription() {
    return "the magnitude of the argument";
  }

  @Override
  public String exampleInput() {
    String str = "";
    str += "OGComplexMatrix foo = new OGComplexMatrix(new double[][]{{1,2,3,4}},new double[][]{{1,2,3,4}});" + //
        "OGArray<? extends Number> bar = abs(foo);" + //
        "disp(bar);";
    return str;
  }

  @Override
  public String exampleOutput() {
    return "Output [1.4142..., 2.8284..., 4.2426..., 5.6569...]";
  }
}
