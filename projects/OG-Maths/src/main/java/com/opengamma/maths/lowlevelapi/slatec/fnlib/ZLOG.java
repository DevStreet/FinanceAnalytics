/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.maths.lowlevelapi.slatec.fnlib;

import com.opengamma.maths.commonapi.MathsConstants;

/**
 * Computes the logarithm of a complex number, wrapping the argument appropriately.
 * This code is an approximate translation of the equivalent function in the "Public Domain" code from SLATEC, see:
 * http://www.netlib.org/slatec/src/zlog.f
 */
public class ZLOG {
  /**
   * Computes the logarithm of a complex number, wrapping the argument appropriately.
   * @param ar the real part of the complex number of which the logarithm shall be computed
   * @param ai the imaginary part of the complex number of which the logarithm shall be computed
   * @param br the real part of the complex number resulting from taking the logarithm of ar+ai*i
   * @param bi the imaginary part of the complex number resulting from taking the logarithm of ar+ai*i
   * @param ierr contains 0 if successful, 1 otherwise.
   */
  public static void zlog(double ar, double ai, double[] br, double[] bi, int[] ierr) {
    double zm;
    double dhpi = MathsConstants.halfpi;
    double dpi = MathsConstants.pi;
    double dtheta;
    ierr[0] = 0;

    if (ar == 0) {
      if (ai == 0) {
        ierr[0] = 1;
        return;
      } else {
        bi[0] = dhpi;
        br[0] = Math.log(Math.abs(ai));
        if (ai < 0) {
          bi[0] = -bi[0];
        }
        return;
      }
    } else if (ai == 0) {
      if (ar > 0.0) {
        br[0] = Math.log(ar);
        bi[0] = 0.d;
        return;
      } else {
        br[0] = Math.log(Math.abs(ar));
        bi[0] = dpi;
        return;
      }
    } else {
      dtheta = Math.atan(ai / ar);
      if (dtheta <= 0d) { // this double branch is right, just looks odd from flow indirection produced by GOTO
        if (ar < 0d) {
          dtheta = dtheta + dpi;
        }
      } else {
        if (ar < 0d) {
          dtheta = dtheta - dpi;
        }
      }
      zm = ZABS.zabs(ar, ai);
      br[0] = Math.log(zm);
      bi[0] = dtheta;
      return;
    }

  }
}
