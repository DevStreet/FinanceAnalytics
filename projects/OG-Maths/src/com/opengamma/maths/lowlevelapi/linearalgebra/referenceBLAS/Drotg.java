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
 * Does DROTG, See {@linkplain BLASAPIInterface}
 */
public class Drotg {
  public static void drotg(double da, double db, double c, double s) {

    double r = 0.0d;
    double roe = 0.0d;
    double scale = 0.0d;
    double z = 0.0d;
    roe = db;
    if ((Math.abs(da) > Math.abs(db))) {
      roe = da;
    }
    scale = (Math.abs(da) + Math.abs(db));
    if ((scale == 0.0e0)) {
      c = 1.0e0;
      s = 0.0e0;
      r = 0.0e0;
      z = 0.0e0;
    } else {
      r = scale * Math.sqrt((da / scale) * (da / scale) + (db / scale) * (db / scale));
      r = Math.copySign(1.0e0, roe) * r;
      c = (da / r);
      s = (db / r);
      z = 1.0e0;
      if ((Math.abs(da) > Math.abs(db))) {
        z = s; 
      }
      if (((Math.abs(db) >= Math.abs(da)) && (c != 0.0e0))) {
        z = (1.0e0 / c);
      }
    }
    da = r;
    db = z;
    return;
  }
} // End class.

