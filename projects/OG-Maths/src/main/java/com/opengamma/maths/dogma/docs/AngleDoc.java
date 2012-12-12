/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.docs;

import com.opengamma.maths.dogma.engine.DOGMADocumentationHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Angle;

/**
 * Documentation for the Angle function
 */
@DOGMADocumentationHook(providesDocsFor = Angle.class)
public class AngleDoc extends DocDefault {
  @Override
  public String shortDescription() {
    return "Returns the argument (or phase) of the input argument.";
  }

  @Override
  public String longDescription() {
    return "Returns the argument (or phase) of the input argument."
        + " By definition for a complex number a+bi it is atan(b/a)." +
        "The quadrant in which the answer lies is accounted for through the use of atan2()";
  }

  @Override
  public String[] argDescriptions() {
    return new String[] {"The argument for which the value of the mathematical argument is to be computed." };
  }

  @Override
  public String returnDescription() {
    return "the mathematical argument of the input";
  }

  @Override
  public String exampleInput() {
    String str = "";
    str += "OGComplexMatrix foo = new OGComplexMatrix(new double[][]{{0,1,-1}},new double[][]{{1,1,0}});" + //
        "OGArray<? extends Number> bar = angle(foo);" + //
        "disp(bar);";
    return str;
  }

  @Override
  public String exampleOutput() {
    return "Output [1.57080..., 0.78540..., 3.14159]";
  }
}
