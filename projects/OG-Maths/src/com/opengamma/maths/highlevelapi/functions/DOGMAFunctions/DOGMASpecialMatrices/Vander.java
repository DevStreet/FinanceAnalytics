/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialMatrices;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGMatrix;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;

/**
 * Generates Vandermonde matrices from a vector
 */
public class Vander {

  /**
   * Generates the Vandermonde matrix of order "n"
   * @param aMatrix a vector of values to act as the base for the polynomial form, orientation invariant
   * @param n the order
   * @return a Vandermonde matrix based on aMatrix of order "n"
   */
  public static OGMatrix vander(OGMatrix aMatrix, int n) {
    Catchers.catchNull(aMatrix);
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(n, 2);
    // make sure the array is a vector
    final int rows = aMatrix.getNumberOfRows();
    final int cols = aMatrix.getNumberOfColumns();
    final int len = Math.max(rows, cols);

    if (!((rows == 1 && cols >= 1) || (cols == 1 && rows >= 1))) {
      throw new MathsExceptionIllegalArgument("Input matrix must have vector dimensions");
    }

    // the computation
    final double[] basis = new double[len];
    System.arraycopy(aMatrix.getData(), 0, basis, 0, len);
    int offset = (n - 1) * len;
    double[] data = new double[n * len];
    // 0^th power column
    for (int j = 0; j < len; j++) {
      data[offset + j] = 1;
    }
    if (n > 1) {
      // copy in 1^th power column
      System.arraycopy(basis, 0, data, (n - 2) * len, len);

      // walk through rest of columns
      int idx, idxm1;
      for (int i = 2; i < n; i++) {
        idx = n - i;
        idxm1 = idx - 1;
        // backwards copy, Vandermonde in the polynomial sense has most extreme powers on lhs
        offset = (idxm1) * len;
        System.arraycopy(data, idx * len, data, offset, len);
        // multiply by basis
        for (int j = 0; j < len; j++) {
          data[offset + j] *= basis[j];
        }
      }
    }
    return new OGMatrix(data, len, n);
  }

  /**
   * Generates the Vandermonde matrix of order "n" where "n" is the length of the base vector
   * @param aMatrix a vector of values to act as the base for the polynomial form, orientation invariant
   * @return a Vandermonde matrix based on aMatrix of order "n"
   */
  public static OGMatrix vander(OGMatrix aMatrix) {
    return Vander.vander(aMatrix, Math.max(aMatrix.getNumberOfRows(), aMatrix.getNumberOfColumns()));
  }
}
