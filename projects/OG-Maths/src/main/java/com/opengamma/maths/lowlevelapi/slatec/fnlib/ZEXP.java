/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

/**
 * Computes the exponential of a complex number
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * http://www.netlib.org/slatec/src/zexp.f
 */
public class ZEXP {

  /**
   * Computes the exponential of a complex number
   * @param ar the real part of the complex number of which the exponential shall be computed
   * @param ai the imaginary part of the complex number of which the exponential shall be computed
   * @param br the real part of the complex number resulting from taking the exponential of ar+ai*i
   * @param bi the imaginary part of the complex number resulting from taking the exponential of ar+ai*i
   */
  static void zexp(double ar, double ai, double[] br, double[] bi) {
    double zm, ca, cb;
    zm = Math.exp(ar);
    ca = zm * Math.cos(ai);
    cb = zm * Math.sin(ai);
    br[0] = ca;
    bi[0] = cb;
  }

}
