/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.docs;

import com.opengamma.maths.dogma.engine.DOGMADocumentationHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Imag;

/**
 * Documentation for the imag() function
 */
@DOGMADocumentationHook(providesDocsFor = Imag.class)
public class ImagDoc extends DocDefault {
  @Override
  public String shortDescription() {
    return "Returns the imaginary part of the argument.";
  }

  @Override
  public String longDescription() {
    return "Returns the imaginary part of the argument for both real and complex numbers."
        + " In the case of real numbers this returns the zero matrix of dimension the same as the input. In the case of complex numbers a+ib returns the values of \"b\"";
  }

  @Override
  public String[] argDescriptions() {
    return new String[] {"The argument for which the imaginary values are to be extracted." };
  }

  @Override
  public String returnDescription() {
    return "the imaginary part of the argument";
  }

  @Override
  public String exampleInput() {
    String str = "";
    str += "OGComplexMatrix foo = new OGComplexMatrix(new double[][]{{1,2,3,4}},new double[][]{{1,-2,3,-4}});" + //
        "OGArray<? extends Number> bar = imag(foo);" + //
        "disp(bar);";
    return str;
  }

  @Override
  public String exampleOutput() {
    return "Output [1.00..., -2.00..., 3.00..., -4.00...]";
  }
}
