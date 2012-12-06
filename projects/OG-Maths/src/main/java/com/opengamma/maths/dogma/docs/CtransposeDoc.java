/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.dogma.docs;

import com.opengamma.maths.dogma.engine.DOGMADocumentationHook;
import com.opengamma.maths.dogma.engine.methodhookinstances.unary.Ctranspose;

/**
 * Documentation for the Conjugate Transpose operation
 */
@DOGMADocumentationHook(providesDocsFor = Ctranspose.class)
public class CtransposeDoc extends DocDefault {
  @Override
  public String shortDescription() {
    return "Returns a the conjugate transpose of the argument.";
  }

  @Override
  public String longDescription() {
    return "Returns a the conjugate transpose of the argument. This is the equivalent to arg0' in m-code. If the literal transpose of a matrix is needed then use the function transpose()";
  }

  @Override
  public String[] argDescriptions() {
    return new String[] {"the array on which the conjugate transpose operation shall be applied." };
  }

  @Override
  public String returnDescription() {
    return "Returns the conjugate transpose of the argument";
  }

  @Override
  public String exampleInput() {
    String str = "";
    str += "OGComplexMatrix foo = new OGComplexMatrix(new double[][]{{1,2,3,4}},new double[][]{{-10,20,-30,4}});" + //
        "OGArray<? extends Number> bar = ctranspose(foo);" + //
        "disp(bar);";
    return str;
  }

  @Override
  public String exampleOutput() {
    return "Output [0.84270..., 0.99532..., 0.99998..., 1.00000...]";
  }

}
