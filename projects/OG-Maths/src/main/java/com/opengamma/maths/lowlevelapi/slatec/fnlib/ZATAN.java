/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.maths.commonapi.MathsConstants;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.complexbuiltins.ComplexArithmetic;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;

/**
 * ZATAN(Z) computes the complex trigonometric arc tangent of the argument.
 * The result is in units of radians, and the real part is in the first
 * or fourth quadrant. See also ZATAN2.
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * See http://www.netlib.org/slatec/fnlib/catan.f
 * Major difference is that this is in double precision arithmetic
 */
public class ZATAN {
  private static final Logger s_log = LoggerFactory.getLogger(ZATAN.class);
  private static final double s_halfPi = MathsConstants.halfpi;
  // NTERMS = LOG(EPS)/LOG(RBND) WHERE RBND = 0.1
  // -0.4343 is a magic number from 4 byte real precision complex, should still work.
  // -0.4343 * Math.log(D1mach.three()) ~= log(eps)/log(0.1) but saves calling log twice
  private static final int NTERMS = (int) (-0.4343 * Math.log(D1mach.three()) + 1.0);
  private static final double SQEPS = Math.sqrt(D1mach.four());
  private static final double RMIN = Math.sqrt(3.0d * D1mach.three());
  private static final double RMAX = 1.0 / D1mach.three();

  public static double[] zatan(final double[] z) {
    double r = ZABS.zabs(z[0], z[1]);
    double[] zatan = new double[2];
    if (r < 0.1) {
      double[] z2 = new double[2];
      zatan[0] = z[0];
      zatan[1] = z[1];
      if (r < RMIN) {
        return zatan;
      }
      zatan[0] = 0.0d;
      zatan[1] = 0.0d;
      z2 = ComplexArithmetic.cmultiply(z, z);
      int twoi;
      double[] z2timeszatan;
      for (int i = 1; i <= NTERMS; i++) {
        twoi = 2 * (NTERMS - i) + 1;
        z2timeszatan = ComplexArithmetic.cmultiply(z2, zatan);
        zatan[0] = 1.0d / twoi - z2timeszatan[0];
        zatan[1] = -z2timeszatan[1]; // negate, no real part
      }
      zatan = ComplexArithmetic.cmultiply(z, zatan);
    } else if (r > RMAX) {
      zatan[0] = s_halfPi;
      if (z[0] < 0.0) {
        zatan[0] = -s_halfPi;
      }
    } else {
      double x = z[0];
      double y = z[1];
      double r2 = r * r;

      if (r2 == 1.d && x == 0) {
        throw new MathsExceptionIllegalArgument("ZATAN: Z is +i or -i");
      }
      if (Math.abs(r2 - 1.d) < SQEPS) {
        s_log.warn("ZATAN: Answer is less than half available precision, Z^2 close to -1");
      }

      double xans = 0.5d * Math.atan2(2.0d * x, 1.0d - r2);
      double yans = 0.25d * Math.log((r2 + 2.0d * y + 1.0) / (r2 - 2.0d * y + 1.0));
      zatan[0] = xans;
      zatan[1] = yans;

    }
    return zatan;
  }

}
