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
 * Does DASUM, See {@linkplain BLASAPIInterface}
 */
public class Dasum {

  public static double dasum(int n, double[] x, int xOffset, int incx) {
    double dtemp = 0.0d;
    int i = 0;
    int m = 0;
    int mp1 = 0;
    dtemp = 0.0e0;
    if (((n <= 0) || (incx <= 0))) {
      return dtemp;
    }
    if ((incx == 1)) {
      m = n % 6;
      if ((m != 0)) {
        for (i = 0; i < m; i++) {
          dtemp = (dtemp + Math.abs(x[i + xOffset]));
        }
        if (n < 6) {
          return dtemp;
        }
      }
      mp1 = (m + 1);
      int iInc = 6;
      for (i = mp1; i < n; i += iInc) {
        dtemp = ((((((dtemp + Math.abs(x[i + xOffset])) + Math.abs(x[i + 1 + xOffset])) + Math.abs(x[i + 2 + xOffset])) + Math.abs(x[i + 3 + xOffset])) + Math.abs(x[i + 4 +
            xOffset])) + Math.abs(x[i + 5 + xOffset]));
      }
    } else {
      {
        int iInc = incx;
        for (i = 0; i < n; i += iInc) {
          dtemp = dtemp + Math.abs(x[i + xOffset]);
        }
      }
    }

    return dtemp;
  }
}
