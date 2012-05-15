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
 * Does DSCAL, See {@linkplain BLASAPIInterface}
 */
public class Dscal {
  public static void dscal(int n, double alpha, double[] x, int xOffset, int incx) {
    int i = 0;
    int m = 0;
    int mp1 = 0;
    int nincx = 0;
    if (n <= 0 || incx <= 0) {
      return;
    }
    if (incx == 1) {
      m = (n) % (5);
      if (m != 0) {
        for (i = 0; i < m; i++) {
          x[i + xOffset] = alpha * x[i + xOffset];
        }
        if ((n < 5)) {
          return;
        }
      }
      mp1 = m;
      int iInc = 5;
      for (i = mp1; i < n; i += iInc) { // stu - query?
        x[i + xOffset] = alpha * x[i + xOffset];
        x[i + 1 + xOffset] = alpha * x[i + 1 + xOffset];
        x[i + 2 + xOffset] = alpha * x[i + 2 + xOffset];
        x[i + 3 + xOffset] = alpha * x[i + 3 + xOffset];
        x[i + 4 + xOffset] = alpha * x[i + 4 + xOffset];
      }

    } else {
      // *        code for increment not equal to 1
      nincx = (n * incx);
      int iInc = incx;
      for (i = 0; (iInc < 0) ? i > nincx : i < nincx; i += iInc) // stu - query?
      {
        x[i + xOffset] = alpha * x[i + xOffset];
      }
    }
    return;
  }
} // End class.

