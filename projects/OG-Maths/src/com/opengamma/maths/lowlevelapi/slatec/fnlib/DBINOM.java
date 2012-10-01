/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.functions.checkers.Catchers;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.ogblas.auxiliary.D1MACH;

/**
 * Compute the double precision binomial coefficient for integer arguments N and M. 
 * The result is (N!)/((M!)(N-M)!).
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * http://www.netlib.org/slatec/fnlib/dbinom.f
 */
public class DBINOM {

  private static Logger s_log;
  private static double s_dSQ2PIL = 0.91893853320467274178032973640562e0;
  private static double s_dBILNMX, s_dFINTMX;
  static {
    s_dBILNMX = Math.log(D1MACH.two() - 0.0001e0);
    s_dFINTMX = 0.9e0 / D1MACH.three();
    s_log = LoggerFactory.getLogger(DBINOM.class);
  }

  public static double dbinom(final int n, final int m) {
    Catchers.catchValueShouldBeGreaterThanOrEqualToXFromArgList(n, 0, 1);
    Catchers.catchValueShouldBeGreaterThanOrEqualToXFromArgList(m, 0, 2);
    if (n < m) {
      throw new MathsExceptionIllegalArgument("n should be less than m, values given were n=" + n + ", m=" + m);
    }
    double dDBINOM;
    double xn, xk, xnk, corr;
    int k = Math.min(m, n - m);
    if (k <= 20 || k * Math.log(k * Math.max(n, 1)) <= s_dBILNMX) {
      dDBINOM = 1.d;
      if (k == 0) {
        return dDBINOM;
      }
      for (int i = 1; i <= k; i++) {
        xn = n - i + 1;
        xk = i;
        dDBINOM = dDBINOM * (xn / xk);
      }
      if (dDBINOM < s_dFINTMX) {
        dDBINOM = (long) (dDBINOM + 0.5d); // this is silly, need an AINT0() call, rounding to 32bit int is poor, 64bit is ok, EP 80bit would be better but non-existent
      }
      return dDBINOM;
    } else {
      if (k < 9) {
        s_log.warn("Result overflows because N AND/OR M is too big, precision lost. Escape 1.");
      }
      xn = n + 1;
      xk = k + 1;
      xnk = n - k + 1;
      corr = D9LGMC.d9lgmc(xn) - D9LGMC.d9lgmc(xk) - D9LGMC.d9lgmc(xnk);
      dDBINOM = xk * Math.log(xnk / xk) - xn * DLNREL.dlnrel(-(xk - 1.0D) / xn) - 0.5d * Math.log(xn * xnk / xk) + 1.D - s_dSQ2PIL + corr;

      if (dDBINOM > s_dBILNMX) {
        s_log.warn("Result overflows because N AND/OR M is too big, precision lost. Escape 2.");
      }
      dDBINOM = Math.exp(dDBINOM);
      if (dDBINOM < s_dFINTMX) {
        dDBINOM = (int) (dDBINOM + 0.5d);
      }
      return dDBINOM;
    }
  }
}
