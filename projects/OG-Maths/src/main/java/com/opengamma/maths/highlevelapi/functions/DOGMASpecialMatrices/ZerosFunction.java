/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMASpecialMatrices;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.arbitrary.Zeros;
import com.opengamma.maths.highlevelapi.datatypes.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * provides zeros()
 */
@DOGMAMethodHook(provides = Zeros.class)
public class ZerosFunction {

  /**
   * Returns the a matrix where all elements have the value 0.e0;
   * @param n the size of the matrix filled with zeros
   * @return a matrix of ones of dimension [n x n]
   */
  @DOGMAMethodLiteral
  public static OGMatrix zeros(int n) {
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(n, 1);
    double[] tmp = new double[n * n];
    return new OGMatrix(tmp, n, n);
  }

  /**
   * Returns the a matrix where all elements have the value 0.e0;
   * @param n the number of rows for the matrix filled with zeros
   * @param m the number of columns for the matrix filled with zeros
   * @return a matrix of ones of dimension [n x m]
   */
  @DOGMAMethodLiteral
  public static OGMatrix zeros(int n, int m) {
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(n, 1);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(m, 2);    
    double[] tmp = new double[n * m];
    return new OGMatrix(tmp, n, m);
  }
}
