/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
/**
* This code is a translation of the BLAS code provided by netlib.org.
* It has been manually edited based on the results of the f2j project.
* 
*/
package com.opengamma.maths.lowlevelapi.linearalgebra.blas.referenceblas;

import com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking.BLASAPIInterface;

/**
 * Does DROTMG, See {@linkplain BLASAPIInterface}
 */
public class Drotmg {
  private static double s_zero;
  private static double s_one = 1.e0;
  private static double s_two = 2.e0;
  private static double s_gam = 4096.e0;
  private static double s_gamsq = 16777216.e0;
  private static double s_rgamsq = 5.9604645e-8;

  public static void drotmg(double[] dd1, double[] dd2, double[] dx1, double[] dy1, double[] dparam) {
    double dflag = 0.0d;
    double dh11 = 0.0d;
    double dh12 = 0.0d;
    double dh21 = 0.0d;
    double dh22 = 0.0d;
    double dp1 = 0.0d;
    double dp2 = 0.0d;
    double dq1 = 0.0d;
    double dq2 = 0.0d;
    double dtemp = 0.0d;
    double du = 0.0d;
    if (dd1[0] < s_zero) {
      // *       GO ZERO-H-D-AND-DX1..        
      dflag = -(s_one);
      dh11 = s_zero;
      dh12 = s_zero;
      dh21 = s_zero;
      dh22 = s_zero;
      dd1[0] = s_zero;
      dd2[0] = s_zero;
      dx1[0] = s_zero;
    } else {
      // *     CASE-DD1-NONNEGATIVE
      dp2 = (dd2[0] * dy1[0]);
      if (dp2 == s_zero) {
        dflag = -(s_two);
        dparam[0] = dflag;
        return;
      }
      dp1 = (dd1[0] * dx1[0]);
      dq2 = (dp2 * dy1[0]);
      dq1 = (dp1 * dx1[0]);
      if (Math.abs(dq1) > Math.abs(dq2)) {
        dh21 = (-((dy1[0] / dx1[0])));
        dh12 = (dp2 / dp1);
        du = (s_one - (dh12 * dh21));
        if (du > s_zero) {
          dflag = s_zero;
          dd1[0] = (dd1[0] / du);
          dd2[0] = (dd2[0] / du);
          dx1[0] = (dx1[0] * du);
        }

      } else {
        if (dq2 < s_zero) {
          dflag = -(s_one);
          dh11 = s_zero;
          dh12 = s_zero;
          dh21 = s_zero;
          dh22 = s_zero;
          dd1[0] = s_zero;
          dd2[0] = s_zero;
          dx1[0] = s_zero;
        } else {
          dflag = s_one;
          dh11 = (dp1 / dp2);
          dh22 = (dx1[0] / dy1[0]);
          du = (s_one + (dh11 * dh22));
          dtemp = (dd2[0] / du);
          dd2[0] = (dd1[0] / du);
          dd1[0] = dtemp;
          dx1[0] = (dy1[0] * du);
        }
      }

      // PROCEDURE..SCALE-CHECK
      if (dd1[0] != s_zero) {
        while (dd1[0] <= s_rgamsq || dd1[0] >= s_gamsq) {
          if (dflag == s_zero) {
            dh11 = s_one;
            dh22 = s_one;
            dflag = -s_one;
          } else {
            dh21 = -s_one;
            dh12 = s_one;
            dflag = -s_one;
          }
          if (dd1[0] <= s_rgamsq) {
            dd1[0] = dd1[0] * s_gam * s_gam;
            dx1[0] = dx1[0] / s_gam;
            dh11 = dh11 / s_gam;
            dh12 = dh12 / s_gam;
          } else {
            dd1[0] = dd1[0] / (s_gam * s_gam);
            dx1[0] = dx1[0] * s_gam;
            dh11 = dh11 * s_gam;
            dh12 = dh12 * s_gam;
          }
        }
      }

      if (dd2[0] != s_zero) {
        while ((Math.abs(dd2[0]) <= s_rgamsq) || (Math.abs(dd2[0]) >= s_gamsq)) {
          if (dflag == s_zero) {
            dh11 = s_one;
            dh22 = s_one;
            dflag = -s_one;
          } else {
            dh21 = -s_one;
            dh12 = s_one;
            dflag = -s_one;
          }
          if (Math.abs(dd2[0]) <= s_rgamsq) {
            dd2[0] = dd2[0] * s_gam * s_gam;
            dh21 = dh21 / s_gam;
            dh22 = dh22 / s_gam;
          } else {
            dd2[0] = dd2[0] / (s_gam * s_gam);
            dh21 = dh21 * s_gam;
            dh22 = dh22 * s_gam;
          }
        }
      }
    }
    if (dflag < 0) {
      dparam[1] = dh11;
      dparam[2] = dh21;
      dparam[3] = dh12;
      dparam[4] = dh22;
    } else if (dflag == 0) {
      dparam[2] = dh21;
      dparam[3] = dh12;
    } else {
      dparam[1] = dh11;
      dparam[4] = dh22;
    }
    return;
  }
} // End class.

