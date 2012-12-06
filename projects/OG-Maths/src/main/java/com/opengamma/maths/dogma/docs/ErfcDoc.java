/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.docs;

import com.opengamma.maths.dogma.engine.DOGMADocumentationHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Erfc;

/**
 * Documentation for the erfc function
 */
@DOGMADocumentationHook(providesDocsFor = Erfc.class)
public class ErfcDoc extends DocDefault {
  @Override
  public String shortDescription() {
    return "Returns the complimentary error function of the argument.";
  }

  @Override
  public String longDescription() {
    return "Returns the complimentary error function of the argument. Mathematically: 1 - erf(arg0)";
  }

  @Override
  public String[] argDescriptions() {
    return new String[] {"The argument for which the complimentary error function values are to be computed." };
  }

  @Override
  public String returnDescription() {
    return "the value of the complimentary error function evaluated at the argument";
  }

  @Override
  public String exampleInput() {
    String str = "";
    str += "OGMatrix foo = new OGMatrix(new double[][]{{0.1,0.2,0.3,0.4}});" + //
        "OGArray<? extends Number> bar = erfc(foo);" + //
        "disp(bar);";
    return str;
  }

  @Override
  public String exampleOutput() {
    return "Output [0.88754..., 0.77730..., 0.67137..., 0.57161...]";
  }
}
