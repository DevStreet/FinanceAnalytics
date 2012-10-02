/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.highlevelapi.functions.DOGMAFunctions.DOGMASpecialMatrices;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionOverflow;
import com.opengamma.maths.highlevelapi.datatypes.primitive.OGDoubleArray;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.slatec.fnlib.DBINOM;

/**
 * Generates inverse Hilbert matrices of order "n". 
 * For large "n" the inverse is correct within the limits of binomial expansion
 * products provided by {@link DBINOM}.
 * 
 * For "nice" formula derivation options see:
 * Tricks or Treats with the Hilbert Matrix
 * Man-Duen Choi
 * The American Mathematical Monthly
 * Vol. 90, No. 5 (May, 1983), pp. 301-312
 */
public class Invhilb {

  public static OGDoubleArray invhilb(int n) {
    Catchers.catchValueShouldNotBeNegativeOrZeroFromArgList(n, 1);
    double[] data = new double[n * n];
    double[] factors = new double[n];
    // compute factors, matrix is outer product of these, the sign twiddles are horrible, fix them. If we had union{} it is easy. 
    boolean flip = false;
    int ip1, in;
    double ifac;
    final int magicOverFlow = 203; // this is the magic value for n at which the computation overflows double
    if (n > magicOverFlow) {
      throw new MathsExceptionOverflow("invhilb overflows for n=203+ because calls to DBINOM(~204,~102) are squared");
    }
    // calculate the reduced binomial expansion based factors
    for (int i = 0; i < n; i++) {
      factors[i] = (i + 1) * DBINOM.dbinom(i + n, i) * DBINOM.dbinom(n, i + 1);
      if (flip) {
        factors[i] = -factors[i];
        flip = false;
      } else {
        flip = true;
      }
    }
    // do an outer product these factors with a scaling on the bottom
    // when n = 203, which gives max(factors)=~4e154, so at its limit, when squared, just about stays inside the limits of a 64bit double
    for (int i = 0; i < n; i++) {
      ip1 = i + 1;
      in = i * n;
      ifac = factors[i];
      for (int j = 0; j < n; j++) {
        data[in + j] = ifac * factors[j] / (ip1 + j);
      }
    }
    // return
    OGDoubleArray tmp = new OGDoubleArray(data, n, n);
    return tmp;
  }

}
