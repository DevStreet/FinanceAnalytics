/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.docs;

import com.opengamma.maths.dogma.engine.DOGMADocumentationHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Real;

/**
 * Documentation for the real() function
 */
@DOGMADocumentationHook(providesDocsFor = Real.class)
public class RealDoc extends DocDefault {
  @Override
  public String shortDescription() {
    return "Returns the real part of the argument.";
  }

  @Override
  public String longDescription() {
    return "Returns the real part of the argument for both real and complex numbers."
        + " In the case of real numbers this returns the input. In the case of complex numbers a+ib returns the values of \"a\"";
  }

  @Override
  public String[] argDescriptions() {
    return new String[] {"The argument for which the real values are to be extracted." };
  }

  @Override
  public String returnDescription() {
    return "the real part of the argument";
  }

  @Override
  public String exampleInput() {
    String str = "";
    str += "OGComplexMatrix foo = new OGComplexMatrix(new double[][]{{1,20,3,40}},new double[][]{{1,-2,3,-4}});" + //
        "OGArray<? extends Number> bar = real(foo);" + //
        "disp(bar);";
    return str;
  }

  @Override
  public String exampleOutput() {
    return "Output [1.00..., 20.00..., 3.00..., 40.00...]";
  }
}
