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
 * Does DROTG, See {@linkplain BLASAPIInterface}
 */
public class Drotg {
  public static void drotg(double[] da, double[] db, double[] c, double[] s) {

    double r = 0.0d;
    double roe = 0.0d;
    double scale = 0.0d;
    double z = 0.0d;
    roe = db[0];
    if ((Math.abs(da[0]) > Math.abs(db[0]))) {
      roe = da[0];
    }
    scale = (Math.abs(da[0]) + Math.abs(db[0]));
    if ((scale == 0.0e0)) {
      c[0] = 1.0e0;
      s[0] = 0.0e0;
      r = 0.0e0;
      z = 0.0e0;
    } else {
      r = scale * Math.sqrt((da[0] / scale) * (da[0] / scale) + (db[0] / scale) * (db[0] / scale));
      r = Math.copySign(1.0e0, roe) * r;
      c[0] = (da[0] / r);
      s[0] = (db[0] / r);
      z = 1.0e0;
      if ((Math.abs(da[0]) > Math.abs(db[0]))) {
        z = s[0]; 
      }
      if (((Math.abs(db[0]) >= Math.abs(da[0])) && (c[0] != 0.0e0))) {
        z = (1.0e0 / c[0]);
      }
    }
    da[0] = r;
    db[0] = z;
    return;
  }
} // End class.

