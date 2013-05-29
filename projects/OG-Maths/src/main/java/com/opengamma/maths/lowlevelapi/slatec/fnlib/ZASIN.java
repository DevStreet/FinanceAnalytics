/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import com.opengamma.maths.commonapi.MathsConstants;
import com.opengamma.maths.lowlevelapi.complexbuiltins.ComplexArithmetic;
import com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas.D1mach;

/**
 * ZASIN(Z) computes the trigonometric inverse sine of the complex argument.
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * See http://www.netlib.org/slatec/fnlib/casin.f
 * Major difference is that this is in double precision arithmetic
 */
public class ZASIN {
  private static int s_nterms = (int) (-0.4343 * Math.log(D1mach.three()));
  private static double s_rmin = Math.sqrt(6. * D1mach.three());

  public static double[] zasin(double[] zinp) {
    double[] z = new double[] {zinp[0], zinp[1] };
    double r = ZABS.zabs(z[0], z[1]);
    double[] zasin = new double[2];
    double[] I = new double[] {0, 1 }; //CSIGNORE
    if (r <= 0.1) {
      zasin[0] = z[0];
      zasin[1] = z[1];
      if (r < s_rmin) {
        return zasin;
      }
      zasin[0] = 0;
      zasin[1] = 0;
      double[] z2 = ComplexArithmetic.cmultiply(z, z);
      double twoi;
      double[] btm = new double[2];
      for (int i = 1; i <= s_nterms; i++) {
        twoi = 2 * (s_nterms - i) + 1;
        btm[0] = twoi + 1;
        zasin = ComplexArithmetic.cdivide(ComplexArithmetic.cmultiply(zasin, z2), btm);
        zasin[0] *= twoi;
        zasin[1] *= twoi;
        zasin[0] += 1 / twoi;
      }
      zasin = ComplexArithmetic.cmultiply(z, zasin);

    } else { // this is about 3 lines in fortran/C
      if (zinp[0] < 0.) {
        z[0] = -zinp[0];
        z[1] = -zinp[1];
      }
      double[] zp1 = new double[] {z[0] + 1, z[1] };
      double[] zm1 = new double[] {z[0] - 1, z[1] };
      double[] sqzp1 = ZSQRT.zsqrt(zp1);
      if (sqzp1[1] < 0) {
        sqzp1[0] = -sqzp1[0];
        sqzp1[1] = -sqzp1[1];
      }
      double[] sqzm1 = ZSQRT.zsqrt(zm1);
      double[] tmp = ComplexArithmetic.cmultiply(sqzp1, sqzm1);
      tmp[0] += z[0];
      tmp[1] += z[1];
      double[] bi = new double[1];
      double[] br = new double[1];
      int[] ierr = new int[1];
      ZLOG.zlog(tmp[0], tmp[1], br, bi, ierr);
      tmp[0] = br[0];
      tmp[1] = bi[0];
     
      zasin = ComplexArithmetic.cmultiply(I, tmp);
      zasin[0] = MathsConstants.halfpi - zasin[0];
      zasin[1] = -zasin[1];

      if (zasin[0] > MathsConstants.halfpi) {
        zasin[0] = MathsConstants.pi - zasin[0];
        zasin[1] = -zasin[1];
      }
      if (zasin[0] <= (-MathsConstants.halfpi)) {
        zasin[0] = -MathsConstants.pi - zasin[0];
        zasin[1] = -zasin[1];
      }
      if (zinp[0] < 0) {
        zasin[0] = -zasin[0];
        zasin[1] = -zasin[1];
      }

    }
    return zasin;
  }
}
