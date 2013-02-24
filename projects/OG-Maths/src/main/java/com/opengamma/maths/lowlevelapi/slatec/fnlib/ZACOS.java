/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import com.opengamma.maths.commonapi.MathsConstants;

/**
 * ZACOS(Z) computes the trigonometric inverse cosine of the complex argument.
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * See http://www.netlib.org/slatec/fnlib/cacos.f
 * Major difference is that this is in double precision arithmetic
 */
public class ZACOS {

  public static double[] zacos(double[] zinp) {
    double[] asin = ZASIN.zasin(zinp);
    asin[0] = MathsConstants.halfpi - asin[0];
    asin[1] = -asin[1];
    return asin;
  }
}
