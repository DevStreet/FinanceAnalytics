/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.ogblas.auxiliary.D1MACH;

/**
 * Provides the log Gamma correction factor so that
 * LOG(DGAMMA(X)) = LOG(SQRT(2*PI)) + (X-5.)*LOG(X) - X + D9LGMC(X).
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * http://www.netlib.org/slatec/fnlib/d9lgmc.f
 */
public class D9LGMC {
  private static Logger s_log;
  private static double[] s_dALGMCS = new double[15]; 
  private static int s_dNALGM; 
  private static double s_dXBIG, s_dXMAX; 
  static {
    s_dALGMCS[0] = +.1666389480451863247205729650822e+0;
    s_dALGMCS[1] = -.1384948176067563840732986059135e-4;
    s_dALGMCS[2] = +.9810825646924729426157171547487e-8;
    s_dALGMCS[3] = -.1809129475572494194263306266719e-10;
    s_dALGMCS[4] = +.6221098041892605227126015543416e-13;
    s_dALGMCS[5] = -.3399615005417721944303330599666e-15;
    s_dALGMCS[6] = +.2683181998482698748957538846666e-17;
    s_dALGMCS[7] = -.2868042435334643284144622399999e-19;
    s_dALGMCS[8] = +.3962837061046434803679306666666e-21;
    s_dALGMCS[9] = -.6831888753985766870111999999999e-23;
    s_dALGMCS[10] = +.1429227355942498147573333333333e-24;
    s_dALGMCS[11] = -.3547598158101070547199999999999e-26;
    s_dALGMCS[12] = +.1025680058010470912000000000000e-27;
    s_dALGMCS[13] = -.3401102254316748799999999999999e-29;
    s_dALGMCS[14] = +.1276642195630062933333333333333e-30;
    s_dNALGM = INITDS.initds(s_dALGMCS, 15, (float) D1MACH.three());
    s_dXBIG = 1.d / Math.sqrt(D1MACH.three());
    s_dXMAX = Math.exp(Math.min(Math.log(D1MACH.two() / 12.d), -Math.log(12.d * D1MACH.one())));
    s_log = LoggerFactory.getLogger(D9LGMC.class);
  }

  public static double d9lgmc(double x) {
    double d9lgmc = 0;
    Catchers.catchValueShouldBeGreaterThanOrEqualToXFromArgList(x, 10, 1);
    if (x < s_dXMAX) {
      d9lgmc = 1.d / (12.d * x);
      if (x < s_dXBIG) {
        d9lgmc = DCSEVL.dcsevl(2.d * (10.d / x) * (10.d / x) - 1.d, s_dALGMCS, s_dNALGM) / x;
      }
    } else {
      s_log.warn("X so big that D9LGMC underflows");
    }
    return d9lgmc;
  }

}
