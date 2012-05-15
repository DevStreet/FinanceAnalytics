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
package com.opengamma.maths.lowlevelapi.linearalgebra.referenceBLAS;

import com.opengamma.maths.lowlevelapi.exposedapi.BLASBacking.BLASAPIInterface;

/**
 * Does DROTM, See {@linkplain BLASAPIInterface}
 */
public class Drotm {
  private static double s_zero;
  private static double s_two = 2.e0;

  public static void drotm(int n, double[] x, int xOffset, int incx, double[] y, int yOffset, int incy, double[] dparam, int dparamOffset) {
    double dflag = 0.0d;
    double dh11 = 0.0d;
    double dh12 = 0.0d;
    double dh21 = 0.0d;
    double dh22 = 0.0d;
    double w = 0.0d;
    double z = 0.0d;
    int i = 0;
    int kx = 0;
    int ky = 0;
    int nsteps = 0;
    dflag = dparam[0 + dparamOffset];
    if ((n <= 0) || (((dflag + s_two) == s_zero))) {
      return;
    }
    if (incx == incy && incx > 0) {
      nsteps = n * incx;
      if (dflag < 0) {
        dh11 = dparam[1 + dparamOffset];
        dh12 = dparam[3 + dparamOffset];
        dh21 = dparam[2 + dparamOffset];
        dh22 = dparam[4 + dparamOffset];
        int iInc = incx;
        for (i = 0; (iInc < 0) ? i > nsteps : i < nsteps; i += iInc) //stu-query?
        {
          w = x[i + xOffset];
          z = y[i + yOffset];
          x[i + xOffset] = w * dh11 + z * dh12;
          y[i + yOffset] = w * dh21 + z * dh22;
        }
      } else if (dflag == 0) {
        dh12 = dparam[3 + dparamOffset];
        dh21 = dparam[2 + dparamOffset];
        int iInc = incx;
        for (i = 0; (iInc < 0) ? i > nsteps : i < nsteps; i += iInc) //stu-query?
        {
          w = x[i + xOffset];
          z = y[i + yOffset];
          x[i + xOffset] = w + z * dh12;
          y[i + yOffset] = w * dh21 + z;
        }
      } else {
        dh11 = dparam[1 + dparamOffset];
        dh22 = dparam[4 + dparamOffset];
        int iInc = incx;
        for (i = 0; (iInc < 0) ? i > nsteps : i < nsteps; i += iInc)//stu-query?
        {
          w = x[i + xOffset];
          z = y[i + yOffset];
          x[i + xOffset] = w * dh11 + z;
          y[i + yOffset] = dh22 * z - w;
        }
      }
    } else {
      kx = 1;
      ky = 1;
      if ((incx < 0)) {
        kx = (1 + (((1 - n)) * incx));
      }
      if ((incy < 0)) {
        ky = (1 + (((1 - n)) * incy));
      }
      if (dflag < 0) {
        dh11 = dparam[1 + dparamOffset];
        dh12 = dparam[3 + dparamOffset];
        dh21 = dparam[2 + dparamOffset];
        dh22 = dparam[4 + dparamOffset];
        for (i = 0; i < n; i++) {
          w = x[kx + xOffset];
          z = y[ky + yOffset];
          x[kx + xOffset] = w * dh11 + z * dh12;
          y[ky + yOffset] = w * dh21 + z * dh22;
          kx = (kx + incx);
          ky = (ky + incy);
        }
      } else if (dflag == 0) {
        dh12 = dparam[3 + dparamOffset];
        dh21 = dparam[2 + dparamOffset];
        for (i = 0; i < n; i++) {
          w = x[kx + xOffset];
          z = y[ky + yOffset];
          x[kx + xOffset] = (w + (z * dh12));
          y[ky + yOffset] = ((w * dh21) + z);
          kx = (kx + incx);
          ky = (ky + incy);
        }
      } else {
        dh11 = dparam[1 + dparamOffset];
        dh22 = dparam[4 + dparamOffset];
        for (i = 0; i < n; i++) {
          w = x[kx + xOffset];
          z = y[ky + yOffset];
          x[kx + xOffset] = w * dh11 + z;
          y[ky + yOffset] = dh22 * z - w;
          kx = (kx + incx);
          ky = (ky + incy);
        }
      }
    }

    return;
  }
} // End class.
