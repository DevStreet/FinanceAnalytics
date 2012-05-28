/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
/**
* This code is a translation of the LAPACK code provided by netlib.org.
*/
package com.opengamma.maths.lowlevelapi.linearalgebra.referenceBLAS;

/**
 * Double precision machine parameters 
 */
public class Dlamch {
  /*
  'E' or 'e',   DLAMCH := eps
  'S' or 's ,   DLAMCH := sfmin
  'B' or 'b',   DLAMCH := base
  'P' or 'p',   DLAMCH := eps*base
  'N' or 'n',   DLAMCH := t
  'R' or 'r',   DLAMCH := rnd
  'M' or 'm',   DLAMCH := emin
  'U' or 'u',   DLAMCH := rmin
  'L' or 'l',   DLAMCH := emax
  'O' or 'o',   DLAMCH := rmax
  */
  public static double dlamch(char cmach) {
    final double one = 1e0;
    final double zero = 0e0;
    double rnd, eps, sfmin, small, rmach;

    rnd = one;
    if (one == rnd) {
      eps = Double.longBitsToDouble(4368491638549381120L); // EPSILON / 2
    } else {
      eps = Double.longBitsToDouble(4372995238176751616L); // EPSILON
    }

    if (cmach == 'E' || cmach == 'e') {
      rmach = eps;
    } else if (cmach == 'S' || cmach == 's') {
      sfmin = Double.longBitsToDouble(4368491638549381120L); // TINY
      small = one / Double.longBitsToDouble(0x7fefffffffffffffL); // ONE/HUGE
      if (small >= sfmin) {
        sfmin = small * (one + eps);
      }
      rmach = sfmin;
    } else if (cmach == 'B' || cmach == 'b') {
      rmach = 2; // Java radix
    } else if (cmach == 'P' || cmach == 'p') {
      rmach = eps * 2;
    } else if (cmach == 'N' || cmach == 'n') {
      rmach = 53; // mantissa width of double
    } else if (cmach == 'R' || cmach == 'r') {
      rmach = rnd;
    } else if (cmach == 'M' || cmach == 'm') {
      rmach = Double.MIN_EXPONENT;
    } else if (cmach == 'U' || cmach == 'u') {
      rmach = Double.longBitsToDouble(4368491638549381120L);
    } else if (cmach == 'L' || cmach == 'l') {
      rmach = Double.MAX_EXPONENT;      
    } else if (cmach == 'O' || cmach == 'o') {
      rmach = Double.longBitsToDouble(0x7fefffffffffffffL);
    } else {
      rmach = zero;
    }
    return rmach;
  }
}
