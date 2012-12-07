/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialMatrices;

import java.util.Arrays;

import com.opengamma.maths.dogma.engine.DOGMAMethodHook;
import com.opengamma.maths.dogma.engine.DOGMAMethodLiteral;
import com.opengamma.maths.dogma.engine.methodhookinstances.arbitrary.Ones;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * provides ones()
 */
@DOGMAMethodHook(provides = Ones.class)
public class OnesFunction {

  /**
   * Returns the a matrix where all elements have the value 1.e0;
   * @param n the size of the matrix filled with ones
   * @return a matrix of ones of dimension [n x n]
   */
  @DOGMAMethodLiteral
  public static OGMatrix ones(int n) {
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(n, 1);
    double[] tmp = new double[n * n];
    Arrays.fill(tmp, 1.e0);
    return new OGMatrix(tmp, n, n);
  }

  /**
   * Returns the a matrix where all elements have the value 1.e0;
   * @param n the number of rows for the matrix filled with ones
   * @param m the number of columns for the matrix filled with ones
   * @return a matrix of ones of dimension [n x m]
   */
  @DOGMAMethodLiteral
  public static OGMatrix ones(int n, int m) {
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(n, 1);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(m, 2);    
    double[] tmp = new double[n * m];
    Arrays.fill(tmp, 1.e0);
    return new OGMatrix(tmp, n, m);
  }
}
