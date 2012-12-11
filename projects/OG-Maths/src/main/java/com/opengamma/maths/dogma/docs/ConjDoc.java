/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.docs;

import com.opengamma.maths.dogma.engine.DOGMADocumentationHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Conj;

/**
 * Documentation for the Conj function
 */
@DOGMADocumentationHook(providesDocsFor = Conj.class)
public class ConjDoc extends DocDefault {
  @Override
  public String shortDescription() {
    return "Returns the conjugated value of the argument.";
  }

  @Override
  public String longDescription() {
    return "Returns the conjugated value of the argument for both real and complex numbers."
        + " In the case of real numbers this is simply the number. In the case of complex number a+ib it is by definition the square root of (a - bi)";
  }

  @Override
  public String[] argDescriptions() {
    return new String[] {"The argument for which the conjugated values are to be computed." };
  }

  @Override
  public String returnDescription() {
    return "the conjugated value of the argument";
  }

  @Override
  public String exampleInput() {
    String str = "";
    str += "OGComplexMatrix foo = new OGComplexMatrix(new double[][]{{1,2,3,4}},new double[][]{{1,-2,3,-4}});" + //
        "OGArray<? extends Number> bar = conj(foo);" + //
        "disp(bar);";
    return str;
  }

  @Override
  public String exampleOutput() {
    return "Output [1.00... - 1.00...i, 2.00... + 2.00...i, 3.00... - 3.00...i, 4.00... + 4.00...i ]";
  }
}
