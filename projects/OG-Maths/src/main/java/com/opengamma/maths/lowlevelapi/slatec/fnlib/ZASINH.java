/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

/**
 * 
 */
public class ZASINH {

  public static double[] zasinh(double[] x) {
    double[] tmp = new double[2];
    tmp[0] = -x[1];
    tmp[1] = x[0];
    tmp = ZASIN.zasin(tmp);
    double foo = tmp[0];
    tmp[0] = tmp[1];
    tmp[1] = -foo;
    return tmp;
  }
}
