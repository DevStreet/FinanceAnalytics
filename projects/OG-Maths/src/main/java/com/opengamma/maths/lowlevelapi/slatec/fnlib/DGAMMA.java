/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionOverflow;
import com.opengamma.maths.lowlevelapi.functions.MathE;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;

/**
 * Computes the true complete Gamma function at position 'x'
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * http://www.netlib.org/slatec/fnlib/dgamma.f
 */
public class DGAMMA {
  private static Logger s_log = LoggerFactory.getLogger(DGAMMA.class);

  // CSOFF
  private static double[] s_gamcs = new double[] { //
  +0.8571195590989331421920062399942e-2, //
      +0.4415381324841006757191315771652e-2, //
      +0.5685043681599363378632664588789e-1, //
      -0.4219835396418560501012500186624e-2, //
      +0.1326808181212460220584006796352e-2, //
      -0.1893024529798880432523947023886e-3, //
      +0.3606925327441245256578082217225e-4, //
      -0.6056761904460864218485548290365e-5, //
      +0.1055829546302283344731823509093e-5, //
      -0.1811967365542384048291855891166e-6, //
      +0.3117724964715322277790254593169e-7, //
      -0.5354219639019687140874081024347e-8, //
      +0.9193275519859588946887786825940e-9, //
      -0.1577941280288339761767423273953e-9, //
      +0.2707980622934954543266540433089e-10, //
      -0.4646818653825730144081661058933e-11, //
      +0.7973350192007419656460767175359e-12, //
      -0.1368078209830916025799499172309e-12, //
      +0.2347319486563800657233471771688e-13, //
      -0.4027432614949066932766570534699e-14, //
      +0.6910051747372100912138336975257e-15, //
      -0.1185584500221992907052387126192e-15, //
      +0.2034148542496373955201026051932e-16, //
      -0.3490054341717405849274012949108e-17, //
      +0.5987993856485305567135051066026e-18, //
      -0.1027378057872228074490069778431e-18, //
      +0.1762702816060529824942759660748e-19, //
      -0.3024320653735306260958772112042e-20, //
      +0.5188914660218397839717833550506e-21, //
      -0.8902770842456576692449251601066e-22, //
      +0.1527474068493342602274596891306e-22, //
      -0.2620731256187362900257328332799e-23, //
      +0.4496464047830538670331046570666e-24, //
      -0.7714712731336877911703901525333e-25, //
      +0.1323635453126044036486572714666e-25, //
      -0.2270999412942928816702313813333e-26, //
      +0.3896418998003991449320816639999e-27, //
      -0.6685198115125953327792127999999e-28, //
      +0.1146998663140024384347613866666e-28, //
      -0.1967938586345134677295103999999e-29, //
      +0.3376448816585338090334890666666e-30, //
      -0.5793070335782135784625493333333e-31 };
  // CSON

  private static double s_pi = 3.14159265358979323846264338327950d;
  private static double s_sq2pil = 0.91893853320467274178032973640562d;

  private static int s_ngam;
  private static double s_xmin, s_xmax, s_dxrel;
  static {
    s_ngam = INITDS.initds(s_gamcs, 42, 0.1 * D1mach.three());
    double[] tmp1 = new double[1];
    double[] tmp2 = new double[1];
    DGAMLM.dgamlm(tmp1, tmp2);
    s_xmin = tmp1[0];
    s_xmax = tmp2[0];
    s_dxrel = Math.sqrt(D1mach.four());
  }

  public static double dgamma(double x) {
    double y = Math.abs(x);
    double dgamma;
    if (y <= 10.d) {
      // gamma(x) for -xbnd <= x <= xbnd
      int n = (int) MathE.trunc(x);
      if (x < 0.d) {
        n--;
      }
      y = x - n;
      n--;
      dgamma = 0.9375d + DCSEVL.dcsevl(2.d * y - 1.d, s_gamcs, s_ngam);
      if (n == 0) {
        return dgamma;
      }
      if (n <= 0) {
        n = -n;
        if (x == 0.d) {
          throw new MathsExceptionIllegalArgument("x is zero, gamma(x) = Inf");
        }
        if (x < 0.d && x + n - 2 == 0.d) {
          throw new MathsExceptionIllegalArgument("x is a negative integer, gamma(x) = Inf");
        }
        if (x < (-0.5d) && Math.abs((x - MathE.trunc(x - 0.5d)) / x) < s_dxrel) {
          s_log.warn("DGAMMA: result is less than half precision because x is near a negative integer");
        }
        for (int i = 1; i <= n; i++) {
          dgamma = dgamma / (x + i - 1);
        }
        return dgamma;
      } else {
        for (int i = 1; i <= n; i++) {
          dgamma = (y + i) * dgamma;
        }
        return dgamma;
      }
    } else { // label 50, gamma(x) for abs(x) > 10;
      if (x > s_xmax) {
        throw new MathsExceptionOverflow("x so large that gamma(x) overflows");
      }
      dgamma = 0.d;
      if (x < s_xmin) {
        s_log.warn("x so small that gamma underflows");
        return dgamma;
      }

      dgamma = Math.exp((y - 0.5d) * Math.log(y) - y + s_sq2pil + D9LGMC.d9lgmc(y));
      if (x > 0.d) {
        return dgamma;
      }

      if (Math.abs((x - MathE.trunc(x - 0.5d)) / x) < s_dxrel) {
        s_log.warn("DGAMMA: result is less than half precision because x is near a negative integer");
      }

      double sinpiy = Math.sin(s_pi * y);
      if (sinpiy == 0.d) {
        throw new MathsExceptionIllegalArgument("x is a negative integer, gamma(x) = Inf");
      }

      dgamma = -s_pi / (y * sinpiy * dgamma);
      return dgamma;
    }
  }

}
