/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.docs;

import com.opengamma.maths.dogma.engine.DOGMADocumentationHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Erf;

/**
 * Documentation for the erf function
 */
@DOGMADocumentationHook(providesDocsFor = Erf.class)
public class ErfDoc extends DocDefault {
  @Override
  public String shortDescription() {
    return "Returns the error function of the argument.";
  }

  @Override
  public String longDescription() {
    return "Returns the error function of the argument.";
  }

  @Override
  public String[] argDescriptions() {
    return new String[] {"The argument for which the error function values are to be computed." };
  }

  @Override
  public String returnDescription() {
    return "the value of the error function evaluated at the argument";
  }

  @Override
  public String exampleInput() {
    String str = "";
    str += "OGMatrix foo = new OGMatrix(new double[][]{{1,2,3,4}});" + //
        "OGArray<? extends Number> bar = erf(foo);" + //
        "disp(bar);";
    return str;
  }

  @Override
  public String exampleOutput() {
    return "Output [0.84270..., 0.99532..., 0.99998..., 1.00000...]";
  }
}
