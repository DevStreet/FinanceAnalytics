/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialMatrices.hilb;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Generates Hilbert matrices of order "n". 
 * Each element of a Hilbert matrix is defined by
 * Hilb(i,j) = 1.0 / (i+j-1)
 * For [i,j]>=1 
 */
public class Hilb {

  /**
   * Returns the Hilbert matrix of order "n"
   * @param n the order
   * @return a Hilbert matrix of order "n"
   */
  public static OGDoubleArray hilb(int n) {
    Catchers.catchValueShouldNotBeNegativeFromArgList(n, 1);
    double[] data = new double[n * n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        data[i * n + j] = 1.d / (i + j + 1);
      }
    }
    OGDoubleArray tmp = new OGDoubleArray(data, n, n);
    return tmp;
  }

}
