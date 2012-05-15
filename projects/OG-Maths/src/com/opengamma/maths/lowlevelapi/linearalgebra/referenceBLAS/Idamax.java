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
 * Does IDAMAX, See {@linkplain BLASAPIInterface}
 */
public class Idamax {

  public static int idamax(int n, double[] x, int xOffset, int incx) {

    double dmax = 0.0d;
    int i = 0;
    int ix = 0;
    int idamax = 0;
    idamax = 0;
    if (((n < 1) || (incx <= 0))) {
      return idamax;
    }
    idamax = 1;
    if ((n == 1)) {
      return idamax;
    }
    if ((incx == 1)) {
      dmax = Math.abs(x[xOffset]);
      for (i = 1; i < n; i++) {
        if ((Math.abs(x[i + xOffset]) > dmax)) {
          idamax = i;
          dmax = Math.abs(x[i + xOffset]);
        }
      }
    } else {
      // code for increment not equal to 1
      ix = 1;
      dmax = Math.abs(x[xOffset]);
      ix = (ix + incx);
      for (i = 1; i < n; i++) {
        if ((Math.abs(x[ix + xOffset]) > dmax)) {
          idamax = i;
          dmax = Math.abs(x[ix + xOffset]);
        }
        ix = (ix + incx);
      }
    }
    return idamax;
  }
} // End class.
