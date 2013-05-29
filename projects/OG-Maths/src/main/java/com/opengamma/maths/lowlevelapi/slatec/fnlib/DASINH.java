/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;

/**
 * Computes the inverse hyperbolic sine at position 'x'
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * http://www.netlib.org/slatec/fnlib/dasinh.f
 */
public class DASINH {

  //CSOFF // we keep DATA// blocks as written
  private static double[] s_asnhcs = new double[] { //
  -0.12820039911738186343372127359268e+0, //
      -0.58811761189951767565211757138362e-1, //
      +0.47274654322124815640725249756029e-2, //
      -0.49383631626536172101360174790273e-3, //
      +0.58506207058557412287494835259321e-4, //
      -0.74669983289313681354755069217188e-5, //
      +0.10011693583558199265966192015812e-5, //
      -0.13903543858708333608616472258886e-6, //
      +0.19823169483172793547317360237148e-7, //
      -0.28847468417848843612747272800317e-8, //
      +0.42672965467159937953457514995907e-9, //
      -0.63976084654366357868752632309681e-10, //
      +0.96991686089064704147878293131179e-11, //
      -0.14844276972043770830246658365696e-11, //
      +0.22903737939027447988040184378983e-12, //
      -0.35588395132732645159978942651310e-13, //
      +0.55639694080056789953374539088554e-14, //
      -0.87462509599624678045666593520162e-15, //
      +0.13815248844526692155868802298129e-15, //
      -0.21916688282900363984955142264149e-16, //
      +0.34904658524827565638313923706880e-17, //
      -0.55785788400895742439630157032106e-18, //
      +0.89445146617134012551050882798933e-19, //
      -0.14383426346571317305551845239466e-19, //
      +0.23191811872169963036326144682666e-20, //
      -0.37487007953314343674570604543999e-21, //
      +0.60732109822064279404549242880000e-22, //
      -0.98599402764633583177370173440000e-23, //
      +0.16039217452788496315232638293333e-23, //
      -0.26138847350287686596716134399999e-24, //
      +0.42670849606857390833358165333333e-25, //
      -0.69770217039185243299730773333333e-26, //
      +0.11425088336806858659812693333333e-26, //
      -0.18735292078860968933021013333333e-27, //
      +0.30763584414464922794065920000000e-28, //
      -0.50577364031639824787046399999999e-29, //
      +0.83250754712689142224213333333333e-30, //
      -0.13718457282501044163925333333333e-30, //
      +0.22629868426552784104106666666666e-31 };
  //CSON

  private static double s_dln2 = 0.69314718055994530941723212145818d;
  private static int s_nterms;
  private static double s_sqeps, s_xmax;

  static {
    s_nterms = INITDS.initds(s_asnhcs, 39, 0.1d * D1mach.three());
    s_sqeps = Math.sqrt(D1mach.three());
    s_xmax = 1.d / s_sqeps;
  }

  public static double dasinh(final double x) {
    double y;
    y = Math.abs(x);
    if (y > 1.d) {
      if (y < s_xmax) {
        return Math.copySign(Math.log(y + Math.sqrt(y * y + 1.d)), x);
      } else {
        return Math.copySign(s_dln2 + Math.log(y), x);
      }
    } else {
      if (y > s_sqeps) {
        return x * (1 + DCSEVL.dcsevl(2.d * x * x - 1.d, s_asnhcs, s_nterms));
      } else {
        return x;
      }
    }

  }
}
