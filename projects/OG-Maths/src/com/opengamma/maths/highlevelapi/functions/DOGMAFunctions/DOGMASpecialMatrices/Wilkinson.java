/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialMatrices;

import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Generates the Wilkinson matrix of order "n", these are commonly used in the
 * test routines for eigenvalue/vector computation as the matrix has pairs of near
 * identical eigenvalues (for larger "n"'s).
 * By using these matrices as a test, both performance and convergence of such routines can be tested.  
 */
public class Wilkinson {

  /** 
   * Returns the order "n" Wilkinson matrix
   * @param n the order of the matrix required
   * @return the order "n" Wilkinson matrix
   */
  public static OGMatrix wilkinson(final int n) {
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(n, 1);
    final int nn = n * n;
    double[] data = new double[nn];

    // special case for n=1
    if (n == 1) {
      data[0] = 0;
    } else {
      double edgeV = -(n - 1.d) / 2.d;
      final int np1 = (n + 1);
      final int nm1 = (n - 1);
      int inp1;
      // classic loop skew
      data[0] = Math.abs(edgeV);
      data[1] = 1;
      for (int i = 1; i < nm1; i++) { // could split this to remove Math.abs() for more efficiency if we ever need it
        inp1 = i * np1;
        data[inp1 + 1] = 1;
        data[inp1] = Math.abs(edgeV + i);
        data[inp1 - 1] = 1;
      }
      data[nn - 1] = Math.abs(edgeV + nm1);
      data[nn - 2] = 1;
    }
    // return
    OGMatrix tmp = new OGMatrix(data, n, n);
    return tmp;
  }

}
