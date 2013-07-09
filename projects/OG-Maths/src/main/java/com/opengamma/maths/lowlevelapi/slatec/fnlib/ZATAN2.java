/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import com.opengamma.maths.commonapi.MathsConstants;
import com.opengamma.maths.commonapi.exceptions.MathsExceptionIllegalArgument;
import com.opengamma.maths.lowlevelapi.complexbuiltins.ComplexArithmetic;
import com.opengamma.maths.lowlevelapi.exposedapi.SLATEC;

/**
 * ZATAN2(CSN,CCS) computes the complex trigonometric arc tangent of the ratio CSN/CCS argument
 * and ensures the real part of the result is in the correct quadrant 
 * (within a multiple of 2*pi).
 * The result is in units of radians, and the real part is between -pi and pi.
 * See also {@link ZATAN}.
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * See http://www.netlib.org/slatec/fnlib/catan2.f
 * Major difference is that this is in double precision arithmetic
 */
public class ZATAN2 {

  public static double[] zatan2(double[] csn, double[] ccs) {
    double[] zatan2;
    if (SLATEC.getInstance().zabs(ccs[0], ccs[1]) != 0.d) {
      double[] tmp = new double[2];
      tmp = ComplexArithmetic.cdivide(csn, ccs);
      zatan2 = SLATEC.getInstance().zatan(tmp);

      if (ccs[0] < 0) {
        zatan2[0] += MathsConstants.pi;
      }
      if (zatan2[0] > MathsConstants.pi) {
        zatan2[0] -= 2 * MathsConstants.pi;
      }
    } else {
      if (SLATEC.getInstance().zabs(csn[0], csn[1]) == 0d) {
        throw new MathsExceptionIllegalArgument("SLATEC: ZATAN2 called with both arguments numerically zero");
      }
      zatan2 = new double[2];
      zatan2[0] = Math.copySign(0.5 * MathsConstants.pi, csn[0]);
    }
    return zatan2;
  }
}
